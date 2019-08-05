package scaits.util;

public interface Constants {
	public static final String USER_KEY = "user";

	public static final String EMPLOYEE_KEY = "employee";
	public static final String ACCESS_TOKEN = "token";
	public static final String QB_SERVICE_NAMESPACE = "http://192.168.1.93:8081";
	public static final String MESS_LOGIN = "MessLogin";
	public static final String LOGINATTEMPT_KEY = "loginAttempt";
	public static final String STUDENT_KEY = "student";
	public static final String SUPERMAC = "supermac";
	public static final String UUID = "uuid";
	public static final String NEWID = "newid";
	public static final String JSTORE = "jstore";
	public static final String MACID = "macid";
	public static final String TEST_ERROR = "TEST_ERROR";
	public static final String UUID_ENC_KEY = "scaiuuid";
	public static final String FEEDBACK = "feedback";

	public static final String STICKY_EMPLOYEE_ID = "StickyEmployeeId";
	public static final String STICKY_PAYROLL_ID = "StickyPayrollId";
	public static final String INV_DEFAULT_ID = "InentoryDefaultId";
	public static final String ATTEMPT_ID = "StickyAttemptId";
	public static final String STICKY_EMPLOYEE_PASSWORD = "StickyEmpH";

	public static final String STICKY_STUDENT_EMAIL_ID = "StickyStudentId";
	public static final String STICKY_STUDENT_PASSWORD = "StickyStudentH";

	public static final String STICKY_USER_EMAIL_ID = "StickyUserId";
	public static final String STICKY_USER_PASSWORD = "StickyUserH";
	public static final String JYEAR = "2013";

	public static final int INVALID_USERNAME = -1;
	public static final int INVALID_PASSWORD = -2;
	public static final int INVALID_NODE = -3;
	public static final int LOGIN_SUCCESS = 0;
	public static final int LOGIN_FAILED = -1;
	public static final int LOGIN_SUSPENDED = -4;

	public static final double PERCENTAGE_LIMIT = 20;
	public static final long STRAIGHT_OBJECTIVE_SINGLE_ANSWER = 1L;
	public static final long STRAIGHT_OBJECTIVE_MULTIPLE_ANSWERS = 2L;
	public static final long INTEGER = 3L;
	public static final long ASSERTION_REASONING = 4L;
	public static final long STATEMENT1_STATEMENT2 = 5L;
	public static final long ASCENDING_DESCENDING = 6L;
	public static final long INCREASING_DECREASING = 7L;
	public static final long MATRIX_MATCHING_2_COLUMN = 8L;
	public static final long MATRIX_MATCHING_3_COLUMN = 9L;
	public static final long SUBJECTIVE = 10L;
	public static final long COMPREHENSION = 11L;
	public static final long REASONING_STRAIGHTOBJECTIVE = 12L;
	public static final long REASONING_COMPREHENSION = 13L;
	public static final long FIGURE_FINDING = 14L;
	public static final long SUBJECTIVE_VERB = 15L;
	public static final long REASONING_COMPREHENSION_NEW_CHOICES = 16L;

	public static final String QUESTION_UPLOAD_DIRECTORY = "question.upload.directory";
	public static final String DOC2HTML_EXE_PATH = "doc2html.exe.path";
	public static final String DTD_PATH = "dtd.path";
	public static final String DOC_PATH = "document.upload.path";

	public static final String UPLOAD_DIRECTORY = "document.upload.path";
	public static final String CHANGE_EMAIL_UID = "uid";
	public static final String CHANGE_EMAIL_EID = "eid";

	public static final String HTML2DOC_EXE_PATH = "html2doc.exe.path";
	public static final String QP_GEN_PATH = "qp.gen.path";
	public static final String JAR_GEN_PATH = "jar.gen.path";

	public static final String VERSION_COLUMN_NAME = "HISTORY_ID";
	public static final String VERSION_PARAM_NAME = "versionId";

	public static final long COURSE_FEE_CATEGORY_ID = 1L;
	public static final long CONCESSION_CATEGORY_ID = 2L;
	public static final long EXTRA_COURSE_FEE_CATEGORY_ID = 3L;
	public static final long FEE_PAID_CATEGORY_ID = 4L;
	public static final long FEE_DUE_CATEGORY_ID = 5L;
	public static final long MATERIAL_FEE_PAID_CATEGORY_ID = 6L;
	public static final long WELFARE_FUND_PAID_CATEGORY_ID = 7L;
	public static final long MISCLEANEOUS_PAID_CATEGORY_ID = 8L;
	public static final long CAUTION_DEPOSIT_PAID_CATEGORY_ID = 9L;
	public static final long CAUTION_DEPOSIT_BREAKAGE_CATEGORY_ID = 10L;
	public static final long CAUTION_DEPOSIT_REFUND_CATEGORY_ID = 13L;
	public static final long POC_MONEY_DEPOSIT_CATEGORY_ID = 11L;
	public static final long POC_MONEY_ISSUED_CATEGORY_ID = 12L;
	public static final long POC_MONEY_REFUND_CATEGORY_ID = 14L;
	public static final long EXAM_FEE_CATEGORY_ID = 15L;
	public static final long APP_FEE_CATEGORY_ID = 16L;
	public static final long FEE_REFUND_CATEGORY_ID = 17L;
	public static final long BUS_PASS_FEE_CATEGORY_ID = 18L;
	public static final long BUS_PASS_PAID_CATEGORY_ID = 19L;
	public static final long BUS_PASS_REFUND_CATEGORY_ID = 20L;
	public static final long COURSE_FEE_FIRST_BALANCE_CATEGORY_ID = 21L;
	public static final long FISRT_YR_BAL_2ND_YR_PAID_CATEGORY_ID = 22L;
	public static final long AKASH_BOOKS_PAID_CATEGORY_ID = 23L;

	public static final long TUITION_FEE_CATEGORY_ID = 24L;
	public static final long TUITION_FEE_PAID_CATEGORY_ID = 25L;
	public static final long TUITION_FEE_DUE_CATEGORY_ID = 26L;

	public static final long COACHING_FEE_CATEGORY_ID = 27L;
	public static final long COACHING_FEE_PAID_CATEGORY_ID = 28L;
	public static final long COACHING_FEE_DUE_CATEGORY_ID = 29L;

	public static final long SERVICE_TAX_AMOUNT_CATEGORY_ID = 30L;
	public static final long SERVICE_TAX_REFUND_CATEGORY_ID = 31L;
	public static final long SERVICE_TAX_ADJUSTMENT_CATEGORY_ID = 32L;

	public static final float SERVICE_TAX_PERCENTAGE = 0.1236F;

	public static final long INTER1 = 1L;
	public static final long INTER2 = 2L;
	public static final long DEGREE1 = 7L;
	public static final long DEGREE2 = 8L;
	public static final long DEGREE3 = 9L;
	public static final long PG1 = 14L;
	public static final long PG2 = 15L;
	public static final long LONG_TERM = 6L;
	public static final long SHORT_TERM = 3L;
	public static final long OYP = 4L;
	public static final long TYP1 = 5L;
	public static final long TYP2 = 16L;
	public static final long IIT_FOUNDATION_8TH = 11L;
	public static final long IIT_FOUNDATION_9TH = 12L;
	public static final long IIT_FOUNDATION_10TH = 13L;

	public static final String EAMCET = "EAMCET";
	public static final long AP = 1L;
	public static final long TELANGANA = 41L;

	public static final String ONE_WAY = "ONE WAY";
	public static final String TWO_WAY = "TWO WAY";

	public static final long POCKETMONEY_SUBHEAD_CODE = 147L;

	public static final String PREVIOUS_USED_RECEIPT_NUMBER = "PREVIOUS_USED_RECEIPT_NUMBER";
	public static final String USED_PRE_PRINTED_RECEIPT_NUMBERS = "USED_PRE_PRINTED_RECEIPT_NUMBERS";
	public static final String USED_PRE_PRINTED_RECEIPT_PAY_MODE = "USED_PRE_PRINTED_RECEIPT_PAY_MODE";

	public static final String PGW_CAPTCH = "PGW_CAPTCH";

	public static final String OUTINS_STUDENTS = "OUTINS_STUDENTS";

	public static final String TELL_FRIEND = null;

	public static final String ASK_TEACHER = null;

	public static final String EMAIL_VALIDATION = null;
	public static final String XLS = "xls";
	public static final String PDF = "pdf";

	public static final String STUDENTNODUE = "NODUE";
	public static final String COMPANY = "COMPANY";

	public static final long DHOBHI_REFUND = 2;
	public static final String MIS_CAMPUS_ID = "MIS_CAMPUS_ID";
	public static final String PDATA_AUTH = "PDATA_AUTH";
	public static final String RANDOM_KEY = "RANDOM_KEY";

	// SMS template constants
	public static final String SMS_GENERIC = "SMS_GENERIC";
	public static final String SMS_SEND_PROMOCODE = "SMS_SEND_PROMOCODE";
	public static final String SMS_ORDER_BLUEDART_VPP = "SMS_ORDER_BLUEDART_VPP";
	public static final String SMS_DEMOCD_BLUEDART_POST = "SMS_DEMOCD_BLUEDART_POST";
	public static final String SMS_SUBSCRIPTION_ACTIVATION = "SMS_SUBSCRIPTION_ACTIVATION";
	public static final String SMS_OFFLINE_ACTIVATION = "SMS_OFFLINE_ACTIVATION";
	public static final String SMS_SINGLE_ITEM_ORDER_GOT_MARKED_PENDING = "SMS_SINGLE_ITEM_ORDER_GOT_MARKED_PENDING";
	public static final String SMS_MULTI_ITEM_ORDER_GOT_MARKED_PENDING = "SMS_MULTI_ITEM_ORDER_GOT_MARKED_PENDING";
	public static final String SMS_VPP_REMINDER = "SMS_VPP_REMINDER";
	public static final String SMS_SUBSCRIPTION_OFFLINE_ACTIVATION = "SMS_SUBSCRIPTION_OFFLINE_ACTIVATION";
	public static final String SMS_SCP_UPLOAD = "SMS_SCP_UPLOAD";
	public static final String SMS_CONFIRMATION_CODE = "SMS_CONFIRMATION_CODE";
	public static final String SMS_VALID_LUCKY_CODE = "SMS_VALID_LUCKY_CODE";
	public static final String SMS_INVALID_LUCKY_CODE = "SMS_INVALID_LUCKY_CODE";
	public static final String SMS_CONFIRM_WP_ORDER = "SMS_CONFIRM_WP_ORDER";
	public static final String SMS_CONFIRM_WP_ORDER_SUCCESS = "SMS_CONFIRM_WP_ORDER_SUCCESS";

	public static final String IMAGE_MODE_URL = "url";
	public static final String IMAGE_MODE_EMBEDDED = "embedded";

	public static final String RUN_MODE_CTR = "container";
	public static final String RUN_MODE_STANDALONE = "standalone";

	public static final int MAIL_STATUS_PENDING = 0;
	public static final int MAIL_STATUS_SENT_TO_MX = 1;
	public static final int MAIL_STATUS_DELIVERED = 2;
	public static final int MAIL_STATUS_PICKED = 3;
	public static final int MAIL_STATUS_ERROR_OTHER = -98;
	public static final int MAIL_STATUS_ERROR_MX = -99;
	public static final int MAIL_STATUS_ERROR_FILE_IO = -100;
	public static final int MAIL_STATUS_DEFERRED = -101;
	public static final int MAIL_STATUS_BOUNCED = -102;
	public static final String MAIL_STATUS_ALL = "10";

	public static final long ASSET_CATEGORY = 5;
	public static final String TICK_STATUS = "CLOSED";

	public static final String SUCCESFULLY_CREATED = "SUCCESFULLY CREATED ";
	public static final String SUCCESFULLY_UPDATED = "SUCCESFULLY UPDATED ";
	public static final String SUCCESFULLY_SAVED = "SUCCESFULLY SAVED ";
	public static final String RECORD_INSERTED_SUCCESSFULLY = "Record Inserted Successfully";
	public static final String RECORD_UPDATED_SUCCESSFULLY = "Record Updated Successfully";
	public static final String RECORD_CANCELED_SUCCESSFULLY = "Record Cancelled Successfully";
	public static final String FEEDBACK_SUCCESSFULLY_SAVED = "FEEDBACK-1-Successfully Saved";
	public static final String NO_RECORDS_FOUND = "No records Found";
	public static final String DATA_SAVED_SUCCESSFULLY = "Data Saved Successfully";
	public static final String PLEASE_CONTACT_SUPPORT = "Please Contact Support";

	public static final String MPC = "M.P.C";
	public static final String BIPC = "BI.P.C";
	public static final String MBIPC = "M.BI.P.C";

	public static final String SSC = "SSC";
	public static final String INTER = "INTER";
	public static final String DEGREE = "DEGREE";
	public static final String PG = "PG";
	public static final String PANCARD = "PANCARD";
	public static final String VOTER = "VOTER";

	public static final String POCKET_REFUND_NOTAVAILABLE = "FEEDBACK-1-Pocket Money  Refund Not Available";
	public static final String DHOBHI_REFUND_NOTAVAILABLE = "FEEDBACK-1-Dhobhi  Refund Not Available";

	public static final String BUSPASS_REFUND_NOTAVAILABLE = "FEEDBACK-1-BussPass  Refund Not Available";

	public static final String CAUTION_REFUND_NOTAVAILABLE = "FEEDBACK-1-Caution Deposit  Refund Not Available";

	public static final String PAYDATE_LESSTHAN_CURRENTDATE = "FEEDBACK-1-Payment date should be less than or equal to current date";

	public static final String NO_RECORDS_FEEDBACK = "FEEDBACK-1-No Record Found";

	public static final String JPG = ".JPG";
	public static final String JPEG = ".JPEG";
	public static final String PNG = ".PNG";
	public static final String GIF = ".GIF";

	public static final String jpg = ".jpg";
	public static final String jpeg = ".jpeg";
	public static final String png = ".png";
	public static final String gif = ".gif";

	public static final String COLLEGE = "COLLEGE";
	public static final String NOT_ALLOTTED = "NOT_ALLOTTED";

	public interface StreamNames {
		public static String MEDICON = "MEDICON";
		public static String ICON = "ICON";
		public static String LEO = "LEO";
		public static String NEON = "NEON";
	}

	public interface GST_TYPES {
		public static String CENTRAL = "CENTRAL";
		public static String STATE = "STATE";
	}

	public interface ConcurrencyMonitoringClassNames {
		public static String ManageGenerateTimeTableAction = "SAVE_DAY_WISE_TIMETABLE";
		public static String ManageTimeTableAction = "SAVE_TIMETABLE_PLAN";
		public static String ManageEmpTimeTableAction = "SAVE_EMPLOYEE_PERIODS";
		public static String StockDeliveryFormAction = "SAVE_DELIVERY_FORM";
		public static String EmpManagementSystemAction = "SAVE_EMPLOYEE";
		public static String EmpResignationAction = "SAVE_RESIGN";
		public static String ManageEmployeeDayWiseAttendanceAction = "SAVE_SHIFT_TIMINGS";
		public static String EmpManagerUnmapAction = "UNMAP_MANAGER";
		public static String EmpAgreedPeriodsAction = "SAVE_AGREED_PERIODS";
	}

	public interface ModuleOperations {
		public static String VIEW = "VIEW";
		public static String EDIT = "EDIT";
		public static String DELETE = "DELETE";
		public static String SAVE = "SAVE";
	}

	public interface BusWayTypes {
		public static String ONE_WAY = "ONE WAY";
		public static String TWO_WAY = "TWO WAY";
	}

	public interface SMSStatus {
		public static final int SMS_STATUS_IN_QUEUE = 0;
		public static final int SMS_STATUS_SENT_TO_SP = 3;
		public static final int INVALID_INPUT_DATA = -1;
		public static final int INVALID_ACCOUNT = -2;
		public static final int INVALID_SENDER = -3;
		public static final int INSUFFICIENT_FUNDS = -4;
		public static final int INVALID_NUMBER = -5;
		public static final int UNDELIVERED = -6;
		public static final int REJECTED = -7;
		public static final int MEMEXEC = -8;
		public static final int ABSENT = -9;
		public static final int UNKNOWN = -10;
		public static final int MOBOFF = -11;
		public static final int EXPIRED = -12;
		public static final int BARRING = -13;
		public static final int MOBERR = -14;
		public static final int UNKNOWN_LONG_PENDING = -15;
		public static final int SERIESBLOCK = -16;
		public static final int GENERIC_INVALID = -50;
		public static final int SUBMITTED = 5;
		public static final int DELIVERED = 6;
		public static final int SMS_STATUS_ERROR_OTHER = -98;
		public static final int SMS_STATUS_OPTED_OUT = -99;

		/*
		 * public static final int SMS_STATUS_ERROR_OTHER = -98; public static
		 * final int SMS_STATUS_OPTED_OUT = -103; public static final int
		 * SMS_STATUS_INVALID_NUMBER = -102;
		 *
		 * public static final int SMS_STATUS_SP_IN_QUEUE = 10; public static
		 * final int SMS_STATUS_SP_SUBMITTED_TO_CARRIER = 11; public static
		 * final int SMS_STATUS_SP_DELIVERED = 2; public static final int
		 * SMS_STATUS_SP_PICKED = 3; // all these are end statuses public static
		 * final int SMS_STATUS_SP_UNDELIVERED = -12; public static final int
		 * SMS_STATUS_SP_EXPIRED = -13; public static final int
		 * SMS_STATUS_SP_REJECTED = -14;
		 */
	}

	public interface OracleStatus {
		public static String STUDENT_UPDATE = "SU";
		public static String COURSE_UPDATE = "CU";
		public static String COURSE_UPDATE_ADDITIONNAL = "CA";
		public static String STUDENT_TRANSFER = "ST";
		public static String STUDENT_CONFIRMATION = "SC";
		public static String CONCESSION = "CO";
		public static String SECTION = "SE";
		public static String APPLICATION_SALES = "AS";

	}

	public interface MISReportTypes {
		public static String CAMPUS_OVERALL_FEE = "CAMPUS_OVERALL_FEE";
		public static String CAMPUS_ZERO_PAID = "CAMPUS_ZERO_PAID";
		public static String CAMPUS_ABOVE_50_PERC = "CAMPUS_ABOVE_50_PERC";
		public static String CLASS_WISE_FEE_COLLECTION = "CLASS_WISE_FEE_COLLECTION";
		public static String CAMPUS_ABOVE_50_PERCENTAGE = "OVERALL_ABOVE_50_PERCENTAGE";
		public static String CLASS_WISE_ABOVE_50_PERCENTAGE = "CLASS_WISE_ABOVE_50_PERCENTAGE";
		public static String CLASS_WISE_ZERO_PAID = "CLASS_WISE_ZERO_PAID";
	}

	public interface JSONStudentServiceErrors {
		public static final String STUDENT_ADM_NO = "100";
		public static final String STUDENT_ADM_NO_MSG = "Admission number not found";

		public static final String MOBILE_NO = "101";
		public static final String MOBILE_NO_MSG = "Mobile number not found";

		public static final String STUDENT_ADM_NO_VALID = "102";
		public static final String STUDENT_ADM_NO_VALID_MSG = "Admission number is not valid";

		public static final String MOBILE_NO_VALID = "103";
		public static final String MOBILE_NO_VALID_MSG = "Mobile number is not valid";

		public static final String EXCEPTION_VALID = "104";
		public static final String EXCEPTION_VALID_MSG = "Please contact sri chaitanya central office";

		public static final String DETAILS_VALID = "105";
		public static final String DETAILS_VALID_MSG = "Please enter any on of the above details";

		public static final String NAME_VALID = "106";
		public static final String NAME_VALID_MSG = "Student name and parent name are not found";

		public static final String CAMPUS_VALID = "107";
		public static final String CAMPUS_VALID_MSG = "Campus not found";

		public static final String CAMPUS_NOT_VALID = "107";
		public static final String CAMPUS_NOT_VALID_MSG = "Campus is not valid";

		public static final String NO_EMPLOYEE = "108";
		public static final String NO_EMPLOYEE_MSG = "No Employee with login id";

		public static final String WRONG_PASSWORD = "109";
		public static final String WRONG_PASSWORD_MSG = "Wrong password";

		public static final String LEFT_EMPLOYEE = "110";
		public static final String LEFT_EMPLOYEE_MSG = "Wrong password";

		public static final String NO_CAMPUS = "111";
		public static final String NO_CAMPUS_MSG = "Employee does't have CampusId";

		public static final String NO_PAYROLL = "112";
		public static final String NO_PAYROLL_MSG = "Payroll not found";

		public static final String NO_PASSWORD = "113";
		public static final String NO_PASSWORD_MSG = "Password not found";

		public static final String STUDENT_NO_ATTENDANCE = "114";
		public static final String STUDENT_NO_ATTENDANCE_MSG = "Attendance not found for this student";

		public static final String EMPLOYEE_PERMISSION = "115";
		public static final String EMPLOYEE_PERMISSION_MSG = "Employee doesn't have permission to login";

		public static final String INVALID_DETAILS = "116";
		public static final String INVALID_DETAILS_MSG = "Please enter username and password correct";

		public static final String NO_DATE = "117";
		public static final String NO_DATE_MSG = "Date not found";

		public static final String NO_SECTION = "118";
		public static final String NO_SECTION_MSG = "Section not found";

		public static final String SAVE_DATA = "119";
		public static final String SAVE_DATA_MSG = "Saved sucessfully";

		public static final String FEED_BACK = "120";

		public static final String VALID = "VALID";

	}

	public interface JSONSentServiceErrors {

		public static final String NO_PAYROLL = "110";
		public static final String NO_PAYROLL_MSG = "Payroll not found";

		public static final String NO_PASSWORD = "111";
		public static final String NO_PASSWORD_MSG = "Password not found";

		public static final String NO_MOBILE = "113";
		public static final String NO_MOBILE_MSG = "Mobile not found";

		public static final String NO_MESSAGE = "114";
		public static final String NO_MESSAGE_MSG = "Message not found";

		public static final String NO_EMPLOYEE = "115";
		public static final String NO_EMPLOYEE_MSG = "No Employee with login id";

		public static final String WRONG_PASSWORD = "116";
		public static final String WRONG_PASSWORD_MSG = "Wrong password";

		public static final String LEFT_EMPLOYEE = "117";
		public static final String LEFT_EMPLOYEE_MSG = "Employee is Left";

		public static final String MESSAGE_SENT = "200";
		public static final String MESSAGE_SENT_MSG = "Message is sent to";

	}

	public interface JSONPrintLogServiceErrors {

		public static final String PRINT_LOG_SAVED = "100";
		public static final String PRINT_LOG_SAVED_MSG = "Print logs data is succesfully saved";

		public static final String SERVER_SIDE_ISSUES = "404";
		public static final String SERVER_SIDE_ISSUES_MSG = "Logs are not saved due to server error";

		public static final String JSON_STRING = "101";
		public static final String JSON_STRING_MSG = "No data in json string";

	}

	public interface MappingStatus {
		public static final String ENABLED = "ENABLED";
		public static final String DISABLED = "DISABLED";
		public static final String APPROVED = "APPROVED";
		public static final String REJECTED = "REJECTED";
		public static final String TRANSFERED = "TRANSFERED";
		public static final String REQUEST = "REQUEST FOR APPROVAL";
		public static final String CONFIRMED = "CONFIRMED";
		public static final String REQUESTED = "REQUESTED";

	}

	public interface EmployeeType {
		public static final String TEACH = "TEACH";
		public static final String NONTEACH = "NON TEACH";
		public static final String APPROVED = "APPROVED";
		public static final String REJECTED = "REJECTED";
		public static final String TRANSFERED = "TRANSFERED";

	}

	public interface AssetMessages {
		public static final String NO_ASSET_AVAILABLE = "No Asset Available for";
		public static final String ASSET_NOT_AVAILABLE = "Asset is Not Available Trasnfer";
		public static final String NO_STUDENT_ALLOTED = "No Student Alloted  for Asset With Code";
		public static final String SUCCESS_UPDATED = "Successfully Updated";
		public static final String SUCCESS_CONFIRMED = "Successfully Confirmed";
		public static final String SUCCESS_TRANSFERED = "Successfully Transfered";
		public static final String SUCCESS_CANCELED = "Successfully Canceled Allotment for Asset Code";

	}

	public interface AuthenticationMessages {
		public static final String PASSWORD_CHANGED = "Your Password Changed Successfully";
		public static final String PASSWORD_DOESNT_MATCH = "Your Confirmation password doesn't match";
		public static final String PASSWORD_INCORRECT = "Password you entered is incorrect";
		public static final String PAYROLLID_INCORRECT = "Payrollid you entered is incorrect";
		public static final String PAYROLLID_INVALID = "Invalid PayRoll ID";
		public static final String ENTER_VALID_PAYROLLID = "Please Enter Valid Payroll Id";
		public static final String CONTACT_SUPPORT = "Please Contact Support Team";
		public static final String OTP_PASSWORD_SENT = "OTP PASSWORD SENT TO THE MOBILE NUMBER ";
		public static final String PASSWORD_NOT_VALID = "Confirmation Password is not Valid";
		public static final String ENTER_VALID_PASSWORD = "Please Enter valid Password";

	}

	public interface AppSalesMessages {
		public static final String ALREADY_ASSIGNED_HALL_NUMBER = "Already Assigned Hallticket Number ";
		public static final String HALL_NUMBER_DOESNT_EXIST = " Hallticket Number Does Not exist ";
		public static final String SEL_CAM_ADMINS_FILLED = "FEEDBACK-1-Selected Campus Admitions are filled,so please select an other campus";
		public static final String ENTER_VALIED_CONCESSION = "FEEDBACK-1-Sorry! does not accepted concession,Please enter valid concession.";
		public static final String PREPRINTED_RECEIPTNO_NOT_FOUND = "FEEDBACK-1-Pre Printed Receipt No. not found please enter...";
		public static final String FAILED_TO_SAVE_CONC = "Failed to save concession details.";
		public static final String ERROR_IN_STUDENT_CONFIRM = "Error in StudentConfirmaion";
		public static final String RECORD_INSERTED_SUCESSFULLY = "Record Inserted SucessFully&";
		public static final String RECORD_UPDATED_SUCESSFULLY = "Record Updated SucessFully&";
		public static final String APP_SOLDAT_OTHER_CAMPUS = " Sorry! This Application is sold at some other campus. You cannot update this application.";
		public static final String ERROR_IN_STUDENTCONFIRMAION = "Error in StudentConfirmaion";

	}

	public interface RoomAllotmentMessages {
		public static final String BED_ALREADY_ASSIGNED = "Bed Already Assigned";
	}

	public interface generatorMessages {
		public static final String DIESEL_DETAILS_INSERTED = "Generator Diesel Details Inserted Successfully";
		public static final String GENERATOR_READINGS_INSERTED = "Generator Readings Inserted Successfully";
		public static final String OFFDATE_EQUALTO_ONDATE = "off date must be equal to on date";
		public static final String ONTIME_GREATER_PREVIOUS_OFFTIME = "Present on time must be greater than previous off time";
		public static final String READING_DATE_GREATER_MAXIMUMDATE = "Generator Reading Details on date must be greater than maximum date entered in Generator Not Used";
		public static final String GENERATOR_NOTUSED_DATA_INSERTED = "Generator Not Used Data Inserted Successfully";
		public static final String GENERATOR_NOTUSED_DATE_GREATER_PREVIOUS = "Generator Not Used date must be greater than previous date";
	}

	public interface tickMessages {
		public static final String SUCCESFULLY_UPDATED_TICK_DETAILS = "successfully Updated Ticketing Details";
		public static final String Modified_Ticketing_Details = "successfully Modified Ticketing Details";
	}

	public interface studentMessages {
		public static final String ENTER_ENGLISH_MARKS = "FEEDBACK-1-Please Enter English Marks.";
		public static final String ENTER_HINDI_MARKS = "FEEDBACK-1-Please Enter Hindi Marks.";
		public static final String ENTER_MATHS_MARKS = "FEEDBACK-1-Please Enter Maths Marks.";
		public static final String ENTER_MARKS = "FEEDBACK-1-Please Enter  Marks.";
		public static final String STUDENT_DETAILS_ENTERING_FAILED = "FEEDBACK-1-Student In details entering is failed";
		public static final String FAILED_TOSAVE_CONCESSION = "FEEDBACK-1- Failed to save concession details.";
		public static final String NOT_ACCEPTED_CONCESSION = "FEEDBACK-1-Sorry! does not accepted concession,as the student due is 0.";
		public static final String NOT_ACCEPTED_EXCESS_CONCESSION = "FEEDBACK-1-Sorry! does not accepted concession,as the student paid excess amount.";

	}

	public interface messMessages {
		public static final String SAME_ITEMS_NOT_ALLOWED = "FEEDBACK-1-Same items are not allowed!!";
		public static final String INVALID_CONSUMPTION_QUANTITY = "FEEDBACK-1-Invalid consumption quantity...";
		public static final String SELECT_INVENTORY_TYPE = "FEEDBACK-1-Please select inventory type...";
		public static final String ITEM_SAVED_SUCCESSFULLY = "Item is saved successfully";
	}

	public interface EmployeeAuthentication {
		public static final String LOGIN = "login";
		public static final String UN_AUTHORIZED = "unauthorized";
		public static final String SELECT_INVENTORY_TYPE = "FEEDBACK-1-Please select inventory type...";
		public static final String ITEM_SAVED_SUCCESSFULLY = "Item is saved successfully";
	}

	public interface EmployeeWorkLoad {
		public static final Float MAXWORKLOAD = 9.0f;
	}

	public interface MobileLogin {
		public static final String LOGIN = "mobilelogin";
	}

	public interface FormatDates {
		public static final String DATEFORMAT = "dd/MM/yyyy";
		public static final String DATEFORMAT2 = "dd-MM-yyyy";
		public static final String DATEFORMAT3 = "dd MMM yyyy";
	}

	public interface TeachingStatus {
		public static final String TAKEN = "T";
		public static final String NOT_TAKEN = "NT";
		public static final String NOT_ALLOTED = "NA";
		public static final String REPLACE = "R";

	}

	public interface JSONEmployeeAppServiceErrors {

		public static final String NO_PAYROLL = "101";
		public static final String NO_PAYROLL_MSG = "Payroll not found";

		public static final String NO_PASSWORD = "102";
		public static final String NO_PASSWORD_MSG = "Password not found";

		public static final String DETAILS_VALID = "103";
		public static final String DETAILS_VALID_MSG = "Please enter any on of the above details";

		public static final String NAME_VALID = "104";
		public static final String NAME_VALID_MSG = "Student name and parent name are not found";

		public static final String CAMPUS_VALID = "105";
		public static final String CAMPUS_VALID_MSG = "Campus not found";

		public static final String CAMPUS_NOT_VALID = "106";
		public static final String CAMPUS_NOT_VALID_MSG = "Campus is not valid";

		public static final String NO_EMPLOYEE = "107";
		public static final String NO_EMPLOYEE_MSG = "No Employee with login id";

		public static final String EMPLOYEE_PERMISSION = "108";
		public static final String EMPLOYEE_PERMISSION_MSG = "Employee doesn't have permission to login";

		public static final String INVALID_DETAILS = "109";
		public static final String INVALID_DETAILS_MSG = "Please enter username and password correct";

		public static final String WRONG_PASSWORD = "110";
		public static final String WRONG_PASSWORD_MSG = "Wrong password";

		public static final String LEFT_EMPLOYEE = "111";
		public static final String LEFT_EMPLOYEE_MSG = "Wrong password";

		public static final String NO_CAMPUS = "112";
		public static final String NO_CAMPUS_MSG = "Employee does't have CampusId";

		public static final String EXCEPTION_VALID = "113";
		public static final String EXCEPTION_VALID_MSG = "Please contact sri chaitanya central office";

		public static final String LEAVE_REQUEST_SAVED = "114";
		public static final String LEAVE_REQUEST_SAVED_MSG = "Leave Request updated";

		public static final String VALID = "VALID";

	}

	public interface IncomingConstants {
		public static final String FEE = "FEE";
		public static final String CON = "CON";
		public static final String NET = "NET";
		public static final String ADDFEE = "ADDFEE";
		public static final String PAID = "PAID";
		public static final String DUE = "DUE";
		public static final String EXCESS = "EXCESS";

		public static final String TENTHHTNO = "10HTNO";
		public static final String INTERHTNO = "12HTNO";
		public static final String APEHTNO = "APEHTNO";
		public static final String TSEHTNO = "TSEHTNO";
		public static final String MARKS = "MARKS";
		public static final String FEEDET = "FEEDET";

		public static final String TSEAMCET = "TSEAMCET";
		public static final String APEAMCET = "APEAMCET";
		public static final String INTER2 = "INTER2";
		public static final String SSC = "SSC";
		public static final String INVALIDKEYWORD = "invalid keywords entered";

	}

	public interface MisListShowDTOConstants {

		public static final String column_1 = "column_1";
		public static final String column_2 = "column_2";
		public static final String column_3 = "column_3";
		public static final String column_4 = "column_4";
		public static final String column_5 = "column_5";
		public static final String column_6 = "column_6";
		public static final String column_7 = "column_7";
		public static final String column_8 = "column_8";
		public static final String column_9 = "column_9";
		public static final String column_10 = "column_10";
		public static final String column_11 = "column_11";
		public static final String column_12 = "column_12";
		public static final String column_13 = "column_13";
		public static final String column_14 = "column_14";
		public static final String column_15 = "column_15";
		public static final String column_16 = "column_16";
		public static final String column_17 = "column_17";
		public static final String column_18 = "column_18";
		public static final String column_19 = "column_19";
		public static final String column_20 = "column_20";

	}

	public interface GST {
		public static final String STATE = "STATE";
		public static final String CENTRAL = "CENTRAL";
	}

	public interface ConcessionConstants {
		public static final String REQUEST = "REQUEST";
		public static final String CONFIRM = "CONFIRM";
		public static final String REJECTED = "REJECTED";
		public static final String BUSPASS = "BUSPASS";

	}

	public interface EmloyeeLeaveConstants {
		public static final String REQUESTED = "REQUESTED";
		public static final String APPROVED = "APPROVED";
		public static final String REJECTED = "REJECTED";
		public static final String CANCELLED = "CANCELLED";

	}
	

}
