package com.example.game;


import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView welcomeAnimation;
    Button quitButton,settingButton;

    MediaPlayer game_music;
    String playerName;
    int levelValue;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quitButton = findViewById(R.id.quitButton);
        settingButton = findViewById(R.id.settingButton);
        game_music = MediaPlayer.create(this, R.raw.game_music);

        //game_music.start();
        Intent intent = getIntent();
        playerName = intent.getStringExtra("playerName");


        if (intent != null && intent.hasExtra("levelValue")) {
            levelValue = Integer.parseInt(intent.getStringExtra("levelValue"));
        } else {
            // Handle the case where "levelValue" is not provided in the Intent
            levelValue = 3; // Set a default value or handle it differently based on your requirements.
        }


       //quitButton.setOnClickListener(new View.OnClickListener() {
       //    @Override
       //    public void onClick(View view) {
       //        onBackPressed();
       //    }
       //});
    }

    public void setting(View v){
        //Launch real settings
        Intent k = new Intent(this, settingActivity.class);
        startActivity(k);

    }

    public void startGame(View v) {
        // Launch a main game
         Intent i = new Intent(this, SettingsActivity.class);
        i.putExtra("playerName", playerName);
        i.putExtra("levelValue", levelValue);
        startActivity(i);

    }

    public void instruction(View v){
        // Launches an instruction
        Intent j = new Intent(this, instructionActivity.class);
        startActivity(j);
    }

    public void showCredit(View v){
        // Launches an instruction
        Intent h = new Intent(this, statsActivity.class);
        startActivity(h);
    }

    @Override
    public void onBackPressed(){
        onPause();
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        game_music.stop();
                        game_music.release();
                        Intent intent = new Intent(getApplicationContext(), LauncherActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finishAffinity(); // Finish the current activity
                    }
                })
                .setNegativeButton("No",null)
                .show();
        }


    public void exitApp(View view) {
        onPause();
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(game_music!=null && game_music.isPlaying()) {
                            game_music.stop();
                            game_music.release();
                        }
                        Intent intent = new Intent(getApplicationContext(), LauncherActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finishAffinity(); // Finish the current activity
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
}