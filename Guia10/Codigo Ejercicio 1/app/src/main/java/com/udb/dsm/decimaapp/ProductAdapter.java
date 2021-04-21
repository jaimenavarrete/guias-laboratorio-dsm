package com.udb.dsm.decimaapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.udb.dsm.decimaapp.databinding.ItemProductBinding;
import com.udb.dsm.decimaapp.interfaces.Service;
import com.udb.dsm.decimaapp.modelos.Producto;
import com.udb.dsm.decimaapp.modelos.Respuesta;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Producto> products;
    private Activity context;

    public ProductAdapter(List<Producto> products, Activity context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(products.get(position) );
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private ItemProductBinding itemProductoBinding;

        public ProductViewHolder(View view) {
            super(view);
            itemProductoBinding = ItemProductBinding.bind(view);
        }

        public void bind(Producto producto) {
            itemProductoBinding.textCodigo.setText(     "Código      : "  + producto.getCodigo());
            itemProductoBinding.textDescripcion.setText("Descripción : "  + producto.getDescripcion());
            itemProductoBinding.textPrecio.setText(     "Precio      : "  + producto.getPrecio());

            itemProductoBinding.cardProducto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AddActivity.class);
                    intent.putExtra("codigo",producto.getCodigo());
                    intent.putExtra("descripcion",producto.getDescripcion());
                    intent.putExtra("precio",producto.getPrecio());
                    intent.putExtra("accion","e");
                    context.startActivity(intent);
                }
            });

            itemProductoBinding.cardProducto.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String title = "Consulta";
                    String message = "Desea borrar el producto?";
                    String strbt1 = "Si"; String strbt2 = "No";

                    AlertDialog.Builder ad = new AlertDialog.Builder(context);
                    ad.setTitle(title);	ad.setIcon(android.R.drawable.star_big_on);
                    ad.setMessage(message);
                    ad.setPositiveButton(strbt1, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            deleteProduct(producto.getCodigo());
                        }
                    });
                    ad.setNegativeButton(strbt2, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            Toast.makeText(context, "Eliminación cancelada", Toast.LENGTH_LONG).show();
                        }
                    }).setIcon(android.R.drawable.star_on);

                    ad.show();

                    return true;
                }
            });
        }

        private void deleteProduct(String code) {
            Call<Respuesta> call = Service.service.deleteProduct(code);

            call.enqueue(new Callback<Respuesta>() {
                @Override
                public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                    Respuesta r = response.body();
                    if (r.getResultado()==1) {
                        MainActivity.showAllProducts(context);
                        Toast.makeText(context, "El registro se ha borrado correctamente",
                                Toast.LENGTH_LONG).show();
                    } else {Toast.makeText(context, "El registro NO se ha podido borrar",
                            Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<Respuesta> call, Throwable t) {
                    Toast.makeText(context, "Error:" + t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
