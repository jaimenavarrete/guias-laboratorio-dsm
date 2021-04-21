package com.udb.dsm.decimaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.udb.dsm.decimaapp.databinding.ActivityMainBinding;
import com.udb.dsm.decimaapp.interfaces.Service;
import com.udb.dsm.decimaapp.modelos.Producto;
import com.udb.dsm.decimaapp.modelos.RespProducto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ActivityMainBinding binding;
    static public ProductAdapter productAdapter;
    static public List<Producto> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeInterface();
        showAllProducts(MainActivity.this);

        binding.searchProduct.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        showAllProducts(MainActivity.this);
    }

    private void initializeInterface() {
        productAdapter = new ProductAdapter(products, this);
        binding.recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewProducts.setAdapter(productAdapter);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("codigo","");
                intent.putExtra("descripcion","");
                intent.putExtra("precio","");
                intent.putExtra("accion","a");
                startActivity(intent);
            }
        });
    }

    static public void showAllProducts(Context context) {
        Call<List<Producto>> call = Service.service.getProducts();
        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.code() == 200) {
                    products.clear();
                    List<Producto> prods = response.body();
                    products.addAll(prods);
                    productAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(context,"Error:" + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(context,"Error:" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showByCode(String code) {
        final Call<RespProducto> call = Service.service.getProductById(code);

        call.enqueue(new Callback<RespProducto>() {
            @Override
            public void onResponse(Call<RespProducto> call, Response<RespProducto> response) {
                if (response.code()==200) {

                    RespProducto product = response.body();
                    if (product.getResultado()==null) {
                        Toast.makeText(MainActivity.this,"El código no existe",
                                Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        products.clear();

                        Producto prod = response.body().getResultado();
                        products.add(prod);
                        productAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<RespProducto> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!query.trim().equals("")) {
            showByCode(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.trim().equals("")) showAllProducts(MainActivity.this);
        return true;
    }
}