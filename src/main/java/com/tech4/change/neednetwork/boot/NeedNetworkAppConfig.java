package com.tech4.change.neednetwork.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@Configuration
@EnableAutoConfiguration
@ComponentScan("com.tech4")
@EnableMongoRepositories (basePackages= "com.tech4.change")
public class NeedNetworkAppConfig {
	
	public NeedNetworkAppConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		SpringApplication.run(NeedNetworkAppConfig.class, args);
	}

}
