var urlroot = "/Projet_Majeur/";
// command by keybord
var distance = [];
var temps = 0;
var label = [ temps + "s" ];
var obstacles;
var env_connu = [];
var env_parcouru = [];
var tot = [];

// $.getScript("canvas.js");

$(document).ready(

function() {
	// reload le serveur quand un nouvel utilisateur
	$.post(urlroot + "rest/cmd/RELOAD", function(data, status) {
		MAJ();
	});

	// met à jour la carte quand on click sur les settings
	$('#Robot').click(function() {
		console.log("robot change !");
		MAJ();
	});

	// $('#Robot').click();

	// clicks sur les bouton
	$("#btn-up").click(function() {
		$.post(urlroot + "rest/cmd/UP", function(data, status) {
			MAJ();
		});
	});
	$("#btn-down").click(function() {
		$.post(urlroot + "rest/cmd/DOWN", function(data, status) {
			MAJ();
			// MAJ();
		});
	});
	$("#btn-right").click(function() {
		$.post(urlroot + "rest/cmd/RIGHT", function(data, status) {
			MAJ();
		});
	});
	$("#btn-left").click(function() {
		$.post(urlroot + "rest/cmd/LEFT", function(data, status) {
			MAJ();
		});
	});
	$("#btn-refresh").click(function() {
		$.post(urlroot + "rest/cmd/RELOAD", function(data, status) {
			MAJ();
			distance = [];
			env_connu = [];
			env_parcouru = [];
			temps = 0;
			label = [ temps + "s" ];
		});
	});

});

setInterval(function() {
	if (document.getElementById("AutoMapping").checked == true) {
		$.post(urlroot + "rest/cmd/AUTO", function() {
			MAJ();
		});
	}
}, 500);

setInterval(
		function() {
			$
					.get(
							urlroot + "rest/cmd/env",
							function(data, status) {

								// console.log(data);

								// console.log(data);
								// $("#stats").html("Stats:"+ getTime(temps));
								// distance
								distance.push(data.distance);
								temps = temps + 5;
								label.push(temps + "s");
								new Chartist.Line('#stat_distance', {
									labels : label,
									series : [ distance ]
								}, {
									showArea : true,
									showLine : true,
									showPoint : false,
									fullWidth : true,
									height : '150px',
									axisX : {
										showLabel : false,
										showGrid : false
									}
								});

								// obstables
								obstacles = [
										data.obstacle.visible,
										data.obstacle.total
												- data.obstacle.visible ];
								new Chartist.Pie('#stat_obstacle', {

									series : obstacles,
									labels : [ '', '' ]
								}, {
									donut : true,
									donutWidth : 20,
									startAngle : 210,
									total : data.obstacle.total,
									showLabel : false,
									plugins : [ Chartist.plugins.fillDonut({
										items : [ {
											content : '<h3>'
													+ data.obstacle.visible
													+ '/' + data.obstacle.total
													+ '</h3>'
										} ]
									}) ],
								});

								// pourcentage de carte découverte
								var total = data.environnement.taille, inconnu = data.environnement.unknown, visite = data.environnement.visite;

								env_connu.push(total - inconnu);
								env_parcouru.push(visite);
								// tot.push(total);
								// console.log(visite);

								var tooltipText = '<div style="color:#F1664D"><h4> '
										+ Math.round((total - inconnu) / total
												* 10000)
										/ 100
										+ '% </h4> découvert<div>'
										+ '<div style="color:#DD1E12"><h4> '
										+ Math.round((visite) / total * 10000)
										/ 100 + '% </h4>visité </div>';
								$("#pourcentages_env").tooltip().attr(
										'data-original-title', tooltipText);

								new Chartist.Line('#stat_env', {
									labels : label,
									series : [ env_parcouru, env_connu ]
								}, {
									high : total - 70,
									low : 0,
									showArea : true,
									showLine : true,
									showPoint : false,
									fullWidth : true,
									axisX : {
										showLabel : false,
										showGrid : false
									}
								});
							});
		}, 3000);

$(document).keydown(

function(e) {
	switch (e.which) {
	case 37: // left
		$.post(urlroot + "rest/cmd/LEFT", function() {
			MAJ();
		});
		break;

	case 38: // up

		$.post(urlroot + "rest/cmd/UP", function() {
			MAJ();
		});
		break;

	case 39: // right
		$.post(urlroot + "rest/cmd/RIGHT", function() {
			MAJ();
		});
		break;

	case 40: // down
		$.post(urlroot + "rest/cmd/DOWN", function() {
			MAJ();
		});
		break;

	case 32: // space to refresh data
		$.post(urlroot + "rest/cmd/AUTO", function() {
			MAJ();
		});
		break;

	default:
		return; // exit this handler for other keys
	}
	e.preventDefault(); // prevent the default action (scroll / move caret)
});
