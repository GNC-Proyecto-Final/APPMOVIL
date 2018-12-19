package com.example.utec.gncpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.Enfermedad;
import com.example.utec.gncpfinal.entidades.EnfermedadTernera;
import com.example.utec.gncpfinal.entidades.EnfermedadTerneraPK;
import com.example.utec.gncpfinal.servicios.EnfermedadTerneraServicios;
import com.example.utec.gncpfinal.servicios.RestClient;


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
    private RadioButton radio_visualizar_enfermedad_ternera;
    private RadioButton radio_editar_enfermedad_ternera;

    ArrayList<String> listaInformacion = new ArrayList<String>();
    ArrayList<EnfermedadTernera> listaEnfermedadTerneras = new ArrayList<EnfermedadTernera>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terneras_enfermas);

        listView = (ListView) findViewById(R.id.listView);
        radio_visualizar_enfermedad_ternera=(RadioButton)findViewById(R.id.radio_visualizar_enfermedad_ternera);
        radio_editar_enfermedad_ternera=(RadioButton)findViewById(R.id.radio_editar_enfermedad_ternera);
        getEnfermedadTerneras();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                if(radio_visualizar_enfermedad_ternera.isChecked()){
                                        EnfermedadTernera ternEnfer = listaEnfermedadTerneras.get(pos);
                    visualizarTernerasEnfermas(ternEnfer);

                }else if(radio_editar_enfermedad_ternera.isChecked()){

                    EnfermedadTernera ternEnfer = listaEnfermedadTerneras.get(pos);
                    editarTernerasEnfermas(ternEnfer);

                }

            }
        });

    }

    private void getEnfermedadTerneras() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestClient.getBaseUrl())
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

    public void visualizarTernerasEnfermas(EnfermedadTernera enf){

        Intent intent = new Intent(TernerasEnfermas.this,VisualizarTerneraEnferma.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("enfermedadTerneras",enf);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void editarTernerasEnfermas(EnfermedadTernera enf){

        Intent intent = new Intent(TernerasEnfermas.this,EditarTerneraEnferma.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("enfermedadTerneras",enf);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void regresoTerneraEnferma(View view){
        Intent intent = new Intent(TernerasEnfermas.this, ListadoMenu.class);
        startActivity(intent);
    }

    public void homeTerneraEnferma(View view){
        Intent intent = new Intent(TernerasEnfermas.this, MenuActivity.class);
        startActivity(intent);
    }


}