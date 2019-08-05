package scaits.security;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import scaits.bo.employee.EmployeeBO;
import scaits.repository.EmployeeRepository;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator  {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
    @Override
    public boolean hasPermission(
      Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }
        EmployeeBO empVal=employeeRepository.findByPayRollId(auth.getName());
        String targetType = targetDomainObject.getClass().getSimpleName();
         
        return hasMyPermission(auth, targetDomainObject.toString(),targetType,empVal );
    }
 
    @Override
    public boolean hasPermission(
      Authentication auth, Serializable targetId, String targetType, Object permission) {
    	
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(), 
          permission.toString().toUpperCase());
    }
    
    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            if (grantedAuth.getAuthority().startsWith(targetType)) {
                if (grantedAuth.getAuthority().contains(permission)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean hasMyPermission(Authentication auth,String targetStr,String targetType, EmployeeBO empVal) {
   	 
			return true;
    }
    

}