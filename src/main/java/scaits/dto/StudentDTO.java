package scaits.dto;

import scaits.bo.student.StudyClassBO;
import scaits.util.Constants.FormatDates;
import scaits.util.DU;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import scaits.bo.student.AcademicYearBO;
import scaits.bo.student.CastBO;
import scaits.bo.student.ClassSectionBO;
import scaits.bo.student.HeadNamesBO;

public class StudentDTO {
	
	//information feilds
	private String studentNo;
	private String surName;
	private String stuName;
	private String fatherName;
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	private String motherName;
	private Long mobileNo;
	private Long mobileNo2;
	
	private StudyClassBO studyClass;
	private String gender;
	private String bsf;
	public String getBsf() {
		return bsf;
	}
	public void setBsf(String bsf) {
		this.bsf = bsf;
	}
	private String aadhaar;
	
	private AcademicYearBO academicYear;
	private CastBO castVal;
	private String subCast;
	
	private String schoolName;
	private ClassSectionBO sectionVal;
	
	
	private Date dob;
	private String dobStr;
	
	private String email;
	private String pOccupation;
	private String localGuardian;
	private String perAddress;
	private String pAddress;
	private String preSchoolName;
	private String preClassAttended;
	
	private MultipartFile stuImgFile;
	
	private MultipartFile birthCertFile;
	private String religion;
	private String otherReligion;
	
	
	
	//payment feilds
	
	private Long rFee;
	private Date regFeeDate;
	private String regFeeDateStr;
	private String regPrePrintedRecNo;
	
	
    private Long headWiseFee;
	private Date headFeeDate;
	private String headFeeDateStr;
	private String headWisePrePrintedRecNo;
	
	private HeadNamesBO headName;
	
	private String regFeePayMode;
	private String headNamePayMode;
	
	private String bloodGroup;
	private String nationality;
	
	
	
	
	
	
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public StudyClassBO getStudyClass() {
		return studyClass;
	}
	public void setStudyClass(StudyClassBO studyClass) {
		this.studyClass = studyClass;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public AcademicYearBO getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYearBO academicYear) {
		this.academicYear = academicYear;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public ClassSectionBO getSectionVal() {
		return sectionVal;
	}
	public void setSectionVal(ClassSectionBO sectionVal) {
		this.sectionVal = sectionVal;
	}
	public CastBO getCastVal() {
		return castVal;
	}
	public void setCastVal(CastBO castVal) {
		this.castVal = castVal;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getpOccupation() {
		return pOccupation;
	}
	public void setpOccupation(String pOccupation) {
		this.pOccupation = pOccupation;
	}
	public String getLocalGuardian() {
		return localGuardian;
	}
	public void setLocalGuardian(String localGuardian) {
		this.localGuardian = localGuardian;
	}
	
	public String getPerAddress() {
		return perAddress;
	}
	public void setPerAddress(String perAddress) {
		this.perAddress = perAddress;
	}
	public String getpAddress() {
		return pAddress;
	}
	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}
	public Long getrFee() {
		return rFee;
	}
	public void setrFee(Long rFee) {
		this.rFee = rFee;
	}
	public String getPreSchoolName() {
		return preSchoolName;
	}
	public void setPreSchoolName(String preSchoolName) {
		this.preSchoolName = preSchoolName;
	}
	public Date getRegFeeDate() {
		return regFeeDate;
	}
	public void setRegFeeDate(Date regFeeDate) {
		this.regFeeDate = regFeeDate;
	}
	public String getRegFeeDateStr() {
		return regFeeDateStr;
	}
	public void setRegFeeDateStr(String regFeeDateStr) {
		this.regFeeDateStr = regFeeDateStr;
		if(regFeeDateStr==null || regFeeDateStr=="" || regFeeDateStr.length()==0) {
			this.regFeeDate = new Date();
		}else {
			this.regFeeDate = DU.parse(regFeeDateStr, FormatDates.DATEFORMAT);
		}
	}
	public String getRegPrePrintedRecNo() {
		return regPrePrintedRecNo;
	}
	public void setRegPrePrintedRecNo(String regPrePrintedRecNo) {
		this.regPrePrintedRecNo = regPrePrintedRecNo;
	}
	public Long getHeadWiseFee() {
		return headWiseFee;
	}
	public void setHeadWiseFee(Long headWiseFee) {
		this.headWiseFee = headWiseFee;
	}
	public Date getHeadFeeDate() {
		return headFeeDate;
	}
	public void setHeadFeeDate(Date headFeeDate) {
		this.headFeeDate = headFeeDate;
	}
	public String getHeadFeeDateStr() {
		return headFeeDateStr;
	}
	public void setHeadFeeDateStr(String headFeeDateStr) {
		
		this.headFeeDateStr = headFeeDateStr;
		if(headFeeDateStr==null || headFeeDateStr=="" || headFeeDateStr.length()==0) {
			this.headFeeDate = new Date();
		}else {
			this.headFeeDate = DU.parse(headFeeDateStr, FormatDates.DATEFORMAT);
		}
	}
	public String getHeadWisePrePrintedRecNo() {
		return headWisePrePrintedRecNo;
	}
	public void setHeadWisePrePrintedRecNo(String headWisePrePrintedRecNo) {
		this.headWisePrePrintedRecNo = headWisePrePrintedRecNo;
	}
	public HeadNamesBO getHeadName() {
		return headName;
	}
	public void setHeadName(HeadNamesBO headName) {
		this.headName = headName;
	}
	public String getRegFeePayMode() {
		return regFeePayMode;
	}
	public void setRegFeePayMode(String regFeePayMode) {
		this.regFeePayMode = regFeePayMode;
	}
	public String getHeadNamePayMode() {
		return headNamePayMode;
	}
	public void setHeadNamePayMode(String headNamePayMode) {
		this.headNamePayMode = headNamePayMode;
	}
	public MultipartFile getStuImgFile() {
		return stuImgFile;
	}
	public void setStuImgFile(MultipartFile stuImgFile) {
		this.stuImgFile = stuImgFile;
	}
	public MultipartFile getBirthCertFile() {
		return birthCertFile;
	}
	public void setBirthCertFile(MultipartFile birthCertFile) {
		this.birthCertFile = birthCertFile;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getPreClassAttended() {
		return preClassAttended;
	}
	public void setPreClassAttended(String preClassAttended) {
		this.preClassAttended = preClassAttended;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getOtherReligion() {
		return otherReligion;
	}
	public void setOtherReligion(String otherReligion) {
		this.otherReligion = otherReligion;
	}
	public Long getMobileNo2() {
		return mobileNo2;
	}
	public void setMobileNo2(Long mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}
	public String getSubCast() {
		return subCast;
	}
	public void setSubCast(String subCast) {
		this.subCast = subCast;
	}
	
	
	
	
	
	
	
	
	
	
	
}
