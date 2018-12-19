package com.example.utec.gncpfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utec.gncpfinal.entidades.EnfermedadTernera;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisualizarInformeTerneraEnferma extends AppCompatActivity {


    //Widgets
    TextView txtTernera,txtEnfermedad,txtFechaNacio,txtFechaInicio,txtFechaFin,txtDiasNacida;
    Button btnRegresar;
    private Date dateInicio,dateFin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_informe_ternera_enferma);


        txtTernera = (TextView)findViewById(R.id.textViewViEnTernera);
        txtEnfermedad = (TextView)findViewById(R.id.textViewViEnEnfermedad);
        txtFechaNacio = (TextView)findViewById(R.id.textViewViEnFechaNacio);
        txtFechaInicio = (TextView)findViewById(R.id.textViewViEnFechaInicio);
        txtFechaFin = (TextView)findViewById(R.id.textViewViEnFechaFin);
        txtDiasNacida = (TextView)findViewById(R.id.textViewDiasNacida);
        btnRegresar= (Button)findViewById(R.id.btnRegistarTernerasEnfermas);

        Bundle objetoEnviado = getIntent().getExtras();
        EnfermedadTernera enferTern = null;
        if (objetoEnviado != null){
            enferTern = (EnfermedadTernera) objetoEnviado.getSerializable("enfermedadTerneras");

            Date feIni = enferTern.getId().getFechaDesde();
            Date feFin = enferTern.getFechaHasta();
            Date feNac = enferTern.getTernera().getFechaNacimiento();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");




            txtTernera.setText(Long.toString(enferTern.getTernera().getIdTernera()));
            txtEnfermedad.setText(Long.toString(enferTern.getEnfermedad().getIdEnfermedad()));

            if(feFin==null){
                String dti = df.format(feIni);
                String dtn = df.format(feNac);
                txtFechaInicio.setText(dti);
                txtFechaNacio.setText(dtn);
            }else{
                String dtn = df.format(feNac);
                String dti = df.format(feIni);
                String dtf = df.format(feFin);
                txtFechaNacio.setText(dtn);
                txtFechaInicio.setText(dti);
                txtFechaFin.setText(dtf);
            }
            txtDiasNacida.setText(Long.toString(enferTern.getDiasVida()));

        }
    }


    //Metodo del boton
    public void regresarTerneraEnfermas(View view) {

        Intent intent = new Intent(VisualizarInformeTerneraEnferma.this, InformeTernerasEnfermas.class);
        startActivity(intent);


    }



}
