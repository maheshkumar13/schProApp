package scaits.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;


public class SessionRegistryImpl {
	@Autowired
	private SessionRegistry sessionRegistry;
	
	
	 
	public List<String> getUsersFromSessionRegistry() {
		
		
		/*List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
	    for (Object principal : loggedUsers) {
	        if (principal instanceof User) {
	            final User loggedUser = (User) principal;
	            if (studentAction.getCurrentStudent().getAdmNo().equals(loggedUser.getUsername())) {
	                List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(principal, false);
	                if (null != sessionsInfo && sessionsInfo.size() > 0) {
	                    for (SessionInformation sessionInformation : sessionsInfo) {
	                        sessionInformation.expireNow();
	                            sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
	                    }
	                }
	            }
	        }
	    }*/
		
		System.out.println(sessionRegistry.getAllPrincipals().size());
		System.out.println(sessionRegistry.getAllPrincipals().get(0));
		
		List<Object> loggedUsers = sessionRegistry.getAllPrincipals();
		for (Object principal : loggedUsers) {
	        if (principal instanceof User) {
	            final User loggedUser = (User) principal;
	            
	            if(loggedUser.getUsername().chars().allMatch( Character::isDigit )){
	            	 return sessionRegistry.getAllPrincipals().stream()
	   	          	      .filter(u -> ((User)u).getUsername().chars().allMatch( Character::isDigit ) && !sessionRegistry.getAllSessions(u, false).isEmpty())
	   	          	      .map(Object::toString)
	   	          	      .collect(Collectors.toList());
	            }else{
	            	return sessionRegistry.getAllPrincipals().stream().map(Object::toString)
		   	          	      .collect(Collectors.toList());
	            }
	           
	            
	        }
		}
		return null;
		
		
		
	    
	}
}

