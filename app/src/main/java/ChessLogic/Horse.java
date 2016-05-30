package chessLogic;

import java.util.ArrayList;

class Horse extends Piece{

	Horse(Color color, Position position){
		super(color, position);	
	}

	public String toString(){
		return "H";
	}

	ArrayList<Position> getPossibleMoves(Box board[][]){
		ArrayList<Position> validPositions = new ArrayList<Position>();
	
		int x = this.position.getX();
		int y = this.position.getY();

		if(x > 0){
			if(y >= 2)
				validPositions.add(new Position(x-1,y-2));
			if(y <= 5)
				validPositions.add(new Position(x-1,y+2));
		}
		if(x > 1){
			if(y >= 1)
				validPositions.add(new Position(x-2,y-1));
			if(y <= 6)
				validPositions.add(new Position(x-2,y+1));
		}
		if(x < 7){
			if(y >= 2)
				validPositions.add(new Position(x+1,y-2));
			if(y <= 5)
				validPositions.add(new Position(x+1,y+2));
		}
		if(x < 6){
			if(y >= 1)
				validPositions.add(new Position(x+2,y-1));
			if(y <= 6)
				validPositions.add(new Position(x+2,y+1));
		}
		
		validPositions = new MoveGenerator().removePositionsOccupied(validPositions, board, this);
			
		return validPositions;
	}

}
