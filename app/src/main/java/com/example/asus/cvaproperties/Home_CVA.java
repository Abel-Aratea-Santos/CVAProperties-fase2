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
import android.widget.ListView;

import com.example.asus.cvaproperties.ListDataSource.CustomAdapter;
import com.example.asus.cvaproperties.ListDataSource.ItemList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Home_CVA extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView LIST;
    private ArrayList<ItemList> LISTINFO;
    private CustomAdapter ADAPTER;
    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        root = this;
        LISTINFO = new ArrayList<ItemList>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__cv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LIST = (ListView) this.findViewById(R.id.ListViewCasas);

        loadInitialRestData();
        loadComponents();


        }

    private void loadInitialRestData() {
        AsyncHttpClient client = new AsyncHttpClient();
     //  String url ="http://192.168.6.142:5000/api/v1.0/inmueble";
        client.get("http://192.168.100.5:5000/api/v1.0/datos_anuncio",new  JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    System.out.println("****************************** entro al object array " + response);
                    /*JSONArray list = response;
                    for(int i=0; 1< list.length(); i++){
                        JSONObject itemJsom = list.getJSONObject(i);

                        String precio = itemJsom.getString("precio");
                        String nombre = itemJsom.getString("nombre");
                        String ubicacion = itemJsom.getString("ubicacion");
                        String superficie = itemJsom.getString("superficie");
                        String num_habitaciones = itemJsom.getString("num_banos");
                        String num_plantas = itemJsom.getString("num_plantas");

                        ItemList item = new ItemList (precio, nombre, ubicacion, superficie, num_habitaciones, num_plantas,null,null);
                        LISTINFO.add(item);
                    }*/
                    String img_inmu [] = new String[10];
                    int n = 0;
                    for(int i=0; i< response.length(); i++){
                        //System.out.println("****************************** el precio es => " + response.getJSONObject(i).getString("precio"));
                        //String img_inmueble = itemJsom.getString("gallery");
                        JSONObject itemJsom = response.getJSONObject(i);
                        String idimdb = itemJsom.getString("_id");
                        String precio = itemJsom.getString("precio_a");
                        String nombre = itemJsom.getString("frase_destacada_a");
                        String ubicacion = itemJsom.getString("ubicacion_a");
                        String superficie = itemJsom.getString("superficie_a");
                        String num_habitaciones = itemJsom.getString("num_banos_a");
                        String num_plantas = itemJsom.getString("num_plantas_a");
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx" + itemJsom.getJSONArray("gallery " ));
                        System.out.println("****************************** la ubicacion es => " + ubicacion);
                        ItemList item = new ItemList (/*img_inmueble*/idimdb,precio, nombre, ubicacion, superficie, num_habitaciones, num_plantas);
                        LISTINFO.add(item);

                    }

                    ADAPTER = new CustomAdapter(root,LISTINFO);


                    //eventos

                    LIST.setAdapter(ADAPTER);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void loadComponents() {
        System.out.println("------------- loadComponents");
        LIST = (ListView) this.findViewById(R.id.ListViewCasas);

        //acceso a propiedades de inmuebles
        LIST.setOnItemClickListener(this);

        LIST.setAdapter(ADAPTER);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
   String idimdb = this.LISTINFO.get(position).getIdimdb();
        Intent inmu_detalles = new Intent(this,Properties_details.class);
        inmu_detalles.putExtra("id", idimdb);
        this.startActivity(inmu_detalles);
    }
}
