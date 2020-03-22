package com.bank.ctrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bank.ex.InvalidAccountDetailsException;
import com.bank.ex.NotImplementedException;

@ControllerAdvice
public class WebAdvice {
	
	private Logger logger = LoggerFactory.getLogger(WebAdvice.class);
	
	@ResponseBody                                      
	@ExceptionHandler(NotImplementedException.class)  
	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)               
	String pathNotImplementedHandler(NotImplementedException e) {
		logger.info(e.getMessage(), e);
		return e.getMessage();
	}
	
	
	@ResponseBody                                      
	@ExceptionHandler(InvalidAccountDetailsException.class)  
	@ResponseStatus(HttpStatus.BAD_REQUEST)               
	String invalidAccountDetailsHandler(InvalidAccountDetailsException e) {
		logger.info(e.getMessage(), e);
		return e.getMessage();
	}
	
	
}

