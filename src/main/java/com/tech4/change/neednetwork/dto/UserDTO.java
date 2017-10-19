package com.tech4.change.neednetwork.dto;

import java.util.List;



public class UserDTO {

	
	
	private Integer mobileNumber;
	private List<String> friendList;
	private List<NeedDTO> needsList;
	private String userName;
	private String displayName;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
