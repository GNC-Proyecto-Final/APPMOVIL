package com.example.utec.gncpfinal;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarTerneraEnferma extends AppCompatActivity{

    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Widgets
    TextView txtTernera;
    TextView txtEnfermedad;
    TextView txtFechaInicio;
    EditText txtFechaFin;
    EditText txtOtrosAspectos;

    ImageButton ib_obtener_hora_fin;
    Button btnCancelar,btnEditar;
    Context contexto;

    public Enfermedad enfermedad ;
    public Ternera ternera ;
    public EnfermedadTernera terneraEnferma;
    private Date fechaInicial, dateInicio,dateFin,dateNacim;
    private boolean existe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ternera_enferma);


        txtTernera = (TextView)findViewById(R.id.textViewEdEnTernera);
        txtEnfermedad = (TextView)findViewById(R.id.textViewEdEnEnfermedad);
        txtFechaInicio = (TextView)findViewById(R.id.textViewEdEnFechaInicio);
        txtFechaFin = (EditText)findViewById(R.id.editTextFechaFin);
        txtOtrosAspectos = (EditText)findViewById(R.id.editTextOtrosAspect);
        ib_obtener_hora_fin = (ImageButton)findViewById(R.id.ib_obtener_fecha_fin);
        btnCancelar= (Button)findViewById(R.id.btnRegistarTernerasEnfermas);
        btnEditar= (Button)findViewById(R.id.btnEditarTerneraEnferma);
        enfermedad = new Enfermedad();
        ternera = new Ternera();
        contexto = this;


        Bundle objetoEnviado = getIntent().getExtras();
        EnfermedadTernera enferTern = null;
        if (objetoEnviado != null){
            terneraEnferma = (EnfermedadTernera) objetoEnviado.getSerializable("enfermedadTerneras");
            Date feIni = terneraEnferma.getId().getFechaDesde();
            Date feFin = terneraEnferma.getFechaHasta();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            dateInicio =terneraEnferma.getId().getFechaDesde();
            txtTernera.setText(Long.toString(terneraEnferma.getTernera().getIdTernera()));
            txtEnfermedad.setText(Long.toString(terneraEnferma.getEnfermedad().getIdEnfermedad()));

            if(feFin==null){
                String dti = df.format(feIni);
                txtFechaInicio.setText(dti);
            }else{
                String dti = df.format(feIni);
                String dtf = df.format(feFin);
                txtFechaInicio.setText(dti);
                txtFechaFin.setText(dtf);
            }
            txtOtrosAspectos.setText(terneraEnferma.getObservacion());
        }
    }
    public void obtenerFechaFin(View view){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                final int mesActual = month + 1;

                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);

                txtFechaFin.setText(diaFormateado + BARRA + mesFormateado + BARRA + year)
                ;
            }
        },anio, mes, dia);
        recogerFecha.show();
    }
    public void editarTerneraEnferma() {

        formatoFecha();
        long numTernera = terneraEnferma.getTernera().getIdTernera();
        long numEnfermedad = terneraEnferma.getEnfermedad().getIdEnfermedad();

        if(!(ternera.getFechaMuerte()==null)||!(ternera.getFechaBaja()==null)){
            Toast.makeText(this, "Verifique que la ternera este habilitada, ternera muerta o dada de baja.", Toast.LENGTH_LONG).show();
        }

        if( !validarFecha()){
            Toast.makeText(getApplicationContext(), "Verifique las fechas: \n Fecha Inicio o Fin mayor a fecha actual.", Toast.LENGTH_LONG).show();
        }

        if(!validarFechaNaciento()){
            Toast.makeText(getApplicationContext(), "Verifique la fecha inicio enfermedad - fecha inicio enfermedad < nacimiento.", Toast.LENGTH_LONG).show();
        }

        if (this.txtOtrosAspectos.length()==0) {
            Toast.makeText(getApplicationContext(), "Otros aspectos Sanitarios \n Ingrese un comentario.", Toast.LENGTH_LONG).show();
        }

        if (this.txtOtrosAspectos.length()>=250) {
            Toast.makeText(getApplicationContext(), "Otros aspectos Sanitarios \n Excede de los 250 caracteres.", Toast.LENGTH_LONG).show();
        }

        // Si alguno es vacio, mostramos una ventana de mensaje
        if(numTernera==0 && numEnfermedad==0 &&  dateInicio == null ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos1.", Toast.LENGTH_LONG).show();
        }
        else if(numTernera==0 && numEnfermedad==0 ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos2.", Toast.LENGTH_LONG).show();
        }
        else if(numTernera==0 ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos3.", Toast.LENGTH_LONG).show();
        }
        else if(numEnfermedad==0 ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos4.", Toast.LENGTH_LONG).show();
        }
        else if(dateInicio==null ) {

            Toast.makeText(getApplicationContext(), "Faltan algunos datos5." + terneraEnferma.getId().getFechaDesde().toString(), Toast.LENGTH_LONG).show();
        }
        else{
            editar();
        }
    }
    public void  formatoFecha() {

        Date fechaNacim = this.ternera.getFechaNacimiento();
        Date fechaInicio =null;
        Date fechaFin =null;
        dateNacim = ternera.getFechaNacimiento();
        String stringFechaFin = txtFechaFin.getText().toString();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        // df.setTimeZone(TimeZone.getTimeZone("UTC"));

        if (stringFechaFin.equals("") ){
            Toast.makeText(this, "Fecha inicio y Final son nulas " , Toast.LENGTH_LONG).show();
        }
        else if (stringFechaFin.equals("")&& !stringFechaFin.equals("")){
            try {
                fechaFin = df.parse(stringFechaFin);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            try {

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
             //   System.out.println("x1: "+dateInicio);
            }else if(!(fechaInicio==null)&& (fechaFin==null)){
                String dti = df.format(fechaInicio);
                dateInicio = df.parse(dti);
                dateNacim = fechaNacim;
            //    System.out.println("x2: "+dateInicio);
            }
            else if((fechaInicio==null)&& (fechaFin==null)){
              //  System.out.println("x3: "+dateInicio);
            }
            else{
                String dtf = df.format(fechaFin);
                String dtn = df.format(fechaNacim);
                dateFin = df.parse(dtf);
                dateNacim = df.parse(dtn);
               // System.out.println("x4: "+dateInicio);
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
        System.out.println(dateNacim);
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

           long num = terneraEnferma.getTernera().getIdTernera();

        if(num==0){
            Toast.makeText(getApplicationContext(), "Debe ingresar una ternera distinta a 0. Cero no permitido", Toast.LENGTH_LONG).show();

        }else {

            long id = num;

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
        System.out.println(ternera.getFechaNacimiento());

        long num =  terneraEnferma.getEnfermedad().getIdEnfermedad();
        if(num==0){
            Toast.makeText(getApplicationContext(), "Debe ingresar un enfermedad. Cero no permitido", Toast.LENGTH_LONG).show();

        }else {

            long id = num;

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
    public void getTernera(){
    }
    public void getEnfermedad(){
        getTernera();
        editarTerneraEnferma();
    }
    public void editar(){

        if(dateFin == null){
            try {
                EnfermedadTerneraPK pk = new EnfermedadTerneraPK();
                pk.setIdEnfermedad(enfermedad.getIdEnfermedad());
                pk.setIdTernera(ternera.getIdTernera());
                pk.setFechaDesde(dateInicio);
                String aspectSan = txtOtrosAspectos.getText().toString();

                EnfermedadTernera terneraEnferma = new EnfermedadTernera (pk,ternera,enfermedad,aspectSan);

                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd")
                        .create();
                //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RestClient.getBaseUrl())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                EnfermedadTerneraServicios serviceAdd = retrofit.create(EnfermedadTerneraServicios.class);
                Call<Void> call = serviceAdd.updateEnfermedadTernera(terneraEnferma);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                       Toast.makeText(getApplicationContext(), "La Enfermedad por Ternera se ha sido editada con Exito.", Toast.LENGTH_LONG).show();
                        cerrar();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Hubo un error al almacenar. Intente nuevamente mas tarde.", Toast.LENGTH_LONG).show();
            }

        }
        else{
            try {
                EnfermedadTerneraPK pk = new EnfermedadTerneraPK();
                pk.setIdEnfermedad(enfermedad.getIdEnfermedad());
                pk.setIdTernera(ternera.getIdTernera());
                pk.setFechaDesde(dateInicio);
                String aspectSan = txtOtrosAspectos.getText().toString();

                EnfermedadTernera terneraEnferma = new EnfermedadTernera (pk,ternera,enfermedad,dateFin,aspectSan);


                Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd")
                        .create();
                //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RestClient.getBaseUrl())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                EnfermedadTerneraServicios serviceAdd = retrofit.create(EnfermedadTerneraServicios.class);
                Call<Void> call = serviceAdd.updateEnfermedadTernera(terneraEnferma);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        //  Toast.makeText(getApplicationContext(),"La Enfermedad ha sido registrada correctamente. " ,Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "La Enfermedad por Ternera se ha sido editada con Exito.", Toast.LENGTH_LONG).show();
                        cerrar();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Hubo un error al almacenar. Intente nuevamente mas tarde.", Toast.LENGTH_LONG).show();
            }
        }

    }
    //Metodo del boton
    public void cancelarEditarTernraEnferma(View view) {
        Intent intent = new Intent(EditarTerneraEnferma.this, TernerasEnfermas.class);
        startActivity(intent);
    }
    public void editarTernraEnferma(View view) {
        obtenerTernera();
    }
    public void cerrar(){
        Intent intent = new Intent(EditarTerneraEnferma.this, TernerasEnfermas.class);
        startActivity(intent);
    }
}
