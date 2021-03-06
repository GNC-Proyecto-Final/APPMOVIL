package com.example.utec.gncpfinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtMostrarNombreUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



       // txtMostrarNombreUsu.setText("" + bundle.getString("USUARIO"));


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_ternera) {
            fragmentManager.beginTransaction().replace(R.id.escenario, new BlankFragment()).commit();

        } else if (id == R.id.nav_ternera) {
            fragmentManager.beginTransaction().replace(R.id.escenario, new BlankFragment()).commit();

        } else if (id == R.id.nav_alimento) {
            fragmentManager.beginTransaction().replace(R.id.escenario, new BlankFragment()).commit();

        } else if (id == R.id.nav_enfermedad) {
            fragmentManager.beginTransaction().replace(R.id.escenario, new EnfermedadesMenuFragment()).commit();

        } else if (id == R.id.nav_medicamento) {
            fragmentManager.beginTransaction().replace(R.id.escenario, new BlankFragment()).commit();

        } else if (id == R.id.nav_salir) {
            salir();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void salir(){

        SharedPreferences sharedPref = getSharedPreferences("sesionPreference",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.remove("idUsuario");
        editor.remove("apellido");
        editor.remove("contrasenia");
        editor.remove("nombre");
        editor.remove("perfil");
        editor.remove("username");

        editor.clear();
        editor.apply();
        editor.commit();
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
