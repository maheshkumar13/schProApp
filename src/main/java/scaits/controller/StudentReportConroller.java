package scaits.controller;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import scaits.bo.common.FinalYearlyBO;
import scaits.bo.common.HalfYearlyBO;
import scaits.bo.common.PeriodicTestBO;
import scaits.bo.student.ClassSectionBO;
import scaits.bo.student.StudentBO;
import scaits.bo.student.StudyClassBO;
import scaits.bo.student.SubjectBO;
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
import scaits.repository.StudyClassRepository;
import scaits.repository.SubjectRepository;
import scaits.repository.TestRepository;
import scaits.repository.TransactionRepository;
import scaits.util.BulkImporterService;
import scaits.util.EmployeeAction;

@RestController
@Scope(value = "request")
@Transactional(value = "mySqlTransactionManager", rollbackFor = { Exception.class })
public class StudentReportConroller {

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
	BulkImporterService bulkImporterService;

	@Autowired
	GradeRepository gradeRepository;

	@Autowired
	HalfYearlyRepository halfYearlyRepository;

	@Autowired
	FinalYearlyRepository finalYearlyRepository;

	@Autowired
	PeriodicTestRepository periodicTestRepository;

	public static final String uploadingDir = System.getProperty("user.dir") + "/uploadingDir/";

	@GetMapping("/report/progressReport")
	public ModelAndView progressReport() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.addObject("testList", testRepository.findByStatus(true));
		mv.addObject("classList", studyClassRepository.findAll());
		mv.setViewName("myTheme1/reports/studentProgressReport.html");

		return mv;
	}

	@GetMapping(value = "/report/getStuProgressReport")
	public ModelAndView getStudentProgressInformation(@RequestParam("studentNo") StudentBO studentNo) {

		ModelAndView mv = new ModelAndView();

		mv.addObject("student", studentNo);
		mv.setViewName("myTheme1/reports/progressReport");

		return mv;

	}

	@GetMapping("/report/halfyearly")
	public ModelAndView halfyearly() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.addObject("testList", testRepository.findByStatus(true));
		mv.addObject("classList", studyClassRepository.findAll());
		mv.addObject("subList", subjectRepository.findByStatus(true));
		List<ClassSectionBO> classSectionList = new ArrayList<>();
		mv.addObject("classSectionList", classSectionList);
		mv.setViewName("myTheme1/reports/halfYearlyReport");

		return mv;
	}

	@RequestMapping(value = "/report/getHalfyearly", method = RequestMethod.GET)
	public ModelAndView getHalfYearly(@RequestParam("classId") StudyClassBO studyClass,
			@RequestParam("section") ClassSectionBO section) {

		ModelAndView mv = new ModelAndView();

		List<HalfYearlyBO> halfYearly = new ArrayList<>();

		if (studyClass != null && section == null) {
			halfYearly = halfYearlyRepository.findByClassId(studyClass);
		} else if (studyClass != null && section != null) {
			halfYearly = halfYearlyRepository.findByClass(studyClass.getId(), section.getId());
		}

		mv.addObject("half", halfYearly);
		mv.addObject("studyClass", studyClass);
		mv.setViewName("myTheme1/reports/halfYearlyReportInner");

		return mv;
	}

	@GetMapping("/report/finalyearly")
	public ModelAndView finalyearly() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.addObject("testList", testRepository.findByStatus(true));
		mv.addObject("classList", studyClassRepository.findAll());
		mv.addObject("subList", subjectRepository.findByStatus(true));
		List<ClassSectionBO> classSectionList = new ArrayList<>();
		mv.addObject("classSectionList", classSectionList);
		mv.setViewName("myTheme1/reports/finalYearlyReport");

		return mv;
	}

	@RequestMapping(value = "/report/getFinalyearly", method = RequestMethod.GET)
	public ModelAndView getFinalYearly(@RequestParam("classId") StudyClassBO studyClass,
			@RequestParam("section") ClassSectionBO section, @RequestParam("subject") SubjectBO subject) {

		ModelAndView mv = new ModelAndView();

		List<FinalYearlyBO> finalYearly = new ArrayList<>();

		if (studyClass != null && section == null) {
			finalYearly = finalYearlyRepository.findByClassId(studyClass);
		} else if (studyClass != null && section != null) {
			finalYearly = finalYearlyRepository.findByClass(studyClass.getId(), section.getId());
		}

		mv.addObject("studyClass", studyClass);
		mv.addObject("half", finalYearly);
		mv.setViewName("myTheme1/reports/finalYearlyReportInner");
		return mv;
	}

	@GetMapping("/report/periodic")
	public ModelAndView periodic() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.addObject("testList", testRepository.findByStatus(true));
		mv.addObject("classList", studyClassRepository.findAll());
		mv.addObject("subList", subjectRepository.findByStatus(true));
		List<ClassSectionBO> classSectionList = new ArrayList<>();
		mv.addObject("classSectionList", classSectionList);
		mv.setViewName("myTheme1/reports/periodicReport");

		return mv;
	}

	@RequestMapping(value = "/report/getPeriodic", method = RequestMethod.GET)
	public ModelAndView download(@RequestParam("studyClass") StudyClassBO studyClass,
			@RequestParam("section") ClassSectionBO section, @RequestParam("subject") SubjectBO subject,
			HttpServletResponse servletResponse) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		ModelAndView mv = new ModelAndView();
		try {

			List<PeriodicTestBO> periodicTests = new ArrayList<>();

			if (studyClass != null && section == null) {
				periodicTests = periodicTestRepository.findByClassId(studyClass);
			} else if (studyClass != null && section != null) {
				periodicTests = periodicTestRepository.findByClass(studyClass.getId(), section.getId());
			}

			List<List<String>> list = new ArrayList<>();

			for (PeriodicTestBO periodicTest : periodicTests) {
				List<String> objArray = new ArrayList<>();

				objArray.add(periodicTest.getStudent().getStudentNo());
				objArray.add(periodicTest.getStudent().getName());
				String subjectName = subject.getSubjectName();
				if (subjectName.equalsIgnoreCase("MATHEMATICS")) {
					objArray.add(String.valueOf(periodicTest.getMaths()));
					objArray.add(String.valueOf(periodicTest.getMathsPer()));
				} else if (subjectName.equalsIgnoreCase("ENGLISH")) {
					objArray.add(String.valueOf(periodicTest.getEnglish()));
					objArray.add(String.valueOf(periodicTest.getEnglishPer()));
				} else if (subjectName.equalsIgnoreCase("COMPUTERS")) {
					objArray.add(String.valueOf(periodicTest.getComputersTotal()));
					objArray.add(String.valueOf(periodicTest.getComputersPer()));
				} else if (subjectName.equalsIgnoreCase("E.V.S")) {
					objArray.add(String.valueOf(periodicTest.getEvc()));
					objArray.add(String.valueOf(periodicTest.getEvcPer()));
				} else if (subjectName.equalsIgnoreCase("HINDI")) {
					objArray.add(String.valueOf(periodicTest.getHindi()));
					objArray.add(String.valueOf(periodicTest.getHindiPer()));
				}

				list.add(objArray);
			}
			Set<String> columnNames = new LinkedHashSet<String>();
			columnNames.add("Adm No");
			columnNames.add("Name of Student");
			columnNames.add("Marks Obtained");
			columnNames.add("Percentage");

			mv.addObject("studentMarksList", list);
			mv.addObject("studentMarksColList", columnNames);
			mv.setViewName("myTheme1/reports/periodicReportInner");
			return mv;

			/*
			 * Set<String> colNames = columnNames; String dateStr = new
			 * SimpleDateFormat("dd-MMM-yyyy").format(new Date()); String reportName
			 * ="PERIODIC TEST"; SXSSFWorkbook wb = new SXSSFWorkbook(100);
			 * ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 * 
			 * ExcelView.buildExcelDocument(list, colNames, wb, request, servletResponse,
			 * reportName, null, dateStr);
			 * 
			 * try { wb.write(bos); } finally { bos.close(); }
			 * 
			 * byte[] bytes = bos.toByteArray();
			 * 
			 * HttpHeaders headers = new HttpHeaders();
			 * 
			 * headers.set("Content-Type", "application/vnd.ms-excel;");
			 * headers.set("content-length", Integer.toString(bytes.length));
			 * headers.set("Content-Disposition", "attachment; filename=" + reportName + "_"
			 * + new Date().getTime() + ".xlsx");
			 * 
			 * return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/report/subjectWiseHalfYearly")
	public ModelAndView subjectHalfYear() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.addObject("testList", testRepository.findByStatus(true));
		mv.addObject("subList", subjectRepository.findByStatus(true));
		mv.addObject("classList", studyClassRepository.findAll());
		List<ClassSectionBO> classSectionList = new ArrayList<>();
		mv.addObject("classSectionList", classSectionList);
		mv.setViewName("myTheme1/reports/subjectWiseHalfYearlyReport");

		return mv;
	}

	@RequestMapping(value = "/report/getSubjectWiseHalfYearly", method = RequestMethod.GET)
	public ModelAndView downloadSubject(@RequestParam("studyClass") StudyClassBO studyClass,
			@RequestParam("section") ClassSectionBO section, @RequestParam("subject") SubjectBO subject,
			HttpServletResponse servletResponse) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		ModelAndView mv = new ModelAndView();
		try {

			List<HalfYearlyBO> halfYearlys = new ArrayList<>();

			if (studyClass != null && section == null) {
				halfYearlys = halfYearlyRepository.findByClassId(studyClass);
			} else if (studyClass != null && section != null) {
				halfYearlys = halfYearlyRepository.findByClass(studyClass.getId(), section.getId());
			}

			List<List<String>> list = new ArrayList<>();

			for (HalfYearlyBO halfYearly : halfYearlys) {
				List<String> objArray = new ArrayList<>();

				objArray.add(halfYearly.getStudent().getStudentNo());
				objArray.add(halfYearly.getStudent().getName());

				String subjectName = subject.getSubjectName();
				if (subjectName.equalsIgnoreCase("MATHEMATICS")) {
					objArray.add(String.valueOf(halfYearly.getMathsPt()));
					objArray.add(String.valueOf(halfYearly.getMathsNb()));
					objArray.add(String.valueOf(halfYearly.getMathsPro()));
					objArray.add(String.valueOf(halfYearly.getMaths()));
					objArray.add(String.valueOf(halfYearly.getMathsTotal()));
				} else if (subjectName.equalsIgnoreCase("ENGLISH")) {
					objArray.add(String.valueOf(halfYearly.getEnglishPt()));
					objArray.add(String.valueOf(halfYearly.getEnglishNb()));
					objArray.add(String.valueOf(halfYearly.getEnglishPro()));
					objArray.add(String.valueOf(halfYearly.getEnglish()));
					objArray.add(String.valueOf(halfYearly.getEnglishTotal()));
				} else if (subjectName.equalsIgnoreCase("COMPUTERS")) {
					objArray.add(String.valueOf(halfYearly.getComputersPt()));
					objArray.add(String.valueOf(halfYearly.getComputersNb()));
					objArray.add(String.valueOf(halfYearly.getComputersPro()));
					objArray.add(String.valueOf(halfYearly.getComputers()));
					objArray.add(String.valueOf(halfYearly.getComputersTotal()));
				} else if (subjectName.equalsIgnoreCase("E.V.S")) {
					objArray.add(String.valueOf(halfYearly.getEvcPt()));
					objArray.add(String.valueOf(halfYearly.getEvcNb()));
					objArray.add(String.valueOf(halfYearly.getEvcPro()));
					objArray.add(String.valueOf(halfYearly.getEvc()));
					objArray.add(String.valueOf(halfYearly.getEvcTotal()));
				} else if (subjectName.equalsIgnoreCase("HINDI")) {
					objArray.add(String.valueOf(halfYearly.getHindiPt()));
					objArray.add(String.valueOf(halfYearly.getHindiNb()));
					objArray.add(String.valueOf(halfYearly.getHindiPro()));
					objArray.add(String.valueOf(halfYearly.getHindi()));
					objArray.add(String.valueOf(halfYearly.getHindiTotal()));
				}
				list.add(objArray);
			}
			Set<String> columnNames = new LinkedHashSet<String>();
			columnNames.add("Adm No");
			columnNames.add("Name of Student");

			// columnNames.add("Marks Obtained");
			columnNames.add("PERIODIC TEST");
			columnNames.add("NOTE BOOK");
			columnNames.add("PROJECT");
			columnNames.add(subject.getSubjectName());
			columnNames.add("TOTAL");

			/*
			 * Set<String> colNames = columnNames; String dateStr = new
			 * SimpleDateFormat("dd-MMM-yyyy").format(new Date()); String reportName
			 * ="HALF YEARLY"; SXSSFWorkbook wb = new SXSSFWorkbook(100);
			 * ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 * 
			 * ExcelView.buildExcelDocumentHalf(list, colNames, wb, request,
			 * servletResponse, reportName, null, dateStr);
			 * 
			 * try { wb.write(bos); } finally { bos.close(); }
			 * 
			 * byte[] bytes = bos.toByteArray();
			 * 
			 * HttpHeaders headers = new HttpHeaders();
			 * 
			 * headers.set("Content-Type", "application/vnd.ms-excel;");
			 * headers.set("content-length", Integer.toString(bytes.length));
			 * headers.set("Content-Disposition", "attachment; filename=" + reportName + "_"
			 * + new Date().getTime() + ".xlsx");
			 * 
			 * return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
			 */

			mv.addObject("studentMarksList", list);
			mv.addObject("studentMarksColList", columnNames);
			mv.setViewName("myTheme1/reports/subjectWiseHalfYearlyReportInner");

			return mv;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/report/getSubjectWiseHalfYearly1", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadSubject1(@RequestParam("studyClass") StudyClassBO studyClass,
			@RequestParam("section") ClassSectionBO section, @RequestParam("subject") SubjectBO subject,
			HttpServletResponse servletResponse) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		try {

			List<HalfYearlyBO> halfYearlys = halfYearlyRepository.findByClassId(studyClass);
			List<Object[]> list = new ArrayList<>();

			for (HalfYearlyBO halfYearly : halfYearlys) {
				Object[] objArray = new Object[10];

				objArray[0] = halfYearly.getStudent().getStudentNo();
				objArray[1] = halfYearly.getStudent().getName();

				String subjectName = subject.getSubjectName();
				if (subjectName.equalsIgnoreCase("MATHEMATICS")) {
					objArray[2] = halfYearly.getMathsPt();
					objArray[3] = halfYearly.getMathsNb();
					objArray[4] = halfYearly.getMathsPro();
					objArray[5] = halfYearly.getMaths();
					objArray[6] = halfYearly.getMathsTotal();
				} else if (subjectName.equalsIgnoreCase("ENGLISH")) {
					objArray[2] = halfYearly.getEnglishPt();
					objArray[3] = halfYearly.getEnglishNb();
					objArray[4] = halfYearly.getEnglishPro();
					objArray[5] = halfYearly.getEnglish();
					objArray[6] = halfYearly.getEnglishTotal();
				} else if (subjectName.equalsIgnoreCase("COMPUTERS")) {
					objArray[2] = halfYearly.getComputersPt();
					objArray[3] = halfYearly.getComputersNb();
					objArray[4] = halfYearly.getComputersPro();
					objArray[5] = halfYearly.getComputers();
					objArray[6] = halfYearly.getComputersTotal();
				} else if (subjectName.equalsIgnoreCase("E.V.S")) {
					objArray[2] = halfYearly.getEvcPt();
					objArray[3] = halfYearly.getEvcNb();
					objArray[4] = halfYearly.getEvcPro();
					objArray[5] = halfYearly.getEvc();
					objArray[6] = halfYearly.getEvcTotal();
				} else if (subjectName.equalsIgnoreCase("HINDI")) {
					objArray[2] = halfYearly.getHindiPt();
					objArray[3] = halfYearly.getHindiNb();
					objArray[4] = halfYearly.getHindiPro();
					objArray[5] = halfYearly.getHindi();
					objArray[6] = halfYearly.getHindiTotal();
				}

				list.add(objArray);
			}
			Set<String> columnNames = new LinkedHashSet<String>();
			columnNames.add("Adm No");
			columnNames.add("Name of Student");
			columnNames.add("Marks Obtained");
			columnNames.add("PERIODIC TEST");
			columnNames.add("NOTE BOOK");
			columnNames.add("PROJECT");
			columnNames.add(subject.getSubjectName());
			columnNames.add("TOTAL");

			Set<String> colNames = columnNames;
			String dateStr = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			String reportName = "HALF YEARLY";
			SXSSFWorkbook wb = new SXSSFWorkbook(100);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ExcelView.buildExcelDocumentHalf(list, colNames, wb, request, servletResponse, reportName, subject,
					dateStr);

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

	@RequestMapping(value = "/report/getPeriodic1", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download1(@RequestParam("studyClass") StudyClassBO studyClass,
			@RequestParam("section") ClassSectionBO section, @RequestParam("subject") SubjectBO subject,
			HttpServletResponse servletResponse) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		try {

			List<PeriodicTestBO> periodicTests = new ArrayList<>();

			if (studyClass != null && section == null) {
				periodicTests = periodicTestRepository.findByClassId(studyClass);
			} else if (studyClass != null && section != null) {
				periodicTests = periodicTestRepository.findByClass(studyClass.getId(), section.getId());
			}

			List<Object[]> list = new ArrayList<>();

			long hightMarks = 0;
			double percenage = 0;
			String subjectName = subject.getSubjectName();
			if (subjectName.equalsIgnoreCase("MATHEMATICS") && periodicTests!=null && periodicTests.size()>0) {
				hightMarks = periodicTests.stream().filter(a->a.getMaths()!=null) .max(Comparator.comparing( PeriodicTestBO::getMaths)).get().getMaths();
				percenage =  periodicTests.stream().filter(a->a.getMathsPer()!=null) .max(Comparator.comparing( PeriodicTestBO::getMathsPer)).get().getMathsPer();
			} else if (subjectName.equalsIgnoreCase("ENGLISH") && periodicTests!=null && periodicTests.size()>0) {
				hightMarks = periodicTests.stream().filter(a->a.getEnglish()!=null) .max(Comparator.comparing( PeriodicTestBO::getEnglish)).get().getEnglish();
				percenage =  periodicTests.stream().filter(a->a.getEnglishPer()!=null) .max(Comparator.comparing( PeriodicTestBO::getEnglishPer)).get().getEnglishPer();
			} else if (subjectName.equalsIgnoreCase("COMPUTERS") && periodicTests!=null && periodicTests.size()>0) {
				hightMarks = periodicTests.stream().filter(a->a.getComputersTotal()!=null) .max(Comparator.comparing( PeriodicTestBO::getComputersTotal)).get().getComputersTotal();
				percenage =  periodicTests.stream().filter(a->a.getComputersPer()!=null) .max(Comparator.comparing( PeriodicTestBO::getComputersPer)).get().getComputersPer();
			} else if (subjectName.equalsIgnoreCase("E.V.S") && periodicTests!=null && periodicTests.size()>0) {
				hightMarks = periodicTests.stream().filter(a->a.getEvc()!=null) .max(Comparator.comparing( PeriodicTestBO::getEvc)).get().getEvc();
				percenage =  periodicTests.stream().filter(a->a.getEvcPer()!=null) .max(Comparator.comparing( PeriodicTestBO::getEvcPer)).get().getEvcPer();
			} else if (subjectName.equalsIgnoreCase("HINDI") && periodicTests!=null && periodicTests.size()>0) {
				hightMarks = periodicTests.stream().filter(a->a.getHindi()!=null) .max(Comparator.comparing( PeriodicTestBO::getHindi)).get().getHindi();
				percenage =  periodicTests.stream().filter(a->a.getHindiPer()!=null) .max(Comparator.comparing( PeriodicTestBO::getHindiPer)).get().getHindiPer();
			} else if (subjectName.equalsIgnoreCase("PHYSICS") && periodicTests!=null && periodicTests.size()>0) {
				hightMarks = periodicTests.stream().filter(a->a.getPhysics()!=null) .max(Comparator.comparing( PeriodicTestBO::getPhysics)).get().getPhysics();
				percenage =  periodicTests.stream().filter(a->a.getPhysicsPer()!=null) .max(Comparator.comparing( PeriodicTestBO::getPhysicsPer)).get().getPhysicsPer();
			} else if (subjectName.equalsIgnoreCase("CHEMISTRY") && periodicTests!=null && periodicTests.size()>0) {
				hightMarks = periodicTests.stream().filter(a->a.getChemistry()!=null) .max(Comparator.comparing( PeriodicTestBO::getChemistry)).get().getChemistry();
				percenage =  periodicTests.stream().filter(a->a.getChemistryPer()!=null) .max(Comparator.comparing( PeriodicTestBO::getChemistryPer)).get().getChemistryPer();
			}
			for (PeriodicTestBO periodicTest : periodicTests) {
				Object[] objArray = new Object[10];

				objArray[0] = periodicTest.getStudent().getStudentNo();
				objArray[1] = periodicTest.getStudent().getName();
				if (subjectName.equalsIgnoreCase("MATHEMATICS")) {
					objArray[2] = periodicTest.getMaths();
					objArray[3] = periodicTest.getMathsPer();
				} else if (subjectName.equalsIgnoreCase("ENGLISH")) {
					objArray[2] = periodicTest.getEnglish();
					objArray[3] = periodicTest.getEnglishPer();
				} else if (subjectName.equalsIgnoreCase("COMPUTERS")) {
					objArray[2] = periodicTest.getComputersTotal();
					objArray[3] = periodicTest.getComputersPer();
				} else if (subjectName.equalsIgnoreCase("E.V.S")) {
					objArray[2] = periodicTest.getEvc();
					objArray[3] = periodicTest.getEvcPer();
				} else if (subjectName.equalsIgnoreCase("HINDI")) {
					objArray[2] = periodicTest.getHindi();
					objArray[3] = periodicTest.getHindiPer();
				}else if (subjectName.equalsIgnoreCase("PHYSICS")) {
					objArray[2] = periodicTest.getPhysics();
					objArray[3] = periodicTest.getPhysicsPer();
				}else if (subjectName.equalsIgnoreCase("CHEMISTRY")) {
					objArray[2] = periodicTest.getChemistry();
					objArray[3] = periodicTest.getChemistryPer();
				}

				list.add(objArray);
			}
			Set<String> columnNames = new LinkedHashSet<String>();
			columnNames.add("Adm No");
			columnNames.add("Name of Student");
			columnNames.add("Marks Obtained");
			columnNames.add("Percentage");

			Set<String> colNames = columnNames;
			String dateStr = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			String reportName = "PERIODIC TEST";
			SXSSFWorkbook wb = new SXSSFWorkbook(100);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ExcelView.buildExcelDocument(list, colNames, wb, request, servletResponse, reportName, subject, dateStr,
					section, studyClass, hightMarks, percenage);

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

}
