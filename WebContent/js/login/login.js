$.login = {};
$.login.cxtPath = null;

/**
 * Called on document ready.
 */
$.login.init = function(cxtPath) {
	$.login.cxtPath = cxtPath;
	$(".loginSubmitBtn").unbind().click(function() {
		$.login.performClientSideValidation();
	});

	$(".passwordInput").keyup(function(event) {
		if (event.keyCode == 13) {
			$.login.performClientSideValidation();
		}
	});

	$(".unPwRecoverLink").unbind().click(function() {
		alert("Boom");
	});

	$(".signUpSubmitBtn").unbind().click(function() {
		window.location.replace(cxtPath+"/signUp.jsp");
	});
	
	$(".usernameInput").focus();
};

/**
 * 
 */
$.login.performClientSideValidation = function() {
	var username = $(".usernameInput").val();
	var password = $(".passwordInput").val();

	if (username.length == 0) {
		$.coreUtils.createAlert("Please enter a username.");
		return;
	}

	if (password.length == 0) {
		$.coreUtils.createAlert("Please enter a password.");
		return;
	}

	// TODO introduce jquery spinner

	$.ajax({
		type : "POST",
		url : $.login.cxtPath + "/servlets/AttemptLoginServlet",
		data : {
			username : username,
			password : password
		},
		dataType : "json",
		success : function(jsonResult) {
			if (jsonResult.errorMessage) {
				$.coreUtils.createAlert(jsonResult.errorMessage);
				return;
			}
			if (jsonResult.success == true) {
				window.location.replace($.login.cxtPath + "/index.jsp");
			}
		}
	});
};