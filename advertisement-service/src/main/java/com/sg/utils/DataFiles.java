package com.sg.utils;

public enum DataFiles {
	ACCOUNT_FILE("accounts.out"), ADVERTISEMENT("ads1.out");
	private String file;
	private DataFiles(String file) {
		this.file = file;
	}
	
	public String getFile(){
		return file;
	}

}
