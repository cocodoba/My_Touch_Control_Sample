package com.example.shinoharanaoki.mytouchcontrolsample.chord_activity;

/**
 * Created by shinoharanaoki on 2016/08/13.
 */
public class Scale {
    private static final String TAG = Scale.class.getSimpleName();
    private final Scale self = this;

    public static final int OCTAVE = 11;

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

    public static final int _C = 0;
    public static final int _C_SHARP_D_FLAT = 1;
    //public static final int _C_SHARP_D_FLAT_MINOR = 4;
    public static final int _D = 2;
    //public static final int _D_MINOR = 6;
    public static final int _D_SHARP_E_FLAT = 3;
    //public static final int _D_SHARP_E_FLAT_MINOR = 8;
    public static final int _E = 4;
    //public static final int _E_MINOR = 10;
    //public static final int _E_SHARP_F = 6;
    //public static final int _E_SHARP_F_MINOR = 12;
    public static final int _F = 5;
    //public static final int _F_MINOR = 14;
    public static final int _F_SHARP_G_FLAT = 6;
    //public static final int _F_SHARP_G_FLAT_MINOR = 16;
    public static final int _G = 7;
    //public static final int _G_MINOR = 18;
    public static final int _G_SHARP_A_FLAT = 8;
    //public static final int _G_SHARP_A_FLAT_MINOR =20;
    public static final int _A = 9;
    //public static final int _A_MINOR = 22;
    public static final int _A_SHARP_B_FLAT = 10;
    //public static final int _A_SHARP_B_FLAT_MINOR = 24;
    public static final int _B = 11;
    //public static final int _B_MINOR = 26;
    //public static final int _B_SHARP_C = 13;
    //public static final int _B_SHARP_C_MINOR = 28;

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

    public static int[] F_MAJOR_D_MINOR = {C,D_FLAT,D,E_FLAT,E,F,G_FLAT,G,A_FLAT,A,B_FLAT,B_NATURAL};
    public static int[] B_FLAT_MAJOR_G_MINOR = {C,D_FLAT,D,E_FLAT,E_NATURAL,F,G_FLAT,G,A_FLAT,A,B_FLAT,B_NATURAL};
    public static int[] E_FLAT_MAJOR_C_MINOR = {E_FLAT,E_NATURAL,F,G_FLAT,G,A_FLAT,A_NATURAL,B_FLAT,B_NATURAL,C,D_FLAT,D};
    public static int[] A_FLAT_MAJOR_F_MINOR = {A_FLAT,A_NATURAL,B_FLAT,B_NATURAL,C,D_FLAT,D_NATURAL,E_FLAT,E_NATURAL,F,G_FLAT,G};
    public static int[] D_FLAT_MAJOR_B_FLAT_MINOR = {D_FLAT,D_NATURAL,E_FLAT,E_NATURAL,F,G_FLAT,G_NATURAL,A_FLAT,A,B_FLAT,B_NATURAL,C};
    public static int[] G_FLAT_MAJOR_E_FLAT_MINOR = {G_FLAT,G_NATURAL,A_FLAT,A,B_FLAT,C_FLAT,C_NATURAL,D_FLAT,D,E_FLAT,E_NATURAL,F};
    public static int[] C_FLAT_MAJOR_A_FLAT_MINOR = {C_FLAT,C_NATURAL,D_FLAT,D,E_FLAT,F_FLAT,F_NATURAL,G_FLAT,G,A_FLAT,A,B_FLAT};

    //TODO
    /*public static int[] C_MAJOR_A_MINOR = {,,,,,,,,,,,};
    public static int[] G_MAJOR_E_MINOR = {,,,,,,,,,,,};
    public static int[] D_MAJOR_B_MINOR = {,,,,,,,,,,,};
    public static int[] A_MAJOR_F_SHARP_MINOR = {,,,,,,,,,,,};
    public static int[] E_MAJOR_C_SHARP_MINOR = {,,,,,,,,,,,};
    public static int[] B_MAJOR_G_SHARP_MINOR = {,,,,,,,,,,,};
    public static int[] F_SHARP_MAJOR_D_SHARP_MINOR = {,,,,,,,,,,,};
    public static int[] C_SHARP_MAJOR_A_SHARP_MINOR = {,,,,,,,,,,,};*/

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

    /*public static int getRelativeNoteIndicator(int tonic, int note){

        switch (tonic){

            case B_FLAT:
                return B_FLAT_MAJOR_G_MINOR[note];

            default:
                return F_MAJOR_D_MINOR[note];

        }

    }*/

}
