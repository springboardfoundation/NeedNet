package com.tech4.change.neednetwork.security;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech4.change.neednetwork.dto.NeedDTO;
import com.tech4.change.neednetwork.service.NeedService;

import org.springframework.http.MediaType;

@Component
public class NeedNetAuthSuccessHandler  implements AuthenticationSuccessHandler{

	 private static final Logger LOGGER = LoggerFactory.getLogger(NeedNetAuthSuccessHandler.class);
	@Autowired
	NeedService needService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		 TokenAuthenticationService
	        .addAuthentication(response, authentication.getName());
		 
		 LOGGER.info("Get the list of needs created by the user..."+authentication.getName());
		 ObjectMapper mapper = new ObjectMapper();
		 response.setStatus(HttpStatus.OK.value());
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        List<NeedDTO> needs = needService.findCreatedBy(authentication.getName());
	        
	        mapper.writeValue(response.getWriter(), needs);
	}

}
