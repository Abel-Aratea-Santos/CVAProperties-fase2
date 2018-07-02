package com.example.asus.cvaproperties;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class Buscar_Propiedad extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView LIST;
    private ArrayList<ItemList> LISTINFO;
    private CustomAdapter ADAPTER;
    private Context root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__propiedad);
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


        Spinner spinner = (Spinner) findViewById(R.id.tipos_propiedades);
        String[] letra = { "Departamento","Casa","Terreno/Lote","Oficina","Bodega","Otros"};
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, letra));
        String t_propie = spinner.getSelectedItem().toString();



        Spinner spinner1 = (Spinner) findViewById(R.id.tipo_negocio);
        String[] t_n = { "Alquiler","Compra"};
        spinner1.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t_n));
        String t_negocio = spinner1.getSelectedItem().toString();




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            String tipo_pro = this.LISTINFO.get(position).getTipo_inmu_i();
            String tipo_ne = this.LISTINFO.get(position).getOperacion_i();



            Intent result_b = new Intent(this, Result_busqueda.class);
            result_b.putExtra("t_pr", tipo_pro);
            result_b.putExtra("t_ne", tipo_ne);
            this.startActivity(result_b);
            System.out.print("jdjjdh.............................,,,,,,,,,,,,,,,,,,,,,,"+tipo_ne);
        }
}
