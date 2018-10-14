package com.example.rosa.conversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class settings extends AppCompatActivity {

    public static final int TYPE_SELECTION = 1;

    private String to = "";
    private String from = "";
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("to", to);
                intent.putExtra("from", from);
                setResult(MainActivity.TYPE_SELECTION, intent);
                finish();
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.topSpin);
        Spinner spinner_bottom = (Spinner) findViewById(R.id.bottomSpin);


        int array_type;
        type = MainActivity.type;

        if(type == "Volume") {
            array_type = R.array.volume;
        }
        else{
            array_type = R.array.length;
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                from = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, array_type, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_bottom.setAdapter(adapter1);
        spinner_bottom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                to = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });
    }

}
