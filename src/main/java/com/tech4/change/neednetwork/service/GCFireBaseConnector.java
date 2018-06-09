package com.tech4.change.neednetwork.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.tech4.change.neednetwork.dto.NeedDTO;
import org.springframework.stereotype.Service;

@Service
public class GCFireBaseConnector {
	private static final Logger LOGGER = LoggerFactory.getLogger(GCFireBaseConnector.class);
				
	public   void sendMessage(String tokenId,String message,NeedDTO need) {
		try {
			//Send the message to the token id... 
			Message needMessage = Message.builder()
				    .putData("alert", message)
				    .putData("title", need.getTitle())
				    .putData("needNetApp", "True")
				    .setToken(tokenId)
				    .build();
				// Send a message to the device corresponding to the provided
				// registration token.
				String response = FirebaseMessaging.getInstance().sendAsync(needMessage).get();
				// Response is a message ID string.
				LOGGER.info("Successfully sent message: " + response);
			
		}catch(Exception e) {		
			LOGGER.info("Failed to send message to device id...: " + tokenId);
			e.printStackTrace();			
		}				
	}
}
