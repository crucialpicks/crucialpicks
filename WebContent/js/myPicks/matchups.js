$.matchups = {};
$.matchups.cxtPath = null;

/**
 * Layout matchups.
 */
$.matchups.init = function(cxtPath, matchups) {

	$(document).on('shown.bs.tab', 'a[data-toggle="tab"]', function(e) {
		if ($(e.currentTarget).attr("href") == "#bowlMatchupTab") {
			$.matchups.renderCharts();
		}
	});

	for (var i = 0; i < matchups.length; i++) {
		var matchup = matchups[i];
		var $tr = $(".matchupTemplateTable").find("tr").clone();
		$tr.find(".dateCell").html(matchup.date);
		$tr.find(".bowlTitleCell").html(matchup.title);
		$tr.find(".teamAImage").attr("src", "data:image/png;base64," + matchup.teamA.logo);
		var spreadAText = "";
		if (matchup.teamASpread != "") {
			spreadAText = "(" + matchup.teamASpread + ")";
		}
		$tr.find(".teamALabel").html(matchup.teamA.name + " " + spreadAText);
		$tr.find(".teamBImage").attr("src", "data:image/png;base64," + matchup.teamB.logo);
		var spreadBText = "";
		if (matchup.teamASpread != "") {
			spreadBText = "(" + matchup.teamBSpread + ")";
		}
		$tr.find(".teamBLabel").html(matchup.teamB.name + " " + spreadBText);
		if (matchup.teamAScore) {
			$tr.find(".teamAScore").html(matchup.teamAScore);
		}
		if (matchup.teamBScore) {
			$tr.find(".teamBScore").html(matchup.teamBScore);
		}

		$tr.find(".pickPercentageCell").attr("data-teamA-percentage",25);// matchup.teamAPickPercentage);
		$tr.find(".pickPercentageCell").attr("data-teamA-colorAHex", matchup.teamA.colorAHex);
		$tr.find(".pickPercentageCell").attr("data-teamA-colorBHex", matchup.teamA.colorBHex);
		$tr.find(".pickPercentageCell").attr("data-teamA-name", matchup.teamA.name);
		$tr.find(".pickPercentageCell").attr("data-teamB-percentage",75);// matchup.teamBPickPercentage);
		$tr.find(".pickPercentageCell").attr("data-teamB-colorAHex", matchup.teamB.colorAHex);
		$tr.find(".pickPercentageCell").attr("data-teamB-colorBHex", matchup.teamB.colorBHex);
		$tr.find(".pickPercentageCell").attr("data-teamB-name", matchup.teamB.name);
		$tr.find(".bowlMatchupsTable").find("tbody").append($tr);
		
		$(".bowlMatchupsTable").find("tbody").append($tr);
	}

};

/**
 * 
 */
$.matchups.renderCharts = function() {
	Chart.defaults.global = {
		// Boolean - Whether to animate the chart
		animation : true,

		// Number - Number of animation steps
		animationSteps : 60,

		// String - Animation easing effect
		// Possible effects are:
		// [easeInOutQuart, linear, easeOutBounce, easeInBack, easeInOutQuad,
		// easeOutQuart, easeOutQuad, easeInOutBounce, easeOutSine,
		// easeInOutCubic,
		// easeInExpo, easeInOutBack, easeInCirc, easeInOutElastic, easeOutBack,
		// easeInQuad, easeInOutExpo, easeInQuart, easeOutQuint, easeInOutCirc,
		// easeInSine, easeOutExpo, easeOutCirc, easeOutCubic, easeInQuint,
		// easeInElastic, easeInOutSine, easeInOutQuint, easeInBounce,
		// easeOutElastic, easeInCubic]
		animationEasing : "easeOutQuart",

		// Boolean - If we should show the scale at all
		showScale : true,

		// Boolean - If we want to override with a hard coded scale
		scaleOverride : false,

		// ** Required if scaleOverride is true **
		// Number - The number of steps in a hard coded scale
		scaleSteps : null,
		// Number - The value jump in the hard coded scale
		scaleStepWidth : null,
		// Number - The scale starting value
		scaleStartValue : null,

		// String - Colour of the scale line
		scaleLineColor : "rgba(0,0,0,.1)",

		// Number - Pixel width of the scale line
		scaleLineWidth : 1,

		// Boolean - Whether to show labels on the scale
		scaleShowLabels : true,

		// Interpolated JS string - can access value
		scaleLabel : "<%=value%>",

		// Boolean - Whether the scale should stick to integers, not floats even
		// if drawing space is there
		scaleIntegersOnly : true,

		// Boolean - Whether the scale should start at zero, or an order of
		// magnitude down from the lowest value
		scaleBeginAtZero : false,

		// String - Scale label font declaration for the scale label
		scaleFontFamily : "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",

		// Number - Scale label font size in pixels
		scaleFontSize : 12,

		// String - Scale label font weight style
		scaleFontStyle : "normal",

		// String - Scale label font colour
		scaleFontColor : "#666",

		// Boolean - whether or not the chart should be responsive and resize
		// when the browser does.
		responsive : false,

		// Boolean - whether to maintain the starting aspect ratio or not when
		// responsive, if set to false, will take up entire container
		maintainAspectRatio : true,

		// Boolean - Determines whether to draw tooltips on the canvas or not
		showTooltips : true,

		// Function - Determines whether to execute the customTooltips function
		// instead of drawing the built in tooltips (See [Advanced - External
		// Tooltips](#advanced-usage-custom-tooltips))
		customTooltips : false,

		// Array - Array of string names to attach tooltip events
		tooltipEvents : [ "mousemove", "touchstart", "touchmove" ],

		// String - Tooltip background colour
		tooltipFillColor : "rgba(0,0,0,0.8)",

		// String - Tooltip label font declaration for the scale label
		tooltipFontFamily : "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",

		// Number - Tooltip label font size in pixels
		tooltipFontSize : 14,

		// String - Tooltip font weight style
		tooltipFontStyle : "normal",

		// String - Tooltip label font colour
		tooltipFontColor : "#fff",

		// String - Tooltip title font declaration for the scale label
		tooltipTitleFontFamily : "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif",

		// Number - Tooltip title font size in pixels
		tooltipTitleFontSize : 14,

		// String - Tooltip title font weight style
		tooltipTitleFontStyle : "bold",

		// String - Tooltip title font colour
		tooltipTitleFontColor : "#fff",

		// Number - pixel width of padding around tooltip text
		tooltipYPadding : 6,

		// Number - pixel width of padding around tooltip text
		tooltipXPadding : 6,

		// Number - Size of the caret on the tooltip
		tooltipCaretSize : 8,

		// Number - Pixel radius of the tooltip border
		tooltipCornerRadius : 6,

		// Number - Pixel offset from point x to tooltip edge
		tooltipXOffset : 10,

		// String - Template string for single tooltips
		tooltipTemplate : "<%if (label){%><%=label%>: <%}%><%= value %>",

		// String - Template string for multiple tooltips
		multiTooltipTemplate : "<%= value %>",

		// Function - Will fire on animation progression.
		onAnimationProgress : function() {
		},

		// Function - Will fire on animation completion.
		onAnimationComplete : function() {
		}
	}

	$(".bowlMatchupsTable").find(".pickPercentageCell").each(function() {
		var data = [ {
			value : $(this).attr("data-teama-percentage"),
			color : $(this).attr("data-teama-colorahex"),
			highlight : $(this).attr("data-teama-colorbhex"),
			label : $(this).attr("data-teama-name")
		}, {
			value : $(this).attr("data-teamB-percentage"),
			color : $(this).attr("data-teamB-colorahex"),
			highlight : $(this).attr("data-teamB-colorbhex"),
			label : $(this).attr("data-teamb-name")
		} ];
		var ctx = $(this).find(".matchupPercentageChart").get(0).getContext("2d");
		var myPieChart = new Chart(ctx).Pie(data);
	});

};