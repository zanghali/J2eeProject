var urlroot = "/Projet_Majeur/"

$(document).ready(function(){
	
	// Autorise les interractions avec le robot
	$("#btn-start").click(function(){

	  	$.ajax({
	  	    type : "POST",
	  	    url : urlroot + "rest/cmd/startCommande",
	  	    contentType: "text/plain",
	  	    success : function(data,status) {

	  	    	$("#commande").html("<img src='../img/started.png'/>");

	  	    },
	  	    error : function(error) {}
	  	    
	  	    
	  	});

	});
	  
	// Interdit les interractions avec le robot
	$("#btn-stop").click(function(){

	  	$.ajax({
	  	    type : "POST",
	  	    url : urlroot + "rest/cmd/stopCommande",
	  	    contentType: "text/plain",
	  	    success : function(data,status) {

	  	    	$("#commande").html("<img src='../img/stopped.png'/>");
	  	    		
	  	    },
	  	    error : function(error) {}
 
	  	});

	});
	  
	// Rafraichissement de l'état du robot
	setInterval(function(){ 
	  
		$.ajax({
			type : "POST",
	  	    url : urlroot + "rest/cmd/stateAdmin",
	  	    contentType: "text/plain",
	  	    success : function(data,status) {

	  	    	// Robot ok
	  	    	if(data=="ON")
	  	    	  	$("#commande").html("<img src='../img/started.png'/>");

	  	    	  	
	  	    	// Robot nok
	  	    	else
	  	    		$("#commande").html("<img src='../img/stopped.png'/>");
	  	    		
	  	    },
	  	    error : function(error) {}
		});  
  
	}, 1000);

});