package ChessLogic;

import java.util.ArrayList;

class Bishop extends Piece{

	Bishop(Color color, Position position){
		super(color, position);
	}

	public String toString(){
		return "B";
	}

	ArrayList<Position> getPossibleMoves(Box board[][]){
		ArrayList<Position> validPositions = new MoveGenerator().getDiagonalMoves(board, this);
		return validPositions;
	}

}
