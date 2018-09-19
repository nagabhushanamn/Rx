package com.bank.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.bank.model.Txn;
import com.bank.model.TxnType;
import com.bank.service.lib.TxnUtilLib;

public class TxnServiceImpl implements TxnService {

	private final static List<Txn> TXNS = Arrays.asList(
			new Txn(2342349, 100.00, LocalDate.of(2017, 4, 12), TxnType.CREDIT),
			new Txn(3342348, 100.00, LocalDate.of(2017, 4, 17), TxnType.DEBIT),
			new Txn(4342347, 100.00, LocalDate.of(2018, 5, 18), TxnType.DEBIT),
			new Txn(5342346, 300.00, LocalDate.of(2018, 7, 12), TxnType.CREDIT),
			new Txn(6342345, 600.00, LocalDate.of(2018, 2, 18), TxnType.DEBIT),
			new Txn(7342344, 100.00, LocalDate.of(2018, 4, 12), TxnType.DEBIT),
			new Txn(8342342, 600.00, LocalDate.of(2018, 4, 16), TxnType.DEBIT),
			new Txn(9342341, 300.00, LocalDate.of(2018, 1, 15), TxnType.CREDIT));

	@Override
	public List<Txn> getTxns(double amount) {

		// -----------------------------------------
		// way-1: imperative style ( what + How )
		// -----------------------------------------
		
		/*
		List<Txn> out = new ArrayList<>();
		for (Txn e : TXNS) {
			if (e.getAmount() == amount)
				out.add(e);
		}
		return out;
		*/

		// -----------------------------------------

		// -----------------------------------------
		// way-2 : declarative style by local-class ( what )
		// -----------------------------------------

		/*
		class ByAmount implements Predicate<Txn> {
			@Override
			public boolean test(Txn txn) {
				return txn.getAmount() == amount;
			}
		}
		Predicate<Txn> predicate = new ByAmount();

		return TxnUtilLib.filter(TXNS, predicate);
		*/

		// -------------------------------------------

		// -------------------------------------------
		// way-3 : declarative style ( what ) by anonymous class
		//-------------------------------------------

		/*
		
		Predicate<Txn> predicate = new Predicate<Txn>() {
			@Override
			public boolean test(Txn txn) {
				return txn.getAmount() == amount;
			}
		};

		return TxnUtilLib.filter(TXNS, predicate);
		
		*/

		// -----------------------------------------

		// way-4 : declarative style ( what ) by function

		return TxnUtilLib.filter(TXNS, txn -> txn.getAmount() == amount);
		
		//------------------------------------------

	}

	
	@Override
	public List<Txn> getTxns(LocalDate date) {

		// -----------------------------------------
		// imperative style ( what + How )
		//-----------------------------------------

		/*
		List<Txn> out = new ArrayList<>();
		for (Txn e : TXNS) {
			if (e.getDate().equals(date))
				out.add(e);
		}
		return out;
		*/

		// -----------------------------------------
		// declarative style ( what )
		//-----------------------------------------
		class ByDate implements Predicate<Txn> {
			@Override
			public boolean test(Txn txn) {
				return txn.getDate() == date;
			}
		}
		Predicate<Txn> predicate = new ByDate();
		return TxnUtilLib.filter(TXNS, predicate);

		// -----------------------------------------

	}

	@Override
	public List<Txn> getTxns(LocalDate fromDate, LocalDate toDate) {
		return null;
	}

	@Override
	public double getTotalTxnAmount() {

		//-----------------------------------------
		//Imperative style
		//-----------------------------------------
		
		/*
		double total=0.0;
		for(Txn txn:TXNS) {
			total+=txn.getAmount();
		}
		*/

		//	-----------------------------------------
		// functional style with stream
		// -----------------------------------------
		
		/*
		return TXNS
		.stream()
		.mapToDouble(txn->txn.getAmount())
		.sum();
		*/

		// or

		return TXNS.stream().mapToDouble(Txn::getAmount).sum();

	}

}
