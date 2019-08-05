package scaits.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scaits.bo.employee.EmployeeBO;
import scaits.repository.EmployeeRepository;

@Component
public class ApplicationUtil {
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public String getEmpName(Long emLong) {
		
		EmployeeBO empVal=employeeRepository.findByEmployeeId(emLong);
		
		if(empVal!=null) {
			return empVal.getUserName()+" "+empVal.getPayRollId();
		}

		return "";
	}
	
}
