package com.example.graphicsdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class BBView extends View {

    int maxBalls=100;
    int x[]=new int[maxBalls+1];
    int y[]=new int[maxBalls+1];
    int dx[]=new int[maxBalls+1];
    int dy[]=new int[maxBalls+1];
    int width=600;
    int height=500;
    int count=0;
    int radius=6;
    int margin=2*dpToPx(10);
    int collision=0;
    Long startTime=0L;
    int start=0;


    public void countUp(){
        x[count] = radius + (int)(Math.random() * ((width - radius) + 1));
        y[count] = radius + (int)(Math.random() * ((height - radius) + 1));
        dx[count]=3;
        dy[count]=3;
        if(count<maxBalls) count++;
        startTime = System.currentTimeMillis();
        collision=0;
        start=1;
    }

    public BBView(Context context) {
        super(context);
    }

    public BBView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BBView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BBView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Paint p=new Paint();
        p.setColor(Color.BLACK);

        canvas.drawLine(0,0,width,0,p);
        canvas.drawLine(0,0,0,height,p);
        canvas.drawLine(0,height,width,height,p);
        canvas.drawLine(width,0,width,height,p);

        for(int i = 0; i<count; i++) {   //Draw Balls
            canvas.drawCircle(x[i], y[i], radius, p);
        }
//        for(int i = 0; i<count; i++) {    //Wall collision
//            if((x[i]-radius)<=0 || (x[i]+radius)>=width) dx[i]=-dx[i];
//            if((y[i]-radius)<=0 || (y[i]+radius)>=height) dy[i]=-dy[i];
//        }

        for(int i = 0; i<count; i++) {    //Wall collision
            if((x[i]+dx[i]-radius)<0 || (x[i]+dx[i]+radius)>width) dx[i]=-dx[i];
            if((y[i]+dy[i]-radius)<0 || (y[i]+dy[i]+radius)>height) dy[i]=-dy[i];
        }

        for(int i = 0; i<count; i++) {      //Ball collision
            for(int j=i+1;j<count;j++){
                int xdiff=x[i]-x[j];
                int ydiff=y[i]-y[j];
                if(xdiff*xdiff+ydiff*ydiff<=4*radius*radius){
                    collision++;
                    dx[i]=-dx[i];
                    dy[i]=-dy[i];
                    dx[j]=-dx[j];
                    dy[j]=-dy[j];
                }
            }
        }

        for(int i = 0; i<count; i++) {   //Move
            x[i]+=dx[i];
            y[i]+=dy[i];
        }
        Long currentTime=(System.currentTimeMillis()-startTime)/1000;
        /*
        if(currentTime>=60){
            Log.d("col","Balls: "+count+" Collisions: "+collision);
            countUp();
        }
        */
        p.setTextSize(30);
        if(start==1) canvas.drawText("Collisions: "+collision+" (in "+currentTime+" seconds)",0,height+margin,p);
        
        invalidate();
    }
}
