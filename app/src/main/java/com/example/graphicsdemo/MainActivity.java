package com.example.graphicsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    BBView bbView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bbView=(BBView) findViewById(R.id.bb_view);
    }

    public void addBall(View view) {
        bbView.countUp();
        /*
        Timer t=new Timer();
        TimerTask tt=new TimerTask() {
            @Override
            public void run() {
                bbView.time+=1;
            }
        };
        t.schedule(tt,0,1000);
         */
    }
}