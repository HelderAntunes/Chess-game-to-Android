package GameLogic;

import java.util.ArrayList;

class King extends Piece{
	
	King(Color color, Position position){
		super(color, position);
	}
	
	public String toString(){
		return "K";
	}
	
	ArrayList<Position> getPossibleMoves(Box board[][]){
		ArrayList<Position> validPositions = new ArrayList<Position>();
		
		int x = this.position.getX();
		int y = this.position.getY();
		
		if(y >= 1){
			if(x >= 1){
				validPositions.add(new Position(x-1, y-1));
			}
			if(x <= 6){
				validPositions.add(new Position(x+1, y-1));
			}
			validPositions.add(new Position(x, y-1));
		}
		if(x >= 1){
			validPositions.add(new Position(x-1, y));
		}
		if(x <= 6){
			validPositions.add(new Position(x+1, y));
		}
		if(y <= 6){
			if(x >= 1){
				validPositions.add(new Position(x-1, y+1));
			}
			if(x <= 6){
				validPositions.add(new Position(x+1, y+1));
			}
			validPositions.add(new Position(x, y+1));
		}
		
		validPositions = new MoveGenerator().removePositionsOccupied(validPositions, board, this);
		
		return validPositions;
	}
}
