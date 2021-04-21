package com.udb.dsm.cuartaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView txtViewName, txtViewHours, txtViewSalary, txtViewISSS, txtViewAFP, txtViewRent, txtViewNewSalary;

    private Button btnFinish;

    private double hourValue = 8.50, salary, newSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String hours = bundle.getString("hours");

        txtViewName = findViewById(R.id.txtViewName);
        txtViewHours = findViewById(R.id.txtViewHours);
        txtViewSalary = findViewById(R.id.txtViewSalary);
        txtViewISSS = findViewById(R.id.txtViewISSS);
        txtViewAFP = findViewById(R.id.txtViewAFP);
        txtViewRent = findViewById(R.id.txtViewRent);
        txtViewNewSalary = findViewById(R.id.txtViewNewSalary);

        btnFinish = findViewById(R.id.btnFinish);

        salary = hourValue * Integer.parseInt(hours);
        newSalary = (hourValue - hourValue * 0.09) * Integer.parseInt(hours);

        txtViewName.setText("Nombre: " + name);
        txtViewHours.setText("Horas trabajadas: " + hours);
        txtViewSalary.setText("SALARIO: $ " + Math.round(salary * 100.0) / 100.0);

        txtViewISSS.setText("ISSS: -$ " + Math.round((salary * 0.02) * 100.0) / 100.0);
        txtViewAFP.setText("AFP: -$ " + Math.round((salary * 0.03) * 100.0) / 100.0);
        txtViewRent.setText("Renta: -$ " + Math.round((salary * 0.04) * 100.0) / 100.0);

        txtViewNewSalary.setText("SALARIO NETO: $ " + Math.round(newSalary * 100.0) / 100.0);

        btnFinish.setOnClickListener((v) -> {
            finish();
        });
    }
}