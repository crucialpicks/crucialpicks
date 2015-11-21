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

import com.crucialpicks.business.BowlMatchupBo;
import com.crucialpicks.managers.CfbTeamManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Endpoint for saving bowl matchups
 * @author Steven
 */
@WebServlet(name = "SaveBowlMatchupsServlet", urlPatterns = { "/servlets/SaveBowlMatchupsServlet" })
public class SaveBowlMatchupsServlet extends HttpServlet {
	private static final long serialVersionUID = -4123056118850841153L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String matchupListParam = request.getParameter("matchupList");
		String deletedMatchupIdsParam = request.getParameter("deletedMatchupIds");
		Gson gson = new Gson();
		Type listType = new TypeToken<ArrayList<BowlMatchupBo>>() {
		}.getType();
		List<BowlMatchupBo> matchups = gson.fromJson(matchupListParam, listType);

		List<Integer> deletedIds = new ArrayList<Integer>();
		if (deletedMatchupIdsParam != null && !deletedMatchupIdsParam.equalsIgnoreCase("")) {
			Type deleteIdsType = new TypeToken<ArrayList<Integer>>() {
			}.getType();
			deletedIds = gson.fromJson(deletedMatchupIdsParam, deleteIdsType);
		}

		CfbTeamManager cfbTeamManager = new CfbTeamManager();
		cfbTeamManager.saveAllBowlMatchups(matchups, deletedIds);
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