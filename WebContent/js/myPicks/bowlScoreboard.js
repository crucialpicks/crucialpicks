$.bowlScoreboard = {};
$.bowlScoreboard.cxtPath = null;

/**
 * Initialize the bowl pick scoreboard JS.
 */
$.bowlScoreboard.init = function(cxtPath, pickScores) {
	$.bowlScoreboard.cxtPath = cxtPath;
	$.bowlScoreboard.layoutScoreboard(pickScores);
};

/**
 * Layout the bowl pick scoreboard.
 */
$.bowlScoreboard.layoutScoreboard = function(pickScores) {
	for (var i = 0; i < pickScores.length; i++) {
		var score = pickScores[i];
		var $tr = $(".scoreboardTemplateTable").find("tr").clone();
		$tr.find(".usernameCell").html(score.username);
		$tr.find(".scoreCell").attr("data-user-id", score.userId);
		$tr.find(".scoreCell").attr("data-username", score.username);
		$tr.find(".scoreCell").html('<a style="cursor:pointer;">' + score.userScore + '</a>');
		$(".bowlScoreboardTable").find("tbody").append($tr);
	}

	$(".bowlScoreboardTable").DataTable();
	$(".scoreCell").click(function() {
		var userId = $(this).attr("data-user-id");
	});

	$(".scoreCell", ".bowlScoreboardTable").click(function() {
		var userId = $(this).attr("data-user-id");
		var username = $(this).attr("data-username");
		$.bowlScoreboard.showUsersPicksModal(userId, username);
	});
};

/**
 * 
 * @param userId
 * @param username
 */
$.bowlScoreboard.showUsersPicksModal = function(userId, username) {
	$(".modal-header", "#userPicksModal").find("h3").html("Picks for User: " + username);
	$(".userPicksTable").find("tbody").empty();
	$.ajax({
		type : "POST",
		url : $.editBowlPicks.cxtPath + "/servlets/GetUsersBowlPicks",
		data : {
			userId : userId
		},
		dataType : "json",
		success : function(jsonResult) {
			$.editBowlPicks.layoutMatchups($(".userPicksTable"), jsonResult.matchups);
			$.editBowlPicks.layoutPicks($(".userPicksTable"), jsonResult.lockedPicks);
			$("#userPicksModal").modal("show");
		}
	});
};