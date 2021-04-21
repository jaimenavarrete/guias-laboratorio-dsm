package com.udb.dsm.octavaapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.udb.dsm.octavaapp.modelos.Persona;

import java.util.List;

public class AdaptadorPersona extends ArrayAdapter<Persona> {
    List<Persona> listaPersonas;
    private Activity context;

    public AdaptadorPersona(@NonNull Activity context, @NonNull List<Persona> listaPersonas) {
        super(context, R.layout.persona_layout, listaPersonas);

        this.context = context;
        this.listaPersonas = listaPersonas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview = null;

        if(view == null) {
            rowview = layoutInflater.inflate(R.layout.persona_layout, null);
        }
        else {
            rowview = view;
        }

        TextView textDui = rowview.findViewById(R.id.textDui);
        TextView textNombre = rowview.findViewById(R.id.textNombre);
        TextView textFechaNacimiento = rowview.findViewById(R.id.textFechaNacimiento);
        TextView textGenero = rowview.findViewById(R.id.textGenero);
        TextView textPeso = rowview.findViewById(R.id.textPeso);
        TextView textAltura = rowview.findViewById(R.id.textAltura);

        textDui.setText("DUI: " + listaPersonas.get(position).getDui());
        textNombre.setText("Nombre: " + listaPersonas.get(position).getNombre());
        textFechaNacimiento.setText("Fecha de nacimiento: " + listaPersonas.get(position).getFechaNacimiento());
        textGenero.setText("Género: " + listaPersonas.get(position).getGenero());
        textPeso.setText("Peso: " + listaPersonas.get(position).getPeso());
        textAltura.setText("Altura: " + listaPersonas.get(position).getAltura());

        return rowview;
    }
}
