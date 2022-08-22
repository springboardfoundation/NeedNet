package com.tech4.change.neednetwork.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech4.change.neednetwork.data.repository.NeedRepository;
import com.tech4.change.neednetwork.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

  
	private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

 

	public JWTLoginFilter(String url, AuthenticationManager authManager,AuthenticationSuccessHandler successHandler,AuthenticationFailureHandler failureHandler) {
    super(new AntPathRequestMatcher(url));
    setAuthenticationManager(authManager);
    this.successHandler =successHandler;
    this.failureHandler = failureHandler;
    
  }
  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException, IOException, ServletException {
    UserDTO creds = new ObjectMapper()
        .readValue(req.getInputStream(), UserDTO.class);
    return getAuthenticationManager().authenticate(
        new UsernamePasswordAuthenticationToken(
            creds.getMobileNumber().toString(),
            creds.getMobileNumber().toString(),
            Collections.emptyList()
        )
    );
  }
  @Override
  protected void successfulAuthentication(
      HttpServletRequest req,
      HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {
   
	 // TokenAuthenticationService
     //   .addAuthentication(res, auth.getName());
	  successHandler.onAuthenticationSuccess(req, res, auth);
	  
  }
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
          AuthenticationException failed) throws IOException, ServletException {
      
      failureHandler.onAuthenticationFailure(request, response, failed);
  }
}
