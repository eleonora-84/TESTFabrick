package com.test.fabrick.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InvalidAmountException extends Exception {
	 
		private static final long serialVersionUID = 4L;

		private static final Logger logger = LogManager.getLogger(InvalidAccountIdException.class);
		
		public InvalidAmountException(String message) {
	        super(message);
			logger.error("Importo non valido.");
	    }
}
