package com.udb.dsm.cuartaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtHours;

    private Button btnAccept;

    private String name, hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtHours = findViewById(R.id.txtHours);
        btnAccept = findViewById(R.id.btnAccept);

        btnAccept.setOnClickListener((v) -> {
            name = txtName.getText().toString();
            hours = txtHours.getText().toString();

            if(!name.equals("") && !hours.equals("")) {
                Intent i = new Intent(this, SecondActivity.class);

                i.putExtra("name", name);
                i.putExtra("hours", hours);

                startActivity(i);
            }
            else {
                Toast toastMessage = Toast.makeText(this, "Debe rellenar ambos campos", Toast.LENGTH_LONG);
                toastMessage.show();
            }
        });
    }
}