package com.spring.exception;

import java.util.logging.Logger;

import org.springframework.http.converter.HttpMessageNotReadableException;

public class SpringException2 extends Exception {
	private static final Logger logger = Logger.getLogger(SpringException2.class.getName());
	private static final long serialVersionUID = 1L;
	private String exceptionMsg;

	public SpringException2(String exceptionMsg) {
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