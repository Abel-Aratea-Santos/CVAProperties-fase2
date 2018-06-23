package com.example.asus.cvaproperties;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Publicar_anuncio_CVA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_anuncio__cv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Spinner spinner = (Spinner) findViewById(R.id.Spinner_tipo_inmueble_B);
        String[] letra = { "Departamento","Casa","Terreno/Lote","Oficina","Bodega","Otros"};
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, letra));

        Spinner spinner1 = (Spinner) findViewById(R.id.num_habitaciones_B);
        String[] t_n = { "1","2","3","4","5","6","7","8","9","10"};
        spinner1.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t_n));


        Spinner spinner2 = (Spinner) findViewById(R.id.num_banos_B);
        spinner2.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t_n));


        Spinner spinner3 = (Spinner) findViewById(R.id.num_plantas_B);
        spinner3.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t_n));

    }

}
