package com.example.utec.gncpfinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class EnfermedadesMenuFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_enfermedades_menu, container, false);

       Button btnRegistro = (Button) view.findViewById(R.id.buttonRegistro);
       btnRegistro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), RegistroMenu.class);
               startActivity(intent);
           }
       });


        Button btnListado = (Button) view.findViewById(R.id.buttonListado);
        btnListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListadoMenu.class);
                startActivity(intent);
            }
        });

       return view;
    }
}
