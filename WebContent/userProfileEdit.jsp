<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.crucialpicks.managers.UserManager"%>
<%@ page import="com.crucialpicks.business.UserBo"%>
<%@ page import="com.google.gson.Gson"%>
<%
	String cxtPath = request.getContextPath();
	UserManager userManager = new UserManager();
	UserBo userBo = userManager.getCurrentUserBo(request);
	Gson gson = new Gson();
	String userJsonString = gson.toJson(userBo);
%>
<html>
<jsp:include page="/modules/navBar/navBar.jsp" />
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Crucial Picks - Edit User Profile</title>
</head>

<script src="<%=cxtPath%>/js/admin/userProfileEdit.js"></script>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h4>Edit User Profile</h4>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<div class="row">
				<div class="col-md-3">
					<label class="pull-right">Username</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="form-control input input-sm usernameInput" disabled>
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-3">
					<label class="pull-right">First Name</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="form-control input input-sm firstNameInput">
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-3">
					<label class="pull-right">Last Name</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="form-control input input-sm lastNameInput">
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-3">
					<label class="pull-right">Email</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="form-control input input-sm emailInput" disabled>
				</div>
			</div>
			<div class="row text-center" style="margin-top: 10px">
				<hr />
				<label>Change Password</label>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-3">
					<label class="pull-right">Current Password</label>
				</div>
				<div class="col-md-5">
					<input type="password" class="form-control input input-sm currentPw">
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-3">
					<label class="pull-right">New Password</label>
				</div>
				<div class="col-md-5">
					<input type="password" class="form-control input input-sm pw1">
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-3">
					<label class="pull-right">New Password Again</label>
				</div>
				<div class="col-md-5">
					<input type="password" class="form-control input input-sm pw2">
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<hr />
				<button class="btn btn-sm btn-success saveBtn">Save</button>
			</div>
		</div>
		<!-- /.col-md-4 -->
		<div class="col-md-2"></div>
	</div>
</div>
<!-- /container -->

<script>
	$(document).ready(function() {
		$.userProfileEdit.init("<%=cxtPath%>",<%=userJsonString%>);
	});
</script>

<!-- ******************************************************* -->
<!-- 				Modals and Templates					 -->
<!-- ******************************************************* -->

<div id="profileEditModal" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">X</button>
				<h3></h3>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-default okBtn" data-dismiss="modal">OK</button>
			</div>
		</div>
	</div>
</div>