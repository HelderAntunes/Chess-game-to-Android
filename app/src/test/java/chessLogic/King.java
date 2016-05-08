package chessLogic;

import java.util.ArrayList;

class King extends Piece{
	
	King(Color color){
		super(color);
	}
	
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
