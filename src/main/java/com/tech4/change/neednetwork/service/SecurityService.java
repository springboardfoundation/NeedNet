package com.tech4.change.neednetwork.service;

public interface SecurityService {
	

	    String findLoggedInUsername();

	    void login(String username, String password);
	}


