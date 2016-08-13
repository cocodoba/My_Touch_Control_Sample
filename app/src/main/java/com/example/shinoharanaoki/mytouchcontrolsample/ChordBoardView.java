package com.example.shinoharanaoki.mytouchcontrolsample;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by shinoharanaoki on 2016/08/13.
 */
public class ChordBoardView extends View{
    private static final String TAG = ChordBoardView.class.getSimpleName();
    private final ChordBoardView self = this;

    private Context context;

    private float touch_x;    // 画面のタッチされた X 座標    // (1)
    private float touch_y;    // 画面のタッチされた Y 座標    // (2)

    private Canvas canvas;
    private Paint paint_ball;
    private Paint paint_ball_number;

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundC4;
    private int soundD4;
    private int soundE4;
    private int soundF4;
    private int soundG4;

    private Ball[] balls;

    private Thread thread;
    boolean isThreadRunning = false;

    private  int number_of_balls = 5;

    private int[][] selects = {{1,3},{2,4},{4,5,1}};
    private int[] intervals = {800,600,1800};

    public ChordBoardView(Context context){
        super(context);
        this.context = context;
        initialize();
    }

    /**レイアウトファイルの中にはめ込む場合はこのコンストラクタが必要*/
    public ChordBoardView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initialize() {

        // ペイントオブジェクトを設定する
        paint_ball = new Paint();
        paint_ball.setAntiAlias(true);
        paint_ball.setColor(Color.BLUE);    // (4)
        paint_ball.setStyle(Paint.Style.FILL);    // (5)

        paint_ball_number = new Paint();
        paint_ball_number.setAntiAlias(true);
        paint_ball_number.setStyle(Paint.Style.FILL);    // (5)

        balls = new Ball[number_of_balls];
        float radius = 65;
        float init_x = 80;
        float init_y = 400;
        int color = Color.BLUE;
        for (int i=0;i<number_of_balls;i++) {
            balls[i] = new Ball(init_x, init_y, radius, color);
            init_x += 140;
        }

        /**プレビュー画面でのエラー回避*/
        if (isInEditMode()) {
            // 編集モードだったら処理終了
            return ;
        }

        audioAttributes = new AudioAttributes.Builder()
                // USAGE_MEDIA
                // USAGE_GAME
                .setUsage(AudioAttributes.USAGE_GAME)
                // CONTENT_TYPE_MUSIC
                // CONTENT_TYPE_SPEECH, etc.
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                // ストリーム数に応じて(同時に鳴らせる音の数)
                .setMaxStreams(5)
                .build();

        // wav をロードしておく
        soundC4 = soundPool.load(context, R.raw.c4, 1);
        soundD4 = soundPool.load(context, R.raw.d4, 1);
        soundE4 = soundPool.load(context, R.raw.e4, 1);
        soundF4 = soundPool.load(context, R.raw.f4, 1);
        soundG4 = soundPool.load(context, R.raw.g4, 1);

        final int[] sounds = {soundC4,soundD4,soundE4,soundF4,soundG4};

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: \"Thread Start!!\"");
                int term = 0;
                while (isThreadRunning) {
                    /*いったんすべてのボールの色を元に戻す*/
                    for (int i = 0; i < balls.length; i++) {
                        balls[i].color = Color.BLUE;
                    }
                    postInvalidate();

                    for (int num : selects[term]) {
                        for (int i = 0; i < balls.length; i++) {
                            if (balls[i].ball_number == num) {
                                balls[i].color = Color.RED;
                                // play(ロードしたID, 左音量, 右音量, 優先度, ループ,再生速度)
                                soundPool.play(sounds[i], 1.0f, 1.0f, 0, 0, 1);
                            }
                        }
                    }
                    /**画面を更新する
                     *
                     * UIスレッドではないほかのスレッドから画面を更新させたい場合は、
                     * invalidate()ではなく、postInvalidate()を利用する
                     * */
                    postInvalidate();

                    /*次の更新までの間隔を設ける*/
                    try {
                        Thread.sleep(intervals[term]);
                    }catch (InterruptedException e){
                        Log.e(TAG, "run:InterruptedException", e );
                    }

                    term++;
                    if(term ==selects.length){
                        term =0;
                    }
                    Log.d(TAG, "run: \"Thread Running!!\"");
                }
            }
        });
        isThreadRunning = true;
        thread.start();
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

        touch_x = event.getX();    // (10)
        touch_y = event.getY();    // (11)

        final int[] sounds = {soundC4,soundD4,soundE4,soundF4,soundG4};

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:    // 指をタッチした    // (8)
                for (int i=0;i<balls.length;i++) {
                    if(balls[i].checkTouch(touch_x,touch_y)){
                        Log.d(TAG, "onTouchEvent: Ball[" + i + "] is touched");
                        balls[i].color = Color.YELLOW;
                        soundPool.play(sounds[i], 1.0f, 1.0f, 0, 0, 1);

                        down_x = touch_x;
                        down_y = touch_y;
                        now_moving = true;

                        break;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:    // 指を動かしている    // (9)

                assert true;    // 何もしない

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
        }

        /**画面を更新する
         *
         * View # invalidate()は，メインスレッドがアイドル状態になり次第、
         * ビューがonDraw()経由で再描画されるように，システムにお願いする
         * */
        invalidate();    // (13)

        return true;    // (14)
    }

}
