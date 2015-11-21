package com.crucialpicks.business;

import com.crucialpicks.dbo.User;
import com.crucialpicks.managers.CfbTeamManager;

/**
 * 
 * @author Steven
 */
public class BowlPickScoreBo {
	Integer userId;
	String username;
	String userScore;
	
	public BowlPickScoreBo(User user){
		CfbTeamManager cfbTeamMgr = new CfbTeamManager();
		userId = user.getUserId();
		username = user.getUserName();
		userScore = cfbTeamMgr.getUserBowlPickTotalScore(user);
	}
}
