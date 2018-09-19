package com.bank.service;

import org.apache.log4j.Logger;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;
import com.bank.repository.JPAAccountRepository;

/*
 * 
 *  design & performance issues on this component
 *  -----------------------------------------------
 *  
 *   - tight-coupling with dependency
 *   	=> can't extend with new features
 *   - too many duplicate dependency-instances created & ignored
 *   	=> slow, memory/resource use high
 *   - unit-testing not possible
 *   	=> dev/bug fix slow
 *  
 *  -----------------------------------------
 *  
 *  
 *   why these issues ?
 *   
 *   => dependent itself creating its own dependency
 *   
 *   soln : don't create, do lookup in right factory
 *   
 *   Limitation on factory look-up ?
 *   
 *   => factory-location tight-coupling
 *   
 *   best-soln : dont create/lookup  , inject/get thru third-party   ( IOC )
 *   
 *   	how to implement this IOC ?
 *   
 *   		=> dependency injection
 *          => AOP
 *          
 *   -----------------------------------------       
 * 
 */

public class TxrServiceImpl implements TxrService {

	private static final Logger LOGGER = Logger.getLogger("bank-txr");

	private AccountRepository accountRepostory;

	public TxrServiceImpl(AccountRepository accountRepository) {
		this.accountRepostory = accountRepository;
		LOGGER.info("TxrServiceImpl instance created");
	}

	@Override
	public boolean txr(double amount, String fromAccNum, String toAccNum) {
		LOGGER.info("Txr initiated..");
		
		// this.accountRepostory = new JPAAccountRepository(); // Evil line

		Account fromAccount = accountRepostory.loadAccount(fromAccNum);
		Account toAccunt = accountRepostory.loadAccount(toAccNum);

		//

		accountRepostory.updateAccount(fromAccount);
		accountRepostory.updateAccount(toAccunt);
		LOGGER.info("Txr finished..");
		
		return true;
	}

}
