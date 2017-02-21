package com.tech4.change.neednetwork.entity;



import org.springframework.data.annotation.Id;
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

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(int targetAmount) {
		this.targetAmount = targetAmount;
	}

	public int getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(int currentAmount) {
		this.currentAmount = currentAmount;
	}

	@Field(value="createdBy")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
	private String status;
	
	private int targetAmount;
	
    private	int currentAmount;
    
    private String createdBy;
    
   
	
	
	

}
