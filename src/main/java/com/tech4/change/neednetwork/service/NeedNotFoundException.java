package com.tech4.change.neednetwork.service;


public class NeedNotFoundException extends RuntimeException {

	public NeedNotFoundException(String id) {
		
		super(String.format("No Need entry found with id: <%s>", id));
	}

}
