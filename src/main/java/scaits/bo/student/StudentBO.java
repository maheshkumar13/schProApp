package scaits.bo.student;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import scaits.bo.common.FinalYearlyBO;
import scaits.bo.common.HalfYearlyBO;
import scaits.bo.employee.EmployeeBO;

@Entity(name = "t_student")
public class StudentBO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STUDENT_NO", length = 25)
	private String studentNo;

	@Column(name = "REGISTRATION_FEE")
	private Long rFee;

	@Column(name = "PASS_WORD", length = 50)
	private String passwd;

	@Column(name = "SUR_NAME", length = 500)
	private String surName;

	@Column(name = "NAME", length = 500)
	private String name;

	@Column(name = "FATHER_NAME", length = 500)
	private String fatherName;

	@Column(name = "MOTHER_NAME", length = 500)
	private String motherName;

	@Column(name = "PARENT_OCCUPATION", length = 500)
	private String pOccupation;

	@Column(name = "LOCAL_GUARDIAN", length = 500)
	private String localGuardian;

	@Column(name = "COACHING", length = 50)
	private String coaching;

	@Column(name = "ACADEMIC_YEAR", length = 20)
	private String academicYear;

	public enum Student {
		DELETE, LEFT, CURRENT
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Student student;

	@Column(name = "RELIGION", length = 20)
	private String religion;

	@Column(name = "SUB_CAST", length = 20)
	private String subCast;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAST_ID")
	private CastBO cast;

	@Column(name = "PRESENT_ADDRESS", length = 350)
	private String pAddress;

	@Column(name = "PERMANANT_ADDRESS", length = 350)
	private String perAddress;

	@Column(name = "REMARKS", length = 50)
	private String remarks;

	@Column(name = "SCHOOL_NAME", length = 200)
	private String schoolName;

	@Column(name = "PRE_SCHOOL_NAME", length = 200)
	private String preSchoolName;

	@Column(name = "PRE_CLASS_ATTENDED", length = 200)
	private String preClassAttended;

	@Column(name = "UUID", length = 200)
	private String uuid;

	@Column(name = "MOBILE_NO")
	private Long mobileNo;

	@Column(name = "MOBILE_NO2")
	private Long mobileNo2;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACADEMIC_ID")
	private AcademicYearBO academicId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASS_ID")
	private StudyClassBO classId;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DOB")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dob;

	@Column(name = "DOJ")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doj;

	@Column(name = "GENDER", length = 6)
	private String gender;

	@Column(name = "BSF", length = 6)
	private String bsf;

	@Column(name = "AADHAAR")
	private String aadhaar;

	@Column(name = "BLOOD_GROUP")
	private String bloodGroup;

	@Column(name = "NATIONALITY")
	private String nationality;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	private EmployeeBO createdBy;

	@Column(name = "CREATED_BY_NAME", length = 100)
	private String createdByName;

	@Column(name = "UPDATED_ON")
	private Date modifiedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	private EmployeeBO modifiedBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SECTION_ID")
	private ClassSectionBO sectionId;

	@OneToMany(mappedBy = "student")
	private List<TransactionBO> transList;

	@OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
	private HalfYearlyBO halfYearly;
	
	@OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
	private FinalYearlyBO finalYearly;

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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

	public String getCoaching() {
		return coaching;
	}

	public void setCoaching(String coaching) {
		this.coaching = coaching;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getSubCast() {
		return subCast;
	}

	public void setSubCast(String subCast) {
		this.subCast = subCast;
	}

	public CastBO getCast() {
		return cast;
	}

	public void setCast(CastBO cast) {
		this.cast = cast;
	}

	public String getpAddress() {
		return pAddress;
	}

	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}

	public String getPerAddress() {
		return perAddress;
	}

	public void setPerAddress(String perAddress) {
		this.perAddress = perAddress;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public AcademicYearBO getAcademicId() {
		return academicId;
	}

	public void setAcademicId(AcademicYearBO academicId) {
		this.academicId = academicId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public EmployeeBO getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(EmployeeBO modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ClassSectionBO getSectionId() {
		return sectionId;
	}

	public void setSectionId(ClassSectionBO sectionId) {
		this.sectionId = sectionId;
	}

	public Long getrFee() {
		return rFee;
	}

	public void setrFee(Long rFee) {
		this.rFee = rFee;
	}

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

	public StudyClassBO getClassId() {
		return classId;
	}

	public void setClassId(StudyClassBO classId) {
		this.classId = classId;
	}

	public String getPreSchoolName() {
		return preSchoolName;
	}

	public void setPreSchoolName(String preSchoolName) {
		this.preSchoolName = preSchoolName;
	}

	public List<TransactionBO> getTransList() {
		return transList;
	}

	public void setTransList(List<TransactionBO> transList) {
		this.transList = transList;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getBsf() {
		return bsf;
	}

	public void setBsf(String bsf) {
		this.bsf = bsf;
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

	public Long getMobileNo2() {
		return mobileNo2;
	}

	public void setMobileNo2(Long mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}

	public HalfYearlyBO getHalfYearly() {
		return halfYearly;
	}

	public void setHalfYearly(HalfYearlyBO halfYearly) {
		this.halfYearly = halfYearly;
	}

	public FinalYearlyBO getFinalYearly() {
		return finalYearly;
	}

	public void setFinalYearly(FinalYearlyBO finalYearly) {
		this.finalYearly = finalYearly;
	}

}
