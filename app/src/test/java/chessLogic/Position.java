package chessLogic;

public class Position {
	int x;
	int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean equals(Object p2){
		if(p2 != null && p2 instanceof Position && ((Position)p2).x == x && ((Position)p2).y == y)
			return true;
		else
			return false;
	}
}
