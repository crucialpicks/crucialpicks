package com.crucialpicks.business;

import java.io.Serializable;

import com.crucialpicks.dbo.User;

public class UserBo implements Serializable{
	private static final long serialVersionUID = 8314412361918032969L;
	
	private Integer userId;
	private String name;
	private String username;
	private String email;
	private Boolean adminFlag;

	public UserBo(User user) {
		this.userId = user.getUserId();
		if(user.getFirstName() != null){
			this.name = user.getFirstName() + " " + user.getLastName();
		}
		this.username = user.getUserName();
		this.email = user.getEmail();
		this.adminFlag = user.getAdminFlag();
	}

	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Boolean getAdminFlag() {
		return adminFlag;
	}
}