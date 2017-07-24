package com.tech4.change.neednetwork.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech4.change.neednetwork.data.repository.NeedRepository;
import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.entity.Need;
import static java.util.stream.Collectors.toList;
@Service
public class NeedServiceImpl  implements NeedService{
	private static final Logger LOGGER = LoggerFactory.getLogger(NeedServiceImpl.class);

	private final NeedRepository repository;
	
	@Autowired
	NeedServiceImpl(NeedRepository repository) {
		this.repository=repository;
	}
	
	public NeedDTO create(NeedDTO need) {
		// TODO Auto-generated method stub
		
		Need persistedNeed = new Need();
		persistedNeed.setCreatedBy(need.getCreatedBy());
		persistedNeed.setCurrentAmount(need.getCurrentAmount());
		persistedNeed.setDescription(need.getDescription());
		persistedNeed.setStatus(need.getStatus());
		persistedNeed.setTargetAmount(need.getTargetAmount());
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
        updated.setCurrentAmount(need.getCurrentAmount());
        updated.setDescription(need.getDescription());
        updated.setStatus(need.getStatus());
        updated.setTargetAmount(need.getTargetAmount());
        updated = repository.save(updated);

        LOGGER.info("Updated todo entry with information: {}", updated);

        return convertToNeedDTO(updated);
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

	@Override
	public List<NeedDTO> findCreatedBy(String userName) {
		List<Need> needEntries = repository.findBycreatedBy(userName);
        return convertToDTOs(needEntries);
	}

}
