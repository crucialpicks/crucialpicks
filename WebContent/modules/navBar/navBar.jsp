<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.crucialpicks.managers.UserManager"%>
<%@ page import="com.crucialpicks.business.UserBo"%>
<%@ page import="com.google.gson.Gson"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	UserManager userMgr = new UserManager();
	String cxtPath = request.getContextPath();
	UserBo userBo = userMgr.getCurrentUserBo(request);
	Gson gson = new Gson();
	String userBoString = gson.toJson(userBo);
%>
<link href="<%=cxtPath%>/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=cxtPath%>/lib/bootstrap/css/templates/navbar-fixed-top.css" rel="stylesheet">
<link href="<%=cxtPath%>/lib/jquery/css/jquery-ui.min.css" rel="stylesheet">
<link href="<%=cxtPath%>/css/crucialPicks.css" rel="stylesheet">

<script src="<%=cxtPath%>/lib/jquery/jquery-1.11.3.min.js"></script>
<script src="<%=cxtPath%>/lib/jquery/jquery-ui.min.js"></script>
<script src="<%=cxtPath%>/lib/bootstrap/js/bootstrap.min.js"></script>

<script src="<%=cxtPath%>/js/core/coreUtils.js"></script>
<script src="<%=cxtPath%>/js/navBar/navBar.js"></script>

<nav class="navbar navbar-default navbar-fixed-top hidden">
<div class="container">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
			aria-expanded="false" aria-controls="navbar">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<img src="<%=cxtPath%>/img/logo.png">
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav">
			<li class="activeXXX"><a href="<%=cxtPath%>/index.jsp">Home</a></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
				aria-haspopup="true" aria-expanded="false">My Picks<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<%=cxtPath%>/modules/myPicks/index.jsp">Bowl Picks 2015</a></li>
					<li><a href="#">Fantasy Golf 2016</a></li>
				</ul></li>
			<%if(userBo.getAdminFlag()){ %>
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
				aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li class="dropdown-header">Game Management</li>
					<li><a href="<%=cxtPath%>/modules/admin/manage2015BowlPicks.jsp">Manage 2015 Bowl Picks</a></li>
					<li role="separator" class="divider"></li>
					<li class="dropdown-header">User Management</li>
					<li><a href="<%=cxtPath%>/modules/admin/manageUsers.jsp">Manage Users</a></li>
					<li role="separator" class="divider"></li>
					<li class="dropdown-header">App Management</li>
					<li><a href="#">Latest News</a></li>
				</ul></li>
			<%} %>
		</ul>
		<ul class="nav navbar-nav">
			<li>
				<div class="alert alert-danger pageAlert hidden" role="alert"></div>
			</li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="<%=cxtPath%>/userProfileEdit.jsp"><label class="usernameLabel" style="cursor: pointer; color: blue;"></label></a></li>
			<li><a href="#" class="logoutBtn">Log Out</a></li>
		</ul>
	</div>
	<!--/.nav-collapse -->
</div>
<!-- /.container --> </nav>

<script>
$(document).ready(function() {
	$.navBar.init("<%=cxtPath%>", <%=userBoString%>);
});
</script>