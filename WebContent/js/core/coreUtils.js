$.coreUtils = {};

/**
 * Checks if the given email is valid.
 */
$.coreUtils.isValidEmail = function(emailInput) {
	var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	return re.test(emailInput);
};

/**
 * @param msg
 */
$.coreUtils.createAlert = function(msg) {
	$(".pageAlert").html(msg);
	$(".pageAlert").removeClass("hidden");
	setTimeout(function() {
		$(".pageAlert").addClass("hidden");
	}, 3000);
};