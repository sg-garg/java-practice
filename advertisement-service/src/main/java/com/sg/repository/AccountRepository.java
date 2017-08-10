package com.sg.repository;

import java.util.List;

import com.sg.model.Account;
import com.sg.model.Advertisement;
import com.sg.model.Client;

public interface AccountRepository {
	public List<Account> getAllAccounts();
	public List<Account> getAllAccountsByClient(Client client);
	public void saveAccount(Account account);
	Account validateAccount(String userName, String password);
	public Account getAccountByUsername(String userName);
}
