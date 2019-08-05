package scaits.bo.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_school")
public class SchoolBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "SCHOOL_NAME", length = 50)
	private String schoolName;
	
	
	
	@Column(name = "STATUS")
	private boolean status;




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getSchoolName() {
		return schoolName;
	}




	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}




	public boolean isStatus() {
		return status;
	}




	public void setStatus(boolean status) {
		this.status = status;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	


	
}