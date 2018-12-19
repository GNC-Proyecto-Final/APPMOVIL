package com.example.utec.gncpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuEnfermedad extends AppCompatActivity {

    private Button buttonRegistro;
    private Button buttonListado;
    private Button buttonMenuInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_enfermedad);

        buttonRegistro = findViewById(R.id.buttonRegistro);
        buttonListado = findViewById(R.id.buttonListado);
        buttonMenuInicio = findViewById(R.id.buttonMenuInicio);


        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEnfermedad.this, RegistroMenu.class);
                startActivity(intent);
            }
        });

        buttonListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEnfermedad.this, ListadoMenu.class);
                startActivity(intent);
            }
        });

        buttonMenuInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEnfermedad.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }
}
