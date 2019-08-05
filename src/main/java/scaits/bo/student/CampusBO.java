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

@Entity(name = "t_campus")
public class CampusBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "CAMPUS_NAME", length = 10)
	private String campusName;
	
	@Column(name = "SCHOOL_NAME", length = 10)
	private String schoolName;
	

	@Column(name = "STATUS")
	private boolean status;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}



	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public String getCampusName() {
		return campusName;
	}


	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

}