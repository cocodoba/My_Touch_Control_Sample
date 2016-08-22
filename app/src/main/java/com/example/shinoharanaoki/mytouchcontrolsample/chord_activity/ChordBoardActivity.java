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

public class ChordBoardActivity extends AppCompatActivity {

    private Spinner key_select_spinner;
    private Spinner root_select_spinner;
    private Spinner chord_structure_select_spinner;
    private EditText term_length_imput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_board);

        // Spinnerの設定
        final ArrayAdapter<CharSequence> key_adapter =
                ArrayAdapter.createFromResource(this, R.array.key_array,
                        android.R.layout.simple_spinner_item);

        final ArrayAdapter<CharSequence> root_absolute_adapter =
                ArrayAdapter.createFromResource(this, R.array.root_absolute_array,
                        android.R.layout.simple_spinner_item);

        final ArrayAdapter<CharSequence> root_relative_adapter =
                ArrayAdapter.createFromResource(this, R.array.root_relative_array,
                        android.R.layout.simple_spinner_item);

        key_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        key_select_spinner = (Spinner) findViewById(R.id.spinner);
        root_select_spinner = (Spinner) findViewById(R.id.spinner2);
        key_select_spinner.setAdapter(key_adapter);
        key_select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                if(spinner.getSelectedItemPosition()==0){

                    root_absolute_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

                    root_relative_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    root_select_spinner.setAdapter(root_relative_adapter);
                    root_select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Spinner spinner = (Spinner) parent;
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
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });

        // Spinnerの設定
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
            }
        });

        // ボタンの設定
        Button threadButton;
        threadButton = (Button)findViewById(R.id.thread_button);
        threadButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
