package com.crucialpicks.dbo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the bowl_matchup database table.
 * 
 * @author Steven
 */
@Entity
@Table(name = "bowl_pick")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BowlPick implements Serializable {
	private static final long serialVersionUID = 868350224998441691L;

	@Id
	@GenericGenerator(name = "bowl_pick_id_gen", strategy = "increment")
	@GeneratedValue(generator = "bowl_pick_id_gen")
	@Column(name = "bowl_pick_id")
	private Integer bowlPickId;

	// bi-directional many-to-one association to BowlMatchup
	@ManyToOne
	@JoinColumn(name = "matchup_id")
	private BowlMatchup bowlMatchup;

	// bi-directional many-to-one association to CfbTeam
	@ManyToOne
	@JoinColumn(name = "selected_team")
	private CfbTeam selectedTeam;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_timestamp")
	private Date createTimestamp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_edit_timestamp")
	private Date lastEditTimestamp;

	public Integer getBowlPickId() {
		return bowlPickId;
	}

	public void setBowlPickId(Integer bowlPickId) {
		this.bowlPickId = bowlPickId;
	}

	public BowlMatchup getBowlMatchup() {
		return bowlMatchup;
	}

	public void setBowlMatchup(BowlMatchup bowlMatchup) {
		this.bowlMatchup = bowlMatchup;
	}

	public CfbTeam getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(CfbTeam selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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