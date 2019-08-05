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

@Entity(name = "t_half_yearly")
public class HalfYearlyBO implements Serializable {

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
	private Double maths;

	@Column(name = "MATHS_NB")
	private Double mathsNb;

	@Column(name = "MATHS_PT")
	private Double mathsPt;

	@Column(name = "MATHS_PRO")
	private Double mathsPro;

	@Column(name = "MATHS_TOTAL")
	private Double mathsTotal;

	@Column(name = "HINDI")
	private Double hindi;
	
	@Column(name = "HINDI_NB")
	private Double hindiNb;
	
	@Column(name = "HINDI_PT")
	private Double hindiPt;
	
	@Column(name = "HINDI_PRO")
	private Double hindiPro;
	
	@Column(name = "HINDI_TOTAL")
	private Double hindiTotal;
	
	@Column(name = "PHYSICS_TOTAL")
	private Double physicsTotal;
	

	@Column(name = "EVC")
	private Double evc;
	
	@Column(name = "EVC_NB")
	private Double evcNb;
	
	@Column(name = "EVC_PT")
	private Double evcPt;
	
	@Column(name = "EVC_PRO")
	private Double evcPro;
	
	@Column(name = "EVC_TOTAL")
	private Double evcTotal;
	
	@Column(name = "COMPUTERS")
	private Double computers;
	
	@Column(name = "COMPUTERS_NB")
	private Double computersNb;
	
	@Column(name = "COMPUTERS_PT")
	private Double computersPt;
	
	@Column(name = "COMPUTERS_PRO")
	private Double computersPro;
	
	@Column(name = "COMPUTERS_TOTAL")
	private Double computersTotal;
	

	@Column(name = "ENGLISH")
	private Double english;
	
	@Column(name = "ENGLISH_NB")
	private Double englishNb;
	
	@Column(name = "ENGLISH_PT")
	private Double englishPt;
	
	@Column(name = "ENGLISH_PRO")
	private Double englishPro;
	
	@Column(name = "ENGLISH_TOTAL")
	private Double englishTotal;

	@Column(name = "PHYSICS")
	private Double physics;
	
	@Column(name = "CHEMISTRY")
	private Double chemistry;
	
	@Column(name = "CHEMISTRY_PT")
	private Double chemistryPt;

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
	
	@Column(name = "CHEMISTRY_TOTAL")
	private Double chemistryTotal;

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
	
	@Column(name = "PHYSICS_PT")
	private Double physicsPt;

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

	@Column(name = "STATUS", length = 50)
	private String status;

	@Column(name = "REMARKS", length = 50)
	private String remarks;

	@Column(name = "GRADE", length = 50)
	private String grade;
	
	@Column(name = "WORK", length = 50)
	private String work;
	
	@Column(name = "ART", length = 50)
	private String art;
	
	@Column(name = "SPORTS", length = 50)
	private String sports;
	
	@Column(name = "CCA", length = 50)
	private String cca;
	
	@Column(name = "MUSIC", length = 50)
	private String music;
	
	@Column(name = "DANCE", length = 50)
	private String dance;
	
	@Column(name = "ATTENDANCE")
	private Long attendance;

	@Column(name = "TOTAL_SCORED")
	private Double totalScored;
	
	@Column(name = "TOTAL_PER")
	private Double totalPer;

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

	public StudentBO getStudent() {
		return student;
	}

	public void setStudent(StudentBO student) {
		this.student = student;
	}

	public Double getMaths() {
		return maths;
	}

	public void setMaths(Double maths) {
		this.maths = maths;
	}

	public Double getMathsNb() {
		return mathsNb;
	}

	public void setMathsNb(Double mathsNb) {
		this.mathsNb = mathsNb;
	}

	public Double getMathsPt() {
		return mathsPt;
	}

	public void setMathsPt(Double mathsPt) {
		this.mathsPt = mathsPt;
	}

	public Double getMathsPro() {
		return mathsPro;
	}

	public void setMathsPro(Double mathsPro) {
		this.mathsPro = mathsPro;
	}

	public Double getMathsTotal() {
		return mathsTotal;
	}

	public void setMathsTotal(Double mathsTotal) {
		this.mathsTotal = mathsTotal;
	}

	public Double getHindi() {
		return hindi;
	}

	public void setHindi(Double hindi) {
		this.hindi = hindi;
	}

	public Double getHindiNb() {
		return hindiNb;
	}

	public void setHindiNb(Double hindiNb) {
		this.hindiNb = hindiNb;
	}

	public Double getHindiPt() {
		return hindiPt;
	}

	public void setHindiPt(Double hindiPt) {
		this.hindiPt = hindiPt;
	}

	public Double getHindiPro() {
		return hindiPro;
	}

	public void setHindiPro(Double hindiPro) {
		this.hindiPro = hindiPro;
	}

	public Double getHindiTotal() {
		return hindiTotal;
	}

	public void setHindiTotal(Double hindiTotal) {
		this.hindiTotal = hindiTotal;
	}

	public Double getPhysicsTotal() {
		return physicsTotal;
	}

	public void setPhysicsTotal(Double physicsTotal) {
		this.physicsTotal = physicsTotal;
	}

	public Double getEvc() {
		return evc;
	}

	public void setEvc(Double evc) {
		this.evc = evc;
	}

	public Double getEvcNb() {
		return evcNb;
	}

	public void setEvcNb(Double evcNb) {
		this.evcNb = evcNb;
	}

	public Double getEvcPt() {
		return evcPt;
	}

	public void setEvcPt(Double evcPt) {
		this.evcPt = evcPt;
	}

	public Double getEvcPro() {
		return evcPro;
	}

	public void setEvcPro(Double evcPro) {
		this.evcPro = evcPro;
	}

	public Double getEvcTotal() {
		return evcTotal;
	}

	public void setEvcTotal(Double evcTotal) {
		this.evcTotal = evcTotal;
	}

	public Double getComputers() {
		return computers;
	}

	public void setComputers(Double computers) {
		this.computers = computers;
	}

	public Double getComputersNb() {
		return computersNb;
	}

	public void setComputersNb(Double computersNb) {
		this.computersNb = computersNb;
	}

	public Double getComputersPt() {
		return computersPt;
	}

	public void setComputersPt(Double computersPt) {
		this.computersPt = computersPt;
	}

	public Double getComputersPro() {
		return computersPro;
	}

	public void setComputersPro(Double computersPro) {
		this.computersPro = computersPro;
	}

	public Double getComputersTotal() {
		return computersTotal;
	}

	public void setComputersTotal(Double computersTotal) {
		this.computersTotal = computersTotal;
	}

	public Double getEnglish() {
		return english;
	}

	public void setEnglish(Double english) {
		this.english = english;
	}

	public Double getEnglishNb() {
		return englishNb;
	}

	public void setEnglishNb(Double englishNb) {
		this.englishNb = englishNb;
	}

	public Double getEnglishPt() {
		return englishPt;
	}

	public void setEnglishPt(Double englishPt) {
		this.englishPt = englishPt;
	}

	public Double getEnglishPro() {
		return englishPro;
	}

	public void setEnglishPro(Double englishPro) {
		this.englishPro = englishPro;
	}

	public Double getEnglishTotal() {
		return englishTotal;
	}

	public void setEnglishTotal(Double englishTotal) {
		this.englishTotal = englishTotal;
	}

	public Double getPhysics() {
		return physics;
	}

	public void setPhysics(Double physics) {
		this.physics = physics;
	}

	public Double getChemistry() {
		return chemistry;
	}

	public void setChemistry(Double chemistry) {
		this.chemistry = chemistry;
	}

	public Double getChemistryPt() {
		return chemistryPt;
	}

	public void setChemistryPt(Double chemistryPt) {
		this.chemistryPt = chemistryPt;
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

	public Double getChemistryTotal() {
		return chemistryTotal;
	}

	public void setChemistryTotal(Double chemistryTotal) {
		this.chemistryTotal = chemistryTotal;
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

	public Double getPhysicsPt() {
		return physicsPt;
	}

	public void setPhysicsPt(Double physicsPt) {
		this.physicsPt = physicsPt;
	}

	public Double getChemistryPer() {
		return chemistryPer;
	}

	public void setChemistryPer(Double chemistryPer) {
		this.chemistryPer = chemistryPer;
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

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public String getCca() {
		return cca;
	}

	public void setCca(String cca) {
		this.cca = cca;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getDance() {
		return dance;
	}

	public void setDance(String dance) {
		this.dance = dance;
	}

	public Long getAttendance() {
		return attendance;
	}

	public void setAttendance(Long attendance) {
		this.attendance = attendance;
	}

	public Double getTotalScored() {
		return totalScored;
	}

	public void setTotalScored(Double totalScored) {
		this.totalScored = totalScored;
	}

	public Double getTotalPer() {
		return totalPer;
	}

	public void setTotalPer(Double totalPer) {
		this.totalPer = totalPer;
	}

	
	
	

	
	

}