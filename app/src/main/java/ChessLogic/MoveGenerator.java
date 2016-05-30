package chessLogic;

import java.util.ArrayList;

class MoveGenerator {

	ArrayList<Position> getVerticalAndHorizontalMoves(Box board[][], Piece piece){
		ArrayList<Position> validPositions = new ArrayList<Position>();

		int x = piece.getPosition().getX();
		int y = piece.getPosition().getY();
		Color color = piece.getColor();

		for(int i = y-1, j = x;i >= 0;i--)
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == color)
					break;
				else if(board[i][j].getPiece().getColor() != color){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else
				validPositions.add(new Position(j,i));

		for(int i = y+1, j = x;i < 8;i++)
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == color)
					break;
				else if(board[i][j].getPiece().getColor() != color){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else
				validPositions.add(new Position(j,i));

		for(int i = y, j = x+1;j < 8;j++)
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == color)
					break;
				else if(board[i][j].getPiece().getColor() != color){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else
				validPositions.add(new Position(j,i));

		for(int i = y, j = x-1;j >= 0;j--)
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == color)
					break;
				else if(board[i][j].getPiece().getColor() != color){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else
				validPositions.add(new Position(j,i));
		
		return validPositions;
	}

	ArrayList<Position> getDiagonalMoves(Box board[][], Piece piece){
		ArrayList<Position> validPositions = new ArrayList<Position>();

		int x = piece.getPosition().getX();
		int y = piece.getPosition().getY();
		Color color = piece.getColor();
		
		for(int i = y-1, j = x-1;i >= 0 && j >= 0;i--, j--)
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == color)
					break;
				else if(board[i][j].getPiece().getColor() != color){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else
				validPositions.add(new Position(j,i));

		for(int i = y-1, j = x+1;i >= 0 && j < 8;i--, j++)
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == color)
					break;
				else if(board[i][j].getPiece().getColor() != color){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else
				validPositions.add(new Position(j,i));


		for(int i = y+1, j = x-1;i < 8 && j >= 0;i++, j--)
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == color)
					break;
				else if(board[i][j].getPiece().getColor() != color){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else
				validPositions.add(new Position(j,i));

		for(int i = y+1, j = x+1;i < 8 && j < 8;i++, j++)
			if(board[i][j].isEmpty() == false){
				if(board[i][j].getPiece().getColor() == color)
					break;
				else if(board[i][j].getPiece().getColor() != color){
					validPositions.add(new Position(j,i));
					break;
				}
			}
			else
				validPositions.add(new Position(j,i));

		return validPositions;
	}
	
	ArrayList<Position> removePositionsOccupied(ArrayList<Position> positions, Box board[][], Piece piece){
		for(int i = 0;i < positions.size();i++){
			Position position = positions.get(i);
			int yp = position.getY();
			int xp = position.getX();
			if(board[yp][xp].isEmpty() == false && board[yp][xp].getPiece().getColor() == piece.getColor()){
				positions.remove(i);
				i--;
			}
		}
		return positions;
	}
}
