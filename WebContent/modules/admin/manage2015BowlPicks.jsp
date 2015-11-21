<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.crucialpicks.managers.CfbTeamManager"%>
<%@ page import="com.crucialpicks.business.BowlMatchupBo"%>
<%@ page import="com.crucialpicks.business.CfbTeamBo"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>

<%
	String cxtPath = request.getContextPath();
	CfbTeamManager cfbTeamMgr = new CfbTeamManager();
	List<BowlMatchupBo> existngMatchups = cfbTeamMgr.getExistingMatchups();
//	cfbTeamMgr.reOrderCfTeams();
	List<CfbTeamBo> teams = cfbTeamMgr.getAllCfbTeams();
	Gson gson = new Gson();
	String existingMatchupsString = gson.toJson(existngMatchups);
	request.setAttribute("cfbTeams", teams);
%>

<html>
<jsp:include page="/modules/navBar/navBar.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Crucial Picks - Manage 2015 Bowl Picks</title>

<script src="<%=cxtPath%>/js/admin/manageBowlPicks.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h4>Manage 2015 Bowl Picks</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table bowlMatchupTable">
					<thead>
						<tr>
							<th> Add / Remove</th>
							<th>Date</th>
							<th>Bowl Title</th>
							<th>Team Selection</th>
							<th>Spread</th>
							<th>Score</th>
							<th>Winner</th>
							<th>Lock</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<button class="btn btn-sm btn-success addRowBtn">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</div>
		</div>
		<button class="btn btn-sm btn-default saveBtn">Save</button>
	</div>
	<!-- /container -->

	<script>
		$(document).ready(function() {
			$.manageBowlPicks.init("<%=cxtPath%>",<%=existingMatchupsString%>);
		});
	</script>

	<!-- ******************************************************* -->
	<!-- 				Modals and Templates					 -->
	<!-- ******************************************************* -->
	<div class="hidden">
		<table class="newRowTemplateTable">
			<tr class="matchupRow">
				<td>
					<button class="btn btn-sm btn-danger deleteRowBtn">
						<span class="glyphicon glyphicon-minus"></span>
					</button>
				</td>
				<td class="dateCell"><input class="form-control input input-sm dateInput" type="text" style="cursor: pointer;" /></td>
				<td class="bowlTitleCell"><input class="form-control input input-sm titleInput" type="text" /></td>
				<td class="teamSelectionCell"><select class="form-control teamASelect">
						<option disabled selected></option>
						<c:forEach var="cfbTeam" items="${cfbTeams}">
							<option value="${cfbTeam.getCfbTeamId()}">${cfbTeam.getName()}</option>
						</c:forEach>
				</select> <select class="form-control teamBSelect">
						<option disabled selected></option>
						<c:forEach var="cfbTeam" items="${cfbTeams}">
							<option value="${cfbTeam.getCfbTeamId()}">${cfbTeam.getName()}</option>
						</c:forEach>
				</select></td>
				<td><input class="form-control input input-sm teamASpreadInput" type="text" /> <input
					class="form-control input input-sm teamBSpreadInput" type="text" /></td>
				<td><input class="form-control input input-sm teamAScoreInput" type="text" /> <input
					class="form-control input input-sm teamBScoreInput" type="text" /></td>
					<td>
					<input type="checkbox" class="teamAWinnerSelect"><br>
					<input type="checkbox" class="teamBWinnerSelect">
					</td>
				<td><input type="checkbox" class="lockSelect"></td>
			</tr>
		</table>
	</div>
	<!-- /hidden -->

	<div id="bowlManageSaveModal" class="modal fade" tabindex="-1">
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
</body>
</html>