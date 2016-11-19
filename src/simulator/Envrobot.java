package simulator;

public class Envrobot extends Env {

	public Envrobot(int sizeX, int sizeY) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		generation();
	}

	public void generation() {
		int i, j;

		matrix = new String[this.sizeX][this.sizeY];

		for (i = 0; i < sizeX; i++) {
			for (j = 0; j < sizeY; j++) {
				this.matrix[i][j] = "UNKNOWN";
			}
		}
	}

	public int[] Comptage() {
		int nbr_obs = 0;
		int nbr_visite = 0;
		int nbr_unknown = 0;
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				if (this.matrix[i][j] == "OBSTACLE") {
					nbr_obs += 1;
				} else {
					if (this.matrix[i][j] == "VISITE") {
						nbr_visite++;
					} else {
						if (this.matrix[i][j] == "UNKNOWN") {
							nbr_unknown++;
						}
					}
				}
			}
		}
		int tab[] ={nbr_obs,nbr_visite,nbr_unknown};
		//tab[0]=nbr_obs;
		//tab[1]=nbr_visite;
		//tab[2]=nbr_unknown;
		
		return tab;
	}

	public void addEnvRobot(int x, int y, String caseDecouverte) {
		if (caseDecouverte != null) {
			switch (caseDecouverte) {
			case "OBSTACLE":
				matrix[x][y] = "OBSTACLE";
				break;
			case "FREE":
				matrix[x][y] = "FREE";
				break;
			case "ROBOT":
				matrix[x][y] = "ROBOT";
				break;
			case "VISITE":
				matrix[x][y] = "VISITE";
				break;
			default:
				matrix[x][y] = "UNKNOWN";
				break;
			}
		}
	}

}