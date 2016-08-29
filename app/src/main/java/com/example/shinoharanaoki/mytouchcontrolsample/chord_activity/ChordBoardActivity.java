package com.example.shinoharanaoki.mytouchcontrolsample.chord_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.shinoharanaoki.mytouchcontrolsample.R;

import java.util.ArrayList;
import java.util.Iterator;

public class ChordBoardActivity extends AppCompatActivity {

    private Spinner key_select_spinner;
    private Spinner root_select_spinner;
    private Spinner chord_structure_select_spinner;
    private EditText term_length_imput;

    private KeyBoardView keyboard_view;
    private boolean isThreadRunning = false;

    public ArrayList<ChordTerm> chordTerms_arraylist; //スレッド再生用
    private int key_tonic;     //キーボードのキー表示変更用
    public String tonic_string;   //キーを指定した場合のトニック
    public String root_absolute_string; //キーを指定していない場合のルート音
    public String root_relative_string; //キーを指定している場合のスケール上のルート音程
    public String chord_symbol;
    public boolean is_key_selected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_board);

        keyboard_view = (KeyBoardView) findViewById(R.id.keyboard_view);

        chordTerms_arraylist = new ArrayList<>();

        // Spinnerの設定
        /* 1. キーを選択するスピナー*/
        final ArrayAdapter<CharSequence> key_adapter =
                ArrayAdapter.createFromResource(this, R.array.key_array,
                        android.R.layout.simple_spinner_item);

        /**
         * ルートを指定するスピナーに設定するアダプタを２通り用意
         * */
        /*C,D,E,,,で表示するアダプタ*/
        final ArrayAdapter<CharSequence> root_absolute_adapter =
                ArrayAdapter.createFromResource(this, R.array.root_absolute_array,
                        android.R.layout.simple_spinner_item);
        root_absolute_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        /*I,II,III,,,で表示するアダプタ*/
        final ArrayAdapter<CharSequence> root_relative_adapter =
                ArrayAdapter.createFromResource(this, R.array.root_relative_array,
                        android.R.layout.simple_spinner_item);
        root_relative_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        key_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        key_select_spinner = (Spinner) findViewById(R.id.spinner);
        root_select_spinner = (Spinner) findViewById(R.id.spinner2);
        key_select_spinner.setAdapter(key_adapter);
        key_select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                /**
                 * キーを選択するスピナーで、キーを指定無しにした場合のみ、隣のルートを選択するスピナーにおいて、root=C,D,E,,,のように絶対音で
                 * 選択するように表示を切り替える。
                 */
                if(spinner.getSelectedItemPosition()==0){
                    /**(キー指定なし)*/
                is_key_selected = false;
                    /*2(A). ルートをC,D,E,,,で選択するスピナー*/
                    root_select_spinner.setAdapter(root_absolute_adapter);
                    root_select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Spinner spinner = (Spinner) parent;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }else{
                    /**(キー選択)*/
                    is_key_selected = true;
                    tonic_string = spinner.getSelectedItem().toString();
                    key_tonic = Chord.tonicRootStringToInt(tonic_string);
                    keyboard_view.nowKey = key_tonic;
                    keyboard_view.setupKeyBoardScaleOfNowKey();

                     /*2(B). ルートをI,II,III,,,で選択するスピナー*/
                    root_select_spinner.setAdapter(root_relative_adapter);
                    root_select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Spinner spinner = (Spinner) parent;
                            root_relative_string = spinner.getSelectedItem().toString();
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                root_absolute_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                root_select_spinner.setAdapter(root_absolute_adapter);
                root_select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Spinner spinner = (Spinner) parent;
                        root_relative_string = spinner.getSelectedItem().toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });

        /* 3. コードシンボル(構成)を選択するスピナー*/
        ArrayAdapter<CharSequence> chord_structure_adapter =
                ArrayAdapter.createFromResource(this, R.array.chord_structure_array,
                        android.R.layout.simple_spinner_item);

        chord_structure_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        chord_structure_select_spinner = (Spinner) findViewById(R.id.spinner3);
        chord_structure_select_spinner.setAdapter(chord_structure_adapter);
        chord_structure_select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                chord_symbol = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        term_length_imput = (EditText)findViewById(R.id.term_length_imput);

        // ボタンの設定
        Button okButton;
        okButton = (Button)findViewById(R.id.ok_button);
        okButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chord new_chord = new Chord();
                new_chord.setKey(Chord.tonicRootStringToInt(tonic_string));
                if (!is_key_selected) {
                    new_chord.setRoot(Chord.tonicRootStringToInt(root_absolute_string));
                } else {
                    new_chord.setKey(key_tonic);
                    new_chord.setRoot(Chord.tonicRootStringToInt(root_relative_string));
                }

                new_chord.setChordIntervalsBySymbol(Chord.chordSymbolStringToInt(chord_symbol));
                ChordTerm new_chordterm = new ChordTerm(new_chord,2000);
                chordTerms_arraylist.add(new_chordterm);

            }
        });

        // ボタンの設定
        Button threadButton;
        threadButton = (Button)findViewById(R.id.thread_button);
        threadButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isThreadRunning) {
                    int l = chordTerms_arraylist.size();
                    ChordTerm[] array = new ChordTerm[l];
                    Iterator<ChordTerm> iter = chordTerms_arraylist.iterator();
                    for (int i=0;i<l;i++) array[i] = iter.next();
                    isThreadRunning = true;
                    keyboard_view.startThread(array);
                } else{
                    isThreadRunning = false;
                    keyboard_view.endThread();
                }
            }
        });
    }
}
