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
public class LoopingBalloonsView extends View {
    private static final String TAG = LoopingBalloonsView.class.getSimpleName();
    private final LoopingBalloonsView self = this;

    private float touch_x;    // 画面のタッチされた X 座標    // (1)
    private float touch_y;    // 画面のタッチされた Y 座標    // (2)

    private Canvas canvas;
    private Paint paint_ball;
    private Paint paint_ball_number;

    private Balloon[] balloons;
    private int balloon_count = 50;

    private String[] iroha = {"は","に","ほ","へ","と","い","ろ"};
    private int[] alignment_pattern = {5, 7};

    public LoopingBalloonsView(Context context) {
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

        balloons = new Balloon[balloon_count];

        /**C1,D1,E1 ... と順番に生成する(後で使う)*/
        /*char alphabet = 'C';
        int hight = 2;
        for (int i=0;i<balloon_count;i++) {
            mIDs[i] = ""+ alphabet + hight;
            alphabet++;
            if(alphabet=='C'){
                hight++;
            }
            if(alphabet=='H'){
                alphabet='A';
            }
            Log.i(TAG, "initialize: mIDs = "+mIDs[i]);
        }*/

        float radius = 50;
        float init_x = 100;
        float init_y = 435;
        float upper_y = 350;
        float x_gap = 105;
        int color = Color.BLUE;
        boolean pattern_CtoE = true;
        int count_align = 0;
        int n = 0;
        String kana;

        //FIXME 生成されるBalloonインスタンスの数が配列の長さより９個くらいなぜか多くなってしまう
        float y = upper_y;
        for (int i=0;i<balloon_count;i++) {
            /**上下交互に並べる*/
            if (y != init_y) { /*下の段*/
                y = init_y;
                kana = iroha[n];
                n++;
            } else { /*上の段*/
                y = upper_y;
                kana = "";
            }
            init_x += x_gap/2; //間隔
            balloons[i] = new Balloon(init_x, y, radius, color, kana);
            count_align++;

            if(pattern_CtoE && count_align==alignment_pattern[0]){
                /*下の段のパターンの最後*/
                n--;
                balloons[i] = new Balloon(init_x, y, radius, color, iroha[n]);
                y = upper_y;
                init_x +=x_gap/2;
                pattern_CtoE = false;
                n++;
                count_align=0;
            }
            if(!pattern_CtoE && count_align==alignment_pattern[1]){
                /*下の段のパターンの最後*/
                n--;
                balloons[i] = new Balloon(init_x, y, radius, color, iroha[n]);
                y = upper_y;
                init_x +=x_gap/2;
                pattern_CtoE = true;
                n =0;
                count_align=0;
            }
        }

        int num = 1;
        int hight = 1;
        for (int i=0;i<balloons.length;i++) {
            balloons[i].twelve_num = num;
            balloons[i].hight = hight;
            if (num<12) {
                num++;
            }else if(num==12){
                balloons[i].twelve_num = num;
                balloons[i].hight = hight;
                num = 1;
                hight++;
            }
            Log.i(TAG, "initialize: baloons["+i+"] ... hight= "+balloons[i].hight + "  " +
                    "  twelve_num="+ balloons[i].twelve_num);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 円を描画する

        for(int i=0;i<balloons.length;i++) {

            float ball_x = balloons[i].cx;
            float ball_y = balloons[i].cy;
            float radius = balloons[i].radius;
            paint_ball.setColor(balloons[i].color);
            canvas.drawCircle(ball_x, ball_y, radius, paint_ball);  // (6)

            //円の中に書く数字を描画する
            float num_x = balloons[i].cx -8; //Stringは中心ではなく左端を起点に描画されるので微調整
            float num_y = balloons[i].cy +12;
            paint_ball_number.setTextSize(40);
            paint_ball_number.setColor(Color.WHITE);
            canvas.drawText(String.valueOf(balloons[i].twelve_num), num_x, num_y, paint_ball_number);

            //円の中に書く文字を描画する
            num_x = balloons[i].cx -15; //Stringは中心ではなく左端を起点に描画されるので微調整
            num_y = balloons[i].cy +100;
            paint_ball_number.setTextSize(40);
            paint_ball_number.setColor(Color.BLUE);
            canvas.drawText(balloons[i].kana, num_x, num_y, paint_ball_number);
        }
    }

    boolean now_moving = false;
    float down_x;
    float down_y;


    @Override
    public boolean onTouchEvent(MotionEvent event) {    // (7)

        touch_x = event.getX();    // (10)
        touch_y = event.getY();    // (11)

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:    // 指をタッチした    // (8)
                for (int i=0;i<balloons.length;i++) {
                    if(balloons[i].checkTouch(touch_x,touch_y)){
                        Log.d(TAG, "onTouchEvent: Balloon[" + i + "] is touched");
                        balloons[i].color = Color.YELLOW;

                        down_x = touch_x;
                        down_y = touch_y;
                        now_moving = true;

                        break;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:    // 指を動かしている    // (9)

                if (now_moving) {
                    for (int i=0;i<balloons.length;i++) {
                        Log.d(TAG, "onTouchEvent: Balloon[" + i + "] is moving");
                        for (int index=0;index<balloons.length;index++) {
                            if(down_x<=touch_x) {
                                balloons[index].cx += touch_x-down_x;
                            }else {
                                balloons[index].cx -= down_x-touch_x;}
                        }
                        down_x = touch_x;
                        now_moving = true;
                    }
                }

                break;

            case MotionEvent.ACTION_UP:        // 指を離した    // (12)
                for (int i=0;i<balloons.length;i++) {
                    balloons[i].color = Color.BLUE;
                    if (balloons[i].checkTouch(touch_x,touch_y)) {
                        Log.d(TAG, "onTouchEvent: Balloon[" + i + "] is released");
                        now_moving = false;
                    }
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
