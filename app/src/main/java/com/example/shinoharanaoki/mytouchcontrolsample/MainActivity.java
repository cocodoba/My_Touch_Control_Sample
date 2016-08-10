package com.example.shinoharanaoki.mytouchcontrolsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickSample1(View view) {
        Intent i = new Intent(this, MoveImageViewActivity.class);
        startActivity(i);
    }

    public void onClickSample2(View view) {
        Intent i = new Intent(this, MoveBallObjectActivity.class);
        startActivity(i);
    }

    public void onClickSample3(View view) {
        Intent i = new Intent(this, ThreeBallsActivity.class);
        startActivity(i);
    }

    public void onClickSample4(View view) {
        Intent i = new Intent(this, LoopingBalloonsActivity.class);
        startActivity(i);
    }
}
