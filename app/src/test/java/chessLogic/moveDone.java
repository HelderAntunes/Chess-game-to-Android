package chessLogic;

class MoveDone {
	private int sourceX;
	private int sourceY;
	private int DestX;
	private int DestY;
	
	MoveDone(int sourceX,int sourceY,int DestX,int DestY){
		this.sourceX=sourceX;
		this.sourceY=sourceY;
		this.DestX=DestX;
		this.DestY=DestY;
	}
	
	int getSourceX(){
		return this.sourceX;
	}
	
	int getSourceY(){
		return this.sourceY;
	}
	
	int getDestX(){
		return this.DestX;
	}
	
	int getDestY(){
		return this.DestY;
	}
	
}
