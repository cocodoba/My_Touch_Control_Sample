package com.example.shinoharanaoki.mytouchcontrolsample.chord_activity;

/**
 * Created by shinoharanaoki on 2016/08/16.
 */
public class ChordTerm {
    private static final String TAG = ChordTerm.class.getSimpleName();
    private final ChordTerm self = this;

    private Chord chord;
    private long term;
    private int tonic;

    public ChordTerm(Chord chord, long term){
        this.chord = chord;
        this.term = term;
    }

    public Chord getChord() {
        return chord;
    }

    public long getTerm() {
        return term;
    }

    public void setChord(Chord chord) {
        this.chord = chord;
    }

    public void setTerm(long term) {
        this.term = term;
    }
}
