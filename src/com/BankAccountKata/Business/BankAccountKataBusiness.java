package com.BankAccountKata.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.BankAccountKata.Entities.Account;
import com.BankAccountKata.Entities.Operation;

public class BankAccountKataBusiness {
	private List<Account> accounts;

	// To change from static to get data from dataBase
	public BankAccountKataBusiness() {
		
		
		
		accounts = new ArrayList<>();
		//
		Account account = new Account();
		account.id = 1;
		account.amount = 5000;
		account.operations = new ArrayList<Operation>();
		
		
		
		Operation operation = new Operation();
		
		operation.amount = 200;
		operation.date = new Date();
		operation.description = "Operation type : deposit, amount added :" + operation.getAmount();
		
		account.operations.add(operation);
		
		
		accounts.add(account);
		
		
		
		
		
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	// we can adding method here like add account, delete and search an account ...
	
	
	

}

