package com.example.utec.gncpfinal.servicios;

import com.example.utec.gncpfinal.entidades.Enfermedad;
import com.example.utec.gncpfinal.entidades.EnfermedadTernera;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EnfermedadTerneraServicios {
    String API_ROUTE = "PLTIGNC-APPWEB-JSF/rest/enfermedadTerneras";

    @GET(API_ROUTE)
    Call<List<EnfermedadTernera>> getEnfermedadTerneras();

    @GET(API_ROUTE +"/informe" )
    Call<List<EnfermedadTernera>> getObtenerInformeTodasEnfermedadesTerneras();

    @POST(API_ROUTE)
    Call<Void> addEnfermedadTernera(@Body EnfermedadTernera enfermedadTernera);

    @PUT(API_ROUTE)
    Call<Void> updateEnfermedadTernera(@Body EnfermedadTernera enfermedadTernera);

    @POST(API_ROUTE + "/existe/{idEnfermedad}")
    Call<Boolean> existeEnfermedadEnEnfermedadTernera(@Path("idEnfermedad") Long idEnfermedad);

    @POST(API_ROUTE + "/existe")
    Call<Boolean> obtenerTerneraEnfermaExiste(@Body EnfermedadTernera enfermedadTernera);

}

