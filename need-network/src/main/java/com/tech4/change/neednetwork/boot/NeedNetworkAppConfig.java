package com.tech4.change.neednetwork.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tech4.change.neednetwork.security.JWTAuthenticationFilter;
import com.tech4.change.neednetwork.security.JWTLoginFilter;
import com.tech4.change.neednetwork.security.NedNetAuthFailureHandler;
import com.tech4.change.neednetwork.security.NeedNetAuthSuccessHandler;
import com.tech4.change.neednetwork.service.NeedNetAuthenticationProvider;
import com.tech4.change.neednetwork.service.UserDetailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@Configuration
@EnableAutoConfiguration
@ComponentScan("com.tech4")
@EntityScan
@EnableMongoRepositories (basePackages= "com.tech4.change")
public class NeedNetworkAppConfig {
	
	public NeedNetworkAppConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		SpringApplication.run(NeedNetworkAppConfig.class, args);
	}

	@Configuration
	@ComponentScan("com.tech4")
	@EnableWebSecurity
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	    
		@Bean
	    public UserDetailsService userDetailsService() {
	    	return new UserDetailServiceImpl();
	    	
	    }
		
		@Autowired
		NeedNetAuthenticationProvider authenticationProvider;
		
		@Autowired
		NedNetAuthFailureHandler needAuthFailureHandler;
		
		@Autowired
		NeedNetAuthSuccessHandler needAuthSuccessHandler;

	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	                .authorizeRequests()
	                    .antMatchers("/resources/**", "/rest/register").permitAll()
	                    .antMatchers("/resources/**", "/rest/login").permitAll()
	                    .antMatchers("/resources/**", "/rest/test").permitAll()  
	                    .anyRequest().authenticated()
	                    .and()
	                    // We filter the api/login requests
	                    .addFilterBefore(new JWTLoginFilter("/rest/login", authenticationManager(),needAuthSuccessHandler,needAuthFailureHandler),
	                            UsernamePasswordAuthenticationFilter.class)
	                    // And filter other requests to check the presence of JWT in header
	                    .addFilterBefore(new JWTAuthenticationFilter(),
	                            UsernamePasswordAuthenticationFilter.class)
	                    
	                .logout()
	                    .permitAll().and().csrf().disable();
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    	//UserDetailsService userDetailsService = userDetailsService();
	    	auth.authenticationProvider(authenticationProvider);
	       // auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	    }
	}
}
