package chessLogic;

class NonePiece extends Piece{
	
	NonePiece(Color color){
		super(color);
	}
	
	NonePiece(Color color, Position position){
		super(color, position);
	}
	
	public String toString(){
		return " ";
	}

}
