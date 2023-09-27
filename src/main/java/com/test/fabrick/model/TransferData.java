package com.test.fabrick.model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.fabrick.model.transfer.Creditor;

public class TransferData {
	@JsonIgnore
	private Long accountId;
	private Creditor creditor;
	private LocalDate executionDate;
	private String description;
	private float amount;
	private String currency;
	
	public Long getAccountId() {
		return 14537780L;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Creditor getCreditor() {
		return creditor;
	}
	public void setCreditor(Creditor creditor) {
		this.creditor = creditor;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(LocalDate executionDate) {
		this.executionDate = executionDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "TransferData [accountId=" + accountId + ", creditor=" + creditor + ", executionDate=" + executionDate
				+ ", description=" + description + ", amount=" + amount + ", currency=" + currency + "]";
	}

}