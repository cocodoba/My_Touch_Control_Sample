package com.example.shinoharanaoki.mytouchcontrolsample.chord_activity;

/**
 * Created by shinoharanaoki on 2016/08/13.
 */
public class Chord {
    private static final String TAG = Chord.class.getSimpleName();
    private final Chord self = this;

    public static final int OMITTED = 99;
    public static final int OCTAVE_INTERVAL = 12;

    /**Tonic Positions*/
    public static final int C       = 0;
    public static final int C_SHARP = 1;
    public static final int D_FLAT  = 1;
    public static final int D       = 2;
    public static final int D_SHARP = 3;
    public static final int E_FLAT  = 3;
    public static final int E       = 4;
    public static final int F_FLAT  = 4;
    public static final int E_SHARP = 5;
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
    public static final int C_FLAT  = 11;
    public static final int B_SHARP = 0;

    /*度数(コードスケールポジションに対応する)*/
    public static final int _I         = 0;
    public static final int _I_SHARP   = 1;
    public static final int _II_FLAT = 1;
    public static final int _II       = 2;
    public static final int _II_SHARP   = 3;
    public static final int _III_FLAT    = 3;
    public static final int _III          = 4;
    public static final int _IV_FLAT      = 4;
    public static final int _III_SHARP   = 5;
    public static final int _IV      = 5;
    public static final int _IV_SHARP   = 6;
    public static final int _V_FLAT   = 6;
    public static final int _V         = 7;
    public static final int _V_SHARP   = 8;
    public static final int _VI_FLAT = 8;
    public static final int _VI       = 9;
    public static final int _VI_SHARP   = 10;
    public static final int _VII_FLAT   = 10;
    public static final int _VII         = 11;
    public static final int _VIII_FLAT = 11;
    public static final int _VII_SHARP   = 12;
    public static final int _VIII = 12;
    public static final int _VIII_SHARP   = 13;
    public static final int _IX_FLAT = 13;
    public static final int _IX = 14;
    public static final int _IX_SHARP   = 15;
    public static final int _X_FLAT = 15;
    public static final int _X = 16;
    public static final int _XI_FLAT = 16;
    public static final int _X_SHARP   = 17;
    public static final int _XI = 17;
    public static final int _XI_SHARP   = 18;
    public static final int _XII_FLAT = 18;
    public static final int _XII = 19;
    public static final int _XII_SHARP   = 20;
    public static final int _XIII_FLAT = 20;
    public static final int _XIII = 21;
    public static final int _XIII_SHARP   = 22;
    public static final int _I_FLAT   = 11;


    /*トライアド種(メジャー・マイナー(m3rd)・ディミニッシュ(m3rd,m5th)・オーギュメント(aug5th)など)(コード作成画面と共通)*/
    public static final int POWER_CHORD = 0;
    public static final int MAJOR_TRIAD = 1;
    public static final int MINOR_TRIAD = 2;
    public static final int SUSPENDED_FOURTH_TRIAD = 3;
    public static final int DIMINISHED_TRIAD = 4;
    public static final int AUGMENTED_TRIAD = 6;

    /**Chord Structure Types*/
    public static final int MAJOR = 0;
    public static final int SIXTH = 1;
    public static final int ADD_NINTH = 2;
    public static final int SIXTH_NINTH = 3;
    public static final int MAJOR_SEVENTH = 4;
    public static final int MAJOR_NINTH = 5;
    public static final int MAJOR_THIRTEENTH = 6;
    public static final int MAJOR_SEVENTH_SHARP_ELEVENTH = 7;
    public static final int MAJOR_NINETH_SHARP_ELEVENTH = 8;
    public static final int MAJOR_THERTEENTH_SHARP_ELEVENTH = 9;
    public static final int SEVENTH = 10;
    public static final int NINTH = 11;
    public static final int SEVENTH_FLAT_NINTH = 12;
    public static final int SEVENTH_SHARP_NINTH = 13;
    public static final int SEVENTH_SHARP_ELEVENTH = 14;
    public static final int SEVENTH_FLAT_NINETH_THIRTEENTH = 15;
    public static final int THIRTEENTH = 16;
    public static final int MINOR = 17;
    public static final int MINOR_SIXTH = 18;
    public static final int MINOR_ADD_NINTH = 19;
    public static final int MINOR_SIXTH_NINTH = 20;
    public static final int MINOR_MAJOR_SEVENTH = 21;
    public static final int MINOR_SEVENTH = 22;
    public static final int MINOR_NINTH = 23;
    public static final int MINOR_ELEVENTH = 24;
    public static final int MINOR_THIRTEENTH = 25;
    public static final int DIMINISHED = 26;
    public static final int MINOR_SEVENTH_FLAT_FIFTH = 27;
    public static final int MINOR_ELEVENTH_FLAT_FIFTH = 28;
    public static final int SEVENTH_FLAT_FIFTH = 29;
    public static final int MAJOR_SEVENTH_FLAT_FIFTH = 30;
    public static final int AUGMENTED = 31;
    public static final int SEVENTH_SHARP_FIFTH = 32;
    public static final int MAJOR_SEVENTH_SHARP_FIFTH = 33;
    public static final int SUSPENDED_FOURTH = 34;
    public static final int SEVENTH_SUSPENDED_FOURTH = 35;
    public static final int NINTH_SUSPENDED_FOURTH = 36;


    private int key = C;
    private int chords_hight = 3; //TODO UserSelect

    /**最終的に出来たコードトーンの度数の配列*/
    /*最終的に出来たコードのルート*/
    private int root;
    private int root_height = OCTAVE_INTERVAL*chords_hight;
    private String root_string;

    private int third_or_fourth = OMITTED;
    private int fifth = OMITTED;
    private int sixth = OMITTED;
    private int seventh = OMITTED;
    private int ninth = OMITTED;
    private int eleventh = OMITTED;
    private int thirteenth = OMITTED;
    private String chord_symbol_string;

    /*ダブルフラットやダブルシャープの表示に対応するためのフラグ*/
    public boolean flag_diminished;
    public boolean flag_augumented;


    public void setRoot(int root, String root_string) {
        this.root =  key + root;
        this.root_string = root_string;
    }

    public void setChordIntervalsBySymbol(int chord_symbol, String chord_symbol_string){

        if(chord_symbol == MAJOR||chord_symbol == SIXTH||chord_symbol == ADD_NINTH||chord_symbol == SIXTH_NINTH
                ||chord_symbol == MAJOR_SEVENTH||chord_symbol == MAJOR_NINTH||chord_symbol == MAJOR_THIRTEENTH
                ||chord_symbol == MAJOR_SEVENTH_SHARP_ELEVENTH||chord_symbol == MAJOR_NINETH_SHARP_ELEVENTH
                ||chord_symbol == MAJOR_THERTEENTH_SHARP_ELEVENTH
                ||chord_symbol == SEVENTH||chord_symbol == NINTH||chord_symbol == SEVENTH_FLAT_NINTH||chord_symbol == SEVENTH_SHARP_NINTH
                ||chord_symbol == SEVENTH_SHARP_ELEVENTH||chord_symbol == SEVENTH_FLAT_NINETH_THIRTEENTH||chord_symbol == THIRTEENTH){

            setChordTriadIntervals(MAJOR_TRIAD);

        }else if(chord_symbol == MINOR||chord_symbol == MINOR_SIXTH||chord_symbol == MINOR_ADD_NINTH||chord_symbol == MINOR_SIXTH_NINTH
                ||chord_symbol == MINOR_MAJOR_SEVENTH||chord_symbol == MINOR_SEVENTH
                ||chord_symbol == MINOR_NINTH||chord_symbol == MINOR_ELEVENTH||chord_symbol == MINOR_THIRTEENTH){

            setChordTriadIntervals(MINOR_TRIAD);

        }else if(chord_symbol == SUSPENDED_FOURTH||chord_symbol == SEVENTH_SUSPENDED_FOURTH||chord_symbol == NINTH_SUSPENDED_FOURTH){

            setChordTriadIntervals(SUSPENDED_FOURTH_TRIAD);

        }else if(chord_symbol == DIMINISHED||chord_symbol == MINOR_SEVENTH_FLAT_FIFTH
                ||chord_symbol == MINOR_ELEVENTH_FLAT_FIFTH||chord_symbol == SEVENTH_FLAT_FIFTH||chord_symbol == MAJOR_SEVENTH_FLAT_FIFTH){

            setChordTriadIntervals(DIMINISHED_TRIAD);

        }else if(chord_symbol == AUGMENTED||chord_symbol == SEVENTH_SHARP_FIFTH||chord_symbol == MAJOR_SEVENTH_SHARP_FIFTH){

            setChordTriadIntervals(AUGMENTED_TRIAD);
        }


        /*Add Sixth Note*/
        if(chord_symbol == SIXTH||chord_symbol == SIXTH_NINTH||chord_symbol == MINOR_SIXTH||chord_symbol ==MINOR_SIXTH_NINTH){
            sixth = root + root_height + _VI;
        }

        /*Add Seventh Note*/
        if(chord_symbol == MAJOR_SEVENTH||chord_symbol == MAJOR_THIRTEENTH||chord_symbol == MAJOR_SEVENTH_SHARP_ELEVENTH ||chord_symbol == MAJOR_NINTH
                ||chord_symbol == MAJOR_NINETH_SHARP_ELEVENTH||chord_symbol == MAJOR_THERTEENTH_SHARP_ELEVENTH||chord_symbol == MINOR_MAJOR_SEVENTH
                ||chord_symbol == MAJOR_SEVENTH_FLAT_FIFTH||chord_symbol == MAJOR_SEVENTH_SHARP_FIFTH){
            seventh = root + root_height + _VII;
        }else if(chord_symbol == SEVENTH||chord_symbol == NINTH||chord_symbol == SEVENTH_FLAT_NINTH
                ||chord_symbol == SEVENTH_SHARP_NINTH||chord_symbol == SEVENTH_SHARP_ELEVENTH||chord_symbol == SEVENTH_FLAT_NINETH_THIRTEENTH
                ||chord_symbol == THIRTEENTH||chord_symbol == MINOR_SEVENTH||chord_symbol == MINOR_NINTH||chord_symbol == MINOR_ELEVENTH||chord_symbol == MINOR_THIRTEENTH
                ||chord_symbol == MINOR_SEVENTH_FLAT_FIFTH||chord_symbol == MINOR_ELEVENTH_FLAT_FIFTH||chord_symbol == SEVENTH_FLAT_FIFTH||chord_symbol == SEVENTH_SHARP_FIFTH
                ||chord_symbol == SEVENTH_SUSPENDED_FOURTH||chord_symbol == NINTH_SUSPENDED_FOURTH){

            seventh = root + root_height + _VII_FLAT;
        }

        /*Add Ninth Note*/
        if(chord_symbol == ADD_NINTH||chord_symbol == SIXTH_NINTH||chord_symbol == MAJOR_NINTH
                ||chord_symbol == MAJOR_NINETH_SHARP_ELEVENTH||chord_symbol == MAJOR_THERTEENTH_SHARP_ELEVENTH||chord_symbol == NINTH
                ||chord_symbol == THIRTEENTH ||chord_symbol == MINOR_ADD_NINTH||chord_symbol == MINOR_SIXTH_NINTH
                ||chord_symbol == MINOR_NINTH||chord_symbol == MINOR_ELEVENTH
                ||chord_symbol == MINOR_THIRTEENTH||chord_symbol == NINTH_SUSPENDED_FOURTH){

            ninth = root + root_height + _IX;
        }else if(chord_symbol == SEVENTH_FLAT_NINTH||chord_symbol == SEVENTH_FLAT_NINETH_THIRTEENTH){

            ninth = root + root_height + _IX_FLAT;
        }else if(chord_symbol == SEVENTH_SHARP_NINTH){

            ninth = root + root_height + _IX_SHARP;
        }

        /*Add Eleventh Note*/
        if(chord_symbol == MINOR_ELEVENTH||chord_symbol == MINOR_ELEVENTH_FLAT_FIFTH){

            eleventh = root + root_height + _XI;
        }else if(chord_symbol == MAJOR_SEVENTH_SHARP_ELEVENTH||chord_symbol == MAJOR_NINETH_SHARP_ELEVENTH
                ||chord_symbol == MAJOR_THERTEENTH_SHARP_ELEVENTH||chord_symbol == SEVENTH_SHARP_ELEVENTH){

            eleventh = root + root_height + _XI_SHARP;
        }

        /*Add Thirteenth Note*/
        if(chord_symbol == MAJOR_THIRTEENTH||chord_symbol == MAJOR_THERTEENTH_SHARP_ELEVENTH||chord_symbol == THIRTEENTH
                ||chord_symbol == SEVENTH_FLAT_NINETH_THIRTEENTH||chord_symbol == MINOR_THIRTEENTH){

            thirteenth = root + root_height + _XIII;
        }

        this.chord_symbol_string = chord_symbol_string;

    }

    public void setChordTriadIntervals(int triad_type){

        switch (triad_type){

            case MAJOR_TRIAD:
                third_or_fourth = 4;
                fifth = 7;
                break;
            case MINOR_TRIAD:
                third_or_fourth = 3;
                fifth = 7;
                break;
            case SUSPENDED_FOURTH:
                third_or_fourth = 5;
                fifth = 7;
                break;
            case DIMINISHED:
                third_or_fourth = 3;
                fifth = 6;
                flag_diminished = true;
                break;
            case AUGMENTED:
                third_or_fourth = 4;
                fifth = 8;
                flag_augumented = true;
                break;
            case POWER_CHORD:
                third_or_fourth = OMITTED;
                fifth = 7;
                break;
        }
    }

    //TODO 転回、ルートの高さ voicingStyle(omit) 等
    public int[] generateChordSounds(){

        int[] chord_sounds = new int[3];
        chord_sounds[0] = root + 36; //+ offset
        chord_sounds[1] = root + third_or_fourth + 36;
        chord_sounds[2] = root + fifth + 36;
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

    public String getRootString(){
        return root_string;
    }

    public int getKey(){
        return key;
    }

    public int[] getChordTriad(){
        int[] chord_triad = new int[3];
        chord_triad[0] = root + 36; //+ offset
        chord_triad[1] = root + third_or_fourth + 36;
        chord_triad[2] = root + fifth + 36;
        return chord_triad;
    }

    public int getSixth(){
        return sixth;
    }

    public int getSeventh(){
        return seventh;
    }

    public int getNinth(){
        return ninth;
    }

    public int getEleventh(){
        return eleventh;
    }

    public int getThirteenth(){
        return thirteenth;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setSixth(int sixth) {
        this.sixth = sixth;
    }

    public void setSeventh(int seventh) {
        this.seventh = seventh;
    }

    public String getChordSymbolString(){
        return chord_symbol_string;
    }

    //TODO
    public static int tonicRootStringToInt(String tonic_or_root_string){
        int tonic_or_root_int;

        switch (tonic_or_root_string){
            case "C":
                tonic_or_root_int = C;
                break;
            case "C♭":
                tonic_or_root_int = C_FLAT;
                break;
            case "C♯":
                tonic_or_root_int = C_SHARP;
                break;
            case "D":
                tonic_or_root_int = D;
                break;
            case "D♭":
                tonic_or_root_int = D_FLAT;
                break;
            case "D♯":
                tonic_or_root_int = D_SHARP;
                break;
            case "E":
                tonic_or_root_int = E;
                break;
            case "E♭":
                tonic_or_root_int = E_FLAT;
                break;
            case "E♯":
                tonic_or_root_int = E_SHARP;
                break;
            case "F":
                tonic_or_root_int = F;
                break;
            case "F♭":
                tonic_or_root_int = F_FLAT;
                break;
            case "F♯":
                tonic_or_root_int = F_SHARP;
                break;
            case "G":
                tonic_or_root_int = G;
                break;
            case "G♭":
                tonic_or_root_int = G_FLAT;
                break;
            case "G♯":
                tonic_or_root_int = G_SHARP;
                break;
            case "A":
                tonic_or_root_int = A;
                break;
            case "A♭":
                tonic_or_root_int = A_FLAT;
                break;
            case "A♯":
                tonic_or_root_int = A_SHARP;
                break;
            case "B":
                tonic_or_root_int = B;
                break;
            case "B♭":
                tonic_or_root_int = B_FLAT;
                break;
            case "B♯":
                tonic_or_root_int = B_SHARP;
                break;

            default:
                tonic_or_root_int = C;
        }
        return tonic_or_root_int;
    }

    public static int keyStringToTonicPositionInt(String key_string){
        int tonic_position_int;

        switch (key_string){
            case "C major":
                tonic_position_int = C;
                break;
            case "C♭ major":
                tonic_position_int = C_FLAT;
                break;
            case "C♯ major":
                tonic_position_int = C_SHARP;
                break;
            case "D major":
                tonic_position_int = D;
                break;
            case "D♭ major":
                tonic_position_int = D_FLAT;
                break;
            case "E major":
                tonic_position_int = E;
                break;
            case "E♭ major":
                tonic_position_int = E_FLAT;
                break;
            case "F major":
                tonic_position_int = F;
                break;
            case "F♯ major":
                tonic_position_int = F_SHARP;
                break;
            case "G major":
                tonic_position_int = G;
                break;
            case "G♭ major":
                tonic_position_int = G_FLAT;
                break;
            case "A major":
                tonic_position_int = A;
                break;
            case "A♭ major":
                tonic_position_int = A_FLAT;
                break;
            case "B major":
                tonic_position_int = B;
                break;
            case "B♭ major":
                tonic_position_int = B_FLAT;
                break;


            case "C minor":
                tonic_position_int = C;
                break;
            case "C♭ minor":
                tonic_position_int = C_FLAT;
                break;
            case "C♯ minor":
                tonic_position_int = C_SHARP;
                break;
            case "D minor":
                tonic_position_int = D;
                break;
            case "D♭ minor":
                tonic_position_int = D_FLAT;
                break;
            case "D♯ minor":
                tonic_position_int = D_SHARP;
                break;
            case "E minor":
                tonic_position_int = E;
                break;
            case "E♭ minor":
                tonic_position_int = E_FLAT;
                break;
            case "E♯ minor":
                tonic_position_int = E_SHARP;
                break;
            case "F minor":
                tonic_position_int = F;
                break;
            case "F♭ minor":
                tonic_position_int = F_FLAT;
                break;
            case "F♯ minor":
                tonic_position_int = F_SHARP;
                break;
            case "G minor":
                tonic_position_int = G;
                break;
            case "G♭ minor":
                tonic_position_int = G_FLAT;
                break;
            case "G♯ minor":
                tonic_position_int = G_SHARP;
                break;
            case "A minor":
                tonic_position_int = A;
                break;
            case "A♭ minor":
                tonic_position_int = A_FLAT;
                break;
            case "A♯ minor":
                tonic_position_int = A_SHARP;
                break;
            case "B minor":
                tonic_position_int = B;
                break;
            case "B♭ minor":
                tonic_position_int = B_FLAT;
                break;
            case "B♯ minor":
                tonic_position_int = B_SHARP;
                break;

            default:
                tonic_position_int = C;
        }
        return tonic_position_int;
    }

    public static int degreeNameStringToInt(String degree_name){

        int degree_name_int;

        switch (degree_name) {
            case "I":
                degree_name_int = _I;
                break;
            case "I♭":
                degree_name_int = _I_FLAT;
                break;
            case "I♯":
                degree_name_int = _I_SHARP;
                break;
            case "II":
                degree_name_int = _II;
                break;
            case "II♭":
                degree_name_int = _II_FLAT;
                break;
            case "II♯":
                degree_name_int = _II_SHARP;
                break;
            case "III":
                degree_name_int = _III;
                break;
            case "III♭":
                degree_name_int = _III_FLAT;
                break;
            case "III♯":
                degree_name_int = _III_SHARP;
                break;
            case "IV":
                degree_name_int = _IV;
                break;
            case "IV♭":
                degree_name_int = _IV_FLAT;
                break;
            case "IV♯":
                degree_name_int = _IV_SHARP;
                break;
            case "V":
                degree_name_int = _V;
                break;
            case "V♭":
                degree_name_int = _V_FLAT;
                break;
            case "V♯":
                degree_name_int = _V_SHARP;
                break;
            case "VI":
                degree_name_int = _VI;
                break;
            case "VI♭":
                degree_name_int = _VI_FLAT;
                break;
            case "VI♯":
                degree_name_int = _VI_SHARP;
                break;
            case "VII":
                degree_name_int = _VII;
                break;
            case "VII♭":
                degree_name_int = _VII_FLAT;
                break;
            case "VII♯":
                degree_name_int = _I;     //FIXME
                break;

            default:
                degree_name_int = _I;
        }
        return degree_name_int;
    }

    public static int chordSymbolStringToInt(String symbol_string){

        int chord_structure_int;

        switch (symbol_string){
            case "Major":
                chord_structure_int = MAJOR;
                break;
            case "6":
                chord_structure_int = SIXTH;
                break;
            case "add9":
                chord_structure_int = ADD_NINTH;
                break;
            case "6(9)":
                chord_structure_int = SIXTH_NINTH;
                break;
            case "M7":
                chord_structure_int = MAJOR_SEVENTH;
                break;
            case "M9":
                chord_structure_int = MAJOR_NINTH;
                break;
            case "M13":
                chord_structure_int = MAJOR_THIRTEENTH;
                break;
            case "M7(♯11)":
                chord_structure_int = MAJOR_SEVENTH_SHARP_ELEVENTH;
                break;
            case "M9(♯11)":
                chord_structure_int = MAJOR_NINETH_SHARP_ELEVENTH;
                break;
            case "M13(♯11)":
                chord_structure_int = MAJOR_THERTEENTH_SHARP_ELEVENTH;
                break;
            case "7":
                chord_structure_int = SEVENTH;
                break;
            case "9":
                chord_structure_int = NINTH;
                break;
            case "7(♭9)":
                chord_structure_int = SEVENTH_FLAT_NINTH;
                break;
            case "7(♯9)":
                chord_structure_int = SEVENTH_SHARP_NINTH;
                break;
            case "7(♯11)":
                chord_structure_int = SEVENTH_SHARP_ELEVENTH;
                break;
            case "7(♭9,13)":
                chord_structure_int = SEVENTH_FLAT_NINETH_THIRTEENTH;
                break;
            case "13":
                chord_structure_int = THIRTEENTH;
                break;
            case "Minor":
                chord_structure_int = MINOR;
                break;
            case "m6":
                chord_structure_int = MINOR_SIXTH;
                break;
            case "m add9":
                chord_structure_int = MINOR_ADD_NINTH;
                break;
            case "m6(9)":
                chord_structure_int = MINOR_SIXTH_NINTH;
                break;
            case "mM7":
                chord_structure_int = MINOR_MAJOR_SEVENTH;
                break;
            case "m7":
                chord_structure_int = MINOR_SEVENTH;
                break;
            case "m9":
                chord_structure_int = MINOR_NINTH;
                break;
            case "m11":
                chord_structure_int = MINOR_ELEVENTH;
                break;
            case "m13":
                chord_structure_int = MINOR_THIRTEENTH;
                break;
            case "Diminished":
                chord_structure_int = DIMINISHED;
                break;
            case "m7(♭5)":
                chord_structure_int = MINOR_SEVENTH_FLAT_FIFTH;
                break;
            case "7(♭5)":
                chord_structure_int = MINOR_ELEVENTH_FLAT_FIFTH;
                break;
            case "M7(♭5)":
                chord_structure_int = SEVENTH_FLAT_FIFTH;
                break;
            case "m11(♭5)":
                chord_structure_int = MAJOR_SEVENTH_FLAT_FIFTH;
                break;
            case "Augumented":
                chord_structure_int = AUGMENTED;
                break;
            case "7(♯5)":
                chord_structure_int = SEVENTH_SHARP_FIFTH;
                break;
            case "M7(♯5)":
                chord_structure_int = MAJOR_SEVENTH_SHARP_FIFTH;
                break;
            case "sus4":
                chord_structure_int = SUSPENDED_FOURTH;
                break;
            case "7sus4":
                chord_structure_int = SEVENTH_SUSPENDED_FOURTH;
                break;
            case "9sus4":
                chord_structure_int = NINTH_SUSPENDED_FOURTH;
                break;

            default:
                chord_structure_int = MAJOR;
        }
        return  chord_structure_int;
    }
}
