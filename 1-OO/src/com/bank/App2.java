package com.bank;

import java.time.LocalDate;
import java.util.List;

import com.bank.model.Txn;
import com.bank.service.TxnService;
import com.bank.service.TxnServiceImpl;

public class App2 {

	public static void main(String[] args) {

		TxnService txnService = new TxnServiceImpl();

		List<Txn> txns = txnService.getTxns(100.00);
		txns = txnService.getTxns(LocalDate.of(2018, 04, 12));
		double total = txnService.getTotalTxnAmount();

	}

}
