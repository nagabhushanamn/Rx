package com.bank.model;

public class Account {

	private String num;
	private double balance;

	public Account(String num, double balance) {
		super();
		this.num = num;
		this.balance = balance;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [num=" + num + ", balance=" + balance + "]";
	}

}
