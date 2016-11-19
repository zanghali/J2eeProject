package simulator;


import java.util.ArrayList;

public class RobotCrt {
	private Robot Robot;
	private Envplateau environnement;
	private ArrayList<Coord> historique;
	
	// Pour la découverte automatique
		final static String UNKNOWN = "UNKNOWN";
	    final static String VISITE = "VISITE";
	    final static String FREE = "FREE";
	    final static String OBSTACLE = "OBSTACLE";
	

	//Paramètres d’initialisation: un environement Env, un robot Robot
	public RobotCrt (Robot Robot, Envplateau environnement){
		this.environnement = environnement;
		this.Robot = Robot;
	}
	
	//construteur par défaut
	public RobotCrt (){
		this.environnement =new Envplateau(15,15,5);
		this.environnement.addEnvPlateau(3, 3, "ROBOT");
		this.Robot = new Robot ("N",3,3,15,15,this.environnement);
		this.historique=new ArrayList<Coord>();
		this.historique.add(new Coord(3,3));
	}
	
	//méthode permettant de récupérer l’environnement perçu par le Robot
	public Envrobot getEnvRobot() {
		return this.Robot.getEnv();
	}

	//méthode permettant de récupérer l’environnement utilisé Env
	public Envplateau getEnvironnement() {
		return this.environnement;
	}
	
	
	//getters
	public String getContenu(int x,int y){ 
		return this.Robot.getEnv().contenu(x,y);
	}
	
	public String getOrientation(){ 
		return this.Robot.getOrientation();
	}
	public Measures get_measures(){
		
		return this.Robot.getMeasures();
	}
	
	
	public int getNbr_cmd(){
		return this.Robot.getMeasures().getNbr_cmd();
	}
	public int getsizeEnvY(){
		return this.environnement.getSizeY();
	}
	public int getsizeEnvX(){
		return this.environnement.getSizeX();
	}

	//méthode permettant de retourner ce que perçoit le capteur de vision du robot
		public ArrayList<Coord> ObservationCapteur (){
			ArrayList<Coord> list= new ArrayList<>();
			list=this.Robot.getCapteur().regarder_vision(this.environnement);
			return list;
		}
		
		private void Deplacement(int newcoordX,int newcoordY,int coordX,int coordY){
			this.Robot.setX(newcoordX);
            this.Robot.setY(newcoordY);
            this.Robot.getEnv().addEnvRobot(coordX, coordY, "VISITE");
            this.Robot.getEnv().addEnvRobot(newcoordX, newcoordY, "ROBOT");
            this.environnement.addEnvPlateau(coordX, coordY, "FREE");
            this.environnement.addEnvPlateau(newcoordX, newcoordY, "ROBOT");
            this.Robot.Maj_env (newcoordX,newcoordY, this.Robot.getOrientation(), this.getEnvRobot(), this.environnement);
            this.Robot.getMeasures().incDistance();
            //add les nouvelle coord du robot
            this.historique.add(new Coord(newcoordX,newcoordY));
            //
            System.out.println(Env.printMatrix(this.Robot.getEnv().getMatrix(),this.Robot.getEnv().getSizeX(),this.Robot.getEnv().getSizeY()));
		}

		   //méthodes permettant de déplacer le robot (UP,DOWN,LEFT,RIGHT)
        public String DeplacementDOWN(){
            int coordX = this.Robot.getX();
            int coordY = this.Robot.getY();
            int newcoordX = coordX+1;
            int newcoordY = coordY;
            this.Robot.setOrientation("S");
            this.Robot.getMeasures().incNbr_cmd();
            this.Robot.Maj_env (coordX,coordY, this.Robot.getOrientation(), this.getEnvRobot(), this.environnement);
            if ( coordX >= (this.Robot.getEnv().getSizeX()-1) )
                return "Deplacement impossible: bord de l'environnement";
 
            if (this.Robot.getEnv().contenu(newcoordX,coordY) == "OBSTACLE"){
                return "Deplacement impossible : obstacle";
            }else{
            	Deplacement(newcoordX,newcoordY,coordX,coordY);
                return "Deplacement effectué";
            }
        }
        public String DeplacementUP(){
            int coordX = this.Robot.getX();
            int coordY = this.Robot.getY();
            int newcoordX = coordX-1;
            int newcoordY = coordY;
            this.Robot.setOrientation("N");
            this.Robot.getMeasures().incNbr_cmd();
            this.Robot.Maj_env (coordX,coordY, this.Robot.getOrientation(), this.getEnvRobot(), this.environnement);
            if ( coordX <= 0)
                return "Deplacement impossible: bord de l'environnement";
 
            if (this.Robot.getEnv().contenu(newcoordX,coordY) == "OBSTACLE")
                return "Deplacement impossible : obstacle";
            else{
            	Deplacement(newcoordX,newcoordY,coordX,coordY);
                return "Deplacement effectué";
            }
 

        }
        public String DeplacementLEFT(){
            int coordX = this.Robot.getX();
            int coordY = this.Robot.getY();
            int newcoordX = coordX;
            int newcoordY = coordY - 1;
            this.Robot.setOrientation("O");
            this.Robot.getMeasures().incNbr_cmd();      
            this.Robot.Maj_env (coordX,coordY, this.Robot.getOrientation(), this.getEnvRobot(), this.environnement);
            if ( coordY <= 0)
                return "Deplacement impossible: bord de l'environnement";
 
            if (this.Robot.getEnv().contenu(coordX,newcoordY) == "OBSTACLE"){
                return "Deplacement impossible : obstacle";
            }else{
            	Deplacement(newcoordX,newcoordY,coordX,coordY);
                return "Deplacement effectué";
            }
        }
        public String DeplacementRIGHT(){
            int coordX = this.Robot.getX();
            int coordY = this.Robot.getY();
            int newcoordX = coordX;
            int newcoordY = coordY + 1;
            this.Robot.setOrientation("E");
            this.Robot.getMeasures().incNbr_cmd();
            this.Robot.Maj_env (coordX,coordY, this.Robot.getOrientation(), this.getEnvRobot(), this.environnement);
            if ( coordY >= (this.Robot.getEnv().getSizeY()-1))
                return "Deplacement impossible: bord de l'environnement";
 
            if (this.Robot.getEnv().contenu(coordX,newcoordY) == "OBSTACLE"){
                return "Deplacement impossible : obstacle";
            }else{
            	Deplacement(newcoordX,newcoordY,coordX,coordY);
                return "Deplacement effectué";
            }
        }

		public ArrayList<Coord> getHistorique() {
			return historique;
		}
		
		
		// Fonction de découverte optimisée de la carte
	    public void decouverte(){
	    	
	    	// Coordonnées de la case destination
	    	System.out.println(findcase(this.Robot.getX(),this.Robot.getY()));
	    	
	    	if(!envConnu())
	    		traverse(this.Robot.getX(),this.Robot.getY());
	    	
	    	else
	    		System.out.println("Environnement connu !");
	        
	    }

	    // Fonction de déplacement automatique
	    private boolean traverse(int i, int j) {
	    	
	    	// On ne peut pas aller à une case inconnue ou avec un obstacle
	    	if (!inRange(i,j)||isObstacle(i,j) || isUnknown(i,j)) {
	            return false;
	        }
	    	
	    	// On peut aller à une case vide ou visitée à l'intérieur de la carte
	        if (isValide(i,j)) {
	            return true;
	        }

	    	// Si la case inconnue la plus proche est en HAUT
	        if (findcase(i,j).getX()<i){
	        	
	        	// Si on peut y aller directement
	        	if(traverse(i-1, j)){	
	        		this.DeplacementUP();
	        	}
	        	// Si on peut contourner par la droite
	        	else if (findcase(i,j).getY()>=j && traverse(i,j+1)){
	        		this.DeplacementRIGHT();
	        		if(traverse(i-1,j+1))
	        			this.DeplacementUP();
	        	}	        	
	        	// Si on peut contourner par la gauche
	        	else if (findcase(i,j).getY()<=j && traverse(i,j-1)){
	        		this.DeplacementLEFT();
	        		if(traverse(i-1,j-1))
	        			this.DeplacementUP();
	        	}
	        	// Sinon on recule
	        	else{
	        		this.DeplacementDOWN();
	        	}
	            return true;
	        		
	        }
	        // Si la case inconnue la plus proche est en BAS
	        else if (findcase(i,j).getX()>i){
	        	
	        	// Si on peut y aller directement
	        	if(traverse(i+1, j)){
	        		this.DeplacementDOWN();
	        	}
	        	// Si on peut contourner par la gauche
	        	else if (findcase(i,j).getY()<=j && traverse(i,j-1)){
	        		this.DeplacementLEFT();
	        		if(traverse(i+1,j-1))
	        			this.DeplacementDOWN();
	        	}
	        	// Si on peut contourner par la droite
	        	else if (findcase(i,j).getY()>=j && traverse(i,j+1)){
	        		this.DeplacementRIGHT();
	        		if(traverse(i+1,j+1))
	        			this.DeplacementDOWN();
	        	}
	        	// Sinon on recule
	        	else{
	        		this.DeplacementUP();
	        	}
	            return true;
	        }

	        // Si la case inconnue la plus proche est sur la même ligne
	        else{
	        	// Si la case inconnue la plus proche est à GAUCHE
	        	if ((findcase(i,j).getY()<j)){
	        	
		        	// Si on peut y aller directement
		        	if(traverse(i, j-1)){
		        		this.DeplacementLEFT();
		        	}
		        	// Si on peut contourner par le haut
		        	else if (findcase(i,j).getX()<=i && traverse(i-1,j)){
		        		this.DeplacementUP();
		        		if(traverse(i-1,j-1))
		        			this.DeplacementLEFT();
		        	}
		        	// Si on peut contourner par le bas
		        	else if (findcase(i,j).getX()>=i && traverse(i+1,j)){
		        		this.DeplacementDOWN();
		        		if(traverse(i+1,j-1))
		        			this.DeplacementLEFT();
		        	}
		        	// Sinon on recule
		        	else{
		        		this.DeplacementRIGHT();
		        	}
		            return true;
	        	}					
		        // Si la case inconnue la plus proche est à DROITE
		        else if (findcase(i,j).getY()>j){
		        	
		        	// Si on peut y aller directement
		        	if(traverse(i, j+1)){
		        		this.DeplacementRIGHT();
		        	}
		        	// Si on peut contourner par le bas
		        	else if (findcase(i,j).getX()>=i && traverse(i+1,j)){
		        		this.DeplacementDOWN();
		        		if(traverse(i+1,j+1))
		            		this.DeplacementRIGHT();
		        	}
		        	// Si on peut contourner par en haut
		        	else if (findcase(i,j).getX()<=i && traverse(i-1,j)){
		        		this.DeplacementUP();
		        		if(traverse(i-1,j+1))
		            		this.DeplacementRIGHT();
		        	}
		        	// Sinon on recule
		        	else{
		        		this.DeplacementLEFT();
		        	}
		            return true;
		        }
	        }
	        return false;
	    }

	    // Fonction de recherche de la case inconnue la plus proche
		private Coord findcase(int X,int Y){ 

			int i=0,j=0,k=0;
			int taillemax;
			Coord actuel = new Coord();
			
			// Vérification optimisée des 4 cases les plus proches
			if(verifeUnknown(X,Y-1)){actuel.x=X;actuel.y=Y-1;actuel.contenu="UNKNOWN"; return actuel;}
			if(verifeUnknown(X-1,Y)){actuel.x=X-1;actuel.y=Y;actuel.contenu="UNKNOWN"; return actuel;}
			if(verifeUnknown(X,Y+1)){actuel.x=X;actuel.y=Y+1;actuel.contenu="UNKNOWN"; return actuel;}
			if(verifeUnknown(X+1,Y)){actuel.x=X+1;actuel.y=Y;actuel.contenu="UNKNOWN"; return actuel;}
			
			// Vérification optimisée des 8 cases les plus proches avec 2 pas
			if(verifeUnknown(X,Y-2) && !isObstacle(X,Y-1)){actuel.x=X;actuel.y=Y-2;actuel.contenu="UNKNOWN"; return actuel;}
			if(verifeUnknown(X-2,Y) && !isObstacle(X-1,Y)){actuel.x=X-2;actuel.y=Y;actuel.contenu="UNKNOWN"; return actuel;}
			if(verifeUnknown(X,Y+2) && !isObstacle(X,Y+1)){actuel.x=X;actuel.y=Y+2;actuel.contenu="UNKNOWN"; return actuel;}
			if(verifeUnknown(X+2,Y) && !isObstacle(X+1,Y)){actuel.x=X+2;actuel.y=Y;actuel.contenu="UNKNOWN"; return actuel;}
			
			if(verifeUnknown(X+1,Y+1)){actuel.x=X+1;actuel.y=Y+1;actuel.contenu="UNKNOWN"; return actuel;}
			if(verifeUnknown(X-1,Y-1)){actuel.x=X-1;actuel.y=Y-1;actuel.contenu="UNKNOWN"; return actuel;}
			if(verifeUnknown(X+1,Y-1)){actuel.x=X+1;actuel.y=Y-1;actuel.contenu="UNKNOWN"; return actuel;}
			if(verifeUnknown(X-1,Y+1)){actuel.x=X-1;actuel.y=Y+1;actuel.contenu="UNKNOWN"; return actuel;}
				
			// On parcourt le reste des cases de manière moins précise
			if(this.getEnvRobot().getMatrix().length>this.getEnvRobot().getMatrix()[0].length)
				taillemax=this.getEnvRobot().getMatrix().length;
			else
				taillemax=this.getEnvRobot().getMatrix()[0].length;
	    			
	    	for(i=2;i<=taillemax;i++){
	    		
				for(k=-i;k<=i;k++)
				{
					actuel.x=X+k;
					for(j=-i;j<=i;j++)
					{
						actuel.y=Y+j;
						if(inRange(X+k,Y+j))
						{
							if(this.Robot.getEnv().contenu(X+k, Y+j).equals(UNKNOWN))
							{
								actuel.contenu="UNKNOWN";
								return actuel;
							}
						}
					}
				}
			}
				
			actuel.x=X;
			actuel.y=Y;
			actuel.contenu="ROBOT";
			
			return actuel; 
			
		}
		
	    private boolean envConnu(){
	        for (String[] line : this.getEnvRobot().getMatrix())        	
	        	for (String column : line)
	        		if(column.equals(UNKNOWN))
	        			return false;
	            
	    	return true;
	    }
	    
		private Boolean verifeUnknown(int X,int Y){
		    return inRange(X,Y) && this.getEnvRobot().getMatrix()[X][Y].equals(UNKNOWN);
	    }

		private boolean isValide(int i, int j) {
	        return (isFree(i,j) || isVisite(i,j)) && inRange(i,j);
	    }

	    private boolean isUnknown(int i, int j) {
	        return this.getEnvRobot().getMatrix()[i][j].equals(UNKNOWN);
	    }

	    private boolean isFree(int i, int j) {
	        return this.getEnvRobot().getMatrix()[i][j].equals(FREE);
	    }

	    private boolean isVisite(int i, int j) {
	        return this.getEnvRobot().getMatrix()[i][j].equals(VISITE);
	    }
	    
	    private boolean isObstacle(int i, int j) {
	        return this.getEnvRobot().getMatrix()[i][j].equals(OBSTACLE);
	    }

	    private boolean inRange(int i, int j) {
	        return inHeight(i) && inWidth(j);
	    }

	    private boolean inHeight(int i) {
	        return i >= 0 && i < this.getEnvRobot().getMatrix().length;
	    }
	   
	    private boolean inWidth(int j) {
	        return j >= 0 && j < this.getEnvRobot().getMatrix()[0].length;
	    }

		
		

	}
