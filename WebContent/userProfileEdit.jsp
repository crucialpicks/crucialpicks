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
<title>Crucial Picks - Edit User Profile</title>
</head>
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
					<input type="text" class="form-control input input-sm">
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-3">
					<label class="pull-right">New Password</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="form-control input input-sm">
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-3">
					<label class="pull-right">New Password Again</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="form-control input input-sm">
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