package com.bank.service;

import java.time.LocalDate;
import java.util.List;

import com.bank.model.Txn;

public interface TxnService {

	List<Txn> getTxns(double amount);

	List<Txn> getTxns(LocalDate date);

	List<Txn> getTxns(LocalDate fromDate, LocalDate toDate);
	
	double getTotalTxnAmount();

}