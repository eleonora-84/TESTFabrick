package com.test.fabrick.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.test.fabrick.model.Transaction;
import com.test.fabrick.model.TransferData;
import com.test.fabrick.services.FabrickApiClient;
import com.test.fabrick.services.FabrickService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/fabrick")
public class TestRestController {
	
	private static final Logger logger = LogManager.getLogger(TestRestController.class);

	@Autowired
    FabrickApiClient fabrickApiClient;
	
	@Autowired
	FabrickService fabrickService;

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<?> getBalance(@PathVariable Long accountId) {
        try {
            float balance = fabrickService.getBalance(accountId);
            logger.info("Richiesto il saldo del conto corrente con id: " + accountId + ".");
            return ResponseEntity.ok(balance);
        } catch (Exception e) {
            logger.error("Errore: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore nella richiesta di saldo.");
        }
    }
    
    @GetMapping("/transactionsList/{accountId}/{from}/{to}")
    public ResponseEntity<?> getTransactionsList(@PathVariable Long accountId, @PathVariable String from, @PathVariable String to) {
        try {
            List<Transaction> transactionList = fabrickService.getTransactionsList(accountId, from, to);
            logger.info("Richiesto elenco transazioni del conto corrente con id: " + accountId + " dal " + fabrickService.transformDate(from) + " al " + fabrickService.transformDate(to) + ".");
            return ResponseEntity.ok(transactionList);
        } catch (Exception e) {
            logger.error("Errore: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore nella richiesta di elenco transazioni.");
        }
    }
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferData data){

        logger.info("Richiesto bonifico.");
    	try {
    		TransferData transfer = fabrickService.transfer(data);
            logger.info("La richiesta di bonifico è andata a buon fine.");
    		return ResponseEntity.ok(transfer);  
    	} catch (Exception e) {
        	logger.error("Errore nella richiesta di bonifico: " + e.getMessage());
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore nella richiesta di bonifico.");
        }
    }    

}
