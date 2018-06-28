package com.example.asus.cvaproperties;

import android.content.Context;
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

        loadInitialRestData();
        loadComponents();


    }

    private void loadInitialRestData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.6.142:5000/api/v1.0/inmueble",new  JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray list = (JSONArray) response.get("inmueble");
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
                    }
                    ADAPTER = new CustomAdapter(root,LISTINFO);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

            }

        });
    }

    private void loadComponents() {
        LIST = (ListView) this.findViewById(R.id.ListViewCasas);

        //eventos



        LIST.setAdapter(ADAPTER);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
