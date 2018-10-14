package com.example.rosa.conversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final int TYPE_SELECTION = 1;
    public static String type = "Length";
    private TextView unit1;
    private TextView unit2;
    private UnitsConverter.LengthUnits fromLength = UnitsConverter.LengthUnits.Meters;;
    private UnitsConverter.LengthUnits toLength = UnitsConverter.LengthUnits.Yards;
    private UnitsConverter.VolumeUnits fromVolume = UnitsConverter.VolumeUnits.Liters;
    private UnitsConverter.VolumeUnits toVolume = UnitsConverter.VolumeUnits.Gallons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText toText = (EditText) findViewById(R.id.toText);
        EditText fromText = (EditText) findViewById(R.id.fromText);
        Button Calculate = (Button) findViewById(R.id.Calculate);
        Button Cancel = (Button) findViewById(R.id.Clear);
        Button Mode = (Button) findViewById(R.id.Mode);
        Button Settings = (Button) findViewById(R.id.Settings);
        TextView Title = (TextView) findViewById(R.id.title);
        unit1 = (TextView) findViewById(R.id.textView2);
        unit2 = (TextView) findViewById(R.id.textView3);


        Title.setText("Length Converter");

        Calculate.setOnClickListener(v -> {
            if (toText.length() == 0) {
                String fromVal = fromText.getText().toString();
                if (type == "Length") {
                    Double value = UnitsConverter.convert(Double.parseDouble(fromVal), fromLength, toLength);
                    toText.setText(value.toString());
                } else {
                    Double value = UnitsConverter.convert(Double.parseDouble(fromVal), fromVolume, toVolume);
                    toText.setText(value.toString());
                }
            } else {
                String toVal = toText.getText().toString();
                if (type == "Length") {
                    Double value = UnitsConverter.convert(Double.parseDouble(toVal), toLength, fromLength);
                    fromText.setText(value.toString());
                } else {
                    Double value = UnitsConverter.convert(Double.parseDouble(toVal), toVolume, fromVolume);
                    fromText.setText(value.toString());
                }

            }

        });

        Cancel.setOnClickListener(v -> {
         toText.setText("");
         fromText.setText("");
        });


        Mode.setOnClickListener(v -> {
            if(type == "Length"){
                type = "Volume";
                unit1.setText(toVolume.toString());
                unit2.setText(fromVolume.toString());
                Title.setText("Volume Converter");
            }
            else {
                type = "Length";
                unit1.setText(toLength.toString());
                unit2.setText(fromLength.toString());
                Title.setText("Length Converter");
            }


        });

        Settings.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, settings.class);
            startActivityForResult(intent, TYPE_SELECTION);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == TYPE_SELECTION){
            String toString = data.getStringExtra("to");
            String fromString = data.getStringExtra("from");
            if(type == "Length") {
                toLength = UnitsConverter.LengthUnits.valueOf(toString);
                fromLength = UnitsConverter.LengthUnits.valueOf(fromString);
            }
            else{
                toVolume = UnitsConverter.VolumeUnits.valueOf(toString);
                fromVolume= UnitsConverter.VolumeUnits.valueOf(fromString);
            }
            unit1.setText(fromString);
            unit2.setText(toString);
        }
    }

}
