package scaits.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import scaits.util.EmployeeAction;

/**
 * @author mahesh.g 23-Mar-2018 10:18:45 AM
 */
@Component
public class LoginController  extends HandlerInterceptorAdapter {


	@Autowired
	private EmployeeAction employeeAction;


	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/")
	public String root() {
		ModelAndView mv = new ModelAndView();
			mv.setViewName("myTheme1/pageContent");
			mv.addObject("currentUser", employeeAction.getCurrentUser());
			return "myTheme1/pageContent";
	}

	@GetMapping("/login")
	public ModelAndView login1() {
		ModelAndView mv = new ModelAndView();
		String hdrs = request.getHeader("referer");
		if (hdrs != null && hdrs.contains("/studentLogin")) {
			mv.addObject("currentUser", null);
			mv.setViewName("student/studentLogin");
		} else {
			mv.addObject("currentUser", employeeAction.getCurrentUser());
			mv.setViewName("myTheme1/loginPage");
		}

		return mv;
	}

	@RequestMapping("/logout")
	public String logout() {
		return "myTheme1/loginPage";
	}


	@GetMapping("/qb/employeeProfile")
	public String employeeProfile() {
		return "myTheme1/employeeProfile";
	}

	/*@GetMapping("/lockscreen")
	public ModelAndView lockscreen() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("currentUser", employeeAction.getCurrentUser());
		mv.setViewName("myTheme1/lockscreen");
		return mv;
	}*/


	@RequestMapping("/studentlogout")
	public String studentLogout() {
		return "student/studentLogin";
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
		mv.setViewName("student/studentDashboard");
		return mv;
	}
	
	
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("preHandle");

		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("postHandle");
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(
			HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("afterConcurrentHandlingStarted");
	}
	
	
	


}
