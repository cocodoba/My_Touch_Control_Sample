package com.example.shinoharanaoki.mytouchcontrolsample.sample4_sound_on_touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FlashBallsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FlashBallsView(FlashBallsActivity.this));
    }
}
