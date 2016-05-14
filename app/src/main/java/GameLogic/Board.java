package GameLogic;

import java.util.ArrayList;


class Board {

	private Box board[][] = new Box[8][8];

	Board(char[][] board){
		initialize(board);
	}
	
	void initialize(char[][] board){
		for(int i = 0;i < board.length;i++)
			for(int j = 0;j < board.length;j++){
				char c = board[i][j];
				Position position = new Position(j,i);
				Piece piece = null;
				Color colorBoard;
				if(i%2 == 0)
					if(j%2 == 0)
						colorBoard = Color.BLACK;
					else
						colorBoard = Color.WHITE;
				else
					if(j%2 == 0)
						colorBoard = Color.WHITE;
					else
						colorBoard = Color.BLACK;
				switch(c){
				case ' ':
					piece = new NonePiece(colorBoard, position);
					break;
				case 'p':
					piece = new Pawn(Color.WHITE, position);
					break;
				case 'P':
					piece = new Pawn(Color.BLACK, position);
					break;
				case 'h':
					piece = new Horse(Color.WHITE, position);
					break;
				case 'H':
					piece = new Horse(Color.BLACK, position);
					break;
				case 'b':
					piece = new Bishop(Color.WHITE, position);
					break;
				case 'B':
					piece = new Bishop(Color.BLACK, position);
					break;
				case 'k':
					piece = new King(Color.WHITE, position);
					break;
				case 'K':
					piece = new King(Color.BLACK, position);
					break;
				case 'q':
					piece = new Queen(Color.WHITE, position);
					break;
				case 'Q':
					piece = new Queen(Color.BLACK, position);
					break;
				case 'r':
					piece = new Rook(Color.WHITE, position);
					break;
				case 'R':
					piece = new Rook(Color.BLACK, position);
					break;
				}
				this.board[i][j] = new Box(piece, colorBoard);
			}
	}
	
	char[][] getBoard(){
		char[][] boardChar = new char[8][8];
		for(int i = 0;i < 8;i++)
			for(int j = 0;j < 8;j++){
				Piece piece = board[i][j].getPiece();
				if(piece instanceof NonePiece)
					boardChar[i][j]  = ' ';
				else if(piece.getColor() == Color.WHITE)
					boardChar[i][j] = piece.toString().toLowerCase().charAt(0);
				else
					boardChar[i][j] = piece.toString().charAt(0);
			}
		return boardChar;
	}

	void move(Position iniPosition, Position endPosition){
		Piece pieceToMove = board[iniPosition.getY()][iniPosition.getX()].getPiece();
		removePiece(iniPosition);
		insertPiece(endPosition, pieceToMove);
	}
	
	void removePiece(Position position){
		Color color = board[position.getY()][position.getX()].getColor();
		board[position.getY()][position.getX()].setPiece(new NonePiece(color, position));
	}
	
	void insertPiece(Position position, Piece pieceToInsert){
		board[position.getY()][position.getX()].setPiece(pieceToInsert);
		pieceToInsert.setPosition(position);
	}
	
	boolean whiteWinsByCheckMate(){
		boolean whiteWins = true;
		ArrayList<Position> positionsValids;
		Position position = null;
		Piece piece = null;
		
		for(int i = 0;i < 8;i++)
			for(int j = 0;j < 8;j++){
				piece = board[i][j].getPiece();
				
				if(!(piece instanceof NonePiece) && piece.getColor() == Color.BLACK){
					position = new Position(j,i);
					positionsValids = getPossibleMoves(position);
					
					if(positionsValids.size() > 0){
						whiteWins = false;
						break;
					}		
				}
			}
		
		return whiteWins;
	}
	
	boolean blackWinsByCheckMate(){
		boolean blackWins = true;
		ArrayList<Position> positionsValids;
		Position position = null;
		Piece piece = null;
		
		for(int i = 0;i < 8;i++)
			for(int j = 0;j < 8;j++){
				piece = board[i][j].getPiece();
				
				if(!(piece instanceof NonePiece) && piece.getColor() == Color.WHITE){
					position = new Position(j,i);
					positionsValids = getPossibleMoves(position);
			 		
					if(positionsValids.size() > 0){
						blackWins = false;
						break;
					}		
				}
			}
		
		return blackWins;
	}
	
	boolean whiteKingIsInCheck(){
		Position whiteKingPos = findWhiteKing();
		
		for(int i = 0;i < 8;i++)
			for(int j = 0;j < 8;j++){
				Piece piece = board[i][j].getPiece();
				if(piece.getColor() == Color.WHITE)
					continue;
				ArrayList<Position> positions = piece.getPossibleMoves(board);
				for(Position position: positions)
					if(position.equals(whiteKingPos))
						return true;
			}
		
		return false;
	}
	
	boolean blackKingIsInCheck(){
		Position blackKingPos = findBlackKing();
		
		for(int i = 0;i < 8;i++)
			for(int j = 0;j < 8;j++){
				Piece piece = board[i][j].getPiece();
				if(piece.getColor() == Color.BLACK)
					continue;
				ArrayList<Position> positions = piece.getPossibleMoves(board);
				for(Position position: positions)
					if(position.equals(blackKingPos))
						return true;
			}
		
		return false;
	}
	
	Position findWhiteKing(){
		for(int i = 0;i < 8;i++)
			for(int j = 0;j < 8;j++){
				Piece piece = board[i][j].getPiece();
				if(piece instanceof King && piece.getColor() == Color.WHITE)
					return new Position(j,i);
			}
		return null;
	}
	
	Position findBlackKing(){
		for(int i = 0;i < 8;i++)
			for(int j = 0;j < 8;j++){
				Piece piece = board[i][j].getPiece();
				if(piece instanceof King && piece.getColor() == Color.BLACK)
					return new Position(j,i);
			}
		return null;
	}
	
	ArrayList<Position> getPossibleMoves(Position position){
		ArrayList<Position> validPositions = board[position.getY()][position.getX()].getPiece().getPossibleMoves(board);
		Piece piece = board[position.getY()][position.getX()].getPiece();
		
		for(int i = 0;i < validPositions.size();i++){
			Position newPosition = validPositions.get(i);
			Board newBoard = new Board(this.getBoard());
			newBoard.move(position, newPosition);
			
			if(newBoard.blackKingIsInCheck() && piece.getColor() == Color.BLACK){
				validPositions.remove(i);
				i--;
			}
			
			if(newBoard.whiteKingIsInCheck() && piece.getColor() == Color.WHITE){
				validPositions.remove(i);
				i--;
			}
		}
		
		return validPositions;
	} 
	
	Box getBox(Position position){
		return board[position.getY()][position.getX()];
	}

}
