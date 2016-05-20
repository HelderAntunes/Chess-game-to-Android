package ChessLogic;

class NonePiece extends Piece{

	NonePiece(Color color, Position position){
		super(color, position);
	}
	
	public String toString(){
		return " ";
	}

}
