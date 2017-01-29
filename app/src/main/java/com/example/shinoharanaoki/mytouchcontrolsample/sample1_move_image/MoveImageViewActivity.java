package com.example.shinoharanaoki.mytouchcontrolsample.sample1_move_image;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.shinoharanaoki.mytouchcontrolsample.R;

public class MoveImageViewActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private FrameLayout frameLayout01;
    private ImageView target;
    private Button trash;

    private int targetLocalX;
    private int targetLocalY;

    private int screenX;
    private int screenY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_image_view);

        frameLayout01 = (FrameLayout)findViewById(R.id.FrameLayout01);

        target = (ImageView)findViewById(R.id.ImageView01); //ターゲット ＝ ぞうさんの絵
        target.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int x = (int)event.getRawX();
        int y = (int)event.getRawY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                targetLocalX = target.getLeft();
                targetLocalY = target.getTop();

                screenX = x;
                screenY = y;

                break;

            case MotionEvent.ACTION_MOVE:

                int diffX = screenX - x;
                int diffY = screenY - y;

                targetLocalX -= diffX;
                targetLocalY -= diffY;

                target.layout(targetLocalX,
                        targetLocalY,
                        targetLocalX + target.getWidth(),
                        targetLocalY + target.getHeight());

                screenX = x;
                screenY = y;

                break;

            case MotionEvent.ACTION_UP:

                int targetRight  = target.getLeft() + target.getWidth();
                int targetBottom = target.getTop() + target.getHeight();

                break;
        }

        Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("TouchEvent", "getAction()" + "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TouchEvent", "getAction()" + "ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("TouchEvent", "getAction()" + "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("TouchEvent", "getAction()" + "ACTION_CANCEL");
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        int childCount = frameLayout01.getChildCount();
        if(childCount == 1) {
            frameLayout01.addView(target);
        }
    }
}
