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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.service.NeedNotFoundException;
import com.tech4.change.neednetwork.service.NeedService;

@RestController
@RequestMapping("/rest/{userId}/needs")
public class NeedController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NeedController.class);
	
	private final NeedService service;
	
	@Autowired
	public NeedController(NeedService needService) {
		this.service= needService; 
	}
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    NeedDTO create(@RequestBody @Valid NeedDTO todoEntry) {
        LOGGER.info("Creating a new need entry with information: {}", todoEntry);

        NeedDTO created = service.create(todoEntry);
        LOGGER.info("Created a new need entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    NeedDTO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting need entry with id: {}", id);

        NeedDTO deleted = service.delete(id);
        LOGGER.info("Deleted need entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<NeedDTO> findAll() {
        LOGGER.info("Finding all need entries");

        List<NeedDTO> todoEntries = service.findAll();
        LOGGER.info("Found {} need entries", todoEntries.size());

        return todoEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    NeedDTO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding need entry with id: {}", id);

        NeedDTO todoEntry = service.findById(id);
        LOGGER.info("Found need entry with information: {}", todoEntry);

        return todoEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    NeedDTO update(@RequestBody @Valid NeedDTO todoEntry) {
        LOGGER.info("Updating need entry with information: {}", todoEntry);

        NeedDTO updated = service.update(todoEntry);
        LOGGER.info("Updated need entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(NeedNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
	
	
	

}
