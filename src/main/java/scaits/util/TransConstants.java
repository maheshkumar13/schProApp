package scaits.util;

public interface TransConstants {

	public interface DaysSheetStatus {
		public static char OPEN = 'O';
		public static char STUDENT_TRANSFER_OR_COURSE_CHANGED = 'T';
		public static char CLOSE = 'C';
		public static char NON_CASH = 'N';
		public static char CONFIRMATION = 'P';
		public static char BANK_INSTRUMENTS_SHIFTED = 'S';
		public static char ON_LINE_PAYMENTS = 'B';
		public static char EXCESS_CANCELLED = 'E';
		public static char ADJUSTMENT = 'A';
	}

	public interface PaymentGateWayStatus {
		public static String SUCCESS = "SUCCESS";
		public static String FAIL = "FAIL";
	}

	public interface PaymentGateWayPaymentTypes {
		public static String PGW_NET_BANKING = "PGW_NET_BANKING";
		public static String PWG_CARD_PAYMENT = "PWG_CARD_PAYMENT";
	}

	public interface PaymentGateWayPaidFrom {
		public static String INTERNET_PAID = "INTERNET_PAID";
		public static String PARENT_LOGIN = "PARENT_LOGIN";
	}

	public interface PaymentGateWayStatus_F_CODE {
		public static String Ok = "OK";
		public static String FORCESUCCESS = "FORCE SUCCESS";
		public static String F = "F";
	}

	public interface PaymentGateWayStatus_VPC_MESSAGE {
		public static String APPROVED = "APPROVED";
		public static String F = "F";
	}

	public interface MessType {
		public static String DELIVERED = "DELIVERED";
		public static String CONSUMPTION = "CONSUMPTION";
		public static String ADJUSTMENT = "ADJUSTMENT";
		public static String RECEIVED = "RECEIVED";
		public static String PURCHASE = "PURCHASE";
		public static String BAKERYMAKING = "BAKERY MAKING";
		public static String WASTAGE = "WASTAGE";
		public static String TRANSFER = "TRANSFER";
		public static String OPENSTOCK = "OPEN STOCK";
		public static String TRANSFEREDRECEIVED = "TRANSFERED RECEIVED";
		public static String INDENTPENDING = "INDENT PENDING";
		public static String INDENTCANCELLED = "INDENT CANCELLED";
		public static String RECEIVEDPENDING = "RECEIVED PENDING";
		public static String STOCKRECEIVED = "STOCK RECEIVED";
		public static String NOTRECEIVED = "NOT RECEIVED";
		public static String PARTIALRECEIVED = "PARTIAL RECEIVED";
		public static String STOCKNOTSENT = "STOCK NOT SENT";
	}

	public interface InventoryType {
		public static String GODOWN = "GODOWN";
		public static String BAKERY = "BAKERY";
		public static String ROTI = "ROTI";
		public static String MESS = "MESS";
	}

	public interface InventoryCancelledTypes {
		public static String CANCELLED = "CANCELLED";
	}

	public interface FeeSummaryType {
		public static final long COURSE_FEE = 1l;
		public static final long COURSE_FEE_CONCESSION = 2l;
		public static final long ADD_COURSE_FEE = 3l;
		public static final long COURSE_FEE_PAID = 4l;
		public static final long COURSE_FEE_DUE = 5l;
		public static final long MATERIAL_FEE = 6l;
		public static final long STUDENT_WEL_FUND = 7l;
		public static final long MISCELLANEOUS = 8l;
		public static final long CDEPOSIT = 9l;
		public static final long CD_BREAKAGE = 10l;
		public static final long POCMNY_DEPOSIT = 11l;
		public static final long POCMNY_ISSUED = 12l;
		public static final long PM_REFUND = 13l;
		public static final long EXAM_FEE = 14l;
		public static final long APPFEE = 15l;
		public static final long FEE_REFUND = 16l;
		public static final long BUS_PASS_ROUTE_FEE = 17l;
		public static final long BUS_PASS_PAID = 18l;
		public static final long BP_REFUND = 19l;
		public static final long COURSE_FEE_FIRSTBAL = 20l;
		public static final long PAID_FIRSTBAL_2YR = 21l;
		public static final long AKASH_BOOKS = 22l;
		public static final long TUTION_FEE = 23l;
		public static final long TUTION_FEE_CONCESSION = 24l;
		public static final long TUTION_FEE_PAID = 25l;
		public static final long TUTION_FEE_DUE = 26l;
		public static final long TUTION_FEE_REFUND = 27l;
		public static final long TUTION_FEE_FIRSTBAL = 28l;
		public static final long TUTION_FEE_PAID_FIRSTBAL_2YR = 29l;
		public static final long COACHING_FEE = 30l;
		public static final long COACHING_FEE_CONCESSION = 31l;
		public static final long COACHING_FEE_PAID = 32l;
		public static final long COACHING_FEE_DUE = 33l;
		public static final long COACHING_FEE_REFUND = 34l;
		public static final long COACHING_FEE_FIRSTBAL = 35l;
		public static final long COACHING_FEE_PAID_FIRSTBAL_2YR = 36l;
		public static final long BOARDING_FEE = 37l;
		public static final long BOARDING_FEE_CONCESSION = 38l;
		public static final long BOARDING_FEE_PAID = 39l;
		public static final long BOARDING_FEE_DUE = 40l;
		public static final long BOARDING_FEE_REFUND = 41l;
		public static final long BOARDING_FEE_FIRSTBAL = 42l;
		public static final long BOARDING_FEE_PAID_FIRSTBAL_2YR = 43l;
		public static final long CD_REFUND = 44l;
		public static final long INTERNAL_EXAM_FEE = 45l;
		public static final long JEE_MAINS_EXAM_FEE = 46l;
		public static final long NEET_UG_EXAM_FEE = 47l;

		public static final long AKASH_BOOKS_REFUND = 48l;
		public static final long EXAM_FEE_REFUND = 49l;
		public static final long INTERNAL_EXAM_FEE_REFUND = 50l;
		public static final long JEE_MAINS_EXAM_FEE_REFUND = 51l;
		public static final long MATERIAL_FEE_REFUND = 52l;
		public static final long MISCELLANEOUS_REFUND = 53l;
		public static final long NEET_UG_EXAM_FEE_REFUND = 54l;
		public static final long STUDENT_WEL_FUND_REFUND = 55l;
		public static final long IPE_PRACTICAL_FEE = 56l;
		public static final long IPE_PRACTICAL_FEE_REFUND = 57l;
		public static final long CRD_BOOKS = 58l;
		public static final long CRD_BOOKS_REFUND = 59l;
		public static final long EAMCET_APP_FEE = 60l;
		public static final long EAMCET_APP_FEE_REFUND = 61l;

		public static final long DHOBI = 80l;
		public static final long DHOBI_REFUND = 81l;
		public static final long SERVICE_TAX_DUE = 93l;
		public static final long COACHING_FEE_SERVICE_TAX_PAID = 130l;
		public static final long ADD_COURSE_FEE_DUE = 118l;
		public static final long COURSE_CHANGE_PENALTY_DUE = 124l;
		public static final long ADD_COURSE_FEE_SERVICE_TAX_DUE = 116l;
		public static final long COURSE_CHANGE_PENALTY_SERVICE_TAX_DUE = 117l;

		public static final long HOSTEL_FEE = 86l;
		public static final long HOSTEL_FEE_CONCESSION = 87l;
		public static final long HOSTEL_FEE_PAID = 88l;
		public static final long HOSTEL_FEE_DUE = 89l;
		public static final long HOSTEL_FEE_REFUND = 90l;
		public static final long HOSTEL_FEE_ADD_COURSE_FEE_DUE = 122l;

		public static final long UNIFORM_FEE = 78l;
		public static final long UNIFORM_FEE_REFUND = 79l;

		public static final long BUSPASS_CONCESSION = 158l;

		public static final long BUSPASS_DUE = 159l;
		public static final long BUSPASS_PAYABLEFARE = 159l;

		public static final long BOOKS_FEE = 160l;
		public static final long BOOKS_FEE_CONCESSION = 161l;
		public static final long BOOKS_FEE_PAID = 162l;
		public static final long BOOKS_FEE_DUE = 163l;
		public static final long BOOKS_FEE_REFUND = 164l;

	}

	public interface HeadCode {
		public static String AKASH_BOOKS = "AKASH_BOOKS";
		public static String APPFEE = "APPFEE";
		public static String BUSPASS = "BUSPASS";
		public static String CDEPOSIT = "CDEPOSIT";
		public static String EXAM_FEE = "EXAM_FEE";
		public static String INTERNAL_EXAM_FEE = "INTERNAL_EXAM_FEE";
		public static String JEE_MAINS_EXAM_FEE = "JEE_MAINS_EXAM_FEE";
		public static String NEET_UG_EXAM_FEE = "NEET_UG_EXAM_FEE";
		public static String IPE_PRACTICAL_FEE = "IPE_PRACTICAL_FEE";
		public static String CRD_BOOKS = "CRD_BOOKS";
		public static String EAMCET_APP_FEE = "EAMCET_APP_FEE";
		public static String FEE = "FEE";
		public static String MATERIAL_FEE = "MATERIAL_FEE";
		public static String MISCELLANEOUS = "MISCELLANEOUS";
		public static String POCMNY = "POCMNY";
		public static String STUDENT_WEL_FUND = "STUDENT_WEL_FUND";
		public static String VAT = "VAT";
		// public static String SERVICE_TAX = "SERVICE_TAX";
		public static String TAX = "TAX";
		public static String CAUTION_DEPOSIT = "CAUTION DEPOSIT";
		public static String DHOBI_CHARGES = "DHOBI_CHARGES";
		public static String UNIFORM_FEE = "UNIFORM_FEE";
	}

	public interface PaymentHeadCodes {
		public static String AKASH_BOOKS = "AKASH_BOOKS";
		public static String APPFEE = "APPFEE";
		public static String BUSPASS = "BUSPASS";
		public static String CDEPOSIT = "CDEPOSIT";
		public static String EXAM_FEE = "EXAM_FEE";
		public static String INTERNAL_EXAM_FEE = "INTERNAL_EXAM_FEE";
		public static String JEE_MAINS_EXAM_FEE = "JEE_MAINS_EXAM_FEE";
		public static String NEET_UG_EXAM_FEE = "NEET_UG_EXAM_FEE";
		public static String IPE_PRACTICAL_FEE = "IPE_PRACTICAL_FEE";
		public static String CRD_BOOKS = "CRD_BOOKS";
		public static String EAMCET_APP_FEE = "EAMCET_APP_FEE";
		public static String FEE = "FEE";
		public static String MATERIAL_FEE = "MATERIAL_FEE";
		public static String MISCELLANEOUS = "MISCELLANEOUS";
		public static String POCMNY = "POCMNY";
		public static String STUDENT_WEL_FUND = "STUDENT_WEL_FUND";
		public static String VAT = "VAT";
		public static String SECOND_FEE_PAYMENT = "SECOND_FEE_PAYMENT";
		public static String SECOND_BUSPASS_PAYMENT = "SECOND_BUSPASS_PAYMENT";
		public static String TAX = "TAX";
	}

	public interface BankInstrumentPaymentResourse {
		public static String APPLICATION_SALES = "APPLICATION SALES";
		public static String PAYMENT = "PAYMENT";
		public static String SCRAP = "SCRAP";
		public static String TALENTTEST = "TALENTTEST";
		public static String MISCELLANEOUS_INCOME = "MISCELLANEOUS_INCOME";
		public static String EX_STUDENT_PAYMENT = "EX STUDENT PAYMENT";
		public static String CANCELLED = "CANCELLED";
	}

	public interface BankInstrumentClearanceStatus {
		public static String UN_CLEARED = "UN CLEARED";
		public static String BOUNCE = "BOUNCE";
		public static String DEPOSITED = "DEPOSITED";
		public static String CLEARANCE = "CLEARANCE";
	}

	public interface ApplicationSalesHeadCodes {

		public static String REG = "REG";
		public static String PAID = "PAID";
		public static String PRO_APP_FEE = "PRO_APP_FEE";
		public static String PRO_PAID = "PRO_PAID";
		public static String REG_APP_FEE = "REG_APP_FEE";
		public static String REG_PAID = "REG_PAID";
	}

	public interface DaySheetSummaryHeadCodes {
		public static String PRO_APP_FEE = "PRO_APP_FEE";
		public static String APP_FEE = "APP_FEE";
		public static String FEE = "FEE";
		public static String PRO_PAID = "PRO_PAID";
		public static String PRO_INCENTIVE = "PRO INCENTIVE";
		public static String TUTION_FEE = "TUTION_FEE";
		public static String HOSTEL_FEE = "HOSTEL_FEE";
		public static String REG_APP_FEE = "REG_APP_FEE";
		public static String REG_PAID = "REG_PAID";
		public static String SCRAP = "SCRAP";
		public static String TALENTTEST = "TALENTTEST";
		public static String MISCELLANEOUS_INCOME = "MISCELLANEOUS_INCOME";
		public static String SPECIAL_CONCESSION = "SPECIAL CONCESSION";
		public static String STATE_GST = "STATE GST";
		public static String CENTRAL_GST = "CENTRAL GST";
		public static String BOOKS_FEE = "BOOKS FEE";
	}

	public interface AttendanceCode {
		public static String A = "--";
		public static String A1PRESENT = "P";
		public static String A2ABSENT = "A";
		public static String A3LEFT = "L";
		public static String A4ON_DUTY = "O";
		public static String A5HOLIDAY = "H";
		public static String A6BANDH = "B";
		public static String A7CASUAL_LEAVE = "C";
		public static String A8SICK_LEAVE = "S";
	}

	public interface BankDenominationsCode {
		public static String TWOTHOUSANDS = "TWOTHOUSANDS";
		public static String THOUSANDS = "THOUSANDS";
		public static String FIVE_HUNDREDS = "FIVE_HUNDREDS";
		public static String TWO_HUNDREDS = "TWO_HUNDREDS";
		public static String ONE_HUNDREDS = "ONE_HUNDREDS";
		public static String FIFTYS = "FIFTYS";
		public static String TWENTYS = "TWENTYS";
		public static String TENS = "TENS";
		public static String FIVES = "FIVES";
		public static String TWOS = "TWOS";
		public static String ONES = "ONES";
		public static String SHORTAGE_AMOUNT = "SHORTAGE_AMOUNT";
		public static String TOTAL_AMOUNT = "TOTAL_AMOUNT";
	}

	public interface PayMode {
		public static String CASH = "CASH";
		public static String NON_CASH = "NON_CASH";
		public static String DD = "DD";
		public static String CHEQUE = "CHEQUE";
		public static String DEPOSITED = "DEPOSITED";
		public static String CARD = "CARD";
		public static String BANK = "BANK";
		public static String PROCARD = "PROCARD";
	}

	public interface SubHeadCode {
		public static String ADD_COURSE_FEE = "ADD_COURSE_FEE";
		public static String AKASH_BOOKS = "AKASH_BOOKS";
		public static String APPFEE = "APPFEE";
		public static String BP_PAYMENT = "BP_PAYMENT";
		public static String BP_REFUND = "BP_REFUND";
		public static String CDEPOSIT = "CDEPOSIT";
		public static String CD_BREAKAGE = "CD_BREAKAGE";
		public static String CONCESSION = "CONCESSION";
		public static String COURSE_CHANGE_PENALTY = "COURSE_CHANGE_PENALTY";
		public static String COURSE_FEE = "COURSE_FEE";
		public static String DEPOSITED = "DEPOSITED";
		public static String EXAM_FEE = "EXAM_FEE";
		public static String INTERNAL_EXAM_FEE = "INTERNAL_EXAM_FEE";
		public static String JEE_MAINS_EXAM_FEE = "JEE_MAINS_EXAM_FEE";
		public static String NEET_UG_EXAM_FEE = "NEET_UG_EXAM_FEE";
		public static String IPE_PRACTICAL_FEE = "IPE_PRACTICAL_FEE";
		public static String CRD_BOOKS = "CRD_BOOKS";
		public static String EAMCET_APP_FEE = "EAMCET_APP_FEE";
		public static String FEE_REFUND = "FEE_REFUND";
		public static String ISSUED = "ISSUED";
		public static String CD_REFUND = "CD_REFUND";
		public static String MATERIAL_FEE = "MATERIAL_FEE";
		public static String MISCELLANEOUS = "MISCELLANEOUS";
		public static String PAYMENT = "PAYMENT";
		public static String PM_REFUND = "PM_REFUND";
		public static String PREVIOUS_ROUTE_FEE = "PREVIOUS_ROUTE_FEE";
		public static String ROUTE_FEE = "ROUTE_FEE";
		public static String STUDENT_WEL_FUND = "STUDENT_WEL_FUND";
		public static String VAT = "VAT";
		public static String SERVICE_TAX = "SERVICE_TAX";
		public static String DHOBI_REFUND = "DHOBI_CHARGES_REFUND";

		public static String UNIFORM_FEE_REFUND = "UNIFORM_FEE_REFUND";

		public static String AKASH_BOOKS_REFUND = "AKASH_BOOKS_REFUND";
		public static String EXAM_FEE_REFUND = "EXAM_FEE_REFUND";
		public static String INTERNAL_EXAM_FEE_REFUND = "INTERNAL_EXAM_FEE_REFUND";
		public static String JEE_MAINS_EXAM_FEE_REFUND = "JEE_MAINS_EXAM_FEE_REFUND";
		public static String MATERIAL_FEE_REFUND = "MATERIAL_FEE_REFUND";
		public static String MISCELLANEOUS_REFUND = "MISCELLANEOUS_REFUND";
		public static String NEET_UG_EXAM_FEE_REFUND = "NEET_UG_EXAM_FEE_REFUND";
		public static String STUDENT_WEL_FUND_REFUND = "STUDENT_WEL_FUND_REFUND";
		public static String IPE_PRACTICAL_FEE_REFUND = "IPE_PRACTICAL_FEE_REFUND";
		public static String CRD_BOOKS_REFUND = "CRD_BOOKS_REFUND";
		public static String EAMCET_APP_FEE_REFUND = "EAMCET_APP_FEE_REFUND";

		public static String BUSPASS = "BUSPASS";
	}

	public interface SchoolType {
		public static String SSC = "1";
		public static String CBSE = "2";
		public static String ICSE = "3";
		public static String MPBOARD = "4";
		public static String OTHERS = "5";
	}

	public interface SubTransCode {
		public static String PREVIOUS = "PREVIOUS";
		public static String COMMON = "COMMON";
		public static String TUTION_FEE = "TUTION_FEE";
		public static String HOSTEL_FEE = "HOSTEL_FEE";
		public static String COURSE_FEE = "COURSE_FEE";
		public static String COACHING_FEE = "COACHING_FEE";
		public static String BOARDING_FEE = "BOARDING_FEE";
		public static String PAID = "PAID";
		public static String PAID_FOR_AKASH_BOOKS = "PAID_FOR_AKASH_BOOKS";
		public static String PAID_CDEPOSIT = "PAID_CDEPOSIT";
		public static String PAID_EXAM_FEE = "PAID_EXAM_FEE";
		public static String PAID_INTERNAL_EXAM_FEE = "PAID_INTERNAL_EXAM_FEE";
		public static String PAID_JEE_MAINS_EXAM_FEE = "PAID_JEE_MAINS_EXAM_FEE";
		public static String PAID_NEET_UG_EXAM_FEE = "PAID_NEET_UG_EXAM_FEE";
		public static String PAID_IPE_PRACTICAL_FEE = "PAID_IPE_PRACTICAL_FEE";
		public static String PAID_CRD_BOOKS = "PAID_CRD_BOOKS";
		public static String PAID_EAMCET_APP_FEE = "PAID_EAMCET_APP_FEE";
		public static String PAID_POCMNY = "PAID_POCMNY";
		public static String PAID_MATERIAL_FEE = "PAID_MATERIAL_FEE";
		public static String PAID_MISCELLANEOUS = "PAID_MISCELLANEOUS";
		public static String PAID_STUDENT_WEL_FUND = "PAID_STUDENT_WEL_FUND";
		public static String CONCESSION = "CONCESSION";
		public static String CONCESSION_CANCELLED = "CONCESSION_CANCELLED";
		public static String ISSUED_SINGLE = "ISSUED_SINGLE";
		public static String ISSUED_SINGLE_CANCELLED = "ISSUED_SINGLE_CANCELLED";
		public static String ISSUED_COMMON_CANCELLED = "ISSUED_COMMON_CANCELLED";
		public static String REFUND_CDEPOSIT_EXCESS_AMOUNT = "REFUND_CDEPOSIT_EXCESS_AMOUNT";
		public static String OB_PAID_POCMNY = "OB_PAID_POCMNY";
		public static String PAID_PRO_PORTAL = "PAID_PRO_PORTAL";
		public static String PAID_REG_PORTAL = "PAID_REG_PORTAL";
		public static String OB_PAID_PRO_PORTAL = "OB_PAID_PRO_PORTAL";
		public static String OB_PAID_REG_PORTAL = "OB_PAID_REG_PORTAL";
		public static String OB_PAID_AKASH_BOOKS = "OB_PAID_AKASH_BOOKS";
		public static String OB_PAID_CDEPOSIT = "OB_PAID_CDEPOSIT";
		public static String OB_PAID_EXAM_FEE = "OB_PAID_EXAM_FEE";
		public static String OB_PAID_INTERNAL_EXAM_FEE = "OB_PAID_INTERNAL_EXAM_FEE";
		public static String OB_PAID_JEE_MAINS_EXAM_FEE = "OB_PAID_JEE_MAINS_EXAM_FEE";
		public static String OB_PAID_NEET_UG_EXAM_FEE = "OB_PAID_NEET_UG_EXAM_FEE";
		public static String OB_PAID_IPE_PRACTICAL_FEE = "OB_PAID_IPE_PRACTICAL_FEE";
		public static String OB_PAID_CRD_BOOKS = "OB_PAID_CRD_BOOKS";
		public static String OB_PAID_EAMCET_APP_FEE = "OB_PAID_EAMCET_APP_FEE";
		public static String OB_PAID_MATERIAL_FEE = "OB_PAID_MATERIAL_FEE";
		public static String OB_PAID_MISCELLANEOUS = "OB_PAID_MISCELLANEOUS";
		public static String OB_PAID_STUDENT_WEL_FUND = "OB_PAID_STUDENT_WEL_FUND";
		public static String PAID_BP_PAYMENT = "PAID_BP_PAYMENT";
		public static String ROUTE_FEE = "ROUTE_FEE";
		public static String ROUTE_FEE_CANCELLED = "ROUTE_FEE_CANCELLED";
		public static String ROUTE_FEE_CHANGED = "ROUTE_FEE_CHANGED";
		public static String DEDUCTION_FOR_TRANSFER_TO_OTHER_CAMPUS = "DEDUCTION_FOR_TRANSFER_TO_OTHER_CAMPUS";
		public static String CD_BREAKAGE_OR_OTHER = "CD_BREAKAGE_OR_OTHER";
		public static String POCKET_MONEY_BALANCE_ADJUSTMENT = "POCKET_MONEY_BALANCE_ADJUSTMENT";
		public static String PANINEEYA_FEE = "PANINEEYA_FEE";

		public static String BOOKS_FEE = "BOOKS_FEE";
	}

	public interface TransCode {
		public static String ADD_COURSE_FEE = "ADD_COURSE_FEE";
		public static String CD_BREAKAGE_OR_OTHER = "CD_BREAKAGE_OR_OTHER";
		public static String CONCESSION = "CONCESSION";
		public static String CONCESSION_CANCELLED = "CONCESSION_CANCELLED";
		public static String CONCESSION_CANCELLED_STUDENT_TRANSFERRED = "CONCESSION_CANCELLED STUDENT TRANSFERRED";
		public static String CONCESSION_UPDATE = "CONCESSION_UPDATE";

		public static String COURSE_FEE = "COURSE_FEE";
		public static String COURSE_FEE_CANCELLED = "COURSE_FEE_CANCELLED";
		public static String COURSE_FEE_CHANGED = "COURSE_FEE_CHANGED";
		public static String COURSE_FEE_FIRSTBAL = "COURSE_FEE_FIRSTBAL";
		public static String DEDUCTION_FOR_TRANSFER_TO_OTHER_CAMPUS = "DEDUCTION_FOR_TRANSFER_TO_OTHER_CAMPUS";
		public static String EXCESS_FEE_REFUND = "EXCESS_FEE_REFUND";
		public static String ISSUED_COMMON = "ISSUED_COMMON";
		public static String ISSUED_COMMON_CANCELLED = "ISSUED_COMMON_CANCELLED";
		public static String ISSUED_SINGLE = "ISSUED_SINGLE";
		public static String ISSUED_SINGLE_CANCELLED = "ISSUED_SINGLE_CANCELLED";
		public static String MATERIAL_FEE = "MATERIAL_FEE";
		public static String OB_PAID = "OB_PAID";
		public static String OB_PAID_AKASH_BOOKS = "OB_PAID_AKASH_BOOKS";
		public static String OB_PAID_CANCELLED = "OB_PAID_CANCELLED";
		public static String OB_PAID_CDEPOSIT = "OB_PAID_CDEPOSIT";
		public static String OB_PAID_EXAM_FEE = "OB_PAID_EXAM_FEE";
		public static String OB_PAID_INTERNAL_EXAM_FEE = "OB_PAID_INTERNAL_EXAM_FEE";
		public static String OB_PAID_JEE_MAINS_EXAM_FEE = "OB_PAID_JEE_MAINS_EXAM_FEE";
		public static String OB_PAID_NEET_UG_EXAM_FEE = "OB_PAID_NEET_UG_EXAM_FEE";
		public static String OB_PAID_IPE_PRACTICAL_FEE = "OB_PAID_IPE_PRACTICAL_FEE";
		public static String OB_PAID_CRD_BOOKS = "OB_PAID_CRD_BOOKS";
		public static String OB_PAID_EAMCET_APP_FEE = "OB_PAID_EAMCET_APP_FEE";
		public static String OB_PAID_FIRSTBAL = "OB_PAID_FIRSTBAL";
		public static String OB_PAID_FIRSTBAL_2YR = "OB_PAID_FIRSTBAL_2YR";
		public static String OB_PAID_MATERIAL_FEE = "OB_PAID_MATERIAL_FEE";
		public static String OB_PAID_MISCELLANEOUS = "OB_PAID_MISCELLANEOUS";
		public static String OB_PAID_POCMNY = "OB_PAID_POCMNY";
		public static String OB_PAID_PRO_PORTAL = "OB_PAID_PRO_PORTAL";
		public static String OB_PAID_REG_PORTAL = "OB_PAID_REG_PORTAL";
		public static String OB_PAID_STUDENT_WEL_FUND = "OB_PAID_STUDENT_WEL_FUND";
		public static String OB_PRO_RECEIVED = "OB_PRO_PAID_RECEIVED";
		public static String PAID = "PAID";
		public static String PAID_BP_PAYMENT = "PAID_BP_PAYMENT";
		public static String PAID_CANCELLED = "PAID_CANCELLED";
		public static String PAID_CDEPOSIT = "PAID_CDEPOSIT";
		public static String PAID_EXAM_FEE = "PAID_EXAM_FEE";
		public static String PAID_INTERNAL_EXAM_FEE = "PAID_INTERNAL_EXAM_FEE";
		public static String PAID_JEE_MAINS_EXAM_FEE = "PAID_JEE_MAINS_EXAM_FEE";
		public static String PAID_NEET_UG_EXAM_FEE = "PAID_NEET_UG_EXAM_FEE";
		public static String PAID_IPE_PRACTICAL_FEE = "PAID_IPE_PRACTICAL_FEE";
		public static String PAID_CRD_BOOKS = "PAID_CRD_BOOKS";
		public static String PAID_EAMCET_APP_FEE = "PAID_EAMCET_APP_FEE";
		public static String PAID_FIRSTBAL = "PAID_FIRSTBAL";
		public static String PAID_SECOND_BAL = "PAID_SECOND_BAL";
		public static String OB_PAID_SECOND_BAL = "OB_PAID_SECOND_BAL";
		public static String PAID_FIRSTBAL_2YR = "PAID_FIRSTBAL_2YR";
		public static String PAID_FIRSTBAL_2YR_CANCELLED = "PAID_FIRSTBAL_2YR_CANCELLED";
		public static String PAID_FIRSTYEAR_2YR = "PAID_FIRSTYEAR_2YR";
		public static String PAID_FIRSTYEAR_EXCESS = "PAID_FIRSTYEAR_EXCESS";
		public static String PAID_SECONDYEAR_EXCESS = "PAID_SECONDYEAR_EXCESS";
		public static String PAID_THIRDYEAR_EXCESS = "PAID_THIRDYEAR_EXCESS";
		public static String OB_PAID_FIRSTYEAR_EXCESS = "OB_PAID_FIRSTYEAR_EXCESS";
		public static String OB_PAID_SECONDYEAR_EXCESS = "OB_PAID_SECONDYEAR_EXCESS";
		public static String OB_PAID_THIRDYEAR_EXCESS = "OB_PAID_THIRDYEAR_EXCESS";
		public static String PAID_FOR_AKASH_BOOKS = "PAID_FOR_AKASH_BOOKS";
		public static String PAID_MATERIAL_FEE = "PAID_MATERIAL_FEE";
		public static String PAID_MISCELLANEOUS = "PAID_MISCELLANEOUS";
		public static String PAID_PAYMENT_CANCELLED = "PAID_PAYMENT_CANCELLED";
		public static String PAID_POCMNY = "PAID_POCMNY";
		public static String PAID_PRO_PORTAL = "PAID_PRO_PORTAL";
		public static String PAID_REG_PORTAL = "PAID_REG_PORTAL";
		public static String PAID_STUDENT_WEL_FUND = "PAID_STUDENT_WEL_FUND";
		public static String PAID_VAT = "PAID_VAT";
		public static String PM_REFUND = "PM_REFUND";
		public static String POCKET_MONEY_BALANCE_ADJUSTMENT = "POCKET_MONEY_BALANCE_ADJUSTMENT";
		public static String PRO_PAID_CANCELLED = "PRO_PAID_CANCELLED";
		public static String PRO_RECEIVED = "PRO_RECEIVED";
		public static String PRO_RECEIVED_CANCELLED = "PRO_RECEIVED_CANCELLED";
		public static String REFUND_BUSPASS_EXCESS_AMOUNT = "REFUND_BUSPASS_EXCESS_AMOUNT";
		public static String REFUND_WHEN_STUDENT_DISCONTINUES_COURSE = "REFUND_WHEN_STUDENT_DISCONTINUES_COURSE";
		public static String ROUTE_FEE = "ROUTE_FEE";
		public static String ROUTE_FEE_CANCELLED = "ROUTE_FEE_CANCELLED";
		public static String ROUTE_FEE_CHANGED = "ROUTE_FEE_CHANGED";
		public static String REFUND_CDEPOSIT_EXCESS_AMOUNT = "REFUND_CDEPOSIT_EXCESS_AMOUNT";
		public static String REFUND_DHOBI_EXCESS_AMOUNT = "REFUND_DHOBI_EXCESS_AMOUNT";
		public static String COACHING_FEE_ADJUSTMENT_TO_TUTION_FEE_WHEN_STUDENT_TRANSFERRED = "COACHING_FEE_ADJUSTMENT_TO_TUTION_FEE_WHEN_STUDENT_TRANSFERRED";
		public static String COACHING_FEE_ADJUSTMENT_TO_TUTION_FEE_WHEN_STUDENT_COURSE_CHANGED = "COACHING_FEE_ADJUSTMENT_TO_TUTION_FEE_WHEN_STUDENT_COURSE_CHANGED";
		// public static String COURSE_FEE_SPLIT = "COURSE_FEE_SPLIT";
		// public static String COURSE_FEE_CHANGE_SPLIT =
		// "COURSE_FEE_CHANGE_SPLIT";
		// public static String PRE_PAID_NON_TAX = "PRE_PAID_NON_TAX";
		// public static String CANCELLED = "CANCELLED";
		// public static String REFUND = "REFUND";
		// public static String ADJUSTMENT = "ADJUSTMENT";
		public static String SERVICE_TAX = "SERVICE_TAX";
		public static String PAID_ADD_COURSE_FEE = "PAID_ADD_COURSE_FEE";
		public static String PAID_CANCELLED_ADD_COURSE_FEE = "PAID_CANCELLED_ADD_COURSE_FEE";
		public static String OB_PAID_ADD_COURSE_FEE = "OB_PAID_ADD_COURSE_FEE";
		public static String OB_PAID_CANCELLED_ADD_COURSE_FEE = "OB_PAID_CANCELLED_ADD_COURSE_FEE";
		public static String COURSE_CHANGE_PENALTY = "COURSE_CHANGE_PENALTY";
		public static String PAID_COURSE_CHANGE_PENALTY = "PAID_COURSE_CHANGE_PENALTY";
		public static String PAID_CANCELLED_COURSE_CHANGE_PENALTY = "PAID_CANCELLED_COURSE_CHANGE_PENALTY";
		public static String OB_PAID_COURSE_CHANGE_PENALTY = "OB_PAID_COURSE_CHANGE_PENALTY";
		public static String OB_PAID_CANCELLED_COURSE_CHANGE_PENALTY = "OB_PAID_CANCELLED_COURSE_CHANGE_PENALTY";

		public static String EXCESS_ADJUSTMENT_COACHING_FEE = "EXCESS_ADJUSTMENT_COACHING_FEE";
		public static String EXCESS_ADJUSTMENT_HOSTEL_FEE = "EXCESS_ADJUSTMENT_HOSTEL_FEE";
		public static String EXCESS_ADJUSTMENT_TUTION_FEE = "EXCESS_ADJUSTMENT_TUTION_FEE";

		public static String REFUND_UNIFORM_FEE_EXCESS_AMOUNT = "REFUND_UNIFORM_FEE_EXCESS_AMOUNT";
		public static String BUSPASS = "BUSPASS";
	}

	public interface TransType {
		public static String CREDIT = "CREDIT";
		public static String DEBIT = "DEBIT";
	}

	public interface PaymentType {
		public static String PAYMENT = "PAYMENT";
		public static String CASH_PICKUP = "CASH PICKUP";
		public static String CASH_BANK_DEPOSIT = "CASH BANK DEPOSIT";
		public static String CHEQUE_BANK_DEPOSIT = "CHEQUE BANK DEPOSIT";
		public static String DD_BANK_DEPOSIT = "DD BANK DEPOSIT";
		public static String CHEQUE_CLEARANCE = "CHEQUE CLEARANCE";
		public static String DD_CLEARANCE = "DD CLEARANCE";
		public static String CHEQUE_BOUNCE = "CHEQUE BOUNCE";
		public static String DD_BOUNCE = "DD BOUNCE";
		public static String DAYSHEET_CLOSE_AMOUNT = "DAYSHEET CLOSE AMOUNT";
		public static String CANCELLED_AMOUNT = "CANCELLED AMOUNT";
	}

	public interface ApplicationStatus {
		public static String ZONE = "AT_ZO";
		public static String CAMPUS = "AT_CA";
		public static String SOLD = "AT_SO";
		public static String CONFIRM = "AT_CO";
		public static String SOLD_IC = "AT_SO_IC";
		public static String SOLD_BK = "AT_SO_BK";
		public static String SOLD_OFF = "AT_SO_OFF";
	}

	public interface StudentStatus {
		public static String CURRENT = "CURRENT";
		public static String LONGABSENT = "LONGABSENT";
		public static String OUTGOING = "OUTGOING";
		public static String LEFT = "LEFT";
	}

	public interface RefundStatus {
		public static String REQUEST = "Request";
		public static String DIVISIONAL_APPROVED = "DivisionalChecked";
		public static String APPROVED = "Approved";
		public static String REJECT = "Reject";
		public static String DIVISIONAL_REJECT = "DivisionalReject";
		public static String CONFIRMED = "Confirmed";
	}

	public interface ApplicationSearchSource {
		public static int ZONE = 1;
		public static int CAMPUS = 2;
		public static int SOLD = 3;
	}

	public interface ReceiptType {
		public static String RECEIPT_CANCELLED = "Receipt Cancelled";
		public static String APPLICATION_SALES = "APPLICATION SALES";
		public static String REFUND = "REFUND";
		public static String AKASHPRINT = "Akash Print";
		public static String UNIFORMPRINT = "Uniform Print";
	}

	public interface CampusType {
		public static String RESIDENTIAL = "RESIDENTIAL";
		public static String DAYSCHOLAR = "DAYSCHOLAR";
		public static String DS_AND_RES = "DS AND RES";
	}

	public interface OrientationType {
		public static String REGULAR = "REGULAR";
		public static String LONG_TERM = "LONG_TERM";
		public static String SHORT_TERM = "SHORT_TERM";
	}

	public interface PROAmountPaidType {
		public static String PRO_APP_FEE = "PRO_APP_FEE";
		public static String REG_APP_FEE = "REG_APP_FEE";
		public static String PRO_PAID = "PRO_PAID";
		public static String SPECIAL_CONCESSION = "SPECIAL CONCESSION";
		public static String PRO_INCENTIVE = "PRO INCENTIVE";
	}

	public interface DecoratorTitles {
		public static String EMPLOYEE_MANAGEMENT = "Manager Mapping";
		public static String TIME_TABLE = "Time Table";
		public static String SECTION_TIMETABLE = "Section Time Table";
		public static String EMPLOYEE_AVAILABLE_TIME = "Employee Available Time";
		public static String TIMETABLE_PLAN = "Time Table Plan";
		public static String GENERATE_TIMETABLE = "Daywise Time Table";
		public static String CAMPUS_TIMETABLE_REPORT = "Campus Daywise Time Table Report";
		public static String PENDING_EMPLOYEES = "Pending Employees";
		public static String MANAGER_MAPPING = "Manager Mapping Report";
		public static String NEW_EMPLOYEE = "New Employee";
		public static String MANAGER_REMAPPING = "Manager ReMapping";
		public static String PERIOD_DURATION = "TimeTable Period Duration";
		public static String EMPLOYEE_UTILIZED = "Lecturer Utilized Details";
	}

	public interface ClassName {
		public static String INTER1 = "INTER1";
		public static String INTER2 = "INTER2";

		public static String DEGREE1 = "DEGREE1";
		public static String DEGREE2 = "DEGREE2";
		public static String DEGREE3 = "DEGREE3";

		public static String TYP1 = "TYP1";
		public static String TYP2 = "TYP2";

		public static String PG1 = "PG1";
		public static String PG2 = "PG2";

	}
}
