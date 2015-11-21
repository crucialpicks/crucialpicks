package com.crucialpicks.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crucialpicks.business.BowlMatchupBo;
import com.crucialpicks.business.BowlPickBo;
import com.crucialpicks.dbo.User;
import com.crucialpicks.managers.CfbTeamManager;
import com.crucialpicks.managers.UserManager;
import com.google.gson.Gson;

/**
 * Endpoint for retrieving a users bowl picks.
 * @author Steven
 */
@WebServlet(name = "GetUsersBowlPicks", urlPatterns = { "/servlets/GetUsersBowlPicks" })
public class GetUsersBowlPicks extends HttpServlet {
	private static final long serialVersionUID = 3879106622388028273L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userIdParam = request.getParameter("userId");
		Integer userId = new Integer(userIdParam);
		UserManager usrMgr = new UserManager();
		User user = usrMgr.getUserById(userId);

		BowlPickRetrievalServletResult result = new BowlPickRetrievalServletResult();
		CfbTeamManager cfbTeamMgr = new CfbTeamManager();
		result.matchups = cfbTeamMgr.getExistingMatchups();
		result.lockedPicks = cfbTeamMgr.getBowlPicksForUser(user, true);
		Gson gson = new Gson();
		PrintWriter outWriter = response.getWriter();
		outWriter.write(gson.toJson(result));
	}

	@SuppressWarnings("unused")
	private class BowlPickRetrievalServletResult {
		private List<BowlMatchupBo> matchups;
		private List<BowlPickBo> lockedPicks;
	}
}
