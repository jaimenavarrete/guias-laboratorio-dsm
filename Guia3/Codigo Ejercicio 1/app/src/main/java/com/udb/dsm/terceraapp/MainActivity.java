package com.udb.dsm.terceraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView number;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = (TextView)findViewById(R.id.viewNumber);
        count = 0;

        if(savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            number.setText(String.valueOf(count));
        }
    }

    public void addNumber(View v) {
        count = Integer.parseInt(number.getText().toString());

        if(count < 9) {
            count++;
        }
        else {
            count = 0;
        }

        number.setText(String.valueOf(count));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count", count);
    }
}