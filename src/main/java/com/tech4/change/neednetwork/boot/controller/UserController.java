package com.tech4.change.neednetwork.boot.controller;



import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.dto.UserDTO;

import com.tech4.change.neednetwork.service.SecurityService;
import com.tech4.change.neednetwork.service.UserService;
import com.tech4.change.neednetwork.util.GenericResponse;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/rest")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	
    
    private SecurityService securityService;
    
    
    
    @Autowired
    private Environment env;
	
	@Autowired
	public UserController(UserService userService,SecurityService securityService) {
		this.securityService = securityService;
		this.userService = userService; 
		
	}
	
	@RequestMapping( value ="/register" ,method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO user) {
        LOGGER.info("Creating a new  User with information: {}", user);
        UserDTO created = null;
        try {
        	created = userService.createUser(user);
        LOGGER.info("Created a new  User with information: {}", created);
        }catch (Exception e) {
        	LOGGER.error("Got Error registering the user", e.getMessage());
        	return new ResponseEntity<UserDTO>(created,HttpStatus.ALREADY_REPORTED);
        	
        }
        return new ResponseEntity<UserDTO>(created,HttpStatus.OK);
    }
	
	@RequestMapping( value ="/users" ,method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	ResponseEntity<List<String>> getRegisteredUsers(@RequestBody @Valid List<String> users) {
        LOGGER.info("Get Sorted list of registered users.. {}", users);
        List<String> regiseteredUsers = null;
        try {
        	regiseteredUsers = userService.getRegisteredUser(users);
        LOGGER.info("Registered users: {}", regiseteredUsers.toString());
        }catch (Exception e) {
        	LOGGER.error("Got Error getting registered users", e.getMessage());
        	return new ResponseEntity<List<String>>(regiseteredUsers,HttpStatus.UNPROCESSABLE_ENTITY);
        	
        }
        return new ResponseEntity<List<String>>(regiseteredUsers,HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/login" ,method= RequestMethod.POST)
	ResponseEntity<List<NeedDTO>> login(@RequestBody UserDTO user) throws Throwable {
		//
		LOGGER.info("inside login method.... ");
		List<NeedDTO>  needsList = null;
		//UserDTO loggedInUser = userService.findByUserName(username);
		
		 securityService.login(user.getMobileNumber().toString(), user.getMobileNumber().toString());	
	 
		//Query and fetch list of needs for this user
		 needsList =  userService.findNeedsForUser(user.getMobileNumber().toString());
		if(needsList != null) {
		LOGGER.info("returning needslist"+needsList.size());
		}
		return new ResponseEntity<List<NeedDTO>>(needsList,HttpStatus.ACCEPTED);
		
		
	}
	
	
	 @RequestMapping(value = "{mobileno}/displayname", method = RequestMethod.PUT)
	    UserDTO updateUser(@PathVariable String mobileno ,@RequestBody @Valid String displayName) {
	        LOGGER.info("Adding need entry with information: {}", displayName);
	     return userService.updateDisplayName(mobileno, displayName);
	      
	    }
	
    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }


}
