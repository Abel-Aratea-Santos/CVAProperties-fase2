package com.example.asus.cvaproperties;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //VERIFICO SI SE LEGEO
        /*Bundle datos = this.getIntent().getExtras();
        //int aux_login = datos.getInt("logear", 0);
        if(datos == null)
        {
            Intent intencion = new Intent(this, Registrar_CVA.class);
            startActivity(intencion);
            finish();

        }
        //FIN DE VERIFICO
*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.home:
                i = new Intent(MainActivity.this, Home_CVA.class);
                startActivity(i);
                //finish();
                return true;
            case R.id.registro:
                i = new Intent(MainActivity.this, Registrar_CVA.class);
                startActivity(i);
                //finish();
                return true;
            case R.id.publicar_anuncio:
                i = new Intent(MainActivity.this, Publicar_anuncio_CVA.class);
                startActivity(i);
                //finish();
                return true;

            case R.id.propiedad:
                i = new Intent(MainActivity.this, Buscar_Propiedad.class);
                startActivity(i);
                //finish();
                return true;

            case R.id.map:
                i = new Intent(MainActivity.this, Buscar_Mapa.class);
                startActivity(i);
                //finish();
                return true;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
