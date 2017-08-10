package com.sg.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="client")
public class Client implements Serializable{
	private static final long serialVersionUID = -7235672914332511944L;
	
	private Long clientId;
	private String name;
	
	private Client(){}
	public Client(Long clientId, String name) {
		super();
		this.clientId = clientId;
		this.name = name;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
