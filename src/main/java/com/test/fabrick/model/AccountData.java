package com.test.fabrick.model;

import java.time.LocalDate;

public class AccountData {
	
	private LocalDate date;
	private float balance;
	private float availableBalance;
	private String currency;
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public float getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(float availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "AccountData [date=" + date + ", balance=" + balance + ", availableBalance=" + availableBalance
				+ ", currency=" + currency + "]";
	}

}
