package com.crucialpicks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crucialpicks.core.SessionInfo;
import com.google.gson.Gson;

/**
 * 
 * @author Steven
 */
@WebServlet(name = "LogoutServlet", urlPatterns = { "/servlets/LogoutServlet" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = -1560503827693320342L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		LogoutResult result = new LogoutResult();
		SessionInfo.destroySessionInfoForTomcatSession(request.getSession());
		result.success = true;
		PrintWriter outWriter = response.getWriter();
		outWriter.write(gson.toJson(result));
	}

	@SuppressWarnings("unused")
	private class LogoutResult {
		private String errorMessage;
		private Boolean success = false;
	}
}
