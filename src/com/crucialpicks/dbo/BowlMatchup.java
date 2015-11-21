
package com.crucialpicks.dbo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the bowl_matchup database table.
 * 
 */
@Entity
@Table(name = "bowl_matchup")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BowlMatchup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "bowl_matchup_id_gen", strategy = "increment")
	@GeneratedValue(generator = "bowl_matchup_id_gen")
	@Column(name = "bowl_matchup_id")
	private Integer bowlMatchupId;

	private String date;

	private String title;

	// bi-directional many-to-one association to CfbTeam
	@ManyToOne
	@JoinColumn(name = "team_a")
	private CfbTeam teamA;

	// bi-directional many-to-one association to CfbTeam
	@ManyToOne
	@JoinColumn(name = "team_b")
	private CfbTeam teamB;

	@Column(name = "team_a_spread")
	private String teamASpread;
	
	@Column(name = "team_b_spread")
	private String teamBSpread;

	// bi-directional many-to-one association to CfbTeam
	@ManyToOne
	@JoinColumn(name = "winning_team")
	private CfbTeam winningTeam;

	@Column(name = "lock_flag")
	private Boolean lockFlag;

	@Column(name = "team_a_score")
	private String teamAScore;

	@Column(name = "team_b_score")
	private String teamBScore;

	// bi-directional many-to-one association to BowlMatchup
	@OneToMany(mappedBy = "bowlMatchup", orphanRemoval = true)
	private List<BowlPick> bowlPicks = new ArrayList<BowlPick>();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_timestamp")
	private Date createTimestamp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_edit_timestamp")
	private Date lastEditTimestamp;

	public BowlMatchup() {
	}

	public Integer getBowlMatchupId() {
		return bowlMatchupId;
	}

	public void setBowlMatchupId(Integer bowlMatchupId) {
		this.bowlMatchupId = bowlMatchupId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CfbTeam getTeamA() {
		return teamA;
	}

	public void setTeamA(CfbTeam teamA) {
		this.teamA = teamA;
	}

	public CfbTeam getTeamB() {
		return teamB;
	}

	public void setTeamB(CfbTeam teamB) {
		this.teamB = teamB;
	}

	public String getTeamASpread() {
		return teamASpread;
	}

	public void setTeamASpread(String teamASpread) {
		this.teamASpread = teamASpread;
	}

	public String getTeamBSpread() {
		return teamBSpread;
	}

	public void setTeamBSpread(String teamBSpread) {
		this.teamBSpread = teamBSpread;
	}

	public CfbTeam getWinningTeam() {
		return winningTeam;
	}

	public void setWinningTeam(CfbTeam winningTeam) {
		this.winningTeam = winningTeam;
	}

	public Boolean getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(Boolean lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getTeamAScore() {
		return teamAScore;
	}

	public void setTeamAScore(String teamAScore) {
		this.teamAScore = teamAScore;
	}

	public String getTeamBScore() {
		return teamBScore;
	}

	public void setTeamBScore(String teamBScore) {
		this.teamBScore = teamBScore;
	}

	public List<BowlPick> getBowlPicks() {
		return bowlPicks;
	}

	public void setBowlPicks(List<BowlPick> bowlPicks) {
		this.bowlPicks = bowlPicks;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getLastEditTimestamp() {
		return lastEditTimestamp;
	}

	public void setLastEditTimestamp(Date lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}

}