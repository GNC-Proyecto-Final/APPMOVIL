package com.example.utec.gncpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistroMenu extends AppCompatActivity {

    private Button btnNuevaEnf;
    private Button btnTernEnf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_menu);

        btnNuevaEnf = findViewById(R.id.buttonNuevaEnf);
        btnTernEnf = findViewById(R.id.buttonTernEnf);


        btnNuevaEnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroMenu.this, NuevaEnfermedad.class);
                startActivity(intent);
            }
        });

        btnTernEnf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroMenu.this, TerneraEnferma.class);
                startActivity(intent);
            }
        });
    }
}
