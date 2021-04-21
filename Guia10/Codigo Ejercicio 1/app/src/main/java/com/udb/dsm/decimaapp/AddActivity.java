package com.udb.dsm.decimaapp;

import androidx.appcompat.app.AppCompatActivity;

import com.udb.dsm.decimaapp.databinding.ActivityAddBinding;
import com.udb.dsm.decimaapp.interfaces.Service;
import com.udb.dsm.decimaapp.modelos.Producto;
import com.udb.dsm.decimaapp.modelos.Respuesta;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;
    String action="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeInterface();
    }

    private void initializeInterface(){
        Bundle data = getIntent().getExtras();

        binding.editCodigo.setText(data.getString("codigo",""));
        binding.editDescripcion.setText(data.getString("descripcion",""));
        binding.editPrecio.setText("" + data.getFloat("precio",0.00f));

        action = data.getString("accion","a");

        if (action.equals("e"))
            binding.editCodigo.setEnabled(false);
    }


    public void save(View view) {
        if (!validate()) return;

        if (action.equals("a"))
            addProduct();
        else
            updateProduct();

    }

    private boolean validate() {
        String codigo = binding.editCodigo.getText().toString().trim();
        String descripcion = binding.editDescripcion.getText().toString().trim();
        float precio = Float.valueOf(binding.editPrecio.getText().toString().trim());

        if (codigo.equals("")) {
            binding.editCodigo.setError("Por favor digite un código");
            return false;
        }
        if (descripcion.equals("")) {
            binding.editDescripcion.setError("Por favor digite una descripción");
            return false;
        }
        if (precio <= 0.00f) {
            binding.editPrecio.setError("El precio debe ser mayor a $0.00");
            return false;
        }
        return true;
    }

    public void addProduct() {
        String codigo = binding.editCodigo.getText().toString().trim();
        String descripcion =binding.editDescripcion.getText().toString().trim();
        float precio = Float.valueOf(binding.editPrecio.getText().toString().trim());

        Producto producto = new Producto(codigo,descripcion,precio);

        Call<Respuesta> call  = Service.service.insertProduct(producto);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if (response.code()==200) {
                    Toast.makeText(getBaseContext(),"El producto se ha agregado correctamente",
                            Toast.LENGTH_LONG ).show();
                    finish();
                } else
                {
                    Toast.makeText(getBaseContext(),"Error : " + response.code(),
                            Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Error : " + t.getMessage(),
                        Toast.LENGTH_LONG ).show();
            }
        });
    }

    public void updateProduct() {
        String codigo =  binding.editCodigo.getText().toString().trim();
        String descripcion = binding.editDescripcion.getText().toString().trim();
        float precio = Float.valueOf(binding.editPrecio.getText().toString().trim());

        Producto producto = new Producto(codigo,descripcion,precio);

        Call<Respuesta> call = Service.service.updateProduct(codigo,producto);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                if (respuesta.getResultado()==1) {
                    Toast.makeText(getBaseContext(),"El producto se ha actualizado correctamente",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getBaseContext(),"Error:" +response.code(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(getBaseContext(),"Error:" + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void cancel(View view) {
        finish();
    }
}