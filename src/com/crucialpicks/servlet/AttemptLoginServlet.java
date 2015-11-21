package com.crucialpicks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.crucialpicks.core.SessionInfo;
import com.crucialpicks.dbo.User;
import com.crucialpicks.managers.UserManager;
import com.google.gson.Gson;

/**
 * Attempt Login for user.
 * @author Steven
 */
@WebServlet(name = "AttemptLoginServlet", urlPatterns = { "/servlets/AttemptLoginServlet" })
public class AttemptLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 4996895961255931949L;
	private Log log = LogFactory.getLog(AttemptLoginServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		SignUpResult result = new SignUpResult();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		log.debug("User [" + username + "] is attempting to log in.");

		UserManager userManager = new UserManager();
		Boolean isValidUserLogin = userManager.isValidUserLogin(username, password);
		if (!isValidUserLogin) {
			result.errorMessage = "Invalid Login.";
			PrintWriter outWriter = response.getWriter();
			outWriter.write(gson.toJson(result));
			return;
		}

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpSession tomcatSession = servletRequest.getSession();

		User user = userManager.getUserByUsername(username);
		SessionInfo sessionInfo = SessionInfo.createSessionInfoForTomcatSession(tomcatSession);
		sessionInfo.setUserId(user.getUserId());

		log.debug("User " + user.getUserName() + " has logged in.");

		result.success = true;
		PrintWriter outWriter = response.getWriter();
		outWriter.write(gson.toJson(result));
	}

	@SuppressWarnings("unused")
	private class SignUpResult {
		private String errorMessage;
		private Boolean success = false;
	}
}