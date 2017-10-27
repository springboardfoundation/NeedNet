package com.tech4.change.neednetwork.entity;


import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;


public class User  {
	
	public User() {
		super();
	}
	
	@Id
	private String id;
	
	
	
	@NotBlank
	@Indexed(unique = true)
	@Field(value="mobileNumber")
	private String mobileNumber;
	
	@NotBlank
	@Indexed(unique = true)
	@Field(value="username")
	private String username; // username is same as mobile no
	
	@Field(value="displayName")
	private String displayName;
	
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}

	private List<String> friendList;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumboer) {
		this.mobileNumber = mobileNumboer;
	}
	public List<String> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<String> friendList) {
		this.friendList = friendList;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
	
	
	
	

}
