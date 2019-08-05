package scaits.bo.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_grade")
public class GradeBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRADE_ID")
	private long id;

	@Column(name = "GRADE", length = 10)
	private String grade;

	
	@Column(name = "GRADE_RANGE", length = 10)
	private String gradeRande;
	
	
	@Column(name = "GRADE_RESULT", length = 30)
	private String gradeResult;
	
	

	@Column(name = "STATUS")
	private boolean status;



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public String getGradeRande() {
		return gradeRande;
	}



	public void setGradeRande(String gradeRande) {
		this.gradeRande = gradeRande;
	}



	public String getGradeResult() {
		return gradeResult;
	}



	public void setGradeResult(String gradeResult) {
		this.gradeResult = gradeResult;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}




	
}