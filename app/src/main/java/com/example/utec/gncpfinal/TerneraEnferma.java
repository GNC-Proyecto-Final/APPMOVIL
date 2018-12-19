package com.example.utec.gncpfinal;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.Enfermedad;
import com.example.utec.gncpfinal.entidades.EnfermedadTernera;
import com.example.utec.gncpfinal.entidades.EnfermedadTerneraPK;
import com.example.utec.gncpfinal.entidades.Ternera;
import com.example.utec.gncpfinal.servicios.EnfermedadTerneraServicios;
import com.example.utec.gncpfinal.servicios.EnfermedadesServicios;
import com.example.utec.gncpfinal.servicios.RestClient;
import com.example.utec.gncpfinal.servicios.TernerasServicios;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TerneraEnferma extends AppCompatActivity implements DialogoEnfermedades.FinDialogo,DialogoTerneras.FinDialogoTernera {

    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    EditText editTextTernera;
    EditText editTextEnfermedad;
    EditText editTextFechaIni;
    EditText editTextFechaFin;
    EditText editTextOtrosAspectos;
    ImageButton ib_obtenerTernera;
    ImageButton ib_obtenerEnfermedad;
    ImageButton ib_obtener_fecha_ini;
    ImageButton ib_obtener_hora_fin;
    Button btnRegistrar;
    Context contexto;

    public Enfermedad enfermedad ;
    public Ternera ternera ;
    public EnfermedadTernera terneraEnferma;
    private Date fechaInicial, dateInicio,dateFin,dateNacim;
    private boolean existe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ternera_enferma);


        editTextTernera = (EditText)findViewById(R.id.editTextTernera);
        editTextEnfermedad = (EditText)findViewById(R.id.editTextTextEnfermedad);
        editTextFechaIni = (EditText)findViewById(R.id.editTextFechaIni);
        editTextFechaFin = (EditText)findViewById(R.id.editTextFechaFin);
        editTextOtrosAspectos = (EditText)findViewById(R.id.editTextOtrosAspect);
        ib_obtenerTernera = (ImageButton)findViewById(R.id.ib_obtenerTernera);
        ib_obtenerEnfermedad = (ImageButton)findViewById(R.id.ib_obtenerEnfermedad);
        ib_obtener_fecha_ini = (ImageButton)findViewById(R.id.ib_obtener_fecha_ini);
        ib_obtener_hora_fin = (ImageButton)findViewById(R.id.ib_obtener_fecha_fin);
        btnRegistrar= (Button)findViewById(R.id.btnRegistrar);
        enfermedad = new Enfermedad();
        ternera = new Ternera();
        contexto = this;

    }
    @Override
    public void resultadoDialogo(long idEnfermedad){

        editTextEnfermedad.setText(Long.toString(idEnfermedad));
    }
    @Override
    public void resultadoDialogoTernera(long idTernera){
        editTextTernera.setText(Long.toString(idTernera));
    }
    public void obtenerTerneras(View view){
        Toast.makeText(this,"Desde el boton de obtener terneras. " ,Toast.LENGTH_LONG).show();
        new DialogoTerneras(contexto,TerneraEnferma.this );
    }
    public void obtenerEnfermedades(View view){
        Toast.makeText(this,"Desde el boton de obtener enfermedades. " ,Toast.LENGTH_LONG).show();
        new DialogoEnfermedades(contexto,TerneraEnferma.this );
    }
    public void obtenerFechaIni(View view){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                final int mesActual = month + 1;

                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                editTextFechaIni.setText(diaFormateado + BARRA + mesFormateado + BARRA + year)
               ;


            }
        },anio, mes, dia);

        recogerFecha.show();

    }
    public void obtenerFechaFin(View view){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                final int mesActual = month + 1;

                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                editTextFechaFin.setText(diaFormateado + BARRA + mesFormateado + BARRA + year)
                ;


            }
        },anio, mes, dia);

        recogerFecha.show();

    }
    public void registrar(View view){
        obtenerTernera();





    }


    public void agregarTerneraEnferma() {




        formatoFecha();
       // Toast.makeText(this,  "Nacio "+ ternera.getNroCaravana()+"Nacio "+ ternera.getFechaNacimiento()+"muerte " + ternera.getFechaMuerte() + "baja "+ ternera.getFechaBaja(), Toast.LENGTH_LONG).show();

        long numTernera = Long.parseLong(editTextTernera.getText().toString());
        long numEnfermedad = Long.parseLong(editTextEnfermedad.getText().toString());



        if(!(ternera.getFechaMuerte()==null)||!(ternera.getFechaBaja()==null)){
            Toast.makeText(this, "Verifique que la ternera este habilitada, ternera muerta o dada de baja.", Toast.LENGTH_LONG).show();
        }

        if( !validarFecha()){
            Toast.makeText(getApplicationContext(), "Verifique las fechas: \n Fecha Inicio o Fin mayor a fecha actual.", Toast.LENGTH_LONG).show();
        }

        if(!validarFechaNaciento()){
            Toast.makeText(getApplicationContext(), "Verifique la fecha inicio enfermedad - fecha inicio enfermedad < nacimiento.", Toast.LENGTH_LONG).show();
        }

        if (this.editTextOtrosAspectos.length()==0) {
            Toast.makeText(getApplicationContext(), "Otros aspectos Sanitarios \n Ingrese un comentario.", Toast.LENGTH_LONG).show();
        }

        if (this.editTextOtrosAspectos.length()>=250) {
            Toast.makeText(getApplicationContext(), "Otros aspectos Sanitarios \n Excede de los 250 caracteres.", Toast.LENGTH_LONG).show();
        }

        // Si alguno es vacio, mostramos una ventana de mensaje
        if(numTernera==0 && numEnfermedad==0 &&  dateInicio == null ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos.1.tt", Toast.LENGTH_LONG).show();
        }
        else if(numTernera==0 && numEnfermedad==0 ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos.2.tt", Toast.LENGTH_LONG).show();
        }
        else if(numTernera==0 ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos.3.tt", Toast.LENGTH_LONG).show();
        }
        else if(numEnfermedad==0 ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos.4.tt", Toast.LENGTH_LONG).show();
        }
        else if(dateInicio==null ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos.5.tt", Toast.LENGTH_LONG).show();
        }
        else{

            if(dateFin == null){
                existeTerneraEnferma();
                if (existe) {
                  //  Toast.makeText(getApplicationContext(), "La Enfermedad por Ternera ya se encuentra registrada..", Toast.LENGTH_LONG).show();
                }

                try {

                 //   Toast.makeText(getApplicationContext(), "La Enfermedad por Ternera se ha sido registrado con Exito.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Hubo un error al almacenar. Intente nuevamente mas tarde.", Toast.LENGTH_LONG).show();

                }
            }
            else{

                if (existe) {
                    Toast.makeText(getApplicationContext(), "La Enfermedad por Ternera ya se encuentra registrada.", Toast.LENGTH_LONG).show();
                }

                try {
                    Toast.makeText(getApplicationContext(), "La  Enfermedad por Ternera se ha sido registrado con Exito.", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Ocurrio un error al almacenar. Intente nuevamente mas tarde.", Toast.LENGTH_LONG).show();

                }
            }
        }

/*
         */



    }

    public void  formatoFecha() {

        Date fechaNacim = this.ternera.getFechaNacimiento();
        Date fechaInicio =null;
        Date fechaFin =null;
        String stringFechaInicio = editTextFechaIni.getText().toString();
        String stringFechaFin = editTextFechaFin.getText().toString();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

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
                String dtn = df.format(fechaNacim);
                dateInicio = df.parse(dti);
                dateFin = df.parse(dtf);
                dateNacim = df.parse(dtn);
           }else if(!(fechaInicio==null)&& (fechaFin==null)){
                String dti = df.format(fechaInicio);
                dateInicio = df.parse(dti);
                dateNacim = fechaNacim;
            }
            else if((fechaInicio==null)&& (fechaFin==null)){
            }
            else{
                String dtf = df.format(fechaFin);
                String dtn = df.format(fechaNacim);
                dateFin = df.parse(dtf);
                dateNacim = df.parse(dtn);
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

    public boolean validarFechaNaciento(){

        boolean fechaValida= false;
         if(!(dateInicio == null )&& !(dateFin == null )){
            if(dateInicio.compareTo(dateNacim)>=0 && dateFin.compareTo(dateNacim)>=0){
                fechaValida= true;
            }
         }else if(!(dateInicio == null )&& (dateFin == null )){


            if(dateInicio.compareTo(dateNacim)>=0 ){
                fechaValida= true;

            }

         }
        else if((dateInicio == null )&& (dateFin  == null  )){
            fechaValida= false;
        }
        else{
            if( dateFin.compareTo(dateNacim)>=0 ){
                fechaValida= true;
            }
        }

         return fechaValida;

    }


    public void obtenerTernera(){


        long num = Long.parseLong(editTextTernera.getText().toString());

        if(num==0){
            Toast.makeText(getApplicationContext(), "Debe ingresar una ternera distinta a 0. Cero no permitido", Toast.LENGTH_LONG).show();

        }else {

            String ter = editTextTernera.getText().toString();

            long id = Long.parseLong(ter);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestClient.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            TernerasServicios service = retrofit.create(TernerasServicios.class);
            Call<Ternera> call = service.getTernera(id);

            call.enqueue(new Callback<Ternera>() {
                @Override
                public void onResponse(Call<Ternera> call, Response<Ternera> response) {


                  ternera = response.body();
                    obtenerEnfermedad();
                }

                @Override
                public void onFailure(Call<Ternera> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }

    }

    public void obtenerEnfermedad(){

        long num = Long.parseLong(editTextEnfermedad.getText().toString());
        if(num==0){
            Toast.makeText(getApplicationContext(), "Debe ingresar un enfermedad. Cero no permitido", Toast.LENGTH_LONG).show();

        }else {

            String enf = editTextEnfermedad.getText().toString();

            long id = Long.parseLong(enf);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestClient.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EnfermedadesServicios service = retrofit.create(EnfermedadesServicios.class);
            Call<Enfermedad> call = service.getEnfermedad(id);

            call.enqueue(new Callback<Enfermedad>() {
                @Override
                public void onResponse(Call<Enfermedad> call, Response<Enfermedad> response) {


                    enfermedad = response.body();
                    getEnfermedad();
                }

                @Override
                public void onFailure(Call<Enfermedad> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        }

    }

    public void existeTerneraEnferma(){

        long numTernera = Long.parseLong(editTextTernera.getText().toString());
        long numEnfermedad = Long.parseLong(editTextEnfermedad.getText().toString());

        if(numTernera==0 || numEnfermedad==0){
            Toast.makeText(getApplicationContext(), "Debe ingresar un enfermedad. Cero no permitido", Toast.LENGTH_LONG).show();

        }else {


            /*
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            String inputText = editTextFechaIni.getText().toString();
            Date fn = null;
            try {
                fn = inputFormat.parse(inputText);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String outputText = outputFormat.format(fn);

            try {
                Date fechaDeNacimiento=new SimpleDateFormat("yyyy-MM-dd").parse(outputText);
                fechaInicial = fechaDeNacimiento;

            } catch (ParseException e) {
                e.printStackTrace();
            }
            */
           // Toast.makeText(this," "+ fechaInicio, Toast.LENGTH_LONG).show();




            String enf = editTextEnfermedad.getText().toString();

            long id = Long.parseLong(enf);

            EnfermedadTerneraPK pk = new EnfermedadTerneraPK();
            pk.setIdEnfermedad(enfermedad.getIdEnfermedad());
            pk.setIdTernera(ternera.getIdTernera());


            pk.setFechaDesde(dateInicio);

            terneraEnferma = new EnfermedadTernera (pk,this.ternera,this.enfermedad);
          Toast.makeText(this," "+ fechaInicial, Toast.LENGTH_LONG).show();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();
            //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestClient.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            EnfermedadTerneraServicios service = retrofit.create(EnfermedadTerneraServicios.class);
            Call<Boolean> call = service.obtenerTerneraEnfermaExiste(terneraEnferma);

            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                   existe = response.body();

                    Toast.makeText(getApplicationContext(), "existe :"
                            + response.body(), Toast.LENGTH_LONG).show();


            }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                   System.out.println(t.getMessage());
                }
            });

        }

    }

    public void getTernera(){
        ternera.getNroCaravana();
    }
    public void getEnfermedad(){

        agregarTerneraEnferma();
    }
}
