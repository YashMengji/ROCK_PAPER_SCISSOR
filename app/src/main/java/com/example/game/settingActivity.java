package com.example.game;

import android.Manifest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class settingActivity extends AppCompatActivity {

    EditText nameText, levelText;
    Switch musicSwitch;
    MediaPlayer game_music;
    ImageButton backButton;
    Button submitName;

    private static final int WRITE_EXTERNAL_STORAGE_CODE = 1;

    private boolean isMusicPlaying = false; // To track music state
    private MyApplication myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        nameText = findViewById(R.id.nameText);
        levelText = findViewById(R.id.levelText);
        musicSwitch = findViewById(R.id.musicSwitch);
        backButton = findViewById(R.id.backButton);
        submitName = findViewById(R.id.saveButton);

        game_music = MediaPlayer.create(this, R.raw.game_music);

        myApp = (MyApplication) getApplication();

        submitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions, WRITE_EXTERNAL_STORAGE_CODE);
                    }
                    else{
                        saveText();
                    }
                }
            }
        });
        musicSwitch.setChecked(myApp.isMusicChecked());
        // Check the state of the musicSwitch
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isMusicPlaying = true;
                    if (!game_music.isPlaying()) {
                        // Music is currently playing
                        game_music.start();
                    }
                    game_music.setLooping(true);
                } else {
                    isMusicPlaying = false;
                    game_music.pause();
                }
            }
        });
        myApp.setMusicChecked(isMusicPlaying);
    }




    public void saveText() {
        String message = nameText.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput("name.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveText();
            } else {
                Toast.makeText(this, "Permission needed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void backMethod(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
