package com.sg.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AdvertisementRequest {
	private Advertisement advertisement;
	private String username;
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
