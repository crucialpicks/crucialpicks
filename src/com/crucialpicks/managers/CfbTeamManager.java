package com.crucialpicks.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.crucialpicks.business.BowlMatchupBo;
import com.crucialpicks.business.BowlPickBo;
import com.crucialpicks.business.BowlPickBo.BowlPickStatus;
import com.crucialpicks.business.BowlPickScoreBo;
import com.crucialpicks.business.CfbTeamBo;
import com.crucialpicks.dbo.BowlMatchup;
import com.crucialpicks.dbo.BowlPick;
import com.crucialpicks.dbo.CfbTeam;
import com.crucialpicks.dbo.User;
import com.crucialpicks.hibernate.HibernateUtils;

/**
 * College Football Team Manager.
 * @author Steven
 */
public class CfbTeamManager {
	// TODO rename to college football picks mgr
	private Session session;

	public CfbTeamManager() {
		session = HibernateUtils.getSession();
	}

	/**
	 * Get Team by ID.
	 * @param cfbTeamId
	 * @return
	 */
	private CfbTeam getCfbTeamById(Integer cfbTeamId) {
		Criteria criteria = session.createCriteria(CfbTeam.class);
		criteria.add(Restrictions.eqOrIsNull("cfbTeamId", cfbTeamId));
		return (CfbTeam) criteria.list().get(0);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void reOrderCfTeams() {
		List<CfbTeamBo> teamBos = getAllCfbTeams();
		Collections.sort(teamBos);

		Criteria criteria = session.createCriteria(CfbTeam.class);
		criteria.addOrder(Order.asc("name").ignoreCase());
		List<CfbTeam> teams = criteria.list();
		Transaction tx = session.beginTransaction();
		for (CfbTeam team : teams) {
			session.delete(team);
		}
		tx.commit();
		session.flush();

		Transaction saveTx = session.beginTransaction();
		for (CfbTeamBo teamBo : teamBos) {
			CfbTeam team = new CfbTeam();
			team.setName(teamBo.getName());
			team.setLogo(teamBo.getLogoRaw());
			team.setColorAHex(teamBo.getColorAHex());
			team.setColorBHex(teamBo.getColorBHex());
			team.setLastEditTimestamp(new Date());
			team.setCreateTimestamp(new Date());
			session.save(team);
		}
		saveTx.commit();
		session.flush();
	}

	/**
	 * @return all cfb teams.
	 */
	@SuppressWarnings("unchecked")
	public List<CfbTeamBo> getAllCfbTeams() {
		Criteria criteria = session.createCriteria(CfbTeam.class);
		criteria.addOrder(Order.asc("name").ignoreCase());
		List<CfbTeam> teams = criteria.list();
		List<CfbTeamBo> bizObjs = new ArrayList<CfbTeamBo>();
		for (CfbTeam team : teams) {
			CfbTeamBo bo = new CfbTeamBo(team);
			bizObjs.add(bo);
		}

		return bizObjs;
	}

	/**
	 * @return all bowl matchups.
	 */
	@SuppressWarnings("unchecked")
	public List<BowlMatchup> getAllBowlMatchups() {
		Criteria criteria = session.createCriteria(BowlMatchup.class);
		criteria.setCacheable(false);
		return criteria.list();
	}

	/**
	 * Save the bowl matchups.
	 * @param matchupBizObjs
	 * @param deletedIds
	 */
	public void saveAllBowlMatchups(List<BowlMatchupBo> matchupBizObjs, List<Integer> deletedIds) {
		Transaction clearPicksTx = session.beginTransaction();
		for (Integer matchupIdToDelete : deletedIds) {
			BowlMatchup matchup = getMatchupById(matchupIdToDelete);
			matchup.getBowlPicks().clear();
			session.save(matchup);
		}
		clearPicksTx.commit();
		session.flush();

		Transaction deleteMatchupTx = session.beginTransaction();
		for (Integer matchupIdToDelete : deletedIds) {
			BowlMatchup matchup = getMatchupById(matchupIdToDelete);
			session.delete(matchup);
		}
		deleteMatchupTx.commit();
		session.flush();

		Transaction saveTransaction = session.beginTransaction();
		for (BowlMatchupBo matchupBo : matchupBizObjs) {
			BowlMatchup matchup = null;
			if (matchupBo.getMatchupId() != null) {
				matchup = this.getMatchupById(matchupBo.getMatchupId());
			} else {
				matchup = new BowlMatchup();
			}

			matchup.setDate(matchupBo.getDate());
			matchup.setTitle(matchupBo.getTitle());
			matchup.setTeamA(getCfbTeamById(matchupBo.getTeamA().getCfbTeamId()));
			matchup.setTeamB(getCfbTeamById(matchupBo.getTeamB().getCfbTeamId()));
			matchup.setTeamASpread(matchupBo.getTeamASpread());
			matchup.setTeamBSpread(matchupBo.getTeamBSpread());
			matchup.setTeamAScore(matchupBo.getTeamAScore());
			matchup.setTeamBScore(matchupBo.getTeamBScore());
			if (matchupBo.getWinningTeam() != null) {
				matchup.setWinningTeam(getCfbTeamById(matchupBo.getWinningTeam().getCfbTeamId()));
			}
			matchup.setLockFlag(matchupBo.getLockFlag());
			matchup.setLastEditTimestamp(new Date());
			matchup.setCreateTimestamp(new Date());
			session.saveOrUpdate(matchup);
		}
		saveTransaction.commit();
		session.flush();
	}

	/**
	 * @return List of existing matchups.
	 */
	public List<BowlMatchupBo> getExistingMatchups() {
		List<BowlMatchupBo> existingMatchups = new ArrayList<BowlMatchupBo>();
		List<BowlMatchup> matchups = getAllBowlMatchups();
		for (BowlMatchup matchup : matchups) {
			BowlMatchupBo bo = new BowlMatchupBo(matchup);
			existingMatchups.add(bo);
		}
		return existingMatchups;
	}
	
	/**
	 * 
	 * @param user
	 * @param onlyLockedOrScored
	 * @return
	 */
	public List<BowlPickBo> getBowlPicksForUser(User user, Boolean onlyLockedOrScored) {
		List<BowlPickBo> bowlPicks = new ArrayList<BowlPickBo>();
		for (BowlPick pick : user.getBowlPicks()) {
			BowlPickBo bo = new BowlPickBo(pick);
			if(bo.getBowlPickStatus() == BowlPickStatus.OPEN){
				continue;
			}
			bowlPicks.add(bo);
		}
		return bowlPicks;
	}

	/**
	 * @param bowlMatchupId
	 * @return BowlMatchup for the given ID.
	 */
	public BowlMatchup getMatchupById(Integer bowlMatchupId) {
		Criteria criteria = session.createCriteria(BowlMatchup.class);
		criteria.add(Restrictions.eq("bowlMatchupId", bowlMatchupId));
		criteria.setCacheable(false);
		return (BowlMatchup) criteria.list().get(0);
	}

	public BowlPick getBowlPickById(Integer bowlPickId) {
		Criteria criteria = session.createCriteria(BowlPick.class);
		criteria.add(Restrictions.eq("bowlPickId", bowlPickId));
		criteria.setCacheable(false);
		return (BowlPick) criteria.list().get(0);
	}

	/**
	 * Save the bowl picks
	 */
	public void saveBowlPicks(Integer userId, List<BowlPickBo> bowlPicks) {
		UserManager userMgr = new UserManager();
		User user = userMgr.getUserById(userId);

		Transaction saveTransaction = session.beginTransaction();
		for (BowlPickBo bowlPickBo : bowlPicks) {
			BowlMatchup matchup = getMatchupById(bowlPickBo.getBowlMatchupId());
			if (matchup.getLockFlag() == true) {
				continue;
			}

			BowlPick pick = null;
			if (bowlPickBo.getBowlPickId() != null) {
				pick = getBowlPickById(bowlPickBo.getBowlPickId());
			} else {
				pick = new BowlPick();
			}
			pick.setBowlMatchup(getMatchupById(bowlPickBo.getBowlMatchupId()));
			pick.setSelectedTeam(getCfbTeamById(bowlPickBo.getSelectedTeamId()));
			pick.setUser(user);
			pick.setLastEditTimestamp(new Date());
			pick.setCreateTimestamp(new Date());
			user.getBowlPicks().add(pick);

			if (pick.getBowlMatchup().getLockFlag() == true) {
				continue;
			}

			session.saveOrUpdate(pick);
		}
		saveTransaction.commit();
		session.flush();
	}

	/**
	 * @return
	 */
	public List<BowlPickScoreBo> getAllUsersBowlPickScores() {
		List<BowlPickScoreBo> results = new ArrayList<BowlPickScoreBo>();
		UserManager usrMgr = new UserManager();
		for (User user : usrMgr.getAllUsers()) {
			if (user.getBowlPicks().isEmpty()) {
				continue;
			}
			BowlPickScoreBo pickScore = new BowlPickScoreBo(user);
			results.add(pickScore);
		}
		return results;
	}

	/**
	 * 
	 */
	public String getUserBowlPickTotalScore(User user) {
		Integer pointsPossible = 0;
		for (BowlMatchup matchup : this.getAllBowlMatchups()) {
			if (matchup.getWinningTeam() != null) {
				pointsPossible++;
			}
		}

		Integer points = 0;
		for (BowlPick pick : user.getBowlPicks()) {
			CfbTeam pickedTeam = pick.getSelectedTeam();
			CfbTeam winningTeam = pick.getBowlMatchup().getWinningTeam();
			if (pickedTeam != null && winningTeam != null) {
				if (pickedTeam.getCfbTeamId() == winningTeam.getCfbTeamId()) {
					points++;
				}
			}
		}

		return points + " / " + pointsPossible;
	}

	/**
	 * @param bowlPick
	 * @return Scored pick status
	 */
	public BowlPickStatus scoreBowlPick(BowlPick bowlPick) {
		BowlMatchup matchup = bowlPick.getBowlMatchup();
		if (matchup.getWinningTeam() == null && matchup.getLockFlag() == false) {
			return BowlPickStatus.OPEN;
		}

		if (matchup.getWinningTeam() == null && matchup.getLockFlag() == true) {
			return BowlPickStatus.LOCKED;
		}

		if (matchup.getWinningTeam().getCfbTeamId() == bowlPick.getSelectedTeam().getCfbTeamId()) {
			return BowlPickStatus.RIGHT;
		}

		return BowlPickStatus.WRONG;
	}
}
