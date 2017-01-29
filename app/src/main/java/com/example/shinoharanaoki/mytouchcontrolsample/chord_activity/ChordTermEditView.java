package com.example.shinoharanaoki.mytouchcontrolsample.chord_activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by shinoharanaoki on 2016/09/01.
 */
public class ChordTermEditView extends View{
    private static final String TAG = ChordTermEditView.class.getSimpleName();
    private final ChordTermEditView self = this;

    private Context context;

    private Canvas canvas;
    private Paint paint_chord_block;
    private Paint paint_chord_root;
    private Paint paint_chord_symbol;

    float int_left = 20;
    float int_bottom = 60;
    float width = 140;
    float height = 50;
    float radius = 10;

    public ArrayList<ChordTerm> chordTerms_arraylist;

    public ChordTermEditView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        initialize();
    }

    private void initialize(){

        paint_chord_block = new Paint();
        paint_chord_block.setAntiAlias(true);
        paint_chord_block.setColor(Color.BLUE);    // (4)
        paint_chord_block.setStyle(Paint.Style.FILL);

        paint_chord_root = new Paint();
        paint_chord_root.setAntiAlias(true);
        paint_chord_root.setColor(Color.BLUE);    // (4)
        paint_chord_root.setStyle(Paint.Style.FILL);

        paint_chord_symbol = new Paint();
        paint_chord_symbol.setAntiAlias(true);
        paint_chord_symbol.setColor(Color.BLUE);    // (4)
        paint_chord_symbol.setStyle(Paint.Style.FILL);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas){

        float space;
        float total_move_x = 0;

        if (!chordTerms_arraylist.isEmpty()) {
            for (ChordTerm chord_term : chordTerms_arraylist) {

                float left = int_left + total_move_x;
                paint_chord_block.setColor(chord_term.color);
                canvas.drawRoundRect(left, int_bottom-height, left+width, int_bottom, radius, radius, paint_chord_block);

                paint_chord_root.setColor(Color.WHITE);
                paint_chord_root.setTextSize(30);
                String root_string = chord_term.getChord().getRootString();
                canvas.drawText(root_string,left+width/8,int_bottom-3,paint_chord_root); //FIXME 文字サイズ、位置調整

                paint_chord_symbol.setColor(Color.WHITE);
                paint_chord_symbol.setTextSize(15);
                String symbol_string = chord_term.getChord().getChordSymbolString();
                canvas.drawText(symbol_string,left+(width/8)+20,int_bottom-3,paint_chord_root); //FIXME 文字サイズ、位置調整

                space = chord_term.getTerm()/30;//TODO
                total_move_x += width + space;
            }
        }

    }

    public void setChordTermsList(ArrayList<ChordTerm> chord_list){
        chordTerms_arraylist = chord_list;
        invalidate();
    }

    boolean now_moving = false;
    float down_x;
    float down_y;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touch_x = event.getX();
        float touch_y = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:    // 指をタッチした
                /*for (int position = 0; position< chordTerms_arraylist.length; position++) {
                    if(chordTerms_arraylist[position].checkTouch(touch_x,touch_y)){
                        Log.d(TAG, "onTouchEvent: Kenban[" + position + "] is touched");
                        chordTerms_arraylist[position].color = Color.YELLOW;
                        soundPool.play(sounds[position], 1.0f, 1.0f, 0, 0, 1);
                        break;
                    }
                }*/
                down_x = touch_x;
                down_y = touch_y;
                now_moving = true;

                break;

            case MotionEvent.ACTION_MOVE:    // 指を動かしている

                if (now_moving) {
                    /*for (ChordTerm chordterm:chordTerms_arraylist) {
                    }*/
                    if(down_x<=touch_x) {
                        int_left += touch_x-down_x;
                    }else {
                        int_left -= down_x-touch_x;}
                    down_x = touch_x;
                    now_moving = true;
                    /*for (int position = 0; position< keyboard.length; position++) {
                        Log.d(TAG, "onTouchEvent: Kenban[" + position + "] is moving");
                    }*/
                }

                break;

            case MotionEvent.ACTION_UP:        // 指を離した    // (12)
                /*for (int position = 0; position< keyboard.length; position++) {
                    if (keyboard[position].checkTouch(touch_x,touch_y)) {
                        Log.d(TAG, "onTouchEvent: Ball[" + position + "] is released");
                        keyboard[position].color = Color.BLUE;

                    }
                }*/
                now_moving = false;
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
