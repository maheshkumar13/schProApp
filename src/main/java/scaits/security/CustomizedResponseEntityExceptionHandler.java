package scaits.security;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler  {
	
	ExceptionResponse exceptionResponse;
	String httpStatus;

  @ExceptionHandler(Exception.class)
  public final ModelAndView handleAllExceptions(Exception ex, WebRequest request) {
    exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    httpStatus=HttpStatus.INTERNAL_SERVER_ERROR.toString();
    
    
    ModelAndView mv = new ModelAndView();
    mv.addObject("errorMessage", ex.toString());
    mv.addObject("errorStatus", httpStatus);
		mv.setViewName("error");
		return mv;
		
		
  }
  
  @ExceptionHandler(AccessDeniedException.class)
  public final ModelAndView handleAllExceptions(AccessDeniedException ex, WebRequest request) {
	  exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
		        request.getDescription(false));
	  httpStatus=HttpStatus.INTERNAL_SERVER_ERROR.toString();
	  ModelAndView mv = new ModelAndView();
	    mv.addObject("errorMessage", exceptionResponse.getMessage());
	    mv.addObject("errorStatus", httpStatus);
			mv.setViewName("error");
			return mv;
  }
  
  
  public ModelAndView handleError(HttpServletRequest request) {
      Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
      
      Object status1 = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
      
      request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
      
      ModelAndView mv = new ModelAndView();
      if (status != null) {
          Integer statusCode = Integer.valueOf(status.toString());
      }
      mv.addObject("errorMessage", status1.toString());
      mv.addObject("errorStatus", status.toString());
		mv.setViewName("error");
		return mv;
  }



}