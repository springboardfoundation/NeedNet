package com.tech4.change.neednetwork.service;

import java.util.List;


import com.tech4.change.neednetwork.dto.NeedDTO;

 public interface  NeedService {
	
	NeedDTO create(NeedDTO need);
	
	NeedDTO delete(String id);
	
	List<NeedDTO> findAll();
	
	NeedDTO findById(String id);
	
	List<NeedDTO> findCreatedBy(String userName);
	 
	NeedDTO update(NeedDTO need);
	
	
	

}
