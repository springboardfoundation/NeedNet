package com.tech4.change.neednetwork.boot.controller;



import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.dto.UserDTO;
import com.tech4.change.neednetwork.service.SecurityService;
import com.tech4.change.neednetwork.service.UserService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
@RestController
@RequestMapping("/rest")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	
    
    private SecurityService securityService;
	
	@Autowired
	public UserController(UserService userService,SecurityService securityService) {
		this.securityService = securityService;
		this.userService = userService; 
		
	}
	
	@RequestMapping( value ="/register" ,method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO create(@RequestBody @Valid UserDTO user) {
        LOGGER.info("Creating a new need User with information: {}", user);

        UserDTO created = userService.createUser(user);
        LOGGER.info("Created a new need User with information: {}", created);

        return created;
    }
	@RequestMapping(value = "/login" ,method= RequestMethod.POST)
	List<NeedDTO> login(@RequestBody UserDTO user) throws Throwable {
		//
		LOGGER.info("inside login method.... ");
		//UserDTO loggedInUser = userService.findByUserName(username);
		
		 securityService.login(user.getUsername(), user.getPassword());	
	 
		//Query and fetch list of needs for this user
		List<NeedDTO> needsList = userService.findNeedsForUser(user.getUsername());
		if(needsList != null) {
		LOGGER.info("returning needslist"+needsList.size());
		}
		return needsList;
		
		
	}
	
	@RequestMapping(value = "/test" ,method= RequestMethod.GET)
	String test() throws Throwable {
		//
		LOGGER.info("inside test method.... ");
		//UserDTO loggedInUser = userService.findByUserName(username);
		// securityService.login(username, password);	
	 
		//Query and fetch list of needs for this user
		//List<NeedDTO> needsList = userService.findNeedsForUser(username);
		
		return "Hello World";
		
	}

}
