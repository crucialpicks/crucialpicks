$.userProfileEdit = {};
$.userProfileEdit.cxtPath = null;

/**
 * Setup the user profile edit page
 */
$.userProfileEdit.init = function(cxtPath, userJson) {
	$.userProfileEdit.cxtPath = cxtPath;
	$(".usernameInput").val(userJson.username);
	$(".firstNameInput").val(userJson.firstName);
	$(".lastNameInput").val(userJson.lastName);
	$(".emailInput").val(userJson.email);
	$(".currentPw").attr("data-current-pw", userJson.password);
	$(".saveBtn").unbind().click(function() {
		$.userProfileEdit.validateAndSave();
	});
};

/**
 * 
 */
$.userProfileEdit.validateAndSave = function() {
	var firstName = $(".firstNameInput").val();
	var lastName = $(".lastNameInput").val();
	var currentPw = $(".currentPw").val();
	var pw1 = $(".pw1").val();
	var pw2 = $(".pw2").val();

	if (firstName.length == 0) {
		$.coreUtils.createAlert("First Name required.");
		return;
	}

	if (lastName.length == 0) {
		$.coreUtils.createAlert("Last Name required.");
		return;
	}

	var newPassword = null;

	if (currentPw != "" && currentPw != $(".currentPw").attr("data-current-pw")) {
		$.coreUtils.createAlert("Current password is incorrect.");
		return;
	}
	if (currentPw != "" && (pw1 == null || pw1.length < 6)) {
		$.coreUtils.createAlert("New Password must be at least 6 characters.");
		return;
	}

	if (currentPw != "" &&pw1 != pw2) {
		$.coreUtils.createAlert("New Passwords do not match.");
		return;
	} else {
		newPassword = pw1;
	}

	$.ajax({
		type : "POST",
		url : $.userProfileEdit.cxtPath + "/servlets/UserProfileUpdateServlet",
		data : {
			firstName : firstName,
			lastName : lastName,
			newPassword : newPassword
		},
		dataType : "json",
		success : function(result) {
			$("#profileEditModal").find(".modal-body").html(result.message);
			$("#profileEditModal").modal("show");
		}
	});
};