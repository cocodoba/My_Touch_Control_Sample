package com.example.shinoharanaoki.mytouchcontrolsample.chord_activity;

/**
 * Created by shinoharanaoki on 2016/08/13.
 */
public class Scale {
    private static final String TAG = Scale.class.getSimpleName();
    private final Scale self = this;

    public static final int OCTAVE = 11;

    /**
     * Actual Note Names Indicator
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

    /*キーの種類(Major, Minor)*/
    //メジャー系15種
    public static final int C_MAJOR = 0;
    public static final int C_SHARP_MAJOR = 1;
    public static final int D_FLAT_MAJOR= 2;
    public static final int D_MAJOR= 3;
    public static final int E_FLAT_MAJOR= 4;
    public static final int E_MAJOR= 5;
    public static final int F_MAJOR= 6;
    public static final int F_SHARP_MAJOR= 7;
    public static final int G_FLAT_MAJOR= 8;
    public static final int G_MAJOR= 9;
    public static final int A_FLAT_MAJOR= 10;
    public static final int A_MAJOR= 11;
    public static final int B_FLAT_MAJOR= 12;
    public static final int B_MAJOR= 13;
    public static final int C_FLAT_MAJOR= 14;
    //マイナー系15種
    public static final int A_MINOR= 15;
    public static final int A_SHARP_MINOR= 16;
    public static final int B_MINOR= 17;
    public static final int B_FLAT_MINOR= 18;
    public static final int C_MINOR= 19;
    public static final int C_SHARP_MINOR= 20;
    public static final int D_MINOR= 21;
    public static final int D_SHARP_MINOR= 22;
    public static final int E_MINOR= 23;
    public static final int E_FLAT_MINOR= 24;
    public static final int F_MINOR= 25;
    public static final int F_SHARP_MINOR= 26;
    public static final int G_MINOR= 27;
    public static final int G_SHARP_MINOR= 28;
    public static final int A_FLAT_MINOR= 29;


    public static final int _TONIC            = 0; //Do
    public static final int _SUPERTONIC_MINOR = 1;
    public static final int _SUPERTONIC       = 2; //Re
    public static final int _MEDIANT_MINOR    = 3;
    public static final int _MEDIANT          = 4; //Mi
    public static final int _SUBDOMINANT      = 5; //Fa
    public static final int _DOMINANT_MINOR   = 6;
    public static final int _DOMINANT         = 7; //Sol
    public static final int _SUBMEDIANT_MINOR = 8;
    public static final int _SUBMEDIANT      = 9; //La
    public static final int _SUBTONIC_MINOR  = 10;
    public static final int _SUBTONIC        = 11; //Si

    public static final int[] IONIAN_SCALE = {_TONIC,_SUPERTONIC_MINOR,_SUPERTONIC,_MEDIANT_MINOR,_MEDIANT,
            _SUBDOMINANT,_DOMINANT_MINOR,_DOMINANT,_SUBMEDIANT_MINOR,_SUBMEDIANT,_SUBTONIC_MINOR,_SUBTONIC};

    public static final boolean[] DIATONIC_SCALE = {true,false,true,false,true,true,false,true,false,true,false,true};
    public static final boolean[] PENTATONIC_SCALE = {true,false,true,false,true,false,false,true,false,true,false,false};
    public static final boolean[] BLUCE_SCALE = {};
    public static final boolean[] RYUKYU_SCALE = {};

    /*キーを選択した場合に各キーボードに保存する*/
    public static int[] F_MAJOR_D_MINOR = {F,G_FLAT,G,A_FLAT,A,B_FLAT,B_NATURAL,C,D_FLAT,D,E_FLAT,E};
    public static int[] B_FLAT_MAJOR_G_MINOR = {B_FLAT,B_NATURAL,C,D_FLAT,D,E_FLAT,E_NATURAL,F,G_FLAT,G,A_FLAT,A};
    public static int[] E_FLAT_MAJOR_C_MINOR = {E_FLAT,E_NATURAL,F,G_FLAT,G,A_FLAT,A_NATURAL,B_FLAT,B_NATURAL,C,D_FLAT,D};
    public static int[] A_FLAT_MAJOR_F_MINOR = {A_FLAT,A_NATURAL,B_FLAT,B_NATURAL,C,D_FLAT,D_NATURAL,E_FLAT,E_NATURAL,F,G_FLAT,G};
    public static int[] D_FLAT_MAJOR_B_FLAT_MINOR = {D_FLAT,D_NATURAL,E_FLAT,E_NATURAL,F,G_FLAT,G_NATURAL,A_FLAT,A,B_FLAT,B_NATURAL,C};
    public static int[] G_FLAT_MAJOR_E_FLAT_MINOR = {G_FLAT,G_NATURAL,A_FLAT,A,B_FLAT,C_FLAT,C_NATURAL,D_FLAT,D,E_FLAT,E_NATURAL,F};
    public static int[] C_FLAT_MAJOR_A_FLAT_MINOR = {C_FLAT,C_NATURAL,D_FLAT,D,E_FLAT,F_FLAT,F_NATURAL,G_FLAT,G,A_FLAT,A,B_FLAT};

    public static int[] C_MAJOR_A_MINOR = {C,C_SHARP,D,D_SHARP,E,F,F_SHARP,G,G_SHARP,A,A_SHARP,B};
    public static int[] G_MAJOR_E_MINOR = {G,G_SHARP,A,A_SHARP,B,C,C_SHARP,D,D_SHARP,E,F_NATURAL,F_SHARP};
    public static int[] D_MAJOR_B_MINOR = {D,D_SHARP,E,F_NATURAL,F_SHARP,G,G_SHARP,A,A_SHARP,B,C_NATURAL,C_SHARP};
    public static int[] A_MAJOR_F_SHARP_MINOR = {A,A_SHARP,B,C_NATURAL,C_SHARP,D,D_SHARP,E,F_NATURAL,F_SHARP,G_NATURAL,G_SHARP};
    public static int[] E_MAJOR_C_SHARP_MINOR = {E,F_NATURAL,F_SHARP,G_NATURAL,G_SHARP,A,A_SHARP,B,C_NATURAL,C_SHARP,D_NATURAL,D_SHARP};
    public static int[] B_MAJOR_G_SHARP_MINOR = {B,C_NATURAL,C_SHARP,D_NATURAL,D_SHARP,E,F_NATURAL,F_SHARP,G_NATURAL,G_SHARP,A_NATURAL,A_SHARP};
    //FIXME
    public static int[] F_SHARP_MAJOR_D_SHARP_MINOR = {F_SHARP,G,G_SHARP,A,A_SHARP,B,C,C_SHARP,D,D_SHARP,E,F};
    public static int[] C_SHARP_MAJOR_A_SHARP_MINOR = {C_SHARP,D,D_SHARP,E,F,F_SHARP,G,G_SHARP,A,A_SHARP,B,C,};

    public static int getKeyPositionFromTonic(int tonic, int note){

        int scale_degree = IONIAN_SCALE[note] - tonic;
        if(scale_degree<0){
            scale_degree += OCTAVE +1;
        }
        return scale_degree;
    }

    public static int[] getScale(){ //TODO パラメータ: major.minor...
        int[] major_scale = {0,2,4,5,7,9,11};
        return major_scale;
    }

    public static int getActualNoteIndicator(int key, int position_from_tonic){

        //TODO
        switch (key){

            case C:
                return C_MAJOR_A_MINOR[position_from_tonic];

            case B_FLAT_MAJOR:
                return B_FLAT_MAJOR_G_MINOR[position_from_tonic];

            default:
                return C_MAJOR_A_MINOR[position_from_tonic];

        }

    }


    public static String NoteIndicatorToString(int indicator){

        switch (indicator){

            case C:
                return "C";
            case C_NATURAL:
                return "C♮";
            case C_FLAT:
                return "C♭";
            case C_SHARP:
                return "C♯";
            case C_DOUBLE_FLAT:
                return "C♭♭";
            case C_DOUBLE_SHARP:
                return "C♯♯";
            case D:
                return "D";
            case D_NATURAL:
                return "D♮";
            case D_FLAT:
                return "D♭";
            case D_SHARP:
                return "D♯";
            case D_DOUBLE_FLAT:
                return "D♭♭";
            case D_DOUBLE_SHARP:
                return "D♯♯";
            case E:
                return "E";
            case E_NATURAL:
                return "E♮";
            case E_FLAT:
                return "E♭";
            case E_SHARP:
                return "E♯";
            case E_DOUBLE_FLAT:
                return "E♭♭";
            case E_DOUBLE_SHARP:
                return "E♯♯";
            case F:
                return "F";
            case F_NATURAL:
                return "F♮";
            case F_FLAT:
                return "F♭";
            case F_SHARP:
                return "F♯";
            case F_DOUBLE_FLAT:
                return "F♭♭";
            case F_DOUBLE_SHARP:
                return "F♯♯";
            case G:
                return "G";
            case G_NATURAL:
                return "G♮";
            case G_FLAT:
                return "G♭";
            case G_SHARP:
                return "G♯";
            case G_DOUBLE_FLAT:
                return "G♭♭";
            case G_DOUBLE_SHARP:
                return "G♯♯";
            case A:
                return "A";
            case A_NATURAL:
                return "A♮";
            case A_FLAT:
                return "A♭";
            case A_DOUBLE_FLAT:
                return "A♭♭";
            case A_SHARP:
                return "A♯";
            case A_DOUBLE_SHARP:
                return "A♯♯";
            case B:
                return "B";
            case B_NATURAL:
                return "B♮";
            case B_FLAT:
                return "B♭";
            case B_SHARP:
                return "B♯";
            case B_DOUBLE_FLAT:
                return "B♭♭";
            case B_DOUBLE_SHARP:
                return "B♯♯";

            default:
                return "?";

        }
    }

    public static int keyStringToInt(String key_name){

        switch (key_name){

            case "C major":
                return C_MAJOR;
            case "F major":
                return F_MAJOR;
            case "B♭ major":
                return B_FLAT_MAJOR;
            case "E♭ major":
                return E_FLAT_MAJOR;
            case "A♭ major":
                return A_FLAT_MAJOR;
            case "D♭ major":
                return D_FLAT_MAJOR;
            case "G♭ major":
                return G_FLAT_MAJOR;
            case "B major":
                return B_MAJOR;
            case "E major":
                return E_MAJOR;
            case "A major":
                return A_MAJOR;
            case "D major":
                return D_MAJOR;
            case "G major":
                return G_MAJOR;
            case "C♯ major":
                return C_SHARP_MAJOR;
            case "F♯ major":
                return F_SHARP_MAJOR;
            case "C♭ major":
                return C_FLAT_MAJOR;


            case "A minor":
                return A_MINOR;
            case "D minor":
                return D_MINOR;
            case "G minor":
                return G_MINOR;
            case "C minor":
                return C_MINOR;
            case "F minor":
                return F_MINOR;
            case "A♯ minor":
                return A_SHARP_MINOR;
            case "D♯ minor":
                return D_SHARP_MINOR;
            case "G♯ minor":
                return G_SHARP_MINOR;
            case "C♯ minor":
                return C_SHARP_MINOR;
            case "F♯ minor":
                return F_SHARP_MINOR;
            case "B minor":
                return B_MINOR;
            case "E minor":
                return E_MINOR;
            case "B♭ minor":
                return B_FLAT_MINOR;
            case "E♭ minor":
                return E_FLAT_MINOR;
            case "A♭ minor":
                return A_FLAT_MINOR;

            default:
                return C_MAJOR;

        }

    }

}
