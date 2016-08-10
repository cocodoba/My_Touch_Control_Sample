package com.example.shinoharanaoki.mytouchcontrolsample;

/**
 * Created by shinoharanaoki on 2016/08/10.
 */
public class Ball {
    private static final String TAG = Ball.class.getSimpleName();
    private final Ball self = this;

    public float cx;    // 図形を描画する X 座標    // (1)
    public float cy;    // 図形を描画する Y 座標    // (2)

    public float radius;    // 円の半径    // (3)

    public Ball(float x, float y, float r){
        cx = x;
        cy = y;
        radius = r;
    }



}
