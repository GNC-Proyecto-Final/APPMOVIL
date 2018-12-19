package com.example.utec.gncpfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class VisualizarEnfermedad extends AppCompatActivity {

    private TextView nombre,gravedad;
    private Button regresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_enfermedad);

        nombre = (TextView) findViewById(R.id.textViewEdEnNombre);
        gravedad = (TextView) findViewById(R.id.textViewEdEnGravedad);

        regresar = (Button) findViewById(R.id.buttonEnfRegresar);

        Bundle objetoEnviado = getIntent().getExtras();
        Enfermedad enfermedad = null;
        if (objetoEnviado != null){
            enfermedad = (Enfermedad) objetoEnviado.getSerializable("enfermedad");
            nombre.setText(enfermedad.getNombre().getNombre());
            gravedad.setText((Long.toString(enfermedad.getGradoGravedad())));
        }

    }

    //Metodo del boton
    public void regresarEnfermedades(View view) {

        Intent intent = new Intent(VisualizarEnfermedad.this, Enfermedades.class);
        startActivity(intent);


    }



}
