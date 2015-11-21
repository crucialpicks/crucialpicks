package com.crucialpicks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crucialpicks.business.UserBo;
import com.crucialpicks.managers.UserManager;
import com.google.gson.Gson;

/**
 * Endpoint for updating user information.
 * 
 * @author Steven
 */
@WebServlet(name = "UserProfileUpdateServlet", urlPatterns = { "/servlets/UserProfileUpdateServlet" })
public class UserProfileUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = -8985228460319758309L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("newPassword");
		
		UserManager userManager = new UserManager();
		UserBo userBo = userManager.getCurrentUserBo(request);
		userBo.setFirstName(firstName);
		userBo.setLastName(lastName);
		String resultString = "Name updated";
		if(password != null && password != ""){
			userBo.setPassword(password);
			resultString =  "First name, Last Name, and Password updated.";
		}

		userManager.updateUser(userBo);
		
		UserProfileUpdateResult result = new UserProfileUpdateResult();
		result.message = resultString;
		Gson gson = new Gson();
		PrintWriter outWriter = response.getWriter();
		outWriter.write(gson.toJson(result));
	}
	
	@SuppressWarnings("unused")
	private class UserProfileUpdateResult {
		private String message;
	}
}
