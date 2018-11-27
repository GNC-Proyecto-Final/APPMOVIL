package com.example.utec.gncpfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.EnfermedadTernera;
import com.example.utec.gncpfinal.entidades.EnfermedadTerneraPK;
import com.example.utec.gncpfinal.servicios.EnfermedadTerneraServicios;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TernerasEnfermas extends AppCompatActivity {

    private ListView listView;

    ArrayList<String> listaInformacion = new ArrayList<String>();
    ArrayList<EnfermedadTernera> listaEnfermedadTerneras = new ArrayList<EnfermedadTernera>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terneras_enfermas);

        listView = (ListView) findViewById(R.id.listView);
        getEnfermedadTerneras();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="id: "+listaEnfermedadTerneras.get(pos).getId().getIdEnfermedad()+"\n";
                informacion+="Grado de Enfermedad: "+ listaEnfermedadTerneras.get(pos).getObservacion()+"\n";

                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

            }
        });

    }

    private void getEnfermedadTerneras() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8180/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EnfermedadTerneraServicios service = retrofit.create(EnfermedadTerneraServicios.class);
        Call<List<EnfermedadTernera>> call =  service.getEnfermedadTerneras();

        call.enqueue(new Callback<List<EnfermedadTernera>>() {
            @Override
            public void onResponse(Call<List<EnfermedadTernera>> call, Response<List<EnfermedadTernera>> response) {

                EnfermedadTernera enfermTerneras =null;

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                for(EnfermedadTernera enfermTernera: response.body()) {



                    EnfermedadTerneraPK enfTerPk = new EnfermedadTerneraPK();

                    enfTerPk.setIdTernera(enfermTernera.getTernera().getIdTernera());
                    enfTerPk.setIdEnfermedad(enfTerPk.getIdEnfermedad());
                    enfTerPk.setFechaDesde(enfermTernera.getId().getFechaDesde());

                    enfermTerneras = new EnfermedadTernera();
                    enfermTerneras.setId(enfTerPk);
                    enfermTernera.setEnfermedad(enfermTernera.getEnfermedad());
                    enfermTerneras.setTernera(enfermTernera.getTernera());


                    listaEnfermedadTerneras.add(enfermTernera);
                }
                obtenerLista();
            }


            @Override
            public void onFailure(Call<List<EnfermedadTernera>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
    public void obtenerLista() {

        for (int i = 0; i < listaEnfermedadTerneras.size(); i++) {
            listaInformacion.add(listaEnfermedadTerneras.get(i).getEnfermedad().getIdEnfermedad() + " - "
                    + listaEnfermedadTerneras.get(i).getTernera().getIdTernera() + " - "
                    + listaEnfermedadTerneras.get(i).getId().getFechaDesde() + " - "
                    + listaEnfermedadTerneras.get(i).getObservacion()
            );

        }
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listView.setAdapter(adaptador);

    }
}