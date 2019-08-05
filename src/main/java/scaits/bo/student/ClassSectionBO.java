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


@Entity
@Table(name = "t_class_section")
public class ClassSectionBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SECTION_ID")
	private long id;

	@Column(name = "SECTION_NAME", length = 30)
	private String sectionName;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASS_ID")
	private StudyClassBO classId;

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


	@Column(name = "STATUS")
	private boolean status;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSectionName() {
		return sectionName;
	}


	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public StudyClassBO getClassId() {
		return classId;
	}


	public void setClassId(StudyClassBO classId) {
		this.classId = classId;
	}


}