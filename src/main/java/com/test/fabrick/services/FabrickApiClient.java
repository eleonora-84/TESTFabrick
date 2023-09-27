package com.test.fabrick.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.test.fabrick.model.AccountResponse;
import com.test.fabrick.model.TransactionResponse;
import com.test.fabrick.model.TransferData;
import com.test.fabrick.model.TransferResponse;

@Component
public class FabrickApiClient {

	@Value("${fabrick.base-url}")
	private String baseUrl;
    
	@Value("${fabrick.auth-schema}")
	private String authSchema;
        
    @Value("${fabrick.api-key}")
    private String apiKey;  
    
    private RestTemplate restTemplate = new RestTemplate();
    
    private HttpHeaders getHeaders() {
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", authSchema);
        headers.set("apikey", apiKey);
        headers.set("X-Time-Zone", "Europe/Rome");
        return headers;
    }
    
    public ResponseEntity<AccountResponse> getBalance(Long accountId) {
			String url = baseUrl + "/api/gbs/banking/v4.0/accounts/" + accountId + "/balance";
			HttpEntity<String> entity = new HttpEntity<>(getHeaders());
			ResponseEntity<AccountResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, AccountResponse.class);
			return responseEntity;
    }
    
    public ResponseEntity<TransactionResponse> getTransactionsList(Long accountId, String from, String to) {
        String url = baseUrl + "/api/gbs/banking/v4.0/accounts/" + accountId +"/transactions?fromAccountingDate=" + from + "&toAccountingDate=" +to;
        HttpEntity<String> entity = new HttpEntity<>(getHeaders());
        return restTemplate.exchange(url, HttpMethod.GET, entity, TransactionResponse.class);
    }
    
    public ResponseEntity<TransferResponse> transfer(TransferData data) throws JsonProcessingException {

        String url = baseUrl + "/api/gbs/banking/v4.0/accounts/"+data.getAccountId()+"/payments/money-transfers";
    	ObjectMapper objectMapper = new ObjectMapper();
    	objectMapper.registerModule(new JavaTimeModule());
    	String jsonString = objectMapper.writeValueAsString(data);
    	HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, getHeaders());
        ResponseEntity<TransferResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, TransferResponse.class);
        return responseEntity;
    }
    

}
