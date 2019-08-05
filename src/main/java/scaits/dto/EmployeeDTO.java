package scaits.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import scaits.bo.employee.EmployeeBO;
import scaits.util.Constants.FormatDates;
import scaits.util.DU;

public class EmployeeDTO {
	
	//information feilds
	private String surName;
	private String userName;
	
	private Date dob;
	private String dobStr;

	private Date doj;
	private String dojStr;
	
	private String mobileNo;
	
	private String gender;
	
	private MultipartFile empImgFile;
	
	private String aadharNo;
	
	private String subject;
	
    private String schoolName;
	
	private String preSchoolName;
	
	private String designation;
	
	private EmployeeBO empManager;
	
	private String passWord;
	
	private String experience;
	
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDobStr() {
		return dobStr;
	}

	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
		if(dobStr==null || dobStr==""  || dobStr.length()==0) {
			this.dob = new Date();
		}else {
			this.dob = DU.parse(dobStr, FormatDates.DATEFORMAT);
		}
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDojStr() {
		return dojStr;
	}

	public void setDojStr(String dojStr) {
		this.dojStr = dojStr;
		if(dojStr==null || dojStr==""  || dojStr.length()==0) {
			this.doj = new Date();
		}else {
			this.doj = DU.parse(dojStr, FormatDates.DATEFORMAT);
		}
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public MultipartFile getEmpImgFile() {
		return empImgFile;
	}

	public void setEmpImgFile(MultipartFile empImgFile) {
		this.empImgFile = empImgFile;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public EmployeeBO getEmpManager() {
		return empManager;
	}

	public void setEmpManager(EmployeeBO empManager) {
		this.empManager = empManager;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	
	
	
}
