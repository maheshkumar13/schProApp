package scaits.bo.employee;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_employee")
public class EmployeeBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPLOYEE_ID", length = 10)
	private long employeeId;

	@Column(name = "EMP_ID", length = 30)
	private String empId;

	@Column(name = "MAC_ADDRESS", length = 50)
	private String macaddress;

	@Column(name = "IP_ADDRESS", length = 50)
	private String ipaddress;

	@Column(name = "SURNAME", length = 100)
	private String surName;

	@Column(name = "NAME", length = 200)
	private String name;

	@Column(name = "MOBILENO", length = 50)
	private String mobileNo;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "DOJ")
	private Date doj;

	@Column(name = "DOL")
	private Date dol;

	@Column(name = "STATUS", length = 50)
	private String status;

	@Column(name = "USER_NAME", length = 200)
	private String userName;

	@Column(name = "LAST_LOGIN_TIME")
	private Date lastLogin;

	@Column(name = "GENDER", length = 20)
	private String gender;

	@Column(name = "PAN", length = 20)
	private String panNo;

	@Column(name = "SUBJECT", length = 50)
	private String subject;

	@Column(name = "EMP_TYPE", length = 50)
	private String empType;

	@Column(name = "CREATED_ON")
	private Date createdOn;
	
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@Column(name = "CAMPUS_ID")
	private Long campus;

	@Column(name = "UUID", length = 200)
	private String uuid;

	@Column(name = "PF_NUMBER", length = 50)
	private String pfNumber;

	@Column(name = "PF_ELIGIBLE")
	private boolean pfEligible;

	@Column(name = "ESI_ELIGIBLE")
	private boolean esiEligible;

	@Column(name = "ALLOWED_CONCESSION")
	private boolean allowedConcession;

	@Column(name = "IS_DGM")
	private boolean isDgm;

	@Column(name = "IS_COLLEGE")
	private String isCollege;
	
	@Column(name = "ORG_ID")
	private Long organization;
	
	@Column(name = "FMS_LOGIN", length = 50)
	private String fmsLogin;

	@Column(name = "PAYROLL_ID", length = 50)
	private String payRollId;

	@Column(name = "DESIGNATION", length = 50)
	private String designation;

	@Column(name = "MOBILE", length = 50)
	private String mobile;

	@Column(name = "CHEQUE_AMOUNT", length = 20)
	private Long chequeAmount;

	@Column(name = "CASH_AMOUNT", length = 20)
	private Long cashAmount;

	@Column(name = "DD_AMOUNT", length = 20)
	private Long ddAmount;

	@Column(name = "IMAGE_PATH", length = 100)
	private String imagePath;

	@Column(name = "PRINTER_TYPE", length = 20)
	private String printerType;

	@Column(name = "EMP_TRANSFER_STATUS", length = 20)
	private String empTransferStatus;

	@Column(name = "IS_VALID")
	private Integer isValid;
	
	@Column(name = "EMP_TRANSFER_CAMPUS")
	private Long empTransferCampus;
	
	@Column(name = "ROLE_ID")
	private Long userRole;

	@Column(name = "ACCESS_STATUS")
	private boolean accessStatus;

	@Column(name = "PWD_STATUS")
	private boolean pwdStatus;

	@Column(name = "INCENTIVE")
	private String incentive;
	
	@Column(name = "MANAGER_ID")
	private Long managerId;
	
	@Column(name = "EMP_HISTORY_ID")
	private Long employeeHistory;
	
	@Column(name = "PAYROLL_CAMPUS_ID")
	private Long payrollCampus;

	@Column(name = "BUILDING_ID")
	private Long building;

	@Column(name = "REGISTRATION_TYPE", length = 20)
	private String registrationType;
	
	@Column(name = "SKIP_ATTENDANCE")
	private boolean skipAttendance;
	
	@Column(name = "AADHAR_NO", length = 50)
	private String aadharNo;
	
	@Column(name = "SCHOOL_NAME", length = 100)
	private String schoolName;
	
	@Column(name = "PRE_SCHOOL_NAME", length = 100)
	private String preSchoolName;
	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;
	
	@Column(name = "UPDATED_BY")
	private Long updatedBy;
	
	@Column(name = "PASS_WORD", length = 50)
	private String passWord;
	
	@Column(name = "DRY_PASSWORD", length = 100)
	private String dryPassWord;
	
	@Column(name = "EXPERIENCE", length = 100)
	private String experience;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Date getDol() {
		return dol;
	}

	public void setDol(Date dol) {
		this.dol = dol;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCampus() {
		return campus;
	}

	public void setCampus(Long campus) {
		this.campus = campus;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPfNumber() {
		return pfNumber;
	}

	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}

	public boolean isPfEligible() {
		return pfEligible;
	}

	public void setPfEligible(boolean pfEligible) {
		this.pfEligible = pfEligible;
	}

	public boolean isEsiEligible() {
		return esiEligible;
	}

	public void setEsiEligible(boolean esiEligible) {
		this.esiEligible = esiEligible;
	}

	public boolean isAllowedConcession() {
		return allowedConcession;
	}

	public void setAllowedConcession(boolean allowedConcession) {
		this.allowedConcession = allowedConcession;
	}

	public boolean isDgm() {
		return isDgm;
	}

	public void setDgm(boolean isDgm) {
		this.isDgm = isDgm;
	}

	public String getIsCollege() {
		return isCollege;
	}

	public void setIsCollege(String isCollege) {
		this.isCollege = isCollege;
	}

	public Long getOrganization() {
		return organization;
	}

	public void setOrganization(Long organization) {
		this.organization = organization;
	}

	public String getFmsLogin() {
		return fmsLogin;
	}

	public void setFmsLogin(String fmsLogin) {
		this.fmsLogin = fmsLogin;
	}

	public String getPayRollId() {
		return payRollId;
	}

	public void setPayRollId(String payRollId) {
		this.payRollId = payRollId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getChequeAmount() {
		return chequeAmount;
	}

	public void setChequeAmount(Long chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public Long getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(Long cashAmount) {
		this.cashAmount = cashAmount;
	}

	public Long getDdAmount() {
		return ddAmount;
	}

	public void setDdAmount(Long ddAmount) {
		this.ddAmount = ddAmount;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getPrinterType() {
		return printerType;
	}

	public void setPrinterType(String printerType) {
		this.printerType = printerType;
	}

	public String getEmpTransferStatus() {
		return empTransferStatus;
	}

	public void setEmpTransferStatus(String empTransferStatus) {
		this.empTransferStatus = empTransferStatus;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Long getEmpTransferCampus() {
		return empTransferCampus;
	}

	public void setEmpTransferCampus(Long empTransferCampus) {
		this.empTransferCampus = empTransferCampus;
	}

	public Long getUserRole() {
		return userRole;
	}

	public void setUserRole(Long userRole) {
		this.userRole = userRole;
	}

	public boolean isAccessStatus() {
		return accessStatus;
	}

	public void setAccessStatus(boolean accessStatus) {
		this.accessStatus = accessStatus;
	}

	public boolean isPwdStatus() {
		return pwdStatus;
	}

	public void setPwdStatus(boolean pwdStatus) {
		this.pwdStatus = pwdStatus;
	}

	public String getIncentive() {
		return incentive;
	}

	public void setIncentive(String incentive) {
		this.incentive = incentive;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Long getEmployeeHistory() {
		return employeeHistory;
	}

	public void setEmployeeHistory(Long employeeHistory) {
		this.employeeHistory = employeeHistory;
	}

	public Long getPayrollCampus() {
		return payrollCampus;
	}

	public void setPayrollCampus(Long payrollCampus) {
		this.payrollCampus = payrollCampus;
	}

	public Long getBuilding() {
		return building;
	}

	public void setBuilding(Long building) {
		this.building = building;
	}

	public String getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}

	public boolean isSkipAttendance() {
		return skipAttendance;
	}

	public void setSkipAttendance(boolean skipAttendance) {
		this.skipAttendance = skipAttendance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getPreSchoolName() {
		return preSchoolName;
	}

	public void setPreSchoolName(String preSchoolName) {
		this.preSchoolName = preSchoolName;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDryPassWord() {
		return dryPassWord;
	}

	public void setDryPassWord(String dryPassWord) {
		this.dryPassWord = dryPassWord;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	
	
}
