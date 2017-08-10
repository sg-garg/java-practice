package com.sg.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AdvertisementResponse {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
