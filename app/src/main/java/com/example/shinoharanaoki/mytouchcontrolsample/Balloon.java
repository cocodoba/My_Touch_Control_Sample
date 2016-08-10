package com.example.shinoharanaoki.mytouchcontrolsample;

/**
 * Created by shinoharanaoki on 2016/08/10.
 */
public class Balloon {
    private static final String TAG = Balloon.class.getSimpleName();
    private final Balloon self = this;

    public float cx;    // 図形を描画する X 座標    // (1)
    public float cy;    // 図形を描画する Y 座標    // (2)

    public float radius;    // 角の丸み    // (3)

    public int color;

    public Balloon(float x, float y, float r, int color){
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
