package com.test.fabrick.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TransferResponse {
	
	private String status;
	private List<?> error;
	private TransferData payload;
	
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
	public TransferData getPayload() {
		return payload;
	}
	public void setPayload(TransferData payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "TransferResponse [status=" + status + ", error=" + error + ", payload=" + payload + "]";
	}
}