package com.tech4.change.neednetwork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tech4.change.neednetwork.boot.controller.UserController;
import com.tech4.change.neednetwork.data.repository.UserRepository;
import com.tech4.change.neednetwork.entity.User;

public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDetails loadedUser;
		
		LOGGER.info("Inside UserDetails Service::",userName);
		try {
		   
			User user = userRepository.findByusername(userName);
		
		if(user == null) {
			LOGGER.info("User is not found");
			
			throw new UsernameNotFoundException("could not find the user:"+userName);
		}
		loadedUser = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
		
		
		}catch(Exception ex) {
			throw new InternalAuthenticationServiceException(ex.getMessage(),ex);
		}
		
		
		return loadedUser;
	}

}
