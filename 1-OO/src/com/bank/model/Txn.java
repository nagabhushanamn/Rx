package com.bank.model;

import java.time.LocalDate;

public class Txn {

	private int id;
	private double amount;
	private LocalDate date;
	private TxnType type;

	public Txn(int id, double amount, LocalDate date, TxnType type) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Txn [id=" + id + ", amount=" + amount + ", date=" + date + ", type=" + type + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TxnType getType() {
		return type;
	}

	public void setType(TxnType type) {
		this.type = type;
	}

}
