<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.crucialpicks.managers.UserManager"%>
<%@ page import="com.crucialpicks.business.UserBo"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<%
	String cxtPath = request.getContextPath();
	UserManager userManager = new UserManager();
	List<UserBo> users = userManager.getAllUserBizObjs();
	Gson gson = new Gson();
	String usersJsonString = gson.toJson(users);
%>

<html>
<jsp:include page="/modules/navBar/navBar.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Crucial Picks - Manage Users</title>

<script src="<%=cxtPath%>/js/admin/manageUsers.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h4>Manage Users - TODO make this use datatables</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<table class="table userEditTable">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Username</th>
							<th>Email</th>
							<th>Is Admin</th>
							<th>Active</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
				<button class="btn btn-sm btn-success addRowBtn">
					<span class="glyphicon glyphicon-plus"></span>
				</button>
			</div>
		</div><!-- /row -->
		<button class="btn btn-sm btn-default saveBtn">Save</button>
	</div>
	<!-- /container -->

	<script>
		$(document).ready(function() {
			$.manageUsers.init("<%=cxtPath%>",<%=usersJsonString%>);
		});
	</script>

	<!-- ******************************************************* -->
	<!-- 				Modals and Templates					 -->
	<!-- ******************************************************* -->
	<div class="hidden">
		<table class="newRowTemplateTable">
			<tr class="userEditRow">
				<td class="userIdCell"></td>
				<td class="nameCell"></td>
				<td class="usernameCell"></td>
				<td class="emailCell"><input
					class="form-control input input-sm emailInput" type="text" /></td>
				<td class="adminFlagCell"></td>
				<td class="activeCell"></td>
			</tr>
		</table>
	</div>
	<!-- /hidden -->
</body>
</html>