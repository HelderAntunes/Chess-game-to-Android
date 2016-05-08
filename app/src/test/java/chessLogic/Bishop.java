package chessLogic;

import java.util.ArrayList;

class Bishop extends Piece{

	Bishop(Color color){
		super(color);
	}

	Bishop(Color color, Position position){
		super(color, position);
	}

	public String toString(){
		return "B";
	}

	ArrayList<Position> getPossibleMoves(Box board[][]){
		ArrayList<Position> validPositions = new ArrayList<Position>();

		int x = this.position.getX();
		int y = this.position.getY();
		
		// up-left
		for(int i = y-1, j = x-1;i >= 0 && j >= 0;i--, j--){
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == this.getColor())
					break;
				else if(board[i][j].getPiece().getColor() != this.getColor()){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else{
				validPositions.add(new Position(j,i));
			}
		}
		
		// up-right
		for(int i = y-1, j = x+1;i >= 0 && j < 8;i--, j++){
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == this.getColor())
					break;
				else if(board[i][j].getPiece().getColor() != this.getColor()){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else{
				validPositions.add(new Position(j,i));
			}
		}
		
		// down-left
		for(int i = y+1, j = x-1;i < 8 && j >= 0;i++, j--){
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == this.getColor())
					break;
				else if(board[i][j].getPiece().getColor() != this.getColor()){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else{
				validPositions.add(new Position(j,i));
			}
		}
		
		// down-right
		for(int i = y+1, j = x+1;i < 8 && j < 8;i++, j++){
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == this.getColor())
					break;
				else if(board[i][j].getPiece().getColor() != this.getColor()){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else{
				validPositions.add(new Position(j,i));
			}
		}
		
		return validPositions;
	}

}
