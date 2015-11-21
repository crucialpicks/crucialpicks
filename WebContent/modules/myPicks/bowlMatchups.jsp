<%@ page import="com.crucialpicks.managers.CfbTeamManager"%>
<%@ page import="com.crucialpicks.business.BowlMatchupBo"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>

<%
	String cxtPath = request.getContextPath();
	CfbTeamManager cfbTeamMgr = new CfbTeamManager();
	List<BowlMatchupBo> matchups = cfbTeamMgr.getExistingMatchups();
	Gson gson = new Gson();
	String matchupsJsonString = gson.toJson(matchups);
%>

<script src="<%=cxtPath%>/lib/chartsJs/Chart.min.js"></script>
<script src="<%=cxtPath%>/js/myPicks/matchups.js"></script>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h4>Bowl Matchups</h4>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<table class="table bowlMatchupsTable">
				<thead>
					<tr>
						<th>Date</th>
						<th>Bowl Title</th>
						<th>Teams</th>
						<th>Score</th>
						<th>Pick Percentage</th>
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
		$.matchups.init("<%=cxtPath%>",<%=matchupsJsonString%>);
	});
</script>

<!-- ******************************************************* -->
<!-- 				Modals and Templates					 -->
<!-- ******************************************************* -->
<div class="hidden">
	<table class="matchupTemplateTable">
		<tr class="matchupRow">
			<td class="dateCell"></td>
			<td class="bowlTitleCell"></td>
			<td class="teamsCell">
				<img class="teamAImage" src="" height="25" width="25">
				<label class="teamALabel"></label>
				<br>
				<img class="teamBImage" src="" height="25" width="25">
				<label class="teamBLabel"></label>
			</td>
			<td class="scoreCell">
				<label class="teamAScore"></label><BR>
				<label class="teamBScore"></label>
			</td>
			<td class="pickPercentageCell text-center">
				<canvas class="matchupPercentageChart" width="100" height="100"></canvas>
			</td>
		</tr>
	</table>
</div>
<!-- /hidden -->