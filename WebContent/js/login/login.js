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
		$.login.presentUnPwReminderModal();
	});

	$(".signUpSubmitBtn").unbind().click(function() {
		window.location.replace(cxtPath + "/signUp.jsp");
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

/**
 * 
 */
$.login.presentUnPwReminderModal = function() {
	$("#unPwReminderModal").modal("show");
	$(".emailInput", "#unPwReminderModal").focus();
	$(".emailInput", "#unPwReminderModal").keyup(function(event) {
		if (event.keyCode == 13) {
			$.login.sendUnPwReminder();
		}
	});
	$(".okBtn", "#unPwReminderModal").unbind().click(function() {
		$.login.sendUnPwReminder();
	});
};

/**
 * 
 */
$.login.sendUnPwReminder = function(){
	var email = $(".emailInput", "#unPwReminderModal").val();
	if (!$.coreUtils.isValidEmail(email)) {
		alert("Please enter a valid email.");
		return;
	}
	$.ajax({
		type : "POST",
		url : $.login.cxtPath + "/servlets/SendUnPwReminderServlet",
		data : {
			email : email
		},
		dataType : "json",
		success : function(jsonResult) {
			alert("Email Sent.");
			$("#unPwReminderModal").modal("hide");
		}
	});
};