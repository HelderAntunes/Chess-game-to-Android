package ChessLogic;

import java.util.ArrayList;

class Queen extends Piece{

	Queen(Color color, Position position){
		super(color, position);
	}

	public String toString(){
		return "Q";
	}

	ArrayList<Position> getPossibleMoves(Box board[][]){
		ArrayList<Position> validPositions = new MoveGenerator().getDiagonalMoves(board, this);
		validPositions.addAll(new MoveGenerator().getVerticalAndHorizontalMoves(board, this));
		return validPositions;
	}

}
