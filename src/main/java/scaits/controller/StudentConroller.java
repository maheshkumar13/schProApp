package scaits.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import scaits.bo.common.ExamAnalysisBO;
import scaits.bo.common.FinalYearlyBO;
import scaits.bo.common.GradeBO;
import scaits.bo.common.HalfYearlyBO;
import scaits.bo.common.PeriodicTestBO;
import scaits.bo.common.TestBO;
import scaits.bo.common.TestSubjectDetailsBO;
import scaits.bo.employee.EmployeeBO;
import scaits.bo.student.AcademicYearBO;
import scaits.bo.student.ClassSectionBO;
import scaits.bo.student.SchoolNumberBO;
import scaits.bo.student.StudentBO;
import scaits.bo.student.StudentBO.Student;
import scaits.bo.student.StudentTCDetailsBO;
import scaits.bo.student.StudyClassBO;
import scaits.bo.student.SubjectBO;
import scaits.bo.student.TransactionBO;
import scaits.dto.EmployeeDTO;
import scaits.dto.StudentDTO;
import scaits.dto.StudentTCDTO;
import scaits.dto.TestMarksUploadDTO;
import scaits.excel.util.ExcelUtil;
import scaits.excel.view.ExcelView;
import scaits.repository.AcademicRepository;
import scaits.repository.CastRepository;
import scaits.repository.ClassSectionRepository;
import scaits.repository.EmployeeRepository;
import scaits.repository.ExamAnalysisRepository;
import scaits.repository.FinalYearlyRepository;
import scaits.repository.GradeRepository;
import scaits.repository.HalfYearlyRepository;
import scaits.repository.HeadNameRepository;
import scaits.repository.PeriodicTestRepository;
import scaits.repository.SchoolNumbersRepository;
import scaits.repository.StudentRepository;
import scaits.repository.StudentTCRepository;
import scaits.repository.StudyClassRepository;
import scaits.repository.SubjectRepository;
import scaits.repository.TestRepository;
import scaits.repository.TestSubjectDetailsRepository;
import scaits.repository.TestSubjectRepository;
import scaits.repository.TransactionRepository;
import scaits.util.BulkImporterService;
import scaits.util.DU;
import scaits.util.EmployeeAction;
import scaits.util.ExcelAndPdfUtil;
import scaits.util.NumberToWordsConverter;
import scaits.util.StringUtil;
import scaits.util.TransConstants;

@RestController
@Scope(value = "request")
@Transactional(value = "mySqlTransactionManager", rollbackFor = { Exception.class })
public class StudentConroller {

	@Autowired
	private EmployeeAction employeeAction;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	AcademicRepository academicRepository;

	@Autowired
	StudyClassRepository studyClassRepository;

	@Autowired
	ClassSectionRepository classSectionRepository;

	@Autowired
	CastRepository castRepository;

	@Autowired
	SchoolNumbersRepository schoolNumbersRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	HeadNameRepository headNameRepository;

	@Autowired
	TestRepository testRepository;

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	ExamAnalysisRepository examAnalysisRepository;

	@Autowired
	PeriodicTestRepository periodicTestRepository;

	@Autowired
	TestSubjectRepository testSubjectRepository;

	@Autowired
	BulkImporterService bulkImporterService;

	@Autowired
	GradeRepository gradeRepository;

	@Autowired
	HalfYearlyRepository halfYearlyRepository;

	@Autowired
	FinalYearlyRepository finalYearlyRepository;

	@Autowired
	ExcelAndPdfUtil excelAndPdfUtil;

	@Autowired
	StudentTCRepository studentTCRepository;

	@Autowired
	TestSubjectDetailsRepository testSubjectDetailsRepository;

	@Autowired
	private Environment env;

	public static final String uploadingDir = System.getProperty("user.dir") + "/uploadingDir/";

	@GetMapping("/student/studentInformation")
	public ModelAndView lockpage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.setViewName("myTheme1/student/student-details");

		return mv;
	}

	@GetMapping(value = "/student/searchStudent")
	public ModelAndView search(@RequestParam("academic") String academic, @RequestParam("appNo") String admNo,
			@RequestParam("mobileNo") String mobileNo, @RequestParam("studentName") String studentName,
			@RequestParam("parentName") String parentName) {

		ModelAndView mv = new ModelAndView();
		List<StudentBO> stuList = null;
		AcademicYearBO acaVal = academicRepository.findById(Long.valueOf(academic)).get();

		if (admNo != "" && mobileNo == "" && studentName == "" && parentName == "") {
			stuList = studentRepository.findByAcademicIdAndStudentNoContaining(acaVal, admNo);
		} else if (admNo == "" && mobileNo != "" && studentName == "" && parentName == "") {
			stuList = studentRepository.findByAcademicIdAndMobileNoContaining(acaVal, mobileNo);
		} else if (admNo == "" && mobileNo == "" && studentName != "" && parentName == "") {
			stuList = studentRepository.findByAcademicIdAndNameContaining(acaVal, studentName);
		} else if (admNo == "" && mobileNo == "" && studentName == "" && parentName != "") {
			stuList = studentRepository.findByAcademicIdAndFatherNameContaining(acaVal, parentName);
		}

		mv.addObject("stuList", stuList);
		mv.setViewName("myTheme1/appSales/applicationSalesGrid");

		return mv;

	}

	@GetMapping(value = "/student/getStudentInfo")
	public ModelAndView getStudentInfo(@RequestParam("studentNo") StudentBO studentNo) {

		ModelAndView mv = new ModelAndView();

		mv.addObject("student", studentNo);
		mv.addObject("academicList", academicRepository.findByReceiptStatus("Y"));
		mv.addObject("studyClassList", studyClassRepository.findAll());
		mv.addObject("classSectionList", classSectionRepository.findByClassId(studentNo.getClassId()));
		mv.addObject("castList", castRepository.findAll());
		mv.addObject("headNameList", headNameRepository.findByHeadName("FEE"));

		TransactionBO appFeeTrans = transactionRepository.findByStudentAndHeadCode(studentNo,
				TransConstants.DaySheetSummaryHeadCodes.APP_FEE);
		TransactionBO headNameTrans = null;
		List<TransactionBO> headNameTransList = transactionRepository.findByStudentAndHeadCodeNotIn(studentNo,
				TransConstants.DaySheetSummaryHeadCodes.APP_FEE);
		if (headNameTransList != null && headNameTransList.size() > 0) {
			headNameTrans = headNameTransList.get(0);
		}

		mv.addObject("appFeeTrans", appFeeTrans);
		mv.addObject("headNameTrans", headNameTrans);

		mv.setViewName("myTheme1/appSales/appSalesInformation");

		return mv;

	}

	@GetMapping(value = "/student/getStuInformation")
	public ModelAndView getStuInformation(@RequestParam("studentNo") StudentBO studentNo) {

		ModelAndView mv = new ModelAndView();

		mv.addObject("student", studentNo);
		mv.addObject("academicList", academicRepository.findAll());
		mv.addObject("studyClassList", studyClassRepository.findAll());
		mv.addObject("classSectionList", classSectionRepository.findByClassId(studentNo.getClassId()));
		mv.addObject("castList", castRepository.findAll());
		mv.addObject("headNameList", headNameRepository.findByHeadName("FEE"));

		TransactionBO appFeeTrans = transactionRepository.findByStudentAndHeadCode(studentNo,
				TransConstants.DaySheetSummaryHeadCodes.APP_FEE);
		TransactionBO headNameTrans = null;
		List<TransactionBO> headNameTransList = transactionRepository.findByStudentAndHeadCodeNotIn(studentNo,
				TransConstants.DaySheetSummaryHeadCodes.APP_FEE);
		if (headNameTransList != null && headNameTransList.size() > 0) {
			headNameTrans = headNameTransList.get(0);
		}

		mv.addObject("appFeeTrans", appFeeTrans);
		mv.addObject("headNameTrans", headNameTrans);
		headNameTransList.add(appFeeTrans);
		mv.addObject("headNameTransList", headNameTransList);

		mv.setViewName("myTheme1/student/studentDetails");

		return mv;

	}

	@GetMapping(value = "/student/saveStuPayment")
	public ModelAndView saveStuPayment(@RequestParam("student") StudentBO student,
			@RequestParam("headName") String headName, @RequestParam("amount") float amount,
			@RequestParam("headFeeDate") String headFeeDate, @RequestParam("payMode") String payMode) {

		ModelAndView mv = new ModelAndView();

		mv.addObject("student", student);

		if (headName != null) {
			TransactionBO regFeeTrans = new TransactionBO(amount, student, employeeAction.getCurrentUser(),
					TransConstants.DaySheetSummaryHeadCodes.FEE, headName, payMode);
			regFeeTrans.setPayDate(DU.parse(headFeeDate, "dd/mm/yyyy"));
			regFeeTrans.setAcademic(student.getAcademicId());
			SchoolNumberBO schNumVal = schoolNumbersRepository.findByAcademicId(student.getAcademicId());
			if (schNumVal != null) {
				long autoGenNo = schNumVal.getAutoGenreceiptNo() + 1;
				regFeeTrans.setManualReceipt(autoGenNo);
				regFeeTrans.setInstallmentNo(1);
				regFeeTrans.setAmountInWords(NumberToWordsConverter.convertNumber(String.valueOf(amount)));
				schNumVal.setAutoGenreceiptNo(autoGenNo);
				schoolNumbersRepository.save(schNumVal);
			}
			transactionRepository.save(regFeeTrans);
		}

		List<String> headList = new ArrayList<>();
		headList.add(TransConstants.DaySheetSummaryHeadCodes.APP_FEE);
		headList.add(TransConstants.DaySheetSummaryHeadCodes.FEE);

		List<TransactionBO> headNameTransList = transactionRepository.findByStudentAndHeadCodeIn(student, headList);
		mv.addObject("headNameTransList", headNameTransList);
		mv.setViewName("myTheme1/student/stuPaymentsShowGrid");

		return mv;

	}

	@GetMapping(value = "/student/addStudentInfo")
	public ModelAndView addStudentInfo() {

		ModelAndView mv = new ModelAndView();

		mv.addObject("academicList", academicRepository.findAll());
		mv.addObject("studyClassList", studyClassRepository.findAll());
		mv.addObject("classSectionList", classSectionRepository.findAll());
		mv.addObject("castList", castRepository.findAll());
		mv.addObject("student", null);
		mv.addObject("headNameList", headNameRepository.findByHeadName("FEE"));

		mv.addObject("appFeeTrans", null);
		mv.addObject("headNameTrans", null);

		mv.setViewName("myTheme1/appSales/appSalesInformation");

		return mv;

	}

	@GetMapping(value = "/student/deleteStudent")
	public @ResponseBody String deleteStudent(@RequestParam("studentNo") StudentBO studentNo) {

		if (studentNo != null) {
			studentRepository.delete(studentNo);
			return new Gson().toJson("Deleted SuccessFully");
		}

		return new Gson().toJson("Contact Support Team");

	}

	@GetMapping(value = "/student/getClassWiseSections")
	public @ResponseBody HashMap<String, String> getClassWiseSections(
			@RequestParam("studyClass") StudyClassBO studyClass,
			@RequestParam(value = "academic", required = false) AcademicYearBO academic) {

		List<ClassSectionBO> classSectionList = new ArrayList<ClassSectionBO>();
		classSectionList = classSectionRepository.findByClassId(studyClass);

		HashMap<String, String> classSecList = new HashMap<String, String>();

		classSecList.put("0", "select Section");

		for (ClassSectionBO str : classSectionList) {

			classSecList.put(String.valueOf(str.getId()), str.getSectionName());
		}

		return classSecList;
	}

	@ResponseBody
	@RequestMapping(value = "/student/autocompleteStudent", method = RequestMethod.GET, produces = "application/json")
	public String autocompleteSuggestions(@RequestParam("term") String term) throws JSONException {

		List<StudentBO> stuList = new ArrayList<>();
		if (term.chars().allMatch(Character::isDigit)) {
			stuList = studentRepository.findByStudentNoContaining(term);
		} else {
			stuList = studentRepository.findByNameContaining(term.trim().toUpperCase());
		}

		JSONArray array = new JSONArray();
		for (StudentBO stVal : stuList) {
			JSONObject item = new JSONObject();
			item.put("key", String.valueOf(stVal.getStudentNo()));
			item.put("value", String.valueOf(stVal.getStudentNo()) + " " + stVal.getName() + "  " + stVal.getMobileNo()
					+ " " + stVal.getSchoolName());
			array.put(item);
		}

		return array.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/employee/autocompleteEmployee", method = RequestMethod.GET, produces = "application/json")
	public String autocompleteEmployeeSuggestions(@RequestParam("term") String term) throws JSONException {

		List<EmployeeBO> empList = new ArrayList<>();
		if (term.chars().allMatch(Character::isDigit)) {
			empList = employeeRepository.findByPayRollIdContaining(term);
		} else {
			empList = employeeRepository.findByUserNameContaining(term);
		}

		JSONArray array = new JSONArray();
		for (EmployeeBO empVal : empList) {
			JSONObject item = new JSONObject();
			item.put("key", String.valueOf(empVal.getEmployeeId()));
			item.put("value", String.valueOf(empVal.getPayRollId()) + " " + empVal.getUserName() + "  "
					+ empVal.getMobileNo() + " " + empVal.getSchoolName());
			array.put(item);
		}

		return array.toString();
	}

	@RequestMapping(value = "/student/addNewStudent", method = RequestMethod.POST)
	public String addNewStudent(@ModelAttribute StudentDTO entry) throws Exception {

		try {
			StudentBO stuVal = null;
			if (entry.getStudentNo() == "" || entry.getStudentNo().length() == 0) {
				stuVal = null;
			} else {
				stuVal = studentRepository.findById(entry.getStudentNo()).get();
			}
			Long maxNumber = 0l;

			boolean isNewStu = false;
			if (stuVal == null) {
				stuVal = new StudentBO();
				stuVal.setCreatedBy(employeeAction.getCurrentUser());
				stuVal.setCreatedByName(employeeAction.getCurrentUser().getName());
				stuVal.setCreatedOn(new Date());

				List<StudentBO> stuList = studentRepository.findByAcademicId(entry.getAcademicYear());

				if (stuList != null && stuList.size() > 0) {
					List<Long> stuLongList = stuList.stream().map(a -> Long.valueOf(a.getStudentNo()))
							.collect(Collectors.toList());
					maxNumber = stuLongList.stream().max(Comparator.comparing(Long::valueOf)).get();
					stuVal.setStudentNo(String.valueOf(maxNumber + 1));
				} else {
					stuVal.setStudentNo(String.valueOf(
							schoolNumbersRepository.findByAcademicId(entry.getAcademicYear()).getAppStartNo()));
				}
				stuVal.setStudent(Student.CURRENT);
				isNewStu = true;
			} else {
				stuVal.setModifiedBy(employeeAction.getCurrentUser());
				stuVal.setModifiedOn(new Date());
			}
			stuVal.setAadhaar(entry.getAadhaar());
			stuVal.setAcademicId(entry.getAcademicYear());
			stuVal.setAcademicYear(entry.getAcademicYear() != null ? stuVal.getAcademicYear() : "");
			stuVal.setCast(entry.getCastVal());
			stuVal.setClassId(entry.getStudyClass());

			stuVal.setDob(entry.getDob());
			stuVal.setDoj(new Date());
			stuVal.setEmail(entry.getEmail());
			stuVal.setGender(entry.getGender());
			stuVal.setBsf(entry.getBsf());
			stuVal.setMobileNo(entry.getMobileNo());
			stuVal.setMobileNo2(entry.getMobileNo2());
			stuVal.setName(entry.getStuName());
			stuVal.setFatherName(entry.getFatherName());
			stuVal.setMotherName(entry.getMotherName());
			stuVal.setSchoolName(entry.getSchoolName());
			stuVal.setSectionId(entry.getSectionVal());
			stuVal.setSurName(entry.getSurName());
			stuVal.setpOccupation(entry.getpOccupation());
			stuVal.setLocalGuardian(entry.getLocalGuardian());
			stuVal.setrFee(entry.getrFee());
			stuVal.setPerAddress(entry.getPerAddress());
			stuVal.setpAddress(entry.getpAddress());
			stuVal.setPreSchoolName(entry.getPreSchoolName());

			stuVal.setPreClassAttended(entry.getPreClassAttended());
			stuVal.setBloodGroup(entry.getBloodGroup());
			stuVal.setNationality(entry.getNationality());
			stuVal.setSubCast(entry.getSubCast());

			if (entry.getReligion() != null && entry.getReligion().equalsIgnoreCase("OTHERS")) {

				stuVal.setReligion("OTHERS " + entry.getOtherReligion().toUpperCase());

			} else {
				stuVal.setReligion(entry.getReligion());
			}

			studentRepository.save(stuVal);

			if (entry.getStuImgFile().getBytes().length > 0) {
				byte[] bytes = entry.getStuImgFile().getBytes();

				int idx = entry.getStuImgFile().getOriginalFilename().lastIndexOf(".");
				String extName = idx >= 0 ? entry.getStuImgFile().getOriginalFilename().substring(idx + 1)
						: entry.getStuImgFile().getOriginalFilename();

				File directory = new File(env.getProperty("stu.image.folder"));

				if (directory.exists()) {

					Path path = Paths.get(env.getProperty("stu.image.folder") + stuVal.getStudentNo() + "." + extName);
					Files.write(path, bytes);
				} else {
					directory.mkdirs();

					Path path = Paths.get(env.getProperty("stu.image.folder") + stuVal.getStudentNo() + "." + extName);
					Files.write(path, bytes);
				}

			}

			if (entry.getBirthCertFile().getBytes().length > 0) {
				byte[] bytes = entry.getBirthCertFile().getBytes();

				int idx = entry.getStuImgFile().getOriginalFilename().lastIndexOf(".");
				String extName = idx >= 0 ? entry.getStuImgFile().getOriginalFilename().substring(idx + 1)
						: entry.getStuImgFile().getOriginalFilename();

				File directory = new File(env.getProperty("stuBirth.image.folder"));

				if (directory.exists()) {

					Path path = Paths
							.get(env.getProperty("stuBirth.image.folder") + stuVal.getStudentNo() + "." + extName);
					Files.write(path, bytes);
				} else {
					directory.mkdirs();

					Path path = Paths
							.get(env.getProperty("stuBirth.image.folder") + stuVal.getStudentNo() + "." + extName);
					Files.write(path, bytes);
				}

			}

			if (isNewStu) {
				if (entry.getrFee() != null && entry.getrFee() > 0) {
					TransactionBO regFeeTrans = new TransactionBO(entry.getrFee(), stuVal,
							employeeAction.getCurrentUser(), TransConstants.DaySheetSummaryHeadCodes.APP_FEE,
							TransConstants.DaySheetSummaryHeadCodes.APP_FEE, entry.getRegFeePayMode());
					regFeeTrans.setPayDate(entry.getRegFeeDate());
					regFeeTrans.setAcademic(stuVal.getAcademicId());
					SchoolNumberBO schNumVal = schoolNumbersRepository.findByAcademicId(stuVal.getAcademicId());
					if (schNumVal != null) {
						long autoGenNo = schNumVal.getAutoGenreceiptNo() + 1;
						regFeeTrans.setManualReceipt(autoGenNo);
						regFeeTrans.setInstallmentNo(1);
						regFeeTrans.setAmountInWords(
								NumberToWordsConverter.convertNumber(String.valueOf(entry.getrFee())));
						schNumVal.setAutoGenreceiptNo(autoGenNo);
						schoolNumbersRepository.save(schNumVal);
					}
					transactionRepository.save(regFeeTrans);
				}

				if (entry.getHeadName() != null) {
					TransactionBO regFeeTrans = new TransactionBO(entry.getHeadWiseFee(), stuVal,
							employeeAction.getCurrentUser(), entry.getHeadName().getHeadName(),
							entry.getHeadName().getSubHeadName(), entry.getHeadNamePayMode());
					regFeeTrans.setPayDate(entry.getHeadFeeDate());
					regFeeTrans.setAcademic(stuVal.getAcademicId());
					SchoolNumberBO schNumVal = schoolNumbersRepository.findByAcademicId(stuVal.getAcademicId());
					if (schNumVal != null) {
						long autoGenNo = schNumVal.getAutoGenreceiptNo() + 1;
						regFeeTrans.setManualReceipt(autoGenNo);
						regFeeTrans.setInstallmentNo(1);
						regFeeTrans.setAmountInWords(
								NumberToWordsConverter.convertNumber(String.valueOf(entry.getHeadWiseFee())));
						schNumVal.setAutoGenreceiptNo(autoGenNo);
						schoolNumbersRepository.save(schNumVal);
					}
					transactionRepository.save(regFeeTrans);
				}

			}
			if (isNewStu) {
				return new Gson().toJson(String.valueOf(maxNumber + 1));
			} else {
				return new Gson().toJson("FEEDBACK-1-Saved Successfully");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson("ERROR-1-" + e.getMessage());
		}

	}

	@RequestMapping(value = "/employee/addOrUpdateEmployee", method = RequestMethod.POST)
	public String addOrUpdateEmployee(@ModelAttribute EmployeeDTO entry) throws Exception {

		try {
			EmployeeBO empVal = employeeRepository.findByAadharNo(entry.getAadharNo());
			boolean isNewEmp = false;
			if (empVal == null) {
				empVal = new EmployeeBO();
				empVal.setCreatedBy(employeeAction.getCurrentUser().getEmployeeId());
				empVal.setCreatedOn(new Date());

				empVal.setStatus("CURRENT");

				List<SchoolNumberBO> schollNoList = schoolNumbersRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
				SchoolNumberBO schNum = schollNoList.get(0);
				Long maxNumber = schNum.getAutoEmpId();
				empVal.setPayRollId("SCH".concat(String.valueOf(maxNumber + 1)));

				empVal.setDryPassWord(entry.getPassWord());
				empVal.setPassWord(StringUtil.getMD5Code(entry.getPassWord()));
				schNum.setAutoEmpId(maxNumber + 1);
				schoolNumbersRepository.save(schNum);
				isNewEmp = true;
			} else {
				empVal.setUpdatedBy(employeeAction.getCurrentUser().getEmployeeId());
				empVal.setUpdatedOn(new Date());
			}
			empVal.setSurName(entry.getSurName());
			empVal.setUserName(entry.getUserName());
			empVal.setDob(entry.getDob());
			empVal.setMobileNo(entry.getMobileNo());
			empVal.setGender(entry.getGender());
			empVal.setExperience(entry.getExperience());

			// image

			if (entry.getEmpImgFile().getBytes().length > 0) {
				byte[] bytes = entry.getEmpImgFile().getBytes();

				int idx = entry.getEmpImgFile().getOriginalFilename().lastIndexOf(".");
				String extName = idx >= 0 ? entry.getEmpImgFile().getOriginalFilename().substring(idx + 1)
						: entry.getEmpImgFile().getOriginalFilename();

				File directory = new File(env.getProperty("employee.image.folder"));

				if (directory.exists()) {

					Path path = Paths
							.get(env.getProperty("employee.image.folder") + empVal.getPayRollId() + "." + extName);
					Files.write(path, bytes);
				} else {
					directory.mkdirs();

					Path path = Paths
							.get(env.getProperty("employee.image.folder") + empVal.getPayRollId() + "." + extName);
					Files.write(path, bytes);
				}

			}

			empVal.setAadharNo(entry.getAadharNo());

			empVal.setSubject(entry.getSubject());
			empVal.setDoj(entry.getDoj());
			empVal.setSchoolName(entry.getSchoolName());
			empVal.setPreSchoolName(entry.getPreSchoolName());
			empVal.setDesignation(entry.getDesignation());
			empVal.setManagerId(entry.getEmpManager().getEmployeeId());
			empVal.setUserRole(22l);
			empVal.setIsValid(0);

			employeeRepository.save(empVal);

			return new Gson().toJson(empVal.getPayRollId());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson("ERROR-1-" + e.getMessage());
		}

	}

	@RequestMapping(value = "/student/printReceipt", method = RequestMethod.GET)
	public ModelAndView getAppPaymentPrint(@RequestParam("admNo") String admNo) throws Exception {

		ModelAndView mv = new ModelAndView();

		StudentBO stuVal = studentRepository.findByStudentNo(admNo.replaceAll("\"", ""));

		List<TransactionBO> traList = transactionRepository.findByStudent(stuVal);

		mv.addObject("traList", traList);

		mv.setViewName("myTheme1/appSales/applicationSalesPrint");
		return mv;

	}

	@RequestMapping(value = "/image/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

		File serverFile = new File(env.getProperty("stu.image.folder") + imageName + ".jpg");
		if (!serverFile.exists()) {
			serverFile = new File(env.getProperty("stu.image.folder") + imageName + ".jpeg");
		}
		if (!serverFile.exists()) {
			serverFile = new File(env.getProperty("stu.image.folder") + imageName + ".png");
		}

		return Files.readAllBytes(serverFile.toPath());
	}
	
	@RequestMapping(value = "/stuBirthImage/{stuBirthImage}")
	@ResponseBody
	public  ResponseEntity<byte[]> getStuBirthImage(@PathVariable(value = "stuBirthImage") String imageName,HttpServletResponse response) throws IOException {
		File serverFile = new File(env.getProperty("stuBirth.image.folder") + imageName + ".jpg");
		if (serverFile.exists()) {
			serverFile = new File(env.getProperty("stuBirth.image.folder") + imageName + ".jpeg");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(Files.readAllBytes(serverFile.toPath()));
		}
		if (!serverFile.exists()) {
			serverFile = new File(env.getProperty("stuBirth.image.folder") + imageName + ".png");
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(Files.readAllBytes(serverFile.toPath()));
		}
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(Files.readAllBytes(serverFile.toPath()));

		
		
		
	}
	
	

	@RequestMapping(value = "/empImage/{imageName}")
	@ResponseBody
	public byte[] getEmpImage(@PathVariable(value = "imageName") String imageName) throws IOException {

		File serverFile = new File(env.getProperty("employee.image.folder") + imageName + ".jpg");
		if (!serverFile.exists()) {
			serverFile = new File(env.getProperty("employee.image.folder") + imageName + ".jpeg");
		}
		if (!serverFile.exists()) {
			serverFile = new File(env.getProperty("employee.image.folder") + imageName + ".png");
		}

		return Files.readAllBytes(serverFile.toPath());
	}

	// student test marks upload controller

	@GetMapping(value = "/student/showUploadTestMarks")
	public ModelAndView showUploadTestMarks() {

		ModelAndView mv = new ModelAndView();

		mv.addObject("testList", testRepository.findByStatus(true));
		mv.addObject("classList", studyClassRepository.findAll());

		mv.setViewName("myTheme1/marksUpload/testMarksUpload");

		return mv;

	}

	@GetMapping(value = "/student/showRankGeneration")
	public ModelAndView showRankGeneration() {

		ModelAndView mv = new ModelAndView();

		mv.addObject("testList", testRepository.findByStatus(true));
		mv.addObject("classList", studyClassRepository.findAll());

		mv.setViewName("myTheme1/marksUpload/rankGeneration");

		return mv;

	}

	@GetMapping(value = "/student/showPeriodicRankGeneration")
	public ModelAndView showPeriodicRankGeneration(@RequestParam String testTypeVal,
			@RequestParam StudyClassBO studyClass) {

		ModelAndView mv = new ModelAndView();

		mv.addObject("testList", testRepository.findByStatusAndName(true, testTypeVal));
		mv.addObject("classList", studyClassRepository.findAll());
		mv.addObject("classSectionList", classSectionRepository.findAll());

		mv.setViewName("myTheme1/marksUpload/periodicRankGeneration");

		return mv;

	}

	@GetMapping(value = "/student/showPeriodicMarksUpload")
	public ModelAndView showPeriodicMarksUpload() {

		ModelAndView mv = new ModelAndView();

		mv.addObject("testList", testRepository.findByStatus(true));
		mv.addObject("classList", studyClassRepository.findAll());
		mv.addObject("subList", subjectRepository.findByStatus(true));

		mv.setViewName("myTheme1/marksUpload/periodicMarksUpload");

		return mv;

	}

	@ResponseBody
	@RequestMapping(value = "/student/uploadTestMarksUpload", method = RequestMethod.POST)
	public String uploadTestMarksUpload(@ModelAttribute TestMarksUploadDTO testMarksUpload) throws Exception {
		try {
			if (testMarksUpload.getUploadFile() != null) {

				if (testMarksUpload.getTestVal().getName().contains("Periodic")) {

					InputStream inputStream = testMarksUpload.getUploadFile().getInputStream();
					List<Map<Integer, List<Object>>> sheets = ExcelUtil.readAllExcelSheetsData(inputStream);
					List<Object> colNames = new ArrayList<>();
					for (int i = 0; i < sheets.size(); i++) {
						Map<Integer, List<Object>> sheet = sheets.get(i);
						Set<Entry<Integer, List<Object>>> s = sheet.entrySet();
						Iterator<Entry<Integer, List<Object>>> itr = s.iterator();

						while (itr.hasNext()) {
							Entry<Integer, List<Object>> entry = itr.next();
							System.out
									.println("Sheet " + (i + 1) + ", Row " + entry.getKey() + " : " + entry.getValue());
							if (entry.getKey() == 1) {
								colNames = entry.getValue();
							} else {
								PeriodicTestBO examVal = null;
								int k = 0;
								Long total = 0l;
								for (Object val1 : entry.getValue()) {
									if (k == 0) {
										StudentBO stuval = studentRepository.findByStudentNo(val1.toString());
										examVal = periodicTestRepository.findByStudentAndClassIdAndTest(stuval,
												stuval.getClassId(), testMarksUpload.getTestVal());
										if (examVal == null) {
											examVal = new PeriodicTestBO();
											examVal.setTest(testMarksUpload.getTestVal());
											examVal.setTestDate(testMarksUpload.getTestDate());
											examVal.setStudent(stuval);
											examVal.setClassId(testMarksUpload.getStudyClass());
											if (stuval.getClassId().getId() != testMarksUpload.getStudyClass()
													.getId()) {
												return new Gson().toJson(
														"Student AdmNo " + stuval.getStudentNo() + " Class Not Valid");
											}

											examVal.setTotalScored(0l);
										}

									} else if (k == 2) {
										if (colNames.get(k) != null
												&& colNames.get(k).toString().toUpperCase().contains("MATHEMATICS")) {

											if (val1.toString() != null && val1.toString().length() > 0
													&& isNumeric(val1.toString())) {
												if (Long.valueOf(val1.toString()) > 40) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For Maths "
															+ entry.getValue().get(0));
												}
												examVal.setMaths(Long.valueOf(val1.toString()));
												total = Long.valueOf(val1.toString()) + total;
											} else {
												examVal.setMaths(0l);
												examVal.setStatus("AB");
												;
											}

										} else if (colNames.get(k) != null
												&& colNames.get(k).toString().toUpperCase().contains("PHYSICS")) {

											if (val1.toString() != null && val1.toString().length() > 0
													&& isNumeric(val1.toString())) {
												if (Long.valueOf(val1.toString()) > 40) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For PHYSICS "
																	+ entry.getValue().get(0));
												}
												examVal.setPhysics(Long.valueOf(val1.toString()));
												total = Long.valueOf(val1.toString()) + total;
											} else {
												examVal.setPhysics(0l);
												examVal.setStatus("AB");
												;
											}

										} else if (colNames.get(k) != null
												&& colNames.get(k).toString().toUpperCase().contains("CHEMISTRY")) {
											if (val1.toString() != null && val1.toString().length() > 0
													&& isNumeric(val1.toString())) {
												if (Long.valueOf(val1.toString()) > 40) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For CHEMISTRY "
																	+ entry.getValue().get(0));
												}
												examVal.setChemistry(Long.valueOf(val1.toString()));
												total = Long.valueOf(val1.toString()) + total;
											} else {
												examVal.setChemistry(0l);
												examVal.setStatus("AB");
												;
											}

										} else if (colNames.get(k) != null
												&& colNames.get(k).toString().toUpperCase().contains("ENGLISH")) {

											if (val1.toString() != null && val1.toString().length() > 0
													&& isNumeric(val1.toString())) {

												if (Long.valueOf(val1.toString()) > 40) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For ENGLISH "
																	+ entry.getValue().get(0));
												}
												examVal.setEnglish(Long.valueOf(val1.toString()));
												total = Long.valueOf(val1.toString()) + total;
											} else {
												examVal.setEnglish(0l);
												examVal.setStatus("AB");
												;
											}

										} else if (colNames.get(k) != null
												&& colNames.get(k).toString().toUpperCase().contains("HINDI")) {
											if (val1.toString() != null && val1.toString().length() > 0
													&& isNumeric(val1.toString())) {
												if (Long.valueOf(val1.toString()) > 40) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For HINDI "
															+ entry.getValue().get(0));
												}
												examVal.setHindi(Long.valueOf(val1.toString()));
												total = Long.valueOf(val1.toString()) + total;
											} else {
												examVal.setHindi(0l);
												examVal.setStatus("AB");
												;
											}

										} else if (colNames.get(k) != null
												&& (colNames.get(k).toString().toUpperCase().contains("E.V.S")
														|| colNames.get(k).toString().toUpperCase().contains("EVS"))) {

											if (val1.toString() != null && val1.toString().length() > 0
													&& isNumeric(val1.toString())) {

												if (Long.valueOf(val1.toString()) > 40) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For EVS "
															+ entry.getValue().get(0));
												}
												examVal.setEvc(Long.valueOf(val1.toString()));
												total = Long.valueOf(val1.toString()) + total;
											} else {
												examVal.setEvc(0l);
												examVal.setStatus("AB");
												;
											}

										} else if (colNames.get(k) != null && colNames.get(k).toString().toUpperCase()
												.contains("COMPUTERS_THEORY")) {

											if (val1.toString() != null && val1.toString().length() > 0
													&& isNumeric(val1.toString())) {
												if (Long.valueOf(val1.toString()) > 40) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For COMPUTERS "
																	+ entry.getValue().get(0));
												}
												examVal.setComputersThery(Long.valueOf(val1.toString()));
												total = Long.valueOf(val1.toString()) + total;
											}

										}
									} else if (colNames.size()>3 && k == 3 && colNames.get(k) != null && colNames.get(k).toString()
											.toUpperCase().contains("COMPUTERS_PRACTICAL")) {

										if (val1.toString() != null && val1.toString().length() > 0
												&& isNumeric(val1.toString())) {
											if (Long.valueOf(val1.toString()) > 11) {
												return new Gson().toJson(
														"FEEDBACK-1-Wrong Marks Entered For COMPUTERS PRACTICAL "
																+ entry.getValue().get(0));
											}
											examVal.setComputersPractical(Long.valueOf(val1.toString()));
											total = Long.valueOf(val1.toString()) + total;
											examVal.setComputersTotal(
													examVal.getComputersThery() + Long.valueOf(val1.toString()));
										} else {
											examVal.setComputersTotal(0l);
											examVal.setStatus("AB");
										}

									}
									k++;

								}

								if (examVal.getTotalScored() != null) {
									examVal.setTotalScored(examVal.getTotalScored() + total);
								} else {
									examVal.setTotalScored(total);
								}

								periodicTestRepository.save(examVal);

							}

						}
					}
					List<PeriodicTestBO> examAnaList = periodicTestRepository
							.findByClassIdAndTest(testMarksUpload.getStudyClass(), testMarksUpload.getTestVal());

					float[] score1 = { Integer.MIN_VALUE };
					int[] no1 = { 0 };
					int[] rank1 = { 0 };
					List<PeriodicTestBO> mathList = examAnaList.stream()
							.filter(a -> a.getMaths() != null && a.getMaths() >= 0)
							.sorted(Comparator.comparing(PeriodicTestBO::getMaths).reversed()).map(p -> {
								++no1[0];
								if (score1[0] != p.getMaths())
									rank1[0] = no1[0];
								score1[0] = p.getMaths();
								p.setMathsRank((long) rank1[0]);
								return p;
							}).collect(Collectors.toList());

					for (PeriodicTestBO periodicTestBO : mathList) {
						periodicTestRepository.save(periodicTestBO);
					}

					float[] score2 = { Integer.MIN_VALUE };
					int[] no2 = { 0 };
					int[] rank2 = { 0 };
					List<PeriodicTestBO> phyList = examAnaList.stream()
							.filter(a -> a.getPhysics() != null && a.getPhysics() >= 0)
							.sorted(Comparator.comparing(PeriodicTestBO::getPhysics).reversed()).map(p -> {
								++no2[0];
								if (score2[0] != p.getPhysics())
									rank2[0] = no2[0];
								score2[0] = p.getPhysics();
								p.setPhysicsRank((long) rank2[0]);
								return p;
							}).collect(Collectors.toList());

					for (PeriodicTestBO periodicTestBO : phyList) {
						periodicTestRepository.save(periodicTestBO);
					}

					float[] score3 = { Integer.MIN_VALUE };
					int[] no3 = { 0 };
					int[] rank3 = { 0 };
					List<PeriodicTestBO> cheList = examAnaList.stream()
							.filter(a -> a.getChemistry() != null && a.getChemistry() >= 0)
							.sorted(Comparator.comparing(PeriodicTestBO::getChemistry).reversed()).map(p -> {
								++no3[0];
								if (score3[0] != p.getChemistry())
									rank3[0] = no3[0];
								score3[0] = p.getChemistry();
								p.setChemistryRank((long) rank3[0]);
								return p;
							}).collect(Collectors.toList());

					for (PeriodicTestBO periodicTestBO : cheList) {
						periodicTestRepository.save(periodicTestBO);
					}

					float[] score4 = { Integer.MIN_VALUE };
					int[] no4 = { 0 };
					int[] rank4 = { 0 };
					List<PeriodicTestBO> engList = examAnaList.stream()
							.filter(a -> a.getEnglish() != null && a.getEnglish() >= 0)
							.sorted(Comparator.comparing(PeriodicTestBO::getEnglish).reversed()).map(p -> {
								++no4[0];
								if (score4[0] != p.getEnglish())
									rank4[0] = no4[0];
								score4[0] = p.getEnglish();
								p.setEnglishRank((long) rank4[0]);
								return p;
							}).collect(Collectors.toList());

					for (PeriodicTestBO periodicTestBO : engList) {
						periodicTestRepository.save(periodicTestBO);
					}

					float[] score5 = { Integer.MIN_VALUE };
					int[] no5 = { 0 };
					int[] rank5 = { 0 };
					List<PeriodicTestBO> hindiList = examAnaList.stream()
							.filter(a -> a.getHindi() != null && a.getHindi() >= 0)
							.sorted(Comparator.comparing(PeriodicTestBO::getHindi).reversed()).map(p -> {
								++no5[0];
								if (score5[0] != p.getHindi())
									rank5[0] = no5[0];
								score5[0] = p.getHindi();
								p.setHindiRank((long) rank5[0]);
								return p;
							}).collect(Collectors.toList());

					for (PeriodicTestBO periodicTestBO : hindiList) {
						periodicTestRepository.save(periodicTestBO);
					}

					float[] score6 = { Integer.MIN_VALUE };
					int[] no6 = { 0 };
					int[] rank6 = { 0 };
					List<PeriodicTestBO> evsList = examAnaList.stream()
							.filter(a -> a.getEvc() != null && a.getEvc() >= 0)
							.sorted(Comparator.comparing(PeriodicTestBO::getEvc).reversed()).map(p -> {
								++no6[0];
								if (score6[0] != p.getEvc())
									rank6[0] = no6[0];
								score6[0] = p.getEvc();
								p.setEvcRank((long) rank6[0]);
								return p;
							}).collect(Collectors.toList());

					for (PeriodicTestBO periodicTestBO : evsList) {
						periodicTestRepository.save(periodicTestBO);
					}

					float[] score7 = { Integer.MIN_VALUE };
					int[] no7 = { 0 };
					int[] rank7 = { 0 };
					List<PeriodicTestBO> computerList = examAnaList.stream()
							.filter(a -> a.getComputersTotal() != null && a.getComputersTotal() >= 0)
							.sorted(Comparator.comparing(PeriodicTestBO::getComputersTotal).reversed()).map(p -> {
								++no7[0];
								if (score7[0] != p.getComputersTotal())
									rank7[0] = no7[0];
								score7[0] = p.getComputersTotal();
								p.setEvcRank((long) rank7[0]);
								return p;
							}).collect(Collectors.toList());

					for (PeriodicTestBO periodicTestBO : computerList) {
						periodicTestRepository.save(periodicTestBO);
					}

				} else if (testMarksUpload.getTestVal().getName().equalsIgnoreCase("Half Yearly")) {

					InputStream inputStream = testMarksUpload.getUploadFile().getInputStream();
					List<Map<Integer, List<Object>>> sheets = ExcelUtil.readAllExcelSheetsData(inputStream);
					List<Object> colNames = new ArrayList<>();
					for (int i = 0; i < sheets.size(); i++) {
						Map<Integer, List<Object>> sheet = sheets.get(i);
						Set<Entry<Integer, List<Object>>> s = sheet.entrySet();
						Iterator<Entry<Integer, List<Object>>> itr = s.iterator();

						while (itr.hasNext()) {
							Entry<Integer, List<Object>> entry = itr.next();
							System.out
									.println("Sheet " + (i + 1) + ", Row " + entry.getKey() + " : " + entry.getValue());
							if (entry.getKey() == 1) {
								colNames = entry.getValue();
							} else {
								HalfYearlyBO examVal = null;
								int k = 0;
								Long total = 0l;
								for (Object val1 : entry.getValue()) {
									if (k == 0) {
										StudentBO stuval = studentRepository.findByStudentNo(val1.toString());
										examVal = halfYearlyRepository.findByStudentAndClassId(stuval,
												stuval.getClassId());
										if (examVal == null) {
											examVal = new HalfYearlyBO();
											examVal.setTest(testMarksUpload.getTestVal());
											examVal.setTestDate(testMarksUpload.getTestDate());
											examVal.setStudent(stuval);
											examVal.setClassId(testMarksUpload.getStudyClass());
											if (stuval.getClassId().getId() != testMarksUpload.getStudyClass()
													.getId()) {
												return new Gson().toJson(
														"Student AdmNo " + stuval.getStudentNo() + " Class Not Valid");
											}

											examVal.setTotalScored(0d);
										}

									} else {

										if (k == 2) {
											if (colNames.get(4).toString().toUpperCase().contains("MATHEMATICS")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For Maths "
															+ entry.getValue().get(0));
												}
												examVal.setMathsNb(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("ENGLISH")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For ENGLISH "
																	+ entry.getValue().get(0));
												}
												examVal.setEnglishNb(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("HINDI")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For HINDI "
															+ entry.getValue().get(0));
												}
												examVal.setHindiNb(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("E.V.S")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For EVS "
															+ entry.getValue().get(0));
												}
												examVal.setEvcNb(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("COMPUTERS")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For COMPUTERS "
																	+ entry.getValue().get(0));
												}
												examVal.setComputersNb(Double.valueOf(val1.toString()));
											}

										} else if (k == 3) {
											if (colNames.get(4).toString().toUpperCase().contains("MATHEMATICS")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For MATHEMATICS "
																	+ entry.getValue().get(0));
												}
												examVal.setMathsPro(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("ENGLISH")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For ENGLISH "
																	+ entry.getValue().get(0));
												}
												examVal.setEnglishPro(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("HINDI")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For HINDI "
															+ entry.getValue().get(0));
												}
												examVal.setHindiPro(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("E.V.S")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For E.V.S "
															+ entry.getValue().get(0));
												}
												examVal.setEvcPro(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("COMPUTERS")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For COMPUTERS "
																	+ entry.getValue().get(0));
												}
												examVal.setComputersPro(Double.valueOf(val1.toString()));
											}

										} else if (k == 4) {
											if (colNames.get(4).toString().toUpperCase().contains("MATHEMATICS")) {

												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setMaths(Double.valueOf(val1.toString()));
												} else {
													examVal.setMaths(0d);
													examVal.setStatus("AB");
												}

											} else if (colNames.get(4).toString().toUpperCase().contains("ENGLISH")) {
												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setEnglish(Double.valueOf(val1.toString()));
												} else {
													examVal.setEnglish(0d);
													examVal.setStatus("AB");
												}

											} else if (colNames.get(4).toString().toUpperCase().contains("HINDI")) {
												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setHindi(Double.valueOf(val1.toString()));
												} else {
													examVal.setHindi(0d);
													examVal.setStatus("AB");
												}

											} else if (colNames.get(4).toString().toUpperCase().contains("E.V.S")) {
												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setEvc(Double.valueOf(val1.toString()));
												} else {
													examVal.setEvc(0d);
													examVal.setStatus("AB");
												}

											} else if (colNames.get(4).toString().toUpperCase().contains("COMPUTERS")) {
												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setComputers(Double.valueOf(val1.toString()));
												} else {
													examVal.setComputers(0d);
													examVal.setStatus("AB");
												}

											}

										}

										TestBO testVal = testRepository.findById(8l).get();// find id or name
										PeriodicTestBO per = periodicTestRepository.findByStudentAndClassIdAndTest(
												examVal.getStudent(), examVal.getStudent().getClassId(), testVal);

										if (per == null) {
											return new Gson()
													.toJson("FEEDBACK-1-Periodic Test1 Marks Not Uploaded For Adm No "
															+ entry.getValue().get(0));
										}
										if (per != null && per.getMaths() != null) {
											if (per.getMaths() > 0) {
												examVal.setMathsPt(Double.valueOf(per.getMaths() / 4));
											} else {
												examVal.setMathsPt(0d);
											}
										} else {
											examVal.setMathsPt(0d);
										}
										if (per != null && per.getHindi() != null) {
											if (per.getMaths() > 0) {
												examVal.setHindiPt(Double.valueOf(per.getHindi() / 4));
											} else {
												examVal.setHindiPt(0d);
											}
										} else {
											examVal.setHindiPt(0d);
										}
										if (per != null && per.getEvc() != null) {
											if (per.getMaths() > 0) {
												examVal.setEvcPt(Double.valueOf(per.getEvc() / 4));
											} else {
												examVal.setEvcPt(0d);
											}
										} else {
											examVal.setEvcPt(0d);
										}
										if (per != null && per.getComputersTotal() != null) {
											if (per.getMaths() > 0) {
												examVal.setComputersPt(Double.valueOf(per.getComputersTotal() / 4));
											} else {
												examVal.setComputersPt(0d);
											}
										} else {
											examVal.setComputersPt(0d);
										}
										if (per != null && per.getEnglish() != null) {
											if (per.getMaths() > 0) {
												examVal.setEnglishPt(Double.valueOf(per.getEnglish() / 4));
											} else {
												examVal.setEnglishPt(0d);
											}
										} else {
											examVal.setEnglishPt(0d);
										}

									}
									k++;
								}

								if (colNames.get(4).toString().toUpperCase().contains("MATHEMATICS")) {
									Double mothTotal = examVal.getMathsNb() + examVal.getMathsPt()
											+ examVal.getMathsPro() + examVal.getMaths();
									examVal.setMathsTotal(mothTotal);
								} else if (colNames.get(4).toString().toUpperCase().contains("ENGLISH")) {
									Double engTotal = examVal.getEnglishNb() + examVal.getEnglishPt()
											+ examVal.getEnglishPro() + examVal.getEnglish();
									examVal.setEnglishTotal(engTotal);
								} else if (colNames.get(4).toString().toUpperCase().contains("HINDI")) {
									Double hindiTotal = examVal.getHindiNb() + examVal.getHindiPt()
											+ examVal.getHindiPro() + examVal.getHindi();
									examVal.setHindiTotal(hindiTotal);
								} else if (colNames.get(4).toString().toUpperCase().contains("E.V.S")) {
									Double evsTotal = examVal.getEvcNb() + examVal.getEvcPt() + examVal.getEvcPro()
											+ examVal.getEvc();
									examVal.setEvcTotal(evsTotal);
								} else if (colNames.get(4).toString().toUpperCase().contains("COMPUTERS")) {
									Double computerTotal = examVal.getComputersNb() + examVal.getComputersPt()
											+ examVal.getComputersPro() + examVal.getComputers();
									examVal.setComputersTotal(computerTotal);
								}
								halfYearlyRepository.save(examVal);
							}

						}
					}

				} else if (testMarksUpload.getTestVal().getName().equalsIgnoreCase("Final Year")) {

					InputStream inputStream = testMarksUpload.getUploadFile().getInputStream();
					List<Map<Integer, List<Object>>> sheets = ExcelUtil.readAllExcelSheetsData(inputStream);
					List<Object> colNames = new ArrayList<>();
					for (int i = 0; i < sheets.size(); i++) {
						Map<Integer, List<Object>> sheet = sheets.get(i);
						Set<Entry<Integer, List<Object>>> s = sheet.entrySet();
						Iterator<Entry<Integer, List<Object>>> itr = s.iterator();

						while (itr.hasNext()) {
							Entry<Integer, List<Object>> entry = itr.next();
							System.out
									.println("Sheet " + (i + 1) + ", Row " + entry.getKey() + " : " + entry.getValue());
							if (entry.getKey() == 1) {
								colNames = entry.getValue();
							} else {
								FinalYearlyBO examVal = null;
								int k = 0;
								Long total = 0l;
								for (Object val1 : entry.getValue()) {
									if (k == 0) {
										StudentBO stuval = studentRepository.findByStudentNo(val1.toString());
										examVal = finalYearlyRepository.findByStudentAndClassId(stuval,
												stuval.getClassId());
										if (examVal == null) {
											examVal = new FinalYearlyBO();
											examVal.setTest(testMarksUpload.getTestVal());
											examVal.setTestDate(testMarksUpload.getTestDate());
											examVal.setStudent(stuval);
											examVal.setClassId(testMarksUpload.getStudyClass());
											if (stuval.getClassId().getId() != testMarksUpload.getStudyClass()
													.getId()) {
												return new Gson().toJson(
														"Student AdmNo " + stuval.getStudentNo() + " Class Not Valid");
											}

											examVal.setTotalScored(0d);
										}

									} else {

										if (k == 2) {
											if (colNames.get(4).toString().toUpperCase().contains("MATHEMATICS")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For MATHEMATICS "
																	+ entry.getValue().get(0));
												}
												examVal.setMathsNb(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("ENGLISH")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For ENGLISH "
																	+ entry.getValue().get(0));
												}
												examVal.setEnglishNb(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("HINDI")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For HINDI "
															+ entry.getValue().get(0));
												}
												examVal.setHindiNb(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("E.V.S")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For EVS "
															+ entry.getValue().get(0));
												}
												examVal.setEvcNb(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("COMPUTERS")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For COMPUTERS "
																	+ entry.getValue().get(0));
												}
												examVal.setComputersNb(Double.valueOf(val1.toString()));
											}

										} else if (k == 3) {
											if (colNames.get(4).toString().toUpperCase().contains("MATHEMATICS")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For MATHEMATICS "
																	+ entry.getValue().get(0));
												}
												examVal.setMathsPro(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("ENGLISH")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For ENGLISH "
																	+ entry.getValue().get(0));
												}
												examVal.setEnglishPro(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("HINDI")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For HINDI "
															+ entry.getValue().get(0));
												}
												examVal.setHindiPro(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("E.V.S")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For E.V.S "
															+ entry.getValue().get(0));
												}
												examVal.setEvcPro(Double.valueOf(val1.toString()));
											} else if (colNames.get(4).toString().toUpperCase().contains("COMPUTERS")) {
												if (Long.valueOf(val1.toString()) > 10) {
													return new Gson()
															.toJson("FEEDBACK-1-Wrong Marks Entered For COMPUTERS "
																	+ entry.getValue().get(0));
												}
												examVal.setComputersPro(Double.valueOf(val1.toString()));
											}

										} else if (k == 4) {
											if (colNames.get(4).toString().toUpperCase().contains("MATHEMATICS")) {
												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setMaths(Double.valueOf(val1.toString()));
												} else {
													examVal.setMaths(0d);
													examVal.setStatus("AB");
												}

											} else if (colNames.get(4).toString().toUpperCase().contains("ENGLISH")) {
												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setEnglish(Double.valueOf(val1.toString()));
												} else {
													examVal.setEnglish(0d);
													examVal.setStatus("AB");
												}

											} else if (colNames.get(4).toString().toUpperCase().contains("HINDI")) {
												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setHindi(Double.valueOf(val1.toString()));
												} else {
													examVal.setHindi(0d);
													examVal.setStatus("AB");
												}

											} else if (colNames.get(4).toString().toUpperCase().contains("E.V.S")) {
												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setEvc(Double.valueOf(val1.toString()));
												} else {
													examVal.setEvc(0d);
													examVal.setStatus("AB");
												}

											} else if (colNames.get(4).toString().toUpperCase().contains("COMPUTERS")) {
												if (val1.toString() != null && val1.toString().length() > 0
														&& isNumeric(val1.toString())) {
													examVal.setComputers(Double.valueOf(val1.toString()));
												} else {
													examVal.setComputers(0d);
													examVal.setStatus("AB");
												}

											}

										}

										TestBO testVal = testRepository.findById(9l).get();// find id or name
										PeriodicTestBO per = periodicTestRepository.findByStudentAndClassIdAndTest(
												examVal.getStudent(), examVal.getStudent().getClassId(), testVal);

										if (per == null) {
											return new Gson()
													.toJson("FEEDBACK-1-Periodic Test2 Marks Not Uploaded For Adm No "
															+ entry.getValue().get(0));
										}

										if (per != null && per.getMaths() != null) {
											if (per.getMaths() > 0) {
												examVal.setMathsPt(Double.valueOf(per.getMaths() / 4));
											} else {
												examVal.setMathsPt(0d);
											}
										} else {
											examVal.setMathsPt(0d);
										}
										if (per != null && per.getHindi() != null) {
											if (per.getMaths() > 0) {
												examVal.setHindiPt(Double.valueOf(per.getHindi() / 4));
											} else {
												examVal.setHindiPt(0d);
											}
										} else {
											examVal.setHindiPt(0d);
										}
										if (per != null && per.getEvc() != null) {
											if (per.getMaths() > 0) {
												examVal.setEvcPt(Double.valueOf(per.getEvc() / 4));
											} else {
												examVal.setEvcPt(0d);
											}
										} else {
											examVal.setEvcPt(0d);
										}
										if (per != null && per.getComputersTotal() != null) {
											if (per.getMaths() > 0) {
												examVal.setComputersPt(Double.valueOf(per.getComputersTotal() / 4));
											} else {
												examVal.setComputersPt(0d);
											}
										} else {
											examVal.setComputersPt(0d);
										}
										if (per != null && per.getEnglish() != null) {
											if (per.getMaths() > 0) {
												examVal.setEnglishPt(Double.valueOf(per.getEnglish() / 4));
											} else {
												examVal.setEnglishPt(0d);
											}
										} else {
											examVal.setEnglishPt(0d);
										}
									}
									k++;
								}

								if (colNames.get(4).toString().toUpperCase().contains("MATHEMATICS")) {
									Double mothTotal = examVal.getMathsNb() + examVal.getMathsPt()
											+ examVal.getMathsPro() + examVal.getMaths();
									examVal.setMathsTotal(mothTotal);
								} else if (colNames.get(4).toString().toUpperCase().contains("ENGLISH")) {
									Double engTotal = examVal.getEnglishNb() + examVal.getEnglishPt()
											+ examVal.getEnglishPro() + examVal.getEnglish();
									examVal.setEnglishTotal(engTotal);
								} else if (colNames.get(4).toString().toUpperCase().contains("HINDI")) {
									Double hindiTotal = examVal.getHindiNb() + examVal.getHindiPt()
											+ examVal.getHindiPro() + examVal.getHindi();
									examVal.setHindiTotal(hindiTotal);
								} else if (colNames.get(4).toString().toUpperCase().contains("E.V.S")) {
									Double evsTotal = examVal.getEvcNb() + examVal.getEvcPt() + examVal.getEvcPro()
											+ examVal.getEvc();
									examVal.setEvcTotal(evsTotal);
								} else if (colNames.get(4).toString().toUpperCase().contains("COMPUTERS")) {
									Double computerTotal = 0d;
									if (examVal.getComputersPt() != null) {
										computerTotal = examVal.getComputersNb() + examVal.getComputersPt()
												+ examVal.getComputersPro() + examVal.getComputers();
									} else {
										computerTotal = examVal.getComputersNb() + examVal.getComputersPro()
												+ examVal.getComputers();
									}

									examVal.setComputersTotal(computerTotal);
								}
								finalYearlyRepository.save(examVal);
							}

						}
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson(e.getMessage());
		}
		return new Gson().toJson("FEEDBACK-1-Saved Successfully");
	}

	@ResponseBody
	@RequestMapping(value = "/student/uploadPeriodicSubMarks", method = RequestMethod.POST)
	public String uploadPeriodicSubMarks(@ModelAttribute TestMarksUploadDTO testMarksUpload) throws Exception {
		try {
			if (testMarksUpload.getUploadFile() != null) {
				InputStream inputStream = testMarksUpload.getUploadFile().getInputStream();
				List<Map<Integer, List<Object>>> sheets = ExcelUtil.readAllExcelSheetsData(inputStream);
				List<Object> colNames = new ArrayList<>();
				for (int i = 0; i < sheets.size(); i++) {
					Map<Integer, List<Object>> sheet = sheets.get(i);
					Set<Entry<Integer, List<Object>>> s = sheet.entrySet();
					Iterator<Entry<Integer, List<Object>>> itr = s.iterator();

					while (itr.hasNext()) {
						Entry<Integer, List<Object>> entry = itr.next();
						System.out.println("Sheet " + (i + 1) + ", Row " + entry.getKey() + " : " + entry.getValue());
						if (entry.getKey() == 1) {
							colNames = entry.getValue();
						} else {
							PeriodicTestBO examVal = null;
							int k = 0;
							Long total = 0l;
							for (Object val1 : entry.getValue()) {
								if (k == 0) {
									StudentBO stuval = studentRepository.findByStudentNo(val1.toString());
									examVal = periodicTestRepository.findByStudentAndTest(stuval,
											testMarksUpload.getTestVal());
									if (examVal == null) {
										examVal = new PeriodicTestBO();
										examVal.setTest(testMarksUpload.getTestVal());
										examVal.setTestDate(testMarksUpload.getTestDate());
										examVal.setStudent(stuval);
										examVal.setClassId(testMarksUpload.getStudyClass());
										if (stuval.getClassId().getId() != testMarksUpload.getStudyClass().getId()) {
											return new Gson().toJson(
													"Student AdmNo " + stuval.getStudentNo() + " Class Not Valid");
										}

										examVal.setTotalScored(0l);
									}

								} else if (k == 2) {
									if (colNames.get(k) != null
											&& colNames.get(k).toString().toUpperCase().contains("MATH")) {

										if (Long.valueOf(val1.toString()) > 80) {
											return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For Maths "
													+ entry.getValue().get(0));
										}
										examVal.setMaths(Long.valueOf(val1.toString()));
										total = Long.valueOf(val1.toString()) + total;
									} else if (colNames.get(k) != null
											&& colNames.get(k).toString().toUpperCase().contains("PHYSICS")) {
										if (Long.valueOf(val1.toString()) > 80) {
											return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For PHYSICS "
													+ entry.getValue().get(0));
										}
										examVal.setPhysics(Long.valueOf(val1.toString()));
										total = Long.valueOf(val1.toString()) + total;
									} else if (colNames.get(k) != null
											&& colNames.get(k).toString().toUpperCase().contains("CHEMISTRY")) {
										if (Long.valueOf(val1.toString()) > 80) {
											return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For CHEMISTRY "
													+ entry.getValue().get(0));
										}
										examVal.setChemistry(Long.valueOf(val1.toString()));
										total = Long.valueOf(val1.toString()) + total;
									} else if (colNames.get(k) != null
											&& colNames.get(k).toString().toUpperCase().contains("ENGLISH")) {
										if (Long.valueOf(val1.toString()) > 80) {
											return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For ENGLISH "
													+ entry.getValue().get(0));
										}
										examVal.setEnglish(Long.valueOf(val1.toString()));
										total = Long.valueOf(val1.toString()) + total;
									} else if (colNames.get(k) != null
											&& colNames.get(k).toString().toUpperCase().contains("HINDI")) {
										if (Long.valueOf(val1.toString()) > 80) {
											return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For HINDI "
													+ entry.getValue().get(0));
										}
										examVal.setHindi(Long.valueOf(val1.toString()));
										total = Long.valueOf(val1.toString()) + total;
									} else if (colNames.get(k) != null
											&& (colNames.get(k).toString().toUpperCase().contains("E.V.S")
													|| colNames.get(k).toString().toUpperCase().contains("EVS"))) {
										if (Long.valueOf(val1.toString()) > 80) {
											return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For EVS "
													+ entry.getValue().get(0));
										}
										examVal.setEvc(Long.valueOf(val1.toString()));
										total = Long.valueOf(val1.toString()) + total;
									} else if (colNames.get(k) != null
											&& colNames.get(k).toString().toUpperCase().contains("COMPUTERS")) {
										if (Long.valueOf(val1.toString()) > 80) {
											return new Gson().toJson("FEEDBACK-1-Wrong Marks Entered For COMPUTERS "
													+ entry.getValue().get(0));
										}
										examVal.setComputersTotal(Long.valueOf(val1.toString()));
										total = Long.valueOf(val1.toString()) + total;
									}
								}
								k++;
							}

							examVal.setTotalScored(total);
							periodicTestRepository.save(examVal);
						}

					}
				}

				List<PeriodicTestBO> examAnaList = periodicTestRepository
						.findByClassIdAndTest(testMarksUpload.getStudyClass(), testMarksUpload.getTestVal());

				float[] score1 = { Integer.MIN_VALUE };
				int[] no1 = { 0 };
				int[] rank1 = { 0 };
				List<PeriodicTestBO> mathList = examAnaList.stream()
						.filter(a -> a.getMaths() != null && a.getMaths() >= 0)
						.sorted(Comparator.comparing(PeriodicTestBO::getMaths).reversed()).map(p -> {
							++no1[0];
							if (score1[0] != p.getMaths())
								rank1[0] = no1[0];
							score1[0] = p.getMaths();
							p.setMathsRank((long) rank1[0]);
							return p;
						}).collect(Collectors.toList());

				bulkImporterService.bulkEntityManagerWithMerge(mathList);

				float[] score2 = { Integer.MIN_VALUE };
				int[] no2 = { 0 };
				int[] rank2 = { 0 };
				List<PeriodicTestBO> phyList = examAnaList.stream()
						.filter(a -> a.getPhysics() != null && a.getPhysics() >= 0)
						.sorted(Comparator.comparing(PeriodicTestBO::getPhysics).reversed()).map(p -> {
							++no2[0];
							if (score2[0] != p.getPhysics())
								rank2[0] = no2[0];
							score2[0] = p.getPhysics();
							p.setPhysicsRank((long) rank2[0]);
							return p;
						}).collect(Collectors.toList());

				bulkImporterService.bulkEntityManagerWithMerge(phyList);

				float[] score3 = { Integer.MIN_VALUE };
				int[] no3 = { 0 };
				int[] rank3 = { 0 };
				List<PeriodicTestBO> cheList = examAnaList.stream()
						.filter(a -> a.getChemistry() != null && a.getChemistry() >= 0)
						.sorted(Comparator.comparing(PeriodicTestBO::getChemistry).reversed()).map(p -> {
							++no3[0];
							if (score3[0] != p.getChemistry())
								rank3[0] = no3[0];
							score3[0] = p.getChemistry();
							p.setChemistryRank((long) rank3[0]);
							return p;
						}).collect(Collectors.toList());

				bulkImporterService.bulkEntityManagerWithMerge(cheList);

				float[] score4 = { Integer.MIN_VALUE };
				int[] no4 = { 0 };
				int[] rank4 = { 0 };
				List<PeriodicTestBO> engList = examAnaList.stream()
						.filter(a -> a.getEnglish() != null && a.getEnglish() >= 0)
						.sorted(Comparator.comparing(PeriodicTestBO::getEnglish).reversed()).map(p -> {
							++no4[0];
							if (score4[0] != p.getEnglish())
								rank4[0] = no4[0];
							score4[0] = p.getEnglish();
							p.setEnglishRank((long) rank4[0]);
							return p;
						}).collect(Collectors.toList());

				bulkImporterService.bulkEntityManagerWithMerge(engList);

				float[] score5 = { Integer.MIN_VALUE };
				int[] no5 = { 0 };
				int[] rank5 = { 0 };
				List<PeriodicTestBO> hindiList = examAnaList.stream()
						.filter(a -> a.getHindi() != null && a.getHindi() >= 0)
						.sorted(Comparator.comparing(PeriodicTestBO::getHindi).reversed()).map(p -> {
							++no5[0];
							if (score5[0] != p.getHindi())
								rank5[0] = no5[0];
							score5[0] = p.getHindi();
							p.setHindiRank((long) rank5[0]);
							return p;
						}).collect(Collectors.toList());

				bulkImporterService.bulkEntityManagerWithMerge(hindiList);

				float[] score6 = { Integer.MIN_VALUE };
				int[] no6 = { 0 };
				int[] rank6 = { 0 };
				List<PeriodicTestBO> evsList = examAnaList.stream().filter(a -> a.getEvc() != null && a.getEvc() >= 0)
						.sorted(Comparator.comparing(PeriodicTestBO::getEvc).reversed()).map(p -> {
							++no6[0];
							if (score6[0] != p.getEvc())
								rank6[0] = no6[0];
							score6[0] = p.getEvc();
							p.setEvcRank((long) rank6[0]);
							return p;
						}).collect(Collectors.toList());

				bulkImporterService.bulkEntityManagerWithMerge(evsList);

				float[] score7 = { Integer.MIN_VALUE };
				int[] no7 = { 0 };
				int[] rank7 = { 0 };
				List<PeriodicTestBO> computerList = examAnaList.stream()
						.filter(a -> a.getComputersTotal() != null && a.getComputersTotal() >= 0)
						.sorted(Comparator.comparing(PeriodicTestBO::getComputersTotal).reversed()).map(p -> {
							++no7[0];
							if (score7[0] != p.getComputersTotal())
								rank7[0] = no7[0];
							score7[0] = p.getComputersTotal();
							p.setEvcRank((long) rank7[0]);
							return p;
						}).collect(Collectors.toList());

				bulkImporterService.bulkEntityManagerWithMerge(computerList);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson(e.getMessage());
		}
		return new Gson().toJson("FEEDBACK-1-Saved Successfully");
	}

	@ResponseBody
	@RequestMapping(value = "/student/getHalfYearlyRankGeneration", method = RequestMethod.GET)
	public String getRankGeneration(@ModelAttribute TestMarksUploadDTO testMarksUpload) throws Exception {
		try {

			List<TestSubjectDetailsBO> testSubList = new ArrayList<>();
			testSubList = testSubjectRepository.findByTestAndStatus(testMarksUpload.getTestVal(), true);

			for (TestSubjectDetailsBO TestSubjectDetailsBO : testSubList) {

				if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("MATHEMATICS")) {

					List<HalfYearlyBO> perList = halfYearlyRepository.findByMathsIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students MATHEMATICS Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("PHYSICS")) {

					List<HalfYearlyBO> perList = halfYearlyRepository.findByPhysicsIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students PHYSICS Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("CHEMISTRY")) {

					List<HalfYearlyBO> perList = halfYearlyRepository.findByChemistryIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students CHEMISTRY Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("ENGLISH")) {

					List<HalfYearlyBO> perList = halfYearlyRepository.findByEnglishIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students ENGLISH Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("HINDI")) {

					List<HalfYearlyBO> perList = halfYearlyRepository.findByHindiIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students HINDI Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("E.V.S")) {

					List<HalfYearlyBO> perList = halfYearlyRepository.findByEvcIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students E.V.S Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("COMPUTERS")) {
					List<HalfYearlyBO> perList = halfYearlyRepository.findByComputersTotalIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students COMPUTERS Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}
				}

			}

			long sum = testSubList.stream().mapToLong(x -> x.getSubMaxMarks()).sum();

			List<HalfYearlyBO> examAnaList = halfYearlyRepository.findByClassIdAndTest(testMarksUpload.getStudyClass(),
					testMarksUpload.getTestVal());

			for (HalfYearlyBO halfYearlyBO : examAnaList) {
				Double totalSecuredSum = 0d;
				if (halfYearlyBO.getMathsTotal() != null) {
					totalSecuredSum = halfYearlyBO.getMathsTotal() + totalSecuredSum;
				}
				if (halfYearlyBO.getPhysicsTotal() != null) {
					totalSecuredSum = halfYearlyBO.getPhysicsTotal() + totalSecuredSum;
				}
				if (halfYearlyBO.getChemistryTotal() != null) {
					totalSecuredSum = halfYearlyBO.getChemistryTotal() + totalSecuredSum;
				}
				if (halfYearlyBO.getEnglishTotal() != null) {
					totalSecuredSum = halfYearlyBO.getEnglishTotal() + totalSecuredSum;
				}
				if (halfYearlyBO.getHindiTotal() != null) {
					totalSecuredSum = halfYearlyBO.getHindiTotal() + totalSecuredSum;
				}
				if (halfYearlyBO.getEvcTotal() != null) {
					totalSecuredSum = halfYearlyBO.getEvcTotal() + totalSecuredSum;
				}
				if (halfYearlyBO.getComputersTotal() != null) {
					totalSecuredSum = halfYearlyBO.getComputersTotal() + totalSecuredSum;
				}
				halfYearlyBO.setTotalScored(totalSecuredSum);
				halfYearlyRepository.save(halfYearlyBO);

			}

			Double[] score1 = { (double) Integer.MIN_VALUE };
			int[] no1 = { 0 };
			int[] rank1 = { 0 };

			SubjectBO mathSub = subjectRepository.findBySubjectName("MATHEMATICS");

			TestSubjectDetailsBO mathSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), mathSub);

			List<HalfYearlyBO> mathList = examAnaList.stream().filter(a -> a.getMaths() != null && a.getMaths() >= 0)
					.sorted(Comparator.comparing(HalfYearlyBO::getMathsTotal).reversed()).map(p -> {
						++no1[0];
						if (score1[0] != p.getMathsTotal())
							rank1[0] = no1[0];
						score1[0] = p.getMathsTotal();
						p.setMathsRank((long) rank1[0]);
						p.setMathsPer(
								round(((double) p.getMathsTotal() / mathSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(mathList);

			Double[] score2 = { (double) Integer.MIN_VALUE };
			int[] no2 = { 0 };
			int[] rank2 = { 0 };

			SubjectBO physicsSub = subjectRepository.findBySubjectName("PHYSICS");

			TestSubjectDetailsBO physicsSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), physicsSub);

			List<HalfYearlyBO> phyList = examAnaList.stream().filter(a -> a.getPhysics() != null && a.getPhysics() >= 0)
					.sorted(Comparator.comparing(HalfYearlyBO::getPhysics).reversed()).map(p -> {
						++no2[0];
						if (score2[0] != p.getPhysics())
							rank2[0] = no2[0];
						score2[0] = p.getPhysics();
						p.setPhysicsRank((long) rank2[0]);
						p.setPhysicsPer(
								round(((double) p.getPhysicsTotal() / physicsSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(phyList);

			Double[] score3 = { (double) Integer.MIN_VALUE };
			int[] no3 = { 0 };
			int[] rank3 = { 0 };

			SubjectBO chemistrySub = subjectRepository.findBySubjectName("CHEMISTRY");

			TestSubjectDetailsBO chemistrySubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), chemistrySub);

			List<HalfYearlyBO> cheList = examAnaList.stream()
					.filter(a -> a.getChemistry() != null && a.getChemistry() >= 0)
					.sorted(Comparator.comparing(HalfYearlyBO::getChemistry).reversed()).map(p -> {
						++no3[0];
						if (score3[0] != p.getChemistry())
							rank3[0] = no3[0];
						score3[0] = p.getChemistry();
						p.setChemistryRank((long) rank3[0]);
						p.setChemistryPer(round(
								((double) p.getChemistryTotal() / chemistrySubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(cheList);

			Double[] score4 = { (double) Integer.MIN_VALUE };
			int[] no4 = { 0 };
			int[] rank4 = { 0 };

			SubjectBO englishSub = subjectRepository.findBySubjectName("ENGLISH");

			TestSubjectDetailsBO englishSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), englishSub);

			List<HalfYearlyBO> engList = examAnaList.stream().filter(a -> a.getEnglish() != null && a.getEnglish() >= 0)
					.sorted(Comparator.comparing(HalfYearlyBO::getEnglishTotal).reversed()).map(p -> {
						++no4[0];
						if (score4[0] != p.getEnglishTotal())
							rank4[0] = no4[0];
						score4[0] = p.getEnglishTotal();
						p.setEnglishRank((long) rank4[0]);
						p.setEnglishPer(
								round(((double) p.getEnglishTotal() / englishSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(engList);

			SubjectBO hindiSub = subjectRepository.findBySubjectName("HINDI");

			TestSubjectDetailsBO hindiSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), hindiSub);

			Double[] score5 = { (double) Integer.MIN_VALUE };
			int[] no5 = { 0 };
			int[] rank5 = { 0 };
			List<HalfYearlyBO> hindiList = examAnaList.stream().filter(a -> a.getHindi() != null && a.getHindi() >= 0)
					.sorted(Comparator.comparing(HalfYearlyBO::getHindiTotal).reversed()).map(p -> {
						++no5[0];
						if (score5[0] != p.getHindiTotal())
							rank5[0] = no5[0];
						score5[0] = p.getHindiTotal();
						p.setHindiRank((long) rank5[0]);
						p.setHindiPer(
								round(((double) p.getHindiTotal() / hindiSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(hindiList);

			Double[] score6 = { (double) Integer.MIN_VALUE };
			int[] no6 = { 0 };
			int[] rank6 = { 0 };

			SubjectBO evsSub = subjectRepository.findBySubjectName("E.V.S");

			TestSubjectDetailsBO evsSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), evsSub);
			List<HalfYearlyBO> evsList = examAnaList.stream().filter(a -> a.getEvc() != null && a.getEvc() >= 0)
					.sorted(Comparator.comparing(HalfYearlyBO::getEvcTotal).reversed()).map(p -> {
						++no6[0];
						if (score6[0] != p.getEvcTotal())
							rank6[0] = no6[0];
						score6[0] = p.getEvcTotal();
						p.setEvcRank((long) rank6[0]);
						p.setEvcPer(round(((double) p.getEvcTotal() / evsSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(evsList);

			Double[] score7 = { (double) Integer.MIN_VALUE };
			int[] no7 = { 0 };
			int[] rank7 = { 0 };

			SubjectBO computersSub = subjectRepository.findBySubjectName("COMPUTERS");

			TestSubjectDetailsBO computersSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), computersSub);

			List<HalfYearlyBO> computerList = examAnaList.stream()
					.filter(a -> a.getComputers() != null && a.getComputers() >= 0)
					.sorted(Comparator.comparing(HalfYearlyBO::getComputersTotal).reversed()).map(p -> {
						++no7[0];
						if (score7[0] != p.getComputersTotal())
							rank7[0] = no7[0];
						score7[0] = p.getComputersTotal();
						p.setComputersRank((long) rank7[0]);
						p.setComputersPer(round(
								((double) p.getComputersTotal() / computersSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(computerList);

			Double[] score8 = { (double) Integer.MIN_VALUE };
			int[] no8 = { 0 };
			int[] rank8 = { 0 };
			List<HalfYearlyBO> totalList = examAnaList.stream()
					.filter(a -> a.getTotalScored() != null && a.getTotalScored() >= 0)
					.sorted(Comparator.comparing(HalfYearlyBO::getTotalScored).reversed()).map(p -> {
						++no8[0];
						if (score8[0] != p.getTotalScored())
							rank8[0] = no8[0];
						score8[0] = p.getTotalScored();
						p.setRank((long) rank8[0]);
						p.setTotalPer(round(((double) p.getTotalScored() / sum) * 100, 2));
						p.setGrade(getGrade(((double) p.getTotalScored() / sum) * 100) != null
								? getGrade(((double) p.getTotalScored() / sum) * 100).getGrade()
								: "");
						p.setRemarks(getGrade(((double) p.getTotalScored() / sum) * 100) != null
								? getGrade(((double) p.getTotalScored() / sum) * 100).getGradeResult()
								: "");
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(totalList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson(e.getMessage());
		}
		return new Gson().toJson("FEEDBACK-1-Saved Successfully");
	}

	@ResponseBody
	@RequestMapping(value = "/student/getFinalYearRankGeneration", method = RequestMethod.GET)
	public String getFinalYearRankGeneration(@ModelAttribute TestMarksUploadDTO testMarksUpload) throws Exception {
		try {

			List<TestSubjectDetailsBO> testSubList = new ArrayList<>();
			testSubList = testSubjectRepository.findByTestAndStatus(testMarksUpload.getTestVal(), true);

			for (TestSubjectDetailsBO TestSubjectDetailsBO : testSubList) {

				if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("MATHEMATICS")) {

					List<FinalYearlyBO> perList = finalYearlyRepository.findByMathsIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students MATHEMATICS Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("PHYSICS")) {

					List<FinalYearlyBO> perList = finalYearlyRepository.findByPhysicsIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students PHYSICS Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("CHEMISTRY")) {

					List<FinalYearlyBO> perList = finalYearlyRepository.findByChemistryIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students CHEMISTRY Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("ENGLISH")) {

					List<FinalYearlyBO> perList = finalYearlyRepository.findByEnglishIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students ENGLISH Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("HINDI")) {

					List<FinalYearlyBO> perList = finalYearlyRepository.findByHindiIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students HINDI Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("E.V.S")) {

					List<FinalYearlyBO> perList = finalYearlyRepository.findByEvcIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students E.V.S Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("COMPUTERS")) {
					List<FinalYearlyBO> perList = finalYearlyRepository.findByComputersTotalIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students COMPUTERS Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}
				}

			}

			long sum = testSubList.stream().mapToLong(x -> x.getSubMaxMarks()).sum();

			List<FinalYearlyBO> examAnaList = finalYearlyRepository
					.findByClassIdAndTest(testMarksUpload.getStudyClass(), testMarksUpload.getTestVal());

			for (FinalYearlyBO finalYearlyBO : examAnaList) {
				Double totalSecuredSum = 0d;

				HalfYearlyBO halfVal = halfYearlyRepository.findByStudent(finalYearlyBO.getStudent());

				if (finalYearlyBO.getMathsTotal() != null) {
					totalSecuredSum = finalYearlyBO.getMathsTotal() + totalSecuredSum;
					Double halfYearVal = 0d;
					if (halfVal != null) {
						halfYearVal = halfVal.getMathsTotal() / 2;
					}
					Double fianlYearVal = 0d;
					if (finalYearlyBO.getMathsAehe() != null) {
						fianlYearVal = finalYearlyBO.getMathsAehe() / 2;
					}
					finalYearlyBO.setMathsAehe(fianlYearVal + halfYearVal);

				}
				if (finalYearlyBO.getPhysicsTotal() != null) {
					totalSecuredSum = finalYearlyBO.getPhysicsTotal() + totalSecuredSum;

					Double halfYearVal = 0d;
					if (halfVal != null) {
						halfYearVal = halfVal.getPhysicsTotal() / 2;
					}
					Double fianlYearVal = 0d;
					if (finalYearlyBO.getPhysicsTotal() != null) {
						fianlYearVal = finalYearlyBO.getPhysicsTotal() / 2;
					}
					finalYearlyBO.setPhysicsAehe(fianlYearVal + halfYearVal);

				}
				if (finalYearlyBO.getChemistryTotal() != null) {
					totalSecuredSum = finalYearlyBO.getChemistryTotal() + totalSecuredSum;

					Double halfYearVal = 0d;
					if (halfVal != null) {
						halfYearVal = halfVal.getChemistryTotal() / 2;
					}
					Double fianlYearVal = 0d;
					if (finalYearlyBO.getChemistryTotal() != null) {
						fianlYearVal = finalYearlyBO.getChemistryTotal() / 2;
					}
					finalYearlyBO.setChemistryAehe(fianlYearVal + halfYearVal);
				}
				if (finalYearlyBO.getEnglishTotal() != null) {
					totalSecuredSum = finalYearlyBO.getEnglishTotal() + totalSecuredSum;

					Double halfYearVal = 0d;
					if (halfVal != null) {
						halfYearVal = halfVal.getEnglishTotal() / 2;
					}

					Double fianlYearVal = 0d;
					if (finalYearlyBO.getMathsAehe() != null) {
						fianlYearVal = finalYearlyBO.getEnglishTotal() / 2;
					}

					finalYearlyBO.setEnglishAehe(fianlYearVal + halfYearVal);
				}
				if (finalYearlyBO.getHindiTotal() != null) {
					totalSecuredSum = finalYearlyBO.getHindiTotal() + totalSecuredSum;

					Double halfYearVal = 0d;
					if (halfVal != null) {
						halfYearVal = halfVal.getHindiTotal() / 2;
					}
					Double fianlYearVal = 0d;
					if (finalYearlyBO.getMathsAehe() != null) {
						fianlYearVal = finalYearlyBO.getHindiTotal() / 2;
					}
					finalYearlyBO.setHindiAehe(fianlYearVal + halfYearVal);
				}
				if (finalYearlyBO.getEvcTotal() != null) {
					totalSecuredSum = finalYearlyBO.getEvcTotal() + totalSecuredSum;

					Double halfYearVal = 0d;
					if (halfVal != null) {
						halfYearVal = halfVal.getEvcTotal() / 2;
					}
					Double fianlYearVal = 0d;
					if (finalYearlyBO.getMathsAehe() != null) {
						fianlYearVal = finalYearlyBO.getEvcTotal() / 2;
					}
					finalYearlyBO.setEvcAehe(fianlYearVal + halfYearVal);
				}
				if (finalYearlyBO.getComputersTotal() != null) {
					totalSecuredSum = finalYearlyBO.getComputersTotal() + totalSecuredSum;

					Double halfYearVal = 0d;
					if (halfVal != null) {
						halfYearVal = halfVal.getComputersTotal() / 2;
					}
					Double fianlYearVal = 0d;
					if (finalYearlyBO.getComputersTotal() != null) {
						fianlYearVal = finalYearlyBO.getComputersTotal() / 2;
					}
					finalYearlyBO.setComputersAehe(fianlYearVal + halfYearVal);
				}
				finalYearlyBO.setTotalScored(totalSecuredSum);

				finalYearlyRepository.save(finalYearlyBO);

			}

			Double[] score1 = { (double) Integer.MIN_VALUE };
			int[] no1 = { 0 };
			int[] rank1 = { 0 };

			SubjectBO mathSub = subjectRepository.findBySubjectName("MATHEMATICS");

			TestSubjectDetailsBO mathSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), mathSub);

			List<FinalYearlyBO> mathList = examAnaList.stream().filter(a -> a.getMaths() != null && a.getMaths() >= 0)
					.sorted(Comparator.comparing(FinalYearlyBO::getMathsTotal).reversed()).map(p -> {
						++no1[0];
						if (score1[0] != p.getMathsTotal())
							rank1[0] = no1[0];
						score1[0] = p.getMathsTotal();
						p.setMathsRank((long) rank1[0]);
						p.setMathsPer(
								round(((double) p.getMathsTotal() / mathSubTotalMarks.getSubMaxMarks()) * 100, 2));

						p.setMathsGrade(getGrade(
								((double) p.getMathsTotal() / mathSubTotalMarks.getSubMaxMarks()) * 100) != null
										? getGrade(
												((double) p.getMathsTotal() / mathSubTotalMarks.getSubMaxMarks()) * 100)
														.getGrade()
										: "");
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(mathList);

			Double[] score2 = { (double) Integer.MIN_VALUE };
			int[] no2 = { 0 };
			int[] rank2 = { 0 };

			SubjectBO physicsSub = subjectRepository.findBySubjectName("PHYSICS");

			TestSubjectDetailsBO physicsSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), physicsSub);

			List<FinalYearlyBO> phyList = examAnaList.stream()
					.filter(a -> a.getPhysics() != null && a.getPhysics() >= 0)
					.sorted(Comparator.comparing(FinalYearlyBO::getPhysics).reversed()).map(p -> {
						++no2[0];
						if (score2[0] != p.getPhysics())
							rank2[0] = no2[0];
						score2[0] = p.getPhysics();
						p.setPhysicsRank((long) rank2[0]);
						p.setPhysicsPer(
								round(((double) p.getPhysicsTotal() / physicsSubTotalMarks.getSubMaxMarks()) * 100, 2));

						p.setPhysicsGrade(getGrade(
								((double) p.getPhysicsTotal() / physicsSubTotalMarks.getSubMaxMarks()) * 100) != null
										? getGrade(
												((double) p.getPhysicsTotal() / physicsSubTotalMarks.getSubMaxMarks())
														* 100).getGrade()
										: "");
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(phyList);

			Double[] score3 = { (double) Integer.MIN_VALUE };
			int[] no3 = { 0 };
			int[] rank3 = { 0 };

			SubjectBO chemistrySub = subjectRepository.findBySubjectName("CHEMISTRY");

			TestSubjectDetailsBO chemistrySubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), chemistrySub);

			List<FinalYearlyBO> cheList = examAnaList.stream()
					.filter(a -> a.getChemistry() != null && a.getChemistry() >= 0)
					.sorted(Comparator.comparing(FinalYearlyBO::getChemistry).reversed()).map(p -> {
						++no3[0];
						if (score3[0] != p.getChemistry())
							rank3[0] = no3[0];
						score3[0] = p.getChemistry();
						p.setChemistryRank((long) rank3[0]);
						p.setChemistryPer(round(
								((double) p.getChemistryTotal() / chemistrySubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(cheList);

			Double[] score4 = { (double) Integer.MIN_VALUE };
			int[] no4 = { 0 };
			int[] rank4 = { 0 };

			SubjectBO englishSub = subjectRepository.findBySubjectName("ENGLISH");

			TestSubjectDetailsBO englishSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), englishSub);

			List<FinalYearlyBO> engList = examAnaList.stream()
					.filter(a -> a.getEnglish() != null && a.getEnglish() >= 0)
					.sorted(Comparator.comparing(FinalYearlyBO::getEnglishTotal).reversed()).map(p -> {
						++no4[0];
						if (score4[0] != p.getEnglishTotal())
							rank4[0] = no4[0];
						score4[0] = p.getEnglishTotal();
						p.setEnglishRank((long) rank4[0]);
						p.setEnglishPer(
								round(((double) p.getEnglishTotal() / englishSubTotalMarks.getSubMaxMarks()) * 100, 2));

						p.setEnglishGrade(getGrade(
								((double) p.getEnglishTotal() / englishSubTotalMarks.getSubMaxMarks()) * 100) != null
										? getGrade(
												((double) p.getEnglishTotal() / englishSubTotalMarks.getSubMaxMarks())
														* 100).getGrade()
										: "");
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(engList);

			SubjectBO hindiSub = subjectRepository.findBySubjectName("HINDI");

			TestSubjectDetailsBO hindiSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), hindiSub);

			Double[] score5 = { (double) Integer.MIN_VALUE };
			int[] no5 = { 0 };
			int[] rank5 = { 0 };
			List<FinalYearlyBO> hindiList = examAnaList.stream().filter(a -> a.getHindi() != null && a.getHindi() >= 0)
					.sorted(Comparator.comparing(FinalYearlyBO::getHindiTotal).reversed()).map(p -> {
						++no5[0];
						if (score5[0] != p.getHindiTotal())
							rank5[0] = no5[0];
						score5[0] = p.getHindiTotal();
						p.setHindiRank((long) rank5[0]);
						p.setHindiPer(
								round(((double) p.getHindiTotal() / hindiSubTotalMarks.getSubMaxMarks()) * 100, 2));

						p.setHindiGrade(getGrade(
								((double) p.getHindiTotal() / hindiSubTotalMarks.getSubMaxMarks()) * 100) != null
										? getGrade(((double) p.getHindiTotal() / hindiSubTotalMarks.getSubMaxMarks())
												* 100).getGrade()
										: "");
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(hindiList);

			Double[] score6 = { (double) Integer.MIN_VALUE };
			int[] no6 = { 0 };
			int[] rank6 = { 0 };

			SubjectBO evsSub = subjectRepository.findBySubjectName("E.V.S");

			TestSubjectDetailsBO evsSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), evsSub);
			List<FinalYearlyBO> evsList = examAnaList.stream().filter(a -> a.getEvc() != null && a.getEvc() >= 0)
					.sorted(Comparator.comparing(FinalYearlyBO::getEvcTotal).reversed()).map(p -> {
						++no6[0];
						if (score6[0] != p.getEvcTotal())
							rank6[0] = no6[0];
						score6[0] = p.getEvcTotal();
						p.setEvcRank((long) rank6[0]);
						p.setEvcPer(round(((double) p.getEvcTotal() / evsSubTotalMarks.getSubMaxMarks()) * 100, 2));

						p.setEvcGrade(
								getGrade(((double) p.getEvcTotal() / evsSubTotalMarks.getSubMaxMarks()) * 100) != null
										? getGrade(((double) p.getEvcTotal() / evsSubTotalMarks.getSubMaxMarks()) * 100)
												.getGrade()
										: "");

						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(evsList);

			Double[] score7 = { (double) Integer.MIN_VALUE };
			int[] no7 = { 0 };
			int[] rank7 = { 0 };

			SubjectBO computersSub = subjectRepository.findBySubjectName("COMPUTERS");

			TestSubjectDetailsBO computersSubTotalMarks = testSubjectDetailsRepository
					.findByTestAndTestSubject(testMarksUpload.getTestVal(), computersSub);

			List<FinalYearlyBO> computerList = examAnaList.stream()
					.filter(a -> a.getComputers() != null && a.getComputers() >= 0)
					.sorted(Comparator.comparing(FinalYearlyBO::getComputersTotal).reversed()).map(p -> {
						++no7[0];
						if (score7[0] != p.getComputersTotal())
							rank7[0] = no7[0];
						score7[0] = p.getComputersTotal();
						p.setComputersRank((long) rank7[0]);
						p.setComputersPer(round(
								((double) p.getComputersTotal() / computersSubTotalMarks.getSubMaxMarks()) * 100, 2));

						p.setComputersGrade(
								getGrade(((double) p.getComputersTotal() / computersSubTotalMarks.getSubMaxMarks())
										* 100) != null
												? getGrade(((double) p.getComputersTotal()
														/ computersSubTotalMarks.getSubMaxMarks()) * 100).getGrade()
												: "");
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(computerList);

			Double[] score8 = { (double) Integer.MIN_VALUE };
			int[] no8 = { 0 };
			int[] rank8 = { 0 };
			List<FinalYearlyBO> totalList = examAnaList.stream()
					.filter(a -> a.getTotalScored() != null && a.getTotalScored() >= 0)
					.sorted(Comparator.comparing(FinalYearlyBO::getTotalScored).reversed()).map(p -> {
						++no8[0];
						if (score8[0] != p.getTotalScored())
							rank8[0] = no8[0];
						score8[0] = p.getTotalScored();
						p.setRank((long) rank8[0]);
						p.setTotalPer(round(((double) p.getTotalScored() / sum) * 100, 2));
						p.setGrade(getGrade(((double) p.getTotalScored() / sum) * 100) != null
								? getGrade(((double) p.getTotalScored() / sum) * 100).getGrade()
								: "");
						p.setRemarks(getGrade(((double) p.getTotalScored() / sum) * 100) != null
								? getGrade(((double) p.getTotalScored() / sum) * 100).getGradeResult()
								: "");
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(totalList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson(e.getMessage());
		}
		return new Gson().toJson("FEEDBACK-1-Saved Successfully");
	}

	@ResponseBody
	@RequestMapping(value = "/student/getPeriodicRankGeneration", method = RequestMethod.GET)
	public String getPeriodicRankGeneration(@ModelAttribute TestMarksUploadDTO testMarksUpload) throws Exception {

		TestBO testVal = testMarksUpload.getTestVal();
		try {
			List<TestSubjectDetailsBO> testSubList = new ArrayList<>();
			testSubList = testSubjectRepository.findByTest(testVal);

			for (TestSubjectDetailsBO TestSubjectDetailsBO : testSubList) {

				if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("MATHEMATICS")) {

					List<PeriodicTestBO> perList = periodicTestRepository.findByMathsIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students MATHEMATICS Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("PHYSICS")) {

					List<PeriodicTestBO> perList = periodicTestRepository.findByPhysicsIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students PHYSICS Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("CHEMISTRY")) {

					List<PeriodicTestBO> perList = periodicTestRepository.findByChemistryIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students CHEMISTRY Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("ENGLISH")) {

					List<PeriodicTestBO> perList = periodicTestRepository.findByEnglishIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students ENGLISH Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("HINDI")) {

					List<PeriodicTestBO> perList = periodicTestRepository.findByHindiIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students HINDI Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("E.V.S")) {

					List<PeriodicTestBO> perList = periodicTestRepository.findByEvcIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students E.V.S Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}

				} else if (TestSubjectDetailsBO.getTestSubject().getSubjectName().contains("COMPUTERS")) {
					List<PeriodicTestBO> perList = periodicTestRepository.findByComputersTotalIsNull();
					if (perList != null && perList.size() > 0) {
						return new Gson().toJson("Total Students COMPUTERS Marks Not Uploaded "
								+ perList.get(0).getStudent().getStudentNo());
					}
				}

			}

			long sum = testSubList.stream().mapToLong(x -> x.getSubMaxMarks()).sum();

			List<PeriodicTestBO> examAnaList = periodicTestRepository.findByTest(testVal);

			for (PeriodicTestBO periodicTestVal : examAnaList) {
				Long totalSecuredSum = 0l;
				if (periodicTestVal.getMaths() != null) {
					totalSecuredSum = periodicTestVal.getMaths() + totalSecuredSum;
				}
				if (periodicTestVal.getPhysics() != null) {
					totalSecuredSum = periodicTestVal.getPhysics() + totalSecuredSum;
				}
				if (periodicTestVal.getChemistry() != null) {
					totalSecuredSum = periodicTestVal.getChemistry() + totalSecuredSum;
				}
				if (periodicTestVal.getEnglish() != null) {
					totalSecuredSum = periodicTestVal.getEnglish() + totalSecuredSum;
				}
				if (periodicTestVal.getHindi() != null) {
					totalSecuredSum = periodicTestVal.getHindi() + totalSecuredSum;
				}
				if (periodicTestVal.getEvc() != null) {
					totalSecuredSum = periodicTestVal.getEvc() + totalSecuredSum;
				}
				if (periodicTestVal.getComputersTotal() != null) {
					totalSecuredSum = periodicTestVal.getComputersTotal() + totalSecuredSum;
				}
				periodicTestVal.setTotalScored(totalSecuredSum);
				periodicTestRepository.save(periodicTestVal);

			}

			float[] score1 = { Integer.MIN_VALUE };
			int[] no1 = { 0 };
			int[] rank1 = { 0 };

			SubjectBO mathSub = subjectRepository.findBySubjectName("MATHEMATICS");

			TestSubjectDetailsBO mathSubTotalMarks = testSubjectDetailsRepository.findByTestAndTestSubject(testVal,
					mathSub);

			List<PeriodicTestBO> mathList = examAnaList.stream().filter(a -> a.getMaths() != null && a.getMaths() >= 0)
					.sorted(Comparator.comparing(PeriodicTestBO::getMaths).reversed()).map(p -> {
						++no1[0];
						if (score1[0] != p.getMaths())
							rank1[0] = no1[0];
						score1[0] = p.getMaths();
						p.setMathsRank((long) rank1[0]);
						p.setMathsPer(round(((double) p.getMaths() / mathSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(mathList);

			SubjectBO engSub = subjectRepository.findBySubjectName("ENGLISH");

			TestSubjectDetailsBO engSubTotalMarks = testSubjectDetailsRepository.findByTestAndTestSubject(testVal,
					engSub);

			float[] score2 = { Integer.MIN_VALUE };
			int[] no2 = { 0 };
			int[] rank2 = { 0 };
			List<PeriodicTestBO> engList = examAnaList.stream()
					.filter(a -> a.getEnglish() != null && a.getEnglish() >= 0)
					.sorted(Comparator.comparing(PeriodicTestBO::getEnglish).reversed()).map(p -> {
						++no2[0];
						if (score2[0] != p.getEnglish())
							rank2[0] = no2[0];
						score2[0] = p.getEnglish();
						p.setEnglishRank((long) rank2[0]);
						p.setEnglishPer(round(((double) p.getEnglish() / engSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(engList);

			SubjectBO hindiSub = subjectRepository.findBySubjectName("HINDI");

			TestSubjectDetailsBO hindiSubTotalMarks = testSubjectDetailsRepository.findByTestAndTestSubject(testVal,
					hindiSub);

			float[] score3 = { Integer.MIN_VALUE };
			int[] no3 = { 0 };
			int[] rank3 = { 0 };
			List<PeriodicTestBO> hindiList = examAnaList.stream().filter(a -> a.getHindi() != null && a.getHindi() >= 0)
					.sorted(Comparator.comparing(PeriodicTestBO::getHindi).reversed()).map(p -> {
						++no3[0];
						if (score3[0] != p.getHindi())
							rank3[0] = no3[0];
						score3[0] = p.getHindi();
						p.setHindiRank((long) rank3[0]);
						p.setHindiPer(round(((double) p.getHindi() / hindiSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(hindiList);

			SubjectBO evsSub = subjectRepository.findBySubjectName("E.V.S");

			TestSubjectDetailsBO evsSubTotalMarks = testSubjectDetailsRepository.findByTestAndTestSubject(testVal,
					evsSub);

			float[] score5 = { Integer.MIN_VALUE };
			int[] no5 = { 0 };
			int[] rank5 = { 0 };
			List<PeriodicTestBO> evsList = examAnaList.stream().filter(a -> a.getEvc() != null && a.getEvc() >= 0)
					.sorted(Comparator.comparing(PeriodicTestBO::getEvc).reversed()).map(p -> {
						++no5[0];
						if (score5[0] != p.getEvc())
							rank5[0] = no5[0];
						score5[0] = p.getEvc();
						p.setEvcRank((long) rank5[0]);
						p.setEvcPer(round(((double) p.getEvc() / evsSubTotalMarks.getSubMaxMarks()) * 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(evsList);

			SubjectBO computersSub = subjectRepository.findBySubjectName("COMPUTERS");

			TestSubjectDetailsBO computersSubTotalMarks = testSubjectDetailsRepository.findByTestAndTestSubject(testVal,
					computersSub);

			float[] score6 = { Integer.MIN_VALUE };
			int[] no6 = { 0 };
			int[] rank6 = { 0 };
			List<PeriodicTestBO> computersList = examAnaList.stream()
					.filter(a -> a.getComputersTotal() != null && a.getComputersTotal() >= 0)
					.sorted(Comparator.comparing(PeriodicTestBO::getComputersTotal).reversed()).map(p -> {
						++no6[0];
						if (score6[0] != p.getComputersTotal())
							rank6[0] = no6[0];
						score6[0] = p.getComputersTotal();
						p.setComputersRank((long) rank5[0]);
						p.setComputersPer(round(((double) p.getComputersTotal()
								/ (computersSubTotalMarks.getSubMaxMarks() + computersSubTotalMarks.getPractical()))
								* 100, 2));
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(computersList);

			float[] score4 = { Integer.MIN_VALUE };
			int[] no4 = { 0 };
			int[] rank4 = { 0 };
			List<PeriodicTestBO> totalList = examAnaList.stream()
					.filter(a -> a.getTotalScored() != null && a.getTotalScored() >= 0)
					.sorted(Comparator.comparing(PeriodicTestBO::getTotalScored).reversed()).map(p -> {
						++no4[0];
						if (score4[0] != p.getTotalScored())
							rank4[0] = no4[0];
						score4[0] = p.getTotalScored();
						p.setRank((long) rank4[0]);
						p.setTotalPer(round(((double) p.getTotalScored() / sum) * 100, 2));
						p.setGrade(getGrade(((double) p.getTotalScored() / sum) * 100) != null
								? getGrade(((double) p.getTotalScored() / sum) * 100).getGrade()
								: "");
						p.setRemarks(getGrade(((double) p.getTotalScored() / sum) * 100) != null
								? getGrade(((double) p.getTotalScored() / sum) * 100).getGradeResult()
								: "");
						return p;
					}).collect(Collectors.toList());

			bulkImporterService.bulkEntityManagerWithMerge(totalList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson(e.getMessage());
		}
		return new Gson().toJson("FEEDBACK-1-Saved Successfully");
	}

	public GradeBO getGrade(Double per) {

		Double longval1 = (new Double(per));

		long longval = longval1.longValue();

		List<GradeBO> grades = gradeRepository.findAll();

		for (GradeBO grade : grades) {

			String[] s = grade.getGradeRande().split("-");

			long min = Long.valueOf(s[0]);
			long max = Long.valueOf(s[1]);

			if (min <= longval && max >= longval) {
				return grade;
			}

		}
		return null;

	}

	public static void main(String[] args) {

		List<String> rs = new ArrayList<>();

		rs.add("91-100");
		rs.add("81-90");
		rs.add("71-80");

		float i = 86.04f;

		for (String st : rs) {

			String[] s = st.split("-");

			long min = Long.valueOf(s[0]);
			long max = Long.valueOf(s[1]);

			System.out.println(s[0]);
			System.out.println(s[1]);

			if (min <= i && max >= i) {

				System.out.println("good");
			}

		}

	}

	// tc

	@GetMapping(value = "/student/showStudentTc")
	public ModelAndView showStudentTc() {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("myTheme1/tc/studentTransferCertificate");

		return mv;

	}

	@GetMapping(value = "/student/getStAdmNoDetls")
	public ModelAndView getStAdmNoDetls(@RequestParam("admNumber") StudentBO admNumber) {

		ModelAndView mv = new ModelAndView();

		mv.addObject("admNumber", admNumber);

		StudentTCDetailsBO stuTcVal = studentTCRepository.findByAdmissionNo(admNumber.getStudentNo());

		mv.addObject("stuTcVal", stuTcVal);

		mv.setViewName("myTheme1/tc/studentTransferDetails");

		return mv;

	}

	@RequestMapping(value = "/student/getStudentTcPrint", method = RequestMethod.POST)
	public String getStudentTcPrint(@ModelAttribute StudentTCDTO entry) throws Exception {

		try {
			if (entry.getStudent() != null) {
				StudentBO stuVal = entry.getStudent();
				StudentTCDetailsBO tcVal = studentTCRepository.findByAdmissionNo(stuVal.getStudentNo());
				if (tcVal == null) {
					tcVal = new StudentTCDetailsBO();
					tcVal.setCreatedBy(employeeAction.getCurrentUser());
					tcVal.setCreatedOn(new Date());
				} else {
					tcVal.setUpdatedBy(employeeAction.getCurrentUser());
					tcVal.setUpdatedOn(new Date());
				}
				tcVal.setAdmissionNo(entry.getAdmissionNo().trim());
				tcVal.setAnnualExamsLastTaken(entry.getAnnualExamsLastTaken());
				tcVal.setAnyOtherRemarks(entry.getAnyOtherRemarks());
				tcVal.setCategory(entry.getCategory());
				tcVal.setClassOfLeaving(entry.getClassOfLeaving());
				tcVal.setCoCcurricularActivities(entry.getCoCcurricularActivities());
				tcVal.setConcessionAvailed(entry.getConcessionAvailed());
				tcVal.setConduct(entry.getConduct());
				tcVal.setDateOfAdmission(entry.getDateOfAdmission());
				tcVal.setDateOfAppliedTransferCertificate(entry.getDateOfAppliedTransferCertificate());
				tcVal.setDateOfIssuTransferCertificate(entry.getDateOfIssuTransferCertificate());
				tcVal.setFatherName(entry.getFatherName());
				tcVal.setMotherName(entry.getMotherName());
				tcVal.setName(entry.getName());
				tcVal.setNationalityAndReligion(entry.getNationalityAndReligion());
				tcVal.setNoOfWorkingDays(entry.getNoOfWorkingDays());
				tcVal.setNoOfWorkingDaysPresent(entry.getNoOfWorkingDaysPresent());
				tcVal.setOnceOrtwiceinSameClass(entry.getOnceOrtwiceinSameClass());
				tcVal.setPaidSchoolDues(entry.getPaidSchoolDues());
				tcVal.setQualifierforPromotiontoHigherClass(entry.getQualifierforPromotiontoHigherClass());
				tcVal.setReasonForLeaving(entry.getReasonForLeaving());
				tcVal.setStudentCast(entry.getStudentCast());
				tcVal.setStudentDob(entry.getStudentDob());
				tcVal.setSubject1(entry.getSubject1());
				tcVal.setSubject2(entry.getSubject2());
				tcVal.setSubject3(entry.getSubject3());
				tcVal.setSubject4(entry.getSubject4());
				tcVal.setSubject5(entry.getSubject5());
				tcVal.setSubject6(entry.getSubject6());
				if (studentTCRepository.findTop1ByOrderByIdDesc() != null) {
					Integer intVal = studentTCRepository.findTop1ByOrderByIdDesc().getTcNo();
					tcVal.setTcNo(intVal);
				} else {
					tcVal.setTcNo(1);
				}

				studentTCRepository.save(tcVal);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson(e.getMessage());
		}

		return new Gson().toJson("FEEDBACK-1-Saved Successfully");
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/scaits/dynamicReportDataExport")
	public ResponseEntity<byte[]> dynamicReportDataExcel(HttpServletResponse servletResponse,
			@RequestParam(value = "reportType") String reportType, @RequestParam(value = "student") StudentBO admNumber)
			throws Exception {

		StudentTCDetailsBO stuTcVal = studentTCRepository.findByAdmissionNo(admNumber.getStudentNo());

		ByteArrayOutputStream bis = excelAndPdfUtil.createStudentTransferCertificate(stuTcVal,
				employeeAction.getCurrentUser(), DU.format(new Date(), "dd/mm/yyyy"));

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/pdf;");
		headers.add("Content-Disposition", "attachment; filename=StudentTC_" + admNumber.getStudentNo() + ".pdf");

		return new ResponseEntity<byte[]>(bis.toByteArray(), headers, HttpStatus.CREATED);
	}

	// employee

	@GetMapping(value = "/employee/showEmpInformation")
	public ModelAndView showEmpInformation() {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("myTheme1/employee/employeeSearch");

		return mv;

	}

	@GetMapping(value = "/employee/searchEmployee")
	public ModelAndView searchEmployee(@RequestParam("mobileNo") String mobileNo,
			@RequestParam("surName") String surName, @RequestParam("employeeName") String employeeName,
			@RequestParam("payrollId") String payrollId) {

		ModelAndView mv = new ModelAndView();
		List<EmployeeBO> empList = null;

		if (mobileNo != "" && surName == "" && employeeName == "" && payrollId == "") {
			empList = employeeRepository.findByMobileNoContaining(mobileNo);
		} else if (mobileNo == "" && surName != "" && employeeName == "" && payrollId == "") {
			empList = employeeRepository.findBySurNameContaining(surName);
		} else if (mobileNo == "" && surName == "" && employeeName != "" && payrollId == "") {
			empList = employeeRepository.findByUserNameContaining(employeeName);
		} else if (mobileNo == "" && surName == "" && employeeName == "" && payrollId != "") {
			empList = employeeRepository.findByPayRollIdContaining(payrollId);
		}

		mv.addObject("empList", empList);
		mv.setViewName("myTheme1/employee/empListGrid");

		return mv;

	}

	@GetMapping(value = "/employee/getEmployeeInfo")
	public ModelAndView getEmployeeInfo(@RequestParam("employee") EmployeeBO employee) {

		ModelAndView mv = new ModelAndView();

		mv.addObject("employee", employee);
		mv.addObject("subList", subjectRepository.findAll());
		mv.setViewName("myTheme1/employee/employeeInformation");

		return mv;

	}

	@GetMapping(value = "/employee/addEmployeeInfo")
	public ModelAndView addEmployeeInfo() {

		ModelAndView mv = new ModelAndView();

		mv.addObject("subList", subjectRepository.findByStatus(true));
		mv.setViewName("myTheme1/employee/employeeInformation");

		return mv;

	}

	@RequestMapping(value = "/student/getClassStudents")
	public ResponseEntity<byte[]> download(HttpServletResponse servletResponse,
			@RequestParam("classId") StudyClassBO studyClass, @RequestParam("testId") TestBO testId,
			@RequestParam("sectionId") ClassSectionBO sectionId, @RequestParam("subject") SubjectBO subject)
			throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		try {

			List<StudentBO> students = new ArrayList<>();

			if (studyClass != null && sectionId == null) {
				students = studentRepository.findByClassId(studyClass);
			} else if (studyClass != null && sectionId != null) {
				students = studentRepository.findByClassIdAndSectionId(studyClass, sectionId);
			}

			List<Object[]> list = new ArrayList<>();

			for (StudentBO student : students) {
				Object[] objArray = new Object[10];

				objArray[0] = student.getStudentNo();
				objArray[1] = student.getName();
				list.add(objArray);
			}
			Set<String> columnNames = new LinkedHashSet<String>();
			columnNames.add("Adm No");
			columnNames.add("Student");
			if (testId.getId() == 6) {
				columnNames.add("NoteBook");
				columnNames.add("Project");
			} else if (testId.getId() == 7) {
				columnNames.add("NoteBook");
				columnNames.add("Project");
			}

			if ((testId.getId() == 8 || testId.getId() == 9)
					&& subject.getSubjectName().toUpperCase().contains("COMPUTER")) {
				columnNames.add("COMPUTERS_THEORY");
				columnNames.add("COMPUTERS_PRACTICAL");
			} else {
				columnNames.add(subject.getSubjectName());
			}

			Set<String> colNames = columnNames;
			String dateStr = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			String reportName = subject.getSubjectName();
			SXSSFWorkbook wb = new SXSSFWorkbook(100);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ExcelView.buildExcelDocumentpain(list, colNames, wb, request, servletResponse, reportName, null, dateStr);

			try {
				wb.write(bos);
			} finally {
				bos.close();
			}

			byte[] bytes = bos.toByteArray();

			HttpHeaders headers = new HttpHeaders();

			headers.set("Content-Type", "application/vnd.ms-excel;");
			headers.set("content-length", Integer.toString(bytes.length));
			headers.set("Content-Disposition",
					"attachment; filename=" + reportName + "_" + new Date().getTime() + ".xlsx");

			return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/student/getExtraStudents")
	public ResponseEntity<byte[]> downloadExtra(HttpServletResponse servletResponse,
			@RequestParam("classId") StudyClassBO studyClass, @RequestParam("testId") TestBO testId,
			@RequestParam("sectionId") ClassSectionBO sectionId) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		try {

			List<StudentBO> students = new ArrayList<>();

			if (studyClass != null && sectionId == null) {
				students = studentRepository.findByClassId(studyClass);
			} else if (studyClass != null && sectionId != null) {
				students = studentRepository.findByClassIdAndSectionId(studyClass, sectionId);
			}
			List<Object[]> list = new ArrayList<>();

			for (StudentBO student : students) {
				Object[] objArray = new Object[10];

				objArray[0] = student.getStudentNo();
				objArray[1] = student.getName();
				list.add(objArray);
			}
			Set<String> columnNames = new LinkedHashSet<String>();
			columnNames.add("Adm No");
			columnNames.add("Student");

			columnNames.add("WORK");
			columnNames.add("ART");
			columnNames.add("SPORTS");
			columnNames.add("CCA");
			columnNames.add("MUSIC");
			columnNames.add("DANCE");
			columnNames.add("ATTENDANCE");

			Set<String> colNames = columnNames;
			String dateStr = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			String reportName = "ExtraCurricularActivities";
			SXSSFWorkbook wb = new SXSSFWorkbook(100);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ExcelView.buildExcelDocumentpain(list, colNames, wb, request, servletResponse, reportName, null, dateStr);

			try {
				wb.write(bos);
			} finally {
				bos.close();
			}

			byte[] bytes = bos.toByteArray();

			HttpHeaders headers = new HttpHeaders();

			headers.set("Content-Type", "application/vnd.ms-excel;");
			headers.set("content-length", Integer.toString(bytes.length));
			headers.set("Content-Disposition",
					"attachment; filename=" + reportName + "_" + new Date().getTime() + ".xlsx");

			return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// EXTRACURRICULAR ACTIVITIES

	@GetMapping(value = "/student/showExtracurricularActivitiesUpload")
	public ModelAndView showExtracurricularActivitiesUpload() {

		ModelAndView mv = new ModelAndView();

		List<Long> ids = new ArrayList<>();
		ids.add(6l);
		ids.add(7l);

		mv.addObject("testList", testRepository.findByTestIds(ids));
		mv.addObject("classList", studyClassRepository.findAll());
		mv.addObject("classSectionList", classSectionRepository.findAll());

		mv.setViewName("myTheme1/marksUpload/extracurricularActivitiesUpload");

		return mv;

	}

	@ResponseBody
	@RequestMapping(value = "/student/uploadExtracurricularActivities", method = RequestMethod.POST)
	public String uploadExtracurricularActivities(@ModelAttribute TestMarksUploadDTO testMarksUpload) throws Exception {
		try {
			if (testMarksUpload.getUploadFile() != null) {

				InputStream inputStream = testMarksUpload.getUploadFile().getInputStream();
				List<Map<Integer, List<Object>>> sheets = ExcelUtil.readAllExcelSheetsData(inputStream);
				List<Object> colNames = new ArrayList<>();
				for (int i = 0; i < sheets.size(); i++) {
					if (i == 0) {
						Map<Integer, List<Object>> sheet = sheets.get(i);
						Set<Entry<Integer, List<Object>>> s = sheet.entrySet();
						Iterator<Entry<Integer, List<Object>>> itr = s.iterator();

						while (itr.hasNext()) {
							Entry<Integer, List<Object>> entry = itr.next();
							System.out
									.println("Sheet " + (i + 1) + ", Row " + entry.getKey() + " : " + entry.getValue());
							if (entry.getKey() == 1) {
								colNames = entry.getValue();
							} else {

								if (testMarksUpload.getTestVal().getName().toUpperCase().contains("HALF YEAR")) {
									HalfYearlyBO examVal = null;
									int k = 0;
									Long total = 0l;
									for (Object val1 : entry.getValue()) {
										if (k == 0) {
											StudentBO stuval = studentRepository.findByStudentNo(val1.toString());
											examVal = halfYearlyRepository.findByStudentAndTest(stuval,
													testMarksUpload.getTestVal());
											if (examVal == null) {
												examVal = new HalfYearlyBO();
												examVal.setTest(testMarksUpload.getTestVal());
												examVal.setTestDate(testMarksUpload.getTestDate());
												examVal.setStudent(stuval);
												examVal.setClassId(testMarksUpload.getStudyClass());
												if (stuval.getClassId().getId() != testMarksUpload.getStudyClass()
														.getId()) {
													return new Gson().toJson("Student AdmNo " + stuval.getStudentNo()
															+ " Class Not Valid");
												}

												examVal.setTotalScored(0d);
											}

										} else {

											if (k == 2) {
												if (colNames.get(2).toString().toUpperCase().contains("WORK")) {
													examVal.setWork(val1.toString());
												}
											} else if (k == 3) {
												if (colNames.get(3).toString().toUpperCase().contains("ART")) {
													examVal.setArt(val1.toString());
												}
											} else if (k == 4) {
												if (colNames.get(4).toString().toUpperCase().contains("SPORTS")) {
													examVal.setSports(val1.toString());
												}
											} else if (k == 5) {
												if (colNames.get(5).toString().toUpperCase().contains("CCA")) {
													examVal.setCca(val1.toString());
												}
											} else if (k == 6) {
												if (colNames.get(6).toString().toUpperCase().contains("MUSIC")) {
													examVal.setMusic(val1.toString());
												}
											} else if (k == 7) {
												if (colNames.get(7).toString().toUpperCase().contains("DANCE")) {
													examVal.setDance(val1.toString());
												}
											} else if (k == 8) {
												if (colNames.get(8).toString().toUpperCase().contains("ATTENDANCE")) {
													if (val1.toString() != null && val1.toString().length() > 0) {
														examVal.setAttendance(Long.valueOf(val1.toString()));
													} else {
														examVal.setAttendance(0l);
													}

												}
											}

										}
										k++;
									}

									halfYearlyRepository.save(examVal);
								} else if (testMarksUpload.getTestVal().getName().toUpperCase()
										.contains("FINAL YEAR")) {
									FinalYearlyBO examVal = null;
									int k = 0;
									Long total = 0l;
									for (Object val1 : entry.getValue()) {
										if (k == 0) {
											StudentBO stuval = studentRepository.findByStudentNo(val1.toString());
											examVal = finalYearlyRepository.findByStudentAndTest(stuval,
													testMarksUpload.getTestVal());
											if (examVal == null) {
												examVal = new FinalYearlyBO();
												examVal.setTest(testMarksUpload.getTestVal());
												examVal.setTestDate(testMarksUpload.getTestDate());
												examVal.setStudent(stuval);
												examVal.setClassId(testMarksUpload.getStudyClass());
												if (stuval.getClassId().getId() != testMarksUpload.getStudyClass()
														.getId()) {
													return new Gson().toJson("Student AdmNo " + stuval.getStudentNo()
															+ " Class Not Valid");
												}

												examVal.setTotalScored(0d);
											}

										} else {

											if (k == 2) {
												if (colNames.get(2).toString().toUpperCase().contains("WORK")) {
													examVal.setWork(val1.toString());
												}
											} else if (k == 3) {
												if (colNames.get(3).toString().toUpperCase().contains("ART")) {
													examVal.setArt(val1.toString());
												}
											} else if (k == 4) {
												if (colNames.get(4).toString().toUpperCase().contains("SPORTS")) {
													examVal.setSports(val1.toString());
												}
											} else if (k == 5) {
												if (colNames.get(5).toString().toUpperCase().contains("CCA")) {
													examVal.setCca(val1.toString());
												}
											} else if (k == 6) {
												if (colNames.get(6).toString().toUpperCase().contains("MUSIC")) {
													examVal.setMusic(val1.toString());
												}
											} else if (k == 7) {
												if (colNames.get(7).toString().toUpperCase().contains("DANCE")) {
													examVal.setDance(val1.toString());
												}
											} else if (k == 8) {
												if (colNames.get(8).toString().toUpperCase().contains("ATTENDANCE")) {
													if (val1.toString() != null && val1.toString().length() > 0) {
														examVal.setAttendance(Long.valueOf(val1.toString()));
													} else {
														examVal.setAttendance(0l);
													}

												}
											}

										}
										k++;
									}

									finalYearlyRepository.save(examVal);
								}

							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Gson().toJson(e.getMessage());
		}
		return new Gson().toJson("FEEDBACK-1-Saved Successfully");
	}

	public double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c))
				return false;
		}
		return true;
	}

}
