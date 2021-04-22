package com.udb.dsm.decimaprimeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editCode, editName, editLastName, editAge, editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElements();
    }

    protected void initializeElements() {
        editCode = findViewById(R.id.editCode);
        editName = findViewById(R.id.editName);
        editLastName = findViewById(R.id.editLastName);
        editAge = findViewById(R.id.editAge);
        editPhone = findViewById(R.id.editPhone);
    }

    public void clearInputs() {
        editCode.setText("");
        editName.setText("");
        editLastName.setText("");
        editAge.setText("");
        editPhone.setText("");
    }

    public void addPerson(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String code = editCode.getText().toString();
        String name = editName.getText().toString();
        String lastName = editLastName.getText().toString();
        String age = editAge.getText().toString();
        String phone = editPhone.getText().toString();

        ContentValues register = new ContentValues();
        register.put("codigo", code);
        register.put("nombre", name);
        register.put("apellido", lastName);
        register.put("edad", age);
        register.put("telefono", phone);


        if(!code.equals("") && !name.equals("") && !lastName.equals("") && !age.equals("") && !phone.equals("")) {
            long result = db.insert("personas", null, register);

            if(!(result == -1)) {
                clearInputs();
                Toast.makeText(this, "La persona se ha agregado correctamente", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "El codigo que ha ingresado ya existe", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void queryByCode(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String code = editCode.getText().toString();

        if(!code.equals("")) {
            Cursor row = db.rawQuery("SELECT nombre, apellido, edad, telefono FROM personas WHERE codigo=" + code, null);
            if(row.moveToFirst()) {
                editName.setText(row.getString(0));
                editLastName.setText(row.getString(1));
                editAge.setText(row.getString(2));
                editPhone.setText(row.getString(3));
            }
            else {
                clearInputs();
                editCode.setText(code);
                Toast.makeText(this, "No existe una persona con ese código", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Debe colocar un código", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void queryByName(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String name = editName.getText().toString();

        if(!name.equals("")) {
            Cursor row = db.rawQuery("SELECT codigo, apellido, edad, telefono FROM personas WHERE nombre='" + name + "'", null);
            if(row.moveToFirst()) {
                editCode.setText(row.getString(0));
                editLastName.setText(row.getString(1));
                editAge.setText(row.getString(2));
                editPhone.setText(row.getString(3));
            }
            else {
                clearInputs();
                editName.setText(name);
                Toast.makeText(this, "No existe una persona con ese nombre", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Debe colocar un nombre", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void deletePerson(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String code = editCode.getText().toString();

        if(!code.equals("")) {
            int result = db.delete("personas", "codigo=" + code, null);

            clearInputs();

            if(result == 1) {
                Toast.makeText(this, "La persona se ha borrado correctamente", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "El código de la persona no existe", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Debe colocar un código", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void modifyPerson(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String code = editCode.getText().toString();
        String name = editName.getText().toString();
        String lastName = editLastName.getText().toString();
        String age = editAge.getText().toString();
        String phone = editPhone.getText().toString();

        ContentValues register = new ContentValues();
        register.put("codigo", code);
        register.put("nombre", name);
        register.put("apellido", lastName);
        register.put("edad", age);
        register.put("telefono", phone);

        if(!code.equals("") && !name.equals("") && !lastName.equals("") && !age.equals("") && !phone.equals("")) {
            int result = db.update("personas", register, "codigo=" + code, null);

            if(result == 1) {
                clearInputs();

                Toast.makeText(this, "La persona se ha modificado correctamente", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "El código de la persona no existe", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}