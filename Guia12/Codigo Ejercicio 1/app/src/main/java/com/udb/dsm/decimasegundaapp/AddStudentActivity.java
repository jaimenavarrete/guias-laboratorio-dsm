package com.udb.dsm.decimasegundaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {
    EditText etCarnet, etNombre, etApellido;
    Button btnReturn, btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        initializeElements();
    }

    protected void initializeElements() {
        etCarnet = findViewById(R.id.etCarnet);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            finish();
        });

        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddStudent.setOnClickListener(v -> {
            addStudent();
        });
    }

    protected void addStudent() {
        ContentValues values = new ContentValues();

        values.put(StudentsContract.Columnas.CARNET, etCarnet.getText().toString());
        values.put(StudentsContract.Columnas.NOMBRE, etNombre.getText().toString());
        values.put(StudentsContract.Columnas.APELLIDO, etApellido.getText().toString());

        Uri uri = getContentResolver().insert(StudentsContract.CONTENT_URI, values);

        Toast.makeText(getBaseContext(), "El estudiante ha sido agregado correctamente", Toast.LENGTH_SHORT).show();
    }
}