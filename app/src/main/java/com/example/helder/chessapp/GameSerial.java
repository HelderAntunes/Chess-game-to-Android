package com.example.helder.chessapp;

import java.io.Serializable;

/**
 * Created by Helder on 31/05/2016.
 */
public class GameSerial implements Serializable{

    private char board[][] = new char[8][8];
    private boolean whiteKingMoved;
    private boolean blackKingMoved;
    private boolean isWhiteTurn;

    public GameSerial(char[][] board, boolean whiteKingMoved, boolean blackKingMoved, boolean isWhiteTurn){
        this.board = board;
        this.whiteKingMoved = whiteKingMoved;
        this.blackKingMoved = blackKingMoved;
        this.isWhiteTurn = isWhiteTurn;
    }

    public char[][] getBoard(){
        return board;
    }

    public boolean getWhiteKingMoved(){
        return whiteKingMoved;
    }

    public boolean getBlackKingMoved(){
        return blackKingMoved;
    }

    public boolean isWhiteTurn(){
        return isWhiteTurn;
    }
}
