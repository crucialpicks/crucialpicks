package com.crucialpicks.business;

import org.apache.tomcat.util.codec.binary.Base64;

import com.crucialpicks.dbo.CfbTeam;

/**
 * Biz Obj for College Footbal Team.
 * @author Steven
 */
public class CfbTeamBo implements Comparable<CfbTeamBo> {

	private int cfbTeamId;
	private String name;
	private String colorAHex;
	private String colorBHex;
	private String logo;
	private transient byte[] logoRaw;

	public CfbTeamBo(CfbTeam cfbTeam) {
		this.cfbTeamId = cfbTeam.getCfbTeamId();
		this.name = cfbTeam.getName();
		this.colorAHex = cfbTeam.getColorAHex();
		this.colorBHex = cfbTeam.getColorBHex();

		byte[] baseArray = new byte[cfbTeam.getLogo().length];
		int j = 0;
		for (Byte b : cfbTeam.getLogo())
			baseArray[j++] = b.byteValue();
		logo = Base64.encodeBase64String(baseArray);

		logoRaw = cfbTeam.getLogo();
	}

	public int getCfbTeamId() {
		return cfbTeamId;
	}

	public String getName() {
		return name;
	}

	public String getColorAHex() {
		return colorAHex;
	}

	public String getColorBHex() {
		return colorBHex;
	}

	public String getLogo() {
		return logo;
	}

	public byte[] getLogoRaw() {
		return logoRaw;
	}

	@Override
	public int compareTo(CfbTeamBo o) {
		return this.name.compareTo(o.getName());
	}
}