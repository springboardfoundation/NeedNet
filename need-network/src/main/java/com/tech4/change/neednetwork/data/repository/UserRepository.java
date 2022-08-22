package com.tech4.change.neednetwork.data.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tech4.change.neednetwork.entity.User;

public interface UserRepository  extends MongoRepository<User, String>{
	
	void delete(User user);
	 List<User> findAll();
	 User findByusername(String username);
	 @SuppressWarnings("unchecked")
	User save(User saved);
	 
	 

}
