package com.crucialpicks.managers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.crucialpicks.business.UserBo;
import com.crucialpicks.core.SessionInfo;
import com.crucialpicks.dbo.User;
import com.crucialpicks.hibernate.HibernateUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author Steven
 */
public class UserManager {
	private Session session;

	public UserManager() {
		session = HibernateUtils.getSession();
	}

	/**
	 * Determine if the email address is on the invite list, and does not
	 * already associate with a valid user account.
	 * 
	 * @param email email candidate
	 * @return True if the given email address is a valid user candidate.
	 */
	public Boolean isSignupValidCandidate(String email) {
		User user = getUserByEmail(email);
		if (user == null) {
			return false;
		}

		if (user.getUserName() != null) {
			return false;
		}

		return true;
	}

	/**
	 * Lookup a user by email.
	 * 
	 * @param email the email.
	 * @return the user or null.
	 */
	@SuppressWarnings("unchecked")
	public User getUserByEmail(String email) {
		Criteria userCriteria = session.createCriteria(User.class);
		userCriteria.add(Restrictions.eq("email", email));
		List<User> userList = userCriteria.list();
		if (userList == null || userList.isEmpty()) {
			return null;
		}
		return userList.get(0);
	}

	/**
	 * Get the user by their ID.
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public User getUserById(Integer userId) {
		Criteria userCriteria = session.createCriteria(User.class);
		userCriteria.add(Restrictions.eq("userId", userId));
		List<User> userList = userCriteria.list();
		if (userList == null || userList.isEmpty()) {
			return null;
		}
		return userList.get(0);
	}

	/**
	 * Attempt user lookup by username.
	 * @param username
	 * @return the user or null
	 */
	@SuppressWarnings("unchecked")
	public User getUserByUsername(String username) {
		Criteria userCriteria = session.createCriteria(User.class);
		userCriteria.add(Restrictions.eq("userName", username).ignoreCase());
		List<User> userList = userCriteria.list();
		if (userList == null || userList.isEmpty()) {
			return null;
		}
		return userList.get(0);
	}

	/**
	 * @return all users
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		// TODO this is returning 10 fucknig users.... cache? wtf
		Criteria userCriteria = session.createCriteria(User.class);
		List<User> allUsers = userCriteria.list();
		return allUsers;
	}

	public List<UserBo> getAllUserBizObjs() {
		List<UserBo> users = new ArrayList<UserBo>();
		for (User user : getAllUsers()) {
			UserBo userBo = new UserBo(user);
			users.add(userBo);
		}
		return users;
	};

	/**
	 * Sign up a new user.
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param password
	 */
	public User signupNewUser(String email, String firstName, String lastName, String username, String password) {
		User user = getUserByEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUserName(username);
		user.setPassword(encodePassword(password));
		user.setLastEditTimestamp(new Date());
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		session.flush();
		return user;
	}

	/**
	 * Take plain text password and encode.
	 * @param password
	 * @return encoded password
	 */
	public String encodePassword(String password) {
		Random rand = new Random((new Date()).getTime());
		BASE64Encoder encoder = new BASE64Encoder();
		byte[] salt = new byte[8];
		rand.nextBytes(salt);
		String encrypted = encoder.encode(salt) + encoder.encode(password.getBytes());
		return encrypted;
	}

	/**
	 * Take the encrypted password and decode to plain text.
	 * @param encryptedPassword
	 * @return decoded password.
	 */
	public String decodePassword(String encryptedPassword) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			String cipher = encryptedPassword.substring(12);
			String decrypted = new String(decoder.decodeBuffer(cipher));
			return decrypted;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Confirm user has valid login credentials.
	 * @param username
	 * @param password
	 * @return true if valid
	 */
	public Boolean isValidUserLogin(String username, String password) {
		User user = getUserByUsername(username);
		if (user == null) {
			return false;
		}

		String encryptedPassword = user.getPassword();
		String decryptedPassword = decodePassword(encryptedPassword);

		if (password.equalsIgnoreCase(decryptedPassword)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Create a user Biz Obj from the current session
	 * @param servletRequest
	 * @return
	 */
	public UserBo getCurrentUserBo(HttpServletRequest servletRequest) {
		SessionInfo sessionInfo = SessionInfo.getSessionInfoFromSession(servletRequest.getSession());
		UserManager userMgr = new UserManager();
		User user = userMgr.getUserById(sessionInfo.getUserId());
		return new UserBo(user);
	}

	public void addNewUsers(List<String> emailList) {
		Transaction tx = session.beginTransaction();
		for (String email : emailList) {
			User user = new User();
			user.setEmail(email);
			user.setAdminFlag(false);
			user.setLastEditTimestamp(new Date());
			user.setCreateTimestamp(new Date());
			session.saveOrUpdate(user);
		}
		tx.commit();
		session.flush();
	}

}
