package com.spring.exception;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SpringException extends Exception {
	private static final Logger logger = Logger.getLogger(SpringException.class.getName());
	private static final long serialVersionUID = 1L;
	private String exceptionMsg;

	public SpringException(String exceptionMsg) {
		logger.info(" EXCEPTION HANDLING");
		this.exceptionMsg = exceptionMsg;
	}

//	@ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.info("##################Returning HTTP 400 Bad Request" + e);
        throw e;
    }
	
	public String getExceptionMsg() {
		return this.exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}