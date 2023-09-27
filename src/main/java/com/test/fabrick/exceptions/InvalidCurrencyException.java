package com.test.fabrick.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidCurrencyException extends Exception {
	private static final long serialVersionUID = 3L;

	private static final Logger logger = LogManager.getLogger(InvalidCurrencyException.class);
	
	public InvalidCurrencyException(String message) {
        super(message);
		logger.error("Valuta non valida.");
    }
}
