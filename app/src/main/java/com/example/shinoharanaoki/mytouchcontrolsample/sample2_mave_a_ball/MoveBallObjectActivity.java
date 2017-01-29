package com.example.shinoharanaoki.mytouchcontrolsample.sample2_mave_a_ball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MoveBallObjectActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new BallPlayView(MoveBallObjectActivity.this));
    }


}
