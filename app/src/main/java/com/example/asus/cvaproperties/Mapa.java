package com.example.asus.cvaproperties;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import cz.msebera.android.httpclient.Header;

public class Mapa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    static ArrayList<Marker> marcas=new ArrayList<>();
    TreeMap<String,LatLng> casas=new TreeMap<>();
    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
       run();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab2 = (FloatingActionButton)findViewById(R.id.fab3);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salir = new Intent(root, MainActivity.class);
                root.startActivity(salir);
            }
        });

    }


    private void run() {
        AsyncHttpClient client =new AsyncHttpClient();
        client.get("http://192.168.1.6:5000/api/v1.0/datos_anuncio/", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                JSONArray aux =timeline;
                try
                {
                    JSONArray jsonArray = timeline;
                    //https://es.androids.help/q28175
                    //ArrayList<String> datos=new ArrayList<>();
                    for (int i=0;i<jsonArray.length();i++){
                        // citas.add(llenar(jsonArray.get(i)));
                        //jsonArray.get(i);
                        JSONObject aux2= (JSONObject) jsonArray.get(i);
                        //Toast.makeText(getApplicationContext(),aux2+"",Toast.LENGTH_SHORT).show();
                        String id=aux2.getString("_id");
                        double lat=Double.parseDouble(aux2.getString("lat_a"));
                        double lon=Double.parseDouble(aux2.getString("lon_a"));
                        LatLng lt=new LatLng(lat,lon);
                        casas.put(id,lt);
                        Toast.makeText(getApplicationContext(),casas.size()+"",Toast.LENGTH_SHORT).show();

                    }

                }
                catch (Exception e)
                {

                };
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync( this);
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng potosi=new LatLng(-19.588530,-65.754172);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi,14));
        googleMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
        Toast.makeText(getApplicationContext(),casas.size()+"",Toast.LENGTH_SHORT).show();
        Iterator it=casas.keySet().iterator();
        while (it.hasNext()){
            String id= (String) it.next();
            Marker m=mMap.addMarker(new MarkerOptions().position(casas.get(id)).title(id));
            marcas.add(m);
        }
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent in=null;
        for(int i=0;i<marcas.size();i++) {
            if (marker.equals(marcas.get(i))) {
                //Toast.makeText(getApplicationContext(),"entra",Toast.LENGTH_SHORT).show();
                in = new Intent(this, MainActivity.class);
                in.putExtra("latitud", marker.getPosition().latitude+"");
                in.putExtra("longitud", marker.getPosition().longitude+"");
                in.putExtra("id", marker.getTitle()+"");
                //in.putExtra("ema", email);
                startActivity(in);
            }
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }
}



