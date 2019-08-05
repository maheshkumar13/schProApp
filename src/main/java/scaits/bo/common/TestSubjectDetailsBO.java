package scaits.bo.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import scaits.bo.student.SubjectBO;

@Entity(name = "t_test_subject")
public class TestSubjectDetailsBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TEST_ID")
	private TestBO test;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBJECT_ID")
	private SubjectBO testSubject;

	@Column(name = "SUB_MAX_MARKS")
	private Long subMaxMarks;
	
	@Column(name = "SUB_QUALIFY_MARKS")
	private Long subQualifyMarks;
	
	@Column(name = "PRACTICAL")
	private Long practical;
	
	public Long getPractical() {
		return practical;
	}

	public void setPractical(Long practical) {
		this.practical = practical;
	}

	@Column(name = "STATUS")
	private Boolean status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TestBO getTest() {
		return test;
	}

	public void setTest(TestBO test) {
		this.test = test;
	}

	

	public SubjectBO getTestSubject() {
		return testSubject;
	}

	public void setTestSubject(SubjectBO testSubject) {
		this.testSubject = testSubject;
	}

	public Long getSubMaxMarks() {
		return subMaxMarks;
	}

	public void setSubMaxMarks(Long subMaxMarks) {
		this.subMaxMarks = subMaxMarks;
	}

	public Long getSubQualifyMarks() {
		return subQualifyMarks;
	}

	public void setSubQualifyMarks(Long subQualifyMarks) {
		this.subQualifyMarks = subQualifyMarks;
	}
	
	
	
	


	


	
}