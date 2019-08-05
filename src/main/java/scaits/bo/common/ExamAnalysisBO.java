package scaits.bo.common;

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

import org.springframework.format.annotation.DateTimeFormat;

import scaits.bo.student.StudentBO;
import scaits.bo.student.StudyClassBO;

@Entity(name = "t_exam_analysis")
public class ExamAnalysisBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROW_ID")
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEST_ID")
	private TestBO test;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_ID")
	private StudentBO student;
	
	@Column(name = "MATHS")
	private Long maths;
	
	@Column(name = "HINDI")
	private Long hindi;
	
	@Column(name = "EVC")
	private Long evc;
	
	@Column(name = "COMPUTERS")
	private Long computers;
	
	@Column(name = "ENGLISH")
	private Long english;
	
	@Column(name = "PHYSICS")
	private Long physics;
	
	@Column(name = "CHEMISTRY")
	private Long chemistry;
	
	@Column(name = "MATHS_RANK")
	private Long mathsRank;
	
	@Column(name = "HINDI_RANK")
	private Long hindiRank;
	
	@Column(name = "EVC_RANK")
	private Long evcRank;
	
	@Column(name = "COMPUTERS_RANK")
	private Long computersRank;
	
	@Column(name = "ENGLISH_RANK")
	private Long englishRank;
	
	@Column(name = "PHYSICS_RANK")
	private Long physicsRank;
	
	@Column(name = "CHEMISTRY_RANK")
	private Long chemistryRank;
	
	@Column(name = "MATHS_PER")
	private Double mathsPer;
	
	@Column(name = "HINDI_PER")
	private Double hindiPer;
	
	@Column(name = "EVC_PER")
	private Double evcPer;
	
	@Column(name = "COMPUTERS_PER")
	private Double computersPer;
	
	@Column(name = "ENGLISH_PER")
	private Double englishPer;
	
	@Column(name = "PHYSICS_PER")
	private Double physicsPer;
	
	@Column(name = "CHEMISTRY_PER")
	private Double chemistryPer;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASS_ID")
	private StudyClassBO classId;
	
	@Column(name = "TEST_DATE")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date testDate;
	
	@Column(name = "RANK")
	private Long rank;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REMARKS",length=200)
	private String remarks;
	
	@Column(name = "GRADE")
	private String grade;
	
	@Column(name = "TOTAL_SCORED")
	private Long totalScored;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TestBO getTest() {
		return test;
	}

	public void setTest(TestBO test) {
		this.test = test;
	}

	public Long getMaths() {
		return maths;
	}

	public void setMaths(Long maths) {
		this.maths = maths;
	}

	public Long getHindi() {
		return hindi;
	}

	public void setHindi(Long hindi) {
		this.hindi = hindi;
	}

	public Long getEvc() {
		return evc;
	}

	public void setEvc(Long evc) {
		this.evc = evc;
	}

	public Long getComputers() {
		return computers;
	}

	public void setComputers(Long computers) {
		this.computers = computers;
	}

	public Long getEnglish() {
		return english;
	}

	public void setEnglish(Long english) {
		this.english = english;
	}

	public Long getPhysics() {
		return physics;
	}

	public void setPhysics(Long physics) {
		this.physics = physics;
	}

	public Long getChemistry() {
		return chemistry;
	}

	public void setChemistry(Long chemistry) {
		this.chemistry = chemistry;
	}

	public Long getMathsRank() {
		return mathsRank;
	}

	public void setMathsRank(Long mathsRank) {
		this.mathsRank = mathsRank;
	}

	public Long getHindiRank() {
		return hindiRank;
	}

	public void setHindiRank(Long hindiRank) {
		this.hindiRank = hindiRank;
	}

	public Long getEvcRank() {
		return evcRank;
	}

	public void setEvcRank(Long evcRank) {
		this.evcRank = evcRank;
	}

	public Long getComputersRank() {
		return computersRank;
	}

	public void setComputersRank(Long computersRank) {
		this.computersRank = computersRank;
	}

	public Long getEnglishRank() {
		return englishRank;
	}

	public void setEnglishRank(Long englishRank) {
		this.englishRank = englishRank;
	}

	public Long getPhysicsRank() {
		return physicsRank;
	}

	public void setPhysicsRank(Long physicsRank) {
		this.physicsRank = physicsRank;
	}

	public Long getChemistryRank() {
		return chemistryRank;
	}

	public void setChemistryRank(Long chemistryRank) {
		this.chemistryRank = chemistryRank;
	}

	public Double getMathsPer() {
		return mathsPer;
	}

	public void setMathsPer(Double mathsPer) {
		this.mathsPer = mathsPer;
	}

	public Double getHindiPer() {
		return hindiPer;
	}

	public void setHindiPer(Double hindiPer) {
		this.hindiPer = hindiPer;
	}

	public Double getEvcPer() {
		return evcPer;
	}

	public void setEvcPer(Double evcPer) {
		this.evcPer = evcPer;
	}

	public Double getComputersPer() {
		return computersPer;
	}

	public void setComputersPer(Double computersPer) {
		this.computersPer = computersPer;
	}

	public Double getEnglishPer() {
		return englishPer;
	}

	public void setEnglishPer(Double englishPer) {
		this.englishPer = englishPer;
	}

	public Double getPhysicsPer() {
		return physicsPer;
	}

	public void setPhysicsPer(Double physicsPer) {
		this.physicsPer = physicsPer;
	}

	public Double getChemistryPer() {
		return chemistryPer;
	}

	public void setChemistryPer(Double chemistryPer) {
		this.chemistryPer = chemistryPer;
	}

	public StudentBO getStudent() {
		return student;
	}

	public void setStudent(StudentBO student) {
		this.student = student;
	}

	public StudyClassBO getClassId() {
		return classId;
	}

	public void setClassId(StudyClassBO classId) {
		this.classId = classId;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getTotalScored() {
		return totalScored;
	}

	public void setTotalScored(Long totalScored) {
		this.totalScored = totalScored;
	}
	
	
	
}