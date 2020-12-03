package com.example.navigationbar.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.navigationbar.R;

public class Splash extends AppCompatActivity {
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //intro che non ho fatto commint ancora

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity1.class));
                finish();
            }
        },3000);



    }
}