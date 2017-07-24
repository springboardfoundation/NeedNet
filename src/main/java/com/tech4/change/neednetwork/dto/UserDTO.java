package com.tech4.change.neednetwork.dto;

import java.util.List;



public class UserDTO {

	private String id;
	private String firstName;
	private String lastName;
	
	//@Id (unique = true)
	private String username;
	private String password;
	
	private String emailAddress;
	private Integer mobileNumber;
	private List<String> friendList;
	private List<NeedDTO> needsList;
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
	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public List<String> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<String> friendList) {
		this.friendList = friendList;
	}
	public List<NeedDTO> getNeedsList() {
		return needsList;
	}
	public void setNeedsList(List<NeedDTO> needsList) {
		this.needsList = needsList;
	}
	
	

}
