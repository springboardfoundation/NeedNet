package com.tech4.change.neednetwork.entity;


import java.util.List;


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
	@Field (value="firstName")
	private String firstName;
	@Field(value="lastName")
	private String lastName;
	
	@NotBlank
	@Indexed(unique = true)
	@Field (value="username")
	private String username;
	@Field(value="password")
	private String password;
	@Email
	@Field(value="emailAddress")
	private String emailAddress;
	@Field(value="mobileNumber")
	private Integer mobileNumber;
	private List<String> friendList;
	
	private List<Need> needsList;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Integer getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Integer mobileNumboer) {
		this.mobileNumber = mobileNumboer;
	}
	public List<String> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<String> friendList) {
		this.friendList = friendList;
	}
	public List<Need> getNeedsList() {
		return needsList;
	}
	public void setNeedsList(List<Need> needsList) {
		this.needsList = needsList;
	}
	
	
	
	

}
