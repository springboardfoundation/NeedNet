package com.tech4.change.neednetwork.data.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.tech4.change.neednetwork.entity.Need;
@RepositoryRestResource(collectionResourceRel = "need", path = "need")
public interface NeedRepository extends MongoRepository<Need,String> {
	
 void delete(Need need);
 List<Need> findAll();
 Need findOne(@Param("id") String id);
 List<Need> findBycreatedBy( String createdBy);
 @SuppressWarnings("unchecked")
Need save(Need saved); 
 @Query(value = "{'users': ?0} ")
List<Need> findNeedByUsers(String[] username);
 
 @Query(value = "{'createdBy': ?0}")
 List<Need> findNeedByMe(String username);
 
 List<Need> findByUsersIn(List<String> username);
 @Query(value = "{'users': {$regex:?0}}")
 List<Need> findByUsers(String username);
 

 
 
}
