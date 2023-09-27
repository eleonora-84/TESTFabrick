package com.test.fabrick.model.transfer;

public class Creditor {
	
	private String name;
	private Account account;

	private Address address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Creditor [name=" + name + ", account=" + account + ", address=" + address + "]";
	}
}
