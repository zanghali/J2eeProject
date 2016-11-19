package simulator;

public class Coord {
	int x;
	int y;
	String contenu;
	
	public Coord(){
		x=0;
		y=0;
	}
	public Coord(int x, int y){
		this.x=x;
		this.y=y; 
	}
public Coord(int x, int y,String contenu){
	this.x=x;
	this.y=y;
	this.contenu=contenu;
}

public int getX() {
	return x;
}

public int getY() {
	return y;
}

public String getContenu() {
	return contenu;
}

@Override
public String toString() {
	return "Coord [x=" + x + ", y=" + y + ", contenu=" + contenu + "]";
}

}