package com.tech4.change.neednetwork.service;

import java.util.List;

import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.dto.UserDTO;

public interface UserService {
	
	UserDTO createUser(UserDTO user);
	
	UserDTO delete(String id);
	
	List<UserDTO> findAll();
	
	UserDTO findByUserName(String userName);
	
	List<NeedDTO> findNeedsForUser(String userName);
	
 //To do add friends /remove friends.
	 
	UserDTO update(UserDTO user);
	
	UserDTO updateDisplayName(String userName,String displayName);
	
	List<String> getRegisteredUser(List<String> users);

	void createPasswordResetTokenForUser(UserDTO user, String token);

	UserDTO updateDeviceID(String userName, String deviceID);
	

}
