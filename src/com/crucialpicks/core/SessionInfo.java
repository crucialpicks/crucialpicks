package com.crucialpicks.core;

import javax.servlet.http.HttpSession;

/**
 * Placholder for all user information to be stored on their Tomcat Session.
 * 
 * @author Steven
 */
public class SessionInfo {// TODO rename this to userSession
	private Integer userId;

	public static SessionInfo getSessionInfoFromSession(HttpSession tomcatSession) {
		SessionInfo sessionInfo = (SessionInfo) tomcatSession.getAttribute("sessionInfo");
		return sessionInfo;
	}

	public static SessionInfo createSessionInfoForTomcatSession(HttpSession tomcatSession) {
		SessionInfo sessionInfo = new SessionInfo();
		tomcatSession.setAttribute("sessionInfo", sessionInfo);
		return sessionInfo;
	}

	public static void destroySessionInfoForTomcatSession(HttpSession tomcatSession) {
		tomcatSession.setAttribute("sessionInfo", null);
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
