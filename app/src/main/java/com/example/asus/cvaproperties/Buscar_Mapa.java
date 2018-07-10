package com.example.asus.cvaproperties;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

public class Buscar_Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    static ArrayList<Marker> marcas=new ArrayList<>();
    HashMap<String,LatLng> casas=new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

    }


    private void run() {
        AsyncHttpClient client =new AsyncHttpClient();
        client.get("http://192.168.1.10:5000/api/v1.0/mapa/", null, new JsonHttpResponseHandler() {
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
                        Toast.makeText(getApplicationContext(),aux2+"",Toast.LENGTH_SHORT).show();
                        String id=aux2.getString("_id");
                        double lat=Double.parseDouble(aux2.getString("lat_a"));
                        double lon=Double.parseDouble(aux2.getString("lon_a"));
                        casas.put((String) aux2.get("_id"),new LatLng(lat,lon));
                    }

                    /*for(int i=0;i<citas.size();i++){
                        ArrayList<String> x=citas.get(i);
                        Double lat=Double.parseDouble(x.get(9));
                        Double lon=Double.parseDouble(x.get(13));
                        if(x.get(3).equals("venta")){
                            venta.put(x.get(4),new LatLng(lat,lon));
                        }else{
                            alquiler.put(x.get(4),new LatLng(lat,lon));
                        }
                        if(x.get(8).equals("casa")){
                            casa.put(x.get(4),new LatLng(lat,lon));
                        }
                        else{
                            departamento.put(x.get(4),new LatLng(lat,lon));
                        }
                        listar.put(x.get(4),new LatLng(lat,lon));
                    }*/

                }
                catch (Exception e)
                {

                };
            }
        });
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
}








