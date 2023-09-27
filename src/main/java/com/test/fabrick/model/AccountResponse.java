package com.test.fabrick.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AccountResponse {
	
	private String status;
	private List<?> error;
	private AccountData payload;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<?> getError() {
		return error;
	}
	public void setError(List<?> error) {
		this.error = error;
	}
	public AccountData getPayload() {
		return payload;
	}
	public void setPayload(AccountData payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "AccountResponse [status=" + status + ", error=" + error + ", payload=" + payload + "]";
	}
   
}
