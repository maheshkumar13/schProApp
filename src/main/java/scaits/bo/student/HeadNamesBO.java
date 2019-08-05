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
@Table(name = "t_head_names")
public class HeadNamesBO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "HEAD_NAME", length = 100)
	private String headName;
	
	@Column(name = "SUB_HEAD_NAME", length = 100)
	private String subHeadName;
	
	@Column(name = "STATUS")
	private boolean status;

	@Column(name = "DESCRIPTION", length = 150)
	private String description;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	private EmployeeBO createdBy;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	private EmployeeBO updatedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EmployeeBO getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(EmployeeBO createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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

	public String getSubHeadName() {
		return subHeadName;
	}

	public void setSubHeadName(String subHeadName) {
		this.subHeadName = subHeadName;
	}
	
	
	
	

	

}
