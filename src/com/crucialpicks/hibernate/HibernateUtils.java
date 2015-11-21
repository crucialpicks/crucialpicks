package com.crucialpicks.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.stat.Statistics;

import com.crucialpicks.dbo.BowlMatchup;
import com.crucialpicks.dbo.BowlPick;
import com.crucialpicks.dbo.CfbTeam;
import com.crucialpicks.dbo.User;

/**
 * Houses all the hibernate configuration and maangement.
 * @author Steven
 */
public class HibernateUtils {
	private static Log log = LogFactory.getLog(HibernateUtils.class);

	private static Configuration config;
	private static SessionFactory sessionFactory;
	private static Statistics statistics;
	// Thread safe, one instance of a hibernate session per server requests.
	private static ThreadLocal<Session> session = new ThreadLocal<Session>();

	/**
	 * Load configuration, setup entiites, create hibernate session factory,
	 * enable statistics.
	 */
	public static void initiateSessionFactory() {
		config = new Configuration().configure("hibernate.cfg.xml");
		config.setProperty("hibernate.show_sql", "true");

		// TODO read these from persistence.xml
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(CfbTeam.class);
		config.addAnnotatedClass(BowlMatchup.class);
		config.addAnnotatedClass(BowlPick.class);
		
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
		builder.applySettings(config.getProperties());
		ServiceRegistry serviceRegistry = builder.build();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		statistics = sessionFactory.getStatistics();
		statistics.setStatisticsEnabled(true);

		log.debug("Initialized.");
	}

	/**
	 * Shut everything down.
	 */
	public static void shutdown() {
		config = null;
		sessionFactory.close();
		closeSession();
		log.debug("Shut Down.");
	}

	/**
	 * @return the session.
	 */
	public static Session getSession() {
		if (session.get() == null) {
			Session newSession = sessionFactory.openSession();
			session.set(newSession);
		}

		return session.get();
	}

	/**
	 * Open the session
	 * @return new Session.
	 */
	public static Session openSession() {
		return sessionFactory.openSession();
	}

	/**
	 * Close the session.
	 */
	public static void closeSession() {
		if (session.get() != null) {
			session.get().flush();
			session.get().close();
			session.set(null);
		}
	}

	/**
	 * @return Returns the count of open sessions.
	 */
	public static Long getOpenSessionCount() {
		return statistics.getSessionOpenCount() - statistics.getSessionCloseCount();
	}
}
