package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity{

    Button homeButton;
    TextView myScoreText, compScoreText, resultText, playerName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        homeButton = findViewById(R.id.homeButton);

        playerName = findViewById(R.id.playerName);
        myScoreText = findViewById(R.id.myScoreText);
        compScoreText = findViewById(R.id.compScoreText);
        resultText = findViewById(R.id.resultText);


        int nMyScore = getIntent().getIntExtra("keyScore", 0) ;
        int nCompScore = getIntent().getIntExtra("key1Score", 0);

        Intent intent = getIntent();
        String message = intent.getStringExtra("playerName");
        playerName.setText(message);

        myScoreText.setText("Your Score: " + nMyScore);
        compScoreText.setText("Comp Score: " + nCompScore);

        if(nMyScore==3){
            resultText.setText("YOU WIN!!");
        }
        else{
            resultText.setText("YOU LOSE");
        }
    }

    public void home(View v){
        Intent k = new Intent(this, MainActivity.class);
        startActivity(k);
    }

}