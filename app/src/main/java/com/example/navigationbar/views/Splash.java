package com.example.navigationbar.views;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationbar.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {
    Handler handler;
    ActivitySplashBinding activitySplashBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());

        setContentView(activitySplashBinding.getRoot());

        //intro che non ho fatto commint ancora
        activitySplashBinding.animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {


            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                // startActivity(new Intent(getApplicationContext(), MainActivity1.class));


            }
        });
        activitySplashBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash.this, MainActivity1.class);
                startActivity(intent);
            }
        });


    }
}