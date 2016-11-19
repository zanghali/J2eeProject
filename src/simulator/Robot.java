package simulator;
 
import java.util.ArrayList;
 
public class Robot {
    private int x;
    private int y;
    private String orientation;
    Envrobot env;
    Capteur capteur;
    private Measures measures;
     
 
public Robot(String orientation_initial, int x_initiale, int y_initiale,int sizeX, int sizeY, Envplateau envplateau){
    this.x=x_initiale;
    this.y=y_initiale;
    this.orientation=orientation_initial;
    env = new Envrobot(sizeX,sizeY);
    this.env.addEnvRobot(this.x,this.y,"ROBOT");
    this.measures= new Measures();
    this.capteur= new Capteur(orientation_initial, x_initiale, y_initiale, sizeX, sizeY);
    Maj_env (x_initiale,y_initiale,orientation_initial, this.env, envplateau);
}
 
 
 
public void Maj_env (int x,int y, String orientation, Envrobot env, Envplateau env1){
    capteur= new Capteur(orientation, x, y, env1.getSizeX(), env1.getSizeY());
    ArrayList<Coord> list_obs=new ArrayList<Coord>();
    list_obs=capteur.regarder_vision(env1);
    this.measures.setNbr_obs_vis(0);
    for(Coord list:list_obs){
        if (env.contenu(list.getX(),list.getY()) == "VISITE"){
        	//this.measures.incNbr_visite();
        }
        else{
            if (list.getContenu() == "OBSTACLE"){
                this.measures.incNbr_obs_vis();
            }
            env.addEnvRobot(list.getX(),list.getY(),list.getContenu());
        }
    }
    int tab[]=this.env.Comptage();
    this.measures.setNbr_obs_vis(tab[0]);
    this.measures.setNb_visite(tab[1]);
    this.measures.setNb_unknown(tab[2]);
    //this.measures.incNbr_cmd();
}

public Measures getMeasures() {
    return measures;
}
public int getX() {
    return x;
}
public void setX(int x) {
    this.x = x;
}
public int getY() {
    return y;
}
public void setY(int y) {
    this.y = y;
}
 
 
 
public String getOrientation() {
    return orientation;
}
 
 
 
public void setOrientation(String orientation) {
    //this.measures.incNbr_cmd();
    this.orientation = orientation;
}
 
 
 
public Envrobot getEnv() {
    return env;
}
 
 
 
public Capteur getCapteur() {
    return capteur;
}
 
 
 
}