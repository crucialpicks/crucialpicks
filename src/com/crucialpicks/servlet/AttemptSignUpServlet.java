package com.crucialpicks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crucialpicks.managers.UserManager;
import com.google.gson.Gson;

/**
 * Attempts to sign up the new user.
 * 
 * @author Steven
 */
@WebServlet(name = "AttemptSignUpServlet", urlPatterns = { "/servlets/AttemptSignUpServlet" })
public class AttemptSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = -8969815449097297270L;

	// TODO superclass servlet with doWork that can simply return json
	// TODO error handling on param parse;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		SignUpResult result = new SignUpResult();

		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//TODO validate that the username is not already taken

		UserManager userManager = new UserManager();
		Boolean isValidCandidate = userManager.isSignupValidCandidate(email);
		if (!isValidCandidate) {
			result.errorMessage = "Either the given email has not been invited, or an account has already been created with the given email.";
			PrintWriter outWriter = response.getWriter();
			outWriter.write(gson.toJson(result));
			return;
		}

		userManager.signupNewUser(email, firstName, lastName, username, password);
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