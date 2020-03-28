package com.example.app_smart_pan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.app_smart_pan.Activity.login.LoginActivity;
import com.example.app_smart_pan.R;

public class SplashScreenActivity extends AppCompatActivity {

    private int DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        MediaPlayer splashSound = MediaPlayer.create(getApplicationContext(), R.raw.voila);
//        splashSound.start();
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }, DELAY);
    }
}
