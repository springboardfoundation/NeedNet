package com.tech4.change.neednetwork.boot.controller;

public class UserNotFoundException extends RuntimeException {
	

public UserNotFoundException(String id) {
		
		super(String.format("No User found with id: <%s> Please Register ", id));
	}
}
