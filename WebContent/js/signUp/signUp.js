$.signUp = {};
$.signUp.cxtPath = null;
/**
 * Called on document ready.
 */
$.signUp.init = function(cxtPath) {
	$.signUp.cxtPath = cxtPath;// TODO move into common.js, set on page load
	$(".attemptSignUpBtn").unbind().click(function() {
		$.signUp.performClientSideValidation();
	});

	// TODO bind on keyUP for fields an append check when it's good, to the
	// right
};

/**
 * Perform basic validation on client before sending to the server.
 */
$.signUp.performClientSideValidation = function() {
	var email = $(".emailInput").val();
	var firstName = $(".firstNameInput").val();
	var lastName = $(".lastNameInput").val();
	var username = $(".usernameInput").val();
	var pw1 = $(".pw1Input").val();
	var pw2 = $(".pw2Input").val();

	if (email == null || !$.coreUtils.isValidEmail(email)) {
		$.coreUtils.createAlert("Please enter a valid email address.");
		return;
	}

	if (firstName.length == 0) {
		$.coreUtils.createAlert("First Name required.");
		return;
	}

	if (lastName.length == 0) {
		$.coreUtils.createAlert("Last Name required.");
		return;
	}

	if (username == null || username.length < 10) {
		$.coreUtils.createAlert("Username must be at least 10 characters.");
		return;
	}

	if (pw1 == null || pw1.length < 6) {
		$.coreUtils.createAlert("Password must be at least 6 characters.");
		return;
	}

	if (pw1 != pw2) {
		$.coreUtils.createAlert("Passwords do not match.");
		return;
	}

	$.signUp.attemptAccoutnCreation(email, firstName, lastName, username, pw1);
};

/**
 * Attempt to sign up the new years. Modal will confirm or disconfirm.
 */
$.signUp.attemptAccoutnCreation = function(email, firstName, lastName, username, password) {
	$.ajax({
		type : "POST",
		url : $.signUp.cxtPath + "/servlets/AttemptSignUpServlet",
		data : {
			email : email,
			firstName : firstName,
			lastName : lastName,
			username : username,
			password : password
		},
		dataType : "json",
		success : function(jsonResult) {
			if (jsonResult.success) {
				$("#signUpResultModal").find(".modal-body").html("Sign Up Successful!<BR>Redirecting to Login Page.");

				$("#signUpResultModal").find(".okBtn").unbind().click(function() {
					window.location.replace("login.jsp");
				});
				$("#signUpResultModal").modal("show");

				return;
			} else {
				$("#signUpResultModal").find(".modal-body").html(
						"Could not Sign Up. Either you have not been invited, or you already have an account for this email address.");
				$("#signUpResultModal").modal("show");
			}
		}
	});
};

/**
 * Alert the given msg.
 */
$.signUp.createAlert = function(msg) {
	$(".signupAlert").html(msg);
	$(".signupAlert").removeClass("hidden");
	setTimeout(function() {
		$(".signupAlert").addClass("hidden");
	}, 3000);
};