package com.example.utec.gncpfinal;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

public class DialogoEnfermedades {

    ArrayList<String> listaInformacion = new ArrayList<String>();
    ArrayList<Enfermedad> listaEnfermedades = new ArrayList<Enfermedad>();
    private ListView listView;
    final Dialog dialogo;

    public interface FinDialogo{
        void resultadoDialogo(long idEnfermedad);
    }

    private FinDialogo interfaz;

    public  DialogoEnfermedades(Context contexto,FinDialogo actividad) {

        interfaz = actividad;
        dialogo = new Dialog(contexto);

        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialogo.setTitle("Listado de Enfermedades");
        dialogo.setCancelable(false);
        //dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogo.setContentView(R.layout.dialogo_enfermedades);

        getEnfermedades();
        listView =(ListView) dialogo.findViewById(R.id.listViewEnfermedades);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="id: "+listaEnfermedades.get(pos).getIdEnfermedad()+"\n";
                informacion+="Nombre: "+listaEnfermedades.get(pos).getNombre()+"\n";
                informacion+="Grado de Enfermedad: "+ listaEnfermedades.get(pos).getGradoGravedad()+"\n";

                Toast.makeText(dialogo.getContext(),informacion,Toast.LENGTH_LONG).show();
                interfaz.resultadoDialogo(listaEnfermedades.get(pos).getIdEnfermedad());
                dialogo.dismiss();

            }
        });


        dialogo.show();
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
        ArrayAdapter adaptador = new ArrayAdapter(dialogo.getContext(),R.layout.listview,listaInformacion);
        //listViewEnfermedades.setAdapter(adaptador);
        listView.setAdapter(adaptador);
    }
}
