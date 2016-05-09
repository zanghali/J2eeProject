	var urlroot="/Projet_Majeur/";
$(document).ready(function(){
	//clicks sur les boutons

  $("#btn-up").click(function(){
	  	$.post(urlroot+"rest/cmd/UP",
    		  function(data,status){
    		    alert("Post Done received data: " + data + "\nStatus: " + status);
    	});    
  });
  $("#btn-down").click(function(){
	  	$.post(urlroot+"rest/cmd/DOWN",
  		  function(data,status){
  		    alert("Post Done received data: " + data + "\nStatus: " + status);
  	});    
});
  $("#btn-right").click(function(){
	  	$.post(urlroot+"rest/cmd/RIGHT",
  		  function(data,status){
  		    alert("Post Done received data: " + data + "\nStatus: " + status);
  	});    
});
  $("#btn-left").click(function(){
	  	$.post(urlroot+"rest/cmd/LEFT",
  		  function(data,status){
  		    alert("Post Done received data: " + data + "\nStatus: " + status);
  	});    
});
   
  $("#myButton2").click(function(){
	  	$.get("rest/cmd/env",
  		  function(data,status){
	  		
	  		for(i in data.data){
	  			$("#myContent").append("<h6>---------------------------</h6>");
	  			$("#myContent").append("<h5> X:"+data.data[i].x+",Y:"+data.data[i].y+"</h5>");
	  			$("#myContent").append("<h5> Value:"+data.data[i].val+"</h5>");
	  			$("#myContent").append("<h6>---------------------------</h6>");
	  			$("#mylastContent").text(data.data[i].val);
	  			
	  		}
	  		
  		    alert("Get Done received data: " + data + "\nStatus: " + status);
  	});    
});
});

//commande par keybord
$(document).keydown(function(e) {
    switch(e.which) {
        case 37: // left
        	$.post(urlroot+"rest/cmd/LEFT",
          		  function(data,status){
          		    alert("Post Done received data: " + data + "\nStatus: " + status);
          	});
        break;

        case 38: // up
        break;

        case 39: // right
        break;

        case 40: // down
        break;

        default: return; // exit this handler for other keys
    }
    e.preventDefault(); // prevent the default action (scroll / move caret)
});
