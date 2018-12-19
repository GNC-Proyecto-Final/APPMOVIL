package com.example.utec.gncpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.Enfermedad;
import com.example.utec.gncpfinal.enumerados.NombreEnfermedad;
import com.example.utec.gncpfinal.servicios.EnfermedadesServicios;
import com.example.utec.gncpfinal.servicios.RestClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NuevaEnfermedad extends AppCompatActivity {

    private Spinner spinnerNombre;
    private Spinner spinnerGravedad;
    private Button ingresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_enfermedad);

        spinnerNombre = (Spinner) findViewById(R.id.spinnerForNombre);
        spinnerGravedad = (Spinner) findViewById(R.id.spinnerForGravedad);

        ingresar = (Button) findViewById(R.id.buttonIngresar);

        ArrayList<String> enf = new ArrayList<String>();

        for (NombreEnfermedad es : NombreEnfermedad.values()){
            enf.add(es.getNombre());
        }

        ArrayAdapter <String> adapterNombre = new ArrayAdapter<String>(this,R.layout.spinner_item,enf);
        spinnerNombre.setAdapter(adapterNombre);

        String [] gravedad ={"1","2","3"};

        ArrayAdapter <String> adapterGravedad = new ArrayAdapter<String>(this,R.layout.spinner_item,gravedad);
        spinnerGravedad.setAdapter(adapterGravedad);

    }

    //Metodo del boton
    public void guardar(View view) {


      //  boolean existe = false;
        /// FacesContext context = FacesContext.getCurrentInstance();
        String gravedad = spinnerGravedad.getSelectedItem().toString();
        String enfermedad = spinnerNombre.getSelectedItem().toString();

        long gradoGravedad = Long.parseLong(gravedad);

        NombreEnfermedad emumEnfermedad = null;
        for (NombreEnfermedad es : NombreEnfermedad.values()) {
            if (es.getNombre().equals(enfermedad)) {
                emumEnfermedad = es;
            }
        }

        Enfermedad enferm = new Enfermedad(gradoGravedad, emumEnfermedad);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestClient.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EnfermedadesServicios service = retrofit.create(EnfermedadesServicios.class);
        Call<Boolean> call = service.existeEnfermedad(enferm);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {


               //existe  = response.body();
                if (response.body()==true) {

                    Toast.makeText(getApplicationContext(),"La Enfermedad ya se encuentra registrada. " ,Toast.LENGTH_LONG).show();
                }else {
                    agregar();
                }

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }
    public void agregar(){
        String gravedad = spinnerGravedad.getSelectedItem().toString();
        String enfermedad = spinnerNombre.getSelectedItem().toString();

        long gradoGravedad = Long.parseLong(gravedad);

        NombreEnfermedad emumEnfermedad = null;
        for (NombreEnfermedad es : NombreEnfermedad.values()) {
            if (es.getNombre().equals(enfermedad)) {
                emumEnfermedad = es;
            }
        }

        Enfermedad enferm = new Enfermedad(gradoGravedad, emumEnfermedad);
        try {

            Retrofit retrofit2 = new Retrofit.Builder()
                    .baseUrl(RestClient.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EnfermedadesServicios serviceAdd = retrofit2.create(EnfermedadesServicios.class);
            Call<Void> call = serviceAdd.addEnfermedad(enferm);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(getApplicationContext(),"La Enfermedad ha sido registrada correctamente. " ,Toast.LENGTH_LONG).show();
                    cerrar();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });


        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Hubo un error al almacenar. Intente nuevamente más tarde", Toast.LENGTH_LONG).show();

        }
    }
    public void cerrar(){
        Intent intent = new Intent(NuevaEnfermedad.this, RegistroMenu.class);
        startActivity(intent);
    }

    public void cancelar(View view) {
        Intent intent = new Intent(NuevaEnfermedad.this, RegistroMenu.class);
        startActivity(intent);
    }
}
