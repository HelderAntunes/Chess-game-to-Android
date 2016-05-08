package chessLogic;

import java.util.ArrayList;

class Piece {
	
	protected Color color;
	protected Position position;
	
	Piece(Color color, Position position){
		this.color = color;
		this.position = position;
	};
	
	Piece(Color color){
		this.color = color;
	}
	
	public String toString(){
		return " ";
	}
	
	Color getColor(){
		return color;
	}
	
	ArrayList<Position> getPossibleMoves(Box board[][]){
		ArrayList<Position> validPositions = new ArrayList<Position>();
		return validPositions;
	}
}
