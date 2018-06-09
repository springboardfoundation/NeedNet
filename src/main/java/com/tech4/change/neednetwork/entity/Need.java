package com.tech4.change.neednetwork.entity;



import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;


public class Need {
	
	public Need() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	private String id;
	
	
	@Field(value="description")
	private String description;
	
	public String getId() {
		return id;
	}
	
	public String title;

	public String goal;

	
	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	
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

	private Date  targetDate; // days 
	private String location; 

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	@Field(value="createdBy")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    
    public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public Set<String> getOtherUsers() {
		return otherUsers;
	}

	public void setOtherUsers(Set<String> otherUsers) {
		this.otherUsers = otherUsers;
	}

	private String createdBy;
    private List<String> users;
    private Set<String> otherUsers;
    
   
}
