<%@ page import="com.crucialpicks.managers.CfbTeamManager"%>
<%@ page import="com.crucialpicks.business.BowlPickScoreBo"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>

<%
	String cxtPath = request.getContextPath();
	CfbTeamManager cfbTeamMgr = new CfbTeamManager();
	List<BowlPickScoreBo> scores = cfbTeamMgr.getAllUsersBowlPickScores();

	Gson gson = new Gson();
	String bowlPickScoresJsonString = gson.toJson(scores);
%>

<link href="<%=cxtPath%>/lib/datatables/css/jquery.dataTables.min.css" rel="stylesheet">
<script src="<%=cxtPath%>/lib/datatables/js/jquery.dataTables.min.js"></script>
<script src="<%=cxtPath%>/js/myPicks/bowlScoreboard.js"></script>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h4>Scoreboard</h4>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<table class="table bowlScoreboardTable">
				<thead>
					<tr>
						<th>User</th>
						<th>Score</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>
<!-- /container -->


<script>
	$(document).ready(function() {
		$.bowlScoreboard.init("<%=cxtPath%>",<%=bowlPickScoresJsonString%>);
	});
</script>

<!-- ******************************************************* -->
<!-- 				Modals and Templates					 -->
<!-- ******************************************************* -->
<div class="hidden">
	<table class="scoreboardTemplateTable">
		<tr class="scoreboardRow">
			<td class="usernameCell"></td>
			<td class="scoreCell"></td>
		</tr>
	</table>
</div>
<!-- /hidden -->

<div id="userPicksModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3>Picks for user:</h3>
			</div>
			<div class="modal-body">
				<table class="table userPicksTable">
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
			<div class="modal-footer">
				<button class="btn btn-sm btn-default okBtn" data-dismiss="modal">OK</button>
			</div>
		</div>
	</div>
</div>