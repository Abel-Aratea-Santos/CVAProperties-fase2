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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class Buscar_Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener{

    private GoogleMap mMap;
    private UiSettings uiSettings;
    private Polygon miPoligono;
    private ArrayList<LatLng> points;
    private Button enviar;
    private Button limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar__mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        enviar = this.findViewById(R.id.enviar);
        enviar.setOnClickListener(this);
        limpiar = this.findViewById(R.id.limpiar);
        limpiar.setOnClickListener(this);
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
        uiSettings.setMyLocationButtonEnabled(true);

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng Potosi = new LatLng(-19.5788458, -65.7586330);
        mMap.addMarker(new MarkerOptions().position(Potosi).title("Estoy en Potosi"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Potosi,15));
        LatLng miCasa = new LatLng(-19.5788458, -65.7586330);
        MarkerOptions markerOptions =
                new MarkerOptions()
                        .position(miCasa)
                        .title("Titulo del Puntero")
                        .snippet("Description")
                        .draggable(true)
                        .flat(true)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(markerOptions);
        mMap.setOnMapClickListener(this);
        points = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.limpiar:mMap.clear();
                points.clear();
                miPoligono.remove();
                miPoligono = null;
                break;
            case R.id.enviar: Toast.makeText(this,"enviar",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(this,latLng.toString(),Toast.LENGTH_SHORT).show();
        if(miPoligono == null){
            PolygonOptions polOptions = new PolygonOptions()
                    .add(latLng)
                    .strokeColor(Color.RED)
                    .fillColor(Color.argb(50,50,50,50));
            miPoligono = mMap.addPolygon(polOptions);
            points.add(latLng);
        }else{
            points.add(latLng);
            miPoligono.setPoints(points);
        }
    }
}
