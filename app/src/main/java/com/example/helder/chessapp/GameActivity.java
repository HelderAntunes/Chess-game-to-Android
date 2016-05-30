package com.example.helder.chessapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

import chessLogic.ChessGame;
import chessLogic.Position;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoardView kp = new BoardView(this);
        setContentView(kp);
    }

    public class BoardView extends View
    {
        Paint p;
        ChessGame game = new ChessGame();
        ArrayList<Position> possibleMoves = new ArrayList<Position>();
        Position currPos = new Position(0,0);
        int widthBox;
        int startX;
        int startY;
        int widthScreen;
        int heightScreen;

        public BoardView(Context context)
        {
            super(context);

            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            widthScreen = size.x;
            heightScreen = size.y;
            if(widthScreen < heightScreen*0.75) {
                widthBox = widthScreen / 8;
            }
            else{
                widthBox = heightScreen*3/4/8;
            }
            startX = 0;
            startY = 0;
        }

        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            p = new Paint();
            Rect r = new Rect();
            Bitmap b1;

            char [][]board = game.getBoard();
            for(int i = 0;i < possibleMoves.size();i++){
                Position pos = possibleMoves.get(i);
                board[pos.getY()][pos.getX()] = 'X';
            }

            for(int i = 0;i < 8;i++){
                for(int j = 0;j < 8;j++){
                    r.set(startX+j*widthBox,startY+i*widthBox,startX+j*widthBox+widthBox,startY+i*widthBox+widthBox);
                    boolean boxIsWhite = boxIsWhite(i,j);
                    b1 = selectBitmap(board[i][j], boxIsWhite);
                    canvas.drawBitmap(b1, null, r, p);
                }
            }

            b1 = BitmapFactory.decodeResource(getResources(), R.drawable.noneinformation);
            r.set(0, 8*widthBox, widthScreen, heightScreen);
            canvas.drawBitmap(b1, null, r, p);
            b1 = selectBmpInformation();
            r.set(0, 8*widthBox, widthScreen, 8*widthBox+70);
            canvas.drawBitmap(b1, null, r, p);
        }

        boolean boxIsWhite(int i, int j){
            if(i%2 == 0){
                if(j%2 == 0)
                    return true;
                return false;
            }
            else{
                if(j%2 == 0)
                    return false;
                return true;
            }
        }

        Bitmap selectBmpInformation(){
            if(game.blackWinsByCheckMate()){
                return BitmapFactory.decodeResource(getResources(), R.drawable.blackwins);
            }
            else if(game.whiteWinsByCheckMate()){
                return BitmapFactory.decodeResource(getResources(), R.drawable.whitewins);
            }
            else if(game.blackIsInCheck() || game.whiteIsInCheck()){
                return BitmapFactory.decodeResource(getResources(), R.drawable.check);
            }
            else{
                return BitmapFactory.decodeResource(getResources(), R.drawable.noneinformation);
            }
        }

        Bitmap selectBitmap(char c, boolean boxIsWhite){
            if(boxIsWhite){
                switch (c){
                    case 'B':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bbishopw);
                    case 'b':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wbishopw);
                    case 'Q':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bqueenw);
                    case 'q':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wqueenw);
                    case 'K':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bkingw);
                    case 'k':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wkingw);
                    case 'P':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bpawnw);
                    case 'p':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wpawnw);
                    case 'H':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bhorsew);
                    case 'h':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.whorsew);
                    case 'R':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.brookw);
                    case 'r':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wrookw);
                    case ' ':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.whitebox);
                    case 'X':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.whiteboxshaded);
                    default:
                        return BitmapFactory.decodeResource(getResources(), R.drawable.whitebox);
                }
            }
            else{
                switch (c){
                    case 'B':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bbishopb);
                    case 'b':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wbishopb);
                    case 'Q':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bqueenb);
                    case 'q':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wqueenb);
                    case 'K':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bkingb);
                    case 'k':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wkingb);
                    case 'P':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bpawnb);
                    case 'p':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wpawnb);
                    case 'H':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.bhorseb);
                    case 'h':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.whorseb);
                    case 'R':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.brookb);
                    case 'r':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.wrookb);
                    case ' ':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.blackbox);
                    case 'X':
                        return BitmapFactory.decodeResource(getResources(), R.drawable.blackboxshaded);
                    default:
                        return BitmapFactory.decodeResource(getResources(), R.drawable.blackbox);
                }
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            if(event.getAction() != MotionEvent.ACTION_DOWN)
                return super.onTouchEvent(event);

            float x = event.getX();
            float y = event.getY();
            if(x >= startX && x <= startX+widthBox*7+widthBox && y >= startY && y <= startY+7*widthBox+widthBox){

                int j = (int)(x-startX)/widthBox;
                int i = (int)(y-startY)/widthBox;

                for(int k = 0;k < possibleMoves.size();k++){
                    int u = possibleMoves.get(k).getY();
                    int v = possibleMoves.get(k).getX();
                    if(u == i && v == j){
                        game.move(currPos, possibleMoves.get(k));
                        possibleMoves.clear();
                        invalidate();
                        return true;
                    }
                }

                currPos = new Position(j,i);
                possibleMoves = game.getPossibleMoves(currPos);
            }
            invalidate();
            return true;
        }

    }
}