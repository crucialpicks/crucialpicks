package com.crucialpicks.business;

import java.io.Serializable;

import com.crucialpicks.dbo.User;
import com.crucialpicks.managers.UserManager;

public class UserBo implements Serializable {
	private static final long serialVersionUID = 8314412361918032969L;

	private Integer userId;
	private String name;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private Boolean adminFlag;

	public UserBo(User user) {
		UserManager userMgr = new UserManager();
		this.userId = user.getUserId();
		if (user.getFirstName() != null) {
			this.name = user.getFirstName() + " " + user.getLastName();
		}
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUserName();
		this.email = user.getEmail();
		this.password = userMgr.decodePassword(user.getPassword());
		this.adminFlag = user.getAdminFlag();
	}

	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
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

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdminFlag() {
		return adminFlag;
	}
}