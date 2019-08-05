package scaits.dto;

import java.util.Date;

import scaits.bo.student.StudentBO;
import scaits.util.Constants.FormatDates;
import scaits.util.DU;

public class StudentTCDTO {
	
	private String category;
	
	private String admissionNo;
	
	private String name;
	
	private String fatherName;
	
	private String motherName;
	
	private String nationalityAndReligion;
	
	private String studentCast;
	
	private String dateOfAdmissionStr;
	
	private Date dateOfAdmission;
	
	private String studentDobStr;
	
	private Date studentDob;
	
	private String classOfLeaving;
	
	private String annualExamsLastTaken;
	
	private String onceOrtwiceinSameClass;
	
	private String subject1;
	private String subject2;
	private String subject3;
	private String subject4;
	private String subject5;
	private String subject6;
	
	private String qualifierforPromotiontoHigherClass;
	
	private String paidSchoolDues;
	
	private String concessionAvailed;
	
	private String noOfWorkingDays;
	
	private String noOfWorkingDaysPresent;
	
	private String coCcurricularActivities;
	
	private String conduct;
	
	private String dateOfAppliedTransferCertificateStr;
	
	private Date dateOfAppliedTransferCertificate;
	
	private String dateOfIssuTransferCertificateStr;
	
	private Date dateOfIssuTransferCertificate;
	
	private String reasonForLeaving;
	
	private String anyOtherRemarks;
	
	private StudentBO student;
	
	
	
	
	
	
	
	

	public String getName() {
		return name;
	}









	public void setName(String name) {
		this.name = name;
	}









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









	public String getNationalityAndReligion() {
		return nationalityAndReligion;
	}









	public void setNationalityAndReligion(String nationalityAndReligion) {
		this.nationalityAndReligion = nationalityAndReligion;
	}









	public String getStudentCast() {
		return studentCast;
	}









	public void setStudentCast(String studentCast) {
		this.studentCast = studentCast;
	}









	public String getDateOfAdmissionStr() {
		return dateOfAdmissionStr;
	}









	public void setDateOfAdmissionStr(String dateOfAdmissionStr) {
		this.dateOfAdmissionStr = dateOfAdmissionStr;
		if(dateOfAdmissionStr==null || dateOfAdmissionStr=="" || dateOfAdmissionStr.length()==0) {
			this.dateOfAdmission= new Date();
		}else {
			this.dateOfAdmission = DU.parse(dateOfAdmissionStr, FormatDates.DATEFORMAT);
		}
	}
















	









	public String getClassOfLeaving() {
		return classOfLeaving;
	}









	public void setClassOfLeaving(String classOfLeaving) {
		this.classOfLeaving = classOfLeaving;
	}









	public String getAnnualExamsLastTaken() {
		return annualExamsLastTaken;
	}









	public void setAnnualExamsLastTaken(String annualExamsLastTaken) {
		this.annualExamsLastTaken = annualExamsLastTaken;
	}









	public String getOnceOrtwiceinSameClass() {
		return onceOrtwiceinSameClass;
	}









	public void setOnceOrtwiceinSameClass(String onceOrtwiceinSameClass) {
		this.onceOrtwiceinSameClass = onceOrtwiceinSameClass;
	}









	public String getSubject1() {
		return subject1;
	}









	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}









	public Date getStudentDob() {
		return studentDob;
	}









	public void setStudentDob(Date studentDob) {
		this.studentDob = studentDob;
	}









	public String getSubject2() {
		return subject2;
	}









	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}









	public String getSubject3() {
		return subject3;
	}









	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}









	public String getSubject4() {
		return subject4;
	}









	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}









	public String getSubject5() {
		return subject5;
	}









	public void setSubject5(String subject5) {
		this.subject5 = subject5;
	}









	public String getSubject6() {
		return subject6;
	}









	public void setSubject6(String subject6) {
		this.subject6 = subject6;
	}









	public String getQualifierforPromotiontoHigherClass() {
		return qualifierforPromotiontoHigherClass;
	}









	public void setQualifierforPromotiontoHigherClass(String qualifierforPromotiontoHigherClass) {
		this.qualifierforPromotiontoHigherClass = qualifierforPromotiontoHigherClass;
	}









	public String getPaidSchoolDues() {
		return paidSchoolDues;
	}









	public void setPaidSchoolDues(String paidSchoolDues) {
		this.paidSchoolDues = paidSchoolDues;
	}









	public String getConcessionAvailed() {
		return concessionAvailed;
	}









	public void setConcessionAvailed(String concessionAvailed) {
		this.concessionAvailed = concessionAvailed;
	}









	public String getNoOfWorkingDays() {
		return noOfWorkingDays;
	}









	public void setNoOfWorkingDays(String noOfWorkingDays) {
		this.noOfWorkingDays = noOfWorkingDays;
	}









	public String getNoOfWorkingDaysPresent() {
		return noOfWorkingDaysPresent;
	}









	public void setNoOfWorkingDaysPresent(String noOfWorkingDaysPresent) {
		this.noOfWorkingDaysPresent = noOfWorkingDaysPresent;
	}









	public String getCoCcurricularActivities() {
		return coCcurricularActivities;
	}









	public void setCoCcurricularActivities(String coCcurricularActivities) {
		this.coCcurricularActivities = coCcurricularActivities;
	}









	public String getConduct() {
		return conduct;
	}









	public void setConduct(String conduct) {
		this.conduct = conduct;
	}









	public String getDateOfAppliedTransferCertificateStr() {
		return dateOfAppliedTransferCertificateStr;
	}









	public void setDateOfAppliedTransferCertificateStr(String dateOfAppliedTransferCertificateStr) {
		
		this.dateOfAppliedTransferCertificateStr = dateOfAppliedTransferCertificateStr;
		if(dateOfAppliedTransferCertificateStr==null || dateOfAppliedTransferCertificateStr=="" || dateOfAppliedTransferCertificateStr.length()==0) {
			this.dateOfAppliedTransferCertificate = new Date();
		}else {
			this.dateOfAppliedTransferCertificate = DU.parse(dateOfAppliedTransferCertificateStr, FormatDates.DATEFORMAT);
		}
	}









	public Date getDateOfAppliedTransferCertificate() {
		return dateOfAppliedTransferCertificate;
	}









	public void setDateOfAppliedTransferCertificate(Date dateOfAppliedTransferCertificate) {
		this.dateOfAppliedTransferCertificate = dateOfAppliedTransferCertificate;
	}









	public String getDateOfIssuTransferCertificateStr() {
		return dateOfIssuTransferCertificateStr;
	}









	public void setDateOfIssuTransferCertificateStr(String dateOfIssuTransferCertificateStr) {
		
		this.dateOfIssuTransferCertificateStr = dateOfIssuTransferCertificateStr;
		if(dateOfIssuTransferCertificateStr==null || dateOfIssuTransferCertificateStr=="" || dateOfIssuTransferCertificateStr.length()==0) {
			this.dateOfIssuTransferCertificate = new Date();
		}else {
			this.dateOfIssuTransferCertificate = DU.parse(dateOfIssuTransferCertificateStr, FormatDates.DATEFORMAT);
		}
	}









	public Date getDateOfIssuTransferCertificate() {
		return dateOfIssuTransferCertificate;
	}









	public void setDateOfIssuTransferCertificate(Date dateOfIssuTransferCertificate) {
		this.dateOfIssuTransferCertificate = dateOfIssuTransferCertificate;
	}









	public String getReasonForLeaving() {
		return reasonForLeaving;
	}









	public void setReasonForLeaving(String reasonForLeaving) {
		this.reasonForLeaving = reasonForLeaving;
	}









	public String getAnyOtherRemarks() {
		return anyOtherRemarks;
	}









	public void setAnyOtherRemarks(String anyOtherRemarks) {
		this.anyOtherRemarks = anyOtherRemarks;
	}









	public String getStudentDobStr() {
		return studentDobStr;
	}









	public void setStudentDobStr(String studentDobStr) {
		this.studentDobStr = studentDobStr;
		if(studentDobStr==null || studentDobStr=="" || studentDobStr.length()==0) {
			this.studentDob = new Date();
		}else {
			this.studentDob = DU.parse(studentDobStr, FormatDates.DATEFORMAT);
		}
	}









	public String getCategory() {
		return category;
	}









	public void setCategory(String category) {
		this.category = category;
	}









	public String getAdmissionNo() {
		return admissionNo;
	}









	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}









	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}









	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}









	public StudentBO getStudent() {
		return student;
	}









	public void setStudent(StudentBO student) {
		this.student = student;
	}
	
	

	

    

	
	
	
	
	
	
}
