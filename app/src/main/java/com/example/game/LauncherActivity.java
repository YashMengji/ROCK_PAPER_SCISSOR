package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import android.animation.AnimatorListenerAdapter;
import android.animation.Animator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class LauncherActivity extends AppCompatActivity {

    LottieAnimationView welcomeAnimation;
    MediaPlayer game_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        welcomeAnimation = findViewById(R.id.welcomeAnimation);
        welcomeAnimation.setAnimation(R.raw.home_animation);
        welcomeAnimation.setRepeatCount(0);
        welcomeAnimation.bringToFront();
        welcomeAnimation.playAnimation();

        game_music = MediaPlayer.create(this, R.raw.game_music);
        //game_music.setLooping(true);
        //game_music.start();
    }

    public void startGame(View v) {
        // Launch a new activity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}