package com.crucialpicks.business;

import java.text.NumberFormat;

import com.crucialpicks.dbo.BowlMatchup;
import com.crucialpicks.dbo.BowlPick;

/**
 * Business object for a bowl matchup.
 * @author Steven
 */
public class BowlMatchupBo {
	private Integer matchupId;
	private String date;
	private String title;
	private CfbTeamBo teamA;
	private CfbTeamBo teamB;
	private String teamASpread;
	private String teamBSpread;
	private CfbTeamBo winningTeam;
	private Boolean lockFlag;
	private String teamAScore;
	private String teamBScore;
	private Double teamAPickPercentage = 0.0;
	private Double teamBPickPercentage = 0.0;
	private String teamAPickPercentageString;
	private String teamBPickPercentageString;

	public BowlMatchupBo(BowlMatchup bowlMatchup) {
		this.matchupId = bowlMatchup.getBowlMatchupId();
		this.date = bowlMatchup.getDate();
		this.title = bowlMatchup.getTitle();
		this.teamA = new CfbTeamBo(bowlMatchup.getTeamA());
		this.teamB = new CfbTeamBo(bowlMatchup.getTeamB());
		teamASpread = bowlMatchup.getTeamASpread();
		teamBSpread = bowlMatchup.getTeamBSpread();
		if (bowlMatchup.getWinningTeam() != null) {
			winningTeam = new CfbTeamBo(bowlMatchup.getWinningTeam());
		}
		lockFlag = bowlMatchup.getLockFlag();
		teamAScore = bowlMatchup.getTeamAScore();
		teamBScore = bowlMatchup.getTeamBScore();

		Integer teamACount = 0;
		Integer teamBCount = 0;
		Integer total = bowlMatchup.getBowlPicks().size();
		for(BowlPick pick : bowlMatchup.getBowlPicks()){
			if(pick.getSelectedTeam().getCfbTeamId() == bowlMatchup.getTeamA().getCfbTeamId()){
				teamACount++;
			}else if(pick.getSelectedTeam().getCfbTeamId() == bowlMatchup.getTeamB().getCfbTeamId()){
				teamBCount++;
			}
		}
		
		if(total!=0){
			NumberFormat defaultFormat = NumberFormat.getPercentInstance();
			defaultFormat.setMinimumFractionDigits(1);
			
			teamAPickPercentage = (double)teamACount / (double)total;
			teamBPickPercentage = (double)teamBCount / (double)total;
			teamAPickPercentageString = defaultFormat.format(teamAPickPercentage);
			teamBPickPercentageString = defaultFormat.format(teamBPickPercentage);
		}
	}

	public Integer getMatchupId() {
		return matchupId;
	}

	public String getDate() {
		return date;
	}

	public String getTitle() {
		return title;
	}

	public CfbTeamBo getTeamA() {
		return teamA;
	}

	public CfbTeamBo getTeamB() {
		return teamB;
	}

	public String getTeamASpread() {
		return teamASpread;
	}

	public String getTeamBSpread() {
		return teamBSpread;
	}

	public CfbTeamBo getWinningTeam() {
		return winningTeam;
	}

	public Boolean getLockFlag() {
		return lockFlag;
	}

	public String getTeamAScore() {
		return teamAScore;
	}

	public String getTeamBScore() {
		return teamBScore;
	}

	public String getTeamAPickPercentage() {
		return teamAPickPercentageString;
	}
	
	public String getTeamBPickPercentage() {
		return teamBPickPercentageString;
	}
}