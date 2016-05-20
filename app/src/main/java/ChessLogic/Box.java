package ChessLogic;

class Box {
	private Piece piece;
	private Color color;
	
	Box(Piece piece,Color color){
		this.piece=piece;
		this.color=color;
	}
	
	String getChar(){
		return this.piece.toString();
	}
	
	Piece getPiece(){
		return piece;
	}
	
	void setPiece(Piece newPiece){
		this.piece = newPiece;
	}
	
	boolean isEmpty(){
		return piece instanceof NonePiece;
	}
	
	Color getColor(){
		return color;
	}
	
}
