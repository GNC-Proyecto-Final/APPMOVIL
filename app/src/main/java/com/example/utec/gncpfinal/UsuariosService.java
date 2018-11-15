package com.example.utec.gncpfinal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuariosService {

    String API_ROUTE = "PLTIGNC-APPWEB-JSF/rest/usuarios";

    @GET(API_ROUTE)
    Call<Usuario> getUsuarios();
}
