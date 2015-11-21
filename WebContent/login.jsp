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
<title>Crucial Picks - Login</title>

<link href="<%=cxtPath%>/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=cxtPath%>/css/crucialPicks.css" rel="stylesheet">

<script src="<%=cxtPath%>/lib/jquery/jquery-1.11.3.min.js"></script>
<script src="<%=cxtPath%>/lib/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=cxtPath%>/js/core/coreUtils.js"></script>
<script src="<%=cxtPath%>/js/login/login.js"></script>

</head>

<body>
	<div class="container loginContainer" style="margin-top: 50px;">
		<div class="alert alert-danger pageAlert hidden" role="alert"></div>
		<div class="well">
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<h3>Please Log In</h3>
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-3">
					<input type="text" class="form-control input input-sm usernameInput" placeholder="Username" />
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-4"></div>
				<div class="col-md-3">
					<input type="password" class="form-control input input-sm passwordInput" placeholder="Password" />
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-6"></div>
				<div class="col-md-1">
					<button class="btn btn-sm btn-default loginSubmitBtn">Log In</button>
				</div>
				<div class="col-md-5"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-4"></div>
				<div class="col-md-3">
					<label class="smallFont">Forget your <a style="cursor: pointer;" class="unPwRecoverLink">username</a> or <a
						style="cursor: pointer;" class="unPwRecoverLink">password?</a></label>
				</div>
				<div class="col-md-4"></div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-5"></div>
				<div class="col-md-1">
					<button class="btn btn-sm btn-success signUpSubmitBtn">Sign Up</button>
				</div>
				<div class="col-md-6"></div>
			</div>
		</div>
		<!-- /well -->
	</div>
	<!-- /container -->

	<script>
		$(document).ready(function() {
			$.login.init("<%=cxtPath%>");
		});
	</script>
</body>
</html>