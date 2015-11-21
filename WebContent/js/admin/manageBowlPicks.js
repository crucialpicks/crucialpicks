$.manageBowlPicks = {};
$.manageBowlPicks.cxtPath = null;
$.manageBowlPicks.deletedMatchupIds = [];

/**
 * Setup the bowl picks page.
 */
$.manageBowlPicks.init = function(cxtPath, existingMatchups) {
	$.manageBowlPicks.cxtPath = cxtPath;
	$(".addRowBtn").unbind().click(function() {
		$.manageBowlPicks.addNewRow();
	});

	$(".saveBtn").unbind().click(function() {
		$.manageBowlPicks.gatherMatchupRows();
	});

	if (existingMatchups.length > 0) {
		$.manageBowlPicks.populateExisting(existingMatchups);
	}
};

/**
 * Setup the existing matchups.
 */
$.manageBowlPicks.populateExisting = function(existingMatchups) {
	for (var i = 0; i < existingMatchups.length; i++) {
		var matchup = existingMatchups[i];
		var $tr = $(".newRowTemplateTable").find("tr").clone();
		$tr.attr("data-matchup-id", matchup.matchupId);
		$tr.find(".deleteRowBtn").unbind().click(function() {
			var matchupId = $(this).closest("tr").attr("data-matchup-id");
			$.manageBowlPicks.deletedMatchupIds.push(matchupId);
			$(this).closest("tr").remove();
		});
		$tr.find(".dateInput").datepicker();
		$tr.find(".dateInput").datepicker("setDate", matchup.date);
		$tr.find(".titleInput").val(matchup.title);
		$tr.find(".teamSelectionCell").find(".teamASelect").val(matchup.teamA.cfbTeamId);
		$tr.find(".teamSelectionCell").find(".teamBSelect").val(matchup.teamB.cfbTeamId);
		$tr.find(".teamASpreadInput").val(matchup.teamASpread);
		$tr.find(".teamBSpreadInput").val(matchup.teamBSpread);
		$tr.find(".teamAScoreInput").val(matchup.teamAScore);
		$tr.find(".teamBScoreInput").val(matchup.teamBScore);
		
		if (matchup.winningTeam && matchup.winningTeam.cfbTeamId == matchup.teamA.cfbTeamId) {
			$tr.find(".teamAWinnerSelect").prop("checked", "checked");
		} else if (matchup.winningTeam && matchup.winningTeam.cfbTeamId == matchup.teamB.cfbTeamId) {
			$tr.find(".teamBWinnerSelect").prop("checked", "checked");
		}

		if(matchup.lockFlag){
			$tr.find(".lockSelect").prop("checked", "checked");
		}
		
		$(".bowlMatchupTable").find("tbody").append($tr);

	}
};

/**
 * Add a new matchup row.
 */
$.manageBowlPicks.addNewRow = function() {
	var $tr = $(".newRowTemplateTable").find("tr").clone();
	$tr.find(".deleteRowBtn").unbind().click(function() {
		$(this).closest("tr").remove();
	});
	$tr.find(".dateInput").datepicker();
	$(".bowlMatchupTable").find("tbody").append($tr);
};

/**
 * Gather matchup rows for saving.
 */
$.manageBowlPicks.gatherMatchupRows = function() {
	var matchupList = [];
	var validSave = true;
	$(".bowlMatchupTable").find(".matchupRow").each(function() {
		var $row = $(this);
		var matchupId = null;
		if($row.attr("data-matchup-id")){
			matchupId = $row.attr("data-matchup-id");
		}
		var date = $row.find(".dateInput").val();
		var title = $row.find(".titleInput").val();
		var teamABo = {};
		teamABo.cfbTeamId = $row.find(".teamASelect").val();
		var teamBBo = {};
		teamBBo.cfbTeamId = $row.find(".teamBSelect").val();

		if (date == "") {
			$.coreUtils.createAlert("Every matchup requires a date.");
			validSave = false;
			return false;
		}
		if (title == "") {
			$.coreUtils.createAlert("Every matchup requires a title.");
			validSave = false;
			return false;
		}
		if (teamABo == null || teamBBo == null) {
			$.coreUtils.createAlert("Every matchup requires team selection.");
			validSave = false;
			return false;
		}

		var matchup = {};
		matchup.matchupId = matchupId;
		matchup.date = date;
		matchup.title = title;
		matchup.teamA = teamABo;
		matchup.teamB = teamBBo;
		matchup.teamASpread = $row.find(".teamASpreadInput").val();
		matchup.teamBSpread = $row.find(".teamBSpreadInput").val();
		matchup.teamAScore = $row.find(".teamAScoreInput").val();
		matchup.teamBScore = $row.find(".teamBScoreInput").val();
		var winningTeamBo = {};
		if ($row.find(".teamAWinnerSelect").is(":checked")) {
			matchup.winningTeam = teamABo;
		} else if ($row.find(".teamBWinnerSelect").is(":checked")) {
			matchup.winningTeam = teamBBo;
		}

		matchup.lockFlag = $row.find(".lockSelect").is(":checked");
		matchupList.push(matchup);
	});

	if (validSave) {
		$.manageBowlPicks.save(matchupList);
	}

};

/**
 * Save or update the bowl pick matchups.
 */
$.manageBowlPicks.save = function(matchupList) {
	$.ajax({
		type : "POST",
		url : $.manageBowlPicks.cxtPath + "/servlets/SaveBowlMatchupsServlet",
		data : {
			matchupList : JSON.stringify(matchupList),
			deletedMatchupIds : JSON.stringify($.manageBowlPicks.deletedMatchupIds)
		},
		dataType : "json",
		success : function(jsonResult) {
			var msg = "";
			if (jsonResult.success == true) {
				msg = "Matchups saved successfully.";
				$.manageBowlPicks.deletedMatchupIds = [];
			} else {
				msg = "Matchups NOT saved successfully. Try again or contact support.";
			}
			$("#bowlManageSaveModal").find(".modal-body").html(msg);
			$("#bowlManageSaveModal").modal("show");
		}
	});
};