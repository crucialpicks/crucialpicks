package com.crucialpicks.business;

import com.crucialpicks.dbo.BowlPick;
import com.crucialpicks.managers.CfbTeamManager;

/**
 * Business object for a bowl pick.
 * 
 * @author Steven
 */
public class BowlPickBo {
	private Integer bowlPickId;
	private Integer bowlMatchupId;
	private Integer selectedTeamId;
	private Integer userId;
	private BowlPickStatus bowlPickStatus;

	public enum BowlPickStatus {
		OPEN, LOCKED, RIGHT, WRONG;
	}

	public BowlPickBo(BowlPick pick) {
		CfbTeamManager cfbTeamManager = new CfbTeamManager();
		this.bowlPickId = pick.getBowlPickId();
		this.bowlMatchupId = pick.getBowlMatchup().getBowlMatchupId();
		this.selectedTeamId = pick.getSelectedTeam().getCfbTeamId();
		this.userId = pick.getUser().getUserId();
		bowlPickStatus = cfbTeamManager.scoreBowlPick(pick);
	}

	public Integer getBowlPickId() {
		return bowlPickId;
	}

	public Integer getBowlMatchupId() {
		return bowlMatchupId;
	}

	public Integer getSelectedTeamId() {
		return selectedTeamId;
	}

	public Integer getUserId() {
		return userId;
	}

	public BowlPickStatus getBowlPickStatus() {
		return bowlPickStatus;
	}
}