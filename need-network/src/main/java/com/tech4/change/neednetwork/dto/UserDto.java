package com.tech4.change.neednetwork.dto;
import java.util.List;

public class UserDto {
	private String mobileNumber;
	private List<String> friendList;
	private List<NeedDTO> needsList;
	private String userName;
	private String displayName;
	private String deviceID;
	
	
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
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
