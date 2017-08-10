package com.sg.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sg.model.Account;
import com.sg.model.Client;
import com.sg.utils.AdvertisementServiceUtil;
import com.sg.utils.DataFiles;

public class AccountRepositoryStub implements AccountRepository{
	
	@Override
	public List<Account> getAllAccounts() {
		File file = new File(DataFiles.ACCOUNT_FILE.getFile());
        if (!file.exists()) {
           Client client = new Client(101L, "First Agency");
           Account account = new Account("sgarg", "sgarg@123", client);
           saveAccount(account);
        }
		List<Account> list = AdvertisementServiceUtil.deserilizeObjects(DataFiles.ACCOUNT_FILE.getFile());
		return list;
	}
	
	@Override
	public List<Account> getAllAccountsByClient(Client client) {
		
		if(client == null){
			return null;
		}
		List<Account> allAccounts = getAllAccounts();
		if(allAccounts == null)
			return null;
		
		List<Account> matchedAccount = new ArrayList<Account>();
		for (Account account : allAccounts) {
			if(account.getClient().getClientId() == client.getClientId()){
				matchedAccount.add(account);
			}
		}
		return matchedAccount;
	}
	
	@Override
	public void saveAccount(Account account) {
		AdvertisementServiceUtil.serilizeObjects(DataFiles.ACCOUNT_FILE.getFile(), account);
	}
	
	@Override
	public Account validateAccount(String userName, String password){
		List<Account> accountList = getAllAccounts();
		for (Account account : accountList) {
			if(account.getUsername().equals(userName) && account.getPassword().equals(password)){
				return account;
			}
		}
		return null;
	}
	
	@Override
	public Account getAccountByUsername(String userName) {
		List<Account> accountList = getAllAccounts();
		for (Account account : accountList) {
			if(account.getUsername().equals(userName)){
				return account;
			}
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		AccountRepository accountDao = new AccountRepositoryStub();
		List<Account> accounts = accountDao.getAllAccounts();
		for (Account account : accounts) {
			System.out.println(account.getClient().getName()+" "+ account.getUsername() +" "+account.getPassword() );
		}
	}
	
	
}
