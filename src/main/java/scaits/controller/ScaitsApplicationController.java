package scaits.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import scaits.bo.employee.EmployeeBO;
import scaits.repository.EmployeeRepository;
import scaits.util.EmployeeAction;
import scaits.util.StringUtil;

/**
 * @author mahesh.g 23-Mar-2018 10:18:45 AM
 */
@Controller
@Scope(value = "request")
public class ScaitsApplicationController {


	@Autowired
	private EmployeeAction employeeAction;

	@Autowired
	EmployeeRepository userRepository;

	int monthVal=0;
	int year=0;

	@RequestMapping("/")
	public String root() {

		ModelAndView mv = new ModelAndView();
		try {
			System.out.println("student page lunched");
				mv.setViewName("myTheme1/pageContent");
				mv.addObject("currentUser", employeeAction.getCurrentUser());
				return "myTheme1/pageContent";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/scaits")
	public String scaits() {

		ModelAndView mv = new ModelAndView();
		try {
			System.out.println("scaits page lunched");
				mv.setViewName("myTheme1/pageContent");
				mv.addObject("currentUser", employeeAction.getCurrentUser());
				return "myTheme1/scaitsDashboard";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@GetMapping("/login")
	public ModelAndView login1() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.setViewName("myTheme1/loginPage");

		return mv;
	}

	@GetMapping("/lockpage")
	public ModelAndView lockpage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.setViewName("myTheme1/lockscreen");

		return mv;
	}

	@PostMapping("/locklogin")
	public ModelAndView lockLogin(String username, String password) {

		ModelAndView mv = new ModelAndView();
		if (password != null && password.length() > 0) {
			password = StringUtil.getMD5Code(password);
		}
		EmployeeBO user = userRepository.findByPayRollIdAndPassWordAndStatus(username, password, "CURRENT");
		if (user == null) {
			try {
				mv.setViewName("myTheme1/lockscreen");
				mv.addObject("currentUser", employeeAction.getCurrentUser());
				mv.addObject("exceprionStr", "username or password");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			try {
				mv.setViewName("myTheme1/scaitsDashboard");
				mv.addObject("currentUser", employeeAction.getCurrentUser());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mv;

	}

	@GetMapping("/studentLogin")
	public ModelAndView studentLogin1() {
		ModelAndView mv = new ModelAndView();
		try {
			System.out.println("student page login");

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			String clientId = auth.getName();

			if (clientId != null && !clientId.equalsIgnoreCase("anonymousUser")) {
				mv.setViewName("student/studentDashboard");
				return mv;
			}

			mv.addObject("currentUser", null);
			mv.setViewName("student/studentLogin");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		if (employeeAction.getCurrentUser() != null) {

			employeeAction.currentUser = null;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(request, response, auth);
			}

			return "redirect:/login";
		} 
		return null;

	}

	@RequestMapping("/audio/{uuid}")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getTmp(@PathVariable("uuid") String uuid) throws IOException {

		Path path = Paths.get("mobileapps.scaits.info/audio//2018/04/05/54968_4_15229058540231_ring.mp3");
		String contentType = Files.probeContentType(path);
		FileSystemResource file = new FileSystemResource(
				new File("http:/mobileapps.scaits.info/audio//2018/04/05/54968_4_15229058540231_ring.mp3"));
		return ResponseEntity.ok().contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType(contentType))
				.body(new InputStreamResource(file.getInputStream()));
	}
	
	@GetMapping("/qb/employeeProfile")
	public ModelAndView employeeProfile() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());

		EmployeeBO empval = userRepository.findByPayRollId(employeeAction.getCurrentUser().getPayRollId());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1); // to get previous year add -1
		Date previousYear = cal.getTime();

		

		Map<Integer, String> datesInMonth = new LinkedHashMap<Integer, String>();

		float noOfPresents = 0;
		float noOfAbsents = 0;
		float noOfLeaves = 0;
		float noOfHolidays = 0;
		float noOfWeekOffs = 0;
		float noOfHalfDays = 0;

		Calendar c = Calendar.getInstance();
		if(monthVal==0){
			monthVal = c.get(Calendar.MONTH) + 1;
			year = c.get(Calendar.YEAR);
		}else{
			year = c.get(Calendar.YEAR);
		}
		
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			Date d1 = null;
			Date d2 = null;
			if (monthVal == 1) {
				d1 = formatter.parse((year - 1) + "-" + (12) + "-26");
				d2 = formatter.parse((year) + "-" + monthVal + "-25");
			} else {
				d1 = formatter.parse(year + "-" + (monthVal - 1) + "-26");
				d2 = formatter.parse(year + "-" + monthVal + "-25");
			}

			Calendar c1 = Calendar.getInstance();
			c1.setTime(d1);

			Calendar c2 = Calendar.getInstance();
			c2.setTime(d2);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("datesInMonth", datesInMonth);

		mv.addObject("noOfPresents", noOfPresents);
		mv.addObject("noOfAbsents", noOfAbsents);
		mv.addObject("noOfLeaves", noOfLeaves);
		mv.addObject("noOfHolidays", noOfHolidays);
		mv.addObject("noOfWeekOffs", noOfWeekOffs);
		mv.addObject("noOfHalfDays", noOfHalfDays);
		

		mv.setViewName("myTheme1/employeeProfile");
		return mv;
	}
	
	public List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	@RequestMapping(value = "/qb/getEmpAtdReport")
	public ModelAndView getEmpAttendanceReport(@RequestParam("monthVal") String selMonthVal) {
		Map<Integer, String> datesInMonth = new LinkedHashMap<Integer, String>();

		float noOfPresents = 0;
		float noOfAbsents = 0;
		float noOfLeaves = 0;
		float noOfHolidays = 0;
		float noOfWeekOffs = 0;
		float noOfHalfDays = 0;
		if(selMonthVal!=null && selMonthVal.length()>0){
			monthVal=Integer.valueOf(selMonthVal);	
		}
		Calendar c = Calendar.getInstance();
		if(monthVal==0){
			monthVal = c.get(Calendar.MONTH) + 1;
			year = c.get(Calendar.YEAR);
		}else{
			year = c.get(Calendar.YEAR);
		}
		
		
		try {} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("datesInMonth", datesInMonth);

		mv.addObject("noOfPresents", noOfPresents);
		mv.addObject("noOfAbsents", noOfAbsents);
		mv.addObject("noOfLeaves", noOfLeaves);
		mv.addObject("noOfHolidays", noOfHolidays);
		mv.addObject("noOfWeekOffs", noOfWeekOffs);
		mv.addObject("noOfHalfDays", noOfHalfDays);
		
		mv.setViewName("myTheme1/empAttendanceReport");

		return mv;

	}

	@GetMapping("/lockscreen")
	public ModelAndView lockscreen() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.setViewName("myTheme1/lockscreen");
		return mv;
	}

	@GetMapping("/qb/conceptWiseTestDetails")
	public String conceptWiseTestDetails() {
		return "myTheme1/conceptWiseTestDetails";
	}

	@GetMapping("/student")
	public String studentLogin(@RequestParam String username, @RequestParam String password,
			@RequestParam String loginType) {
		ModelAndView mv = new ModelAndView();
		System.out.println(username + "," + password + "," + loginType);
		mv.setViewName("student/studentDashboard");
		return "redirect:/student/dashboard";
	}

	@GetMapping("/student/profile")
	public ModelAndView studentProfile() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("student/studentProfile");
		return mv;
	}

	@GetMapping("/student/dashboard")
	public ModelAndView studentLogin() {
		ModelAndView mv = new ModelAndView();
		try {
			System.out.println("student page dashboard");

			List<String> categories = new ArrayList<String>();
			List<String> offlineCategories = new ArrayList<String>();
			List<String> onlineCategories = new ArrayList<String>();


			categories.add("offlineExams");
			categories.add("onlineExams");

			List<Object> data = new ArrayList<Object>();

			List<Object> offlineExamData = new ArrayList<Object>();
			List<Object> onlineExamData = new ArrayList<Object>();

			mv.setViewName("student/studentDashboard");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

}
