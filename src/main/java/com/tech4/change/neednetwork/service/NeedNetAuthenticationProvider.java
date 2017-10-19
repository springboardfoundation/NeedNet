package com.tech4.change.neednetwork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.tech4.change.neednetwork.dto.UserDTO;


@Service
public class NeedNetAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NeedNetAuthenticationProvider.class);
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		UserDetails loadedUser;
		 try {
			 LOGGER.info("Invoking user service {}.."+username);
			 
			 UserDTO user = userService.findByUserName(username);
	            loadedUser = new org.springframework.security.core.userdetails.User(user.getUserName(),user.getMobileNumber().toString(),true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
	    		  } 
		 catch (Exception repositoryProblem) {
	            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
	    		  }

		 LOGGER.info("No error dude");

	       
	        return loadedUser;
		
	}
}

