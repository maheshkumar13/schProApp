package scaits.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class OnlineExamFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// String user = httpRequest.getUserPrincipal();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String clientId = auth.getName();

		String JssessionId = httpRequest.getSession().getId();
		//System.out.println(JssessionId);

		String hdrs = httpRequest.getHeader("referer");
		String requestURI = httpRequest.getRequestURI();

		if(clientId!=null && !clientId.equalsIgnoreCase("anonymousUser")){
			chain.doFilter(request, response);
		}else if(clientId==null || clientId.equalsIgnoreCase("anonymousUser") || (hdrs==null && !clientId.equalsIgnoreCase("anonymousUser"))){
			httpResponse.sendRedirect("/login");
        }else  if((hdrs != null && hdrs.contains("login")) || clientId!=null || (hdrs!=null && !hdrs.contains(requestURI))){
        	chain.doFilter(request, response);
        }

	}

}
