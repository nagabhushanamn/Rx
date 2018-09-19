package com.bank;

import com.bank.repository.AccountRepository;
import com.bank.repository.JPAAccountRepository;
import com.bank.service.TxrService;
import com.bank.service.TxrServiceImpl;

public class App {

	public static void main(String[] args) {

		// third-party / container ==> application context

		// init
		System.out.println("--------------------------");
		//
		AccountRepository accountRepository = new JPAAccountRepository();
		TxrService txrService = new TxrServiceImpl(accountRepository);
		System.out.println("--------------------------");

		// use
		System.out.println("--------------------------");

		txrService.txr(100.00, "1", "2");
		System.out.println();
		txrService.txr(100.00, "1", "2");

		System.out.println("--------------------------");

		// destroy
		System.out.println("--------------------------");

		System.out.println("--------------------------");

	}

}
