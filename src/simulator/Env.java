package simulator;

import java.util.Formatter;
import java.util.Locale;


public abstract class Env {
	
	protected int sizeX;
	protected int sizeY;
	protected String matrix[][]= {{"UNKNOWN"},{"UNKNOWN"}};
		
	public Env()
	{
		super();
	}
	
	public void generation()
	{
		
	}
	
	public String contenu(int x, int y)
	{
		return matrix[x][y];
	}
	
	
	public static String printMatrix(String[][] matrix,int sizeX,int sizeY) {
		
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.FRENCH);
		
		String formatS = "%1$5s";
		String[] valueTab = new String[sizeY+1];
		valueTab[0]="";
		
		formatS=formatS+"x/y";
		for (int index = 0; index < sizeY; index++) {
		formatS =formatS + " %" + (index + 2) + "$5s";
		valueTab[index+1] = String.valueOf(index);
		}
		
		formatter.format(formatS + "\n", valueTab);
		formatter.format("%1$5s | %2$47s\n", "","_______________________________________________");
		
		for (int i = 0; i < sizeX; i++) {
			String formatS2 = "%1$5s | ";
			String[] valueTab2 = new String[sizeY+1];
			valueTab2[0]=String.valueOf(i);
			for (int j = 0; j < sizeY; j++) {
				formatS2 = formatS2 + " %" + (j + 2) + "$5s";
				valueTab2[j+1] = matrix[i][j];
			}
			formatter.format(formatS2 + "\n", valueTab2);
			}
		return formatter.toString() ;
		}
	
	
	public int getSizeX() {
		return this.sizeX;
	}

	public int getSizeY() {
		return this.sizeY;
	}

	
	public String[][] getMatrix() {
		return this.matrix;
	}
	

	public static void main(String [] args)
	{
		Env env = new Envplateau(2,2,90);
		//env.generation();
		/*System.out.println(env.contenu(0,0));
		System.out.println(env.contenu(0,1));
		System.out.println(env.contenu(0,2));
		System.out.println(env.contenu(0,3)+'\n');
		System.out.println(env.contenu(1,0));
		System.out.println(env.contenu(1,1));
		System.out.println(env.contenu(1,2));
		System.out.println(env.contenu(1,3)+'\n');*/
		
		System.out.println(Env.printMatrix(env.getMatrix(),env.getSizeX(),env.getSizeY()));
		
	}

	
}