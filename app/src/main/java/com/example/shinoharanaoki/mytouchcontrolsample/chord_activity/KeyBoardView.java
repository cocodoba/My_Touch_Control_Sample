package com.example.shinoharanaoki.mytouchcontrolsample.chord_activity;

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

import com.example.shinoharanaoki.mytouchcontrolsample.R;

/**
 * Created by shinoharanaoki on 2016/08/13.
 */
public class KeyBoardView extends View implements Runnable{
    private static final String TAG = KeyBoardView.class.getSimpleName();
    private final KeyBoardView self = this;

    public static final int OCTAVE = 11;

    public static final int OMITTED = 99;

    /**Tonic & Root Positions*/
    public static final int C       = 0;
    public static final int C_SHARP = 1;
    public static final int D_FLAT  = 1;
    public static final int D       = 2;
    public static final int D_SHARP = 3;
    public static final int E_FLAT  = 3;
    public static final int E       = 4;
    public static final int F       = 5;
    public static final int F_SHARP = 6;
    public static final int G_FLAT  = 6;
    public static final int G       = 7;
    public static final int G_SHARP = 8;
    public static final int A_FLAT  = 8;
    public static final int A       = 9;
    public static final int A_SHARP = 10;
    public static final int B_FLAT  = 10;
    public static final int B       = 11;

    /**
     * Actual Note Name indicators based on selected key or chord
     * */
    /*public static final int C = 1;
    public static final int C_NATURAL = 2;
    public static final int C_FLAT = 3;
    public static final int C_SHARP = 4;
    public static final int C_DOUBLE_FLAT = 5;
    public static final int C_DOUBLE_SHARP = 6;

    public static final int D = 7;
    public static final int D_NATURAL = 8;
    public static final int D_FLAT = 9;
    public static final int D_SHARP = 10;
    public static final int D_DOUBLE_FLAT =11;
    public static final int D_DOUBLE_SHARP = 12;

    public static final int E = 13;
    public static final int E_NATURAL = 14;
    public static final int E_FLAT = 15;
    public static final int E_SHARP = 16;
    public static final int E_DOUBLE_FLAT = 17;
    public static final int E_DOUBLE_SHARP = 18;

    public static final int F = 19;
    public static final int F_NATURAL = 20;
    public static final int F_FLAT = 21;
    public static final int F_SHARP = 22;
    public static final int F_DOUBLE_FLAT = 23;
    public static final int F_DOUBLE_SHARP = 24;

    public static final int G = 25;
    public static final int G_NATURAL = 26;
    public static final int G_FLAT = 27;
    public static final int G_SHARP = 28;
    public static final int G_DOUBLE_FLAT =29;
    public static final int G_DOUBLE_SHARP = 30;

    public static final int A = 31;
    public static final int A_NATURAL = 32;
    public static final int A_FLAT = 33;
    public static final int A_DOUBLE_FLAT = 34;
    public static final int A_SHARP = 35;
    public static final int A_DOUBLE_SHARP = 36;

    public static final int B = 37;
    public static final int B_NATURAL = 38;
    public static final int B_FLAT = 39;
    public static final int B_SHARP = 40;
    public static final int B_DOUBLE_FLAT = 41;
    public static final int B_DOUBLE_SHARP = 42;*/

    private Context context;

    private float touch_x;    // 画面のタッチされた X 座標    // (1)
    private float touch_y;    // 画面のタッチされた Y 座標    // (2)

    private final int[] alignment_pattern = {5, 7};

    private Canvas canvas;
    private Paint paint_ball;
    private Paint paint_ball_number;

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int[] sounds;

    //TEST
    public static final int POWER_CHORD = 0;
    public static final int MAJOR_TRIAD = 1;
    public static final int MINOR_TRIAD = 2;
    public static final int SUSPENDED_FOURTH = 3;
    public static final int DIMINISHED = 4;
    public static final int HALF_DIMINISHED = 5;
    public static final int AUGMENTED = 6;
    //TEST
    public static final int MAJOR_SEVENTH = 11;
    public static final int FLAT_SEVENTH = 10;
    public static final int DIMINISHED_SEVENTH = 9;

    /*テンションノート*/
    public static final int TENSION_NINETH = 14;
    public static final int TENSION_ELEVENTH = 17;
    public static final int TENSION_THIRTEENTH = 21;

    private Key[] keyboard;

    private Thread thread;
    boolean isThreadRunning = false;

    private int balloon_count = 72;

    //TEST
    private ChordTerm chordTerm1 = new ChordTerm(new Chord(D,MINOR_TRIAD, FLAT_SEVENTH, TENSION_NINETH),2000);
    private ChordTerm chordTerm2 = new ChordTerm(new Chord(G,MAJOR_TRIAD, FLAT_SEVENTH, TENSION_THIRTEENTH),2000);
    private ChordTerm chordTerm3 = new ChordTerm(new Chord(C,MAJOR_TRIAD, MAJOR_SEVENTH, TENSION_NINETH),4000);
    private ChordTerm[] chord_sequence = {chordTerm1,chordTerm2,chordTerm3};
    //Code.nowKey = Code.KEY_PITCH
    //private int[][] code_terms = {{Code.nowKey},{Code.},{}}
    //TEST
    private String[] iroha = {"は","に","ほ","へ","と","い","ろ"};
    //TEST
    private int nowKey = C;
    private int[] now_key_scale;

    public KeyBoardView(Context context){
        super(context);
        this.context = context;
        initialize();
    }

    /**レイアウトファイルの中にはめ込む場合はこのコンストラクタが必要*/
    public KeyBoardView(Context context, AttributeSet attrs){
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

        keyboard = new Key[balloon_count];

        float radius = 50;
        float init_x = 100;
        float init_y = 185;
        float upper_y = 100;
        float x_gap = 105;
        int color = Color.BLUE;
        boolean pattern_CtoE = true;
        int count_align = 0;
        int n = 0;
        String kana;

        //FIXME 生成されるBalloonインスタンスの数が配列の長さより９個くらいなぜか多くなってしまう
        float y = upper_y;
        for (int position=0;position<balloon_count;position++) {
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
            keyboard[position] = new Key(init_x, y, radius, color, position,kana);
            count_align++;

            if(pattern_CtoE && count_align==alignment_pattern[0]){
                /*下の段のパターンの最後*/
                n--;
                keyboard[position] = new Key(init_x, y, radius, color, position, iroha[n]);
                y = upper_y;
                init_x +=x_gap/2;
                pattern_CtoE = false;
                n++;
                count_align=0;
            }
            if(!pattern_CtoE && count_align==alignment_pattern[1]){
                /*下の段のパターンの最後*/
                n--;
                keyboard[position] = new Key(init_x, y, radius, color, position, iroha[n]);
                y = upper_y;
                init_x +=x_gap/2;
                pattern_CtoE = true;
                n =0;
                count_align=0;
            }
        }

        /*オブジェクトごとの絶対音を指定*/
        //TODO CからBbなどに変更できるようにoffset値を用意する
        int num = 0;
        int hight = 1;
        for (int position = 0; position< keyboard.length; position++) {
            keyboard[position].absolute_note_name = num;
            keyboard[position].octave_hight = hight;
            if (num<OCTAVE) {
                num++;
            }else if(num==OCTAVE){
                keyboard[position].absolute_note_name = num;
                keyboard[position].octave_hight = hight;
                num = 0;
                hight++;
            }
            Log.i(TAG, "initialize: baloons["+position+"] ... octave_hight= "+ keyboard[position].octave_hight + "  " +
                    "  absolute_note_name="+ keyboard[position].absolute_note_name);
        }

        now_key_scale = Scale.getScale();

        /*キーの初期値を指定してKeyBoardに反映*/
        for (int position = 0; position< keyboard.length; position++) {
            keyboard[position].position_from_tonic = Scale.getKeyPositionFromTonic(nowKey, keyboard[position].absolute_note_name);
//            keyboard[i].indicator_on_key = Scale.getRelativeNoteIndicator(Scale.B_FLAT,i);//TEST
            Log.i(TAG, "initialize: keyboard["+position+"] ...  absolute_note_name="+ keyboard[position].position_from_tonic);
        }


        for (int scale_note : now_key_scale){
            for (int position = 0; position < keyboard.length; position++) {
            if(keyboard[position].position_from_tonic == scale_note){
                    keyboard[position].is_scale_note = true;
                }
            }
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
                .setMaxStreams(8)
                .build();

        // wav をロードしておく
        sounds = setSoundPool();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 円を描画する

        for(int position = 0; position< keyboard.length; position++) {

            float ball_x = keyboard[position].cx;
            float ball_y = keyboard[position].cy;
            float radius = keyboard[position].radius;
            paint_ball.setColor(keyboard[position].color);
            canvas.drawCircle(ball_x, ball_y, radius, paint_ball);  // (6)

            //円の中に書く数字を描画する
            float num_x = keyboard[position].cx -8; //Stringは中心ではなく左端を起点に描画されるので微調整
            float num_y = keyboard[position].cy +12;
            paint_ball_number.setTextSize(40);
            if (keyboard[position].is_scale_note) {
                paint_ball_number.setColor(Color.WHITE);
                canvas.drawText(String.valueOf(keyboard[position].position_from_tonic), num_x, num_y, paint_ball_number);
            } else {
                paint_ball_number.setColor(Color.DKGRAY);
                canvas.drawText(String.valueOf(keyboard[position].position_from_tonic), num_x, num_y, paint_ball_number);
            }

            //円の中に書く数字(position from root)を描画する
            num_x = keyboard[position].cx -8; //Stringは中心ではなく左端を起点に描画されるので微調整
            num_y = keyboard[position].cy +32;
            paint_ball_number.setTextSize(20);
            paint_ball_number.setColor(Color.GREEN);
            canvas.drawText(String.valueOf(keyboard[position].position_from_root), num_x, num_y, paint_ball_number);

            //円の下に書く文字を描画する
            num_x = keyboard[position].cx -15; //Stringは中心ではなく左端を起点に描画されるので微調整
            num_y = keyboard[position].cy +100;
            paint_ball_number.setTextSize(40);
            paint_ball_number.setColor(Color.BLUE);
            canvas.drawText(keyboard[position].kana, num_x, num_y, paint_ball_number);
        }
    }

    @Override
    public void run() {
        Log.d(TAG, "run: \"Thread Start!!\"");
        int now_progress = 0;
        while (isThreadRunning) {
                    /*いったんすべてのボールの色を元に戻す*/
            for (int position = 0; position < keyboard.length; position++) {
                keyboard[position].color = Color.BLUE;
            }
            postInvalidate();

            /** コード表示1. シーケンスから次に表示する「コード+時間」を取り出す*/
            ChordTerm chord_term = chord_sequence[now_progress];
            /** コード表示2. 「コード+時間」からコードを取り出す*/
            Chord chord = chord_term.getChord();
            Log.d(TAG, "run: chord_root =" +chord.getRoot());
            for (int position = 0; position < keyboard.length; position++) {
                /**コード表示3. 現在のコードルートに応じて、キーボードにルートからのポジション番号と、文字表示用のインジケータを割り振る*/
                keyboard[position].position_from_root = chord.getPositionFromRoot(keyboard[position].absolute_note_name);
                /**コード表示4. コードにディミニッシュかオーギュメンテッドのフラグがあれば、該当する鍵盤のインジケータを変更する*/
                //keyboard[i].setflags(chord);
            }

            /** コード表示5-(A). コードからコードトーンを度数の形で出力する(キーボード全体に表示するため)*/
            int[] chord_tones = chord.getChordTriad();
            Log.d(TAG, "run: chord_intervals = " + chord_tones[0]+chord_tones[1]+chord_tones[2]);
            /** コード表示5-(B). コードからコードトーンを確定音名の形で、高さ、転回等指定したうえで出力する(実際に鳴らすため)*/
            int[] chord_sounds = chord.generateChordSounds();
            /** コード表示6. ルートからのポジション番号と、コードトーンの度数が一致するものを照合する(キーボード全体)*/
            for (int chord_tone : chord_tones) {
                for (int position = 0; position < keyboard.length; position++) {
                    if (keyboard[position].position_from_root == chord_tone) {
                        keyboard[position].color = Color.RED;
                    }
                }
            }
            /** コード表示7-(A). キーボードのポジション番号と、コードサウンド配列の数字が一致するものを照合して音を鳴らす*/
            for (int chord_sound : chord_sounds) {
                for (int position = 0; position < keyboard.length; position++) {
                    if (keyboard[position].position == chord_sound) {
                        keyboard[position].color = Color.YELLOW;
                        // play(ロードしたID, 左音量, 右音量, 優先度, ループ,再生速度)
                        soundPool.play(sounds[position], 1.0f, 1.0f, 0, 0, 1);
                    }
                }
            }

            /** コード表示7-(B). キーボードのポジション番号と、6thノートの数字が一致するものを照合して音を鳴らす*/
            if (chord.getSixth()!=OMITTED) {
                for (int position = 0; position < keyboard.length; position++) {
                    if (keyboard[position].position == chord.getSixth()) {
                        keyboard[position].color = Color.CYAN;
                        // play(ロードしたID, 左音量, 右音量, 優先度, ループ,再生速度)
                        soundPool.play(sounds[position], 1.0f, 1.0f, 0, 0, 1);
                    }
                }
            }

            /** コード表示7-(C). キーボードのポジション番号と、セブンスノートの数字が一致するものを照合して音を鳴らす*/
            if (chord.getSeventh()!=OMITTED) {
                for (int position = 0; position < keyboard.length; position++) {
                    if (keyboard[position].position == chord.getSeventh()) {
                        keyboard[position].color = Color.CYAN;
                        // play(ロードしたID, 左音量, 右音量, 優先度, ループ,再生速度)
                        soundPool.play(sounds[position], 1.0f, 1.0f, 0, 0, 1);
                    }
                }
            }

            /** コード表示7-(C). キーボードのポジション番号と、テンションノート配列の数字が一致するものを照合して音を鳴らす*/
            if (chord.getTensions()!=null) {
                for (int position = 0; position < keyboard.length; position++) {
                    for (int tension_note : chord.getTensions()) {
                        if (keyboard[position].position == tension_note) {
                            keyboard[position].color = Color.MAGENTA;
                            // play(ロードしたID, 左音量, 右音量, 優先度, ループ,再生速度)
                            soundPool.play(sounds[position], 1.0f, 1.0f, 0, 0, 1);
                        }
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
                Thread.sleep(chord_term.getTerm());
            }catch (InterruptedException e){
                Log.e(TAG, "run:InterruptedException", e );
            }

            now_progress++;
            if(now_progress ==chord_sequence.length){
                now_progress =0;
            }
            Log.d(TAG, "run: \"Thread Running!!\"");
        }
    }

    public void startThread(){
        if (!isThreadRunning) {
            thread = new Thread(this);
            isThreadRunning = true;
            thread.start();
            Log.d(TAG, "startThread: ");
        }
    }

    public void endThread(){
        if (isThreadRunning) {
            isThreadRunning = false;
            Log.d(TAG, "endThread: ");
        }
    }

    boolean now_moving = false;
    float down_x;
    float down_y;


    @Override
    public boolean onTouchEvent(MotionEvent event) {    // (7)

        touch_x = event.getX();    // (10)
        touch_y = event.getY();    // (11)

        //final int[] sounds = {soundC4,soundD4,soundE4,soundF4,soundG4};

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:    // 指をタッチした    // (8)
                for (int position = 0; position< keyboard.length; position++) {
                    if(keyboard[position].checkTouch(touch_x,touch_y)){
                        Log.d(TAG, "onTouchEvent: Key[" + position + "] is touched");
                        keyboard[position].color = Color.YELLOW;
                        soundPool.play(sounds[position], 1.0f, 1.0f, 0, 0, 1);

                        down_x = touch_x;
                        down_y = touch_y;
                        now_moving = true;

                        break;
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:    // 指を動かしている    // (9)

                if (now_moving) {
                    for (int position = 0; position< keyboard.length; position++) {
                        Log.d(TAG, "onTouchEvent: Key[" + position + "] is moving");
                        for (int index = 0; index< keyboard.length; index++) {
                            if(down_x<=touch_x) {
                                keyboard[index].cx += touch_x-down_x;
                            }else {
                                keyboard[index].cx -= down_x-touch_x;}
                        }
                        down_x = touch_x;
                        now_moving = true;
                    }
                }

                break;

            case MotionEvent.ACTION_UP:        // 指を離した    // (12)
                for (int position = 0; position< keyboard.length; position++) {
                    if (keyboard[position].checkTouch(touch_x,touch_y)) {
                        Log.d(TAG, "onTouchEvent: Ball[" + position + "] is released");
                        keyboard[position].color = Color.BLUE;

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

    public int[] setSoundPool(){

        int[] sounds = {soundPool.load(context, R.raw.c1, 1),
                soundPool.load(context, R.raw.c_sharp1, 1),
                soundPool.load(context, R.raw.d1, 1),
                soundPool.load(context, R.raw.d_sharp1, 1),
                soundPool.load(context, R.raw.e1, 1),
                soundPool.load(context, R.raw.f1, 1),
                soundPool.load(context, R.raw.f_sharp1, 1),
                soundPool.load(context, R.raw.g1, 1),
                soundPool.load(context, R.raw.g_sharp1, 1),
                soundPool.load(context, R.raw.a1, 1),
                soundPool.load(context, R.raw.a_sharp1, 1),
                soundPool.load(context, R.raw.b1, 1),
                soundPool.load(context, R.raw.c2, 1),
                soundPool.load(context, R.raw.c_sharp2, 1),
                soundPool.load(context, R.raw.d2, 1),
                soundPool.load(context, R.raw.d_sharp2, 1),
                soundPool.load(context, R.raw.e2, 1),
                soundPool.load(context, R.raw.f2, 1),
                soundPool.load(context, R.raw.f_sharp2, 1),
                soundPool.load(context, R.raw.g2, 1),
                soundPool.load(context, R.raw.g_sharp2, 1),
                soundPool.load(context, R.raw.a2, 1),
                soundPool.load(context, R.raw.a_sharp2, 1),
                soundPool.load(context, R.raw.b2, 1),
                soundPool.load(context, R.raw.c3, 1),
                soundPool.load(context, R.raw.c_sharp3, 1),
                soundPool.load(context, R.raw.d3, 1),
                soundPool.load(context, R.raw.d_sharp3, 1),
                soundPool.load(context, R.raw.e3, 1),
                soundPool.load(context, R.raw.f3, 1),
                soundPool.load(context, R.raw.f_sharp3, 1),
                soundPool.load(context, R.raw.g3, 1),
                soundPool.load(context, R.raw.g_sharp3, 1),
                soundPool.load(context, R.raw.a3, 1),
                soundPool.load(context, R.raw.a_sharp3, 1),
                soundPool.load(context, R.raw.b3, 1),
                soundPool.load(context, R.raw.c4, 1),
                soundPool.load(context, R.raw.c_sharp4, 1),
                soundPool.load(context, R.raw.d4, 1),
                soundPool.load(context, R.raw.d_sharp4, 1),
                soundPool.load(context, R.raw.e4, 1),
                soundPool.load(context, R.raw.f4, 1),
                soundPool.load(context, R.raw.f_sharp4, 1),
                soundPool.load(context, R.raw.g4, 1),
                soundPool.load(context, R.raw.g_sharp4, 1),
                soundPool.load(context, R.raw.a4, 1),
                soundPool.load(context, R.raw.a_sharp4, 1),
                soundPool.load(context, R.raw.b4, 1),
                soundPool.load(context, R.raw.c5, 1),
                soundPool.load(context, R.raw.c_sharp5, 1),
                soundPool.load(context, R.raw.d5, 1),
                soundPool.load(context, R.raw.d_sharp5, 1),
                soundPool.load(context, R.raw.e5, 1),
                soundPool.load(context, R.raw.f5, 1),
                soundPool.load(context, R.raw.f_sharp5, 1),
                soundPool.load(context, R.raw.g5, 1),
                soundPool.load(context, R.raw.g_sharp5, 1),
                soundPool.load(context, R.raw.a5, 1),
                soundPool.load(context, R.raw.a_sharp5, 1),
                soundPool.load(context, R.raw.b5, 1),
                soundPool.load(context, R.raw.c6, 1),
                soundPool.load(context, R.raw.c_sharp6, 1),
                soundPool.load(context, R.raw.d6, 1),
                soundPool.load(context, R.raw.d_sharp6, 1),
                soundPool.load(context, R.raw.e6, 1),
                soundPool.load(context, R.raw.f6, 1),
                soundPool.load(context, R.raw.f_sharp6, 1),
                soundPool.load(context, R.raw.g6, 1),
                soundPool.load(context, R.raw.g_sharp6, 1),
                soundPool.load(context, R.raw.a6, 1),
                soundPool.load(context, R.raw.a_sharp6, 1),
                soundPool.load(context, R.raw.b6, 1),
        };
        return sounds;
    }

}
