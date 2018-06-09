package com.tech4.change.neednetwork.service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech4.change.neednetwork.data.repository.NeedRepository;

import com.tech4.change.neednetwork.data.repository.UserRepository;

import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.dto.UserDTO;
import com.tech4.change.neednetwork.entity.Need;

import com.tech4.change.neednetwork.entity.User;
import com.tech4.change.neednetwork.exception.ServiceException;


@Service
public class UserServiceImpl  implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository repository;
	
	private final NeedRepository needRepository;
	
   
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, NeedRepository needRepo) {
		this.needRepository = needRepo;
		this.repository = userRepository;
		
	}
	
	
	public UserDTO createUser(UserDTO user) {
		
		User checkUser = repository.findByusername(user.getMobileNumber()); 
		  if(checkUser == null) {
		 User savedUser = new User();
		 
		 savedUser.setMobileNumber(user.getMobileNumber());
		 savedUser.setUserName(user.getMobileNumber().toString());
		 savedUser.setDeviceID(user.getDeviceID());
		 savedUser.setDisplayName(user.getDisplayName());
		 savedUser.setFriendList(user.getFriendList());
		 savedUser = repository.save(savedUser);
		 LOGGER.info("Created the User Succesfully "); 
		 return convertToDTO(savedUser);
		  }else {
			  LOGGER.info("User Already exists . Please invoke login."); 
			 throw new ServiceException("User Already Exists");
		  }
		  
		
	}

	public UserDTO delete(String username) {
		
		  User deleted = repository.findOne(username);
		  repository.delete(deleted);
		return convertToDTO(deleted);
	}

	public List<UserDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDTO findByUserName(String userName) {
		LOGGER.info("Check the user...."+userName);
		User userFound = repository.findByusername(userName);
		List<User> userslist = repository.findAll();
		for(User user: userslist) {
			LOGGER.info("Got the User name from list "+user.getUserName());
		}
		LOGGER.info("Got the User list size"+userslist);
		LOGGER.info("Got the User"+userFound);
		return convertToDTO(userFound);
	}

	public List<NeedDTO> findNeedsForUser(String userName) {
		List<Need> needEntries = needRepository.findBycreatedBy(userName);
        return convertToDTOs(needEntries);
	}

	public UserDTO update(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<NeedDTO> convertToDTOs(List<Need> needEntries) {
		 return needEntries.stream()
	                .map(this::convertToNeedDTO)
	                .collect(toList());
	} 
	
private UserDTO convertToDTO(User user) {
		
	 if(user != null) {
	UserDTO userDTO = new UserDTO();
	
	userDTO.setMobileNumber(user.getMobileNumber().toString());
	userDTO.setUserName(user.getUserName());
	userDTO.setFriendList(user.getFriendList());
	  return userDTO;
	 }else {
		 return null;
	 }
	 
		
	}

private User convertToUserRepo(UserDTO user) {
	
	User savedUser = new User();
	if(user!=null) {
		 savedUser.setMobileNumber(user.getMobileNumber());
		 savedUser.setUserName(user.getMobileNumber().toString());
	}
	return savedUser;
}

	
private NeedDTO convertToNeedDTO(Need need) {
		
		NeedDTO needDTO = new NeedDTO();
		needDTO.setId(need.getId());
		needDTO.setCreatedBy(need.getCreatedBy());
		
		needDTO.setDescription(need.getDescription());
	
		return needDTO;
	}


@Override
public void createPasswordResetTokenForUser(UserDTO user, String token) {
	// TODO Auto-generated method stub
	
}


@Override
public List<String> getRegisteredUser(List<String> users) {
	
	List<User> registeredUser = repository.findAll();
	List<String> userNames = new ArrayList<String>();
	for(User user: registeredUser) {
		userNames.add(user.getUserName());
		
	}
	List<String> intersect = userNames.stream()
            .filter(users::contains)
            .collect(Collectors.toList());
	
	LOGGER.info("Return the  registered Users"+intersect.toString());
	return intersect;
	
}


@Override
public UserDTO updateDisplayName(String userName,String displayName) {
	LOGGER.info("Update the display name for user..."+userName);
	User userFound = repository.findByusername(userName);
	userFound.setDisplayName(displayName);
	
	return convertToDTO(userFound);
	
	
}

@Override
public UserDTO updateDeviceID(String userName,String deviceID) {
	LOGGER.info("Update the display name for user..."+userName);
	User userFound = repository.findByusername(userName);
	userFound.setDeviceID(deviceID);
	
	return convertToDTO(userFound);
	
	
}





}



