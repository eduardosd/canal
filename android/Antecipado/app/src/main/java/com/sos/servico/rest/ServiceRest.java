package com.sos.servico.rest;

import com.sos.servico.rest.pojo.Cartao;
import com.sos.servico.rest.pojo.Cliente;
import com.sos.servico.rest.pojo.Item;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ServiceRest {

    @GET("/getClient/{email}/{password}")
    public void getClient(@Path("email") String email, @Path("password") String password, Callback<Cliente> callback);

    @GET("/getCartao/{email}/{password}/cartaoId")
    public void getCartao(Cliente cliente, @Path("email") String email, @Path("password") String password, @Path("cartaoId") String cartaoId, Callback<Cartao> callback);

    @GET("/putMoney/{email}/{qrCode}")
    public void putMoney(@Path("email") String email, @Path("qrCode") String qrCode, Callback<String> callback);

    @GET("/registerClient/{nome}/{email}/{password}/{sexo}")
    public void registerClient(@Path("nome") String nome, @Path("email") String email, @Path("password") String password, @Path("sexo") String sexo, Callback<Cliente> callback);

    @GET("/getAvailableItens/")
    public void getAvailableItens(Callback<List<Item>> callback);
}
