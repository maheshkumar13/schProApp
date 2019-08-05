package scaits.bo.student;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import scaits.bo.employee.EmployeeBO;


@Entity
@Table(name = "t_transaction")
public class TransactionBO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANS_ID")
	private long id;

	// @Column(name = "CAMPUS_TRANS_ID")
	// private long campusTransId;

	@Column(name = "HEAD_CODE", length = 50)
	private String headCode;

	@Column(name = "SUB_HEAD_CODE", length = 100)
	private String subHeadCode;

	@Column(name = "PAY_MODE")
	private String payMode;


	@Column(name = "AMOUNT")
	private float amount;
	
	@Column(name = "AMOUNT_INWORDS")
	private String amountInWords;


	@Column(name = "AUTO_GEN_RECEIPT_NO", length = 50)
	private Long receiptNo;

	@Column(name = "PRE_PRINTED_RECEIPT_NO", length = 50)
	private Long manualReceipt;

	@Column(name = "INSTALLMENT_NO", length = 2)
	private int installmentNo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "CANCEL_TRANS_ID")
	private TransactionBO cancelTransaction;


	@Column(name = "PAY_DATE")
	private Date payDate;

	@Column(name = "DAYSHEET_DATE")
	private Date daysheetDate;

	@Column(name = "DAYSHEET_STATUS")
	private Character daysheetStatus;

	@Column(name = "DESCRIPTION", length = 250)
	private String description;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY")
	private EmployeeBO createdBy;

	@Column(name = "CREATED_BY_NAME", length = 250)
	private String createdByName;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY")
	private EmployeeBO updatedBy;

	@Transient
	@Column(name = "CHEQUE_DD_NO", length = 50)
	private String chequeDDNo;

	@Transient
	@Column(name = "CHEQUE_DATE")
	private Date chequeDate;

	@Transient
	@Column(name = "CHEQUE_DD_BANK_NAME", length = 50)
	private String chequeDDBankname;

	@Transient
	@Column(name = "CHEQUE_DD_BRANCH_NAME", length = 50)
	private String chequeDDBranchname;

	@Transient
	@Column(name = "CHEQUE_DD_CITY_NAME", length = 50)
	private String chequeDDCityname;

	@Transient
	@Column(name = "CHEQUE_DD_DATE")
	private Date chequeDDDate;

	// @Column(name = "UPDATE_STATUS")
	// private boolean updateStatus;

	// @ManyToOne(fetch=FetchType.LAZY)
	// @JoinColumn(name = "PAYMENT_ID")
	// private PaymentDetailsBO paymentDetails;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLASS_ID")
	private StudyClassBO studyClass;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAMPUS_ID")
	private CampusBO campus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACADEMIC_ID")
	private AcademicYearBO academic;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STUDENT_NO")
	private StudentBO student;

	public TransactionBO() {
}
	public TransactionBO(float amount, StudentBO student, EmployeeBO employee,String HeadCode, String SubHeadCode,String PayMode) {
		super();
		this.amount = amount;
		this.student = student;
		this.createdBy = employee;
		this.updatedBy = employee;
		this.updatedOn = new Date();
		this.createdOn = new Date();
		this.headCode = HeadCode;
		this.subHeadCode = SubHeadCode;
		this.payMode = PayMode;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getHeadCode() {
		return headCode;
	}


	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}


	public String getSubHeadCode() {
		return subHeadCode;
	}


	public void setSubHeadCode(String subHeadCode) {
		this.subHeadCode = subHeadCode;
	}


	public String getPayMode() {
		return payMode;
	}


	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public Long getReceiptNo() {
		return receiptNo;
	}


	public void setReceiptNo(Long receiptNo) {
		this.receiptNo = receiptNo;
	}


	public Long getManualReceipt() {
		return manualReceipt;
	}


	public void setManualReceipt(Long manualReceipt) {
		this.manualReceipt = manualReceipt;
	}


	public int getInstallmentNo() {
		return installmentNo;
	}


	public void setInstallmentNo(int installmentNo) {
		this.installmentNo = installmentNo;
	}


	public TransactionBO getCancelTransaction() {
		return cancelTransaction;
	}


	public void setCancelTransaction(TransactionBO cancelTransaction) {
		this.cancelTransaction = cancelTransaction;
	}


	public Date getPayDate() {
		return payDate;
	}


	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}


	public Date getDaysheetDate() {
		return daysheetDate;
	}


	public void setDaysheetDate(Date daysheetDate) {
		this.daysheetDate = daysheetDate;
	}


	public Character getDaysheetStatus() {
		return daysheetStatus;
	}


	public void setDaysheetStatus(Character daysheetStatus) {
		this.daysheetStatus = daysheetStatus;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public String getChequeDDNo() {
		return chequeDDNo;
	}


	public void setChequeDDNo(String chequeDDNo) {
		this.chequeDDNo = chequeDDNo;
	}


	public Date getChequeDate() {
		return chequeDate;
	}


	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}


	public String getChequeDDBankname() {
		return chequeDDBankname;
	}


	public void setChequeDDBankname(String chequeDDBankname) {
		this.chequeDDBankname = chequeDDBankname;
	}


	public String getChequeDDBranchname() {
		return chequeDDBranchname;
	}


	public void setChequeDDBranchname(String chequeDDBranchname) {
		this.chequeDDBranchname = chequeDDBranchname;
	}


	public String getChequeDDCityname() {
		return chequeDDCityname;
	}


	public void setChequeDDCityname(String chequeDDCityname) {
		this.chequeDDCityname = chequeDDCityname;
	}


	public Date getChequeDDDate() {
		return chequeDDDate;
	}


	public void setChequeDDDate(Date chequeDDDate) {
		this.chequeDDDate = chequeDDDate;
	}


	public StudyClassBO getStudyClass() {
		return studyClass;
	}


	public void setStudyClass(StudyClassBO studyClass) {
		this.studyClass = studyClass;
	}


	public CampusBO getCampus() {
		return campus;
	}


	public void setCampus(CampusBO campus) {
		this.campus = campus;
	}


	public AcademicYearBO getAcademic() {
		return academic;
	}


	public void setAcademic(AcademicYearBO academic) {
		this.academic = academic;
	}


	public StudentBO getStudent() {
		return student;
	}


	public void setStudent(StudentBO student) {
		this.student = student;
	}


	public String getAmountInWords() {
		return amountInWords;
	}


	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}

	
	
	
	

}