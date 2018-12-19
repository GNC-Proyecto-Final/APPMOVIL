package com.example.utec.gncpfinal.servicios;

        import com.example.utec.gncpfinal.entidades.Enfermedad;
        import com.example.utec.gncpfinal.entidades.Usuario;


        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.DELETE;
        import retrofit2.http.GET;
        import retrofit2.http.POST;
        import retrofit2.http.Path;

public interface EnfermedadesServicios {


    String API_ROUTE = "PLTIGNC-APPWEB-JSF/rest/enfermedades";

    @GET(API_ROUTE)
    Call<List<Enfermedad>> getEnfermedades();

    @POST(API_ROUTE)
    Call<Void> addEnfermedad(@Body Enfermedad enfermedad);

    @POST(API_ROUTE + "/enfermedadExiste")
    Call<Boolean> existeEnfermedad(@Body Enfermedad enfermedad);

    @GET(API_ROUTE+"/enfermedad/{idEnfermedad}")
    Call<Enfermedad> getEnfermedad(@Path("idEnfermedad") Long idEnfermedad);


    @DELETE(API_ROUTE + "/delete/{idEnfermedad}")
    Call<Boolean> deleteEnfermedad(@Path("idEnfermedad") Long idEnfermedad);
}
