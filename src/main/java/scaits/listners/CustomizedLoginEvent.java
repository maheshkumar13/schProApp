package scaits.listners;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import scaits.bo.employee.EmployeeBO;
import scaits.repository.EmployeeRepository;

@Configuration("loginEvent")
public class CustomizedLoginEvent implements AuthenticationEventPublisher {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private EmployeeRepository employeeRepository;
	

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		
		String payroll= authentication.getName();
		
		
		if(!payroll.chars().allMatch( Character::isDigit )){
		String randomUUIDString = null;
		String cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("x-token")).findFirst()
				.map(Cookie::getValue).orElse(null);

		String jsession = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("JSESSIONID")).findFirst()
				.map(Cookie::getValue).orElse(null);
		EmployeeBO employee = employeeRepository.findByPayRollId(payroll);
		
		if (cookie == null) {
			UUID uuid = UUID.randomUUID();
			randomUUIDString = uuid.toString();

			
			employee.setUuid(randomUUIDString);
			employee.setLastLogin(new Date());
			employeeRepository.save(employee);

		} else {
			randomUUIDString = cookie;
			employee.setUuid(randomUUIDString);
			employee.setLastLogin(new Date());
			employeeRepository.save(employee);
		}

		if (request.getRequestURL().toString().contains("/login")) {
			String ipAddress = null;
			String browserName = null;

			ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null || "".equals(ipAddress)) {
				ipAddress = request.getRemoteAddr();
			}
			browserName = request.getHeader("User-Agent");
			browserName = browserName.toLowerCase();


			if (cookie == null) {

				Cookie addingCookie = new Cookie("x-token", randomUUIDString); 
				addingCookie.setMaxAge(365 * 24 * 60 * 60); 

				response.addCookie(addingCookie);
			}

		} else {

		}
		
       
		
		}

	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

	}

}
