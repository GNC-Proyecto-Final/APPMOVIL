package com.example.utec.gncpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.Enfermedad;
import com.example.utec.gncpfinal.servicios.EnfermedadesServicios;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Enfermedades extends AppCompatActivity {

    private ListView listView;
    private Button buttonAnterior,buttonSiguiente;

    ArrayList<String> listaInformacion = new ArrayList<String>();
    ArrayList<Enfermedad> listaEnfermedades = new ArrayList<Enfermedad>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfermedades);

        listView = (ListView) findViewById(R.id.listView);
        getEnfermedades();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="id: "+listaEnfermedades.get(pos).getIdEnfermedad()+"\n";
                informacion+="Nombre: "+listaEnfermedades.get(pos).getNombre()+"\n";
                informacion+="Grado de Enfermedad: "+ listaEnfermedades.get(pos).getGradoGravedad()+"\n";

                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();

            }
        });

    }

    private void getEnfermedades() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8180/")
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
}
