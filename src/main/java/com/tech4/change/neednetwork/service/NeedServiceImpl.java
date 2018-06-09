package com.tech4.change.neednetwork.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech4.change.neednetwork.data.repository.NeedRepository;
import com.tech4.change.neednetwork.data.repository.UserRepository;
import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.entity.Need;
import com.tech4.change.neednetwork.entity.User;
import com.tech4.change.neednetwork.exception.ServiceException;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashSet;
@Service
public class NeedServiceImpl  implements NeedService{
	private static final Logger LOGGER = LoggerFactory.getLogger(NeedServiceImpl.class);

	private final NeedRepository repository;
	private final UserRepository userRepo;
	
	@Autowired
	NeedServiceImpl(NeedRepository repository, UserRepository userRepo) {
		this.repository=repository;
		this.userRepo = userRepo;
	}
	
	@Autowired
	GCFireBaseConnector fireBaseService;
	
	public NeedDTO create(NeedDTO need) {
		// TODO Auto-generated method stub
		
		Need persistedNeed = new Need();
		persistedNeed.setCreatedBy(need.getCreatedBy());
		persistedNeed.setTitle(need.getTitle());
		persistedNeed.setDescription(need.getDescription());
		persistedNeed.setLocation(need.getLocation());
		persistedNeed.setTargetDate(need.getTargetDate());
		persistedNeed.setGoal(need.getGoal());
		List<String> users = null;
		if(need.getUsers() == null) {
			users = new ArrayList<String>();
		}else {
			users = need.getUsers();
		}
		for(String userid : users) {
			User user = userRepo.findByusername(userid);
			if(user == null) {
				throw new ServiceException("Invalid user id been added to the need, please add valid user id");
			}
			fireBaseService.sendMessage(user.getDeviceID(), "A New need has been created", need);
		}
		
		persistedNeed.setUsers(users);
		persistedNeed = repository.save(persistedNeed);			
		
		return convertToNeedDTO(persistedNeed);
	}

	public NeedDTO delete(String id) {
		
		Need need = findNeedById(id);
		repository.delete(need);
		return convertToNeedDTO(need);
		
	}
	
	private Need findNeedById(String id) {
        Need result = repository.findOne(id);
        return result;
 
    }

	public List<NeedDTO> findAll() {
		List<Need> needEntries = repository.findAll();
        return convertToDTOs(needEntries);
	}

	private List<NeedDTO> convertToDTOs(List<Need> needEntries) {
		 return needEntries.stream()
	                .map(this::convertToNeedDTO)
	                .collect(toList());
	} 

	public NeedDTO findById(String id) {
		LOGGER.info("Finding need entry with id: {}", id);

        Need  found = findNeedById(id);

        LOGGER.info("Found need entry: {}", found);

        return convertToNeedDTO(found);
	}

	public NeedDTO update(NeedDTO need) {
		LOGGER.info("Updating need entry with information: {}", need);

        Need updated = findNeedById(need.getId());
        updated.setCreatedBy(need.getCreatedBy());
        updated.setDescription(need.getDescription());
        updated.setTitle(need.getTitle());
        updated.setTargetDate(need.getTargetDate());
        updated.setLocation(need.getLocation());
        updated.setId(need.getId());
        updated.setUsers(need.getUsers());
        updated.setGoal(need.getGoal());
        updated = repository.save(updated);

        LOGGER.info("Updated todo entry with information: {}", updated);

        return convertToNeedDTO(updated);
	}
	
	private NeedDTO convertToNeedDTO(Need need) {
		
		NeedDTO needDTO = new NeedDTO();
		needDTO.setId(need.getId());
		needDTO.setCreatedBy(need.getCreatedBy());
	
		needDTO.setDescription(need.getDescription());
		needDTO.setTitle(need.getTitle());
		needDTO.setLocation(need.getLocation());
		needDTO.setTargetDate(need.getTargetDate());
		needDTO.setUsers(need.getUsers());
		needDTO.setGoal(need.getGoal());
		
		return needDTO;
	}

	@Override
	public List<NeedDTO> findCreatedBy(String userName) {
		List<Need> needEntries = repository.findBycreatedBy(userName);
        return convertToDTOs(needEntries);
	}

	@Override
	public NeedDTO updateUsers(List<String> users,String needId) {
		Need need = repository.findOne(needId);
		List<String> existingUsers = need.getUsers();
		existingUsers.addAll(users);
		need.setUsers(existingUsers);
		LOGGER.info("Updated the users to the need..."+need.getUsers());
		repository.save(need);
		for(String userid : users) {
			User user = userRepo.findByusername(userid);
			if(user == null) {
				throw new ServiceException("Invalid user id been added to the need, please add valid user id");
			}
			fireBaseService.sendMessage(user.getDeviceID(), "A New need has been created", convertToNeedDTO(need));
		}
		return convertToNeedDTO(need);
		
	}

	@Override
	public NeedDTO addUser(String user,String needId) {
		
		Need need = repository.findOne(needId);
		List<String> existingUsers = need.getUsers();
		if(existingUsers != null) {
		existingUsers.add(user);
		}else {
			existingUsers = new ArrayList<String>();
			existingUsers.add(user);
		}
		need.setUsers(existingUsers);
		LOGGER.info("Updated the  single user to the need..."+need.getUsers());
		repository.save(need);
		
			User newUser = userRepo.findByusername(user);
			if(user == null) {
				throw new ServiceException("Invalid user id been added to the need, please add valid user id");
			}
			fireBaseService.sendMessage(newUser.getDeviceID(), "A New need has been created", convertToNeedDTO(need));
		
		return convertToNeedDTO(need);
	}

	@Override
	public List<NeedDTO> findNeedsByUsers(String user) {
		LOGGER.info("Inside get needs created by others mapped to me..."+user);
		//repository.
		String[] usersArr = new String[10];
		usersArr[0] = user;
		
		List<String>  users = new ArrayList<String>();
		users.add(user);
		List<Need> needEntries = repository.findNeedByUsers(usersArr);
		
		LOGGER.info("Repository needs created by others mapped to me..."+needEntries);
		
		List<Need> needEntriesCustom = repository.findByUsersIn(users);
		LOGGER.info("Repository needs created by others mapped to me..."+needEntriesCustom);
		
		
		
		List<Need> needEntries1 = repository.findByUsers(user.replace("+", "\\+"));
		LOGGER.info("Repository needs created by others mapped to me..."+needEntries1);
		
        return convertToDTOs(needEntries1);
		
	}
	
	

}

