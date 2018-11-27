package com.example.utec.gncpfinal.servicios;

import com.example.utec.gncpfinal.entidades.Enfermedad;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EnfermedadesServicios {


    String API_ROUTE = "PLTIGNC-APPWEB-JSF/rest/enfermedades";

    @GET(API_ROUTE)
    Call<List<Enfermedad>> getEnfermedades();

}
