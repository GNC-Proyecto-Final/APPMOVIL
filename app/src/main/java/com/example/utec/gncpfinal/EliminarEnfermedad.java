package com.example.utec.gncpfinal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.Enfermedad;
import com.example.utec.gncpfinal.enumerados.NombreEnfermedad;
import com.example.utec.gncpfinal.servicios.EnfermedadTerneraServicios;
import com.example.utec.gncpfinal.servicios.EnfermedadesServicios;
import com.example.utec.gncpfinal.servicios.RestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EliminarEnfermedad extends AppCompatActivity {

    private TextView nombre,gravedad;
    private Button regresar;
    private boolean existe;
    long idEnfermedad=0;
    Enfermedad enfermedad;
    Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_enfermedad);

        nombre = (TextView) findViewById(R.id.textViewElEnNombre);
        gravedad = (TextView) findViewById(R.id.textViewElEnGravedad);

        regresar = (Button) findViewById(R.id.buttonEnfRegresar);

        Bundle objetoEnviado = getIntent().getExtras();

        if (objetoEnviado != null){
            enfermedad = (Enfermedad) objetoEnviado.getSerializable("enfermedad");
            nombre.setText(enfermedad.getNombre().getNombre());
            gravedad.setText((Long.toString(enfermedad.getGradoGravedad())));
            idEnfermedad = enfermedad.getIdEnfermedad();
        }
        contexto = this;
    }
    public void enfermedadExisteTerneraEnferma(){

        if(idEnfermedad==0 ){
            Toast.makeText(getApplicationContext(), "Debe ingresar un enfermedad. Cero no permitido", Toast.LENGTH_LONG).show();

        }else {

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestClient.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            EnfermedadTerneraServicios service = retrofit.create(EnfermedadTerneraServicios.class);
            Call<Boolean> call = service.existeEnfermedadEnEnfermedadTernera(idEnfermedad);

            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {


                    existe = response.body();
                    if (existe) {
                        Toast.makeText(getApplicationContext(), "La Enfermedad por Ternera ya se encuentra registrada..", Toast.LENGTH_LONG).show();
                    }
                    else{

                        eliminar();
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }

    }
    public void eliminar(){
         if(enfermedad!=null){

            try {
                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd")
                        .create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RestClient.getBaseUrl())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                EnfermedadesServicios serviceAdd = retrofit.create(EnfermedadesServicios.class);
                Call<Boolean> call = serviceAdd.deleteEnfermedad(enfermedad.getIdEnfermedad());

                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        System.out.println(response.message()+ " "+response.body());

                        if (response.body()) {
                            Toast.makeText(getApplicationContext(), "La Enfermedad por Ternera se ha sido eliminada con Exito.", Toast.LENGTH_LONG).show();
                            cerrar();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Hubo un error al almacenar. Intente nuevamente mas tarde.", Toast.LENGTH_LONG).show();

            }

        }
        else {
            Toast.makeText(this, "Hubo un error al almacenar. Intente nuevamente mas tarde.", Toast.LENGTH_LONG).show();
        }
    }
    //Metodo del boton
    public void cancelar(View view) {

        Intent intent = new Intent(EliminarEnfermedad.this, Enfermedades.class);
        startActivity(intent);
    }
    public void eliminar(View view) {
         confirmDialog();
    }
    private void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);

        builder
                .setMessage("¿Confirma la eliminación?")
                .setPositiveButton("Si",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        enfermedadExisteTerneraEnferma();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                })
                .show();
    }
    public void cerrar(){
        Intent intent = new Intent(EliminarEnfermedad.this, Enfermedades.class);
        startActivity(intent);
    }
}
