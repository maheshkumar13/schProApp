/**
 * 
 */
package scaits.config;

import scaits.bo.employee.EmployeeBO;

/**
 * @author mahesh.g 07-Apr-2018 4:08:52 PM
 */
public interface GlobalInterface {
	EmployeeBO getCurrentUser();

	String getEmpName(long emLong);

}
