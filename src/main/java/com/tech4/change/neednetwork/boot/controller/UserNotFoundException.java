package com.tech4.change.neednetwork.boot.controller;

public class UserNotFoundException extends RuntimeException {
	

/**
	 * 
	 */
	private static final long serialVersionUID = -8155789768756609811L;

public UserNotFoundException(String id) {
		
		super(String.format("No User found with id: <%s> Please Register ", id));
	}
}
