package com.example.utec.gncpfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.EnfermedadTernera;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisualizarTerneraEnferma extends AppCompatActivity {


    //Widgets
    TextView txtTernera,txtEnfermedad,txtFechaInicio,txtFechaFin,txtOtrosAspectos;
    Button btnRegresar;
    private Date dateInicio,dateFin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_ternera_enferma);


        txtTernera = (TextView)findViewById(R.id.textViewViEnTernera);
        txtEnfermedad = (TextView)findViewById(R.id.textViewViEnEnfermedad);
        txtFechaInicio = (TextView)findViewById(R.id.textViewViEnFechaInicio);
        txtFechaFin = (TextView)findViewById(R.id.textViewViEnFechaFin);
        txtOtrosAspectos = (TextView)findViewById(R.id.textViewDiasNacida);
        btnRegresar= (Button)findViewById(R.id.btnRegistarTernerasEnfermas);

        Bundle objetoEnviado = getIntent().getExtras();
        EnfermedadTernera enferTern = null;
        if (objetoEnviado != null){
            enferTern = (EnfermedadTernera) objetoEnviado.getSerializable("enfermedadTerneras");

            Date feIni = enferTern.getId().getFechaDesde();
            Date feFin = enferTern.getFechaHasta();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");




            txtTernera.setText(Long.toString(enferTern.getTernera().getIdTernera()));
            txtEnfermedad.setText(Long.toString(enferTern.getEnfermedad().getIdEnfermedad()));

            if(feFin==null){
                String dti = df.format(feIni);
                txtFechaInicio.setText(dti);
            }else{
                String dti = df.format(feIni);
                String dtf = df.format(feFin);
                txtFechaInicio.setText(dti);
                txtFechaFin.setText(dtf);
            }
            txtOtrosAspectos.setText(enferTern.getObservacion().toString());

        }
    }



    public void  formatoFecha() {

        Date fechaInicio =null;
        Date fechaFin =null;
        String stringFechaInicio = txtFechaInicio.getText().toString();
        String stringFechaFin = txtFechaFin.getText().toString();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

       // df.setTimeZone(TimeZone.getTimeZone("UTC"));

        if (stringFechaInicio.equals("") &&stringFechaFin.equals("") ){
            Toast.makeText(this, "Fecha inicio y Final son nulas " , Toast.LENGTH_LONG).show();
        }
        else if (!stringFechaInicio.equals("")&& stringFechaFin.equals("") ){
            try{
                fechaInicio = df.parse(stringFechaInicio);
            }  catch (ParseException e) {
                e.printStackTrace();
            }

        }else if (stringFechaFin.equals("")&& !stringFechaFin.equals("")){
            try {
                fechaFin = df.parse(stringFechaFin);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            try {
                fechaInicio = df.parse(stringFechaInicio);
                fechaFin = df.parse(stringFechaFin);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
         try {
            if(!(fechaInicio==null)&& !(fechaFin==null)){
                String dti = df.format(fechaInicio);
                String dtf = df.format(fechaFin);
                dateInicio = df.parse(dti);
                dateFin = df.parse(dtf);
           }else if(!(fechaInicio==null)&& (fechaFin==null)){
                String dti = df.format(fechaInicio);
                dateInicio = df.parse(dti);
            }
            else if((fechaInicio==null)&& (fechaFin==null)){
            }
            else{
                String dtf = df.format(fechaFin);
                dateFin = df.parse(dtf);
          }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public boolean validarFecha(){
        boolean fechaValida= false;
        Date fecha = new Date();

        if(!(dateInicio==null)&& !(dateFin==null)){
            if(dateInicio.compareTo(dateFin)<=0 && dateInicio.compareTo(fecha)<=0  && dateFin.compareTo(fecha)<=0){
                fechaValida= true;
            }
        }else if(!(dateInicio==null)&& (dateFin==null)){
            if(dateInicio.compareTo(fecha)<=0 ){
                fechaValida= true;
            }
        }
        else if((dateInicio==null)&& (dateFin==null)){
            fechaValida= false;
        }
        else{
            if( dateFin.compareTo(fecha)<=0 ){
                fechaValida= true;
            }
        }
       return fechaValida;
    }
    //Metodo del boton
    public void regresarTerneraEnfermas(View view) {

        Intent intent = new Intent(VisualizarTerneraEnferma.this, TernerasEnfermas.class);
        startActivity(intent);


    }



}
