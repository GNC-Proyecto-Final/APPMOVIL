package com.example.utec.gncpfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utec.gncpfinal.entidades.Usuario;
import com.example.utec.gncpfinal.servicios.RestClient;
import com.example.utec.gncpfinal.servicios.UsuariosService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Creado por GNC 11/2018
 */
public class MainActivity extends AppCompatActivity {

    private TextView editTextForUsuario;
    private TextView editTextForPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextForUsuario = (EditText) findViewById(R.id.editTextForUsuario);
        editTextForPassword = (EditText) findViewById(R.id.editTextForPassword);


        if(getSheredPreferences("username").equals("")){
            Toast toast =  Toast.makeText(getApplicationContext(),
                    "Adios", Toast.LENGTH_SHORT);
            toast.show();
        }

        if(!getSheredPreferences("username").equals("")){
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        }

        final Button buttonIngresar = findViewById(R.id.buttonIngresar);
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( validarCampos() ){
                    getUsuarioLogin();
                }
            }
        });
    }

    private void getUsuarioLogin() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestClient.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuariosService servicio = retrofit.create(UsuariosService.class);

        Call<Usuario> call = servicio.getUsuariologin( editTextForUsuario.getText().toString(), editTextForPassword.getText().toString());
        call.enqueue(new Callback<Usuario>(){
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.body()!=null){



                    guardarSharedPreferences(response.body().getIdUsuario(),response.body().getApellido(),response.body().getContrasenia(),
                            response.body().getNombre(),response.body().getPerfil(),response.body().getUsuario());
                    //Usuario usuario = response.body();

                    if(!getSheredPreferences("username").equals("")){

                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);
                    }

                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                System.out.println(t.getMessage());
                Toast toast =  Toast.makeText(getApplicationContext(),
                        "¡¡Error en la conexón!!" + t.getMessage(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    public boolean validarCampos(){
        boolean valido = false;
        if ( !editTextForUsuario.getText().toString().isEmpty() && !editTextForPassword.getText().toString().isEmpty()) {
            valido= true;
        }else{
            Toast.makeText(getApplicationContext(), "Por favor complete todos los campos", Toast.LENGTH_LONG).show();
        }
        return valido;
    }

    public void guardarSharedPreferences( long idUsuario, String apellido, String contrasenia, String nombre, String perfil,String usuario){
        SharedPreferences sharedPref = getSharedPreferences("sesionPreference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("apellido", apellido);
        editor.putString("contrasenia", contrasenia);
        editor.putString("nombre", nombre);
        editor.putString("perfil", perfil);
        editor.putString("username", usuario);
        editor.apply();


    }
    public String getSheredPreferences(String key){
        SharedPreferences sharedPref = getSharedPreferences("sesionPreference",Context.MODE_PRIVATE);
        return sharedPref.getString(key,"");
    }
}
