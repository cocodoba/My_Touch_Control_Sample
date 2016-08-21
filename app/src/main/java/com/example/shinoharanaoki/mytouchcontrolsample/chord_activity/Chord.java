package com.example.shinoharanaoki.mytouchcontrolsample.chord_activity;

/**
 * Created by shinoharanaoki on 2016/08/13.
 */
public class Chord {
    private static final String TAG = Chord.class.getSimpleName();
    private final Chord self = this;

    public static final int OMITTED = 99;

    /**Tonic Positions*/
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

    /*度数(コードスケールポジションに対応する)*/
    public static final int _I         = 0;
    public static final int _II_FLAT = 1;
    public static final int _II       = 2;
    public static final int _III_FLAT    = 3;
    public static final int _III          = 4;
    public static final int _IV      = 5;
    public static final int _V_FLAT   = 6;
    public static final int _V         = 7;
    public static final int _VI_FLAT = 8;
    public static final int _VI       = 9;
    public static final int _VII_FLAT   = 10;
    public static final int _VII         = 11;

    /*トライアド種(メジャー・マイナー(m3rd)・ディミニッシュ(m3rd,m5th)・オーギュメント(aug5th)など)(コード作成画面と共通)*/
    public static final int POWER_CHORD = 0;
    public static final int MAJOR_TRIAD = 1;
    public static final int MINOR_TRIAD = 2;
    public static final int SUSPENDED_FOURTH = 3;
    public static final int DIMINISHED = 4;
    public static final int AUGMENTED = 6;

    /*6thノート*/
    public static final int SIXTH = 9;

    /*セブンス(コード作成画面と共通)*/
    public static final int MAJOR_SEVENTH = 11;
    public static final int FLAT_SEVENTH = 10;
    public static final int DIMINISHED_SEVENTH = 9;

    /*テンションノート*/
    public static final int TENSION_NINETH = 14;
    public static final int TENSION_ELEVENTH = 17;
    public static final int TENSION_THIRTEENTH = 21;

    /**最終的に出来たコードトーンの度数の配列*/
    //TODO ArrayListにする
    private int[] chord_triad;
    private int sixth = OMITTED;
    private int seventh = OMITTED;
    private int[] tensions;
    /*最終的に出来たコードのルート*/
    private int root;
    /*ダブルフラットやダブルシャープの表示に対応するためのフラグ*/
    public boolean flag_diminished;
    public boolean flag_augumented;


    //パラメータ ... ルート、テンション、高さ、転回、オミット
    public Chord(int root, int triad){

        this.root = root;
        chord_triad = generateRelativeChordIntervals(triad);
    }

    public Chord(int root, int triad, int tension){

        this.root = root;
        chord_triad = generateRelativeChordIntervals(triad);

        tensions= new int[3];

        switch (tension){
            case TENSION_NINETH:
                seventh = root+MAJOR_SEVENTH+36;
                tensions[0]=root+TENSION_NINETH+36;
                tensions[1]=OMITTED;
                tensions[2]=OMITTED;
                break;
            case TENSION_ELEVENTH:
                seventh = root+MAJOR_SEVENTH+36;
                tensions[0]=root+TENSION_NINETH+36;
                tensions[1]=root+TENSION_ELEVENTH+36;
                tensions[2]=OMITTED;
                break;
            case TENSION_THIRTEENTH:
                seventh = root+MAJOR_SEVENTH+36;
                tensions[0]=root+TENSION_NINETH+36;
                tensions[1]=root+TENSION_ELEVENTH+36;
                tensions[2]=root+TENSION_THIRTEENTH+36;
                break;

        }

    }


    public Chord(int root, int triad, int seventh, int tension){

        this.root = root;
        chord_triad = generateRelativeChordIntervals(triad);

        tensions= new int[3];

        switch (seventh){
            case MAJOR_SEVENTH:
                this.seventh = root+MAJOR_SEVENTH+36;
                break;
            case FLAT_SEVENTH:
                this.seventh = root+FLAT_SEVENTH+36;
                break;
            case DIMINISHED_SEVENTH:
                this.seventh = root+MAJOR_SEVENTH+36;
                break;

        }

        switch (tension){
            case TENSION_NINETH:
                tensions[0]=root+TENSION_NINETH+36;
                tensions[1]=OMITTED;
                tensions[2]=OMITTED;
                break;
            case TENSION_ELEVENTH:
                tensions[0]=root+TENSION_NINETH+36;
                tensions[1]=root+TENSION_ELEVENTH+36;
                tensions[2]=OMITTED;
                break;
            case TENSION_THIRTEENTH:
                tensions[0]=root+TENSION_NINETH+36;
                tensions[1]=root+TENSION_ELEVENTH+36;
                tensions[2]=root+TENSION_THIRTEENTH+36;
                break;

        }
    }


    public int[] generateRelativeChordIntervals(int triad_type){

        //TEST
        int[] chord_notes = new int[4];

        switch (triad_type){

            case MAJOR_TRIAD:
                chord_notes[1] = 4;
                chord_notes[2] = 7;
                break;
            case MINOR_TRIAD:
                chord_notes[1] = 3;
                chord_notes[2] = 7;
                break;
            case SUSPENDED_FOURTH:
                chord_notes[1] = 5;
                chord_notes[2] = 7;
                break;
            case DIMINISHED:
                chord_notes[1] = 3;
                chord_notes[2] = 6;
                flag_diminished = true;
                break;
            case AUGMENTED:
                chord_notes[1] = 4;
                chord_notes[2] = 8;
                flag_augumented = true;
                break;
            case POWER_CHORD:
                chord_notes[1] = OMITTED;
                chord_notes[2] = 7;
                break;
        }

        return chord_notes;

    }

    public int[] generateChordSounds(){

        int[] chord_sounds = new int[chord_triad.length];
        for (int i = 0; i < chord_triad.length ; i++) {
            chord_sounds[i] = root + chord_triad[i] + 36; //+ offset
        }
        return chord_sounds;
    }

    public int getPositionFromRoot(int absolute_note){

        int position = absolute_note - root;
        if(position<0){
            position += 12;
        }
        return position;
    }

    public int getRoot(){
        return root;
    }

    public int[] getChordTriad(){
        return chord_triad;
    }

    public int getSixth(){
        return sixth;
    }

    public int getSeventh(){
        return seventh;
    }

    public int[] getTensions(){
        return tensions;
    }

}
