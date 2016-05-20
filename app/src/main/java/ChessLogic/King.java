package ChessLogic;

import java.util.ArrayList;

class King extends Piece{

	private boolean moved = false;

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
		
		// castling
		if(moved == false){

			if(x < 5 && board[y][x+1].isEmpty() && board[y][x+2].isEmpty() 
					&& board[y][x+3].getPiece() instanceof Rook &&
					!((Rook)board[y][x+3].getPiece()).wasMoved()){
				validPositions.add(new Position(x+2,y));
			}

			if(x > 3 && board[y][x-1].isEmpty() && board[y][x-2].isEmpty() 
					&& board[y][x-3].isEmpty() 
					&& board[y][x-4].getPiece() instanceof Rook &&
					!((Rook)board[y][x-4].getPiece()).wasMoved()){
				validPositions.add(new Position(x-2,y));
			}
		}

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
