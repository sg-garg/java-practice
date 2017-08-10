package com.sg.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NumberRequest {
	List<Integer> inputArray;
	
	public List<Integer> getInputArray() {
		return inputArray;
	}

	public void setInputArray(List<Integer> inputArray) {
		this.inputArray = inputArray;
	}
	
}