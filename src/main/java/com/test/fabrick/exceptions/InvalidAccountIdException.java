package com.test.fabrick.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidAccountIdException extends Exception {
 
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(InvalidAccountIdException.class);
	
	public InvalidAccountIdException(String message) {
        super(message);
		logger.error("ID dell'account non valido.");
    }
}


