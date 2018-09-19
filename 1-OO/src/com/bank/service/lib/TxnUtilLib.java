package com.bank.service.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.bank.model.Txn;

public class TxnUtilLib {

	public static List<Txn> filter(List<Txn> in, Predicate<Txn> predicate) {
		List<Txn> out = new ArrayList<>();
		for (Txn e : in) {
			if (predicate.test(e))
				out.add(e);
		}
		return out;
	}

}
