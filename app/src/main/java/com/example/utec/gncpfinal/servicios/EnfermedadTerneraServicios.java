package com.example.utec.gncpfinal.servicios;

import com.example.utec.gncpfinal.entidades.EnfermedadTernera;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EnfermedadTerneraServicios {
    String API_ROUTE = "PLTIGNC-APPWEB-JSF/rest/enfermedadTerneras";

    @GET(API_ROUTE)
    Call<List<EnfermedadTernera>> getEnfermedadTerneras();

}

