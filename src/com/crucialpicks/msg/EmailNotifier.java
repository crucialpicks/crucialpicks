package com.crucialpicks.msg;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.crucialpicks.dbo.User;
import com.crucialpicks.managers.UserManager;

/**
 * 
 * @author Steven
 */
public class EmailNotifier {

	public Boolean notifyUserOfInvite(String email) {
		UserManager userManager = new UserManager();
		User cpAdmin = userManager.getUserByEmail("crucialpicks@gmail.com");
		final String username = cpAdmin.getEmail();
		final String password = userManager.decodePassword(cpAdmin.getPassword());
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("crucialpicks@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("You've Been Invited");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("You have been invited to join Crucial Picks.<br/>");
			stringBuilder.append("<a href='http://www.crucialpicks.com/signUp.jsp'>Go Here</a> to complete the sign up process.<br/>");
			stringBuilder.append("<br/><br/> -Crucial Picks Staff (Steven)");
			message.setContent(stringBuilder.toString(), "text/html; charset=utf-8");
			// message.setText(stringBuilder.toString());
			Transport.send(message);
			
			//TODO logging
			
			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
