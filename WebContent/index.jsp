<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String cxtPath = request.getContextPath();
%>
<html lang="en">
<jsp:include page="/modules/navBar/navBar.jsp" />
<head>
<link rel="shortcut icon" href="favicon.ico"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Crucial Picks - Home</title>

<script src="js/home/home.js"></script>

</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h4>Welcome!</h4>
			//TODO wells, with differetn bg colors. Golf = green<br>
			//TODO if user is involved w/ X game, X well.<br>
			Latest News: (TODO Provide UI to update this in Admin)
			Youtube Video - Justin Thomas highlight
			Twitter scroller thing
			Odds / etc
			<br>
	<br>
			Stats at a glance:
			In bowl picks you are 12/15 so far with 12 points. The leader has 15 poitns
			<br><br>
			Fantasy golf:
			You are in 3rd place behind BuzzHard, and Verne's Fuda.
			<br>
			<br>
			//TODO for every matchup in bowl picks, a comment section
		</div>
	</div>
	<!-- /container -->

	<script>
		$(document).ready(function() {
			$.home.init();
		});
	</script>
</body>
</html>