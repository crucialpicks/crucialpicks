package com.crucialpicks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crucialpicks.dbo.User;
import com.crucialpicks.managers.UserManager;
import com.crucialpicks.msg.EmailNotifier;
import com.google.gson.Gson;

/**
 * Endpoint for sending username and password via email.
 * 
 * @author Steven
 */
@WebServlet(name = "SendUnPwReminderServlet", urlPatterns = { "/servlets/SendUnPwReminderServlet" })
public class SendUnPwReminderServlet extends HttpServlet {
	private static final long serialVersionUID = 524699664039162470L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		UserManager userManager = new UserManager();
		User user = userManager.getUserByEmail(email);

		EmailNotifier emailNotifier = new EmailNotifier();
		emailNotifier.sendUnPwReminder(email, user.getUserName(), userManager.decodePassword(user.getPassword()));

		UnPwReminderResult result = new UnPwReminderResult();
		result.success = true;
		PrintWriter outWriter = response.getWriter();
		Gson gson = new Gson();
		outWriter.write(gson.toJson(result));
	}

	private class UnPwReminderResult {
		private Boolean success = false;
	}
}
