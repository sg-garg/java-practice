package com.sg.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="advertisement")
public class Advertisement implements Serializable {
	private static final long serialVersionUID = -5502266187478586041L;
	
	private Long id;
	private String message;
	private String url;
	private String startDate;
	private String endDate;
	private String category;
	
	private Advertisement(){}
	
	public Advertisement(Long id, String message, String url, String startDate,
			String endDate, String category) {
		super();
		this.id = id;
		this.message = message;
		this.url = url;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	};
	
	
}
