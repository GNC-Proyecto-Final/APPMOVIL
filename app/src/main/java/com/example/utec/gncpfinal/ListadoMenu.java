package com.example.utec.gncpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListadoMenu extends AppCompatActivity {

    private Button btnEnfermedades;
    private Button btnTernEnfermas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_menu);

        btnEnfermedades = findViewById(R.id.buttonEnfermedades);
        btnTernEnfermas = findViewById(R.id.buttonTernerasEnf);


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
    }
}
