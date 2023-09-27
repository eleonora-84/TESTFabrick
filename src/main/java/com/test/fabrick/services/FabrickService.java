package com.test.fabrick.services;

import java.util.Currency;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.fabrick.exceptions.InvalidAccountIdException;
import com.test.fabrick.exceptions.InvalidAmountException;
import com.test.fabrick.exceptions.InvalidCurrencyException;
import com.test.fabrick.exceptions.InvalidDateException;
import com.test.fabrick.model.AccountResponse;
import com.test.fabrick.model.Transaction;
import com.test.fabrick.model.TransactionResponse;
import com.test.fabrick.model.TransferData;
import com.test.fabrick.model.TransferResponse;

@Service
public class FabrickService {

	@Autowired
	FabrickApiClient fabrickApiClient;
	@Autowired
	AccountResponse accountResponse;
	@Autowired
	TransactionResponse transactionResponse;
	@Autowired
	TransferResponse transferResponse;
	
	public boolean isAccountIdValid(long accountId) {
		String accountIdString = String.valueOf(accountId);
	    if (accountIdString.length() != 8) {
	        return false;
	    } else {
	    	return true;
	    }
	}

	
	private boolean isDateValid(String date) {
		String dateString = String.valueOf(date);
		if(dateString.length() != 8) {
			return false;
		} else {
			return true;
		}
	}
	
	public String transformDate(String date) {
		String year = date.substring(0, 4);
	    String month = date.substring(4, 6);
	    String day = date.substring(6, 8);
	    return year + "-" + month + "-" + day;
	}
	
	private boolean isCurrencyValid(String currency) {
        return Currency.getAvailableCurrencies().stream()
        .map(Currency::getCurrencyCode)
        .anyMatch(Predicate.isEqual(currency));
	}
	
	public boolean isAmountValid(TransferData data) throws InvalidAccountIdException { 
		   float balance = getBalance(data.getAccountId());
		   float amount = data.getAmount();

		    if (balance >= amount) {
		        return true;
		    } else {
		        return false;
		    }
	}
	
	public float getBalance(long accountId) throws InvalidAccountIdException {
		if (isAccountIdValid(accountId)) {
		accountResponse = fabrickApiClient.getBalance(accountId).getBody();
		return accountResponse.getPayload().getBalance();
	    } else {
	        throw new InvalidAccountIdException("Errore nella richiesta di saldo.");
	    }
	}

	public List<Transaction> getTransactionsList(Long accountId, String from, String to) throws InvalidAccountIdException, InvalidDateException{
		if (isDateValid(from) && isDateValid(to)) {
			if (isAccountIdValid(accountId)) {
			transactionResponse = fabrickApiClient.getTransactionsList(accountId, transformDate(from), transformDate(to)).getBody();
			return transactionResponse.getPayload().getList();
			} else {
		        throw new InvalidAccountIdException("ID dell'account non valido: " + accountId);
			}
		} else {
			throw new InvalidDateException("Formato date non valido  " + from + " " + to);
		}
	}
	
	public TransferData transfer(TransferData data) throws InvalidAccountIdException, InvalidCurrencyException, InvalidAmountException, JsonProcessingException {
		if(isCurrencyValid(data.getCurrency())) {
			if(isAccountIdValid(data.getAccountId())) {
				if(isAmountValid(data)){
					transferResponse = fabrickApiClient.transfer(data).getBody();
					return transferResponse.getPayload();
				} else {
					throw new InvalidAmountException ("Importo non valido.");
				}
				
			} else {
		        throw new InvalidAccountIdException("ID dell'account non valido: " + data.getAccountId());
			}
		} else {
			throw new InvalidCurrencyException("Errore nell'inserimento della valuta: " + data.getCurrency());
		}
	}
}
