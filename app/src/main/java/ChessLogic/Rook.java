package ChessLogic;

import java.util.ArrayList;

class Rook extends Piece{
	
	private boolean moved = false;

	Rook(Color color, Position position){
		super(color, position);
	}

	public String toString(){
		return "R";
	}

	ArrayList<Position> getPossibleMoves(Box board[][]){
		ArrayList<Position> validPositions = new MoveGenerator().getVerticalAndHorizontalMoves(board, this);
		return validPositions;
	}
	
	void setMoved(boolean moved){
		this.moved = moved;
	}
	
	boolean wasMoved(){
		if(moved)
			return true;
		else
			return false;
	}
}
