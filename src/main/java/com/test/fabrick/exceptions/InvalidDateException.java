package com.test.fabrick.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidDateException  extends Exception {
	
	private static final long serialVersionUID = 2L;
	private static final Logger logger = LogManager.getLogger(InvalidDateException.class);
	
	public InvalidDateException(String message) {
        super(message);
		logger.error("Formato date errato.");
    }
}
