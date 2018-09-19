package com.bank.repository;

import org.apache.log4j.Logger;

import com.bank.model.Account;

public class JPAAccountRepository implements AccountRepository {

	private static final Logger LOGGER = Logger.getLogger("bank-txr");

	public JPAAccountRepository() {
		LOGGER.info("JPAAccountRepository instance created..");
	}

	@Override
	public Account loadAccount(String num) {
		LOGGER.info("loading account - " + num);
		return null;
	}

	@Override
	public void updateAccount(Account account) {
		LOGGER.info("updating account.");

	}

}
