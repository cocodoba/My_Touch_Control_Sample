package com.example.shinoharanaoki.mytouchcontrolsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
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
    private Paint paint_ball;
    private Paint paint_ball_number;

    private Ball[] balls;

    public ThreeBallsView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        // ペイントオブジェクトを設定する
        paint_ball = new Paint();
        paint_ball.setAntiAlias(true);
        paint_ball.setColor(Color.BLUE);    // (4)
        paint_ball.setStyle(Paint.Style.FILL);    // (5)

        paint_ball_number = new Paint();
        paint_ball_number.setAntiAlias(true);
        paint_ball_number.setStyle(Paint.Style.FILL);    // (5)

        balls = new Ball[3];
        float radius = 50;
        float init_x = 100;
        float init_y = 100;
        int color = Color.BLUE;
        for (int i=0;i<3;i++) {
            balls[i] = new Ball(init_x, init_y, radius, color);
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
            paint_ball.setColor(balls[i].color);
            canvas.drawCircle(ball_x, ball_y, radius, paint_ball);  // (6)

            //円の中に書く数字を描画する
            float num_x = balls[i].cx;
            float num_y = balls[i].cy +100;
            paint_ball_number.setTextSize(30);
            paint_ball_number.setColor(Color.BLUE);
            canvas.drawText(String.valueOf(i), num_x, num_y, paint_ball_number);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {    // (7)

        touch_x = event.getX();    // (10)
        touch_y = event.getY();    // (11)

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:    // 指をタッチした    // (8)
                assert true;    // 何もしない
                break;

            case MotionEvent.ACTION_MOVE:    // 指を動かしている    // (9)

                for (int i=0;i<balls.length;i++) {
                    if(balls[i].checkTouch(touch_x,touch_y)){
                        Log.d(TAG, "onTouchEvent: Ball[" + i + "] is touched");
                        balls[i].cx = touch_x;
                        balls[i].cy = touch_y;
                        balls[i].color = Color.RED;

                        break;
                    }
                }

                break;

            case MotionEvent.ACTION_UP:        // 指を離した    // (12)
                for (int i=0;i<balls.length;i++) {
                    Log.d(TAG, "onTouchEvent: Ball[" + i + "] is touched");
                    balls[i].color = Color.BLUE;
                }
                break;

            default:
                assert true;    // 何もしない
                break;
        }

        invalidate();    // (13)

        return true;    // (14)
    }
}
