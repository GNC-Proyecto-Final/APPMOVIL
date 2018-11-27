package com.example.utec.gncpfinal.servicios;

import com.example.utec.gncpfinal.entidades.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuariosService {

    String API_ROUTE = "PLTIGNC-APPWEB-JSF/rest/usuarios";

    @GET(API_ROUTE)
    Call<Usuario> getUsuarios();

    @POST(API_ROUTE + "/{user}/{password}")
    Call<Usuario> getUsuariologin(@Path("user") String user, @Path("password") String password);
}
