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
import com.example.asus.cvaproperties.ListDataSource.CustomAdapterA;
import com.example.asus.cvaproperties.ListDataSource.ItemList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Agentes extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView LIST;
    private ArrayList<ItemList> LISTINFO;
    private CustomAdapterA ADAPTERA;
    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        root = this;
        LISTINFO = new ArrayList<ItemList>();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agentes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LIST = (ListView) this.findViewById(R.id.ListViewCasas);

        loadInitialRestData();
        loadComponents();




    }
    private void loadInitialRestData() {
        AsyncHttpClient client = new AsyncHttpClient();
        //  String url ="http://192.168.6.142:5000/api/v1.0/inmueble";


        client.get("http://192.168.1.6:5000/api/v1.0/agente",new  JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                try {
                    System.out.println("****************************** entro al object array " + response);

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject itemJsom = response.getJSONObject(i);
                        String idage = itemJsom.getString("_id");
                        String nom_age = itemJsom.getString("nombre_agente");
                        String email_age = itemJsom.getString("email_agente");
                        String tel_ag = itemJsom.getString("tel_agente");
                        String años_ag = itemJsom.getString("años_agente");
                        String des_ag = itemJsom.getString("descripcion_agente");

                    ItemList item = new ItemList(idage,nom_age,email_age,tel_ag,años_ag,des_ag);
                    LISTINFO.add(item);

                }

                ADAPTERA = new CustomAdapterA(root,LISTINFO);

                LIST.setAdapter(ADAPTERA);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    });
}
    private void loadComponents() {
        System.out.println("------------- loadComponents");
        LIST = (ListView) this.findViewById(R.id.ListViewAgentes);

        //acceso a propiedades de inmuebles
        LIST.setOnItemClickListener(this);

        LIST.setAdapter(ADAPTERA);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String id_a = this.LISTINFO.get(position).getId_age();
        Intent inmu_detalles = new Intent(this,Details_Agente.class);
        inmu_detalles.putExtra("id", id_a);
        this.startActivity(inmu_detalles);
    }
}
