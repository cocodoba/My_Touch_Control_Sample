package com.example.shinoharanaoki.mytouchcontrolsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shinoharanaoki on 2016/08/10.
 */
public class ThreeBallsView extends View{
    private static final String TAG = ThreeBallsView.class.getSimpleName();
    private final ThreeBallsView self = this;

    private float touch_x;    // 画面のタッチされた X 座標    // (1)
    private float touch_y;    // 画面のタッチされた Y 座標    // (2)

    private Canvas canvas;
    private Paint paint;

    private Ball[] balls;

    public ThreeBallsView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        // ペイントオブジェクトを設定する
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);    // (4)
        paint.setStyle(Paint.Style.FILL);    // (5)


        balls = new Ball[3];
        float radius = 50;
        float init_x = 100;
        float init_y = 100;
        for (int i=0;i<3;i++) {
            balls[i] = new Ball(init_x, init_y, radius);
            init_x += 100;
            init_y += 50;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 円を描画する

        for(int i=0;i<balls.length;i++) {

            float ball_x = balls[i].cx;
            float ball_y = balls[i].cy;
            float radius = balls[i].radius;
            canvas.drawCircle(ball_x, ball_y, radius, paint);  // (6)
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {    // (7)
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:    // 指をタッチした    // (8)
                assert true;    // 何もしない
                break;

            case MotionEvent.ACTION_MOVE:    // 指を動かしている    // (9)
                touch_x = event.getX();    // (10)
                touch_y = event.getY();    // (11)
                break;

            case MotionEvent.ACTION_UP:        // 指を離した    // (12)
                assert true;    // 何もしない
                break;

            default:
                assert true;    // 何もしない
                break;
        }

        invalidate();    // (13)

        return true;    // (14)
    }
}