package com.example.shinoharanaoki.mytouchcontrolsample.sample5_multi_touch_color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MultiTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MultiTouchView(MultiTouchActivity.this));
    }
}
