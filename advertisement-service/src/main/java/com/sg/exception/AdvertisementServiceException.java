package com.sg.exception;

public class AdvertisementServiceException extends Exception {
	private static final long serialVersionUID = 5107468593878072355L;
	
	
	private String errorCode;
	private String errorMessage;
	
	public AdvertisementServiceException(String errorCode,String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		
		
	}

	

	public AdvertisementServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
