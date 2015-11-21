package com.crucialpicks.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crucialpicks.business.UserBo;
import com.crucialpicks.managers.UserManager;
import com.crucialpicks.msg.EmailNotifier;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Endpoint for adding new users via email.
 * 
 * @author Steven
 */
@WebServlet(name = "CreateNewUsersServlet", urlPatterns = { "/servlets/CreateNewUsersServlet" })
public class CreateNewUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1991306854501292017L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newUserEmailListParam = request.getParameter("emailList");
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<String>>() {
		}.getType();
		List<String> emailList = gson.fromJson(newUserEmailListParam, listType);
		UserManager userManager = new UserManager();
		UserBo user = userManager.getCurrentUserBo(request);
		if(!user.getAdminFlag()){
			throw new SecurityException("Non-Admin attempting to create a new user.");
		}
		userManager.addNewUsers(emailList);
		EmailNotifier emailNotifier = new EmailNotifier();
		for(String email : emailList){
			emailNotifier.notifyUserOfInvite(email);
		}
		UserAddResult result = new UserAddResult();
		result.success = true;
		PrintWriter outWriter = response.getWriter();
		outWriter.write(gson.toJson(result));
	}

	@SuppressWarnings("unused")
	private class UserAddResult {
		private Boolean success = false;
	}
}
