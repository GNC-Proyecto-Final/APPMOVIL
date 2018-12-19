package com.example.utec.gncpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListadoMenu extends AppCompatActivity {

    private Button btnEnfermedades;
    private Button btnTernEnfermas;
    private Button btnInformeTernEnfermas;
    private Button buttonHomeListado;
    private Button buttonMenuListadoEnfemedades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_menu);

        btnEnfermedades = findViewById(R.id.buttonEnfermedades);
        btnTernEnfermas = findViewById(R.id.buttonTernerasEnf);
        btnInformeTernEnfermas = findViewById(R.id.buttonInformeTerEnf);
        buttonHomeListado = findViewById(R.id.buttonHomeListado);
        buttonMenuListadoEnfemedades = findViewById(R.id.buttonMenuListadoEnfemedades);

        btnEnfermedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListadoMenu.this, Enfermedades.class);
                startActivity(intent);
            }
        });

        btnTernEnfermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListadoMenu.this, TernerasEnfermas.class);
                startActivity(intent);
            }
        });

        btnInformeTernEnfermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListadoMenu.this, InformeTernerasEnfermas.class);
                startActivity(intent);
            }
        });
        buttonHomeListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(ListadoMenu.this, MenuActivity.class);
                    startActivity(intent);
            }
        });

        buttonMenuListadoEnfemedades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListadoMenu.this, MenuEnfermedad.class);
                startActivity(intent);
            }
        });
    }
}
