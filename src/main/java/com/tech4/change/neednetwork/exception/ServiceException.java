package com.tech4.change.neednetwork.exception;

public class ServiceException extends RuntimeException {
	
	private String message;
	public  ServiceException() {
		super();
	}
	public ServiceException(String message) {
		super(message);
		this.message = message;
		
	}
	
	
	

}
