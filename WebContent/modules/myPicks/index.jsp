<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String cxtPath = request.getContextPath();
%>
<html>
<jsp:include page="/modules/navBar/navBar.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Crucial Picks - Bowl Picks 2015</title>
</head>
<body>
	<div class="container">
		<ul class="nav nav-pills">
			<li class="active"><a href="#editBowlPicksTab" data-toggle="tab">My Bowl Picks</a></li>
			<li class=""><a href="#bowlScoreboardTab" data-toggle="tab">Scoreboard</a></li>
			<li class=""><a href="#bowlMatchupTab" data-toggle="tab">Matchups</a></li>
		</ul>
		<div id="sloTabContent" class="tab-content">
			<div class="tab-pane fade active in" id="editBowlPicksTab">
				<jsp:include page="editBowlPicks.jsp" />
			</div>
			<div class="tab-pane fade" id="bowlScoreboardTab">
				<jsp:include page="bowlScoreboard.jsp" />
			</div>
			<div class="tab-pane fade" id="bowlMatchupTab">
				<jsp:include page="bowlMatchups.jsp" />
			</div>			
		</div>
	</div>
	<!-- /container -->
</body>
</html>