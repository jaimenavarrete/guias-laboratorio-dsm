package com.udb.dsm.decimaapp.interfaces;

import com.udb.dsm.decimaapp.modelos.Producto;
import com.udb.dsm.decimaapp.modelos.RespProducto;
import com.udb.dsm.decimaapp.modelos.Respuesta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface APIService {
    @GET(".")
    Call<List<Producto>> getProducts();

    @GET()
    Call<RespProducto> getProductById(@Url String url);

    @POST("agrega")
    Call<Respuesta> insertProduct(@Body Producto producto);

    @DELETE()
    Call<Respuesta> deleteProduct(@Url String url);

    @PUT()
    Call<Respuesta> updateProduct(@Url String url, @Body Producto producto);
}
