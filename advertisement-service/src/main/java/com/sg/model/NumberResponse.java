package com.sg.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NumberResponse {
	
	private int highestNumber;
	private List<Integer> inputArray;
	
	public int getHighestNumber() {
		return highestNumber;
	}

	public void setHighestNumber(int highestNumber) {
		this.highestNumber = highestNumber;
	}

	public List<Integer> getInputArray() {
		return inputArray;
	}

	public void setInputArray(List<Integer> inputArray) {
		this.inputArray = inputArray;
	}


	
}
