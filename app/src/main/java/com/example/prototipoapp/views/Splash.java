package com.example.prototipoapp.views;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prototipoapp.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {
    ActivitySplashBinding activitySplashBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());

        setContentView(activitySplashBinding.getRoot());

        //intro che non ho fatto commint ancora
        activitySplashBinding.animationView.addAnimatorListener(new Animator.AnimatorListener() {
            //queste sono le funzioni di una animazione
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
                //con questo activity lambda fai tutto di un solo colpo
                startActivity(new Intent(getApplicationContext(), MainActivity1.class));


            }
        });


    }
}