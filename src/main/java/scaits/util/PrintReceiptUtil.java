package scaits.util;

import java.util.Date;
import java.util.List;

import scaits.bo.student.CampusBO;
import scaits.bo.student.ClassSectionBO;
import scaits.bo.student.StudentBO;
import scaits.bo.student.TransactionBO;


public class PrintReceiptUtil {

	private StudentBO student;
	private CampusBO campus;
	private TransactionBO transaction;
	private ClassSectionBO classSection;
	private String payMode;
	private Long generatedReceiptNO;
	private Long preprintedReceipt;
	private String payDate;
	private String chequeDDBankName;
	private String chequeDDBranchName;
	private String chequeDDCityName;
	private String chequeDDNO;
	private String chequeDDDate;
	private float amount;
	private float totalAmount;
	private float serviceTaxAmount;
	private String amountinwords;
	private float amountExcludingTax;
	private String amountExcludingTaxinwords;
	private String receiptType;
	private String secondPreprintedReceipt;
	private List<TransactionBO> transactions;
	private String receiptPrintType;
	private String paymentType;
	private CampusBO proposedCampus;
	private String headCode;
	private String subheadCode;
	private String subTransCode;
	private String studentName;
	private String stuParentName;
	private CampusBO paidCampus;
	private String printDisplayName;
	private String organizationName;
	private Long orgId;
	private String orgManagedName;
	private String serviceTaxId;
	private String payStatus;
	private Long paidReceiptNo;
	private String stateGSTCode;
	private String receiverStateGSTCode;
	private String branchStateGSTCode;

	private Long classId;
	private boolean taxPrint;
	private long appNo;
	private String HSNCode;

	private String courseDisplayName;

	private String cityName;
	private String stateName;
	private String campusName;
	private String groupName;
	private String medium;
	private String sectionName;
	private Date daysheetDate;

	private Long acknowledgementReceipt;
	private String acknowledgementChequeDDCardNo;
	public StudentBO getStudent() {
		return student;
	}
	public void setStudent(StudentBO student) {
		this.student = student;
	}
	public CampusBO getCampus() {
		return campus;
	}
	public void setCampus(CampusBO campus) {
		this.campus = campus;
	}
	public TransactionBO getTransaction() {
		return transaction;
	}
	public void setTransaction(TransactionBO transaction) {
		this.transaction = transaction;
	}
	public ClassSectionBO getClassSection() {
		return classSection;
	}
	public void setClassSection(ClassSectionBO classSection) {
		this.classSection = classSection;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public Long getGeneratedReceiptNO() {
		return generatedReceiptNO;
	}
	public void setGeneratedReceiptNO(Long generatedReceiptNO) {
		this.generatedReceiptNO = generatedReceiptNO;
	}
	public Long getPreprintedReceipt() {
		return preprintedReceipt;
	}
	public void setPreprintedReceipt(Long preprintedReceipt) {
		this.preprintedReceipt = preprintedReceipt;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getChequeDDBankName() {
		return chequeDDBankName;
	}
	public void setChequeDDBankName(String chequeDDBankName) {
		this.chequeDDBankName = chequeDDBankName;
	}
	public String getChequeDDBranchName() {
		return chequeDDBranchName;
	}
	public void setChequeDDBranchName(String chequeDDBranchName) {
		this.chequeDDBranchName = chequeDDBranchName;
	}
	public String getChequeDDCityName() {
		return chequeDDCityName;
	}
	public void setChequeDDCityName(String chequeDDCityName) {
		this.chequeDDCityName = chequeDDCityName;
	}
	public String getChequeDDNO() {
		return chequeDDNO;
	}
	public void setChequeDDNO(String chequeDDNO) {
		this.chequeDDNO = chequeDDNO;
	}
	public String getChequeDDDate() {
		return chequeDDDate;
	}
	public void setChequeDDDate(String chequeDDDate) {
		this.chequeDDDate = chequeDDDate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public float getServiceTaxAmount() {
		return serviceTaxAmount;
	}
	public void setServiceTaxAmount(float serviceTaxAmount) {
		this.serviceTaxAmount = serviceTaxAmount;
	}
	public String getAmountinwords() {
		return amountinwords;
	}
	public void setAmountinwords(String amountinwords) {
		this.amountinwords = amountinwords;
	}
	public float getAmountExcludingTax() {
		return amountExcludingTax;
	}
	public void setAmountExcludingTax(float amountExcludingTax) {
		this.amountExcludingTax = amountExcludingTax;
	}
	public String getAmountExcludingTaxinwords() {
		return amountExcludingTaxinwords;
	}
	public void setAmountExcludingTaxinwords(String amountExcludingTaxinwords) {
		this.amountExcludingTaxinwords = amountExcludingTaxinwords;
	}
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public String getSecondPreprintedReceipt() {
		return secondPreprintedReceipt;
	}
	public void setSecondPreprintedReceipt(String secondPreprintedReceipt) {
		this.secondPreprintedReceipt = secondPreprintedReceipt;
	}
	public List<TransactionBO> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionBO> transactions) {
		this.transactions = transactions;
	}
	public String getReceiptPrintType() {
		return receiptPrintType;
	}
	public void setReceiptPrintType(String receiptPrintType) {
		this.receiptPrintType = receiptPrintType;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public CampusBO getProposedCampus() {
		return proposedCampus;
	}
	public void setProposedCampus(CampusBO proposedCampus) {
		this.proposedCampus = proposedCampus;
	}
	public String getHeadCode() {
		return headCode;
	}
	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}
	public String getSubheadCode() {
		return subheadCode;
	}
	public void setSubheadCode(String subheadCode) {
		this.subheadCode = subheadCode;
	}
	public String getSubTransCode() {
		return subTransCode;
	}
	public void setSubTransCode(String subTransCode) {
		this.subTransCode = subTransCode;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStuParentName() {
		return stuParentName;
	}
	public void setStuParentName(String stuParentName) {
		this.stuParentName = stuParentName;
	}
	public CampusBO getPaidCampus() {
		return paidCampus;
	}
	public void setPaidCampus(CampusBO paidCampus) {
		this.paidCampus = paidCampus;
	}
	public String getPrintDisplayName() {
		return printDisplayName;
	}
	public void setPrintDisplayName(String printDisplayName) {
		this.printDisplayName = printDisplayName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrgManagedName() {
		return orgManagedName;
	}
	public void setOrgManagedName(String orgManagedName) {
		this.orgManagedName = orgManagedName;
	}
	public String getServiceTaxId() {
		return serviceTaxId;
	}
	public void setServiceTaxId(String serviceTaxId) {
		this.serviceTaxId = serviceTaxId;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public Long getPaidReceiptNo() {
		return paidReceiptNo;
	}
	public void setPaidReceiptNo(Long paidReceiptNo) {
		this.paidReceiptNo = paidReceiptNo;
	}
	public String getStateGSTCode() {
		return stateGSTCode;
	}
	public void setStateGSTCode(String stateGSTCode) {
		this.stateGSTCode = stateGSTCode;
	}
	public String getReceiverStateGSTCode() {
		return receiverStateGSTCode;
	}
	public void setReceiverStateGSTCode(String receiverStateGSTCode) {
		this.receiverStateGSTCode = receiverStateGSTCode;
	}
	public String getBranchStateGSTCode() {
		return branchStateGSTCode;
	}
	public void setBranchStateGSTCode(String branchStateGSTCode) {
		this.branchStateGSTCode = branchStateGSTCode;
	}
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public boolean isTaxPrint() {
		return taxPrint;
	}
	public void setTaxPrint(boolean taxPrint) {
		this.taxPrint = taxPrint;
	}
	public long getAppNo() {
		return appNo;
	}
	public void setAppNo(long appNo) {
		this.appNo = appNo;
	}
	public String getHSNCode() {
		return HSNCode;
	}
	public void setHSNCode(String hSNCode) {
		HSNCode = hSNCode;
	}
	public String getCourseDisplayName() {
		return courseDisplayName;
	}
	public void setCourseDisplayName(String courseDisplayName) {
		this.courseDisplayName = courseDisplayName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCampusName() {
		return campusName;
	}
	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public Date getDaysheetDate() {
		return daysheetDate;
	}
	public void setDaysheetDate(Date daysheetDate) {
		this.daysheetDate = daysheetDate;
	}
	public Long getAcknowledgementReceipt() {
		return acknowledgementReceipt;
	}
	public void setAcknowledgementReceipt(Long acknowledgementReceipt) {
		this.acknowledgementReceipt = acknowledgementReceipt;
	}
	public String getAcknowledgementChequeDDCardNo() {
		return acknowledgementChequeDDCardNo;
	}
	public void setAcknowledgementChequeDDCardNo(String acknowledgementChequeDDCardNo) {
		this.acknowledgementChequeDDCardNo = acknowledgementChequeDDCardNo;
	}

	

}
