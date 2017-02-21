package com.tech4.change.neednetwork.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech4.change.neednetwork.data.repository.NeedRepository;
import com.tech4.change.neednetwork.data.repository.UserRepository;
import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.dto.UserDTO;
import com.tech4.change.neednetwork.entity.Need;
import com.tech4.change.neednetwork.entity.User;
@Service
public class UserServiceImpl  implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository repository;
	
	private final NeedRepository needRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, NeedRepository needRepo) {
		this.needRepository = needRepo;
		this.repository = userRepository;
		
	}
	
	
	public UserDTO createUser(UserDTO user) {
		
		User checkUser = repository.findByusername(user.getUsername()); 
		  if(checkUser == null) {
		 User savedUser = new User();
		 savedUser.setEmailAddress(user.getEmailAddress());
		 savedUser.setFirstName(user.getFirstName());
		 savedUser.setLastName(user.getLastName());
		 savedUser.setPassword(user.getPassword());
		 savedUser.setUsername(user.getUsername());
		 savedUser.setMobileNumber(user.getMobileNumber());
		  
		 savedUser = repository.save(savedUser);
		 LOGGER.info("Created the User Succesfully "); 
		 return convertToDTO(savedUser);
		  }else {
			  return null;
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
	userDTO.setEmailAddress(user.getEmailAddress());
	userDTO.setFirstName(user.getFirstName());
	userDTO.setLastName(user.getLastName());
	userDTO.setPassword(user.getPassword());
	userDTO.setUsername(user.getUsername());
	userDTO.setMobileNumber(user.getMobileNumber());
	userDTO.setId(user.getId());
	userDTO.setFriendList(user.getFriendList());
	  return userDTO;
	 }else {
		 return null;
	 }
	 
		
	}

	
private NeedDTO convertToNeedDTO(Need need) {
		
		NeedDTO needDTO = new NeedDTO();
		needDTO.setId(need.getId());
		needDTO.setCreatedBy(need.getCreatedBy());
		needDTO.setCurrentAmount(need.getCurrentAmount());
		needDTO.setDescription(need.getDescription());
		needDTO.setStatus(need.getStatus());
		needDTO.setTargetAmount(need.getTargetAmount());
		return needDTO;
	}


}
