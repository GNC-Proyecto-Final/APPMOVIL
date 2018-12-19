package com.example.utec.gncpfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.EnfermedadTernera;
import com.example.utec.gncpfinal.entidades.EnfermedadTerneraPK;
import com.example.utec.gncpfinal.servicios.EnfermedadTerneraServicios;
import com.example.utec.gncpfinal.servicios.RestClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InformeTernerasEnfermas extends AppCompatActivity {

    private ListView listView;
    private RadioButton radio_visualizar_enfermedad_ternera;
    private RadioButton radio_editar_enfermedad_ternera;
    private ImageButton atras,home;


    ArrayList<String> listaInformacion = new ArrayList<String>();
    ArrayList<EnfermedadTernera> listaEnfermedadTerneras = new ArrayList<EnfermedadTernera>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe_terneras_enfermas);

        listView = (ListView) findViewById(R.id.listView);
        radio_visualizar_enfermedad_ternera=(RadioButton)findViewById(R.id.radio_visualizar_enfermedad_ternera);
        radio_editar_enfermedad_ternera=(RadioButton)findViewById(R.id.radio_editar_enfermedad_ternera);
        atras =(ImageButton)findViewById(R.id.imgBtnAtras);

        getEnfermedadTerneras();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                    EnfermedadTernera ternEnfer = listaEnfermedadTerneras.get(pos);
                    visualizarInfomeTernerasEnfermas(ternEnfer);

            }
        });

    }

    private void getEnfermedadTerneras() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestClient.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EnfermedadTerneraServicios service = retrofit.create(EnfermedadTerneraServicios.class);
        Call<List<EnfermedadTernera>> call =  service.getObtenerInformeTodasEnfermedadesTerneras();

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

            Date fecha = null;

            if(listaEnfermedadTerneras.get(i).getFechaHasta()==null){
                fecha = listaEnfermedadTerneras.get(i).getFechaHasta();
            }else{
                fecha = listaEnfermedadTerneras.get(i).getFechaHasta();
            }

            listaInformacion.add(
                listaEnfermedadTerneras.get(i).getTernera().getIdTernera()+ " - " +
                listaEnfermedadTerneras.get(i).getEnfermedad().getNombre().getNombre()+ " - " +
                listaEnfermedadTerneras.get(i).getTernera().getFechaNacimiento()+ " - " +
                listaEnfermedadTerneras.get(i).getId().getFechaDesde()+ " - " +
                fecha+ " - " +
                listaEnfermedadTerneras.get(i).getDiasVida()



            );


        }
        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listView.setAdapter(adaptador);

    }

    public void visualizarInfomeTernerasEnfermas(EnfermedadTernera enf){

        Intent intent = new Intent(InformeTernerasEnfermas.this,VisualizarInformeTerneraEnferma.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("enfermedadTerneras",enf);
        intent.putExtras(bundle);
        startActivity(intent);

    }
    public void homeEnfermedad(View view){
        Intent intent = new Intent(InformeTernerasEnfermas.this, MenuActivity.class);
        startActivity(intent);
    }

    public void volverEnfermedad(View view){
        Intent intent = new Intent(InformeTernerasEnfermas.this, ListadoMenu.class);
        startActivity(intent);
    }
}