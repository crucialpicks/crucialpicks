package com.crucialpicks.dbo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the cfb_team database table.
 * 
 */
@Entity
@Table(name = "cfb_team")
public class CfbTeam implements Serializable {
	private static final long serialVersionUID = -9164406885323500915L;

	@Id
	@GenericGenerator(name="cfb_team_id_gen" , strategy="increment")
	@GeneratedValue(generator="cfb_team_id_gen")
	@Column(name = "cfb_team_id")
	private int cfbTeamId;

	@Column(name = "color_a_hex")
	private String colorAHex;

	@Column(name = "color_b_hex")
	private String colorBHex;

	// bi-directional many-to-one association to BowlMatchup
	@OneToMany(mappedBy = "teamA")
	private List<BowlMatchup> bowlMatchups1;

	// bi-directional many-to-one association to BowlMatchup
	@OneToMany(mappedBy = "teamB")
	private List<BowlMatchup> bowlMatchups2;

	// bi-directional many-to-one association to BowlMatchup
	@OneToMany(mappedBy = "winningTeam")
	private List<BowlMatchup> bowlMatchups3;
	
	// bi-directional many-to-one association to BowlPick
	@OneToMany(mappedBy = "selectedTeam")
	private List<BowlPick> bowlPicks;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_timestamp")
	private Date createTimestamp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_edit_timestamp")
	private Date lastEditTimestamp;

	@Lob
	private byte[] logo;

	private String name;

	public CfbTeam() {
	}

	public int getCfbTeamId() {
		return this.cfbTeamId;
	}

	public void setCfbTeamId(int cfbTeamId) {
		this.cfbTeamId = cfbTeamId;
	}

	public String getColorAHex() {
		return this.colorAHex;
	}

	public void setColorAHex(String colorAHex) {
		this.colorAHex = colorAHex;
	}

	public String getColorBHex() {
		return this.colorBHex;
	}

	public void setColorBHex(String colorBHex) {
		this.colorBHex = colorBHex;
	}

	public Date getCreateTimestamp() {
		return this.createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getLastEditTimestamp() {
		return this.lastEditTimestamp;
	}

	public void setLastEditTimestamp(Date lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}

	public byte[] getLogo() {
		return this.logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}