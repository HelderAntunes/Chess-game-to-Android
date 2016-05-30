package com.example.helder.chessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity{

    private Button newGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameButton = (Button) findViewById(R.id.buttonNewGame) ;
        newGameButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onButtonClick(v);
            }
        });
    }

    public void onButtonClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }




}
