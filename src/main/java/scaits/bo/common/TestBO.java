package scaits.bo.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_test")
public class TestBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEST_ID")
	private long id;

	@Column(name = "TEST_NAME", length = 50)
	private String name;
	
	@Column(name = "MAX_MARKS")
	private Long maxMarks;
	
	@Column(name = "QUALIFY_MARKS")
	private Long qualifyMarks;
	
	@Column(name = "STATUS")
	private Boolean status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(Long maxMarks) {
		this.maxMarks = maxMarks;
	}

	public Long getQualifyMarks() {
		return qualifyMarks;
	}

	public void setQualifyMarks(Long qualifyMarks) {
		this.qualifyMarks = qualifyMarks;
	}
	
	
	
	


	


	
}