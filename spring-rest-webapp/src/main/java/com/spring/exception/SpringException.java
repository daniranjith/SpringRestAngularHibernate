package com.spring.exception;

import java.util.logging.Logger;

public class SpringException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SpringException2.class.getName());
	private String exceptionMsg;

	public SpringException(String exceptionMsg) {
		logger.info(" EXCEPTION HANDLING ");
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		logger.info("getExceptionMsg = " +exceptionMsg);
		return this.exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		logger.info("getExceptionMsg = " +exceptionMsg);
		this.exceptionMsg = exceptionMsg;
	}
}