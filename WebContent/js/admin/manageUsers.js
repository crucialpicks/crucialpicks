$.manageUsers = {};
$.manageUsers.cxtPath = null;

/**
 * Setup the manage Users page.
 */
$.manageUsers.init = function(cxtPath, users) {
	$.manageUsers.cxtPath = cxtPath;
	$(".addRowBtn").unbind().click(function() {
		$.manageUsers.addNewRow();
	});
	$(".saveBtn").unbind().click(function() {
		$.manageUsers.save();
	});
	for (var i = 0; i < users.length; i++) {
		var user = users[i];
		var $tr = $(".newRowTemplateTable").find("tr").clone();
		$tr.find(".emailInput").remove();
		$tr.find(".userIdCell").html(user.userId);
		$tr.find(".nameCell").html(user.name);
		$tr.find(".usernameCell").html(user.username);
		$tr.find(".emailCell").html(user.email);
		if(user.adminFlag){
			$tr.find(".adminFlagCell").html("<span class='glyphicon glyphicon-ok' style='color:green;'>");
		}
		$(".userEditTable").find("tbody").append($tr);
	}
};

/**
 * Add user Row
 */
$.manageUsers.addNewRow = function() {
	var $tr = $(".newRowTemplateTable").find("tr").clone();
	$(".userEditTable").find("tbody").append($tr);
};

/**
 * 
 */
$.manageUsers.save = function() {
	var emailList = [];
	$(".userEditTable").find(".emailInput").each(function() {
		emailList.push($(this).val());
	});
	if (emailList.length == 0) {
		return;
	}
	$.ajax({
		type : "POST",
		url : $.manageUsers.cxtPath + "/servlets/CreateNewUsersServlet",
		data : {
			emailList : JSON.stringify(emailList),
		},
		dataType : "json",
		success : function(jsonResult) {
			if (jsonResult.success == true) {
				window.location.replace($.manageUsers.cxtPath + "/modules/admin/manageUsers.jsp");
			}
		}
	});
};