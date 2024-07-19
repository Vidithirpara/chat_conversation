package com.example.whatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;


public class SplashActivity extends AppCompatActivity {
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        lottie = (LottieAnimationView) findViewById(R.id.lottie);

        lottie.animate().setDuration(1500).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,signInActivity.class);
                startActivity(intent);
            }
        }, 2500);
    }
}