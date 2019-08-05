package scaits.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import scaits.bo.employee.EmployeeBO;
import scaits.repository.EmployeeRepository;
import scaits.util.EmployeeAction;
import scaits.util.StringUtil;

@Component
public class CustomizedServiceImpl implements UserDetailsService {

	@Autowired
	EmployeeRepository userRepository;


	@Autowired
	private HttpServletRequest request;

	@Autowired
	private EmployeeAction loginEmployee;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String password = request.getParameter("password");
		if (password != null && password.length() > 0) {
			password = StringUtil.getMD5Code(password);
		}
		if (!username.chars().allMatch(Character::isDigit)) {
			EmployeeBO user = userRepository.findByPayRollIdAndPassWordAndStatus(username, password, "CURRENT");
			if (user == null) {
				throw new BadCredentialsException("Invalid username or password.");
			} else if (user != null && user.getUserRole() == null) {
				throw new BadCredentialsException("User has No role");
			} else {
				loginEmployee.currentUser = user;

				if (user.getIsValid() == 1) {
					return new org.springframework.security.core.userdetails.User(user.getPayRollId(),
							user.getPassWord(), getAuthorities(user));
				}

				if (user.getUuid() != null && user.getUuid().length() > 0) {
					String cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("x-token"))
							.findFirst().map(Cookie::getValue).orElse(null);
					if (cookie == null) {
						loginEmployee.setCurrentUser(null);
						throw new BadCredentialsException("Invalid Mac.");
					} else {
						if (!user.getUuid().equals(cookie)) {
							loginEmployee.setCurrentUser(null);
							throw new BadCredentialsException("Invalid Mac.");
						}
					}
				}

				return new org.springframework.security.core.userdetails.User(user.getPayRollId(), user.getPassWord(),
						getAuthorities(user));

			}
		}
		return null;

	}

	public Collection<? extends GrantedAuthority> getAuthorities(EmployeeBO user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		/*
		 * for (UrmRoleFormsDetailsBO privilege : list) { authorities.add(new
		 * SimpleGrantedAuthority(privilege.getForm().getFormName())); }
		 */
		return authorities;
	}

}
