package com.test.fabrick;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.fabrick.controllers.TestRestController;
import com.test.fabrick.exceptions.InvalidAccountIdException;
import com.test.fabrick.exceptions.InvalidAmountException;
import com.test.fabrick.exceptions.InvalidCurrencyException;
import com.test.fabrick.exceptions.InvalidDateException;
import com.test.fabrick.model.Transaction;
import com.test.fabrick.model.TransferData;
import com.test.fabrick.model.transfer.Creditor;
import com.test.fabrick.services.FabrickService;
import com.test.fabrick.services.FabrickApiClient;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FabrickApplicationTests {

    @InjectMocks
    private TestRestController testRestController;

    @Mock
    private FabrickService fabrickService;

    @Mock
    private FabrickApiClient fabrickApiClient;
    
	private static final Logger logger = LogManager.getLogger(TestRestController.class);

    @Test
    void balanceTest() throws InvalidAccountIdException {
    	logger.info("Test del metodo getBalance()");
    	when(fabrickService.getBalance(anyLong())).thenReturn(20.0f);

        float balance = (float) testRestController.getBalance(12345678L).getBody();
        assertEquals(20.0f, balance, 0.01f);
    }
    
    @Test
    void testGetTransactionsList() throws InvalidAccountIdException, InvalidDateException {
    	logger.info("Test del metodo getTransactionsList()");
        Long accountId = 12345678L;
        String from = "2023-09-01";
        String to = "2023-09-30";

        List<Transaction> resultTransactions = fabrickService.getTransactionsList(accountId, from, to);

        assertNotNull(resultTransactions);
    }
    @Test
    void testTransfer() throws JsonProcessingException, InvalidAccountIdException, InvalidCurrencyException, InvalidAmountException {
    	logger.info("Test del metodo testTransfer()");
    	TransferData transferData = new TransferData();
    	transferData.setAccountId(12345678L);
    	transferData.setCreditor(new Creditor());
    	transferData.getCreditor().setName("John Doe");
    	transferData.setDescription("Payment invoice 75/2017");
    	transferData.setCurrency("EUR");
    	transferData.setAmount(100.00f);
    	transferData.setExecutionDate(LocalDate.of(2023,9,20));
        when(fabrickService.transfer(any(TransferData.class))).thenReturn(transferData);
        TransferData resultTransferData = (TransferData) testRestController.transfer(transferData).getBody();
        assertEquals(transferData, resultTransferData);
    }
}
