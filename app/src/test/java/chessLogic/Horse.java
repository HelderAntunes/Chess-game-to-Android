package chessLogic;

import java.util.ArrayList;

class Horse extends Piece{

	Horse(Color color){
		super(color);
	}

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
		
		for(int i = 0;i < validPositions.size();i++){
			Position position = validPositions.get(i);
			int yp = position.getY();
			int xp = position.getX();
			if(board[yp][xp].isEmpty() == false && board[yp][xp].getPiece().getColor() == this.getColor()){
				validPositions.remove(i);
				i--;
			}
		}
			
		return validPositions;
	}

}
