package com.crucialpicks.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.crucialpicks.hibernate.HibernateUtils;

/**
 * Application Lifecycle listener.
 * 
 * @author Steven
 */
@WebListener
public class StartupListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("****************************************");
		System.out.println("Crucial Picks Application Starting...");
		System.out.println("****************************************");

		HibernateUtils.initiateSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("****************************************");
		System.out.println("Crucial Picks Application Ending...");
		System.out.println("****************************************");
		
		HibernateUtils.shutdown();
	}
}