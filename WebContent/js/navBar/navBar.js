/**
 * 
 */
$.navBar = {};
$.navBar.cxtPath = null;

/**
 * Setup the navbar
 */
$.navBar.init = function(cxtPath, userBo) {
	$.navBar.cxtPath = cxtPath;
	$(".navbar").find(".usernameLabel").html(userBo.username);

	$(".navbar").find(".logoutBtn").unbind().click(function() {
		$.navBar.logOut();
	});

	$(".navbar").removeClass("hidden");
};

/**
 * Log out.
 */
$.navBar.logOut = function() {
	$.ajax({
		type : "POST",
		url : $.navBar.cxtPath + "/servlets/LogoutServlet",
		data : {},
		dataType : "json",
		success : function(jsonResult) {
			window.location.replace("index.jsp");
		}
	});
};