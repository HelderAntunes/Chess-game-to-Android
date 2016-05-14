package GameLogic;

import java.util.ArrayList;

class Rook extends Piece{

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
}
