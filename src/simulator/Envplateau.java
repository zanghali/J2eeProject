package simulator;


	public class Envplateau extends Env{
		
		protected int pourcentage_obstacle;
		private int nb_obs;
					
		public Envplateau (int sizeX, int sizeY, int pourcentage_obstacle)
		{
		super();
		this.sizeX=sizeX;
		this.sizeY=sizeY;
		this.nb_obs=0;
		this.pourcentage_obstacle=pourcentage_obstacle;
		generation();
		}
		
		public void generation()
		{
			int i,j;
			int random;
			
			matrix = new String[this.sizeX][this.sizeY];
			
			for(i=0; i<this.sizeX; i++){
				for(j=0; j<this.sizeY; j++){ 
				random = (int)( Math.random()*101);
				matrix[i][j]="FREE";
				if(random<pourcentage_obstacle){
					matrix[i][j]="OBSTACLE";
				    this.nb_obs++;
				}
				}
				}
		}
		
		public int getNb_obs() {
			return nb_obs;
		}
		
		public int getPourcentage_obstacle() {
			return this.pourcentage_obstacle;
		}
		
		public void addEnvPlateau(int x,int y, String cases)
		{
			switch(cases)
			{
			case "OBSTACLE":
			matrix[x][y]="OBSTACLE";
			break;
			case "FREE":
			matrix[x][y]="FREE";
			break;
			case "ROBOT":
			matrix[x][y]="ROBOT";
			break;
			default:
			matrix[x][y]="UNKNOWN";
			break;
			}
		}		
	}