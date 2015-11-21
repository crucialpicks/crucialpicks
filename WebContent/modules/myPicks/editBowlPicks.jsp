<%@ page import="com.crucialpicks.managers.CfbTeamManager"%>
<%@ page import="com.crucialpicks.managers.UserManager"%>
<%@ page import="com.crucialpicks.business.BowlMatchupBo"%>
<%@ page import="com.crucialpicks.business.BowlPickBo"%>
<%@ page import="com.crucialpicks.core.SessionInfo"%>
<%@ page import="com.crucialpicks.dbo.User"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>

<%
	String cxtPath = request.getContextPath();
	CfbTeamManager cfbTeamMgr = new CfbTeamManager();
	List<BowlMatchupBo> matchups = cfbTeamMgr.getExistingMatchups();
	SessionInfo sessionInfo = SessionInfo.getSessionInfoFromSession(session);
	UserManager usrMgr = new UserManager();
	User user = usrMgr.getUserById(sessionInfo.getUserId());
	List<BowlPickBo> picks = cfbTeamMgr.getBowlPicksForUser(user, false);
	
	Gson gson = new Gson();
	String matchupsJsonString = gson.toJson(matchups);
	String bowlPicksJsonString = gson.toJson(picks);
%>

<script src="<%=cxtPath%>/js/myPicks/editBowlPicks.js"></script>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h4>My 2015 Bowl Picks</h4>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<table class="table bowlPicksTable">
				<thead>
					<tr>
						<th style="width: 20%;">Date</th>
						<th style="width: 30%;">Bowl Title</th>
						<th style="width: 30%;">Team Selection</th>
						<th>Score</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row" style="margin-bottom:50px;">
		<div class="col-md-5"></div>
		<div class="col-md-2">		
			<button class="btn btn-sm btn-default saveBtn">Save</button>
		</div>
		<div class="col-md-5"></div>
	</div>
</div>
<!-- /container -->

<script>
	$(document).ready(function() {
		$.editBowlPicks.init("<%=cxtPath%>",<%=matchupsJsonString%>, <%=bowlPicksJsonString%>);
	});
</script>

<!-- ******************************************************* -->
<!-- 				Modals and Templates					 -->
<!-- ******************************************************* -->
<div class="hidden">
	<table class="newRowTemplateTable">
		<tr class="bowlPickRow">
			<td class="dateCell"></td>
			<td class="bowlTitleCell"></td>
			<td class="teamSelectionCell">
				<input class="teamARadio" type="radio" name="" value="">
				<img class="teamAImage" src="" height="25" width="25">
				<label class="teamALabel"></label>
				<br>
				<input class="teamBRadio" type="radio" name="" value="">
				<img class="teamBImage" src="" height="25" width="25">
				<label class="teamBLabel"></label>
			</td>
			<td class="scoreCell">
				<label class="teamAScore"></label><BR>
				<label class="teamBScore"></label>
			</td>
			<td class="statusCell text-center">
			
			</td>
		</tr>
	</table>
</div>
<!-- /hidden -->
	
<div id="bowlPickSaveModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3></h3>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-default okBtn" data-dismiss="modal">OK</button>
			</div>
		</div>
	</div>
</div>	