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

import scaits.bo.employee.EmployeeBO;

@Entity(name = "t_academic_year")
public class AcademicYearBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACADEMIC_ID")
	private long id;

	@Column(name = "ACADEMIC_YEAR", length = 10)
	private String academicYear;

	@Column(name = "JOIN_YEAR", length = 5)
	private String joinYear;

	@Column(name = "STATUS")
	private boolean status;

	@Column(name = "RECEIPT_STATUS", length = 1)
	private String receiptStatus;

	@Column(name = "START_ON")
	private Date startOn;

	@Column(name = "END_ON")
	private Date endOn;

	@Column(name = "FY_START_ON")
	private Date fyStartOn;

	@Column(name = "FY_END_ON")
	private Date fyEndOn;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	private EmployeeBO createdBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	private EmployeeBO modifiedBy;

	@Column(name = "UPDATED_ON")
	private Date modifiedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASS_ID")
	private StudyClassBO studyclass;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NEXT_ACADEMIC_ID")
	private AcademicYearBO nextYear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PREV_ACADEMIC_ID")
	private AcademicYearBO previousYear;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	public String getJoinYear() {
		return joinYear;
	}

	public void setJoinYear(String joinYear) {
		this.joinYear = joinYear;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public Date getStartOn() {
		return startOn;
	}

	public void setStartOn(Date startOn) {
		this.startOn = startOn;
	}

	public Date getEndOn() {
		return endOn;
	}

	public void setEndOn(Date endOn) {
		this.endOn = endOn;
	}

	public Date getFyStartOn() {
		return fyStartOn;
	}

	public void setFyStartOn(Date fyStartOn) {
		this.fyStartOn = fyStartOn;
	}

	public Date getFyEndOn() {
		return fyEndOn;
	}

	public void setFyEndOn(Date fyEndOn) {
		this.fyEndOn = fyEndOn;
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

	public EmployeeBO getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(EmployeeBO modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public StudyClassBO getStudyclass() {
		return studyclass;
	}

	public void setStudyclass(StudyClassBO studyclass) {
		this.studyclass = studyclass;
	}

	public AcademicYearBO getNextYear() {
		return nextYear;
	}

	public void setNextYear(AcademicYearBO nextYear) {
		this.nextYear = nextYear;
	}

	public AcademicYearBO getPreviousYear() {
		return previousYear;
	}

	public void setPreviousYear(AcademicYearBO previousYear) {
		this.previousYear = previousYear;
	}

}
