package scaits.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import scaits.listners.CustomizedLoginEvent;
import scaits.service.CustomSuccessHandler;
import scaits.service.CustomizedServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomizedServiceImpl userService;

	@Autowired
	CustomizedLoginEvent LoginEvent;
	
	
	@Autowired
    CustomSuccessHandler customSuccessHandler;
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
    	
    	/* String hasAuthority = urmRoleStrList.stream()
    		     .map(i -> "hasAuthority('"+i.toString()+"') ")
    		     .collect(Collectors.joining("or "));*/
		
		

		http.authorizeRequests()
		    .antMatchers("/assets/**")
		    .permitAll()
		    .antMatchers("/",
		                 "/scaits","/scaits/*")
		    .access("hasAuthority('ADMIN')")
		    .anyRequest()
		    .permitAll()
		    .and()
		    .formLogin()
		    .loginPage("/login")
		    //.successHandler(customSuccessHandler)
		    .usernameParameter("username")
		    .passwordParameter("password")
		    .and()
		    .logout()
		    .logoutUrl("/logout")
		    .logoutSuccessUrl("/")
		    .and()
	          .exceptionHandling()
		    .accessDeniedPage("/myTheme1/exceptionPage.html")
		    .and()
		    .csrf()
		    .and()
		    .sessionManagement()
		      .maximumSessions(1)
		      .expiredUrl("/login")
		      ;
	}
	
	
	
	@Bean
    @Override
    public UserDetailsService userDetailsService() {
		Collection<UserDetails> users=new ArrayList<UserDetails>();
        UserDetails user =
             User.withUsername("username").password("password").roles("*").build();
        
        users.add(user);

        return new InMemoryUserDetailsManager(users);
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new PasswordEncoder() {
	        @Override
	        public String encode(CharSequence charSequence) {
	            return getMd5(charSequence.toString());
	        }

	        @Override
	        public boolean matches(CharSequence charSequence, String s) {
	            return getMd5(charSequence.toString()).equals(s);
	        }
	    };
	}
	
	public static String getMd5(String input) {
	    try {
	        // Static getInstance method is called with hashing SHA
	        MessageDigest md = MessageDigest.getInstance("MD5");

	        // digest() method called
	        // to calculate message digest of an input
	        // and return array of byte
	        byte[] messageDigest = md.digest(input.getBytes());

	        // Convert byte array into signum representation
	        BigInteger no = new BigInteger(1, messageDigest);

	        // Convert message digest into hex value
	        String hashtext = no.toString(16);

	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }

	        return hashtext;
	    }

	    // For specifying wrong message digest algorithms
	    catch (NoSuchAlgorithmException e) {
	        System.out.println("Exception thrown"
	                + " for incorrect algorithm: " + e);
	        return null;
	    }
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
		    .passwordEncoder(passwordEncoder());
		auth.authenticationEventPublisher(LoginEvent);
		auth.inMemoryAuthentication().withUser("username").password("password").roles("*").credentialsExpired(true)
        .accountExpired(true)
        .accountLocked(true);
	}

}
