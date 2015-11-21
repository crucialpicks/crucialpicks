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
import javax.servlet.http.HttpSession;

import com.crucialpicks.business.BowlPickBo;
import com.crucialpicks.core.SessionInfo;
import com.crucialpicks.managers.CfbTeamManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Endpoint for saving bowl picks.
 * 
 * @author Steven
 */
@WebServlet(name = "SaveBowlPicksServlet", urlPatterns = { "/servlets/SaveBowlPicksServlet" })
public class SaveBowlPicksServlet extends HttpServlet {
	private static final long serialVersionUID = -4123056118850841153L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bowlPicksListParam = request.getParameter("bowlPicks");
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<BowlPickBo>>() {
		}.getType();
		List<BowlPickBo> bowlPicks = gson.fromJson(bowlPicksListParam, listType);

		HttpSession tomcatSession = request.getSession();
		SessionInfo sessionInfo = SessionInfo.getSessionInfoFromSession(tomcatSession);

		CfbTeamManager cfbTeamManager = new CfbTeamManager();
		cfbTeamManager.saveBowlPicks(sessionInfo.getUserId(), bowlPicks);
		MatchupSaveResult result = new MatchupSaveResult();
		result.success = true;
		PrintWriter outWriter = response.getWriter();
		outWriter.write(gson.toJson(result));
	}

	@SuppressWarnings("unused")
	private class MatchupSaveResult {
		private Boolean success = false;
	}
}