package com.tech4.change.neednetwork.boot.controller;



import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.dto.UserDTO;
import com.tech4.change.neednetwork.service.UserService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
@RestController
@RequestMapping("/rest")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
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
	@RequestMapping(value = "{userId}" ,method= RequestMethod.GET)
	List<NeedDTO> login(@PathVariable String userId) throws Throwable {
		UserDTO loggedInUser = userService.findByUserName(userId);
		if(loggedInUser == null) {
			throw new UserNotFoundException(userId);
		}
		 
		//Query and fetch list of needs for this user
		List<NeedDTO> needsList = userService.findNeedsForUser(userId);
		
		return needsList;
		
	}

}
