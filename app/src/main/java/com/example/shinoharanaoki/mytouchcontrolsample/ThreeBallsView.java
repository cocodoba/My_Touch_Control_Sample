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

    private float cx;    // 図形を描画する X 座標    // (1)
    private float cy;    // 図形を描画する Y 座標    // (2)
    private float radius;    // 円の半径    // (3)

    private Canvas canvas;
    private Paint paint;

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

        // 丸を描画する初期値を設定する
        cx = 100;
        cy = 200;
        radius = 50;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 円を描画する
        canvas.drawCircle(cx, cy, radius, paint);    // (6)
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {    // (7)
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:    // 指をタッチした    // (8)
                assert true;    // 何もしない
                break;

            case MotionEvent.ACTION_MOVE:    // 指を動かしている    // (9)
                cx = event.getX();    // (10)
                cy = event.getY();    // (11)
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
