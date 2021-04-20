package com.udb.dsm.octavaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.udb.dsm.octavaapp.modelos.Persona;

import java.util.ArrayList;
import java.util.List;

public class PersonasActivity extends AppCompatActivity {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refPersonas = database.getReference("personas");

    Query consultaOrdenada = refPersonas.orderByChild("nombre");

    List<Persona> personas;
    ListView listPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas);

        initialize();
    }

    private void initialize() {
        FloatingActionButton fabAdd= findViewById(R.id.fabAdd);
        listPersonas = findViewById(R.id.listPersonas);

        listPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), AddPersonaActivity.class);

                intent.putExtra("accion","e");
                intent.putExtra("key", personas.get(i).getKey());
                intent.putExtra("dui",personas.get(i).getDui());
                intent.putExtra("nombre",personas.get(i).getNombre());
                intent.putExtra("fechaNacimiento",personas.get(i).getFechaNacimiento());
                intent.putExtra("genero",personas.get(i).getGenero());
                intent.putExtra("peso",personas.get(i).getPeso());
                intent.putExtra("altura",personas.get(i).getAltura());
                startActivity(intent);
            }
        });

        listPersonas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder alert = new AlertDialog.Builder(PersonasActivity.this);
                alert.setMessage("¿Está seguro que desea eliminar el registro?")
                        .setTitle("Confirmación");

                alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PersonasActivity.refPersonas
                                .child(personas.get(position).getKey()).removeValue();

                        Toast.makeText(PersonasActivity.this,
                                "¡Registro borrado!",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(PersonasActivity.this,
                                "¡Operación de borrado cancelada!",Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

                return true;
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cuando el usuario quiere agregar un nuevo registro
                Intent i = new Intent(getBaseContext(), AddPersonaActivity.class);
                i.putExtra("accion","a"); // Agregar
                i.putExtra("key","");
                i.putExtra("nombre","");
                i.putExtra("dui","");
                startActivity(i);
            }
        });

        personas = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                personas.removeAll(personas);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Persona persona = dato.getValue(Persona.class);
                    persona.setKey(dato.getKey());
                    personas.add(persona);
                }

                AdaptadorPersona adapter = new AdaptadorPersona(PersonasActivity.this,
                        personas );
                listPersonas.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}