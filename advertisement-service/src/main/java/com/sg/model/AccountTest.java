package com.sg.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccountTest {
	
	//private static final long serialVersionUID = -8608934865070937157L;
	
	private String username;
	private String password;
	
  
	private AccountTest(){}
	public AccountTest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountTest other = (AccountTest) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}*/
	
	

}
