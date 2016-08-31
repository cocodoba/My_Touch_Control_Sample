package com.example.shinoharanaoki.mytouchcontrolsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shinoharanaoki on 2016/08/31.
 */
public class ThreeBallsMultiTouchView extends View {
    private static final String TAG = ThreeBallsMultiTouchView.class.getSimpleName();
    private final ThreeBallsMultiTouchView self = this;

    private float touch_x;    // 画面のタッチされた X 座標    // (1)
    private float touch_y;    // 画面のタッチされた Y 座標    // (2)

    private Canvas canvas;
    private Paint paint_ball;
    private Paint paint_ball_number;

    private Ball[] balls;

    public ThreeBallsMultiTouchView(Context context) {
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
        float radius = 100;
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
            float num_y = balls[i].cy +130;
            paint_ball_number.setTextSize(30);
            paint_ball_number.setColor(Color.BLUE);
            canvas.drawText(String.valueOf(i), num_x, num_y, paint_ball_number);
        }
    }

    boolean now_moving = false;
    float down_x;
    float down_y;


    @Override
    public boolean onTouchEvent(MotionEvent event) {    // (7)

        int eventAction = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);

        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                break;

            case MotionEvent.ACTION_POINTER_UP:
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                break;

            case MotionEvent.ACTION_MOVE:
                break;
        }

        int action1 = event.getAction();
        int action2 = event.getActionMasked();
        int count = event.getPointerCount();

        Log.d("", "getAction=0x" + String.format("%04x", action1) + ",getActionMasked=0x" + String.format("%04x", action2)
                + ",getPointerCount=" + count);

        for (int i=0; i<count; i++) {
            int pid = event.getPointerId(i);
            int id = event.findPointerIndex(pid);
            if (id == -1)
                continue;
            float x = event.getX(id);
            float y = event.getY(id);
            float p = event.getPressure(id);
            float s = event.getSize(id);
            int hsize = event.getHistorySize();

            String line = "\t";
            line += "getPointerId=" + pid + ",";
            line += "getHistorySize=" + hsize + ",";

            for (int j=0; j<hsize; j++) {
                float hx = event.getHistoricalX(id, j);
                float hy = event.getHistoricalY(id, j);
                float hp = event.getHistoricalPressure(id, j);
                float hs = event.getHistoricalSize(id, j);
                line += "x=" + String.format("%.2f", hx) + ",";
                line += "y=" + String.format("%.2f", hy) + ",";
                line += "p=" + String.format("%.4f", hp) + ",";
                line += "s=" + String.format("%.4f", hs) + ",";
            }

            line += "x=" + String.format("%.2f", x) + ",";
            line += "y=" + String.format("%.2f", y) + ",";
            line += "p=" + String.format("%.4f", p) + ",";
            line += "s=" + String.format("%.4f", s) + ",";

            Log.e("", line);
        }


        /*touch_x = event.getX();    // (10)
        touch_y = event.getY();    // (11)


        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:    // 指をタッチした    // (8)
                for (int i=0;i<balls.length;i++) {
                    if(balls[i].checkTouch(touch_x,touch_y)){
                        Log.d(TAG, "onTouchEvent: Ball[" + i + "] is touched");
                        balls[i].color = Color.YELLOW;

                        down_x = touch_x;
                        down_y = touch_y;
                        now_moving = true;

                        break;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:    // 指を動かしている    // (9)

                if (now_moving) {
                    for (int i=0;i<balls.length;i++) {
                        if(balls[i].checkTouch(touch_x,touch_y)){
                            Log.d(TAG, "onTouchEvent: Ball[" + i + "] is moving");
                            if(down_x<=touch_x) {
                                balls[i].cx += touch_x-down_x;
                                down_x = touch_x;
                            }else {
                                balls[i].cx -= down_x-touch_x;
                                down_x = touch_x;}
                            if(down_y<=touch_y) {
                                balls[i].cy += touch_y-down_y;
                                down_y = touch_y;
                            }else {
                                balls[i].cy -= down_y-touch_y;
                                down_y = touch_y;}

                            balls[i].color = Color.RED;

                            now_moving = true;
                        }
                    }
                }

                break;

            case MotionEvent.ACTION_UP:        // 指を離した    // (12)
                for (int i=0;i<balls.length;i++) {
                    if (balls[i].checkTouch(touch_x,touch_y)) {
                        Log.d(TAG, "onTouchEvent: Ball[" + i + "] is released");
                        balls[i].color = Color.BLUE;

                        now_moving = false;
                    }
                }
                break;

            default:
                assert true;    // 何もしない
                break;
        }*/

        invalidate();    // (13)

        return true;    // (14)
    }
}
