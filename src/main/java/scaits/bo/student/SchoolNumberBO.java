package scaits.bo.student;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "t_school_numbers")
public class SchoolNumberBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROW_ID")
	private long id;

	@Column(name = "APP_START_NO", length = 10)
	private long appStartNo;
	
	@Column(name = "APP_END_NO", length = 10)
	private long appEndNo;
	
	@Column(name = "PRE_PRINTED_RECEIPT_NO", length = 10)
	private long prePrintedReceiptNo;
	
	@Column(name = "AUTO_GEN_RECEIPT_NO", length = 10)
	private long autoGenreceiptNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACADEMIC_ID")
	private AcademicYearBO academicId;
	
	@Column(name = "AUTO_EMP_ID", length = 10)
	private long autoEmpId;

	@Column(name = "STATUS")
	private boolean status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAppStartNo() {
		return appStartNo;
	}

	public void setAppStartNo(long appStartNo) {
		this.appStartNo = appStartNo;
	}

	public long getAppEndNo() {
		return appEndNo;
	}

	public void setAppEndNo(long appEndNo) {
		this.appEndNo = appEndNo;
	}

	public long getPrePrintedReceiptNo() {
		return prePrintedReceiptNo;
	}

	public void setPrePrintedReceiptNo(long prePrintedReceiptNo) {
		this.prePrintedReceiptNo = prePrintedReceiptNo;
	}

	public long getAutoGenreceiptNo() {
		return autoGenreceiptNo;
	}

	public void setAutoGenreceiptNo(long autoGenreceiptNo) {
		this.autoGenreceiptNo = autoGenreceiptNo;
	}

	public AcademicYearBO getAcademicId() {
		return academicId;
	}

	public void setAcademicId(AcademicYearBO academicId) {
		this.academicId = academicId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getAutoEmpId() {
		return autoEmpId;
	}

	public void setAutoEmpId(long autoEmpId) {
		this.autoEmpId = autoEmpId;
	}
	
	

	

}
