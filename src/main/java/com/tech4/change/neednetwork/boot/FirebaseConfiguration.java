package com.tech4.change.neednetwork.boot;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


@Configuration
@ComponentScan
public class FirebaseConfiguration {
	
	@Autowired
    private ResourceLoader resourceLoader;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FirebaseConfiguration.class);	
	@Bean
	public FirebaseApp firebaseNeedNetApp() {
		
		Resource resource = resourceLoader.getResource("classpath:NeedNetwork.json");
		FirebaseOptions options = null;
		try {
			options = new FirebaseOptions.Builder()
				    .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
				    .setDatabaseUrl("https://neednetwork-4215f.firebaseio.com/")
				    .build();
			LOGGER.info("Got the options set ...."+options);
		} catch (IOException e) {
			LOGGER.error("Got error initializing the firebase app... ",e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FirebaseApp.initializeApp(options);		
		
	}

}
