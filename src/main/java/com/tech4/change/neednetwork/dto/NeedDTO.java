package com.tech4.change.neednetwork.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class NeedDTO {

	
private String id;
	
	private String description;
	private String title;
	private Date  targetDate; // days 
	private String location; 
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    
    private String createdBy;
    private List<String> users;  
    

    @Override
    public String toString() {
    	return "description: '" + this.description + "', title: '" + this.title + "', location: '" + this.location +"', Users:"+this.users+"', TargetDate: '" + this.targetDate + "'";
    	
    }

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	
}
