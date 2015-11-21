$.editBowlPicks = {};
$.editBowlPicks.cxtPath = null;

/**
 * Initialize the bowl pick edit JS.
 */
$.editBowlPicks.init = function(cxtPath, matchups, picks) {
	$.editBowlPicks.cxtPath = cxtPath;
	$.editBowlPicks.layoutMatchups($(".bowlPicksTable"), matchups);
	$.editBowlPicks.layoutPicks($(".bowlPicksTable"), picks);
	$(".saveBtn").unbind().click(function() {
		$.editBowlPicks.gatherPicks();
	});
};

/**
 * Layout matchups and any previous picks.
 */
$.editBowlPicks.layoutMatchups = function($table, matchups) {
	for (var i = 0; i < matchups.length; i++) {
		var matchup = matchups[i];
		var $tr = $(".newRowTemplateTable").find("tr").clone();
		$tr.attr("data-matchup-id", matchup.matchupId);
		$tr.find("input").attr("name", "matchup-" + matchup.matchupId);
		$tr.find(".dateCell").html(matchup.date);
		$tr.find(".bowlTitleCell").html(matchup.title);

		$tr.find(".teamARadio").attr("value", matchup.teamA.cfbTeamId);
		$tr.find(".teamAImage").attr("src", "data:image/png;base64," + matchup.teamA.logo);

		var spreadAText = "";
		if (matchup.teamASpread != "") {
			spreadAText = "(" + matchup.teamASpread + ")";
		}
		$tr.find(".teamALabel").html(matchup.teamA.name + " " + spreadAText);

		$tr.find(".teamBRadio").attr("value", matchup.teamB.cfbTeamId);
		$tr.find(".teamBImage").attr("src", "data:image/png;base64," + matchup.teamB.logo);

		var spreadBText = "";
		if (matchup.teamASpread != "") {
			spreadBText = "(" + matchup.teamBSpread + ")";
		}
		$tr.find(".teamBLabel").html(matchup.teamB.name + " " + spreadBText);

		if(matchup.teamAScore){
			$tr.find(".teamAScore").html(matchup.teamAScore);
		}
		if(matchup.teamBScore){
			$tr.find(".teamBScore").html(matchup.teamBScore);
		}
		
		if (matchup.lockFlag) {
			$tr.find("input").attr("disabled", "disabled");
			$tr.find(".statusCell").html("<span class='glyphicon glyphicon-lock'>")
		}

		$table.find("tbody").append($tr);
	}
};

/**
 * Layout the user's existing picks.
 */
$.editBowlPicks.layoutPicks = function($table, picks) {
	for (var i = 0; i < picks.length; i++) {
		var pick = picks[i];
		var radioName = "matchup-" + pick.bowlMatchupId;
		var $radios = $table.find("input:radio[name='" + radioName + "']");
		$radios.closest("tr").attr("data-bowl-pick-id", pick.bowlPickId);
		$radios.filter("[value=" + pick.selectedTeamId + "]").prop('checked', true);

		if (pick.bowlPickStatus == "OPEN") {

		} else if (pick.bowlPickStatus == "LOCKED") {
			$radios.closest("tr").find(".statusCell").html("<span class='glyphicon glyphicon-lock'>");
		} else if (pick.bowlPickStatus == "RIGHT") {
			$radios.closest("tr").find(".statusCell").html("<span class='glyphicon glyphicon-ok' style='color:green;'>");
		} else if (pick.bowlPickStatus == "WRONG") {
			$radios.closest("tr").find(".statusCell").html("<span class='glyphicon glyphicon-remove' style='color:red;'>");
		}
	}
};

/**
 * Gather picks for saving
 */
$.editBowlPicks.gatherPicks = function() {
	var bowlPicks = [];
	var unPickedMatchupCount = 0;
	$(".bowlPicksTable").find(".bowlPickRow").each(function() {
		var radioName = "matchup-" + $(this).attr("data-matchup-id");
		var selectedTeamId = $("input:radio[name='" + radioName + "']:checked").val();
		if (selectedTeamId == null) {
			unPickedMatchupCount++;
			return true;// continue
		}

		var pick = {};
		pick.bowlPickId = $(this).attr("data-bowl-pick-id");
		pick.bowlMatchupId = $(this).attr("data-matchup-id");
		pick.selectedTeamId = selectedTeamId;
		bowlPicks.push(pick);
	});
	$.editBowlPicks.save(unPickedMatchupCount, bowlPicks);
};

/**
 * Save or update the user's picks.
 */
$.editBowlPicks.save = function(unPickedMatchupCount, bowlPicks) {
	$.ajax({
		type : "POST",
		url : $.editBowlPicks.cxtPath + "/servlets/SaveBowlPicksServlet",
		data : {
			bowlPicks : JSON.stringify(bowlPicks)
		},
		dataType : "json",
		success : function(jsonResult) {
			var msg = "";
			if (jsonResult.success == true) {
				msg = "Bowl Picks saved successfully.";
				if (unPickedMatchupCount > 0) {
					msg += "<BR>Note, you have " + unPickedMatchupCount + " matchups that you have not picked yet.";
				}
			} else {
				msg = "Bowl Picks NOT saved successfully. Try again or contact support.";
			}
			$("#bowlPickSaveModal").find(".modal-body").html(msg);
			$("#bowlPickSaveModal").modal("show");
		}
	});
};