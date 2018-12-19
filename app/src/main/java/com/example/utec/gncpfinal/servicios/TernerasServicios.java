package com.example.utec.gncpfinal.servicios;

        import com.example.utec.gncpfinal.entidades.Ternera;
        import java.util.List;
        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Path;

public interface TernerasServicios {


    String API_ROUTE = "PLTIGNC-APPWEB-JSF/rest/terneras";

    @GET(API_ROUTE)
    Call<List<Ternera>> getTerneras();

    @GET(API_ROUTE+"/ternera/{idTernera}")
    Call<Ternera> getTernera(@Path("idTernera") Long idTernera);

}
