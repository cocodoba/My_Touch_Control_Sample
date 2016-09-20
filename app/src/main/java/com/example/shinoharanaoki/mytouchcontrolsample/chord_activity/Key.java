package com.example.shinoharanaoki.mytouchcontrolsample.chord_activity;

import android.graphics.PointF;
import android.util.Log;

/**
 * Created by shinoharanaoki on 2016/08/13.
 */
public class Key {
    private static final String TAG = Key.class.getSimpleName();
    private final Key self = this;

    /**
     * Actual Note Names
     * */
    public static final int C = 1;
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
    public static final int B_DOUBLE_SHARP = 42;

    public int position;

    /**このオブジェクトと紐付けられた音名(不変)*/
    public int absolute_note_name; //FIXME finalにしたい  Note.C、 Note.G_FLAT_F_SHARP などが入る
    public int octave_hight; //FIXME finalにしたい

    /*現在のキーに対応する*/
    public int position_from_tonic; //Scale._TONIC, Scale.SUPERTONIC などが入る
    public int indicator_on_key;
    public String degree_name_on_key;
    public boolean is_scale_note = false;
    /*現在のコードTermによって変化する*/
    public int position_from_root;
    public int chord_interval; // Chord._ROOT, Chord._FLAT_SECOND などが入る
    public int chord_scale_note_name;
    public String degree_name_on_chord;
    public boolean is_triad_note;
    public boolean is_tension_note;
    public boolean is_avoid_note;

    public boolean is_chord_scale_note;

    public float cx;    // 図形を描画する X 座標    // (1)
    public float cy;    // 図形を描画する Y 座標    // (2)
    public float radius;    // 角の丸み    // (3)
    public int color;
    public String note_name;

    public static int index = 1;

    public Key(float x, float y, float r, int color, int pos, String string){
        cx = x;
        cy = y;
        radius = r;
        this.color = color;
        note_name = string;
        position = pos;

        Log.d(TAG, "Balloon: " + index + "個目");
        index++;
    }

    public boolean checkTouch(float x, float y){
        if((x<cx+radius&&x>cx-radius)&&(y<cy+radius&&y>cy-radius)){
            return true;
        }else{return false;}
    }

    public boolean checkTouch(PointF point){

        float x = point.x;
        float y = point.y;

        if((x<cx+radius&&x>cx-radius)&&(y<cy+radius&&y>cy-radius)){
            return true;
        }else{return false;}
    }

    public void doubleFlattenActualNoteName(){
        switch (indicator_on_key){
            case C|D|E|F|G|A:
                indicator_on_key = chord_scale_note_name + 10;
                break;

            case B:
                indicator_on_key = chord_scale_note_name + 1;
                break;

            //TODO...続き

        }
    }
}
