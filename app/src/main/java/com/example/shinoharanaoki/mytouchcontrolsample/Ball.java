package com.example.shinoharanaoki.mytouchcontrolsample;

import android.graphics.Color;

/**
 * Created by shinoharanaoki on 2016/08/10.
 */
public class Ball {
    private static final String TAG = Ball.class.getSimpleName();
    private final Ball self = this;

    public float cx;    // 図形を描画する X 座標    // (1)
    public float cy;    // 図形を描画する Y 座標    // (2)

    public float radius;    // 円の半径    // (3)

    public int color;

    public Ball(float x, float y, float r, int color){
        cx = x;
        cy = y;
        radius = r;
        this.color = color;
    }

    public boolean checkTouch(float x, float y){
        if((x<cx+radius&&x>cx-radius)&&(y<cy+radius&&y>cy-radius)){
            return true;
        }else{return false;}
    }

}
