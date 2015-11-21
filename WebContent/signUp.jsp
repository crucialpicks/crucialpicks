<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String cxtPath = request.getContextPath();
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Crucial Picks - Sign Up</title>

<link href="<%=cxtPath%>/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=cxtPath%>/css/crucialPicks.css" rel="stylesheet">

<script src="<%=cxtPath%>/lib/jquery/jquery-1.11.3.min.js"></script>
<script src="<%=cxtPath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=cxtPath%>/js/core/coreUtils.js"></script>
<script src="<%=cxtPath%>/js/signUp/signUp.js"></script>

</head>

<body>
	<div class="container signUpContainer" style="margin-top: 50px;">
		<div class="alert alert-danger pageAlert hidden" role="alert"></div>
		<div class="well">
			<div class="row">
				<div class="col-md-4">
					<label class="smallFont pull-right">Email Address</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control input input-sm emailInput" placeholder="Email Address" />
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-4">
					<label class="smallFont pull-right">First Name</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control input input-sm firstNameInput" placeholder="First Name" />
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-4">
					<label class="smallFont pull-right">Last Name</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control input input-sm lastNameInput" placeholder="Last Name" />
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-4">
					<label class="smallFont pull-right">Desired Username</label>
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control input input-sm usernameInput" placeholder="Username" />
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-4">
					<label class="smallFont pull-right">Password</label>
				</div>
				<div class="col-md-4">
					<input type="password" class="form-control input input-sm pw1Input" placeholder="Password" />
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-4">
					<label class="smallFont pull-right">Password Again</label>
				</div>
				<div class="col-md-4">
					<input type="password" class="form-control input input-sm pw2Input" placeholder="Password" />
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<button class="btn btn-sm btn-default pull-right attemptSignUpBtn">Sign Up</button>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>
		<!-- /well -->
	</div>
	<!-- /container -->

	<script>
		$(document).ready(function() {
			$.signUp.init("<%=cxtPath%>");
		});
	</script>
</body>

<!-- ******************************************************* -->
<!-- 				Modals and Templates					 -->
<!-- ******************************************************* -->

<div id="signUpResultModal" class="modal fade" tabindex="-1">
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
</html>