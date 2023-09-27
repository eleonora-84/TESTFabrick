package com.test.fabrick.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TransactionResponse {
	
	private String status;
	private List<?> error;
	private Payload payload;
	
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
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "TransactionResponse [status=" + status + ", error=" + error + ", payload=" + payload + "]";
	}
}
