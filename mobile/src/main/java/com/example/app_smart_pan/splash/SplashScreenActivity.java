package com.example.app_smart_pan.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import com.example.app_smart_pan.R;
import com.example.app_smart_pan.login.LoginActivity;

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
