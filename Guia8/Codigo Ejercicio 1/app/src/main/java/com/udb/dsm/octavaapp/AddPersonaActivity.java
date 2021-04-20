package com.udb.dsm.octavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.udb.dsm.octavaapp.modelos.Persona;

public class AddPersonaActivity extends AppCompatActivity {
    EditText editDui, editNombre, editFechaNacimiento, editGenero, editPeso, editAltura;
    String key="", dui="", nombre="", fechaNacimiento="", genero="", peso="", altura="", accion="";
    Button buttonSave, buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_persona);

        initialize();
    }

    private void initialize() {
        editDui = findViewById(R.id.editDui);
        editNombre = findViewById(R.id.editNombre);
        editFechaNacimiento = findViewById(R.id.editFechaNacimiento);
        editGenero = findViewById(R.id.editGenero);
        editPeso = findViewById(R.id.editPeso);
        editAltura = findViewById(R.id.editAltura);

        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(v -> {
            save();
        });

        buttonCancel.setOnClickListener(v -> {
            cancel();
        });

        Bundle data = getIntent().getExtras();
        key = data.getString("key");
        dui = data.getString("dui");
        nombre = data.getString("nombre");
        fechaNacimiento = data.getString("fechaNacimiento");
        genero = data.getString("genero");
        peso = data.getString("peso");
        altura = data.getString("altura");
        accion = data.getString("accion");

        editDui.setText(dui);
        editNombre.setText(nombre);
        editFechaNacimiento.setText(fechaNacimiento);
        editGenero.setText(genero);
        editPeso.setText(peso);
        editAltura.setText(altura);
    }

    public void save() {
        String dui = editDui.getText().toString();
        String nombre = editNombre.getText().toString();
        String fechaNacimiento = editFechaNacimiento.getText().toString();
        String genero = editGenero.getText().toString();
        String peso = editPeso.getText().toString();
        String altura = editAltura.getText().toString();

        Persona persona = new Persona(dui, nombre, fechaNacimiento, genero, peso, altura);

        if(accion.equals("a")) {
            PersonasActivity.refPersonas.push().setValue(persona);
        }
        else {
            PersonasActivity.refPersonas.child(key).setValue(persona);
        }

        finish();
    }

    public void cancel() {
        finish();
    }
}