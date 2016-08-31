package com.example.shinoharanaoki.mytouchcontrolsample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.util.SparseArray;
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
    private SparseArray<PointF> mActivePointers = new SparseArray<>();


    @Override
    public boolean onTouchEvent(MotionEvent event) {    // (7)

        // get pointer index from the event object
        int pointer_index = event.getActionIndex();
        // get pointer ID
        int pointerId = event.getPointerId(pointer_index);
        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        PointF touch_point;
        PointF release_point;
        float pressure;

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
                touch_point = new PointF();
                touch_point.x = event.getX(pointer_index);
                touch_point.y = event.getY(pointer_index);
                mActivePointers.put(pointerId, touch_point);

                pressure = event.getPressure(pointer_index);

                for (int i=0;i<balls.length;i++) {
                    if(balls[i].checkTouch(touch_point)){
                        Log.d(TAG, "onTouchEvent: Ball[" + i + "] is touched");
                        balls[i].color = Color.YELLOW;

                        down_x = touch_point.x;
                        down_y = touch_point.y;
                        now_moving = true;

                        break;
                    }
                }
                Log.d(TAG, "onTouchEvent: ACTION_DOWN Touch Pressure = "+ pressure );
                break;

            case MotionEvent.ACTION_POINTER_DOWN:

                // We have a new pointer. Lets add it to the list of pointers
                touch_point = new PointF();
                touch_point.x = event.getX(pointer_index);
                touch_point.y = event.getY(pointer_index);
                mActivePointers.put(pointerId, touch_point);

                pressure = event.getPressure(pointer_index);

                for (int i=0;i<balls.length;i++) {
                    if(balls[i].checkTouch(touch_point)){
                        Log.d(TAG, "onTouchEvent: Ball[" + i + "] is touched");
                        balls[i].color = Color.YELLOW;

                        down_x = touch_point.x;
                        down_y = touch_point.y;
                        now_moving = true;

                        break;
                    }
                }
                Log.d(TAG, "onTouchEvent: ACTION_POINTER_DOWN Touch Pressure = "+ pressure );
                break;

            case MotionEvent.ACTION_POINTER_UP:

                release_point = mActivePointers.get(event.getPointerId(pointer_index));
                if (release_point != null) {
                    release_point.x = event.getX(pointer_index);
                    release_point.y = event.getY(pointer_index);

                    for (int i=0;i<balls.length;i++) {
                        if (balls[i].checkTouch(release_point)) {
                            Log.d(TAG, "onTouchEvent ACTION_POINTER_UP: Ball[" + i + "] is released");
                            balls[i].color = Color.BLUE;
                        }
                    }
                }

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:

                release_point = mActivePointers.get(event.getPointerId(pointer_index));
                if (release_point != null) {
                    release_point.x = event.getX(pointer_index);
                    release_point.y = event.getY(pointer_index);

                    for (int i=0;i<balls.length;i++) {
                        if (balls[i].checkTouch(release_point)) {
                            Log.d(TAG, "onTouchEvent ACTION_UP: Ball[" + i + "] is released");
                            balls[i].color = Color.BLUE;
                            now_moving = false;
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:

                for (int size = event.getPointerCount(), i = 0; i < size; i++) {

                    PointF point = mActivePointers.get(event.getPointerId(i));
                    float x_moving = event.getX(i);
                    float y_moving = event.getY(i);

                    if (now_moving) {
                        for (int j=0;j<balls.length;j++) {
                            if(balls[j].checkTouch(point)){
                                Log.d(TAG, "onTouchEvent: Ball[" + j + "] is moving");
                                if(point.x<=x_moving) {
                                    balls[j].cx += x_moving-point.x;
                                    point.x = x_moving;
                                }else {
                                    balls[j].cx -= point.x-x_moving;
                                    point.x = x_moving;}
                                if(point.y<=y_moving) {
                                    balls[j].cy += y_moving-point.y;
                                    point.y = y_moving;
                                }else {
                                    balls[j].cy -= point.y-y_moving;
                                    point.y = y_moving;}

                                balls[j].color = Color.RED;

                                now_moving = true;
                            }
                        }
                    }
                }
                break;
        }

        invalidate();    // (13)

        return true;    // (14)
    }
}
