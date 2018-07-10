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
import android.widget.Button;
import android.widget.ListView;

import com.example.asus.cvaproperties.DATA.DataApp;
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


        client.get("http://192.168.1.10:5000/api/v1.0/datos_anuncio",new  JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    System.out.println("****************************** entro al object array " + response);

                    for(int i=0; i< response.length(); i++){
                        JSONObject itemJsom = response.getJSONObject(i);
                        String idimdb = itemJsom.getString("_id");
                        String precio = itemJsom.getString("precio_a");
                        String nombre = itemJsom.getString("frase_destacada_a");
                        String ubicacion = itemJsom.getString("ubicacion_a");
                        String superficie = itemJsom.getString("superficie_a");
                        String num_habitaciones = itemJsom.getString("num_banos_a");
                        String num_plantas = itemJsom.getString("num_plantas_a");
                        JSONArray listGalery = itemJsom.getJSONArray("gallery");
                        ArrayList<String> urlList = new ArrayList<String>();
                        for (int j=0; j < listGalery.length(); j++){
                        urlList.add(DataApp.HOST + listGalery.getString(j));
                        }
                                           //  System.out.println("****************************** la ubicacion es => " + ubicacion);
                        ItemList item = new ItemList (urlList.get(0),idimdb,precio+" $", nombre, ubicacion, "Sup: "+superficie, "Hab: "+num_habitaciones, "Plant: "+num_plantas);
                        LISTINFO.add(item);

                    }

                    ADAPTER = new CustomAdapter(root,LISTINFO);

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
