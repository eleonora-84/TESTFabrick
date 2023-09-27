package com.test.fabrick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class FabrickApplication {
    private static final Logger logger = LogManager.getLogger(FabrickApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FabrickApplication.class, args);
		logger.info("Application Started");
		
	}

}
