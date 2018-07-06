package com.example.asus.cvaproperties;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.asus.cvaproperties.ListDataSource.CustomAdapter;
import com.example.asus.cvaproperties.ListDataSource.ItemList;

import java.util.ArrayList;

public class Buscar_Propiedad extends AppCompatActivity  {
    private ListView LIST;
    private ArrayList<ItemList> LISTINFO;
    private CustomAdapter ADAPTER;
    private Context root;
    public String text;
    public String text1;

    public String s_tipop_aux ="",s_tipon_aux = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__propiedad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn = (Button) findViewById(R.id.buscar_p);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("....................... click en registrarse");
                Intent intent = new Intent (v.getContext(), Result_busqueda.class);
                startActivityForResult(intent, 0);
            }
        });



        Spinner spinner = (Spinner) findViewById(R.id.tipos_propiedades_bp);
        final String[] letra_tipo = { "Departamento","Casa","Terreno/Lote","Oficina","Bodega","Otros"};
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, letra_tipo));
        text = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                System.out.println("///////////// departamentossssssssss");
                s_tipop_aux = letra_tipo[pos];
                //System.out.println("///////////// tipo inmueble = " + s_tipo_inmuheble);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        Spinner spinner1 = (Spinner) findViewById(R.id.tipo_negocio_bp);
        final String[] t_n_s = { "Alquiler","Compra"};
        spinner1.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t_n_s));
        text1 = spinner.getSelectedItem().toString();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                System.out.println("///////////// departamentossssssssss");
                s_tipon_aux = t_n_s[pos];
                //System.out.println("///////////// tipo inmueble = " + s_tipo_inmuheble);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




    }

}
