package chessLogic;

import java.util.ArrayList;

class Queen extends Piece{

	Queen(Color color){
		super(color);	
	}

	Queen(Color color, Position position){
		super(color, position);
	}

	public String toString(){
		return "Q";
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

		// up
		for(int i = y-1, j = x;i >= 0;i--){
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

		// down
		for(int i = y+1, j = x;i < 8;i++){
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

		// right
		for(int i = y, j = x+1;j < 8;j++){
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

		// left
		for(int i = y, j = x-1;j >= 0;j--){
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
