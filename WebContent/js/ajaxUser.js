var urlroot = "/Projet_Majeur/"

$(document).ready(function(){
	
	// Connexion dès le chargement de la page
	$.ajax({
	    type : "POST",
	    url : urlroot + "rest/cmd/connexion",
	    contentType: "text/plain",
	    data:  $.cookie('Ttoken'),
	    success : function(data,status) {
	    	
	    	if(data!=null)
	    		$.cookie('Ttoken', data);
	    },
	    error : function(error) {}
	});

	// Interractions avec le robot
	$("#commande").click(function(){

	  	$.ajax({
	  	    type : "POST",
	  	    url : urlroot + "rest/cmd/commande",
	  	    contentType: "text/plain",
	  	    data:  $.cookie('Ttoken'),
	  	    success : function(data,status) {

	  	    	// Robot disponible
	  	    	if(data=="Ready"){
	  	    	  	$("#commande").html("<img src='../img/ready.png'/>")
	  	    	}
	  	    	// Robot occupé
	  	    	else if(data=="Running")
	  	    		$("#commande").html("<img src='../img/running.png'/>")
	  	    		
	  	    },
	  	    error : function(error) {} 
	  	});

	});
	  
	// Rafraichissement de l'état du robot toutes les 1 sec
	setInterval(function(){ 
	  
		$.ajax({
			type : "POST",
	  	    url : urlroot + "rest/cmd/state",
	  	    contentType: "text/plain",
	  	    data:  $.cookie('Ttoken'),
	  	    success : function(data,status) {

	  	    	// Robot disponible
	  	    	if(data=="Ready"){
	  	    	  	$("#commande").html("<img src='../img/ready.png'/>");
	  	    	  	$("#contenu").hide();
	  	    	}
	  	    	  	
	  	    	// Robot occupé
	  	    	else if(data=="Running"){
	  	    		$("#commande").html("<img src='../img/running.png'/>");
	  	    		$("#contenu").show();
	  	    	}
	  	    		
	  	    	// Non disponible
	  	    	else{
	  	    		$("#commande").html("<img src='../img/notavailable.png'/>");
	  	    		$("#contenu").hide();
	  	    	}
	  	    	
	  	    },
	  	    error : function(error) {}
		});  
  
	}, 1000);
	  
	// Deconnexion ou suppression des cookies à la fermeture de la fenêtre
	$(window).on('beforeunload', function () {
    
		$.ajax({
			type : "POST",
		    url : urlroot + "rest/cmd/deconnexion",
		    contentType: "text/plain",
		    data:  $.cookie('Ttoken'),
		    success : function(data,status) {
		
		    	$.removeCookie('Ttoken');
		  	    	
		  	},
		  	error : function(error) {}	
		});

	});

});