package com.example.game;
import android.app.Application;
public class MyApplication extends Application{
    private boolean isMusicChecked = false;

    public boolean isMusicChecked() {
        return isMusicChecked;
    }

    public void setMusicChecked(boolean value) {
        isMusicChecked = value;
    }
}
