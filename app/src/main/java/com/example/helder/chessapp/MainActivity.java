package com.example.helder.chessapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import chessLogic.ChessGame;


public class MainActivity extends AppCompatActivity{

    private Button newGameButton;
    private Button loadGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.mainActivityLayoutId);
        rl.setBackgroundColor(Color.BLACK);

        newGameButton = (Button) findViewById(R.id.buttonNewGame) ;
        newGameButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onNewGameButtonClick(v);
            }
        });

        loadGameButton = (Button) findViewById(R.id.buttonLoadingGame) ;
        loadGameButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onLoadingGameButtonClick(v);
            }
        });
    }

    public void onNewGameButtonClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        char[][] b = {{'R', 'H', 'B', 'Q', 'K', 'B', 'H', 'R'},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'r', 'h', 'b', 'q', 'k', 'b', 'h', 'r'}};

        GameSerial g = new GameSerial(b, false, false, true);
        intent.putExtra("gameObject", g);
        startActivity(intent);
    }

    public void onLoadingGameButtonClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        GameSerial gameSerial = readFromFile(this);
        if(gameSerial != null){
            intent.putExtra("gameObject", gameSerial);
            startActivity(intent);
        }
    }

    public static GameSerial readFromFile(Context context) {
        String fileName = "savedGame.txt";
        GameSerial gameSerial = null;
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            gameSerial = (GameSerial) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return gameSerial;
    }

}
