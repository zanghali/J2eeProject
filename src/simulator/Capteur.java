package simulator;

import java.util.ArrayList;

public class Capteur {
	private int x_Robot;
	private int y_Robot;
	private int size;
	private int sizeX_env;
	private int sizeY_env;
	public String vision[][];

	public Capteur(String orientation, int x, int y, int sizeX, int sizeY){
		this.x_Robot=x;
		this.y_Robot=y;
		this.size=5;
		this.sizeX_env=sizeX;
		this.sizeY_env=sizeY;
		generation_vision(orientation);

	}

	public void generation_vision(String orientation){

		int i,j;
		this.vision = new String[this.size][this.size];
		for(i=0; i<this.size; i++){
			for(j=0; j<this.size; j++){ 
				this.vision[i][j]="0";
			}
		}
		this.vision[1][1]="1";
		this.vision[1][2]="1";
		this.vision[1][3]="1";

		if(orientation=="E"){
			this.vision=rotateMatrixRight(this.vision);
		}
		if(orientation=="O"){
			this.vision=rotateMatrixLeft(this.vision);

		}
		if(orientation=="S"){
			this.vision=rotateMatrixRight(this.vision);
			this.vision=rotateMatrixRight(this.vision);
		}		

	}
	public ArrayList<Coord> regarder_vision(Envplateau env){
		ArrayList<Coord> list_obstacle=new ArrayList<>();
		for(int i=0; i<this.size; i++){
			for(int j=0; j<this.size; j++){ 
				if(this.vision[i][j]=="1"){
					Coord coord=new Coord();
					coord.x=this.x_Robot - (2-i);
					coord.y=this.y_Robot - (2-j);
					if(coord.x<0  || coord.x>this.sizeX_env-1 || coord.y<0  || coord.y>this.sizeY_env-1){}
					else{
						coord.contenu=env.contenu(coord.getX(), coord.getY());
						list_obstacle.add(coord);
					}
				}			
			}
		}
		return list_obstacle;
	}
	
	public String[][] rotateMatrixRight(String[][] matrix)
	{
	    /* W and H are already swapped */
	    int w = matrix.length;
	    int h = matrix[0].length;
	    String[][] ret = new String[h][w];
	    for (int i = 0; i < h; ++i) {
	        for (int j = 0; j < w; ++j) {
	            ret[i][j] = matrix[w - j - 1][i];
	        }
	    }
	    return ret;
	}


	public String[][] rotateMatrixLeft(String[][] matrix)
	{
	    /* W and H are already swapped */
	    int w = matrix.length;
	    int h = matrix[0].length;   
	    String[][] ret = new String[h][w];
	    for (int i = 0; i < h; ++i) {
	        for (int j = 0; j < w; ++j) {
	            ret[i][j] = matrix[j][h - i - 1];
	        }
	    }
	    return ret;
	}
}