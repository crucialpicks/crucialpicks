package com.crucialpicks.core;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simply user tomcat session.
 * @author Steven
 */
public class SessionListener implements HttpSessionListener {
	private static Log log = LogFactory.getLog(SessionListener.class);
	private static Integer sessionCount = 0;

	public void sessionCreated(HttpSessionEvent event) {
		synchronized (this) {
			sessionCount++;
		}

		log.debug("Session Created: " + event.getSession().getId());
		log.debug("Total Sessions: " + sessionCount);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		synchronized (this) {
			sessionCount--;
		}
		log.debug("Session Destroyed: " + event.getSession().getId());
		log.debug("Total Sessions: " + sessionCount);
	}

	public static Integer getSessionCount() {
		return sessionCount;
	}

}