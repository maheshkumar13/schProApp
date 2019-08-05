package scaits;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import scaits.config.OnlineExamFilter;

/**
 * @author mahesh.g 23-Mar-2018 10:12:04 AM
 */
// @EntityScan(basePackages ={ "scaits.bo.*","qb.bo.*"})
// @EnableJpaRepositories(basePackages={"scaits.repository","qb.repository","analysis.repository","reports.repository"})
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EntityScan(basePackages = { "scaits.bo.*"})
@ComponentScan(basePackages = { "scaits.*"})
@EnableCaching
public class ScaitsSpringBootApplication extends SpringBootServletInitializer {

	@Autowired
	private SessionRegistry sessionRegistry;

	public static void main(String[] args) {
		//System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(ScaitsSpringBootApplication.class, args);
	}


	@Bean
	public CacheManager cacheManager() {

		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(

				new ConcurrentMapCache("studentTestQuestions"), new ConcurrentMapCache("studentTestSubTopicQuestions"),
				new ConcurrentMapCache("studentTestSubSubTopicQuestions"), new ConcurrentMapCache("students"),
				new ConcurrentMapCache("student"), new ConcurrentMapCache("catVal"),
				new ConcurrentMapCache("findByOfflineExamAndQuestionAndWrong"),
				new ConcurrentMapCache("showQuestionWise"),
				new ConcurrentMapCache("studentTest"),
				new ConcurrentMapCache("getAttemptedQuestions"),
				new ConcurrentMapCache("getSubjectAttemptedQuestions"),
				new ConcurrentMapCache("getCorrectAnswers"),
				new ConcurrentMapCache("getSubjectCorrectQuestions"),
				new ConcurrentMapCache("getSceduleQuestions"),
				new ConcurrentMapCache("getSceduleSubQuestions"),
				new ConcurrentMapCache("queReport"),
				new ConcurrentMapCache("queObjReport"),
				new ConcurrentMapCache("report")));
		return cacheManager;

	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.setAutoGrowCollectionLimit(600);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
						.allowCredentials(true);
			}
		};
	}

	

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public FilterRegistrationBean dawsonApiFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new OnlineExamFilter());
		registration.addUrlPatterns("/student/*","/student/**");
		return registration;
	}

}
