/**
 * 
 */
package scaits.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import scaits.bo.employee.EmployeeBO;
import scaits.repository.EmployeeRepository;
import scaits.util.EmployeeAction;

/**
 * @author mahesh.g 07-Apr-2018 4:00:29 PM
 */
@Configuration
public class GlobalConfig {

	@Bean(name = "currentUser")
	public GlobalInterface getCurrentUser() {
		return new GlobalInterface() {
			@Autowired
			EmployeeAction employeeAction;


			@Override
			public EmployeeBO getCurrentUser() {
				return employeeAction.getCurrentUser();
			}

			@Autowired
			EmployeeRepository employeeRepository;
			
			@Override
			public String getEmpName(long emLong) {
				
				EmployeeBO empVal=employeeRepository.findByEmployeeId(emLong);
				
				if(empVal!=null) {
					return empVal.getUserName()+" "+empVal.getPayRollId();
				}

				return "";
			}

		};
	}
	
	

}
