package com.example.utec.gncpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.Enfermedad;
import com.example.utec.gncpfinal.servicios.EnfermedadesServicios;
import com.example.utec.gncpfinal.servicios.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Enfermedades extends AppCompatActivity {

    private ListView listView;
    private RadioButton radio_visualizar_enfermedad;
    private RadioButton radio_eliminar_enfermedad;
    private ImageView atras,home;


    ArrayList<String> listaInformacion = new ArrayList<String>();
    ArrayList<Enfermedad> listaEnfermedades = new ArrayList<Enfermedad>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades);


        radio_visualizar_enfermedad=(RadioButton)findViewById(R.id.radio_visualizar_enfermedad);
        radio_eliminar_enfermedad=(RadioButton)findViewById(R.id.radio_eliminar_enfermedad);
        atras =(ImageView)findViewById(R.id.imgBtnLisTerEnfBack);
        home =(ImageView)findViewById(R.id.imgBtnLisTerEnfHome);
        listView = (ListView) findViewById(R.id.listView);
        getEnfermedades();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                if(radio_visualizar_enfermedad.isChecked()){

                    Enfermedad enf = listaEnfermedades.get(pos);
                    visualizarEnfermedad(enf);


                }else if(radio_eliminar_enfermedad.isChecked()){
                   Enfermedad enf = listaEnfermedades.get(pos);
                    eliminarEnfermedad(enf);
                }


            }
        });

    }

    private void getEnfermedades() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestClient.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EnfermedadesServicios service = retrofit.create(EnfermedadesServicios.class);
        Call<List<Enfermedad>> call = service.getEnfermedades();

        call.enqueue(new Callback<List<Enfermedad>>() {
            @Override
            public void onResponse(Call<List<Enfermedad>> call, Response<List<Enfermedad>> response) {

                Enfermedad enferm =null;

                for(Enfermedad enfermedad: response.body()) {

                        enferm = new Enfermedad();
                        enferm.setIdEnfermedad(enfermedad.getIdEnfermedad());
                        enferm.setNombre(enfermedad.getNombre());
                        enferm.setGradoGravedad(enfermedad.getGradoGravedad());

                        listaEnfermedades.add(enferm);
                }
                obtenerLista();
            }


            @Override
            public void onFailure(Call<List<Enfermedad>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
    public void obtenerLista() {

        for (int i = 0; i < listaEnfermedades.size(); i++) {
            listaInformacion.add(listaEnfermedades.get(i).getIdEnfermedad() + " - "
                    + listaEnfermedades.get(i).getNombre() + " - "
                    + listaEnfermedades.get(i).getGradoGravedad()
            );

        }
      ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
      listView.setAdapter(adaptador);

    }

    public void visualizarEnfermedad(Enfermedad enf){

        Intent intent = new Intent(Enfermedades.this,VisualizarEnfermedad.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("enfermedad",enf);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void eliminarEnfermedad(Enfermedad enf){

        Intent intent = new Intent(Enfermedades.this,EliminarEnfermedad.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("enfermedad",enf);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void volverBtnLisTerEnBack(View view){
        Intent intent = new Intent(Enfermedades.this, ListadoMenu.class);
        startActivity(intent);
    }


    public void homeBtnLisTerEnBack(View view){
        Intent intent = new Intent(Enfermedades.this, MenuActivity.class);
        startActivity(intent);
    }
}
