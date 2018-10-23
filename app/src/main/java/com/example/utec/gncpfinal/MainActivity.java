package com.example.utec.gncpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final String USUARIO = "USUARIO";
    public static final String CONTRASENIA = "CONTRASENIA";

    private EditText usuarioText;
    private EditText passText;
    private Button buttonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioText = findViewById(R.id.editTextForUsuario);
        passText = findViewById(R.id.editTextForPassword);
        buttonIngresar = findViewById(R.id.buttonIngresar);



        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String verificar1 = usuarioText.getText().toString();
                String verificar2 = passText.getText().toString();

                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                Bundle b = new Bundle();

                if(verificar1.isEmpty() & verificar2.isEmpty()){
                    usuarioText.setError("No se ingresaron caracteres");
                    passText.setError("No se ingresaron caracteres");
                }else {
                    b.putString("USUARIO", usuarioText.getText().toString());

                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });
    }
}
