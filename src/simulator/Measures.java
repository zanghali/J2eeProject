package simulator;

public class Measures {
	private int nbr_cmd;//nombre de commandes éxecutées sur le robot depuis le début
	private int nbr_obs;//nombe d'obstacles rencontrés par le robot depuis le début
	private int nbr_obs_vis;//nombre d'obstacles visibles par le robot
	private int distance;//distance parcourue par le robot depuis le début du simulateur
	private int nb_unknown;
	private int nb_visite;

public Measures(){
	this.nbr_cmd=0;
	this.nbr_obs_vis=0;
	this.distance=0;
	this.nb_unknown=0;
	this.nb_visite=0;	
}

/*
 *   getters/setters
 */
public int getNb_unknown() {
	return nb_unknown;
}

public void setNb_unknown(int nb_known) {
	this.nb_unknown = nb_known;
}

public void setNb_visite(int nb_visite) {
	this.nb_visite = nb_visite;
}

public int getNb_visite() {
	return nb_visite;
}


public int getNbr_cmd(){
	return nbr_cmd;
}

public void setNbr_cmd(int nbr_cmd){
	this.nbr_cmd = nbr_cmd;
}

public int getNbr_obs_vis(){
	return nbr_obs_vis;
}

public void setNbr_obs_vis(int nbr_obs_vis){
	this.nbr_obs_vis = nbr_obs_vis;
}

public int getDistance(){
	return distance;
}

public void setDistance(int distance){
	this.distance = distance;
}

public void incNbr_cmd(){
	this.nbr_cmd++;
}

public void incNbr_obs(){
	this.nbr_obs = this.nbr_obs +1;
}

public void incNbr_obs_vis(){
	this.nbr_obs_vis = this.nbr_obs_vis +1;
}

public void incDistance(){
	this.distance=this.distance +1;
}


@Override
public String toString() {
	return "Measures [nbr_cmd=" + nbr_cmd + ", nbr_obs=" + nbr_obs + ", nbr_obs_vis=" + nbr_obs_vis + ", distance="
			+ distance + "]";
}


}
