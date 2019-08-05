package scaits.bo.student;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "t_study_class")
public class StudyClassBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLASS_ID")
	private long id;

	@Column(name = "CLASS_NO")
	private long classNo;

	@Column(name = "CLASS_NAME", length = 30)
	private String className;

	@Column(name = "PRINTING_NAME", length = 30)
	private String printingName;

	@Column(name = "DISPLAY_NAME", length = 30)
	private String displayName;

	@Column(name = "RECIPT_NAME", length = 30)
	private String reciptName;

	@Enumerated(EnumType.STRING)
	@Column(name = "MEDIUM")
	private Medium medium = Medium.E;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "studyclass")
	private AcademicYearBO academicYear;

	public enum Medium {
		E, T
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Medium getMedium() {
		return medium;
	}

	public void setMedium(Medium medium) {
		this.medium = medium;
	}

	public long getClassNo() {
		return classNo;
	}

	public void setClassNo(long classNo) {
		this.classNo = classNo;
	}

	public String getPrintingName() {
		return printingName;
	}

	public void setPrintingName(String printingName) {
		this.printingName = printingName;
	}

	public String getReciptName() {
		return reciptName;
	}

	public void setReciptName(String reciptName) {
		this.reciptName = reciptName;
	}

	public AcademicYearBO getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(AcademicYearBO academicYear) {
		this.academicYear = academicYear;
	}

}
