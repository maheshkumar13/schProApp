package scaits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import scaits.repository.AcademicRepository;
import scaits.repository.EmployeeRepository;
import scaits.util.EmployeeAction;

	@Controller
	@Scope(value = "request")
	public class ApplicationConroller {
		
		
		@Autowired
		private EmployeeAction employeeAction;

		@Autowired
		EmployeeRepository employeeRepository;
		
		@Autowired
		AcademicRepository academicRepository;
		
		
	
		@GetMapping("/application/applicationSales")
		public ModelAndView lockpage() {
			ModelAndView mv = new ModelAndView();
			mv.addObject("currentUser", employeeAction.getCurrentUser());
			mv.addObject("academicList", academicRepository.findByReceiptStatus("Y"));
			
			mv.setViewName("myTheme1/appSales/applicationSale");

			return mv;
		}
		

}
