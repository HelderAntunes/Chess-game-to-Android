package com.example.helder.chessapp;

import android.content.Context;
import android.content.Intent;
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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import chessLogic.ChessGame;
import chessLogic.Position;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        GameSerial g = (GameSerial)i.getSerializableExtra("gameObject");
        ChessGame game = new ChessGame(g.getBoard(), g.getWhiteKingMoved(), g.getBlackKingMoved(), g.isWhiteTurn());
        BoardView myView = new BoardView(this, game);

        setContentView(R.layout.activity_game);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.gameLayoutId);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        relativeLayout.addView(myView, params);
    }

    public class BoardView extends View
    {
        Paint p;
        ChessGame game;
        ArrayList<Position> possibleMoves = new ArrayList<Position>();
        Position currPos = new Position(0,0);
        int widthBox;
        int startX;
        int startY;
        int widthScreen;
        int heightScreen;
        boolean whiteView = true;
        boolean saveGameBtnIsTouched = false;
        boolean rotateBtnIsTouched = false;

        public BoardView(Context context, ChessGame chessGame)
        {
            super(context);
            this.game = chessGame;

            setScreenMeasures(context);

            if(widthScreen < heightScreen*0.75) {
                widthBox = widthScreen / 8;
            }
            else{
                widthBox = heightScreen*3/4/8;
            }
            startX = 0;
            startY = 0;

            invalidate();
        }

        void setScreenMeasures(Context context){
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            widthScreen = size.x;
            heightScreen = size.y;
        }

        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            drawBoard(canvas);
            drawBottomIcons(canvas);
        }

        void drawBoard(Canvas canvas){
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
                    if(whiteView)
                        r.set(startX+j*widthBox,startY+i*widthBox,startX+j*widthBox+widthBox,startY+i*widthBox+widthBox);
                    else
                        r.set(startX+(7-j)*widthBox,startY+(7-i)*widthBox,startX+(7-j)*widthBox+widthBox,startY+(7-i)*widthBox+widthBox);
                    boolean boxIsWhite = boxIsWhite(i,j);
                    b1 = selectBitmap(board[i][j], boxIsWhite);
                    canvas.drawBitmap(b1, null, r, p);
                }
            }
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

        void drawBottomIcons(Canvas canvas){
            p = new Paint();
            Rect r = new Rect();
            Bitmap b1;

            b1 = BitmapFactory.decodeResource(getResources(), R.drawable.noneinformation);
            r.set(0, 8*widthBox, widthScreen, heightScreen);
            canvas.drawBitmap(b1, null, r, p);

            int sizeOfIcons = 2*widthBox;
            if(saveGameBtnIsTouched)
                b1 = BitmapFactory.decodeResource(getResources(), R.drawable.savegametouched);
            else
                b1 = BitmapFactory.decodeResource(getResources(), R.drawable.savegame);
            r.set(0, 8*widthBox, sizeOfIcons, 8*widthBox+sizeOfIcons);
            canvas.drawBitmap(b1, null, r, p);

            if(rotateBtnIsTouched)
                b1 = BitmapFactory.decodeResource(getResources(), R.drawable.rotatebtntouched);
            else
                b1 = BitmapFactory.decodeResource(getResources(), R.drawable.rotatebtn);
            r.set(sizeOfIcons, 8*widthBox, sizeOfIcons*2,  8*widthBox+sizeOfIcons);
            canvas.drawBitmap(b1, null, r, p);

            b1 = selectBmpInformation();
            r.set(2*sizeOfIcons, 8*widthBox, widthScreen,  8*widthBox+sizeOfIcons);
            canvas.drawBitmap(b1, null, r, p);
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
            else if(game.isWhiteToPlay()){
                return BitmapFactory.decodeResource(getResources(), R.drawable.whiteturn);
            }
            else if(!game.isWhiteToPlay()){
                return BitmapFactory.decodeResource(getResources(), R.drawable.blackturn);
            }
            else
                return BitmapFactory.decodeResource(getResources(), R.drawable.noneinformation);
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

            if(event.getAction() == MotionEvent.ACTION_DOWN){
                float x = event.getX();
                float y = event.getY();

                if(peaceWasTouched(x,y))
                    updatePossibleMovesOrBoard(x, y);
                else if(saveGameBtnWasTouched(x,y))
                    saveGameBtnIsTouched = true;
                else if(rotateBtnWasTouched(x,y))
                    rotateBtnIsTouched = true;
                invalidate();
                return true;
            }
            else if(event.getAction() == MotionEvent.ACTION_UP){

                if(saveGameBtnIsTouched)
                    saveToFile();
                if(rotateBtnIsTouched)
                    whiteView = !whiteView;

                rotateBtnIsTouched = false;
                saveGameBtnIsTouched = false;
                invalidate();
                return true;
            }
            else
                return super.onTouchEvent(event);
        }

        void updatePossibleMovesOrBoard(float x, float y) {
            int j = calcIndexOfWidthInBoard(x);
            int i = calcIndexOfHeightInBoard(y);

            for(int k = 0;k < possibleMoves.size();k++){
                int u = possibleMoves.get(k).getY();
                int v = possibleMoves.get(k).getX();
                if(u == i && v == j){
                    game.move(currPos, possibleMoves.get(k));
                    possibleMoves.clear();
                    invalidate();
                }
            }

            currPos = new Position(j,i);
            possibleMoves = game.getPossibleMoves(currPos);
            invalidate();
        }

        boolean peaceWasTouched(float x, float y){
            return x >= startX && x <= startX+widthBox*7+widthBox && y >= startY && y <= startY+7*widthBox+widthBox;
        }

        boolean saveGameBtnWasTouched(float x, float y){
            return x >= 0 && x <= 2*widthBox && y >= 8*widthBox && y <= 10*widthBox;
        }

        boolean rotateBtnWasTouched(float x, float y){
            return x >= 2*widthBox && x <= 4*widthBox && y >= 8*widthBox && y <= 10*widthBox;
        }

        int calcIndexOfWidthInBoard(float x){
            if(whiteView)
                return (int)(x-startX)/widthBox;
            else
                return 7-(int)(x-startX)/widthBox;
        }

        int calcIndexOfHeightInBoard(float y){
            if(whiteView)
                return (int)(y-startY)/widthBox;
            else
                return 7-(int)(y-startY)/widthBox;
        }


        public void saveToFile() {
            Context context = getContext();
            String fileName = "savedGame.txt";
            try {
                FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                GameSerial gameSerial = new GameSerial(game.getBoard(), game.whiteKingMoved(), game.blackKingMoved(), game.isWhiteToPlay());
                objectOutputStream.writeObject(gameSerial);
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
