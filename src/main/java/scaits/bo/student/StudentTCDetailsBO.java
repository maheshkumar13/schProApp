package scaits.bo.student;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import scaits.bo.employee.EmployeeBO;


@SuppressWarnings("unused")
@Entity
@Table(name = "t_student_tc_details")
public class StudentTCDetailsBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	
	
	@Column(name = "CATEGORY", length = 150)
private String category;
	
@Column(name = "ADMISSIONNO", length = 150)
	private String admissionNo;
	
	@Column(name = "NAME", length = 150)
	private String name;
	
	@Column(name = "FATHERNAME", length = 150)
	private String fatherName;
	
	@Column(name = "MOTHERNAME", length = 150)
	private String motherName;
	
	@Column(name = "NATIONALITYANDRELIGION", length = 150)
	private String nationalityAndReligion;
	
	@Column(name = "STUDENTCAST", length = 150)
	private String studentCast;
	
	@Column(name = "STUDENTDOB")
	private Date studentDob;
	
	@Column(name = "DATEOFADMISSION")
	private Date dateOfAdmission;
	
	@Column(name = "CLASSOFLEAVING", length = 150)
	private String classOfLeaving;
	
	@Column(name = "ANNUALEXAMSLASTTAKEN", length = 150)
	private String annualExamsLastTaken;
	
	@Column(name = "ONCEORTWICEINSAMECLASS", length = 150)
	private String onceOrtwiceinSameClass;
	
	@Column(name = "SUBJECT1", length = 150)
	private String subject1;
	
	@Column(name = "SUBJECT2", length = 150)
	private String subject2;
	
	@Column(name = "SUBJECT3", length = 150)
	private String subject3;
	
	@Column(name = "SUBJECT4", length = 150)
	private String subject4;
	
	@Column(name = "SUBJECT5", length = 150)
	private String subject5;
	
	@Column(name = "SUBJECT6", length = 150)
	private String subject6;
	
	@Column(name = "QUALIFIERFORPROMOTIONTOHIGHERCLASS", length = 150)
	private String qualifierforPromotiontoHigherClass;
	
	@Column(name = "PAIDSCHOOLDUES", length = 150)
	private String paidSchoolDues;
	
	@Column(name = "CONCESSIONAVAILED", length = 150)
	private String concessionAvailed;
	
	@Column(name = "NOOFWORKINGDAYS", length = 150)
	private String noOfWorkingDays;
	
	@Column(name = "NOOFWORKINGDAYSPRESENT", length = 150)
	private String noOfWorkingDaysPresent;
	
	@Column(name = "COCCURRICULARACTIVITIES", length = 150)
	private String coCcurricularActivities;
	
	@Column(name = "CONDUCT", length = 150)
	private String conduct;
	
	@Column(name = "DATEOFAPPLIEDTRANSFERCERTIFICATE")
	private Date dateOfAppliedTransferCertificate;
	
	@Column(name = "DATEOFISSUTRANSFERCERTIFICATE")
	private Date dateOfIssuTransferCertificate;
	
	@Column(name = "REASONFORLEAVING", length = 150)
	private String reasonForLeaving;
	
	@Column(name = "ANYOTHERREMARKS", length = 150)
	private String anyOtherRemarks;
	
	
	@Column(name = "CREATED_ON")
	private Date createdOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	private EmployeeBO createdBy;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	private EmployeeBO updatedBy;
	
	@Column(name = "TC_NO")
	private Integer tcNo;
	
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	

	public Date getStudentDob() {
		return studentDob;
	}

	public void setStudentDob(Date studentDob) {
		this.studentDob = studentDob;
	}

	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
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

	public Date getDateOfAppliedTransferCertificate() {
		return dateOfAppliedTransferCertificate;
	}

	public void setDateOfAppliedTransferCertificate(Date dateOfAppliedTransferCertificate) {
		this.dateOfAppliedTransferCertificate = dateOfAppliedTransferCertificate;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public EmployeeBO getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(EmployeeBO createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public EmployeeBO getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(EmployeeBO updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getTcNo() {
		return tcNo;
	}

	public void setTcNo(Integer tcNo) {
		this.tcNo = tcNo;
	}
	
	
	
	

	

}
