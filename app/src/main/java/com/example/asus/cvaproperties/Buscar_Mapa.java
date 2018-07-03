package com.example.asus.cvaproperties;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Buscar_Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener{

    private GoogleMap mMap;
    private UiSettings uiSettings;
    private Polygon miPoligono;
    private ArrayList<LatLng> points;
    private boolean isInadd = false;
    private ArrayList<Marker> listMarkers;
    private Button enviar;
    private Button limpiar;
    private Button agregar;
    private Button remover;
    private Button poligono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)this.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        listMarkers = new ArrayList<Marker>();

        enviar = this.findViewById(R.id.enviar);
        enviar.setOnClickListener(this);
        limpiar = this.findViewById(R.id.limpiar);
        limpiar.setOnClickListener(this);
        agregar = this.findViewById(R.id.marcar);
        agregar.setOnClickListener(this);
        remover = this.findViewById(R.id.desmarcar);
        remover.setOnClickListener(this);
        poligono = this.findViewById(R.id.Poligono);
        poligono.setOnClickListener(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setScrollGesturesEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng Potosi = new LatLng(-19.5788458, -65.7586330);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Potosi,15));

        mMap.setOnMapClickListener(this);
        points = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Poligono:
                if(isInadd = true){
                    isInadd = false;
                }else{
                    isInadd = true;
                }
                Toast.makeText(this,"En Mantenimiento",Toast.LENGTH_SHORT).show();
                break;
            case R.id.marcar:
                if(isInadd = true){
                    isInadd = false;
                }else{
                    isInadd = true;
                }
                Toast.makeText(this,"Ya Puedes Agregar Un Marcador",Toast.LENGTH_SHORT).show();
                msjtxt();
                break;
            case R.id.desmarcar:
                if(listMarkers.size()>0) {
                    Marker aux = listMarkers.get(listMarkers.size() - 1);
                    aux.remove();
                    listMarkers.remove(listMarkers.size() - 1);
                }
                Toast.makeText(this,"Deshaciendo Marcador",Toast.LENGTH_SHORT).show();
                break;
            case R.id.limpiar:
                if(points.size()>0){
                    mMap.clear();
                    points.clear();
                    miPoligono.remove();
                    miPoligono =  null ;
                }
                Toast.makeText(this,"Como Nuevo!!",Toast.LENGTH_SHORT).show();
                break;

            case R.id.enviar:
                RequestParams params = new RequestParams();
                String send="";
                for(int i=0;i < listMarkers.size();i++ ){
                    LatLng coor = listMarkers.get(i).getPosition();
                    send +="["+coor.latitude+","+coor.longitude+"]";
                }
                params.put("coor",send);
                AsyncHttpClient client = new AsyncHttpClient();
                client.post("", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        msjToasSuccess();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        msjToasFail();
                    }
                });
                Toast.makeText(this,"Enviar",Toast.LENGTH_SHORT).show();
        }
    }
    private void msjToasFail(){
        Toast.makeText(this,"Fallo en la Inserción", Toast.LENGTH_LONG).show();
    }

    private void msjToasSuccess(){
        Toast.makeText(this,"Se Inserto Exitosamente!", Toast.LENGTH_LONG).show();
    }

    private void msjtxt() {
        if(isInadd = true){
            Toast.makeText(this,"Agregando Marcador",Toast.LENGTH_LONG).show();
        }else{

        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(isInadd){
            Marker m = mMap.addMarker(new MarkerOptions().position(latLng).title(":D"));
            listMarkers.add(m);
            Toast.makeText(this,latLng.toString(),Toast.LENGTH_SHORT).show();
        }
        if(isInadd){
            Toast.makeText(this,latLng.toString(),Toast.LENGTH_SHORT).show();
            PolygonOptions polOptions = new PolygonOptions()
                    .add(latLng)
                    .strokeColor(Color.RED)
                    .fillColor(Color.argb(50,50,50,50));
            miPoligono = mMap.addPolygon(polOptions);
            points.add(latLng);
        }else{/*
            points.add(latLng);
            miPoligono.setPoints(points);*/
        }
    }
}
