package com.rest.services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import simulator.Coord;
import simulator.RobotCrt;

@Path("/cmd")
public class RobotControlService {
	private final static String ROBOT_SIMULATOR_LABEL = "robot_simulator";
	private RobotCrt Robot;

	// Inject servlet context (needed to get general context, application memory
	// space, session memory space ...)
	@Context
	ServletContext context;

	// After RestService construction launches init method
	@PostConstruct
	public void init() {
		// this.Robot= new RobotCrt();
		checkRobot();
	}

	private void checkRobot() {
		Object obj = context.getAttribute(ROBOT_SIMULATOR_LABEL);
		if (obj == null) {
			this.Robot = new RobotCrt();
			context.setAttribute(ROBOT_SIMULATOR_LABEL, this.Robot);
		} else {
			this.Robot = (RobotCrt) obj;
		}

	}
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("AUTO")
	public void Auto() {
		this.Robot.decouverte();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("RELOAD")
	public void reload() {
		this.Robot = new RobotCrt();
		context.setAttribute(ROBOT_SIMULATOR_LABEL, this.Robot);
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("UP")
	public String goUp() {
		return this.Robot.DeplacementUP();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("DOWN")
	public String goDown() {
		return this.Robot.DeplacementDOWN();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("RIGHT")
	public String goRight() {
		return this.Robot.DeplacementRIGHT();
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("LEFT")
	public String goLeft() {
		return this.Robot.DeplacementLEFT();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("env")
	public String getEnv() {

		// create Json container Object
		JSONObject JSON = new JSONObject();

		/*-------Distance--------*/
		JSON.put("distance", this.Robot.get_measures().getDistance());

		/*-------COMMANDE--------*/
		JSON.put("commande", this.Robot.getNbr_cmd());

		/*-------ENVIRONNEMENT--------*/
		JSONObject environnement = new JSONObject();
		environnement.put("taille", this.Robot.getsizeEnvX() * this.Robot.getsizeEnvY());
		environnement.put("X", this.Robot.getsizeEnvX());
		environnement.put("Y", Robot.getsizeEnvY());
		environnement.put("unknown", Robot.get_measures().getNb_unknown());
		environnement.put("visite", Robot.get_measures().getNb_visite());
		environnement.put("orientation", Robot.getOrientation());
		JSON.put("environnement", environnement);

		/*---------HISTORIQUE-------*/
		JSONArray listH = new JSONArray();
		JSONObject historique;
		for (Coord C : this.Robot.getHistorique()) {
			// add json objects to jsonlist
			historique = new JSONObject();
			historique.put("x", C.getX());
			historique.put("y", C.getY());
			listH.add(historique);
		}
		// add jsonlist to json container
		JSON.put("historique", listH);

		/*---------OBSTACLE-------*/
		JSONObject obstacle = new JSONObject();
		obstacle.put("visible", this.Robot.get_measures().getNbr_obs_vis());
		obstacle.put("total", this.Robot.getEnvironnement().getNb_obs());
				//.getNbr_obs());
		JSON.put("obstacle", obstacle);

		/*--------CARTE----------*/
		// create a json list
		JSONArray list = new JSONArray();
		// create set of json objects
		JSONObject position;
		for (int i = 0; i < this.Robot.getsizeEnvX(); i++) {
			for (int j = 0; j < this.Robot.getsizeEnvY(); j++) {
				// add json objects to jsonlist
				position = new JSONObject();
				position.put("x", new Integer(i));
				position.put("y", new Integer(j));
				position.put("val", this.Robot.getContenu(i, j));
				list.add(position);
			}
		}

		// add jsonlist to json container
		JSON.put("carte", list);

		// return json string of the json container
		return JSON.toJSONString();

	}

}
