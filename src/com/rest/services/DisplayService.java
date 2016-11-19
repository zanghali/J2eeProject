package com.rest.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/cmd")
public class DisplayService {
	
	private final static String QUEUE_LIST_LABEL="queue";
	private final static String COMMANDABLE_LABEL="cmd";
	
	private final static String READY_LABEL = "Ready";
	private final static String RUNNING_LABEL = "Running";
	private final static String NOTAVAILABLE_LABEL = "NotAvailable";
	
	private Map<String,String> stateTokenMap;
	
	//Inject servlet context (needed to get general context, application memory space, session memory space ...)
	@Context
	ServletContext context;
	
	//After RestService construction launches init method
	@PostConstruct
	public void init(){
		checkQueue();
	}

	private void checkQueue() {
		Object obj=context.getAttribute(QUEUE_LIST_LABEL);
		
		if(obj==null){
			// map états de chaque token
			stateTokenMap = new HashMap<String,String>();
			context.setAttribute(QUEUE_LIST_LABEL, stateTokenMap);
		}else{
			stateTokenMap=(HashMap<String,String>) obj;
		}
		
	}
			
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("connexion")
	public String seConnecter (String token){
		
		//Initialisation de la validité de la connexion
		boolean isValid = false;
		
		// Vérifie si la connexion est valide avec les cookies
		isValid=isCookieTokenValid(token);
		
		// Nouvelle connexion (pas de cookies)
		if (!isValid) {

			isValid = true;
			
			// Save a token
			// generate a token
			String mytoken = UUID.randomUUID().toString();
			// save token on server
			stateTokenMap.put(mytoken,READY_LABEL);

			return mytoken;
		}
		
		return null;
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("commande")
	public String commanderRobot (String token){
		
		// L'utilisateur le controle déjà donc on le déconnecte
		if(stateToken(token)==RUNNING_LABEL){
			
			for (Map.Entry<String, String> entry : stateTokenMap.entrySet())
				stateTokenMap.put(entry.getKey(),READY_LABEL);

			return READY_LABEL;
		}
		
		// Le robot est disponible donc on peut le commander
		else if (stateToken(token)==READY_LABEL){
			
			for (Map.Entry<String, String> entry : stateTokenMap.entrySet())
			{
				// Bloque les autres utilisateurs
			    if(!entry.getKey().equals(token))
			    	stateTokenMap.put(entry.getKey(),NOTAVAILABLE_LABEL);
			    
			    // Autorise l'utilisateur courant
			    else
			    	stateTokenMap.put(entry.getKey(),RUNNING_LABEL);
			}
			
			return 	RUNNING_LABEL;		
		}
		
		// Quelqu'un d'autre utilise le robot
		else
			return NOTAVAILABLE_LABEL;		
	}
			
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("state")
	public String refreshState (String token){
		
		return stateToken (token);
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("deconnexion")
	public String seDeconnecter (String token){
				
		// Si l'utilisateur le controle on autorise les autres
		if(stateToken(token)==RUNNING_LABEL){
			
			for (Map.Entry<String, String> entry : stateTokenMap.entrySet())
				stateTokenMap.put(entry.getKey(),READY_LABEL);
		}

		stateTokenMap.remove(token);
		
		return null;
	}
	
	@POST
	@Path("startCommande")
	public void startCommande (){
		
		for (Map.Entry<String, String> entry : stateTokenMap.entrySet())
			stateTokenMap.put(entry.getKey(),READY_LABEL);

	}
	
	@POST
	@Path("stopCommande")
	public void stopCommande (){
		
		for (Map.Entry<String, String> entry : stateTokenMap.entrySet())
			stateTokenMap.put(entry.getKey(),NOTAVAILABLE_LABEL);

	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("stateAdmin")
	public String stateAdmin (){
				
		for (Map.Entry<String, String> entry : stateTokenMap.entrySet())
		    if(!entry.getValue().equals(NOTAVAILABLE_LABEL))
			    	  return "ON";
			    			  
		return "OFF";
	}
	
	private boolean isCookieTokenValid(String cookieToken) {
		
		boolean isValid = false;
		
		//if no cookies return false
		if(cookieToken == null)		return isValid;
		
		for (Map.Entry<String, String> entry : stateTokenMap.entrySet())
		    if(entry.getKey().equals(cookieToken))
			    	  isValid = true;

		return isValid;
	}
	
	private String stateToken (String token){
		
		for (Map.Entry<String, String> entry : stateTokenMap.entrySet())
		{
		    if(entry.getKey().equals(token))
                return entry.getValue();
		}

        return null;
	}

}
