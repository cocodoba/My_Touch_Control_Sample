package com.example.shinoharanaoki.mytouchcontrolsample.sample3_multi_touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThreeBallsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ThreeBallsMultiTouchView(ThreeBallsActivity.this));
    }
}
