package com.example.utec.gncpfinal;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.Enfermedad;
import com.example.utec.gncpfinal.entidades.Ternera;
import com.example.utec.gncpfinal.servicios.RestClient;
import com.example.utec.gncpfinal.servicios.TernerasServicios;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DialogoTerneras {

    ArrayList<String> listaInformacion = new ArrayList<String>();
    ArrayList<Ternera> listaTerneras = new ArrayList<Ternera>();
    private ListView listView;
    final Dialog dialogo;

    public interface FinDialogoTernera{
        void resultadoDialogoTernera(long idTernera);
    }

    private FinDialogoTernera interfaz;

    public DialogoTerneras(Context contexto, FinDialogoTernera actividad) {

        interfaz = actividad;
        dialogo = new Dialog(contexto);

        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialogo.setTitle("Listado de Enfermedades");
        dialogo.setCancelable(false);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.dialogo_terneras);

        getTerneras();
        listView =(ListView) dialogo.findViewById(R.id.listViewTerneras);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="id: "+listaTerneras.get(pos).getIdTernera()+"\n";
                informacion+="Nro Caravana: "+listaTerneras.get(pos).getNroCaravana()+"\n";
                informacion+="Grado de Enfermedad: "+ listaTerneras.get(pos).getFechaNacimiento()+"\n";

                Toast.makeText(dialogo.getContext(),informacion,Toast.LENGTH_LONG).show();
                interfaz.resultadoDialogoTernera(listaTerneras.get(pos).getIdTernera());
                dialogo.dismiss();

            }
        });


        dialogo.show();
    }


    private void getTerneras() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestClient.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TernerasServicios service = retrofit.create(TernerasServicios.class);
        Call<List<Ternera>> call = service.getTerneras();

        call.enqueue(new Callback<List<Ternera>>() {
            @Override
            public void onResponse(Call<List<Ternera>> call, Response<List<Ternera>> response) {

                Ternera terne = null;

                for(Ternera ternera: response.body()) {

                    terne = new Ternera();
                    terne.setIdTernera(ternera.getIdTernera());
                    terne.setNroCaravana(ternera.getNroCaravana());
                    terne.setFechaNacimiento(ternera.getFechaNacimiento());
                    listaTerneras.add(terne);
                }
                obtenerLista();
            }


            @Override
            public void onFailure(Call<List<Ternera>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
    public void obtenerLista() {

        for (int i = 0; i < listaTerneras.size(); i++) {
            listaInformacion.add(listaTerneras.get(i).getIdTernera() + " - "
                    + listaTerneras.get(i).getNroCaravana() + " - "
                    + listaTerneras.get(i).getFechaNacimiento()
            );

        }
        ArrayAdapter adaptador = new ArrayAdapter(dialogo.getContext(),R.layout.listview,listaInformacion);
        //listViewEnfermedades.setAdapter(adaptador);
        listView.setAdapter(adaptador);
    }
}
