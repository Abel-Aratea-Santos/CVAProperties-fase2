package com.example.asus.cvaproperties;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.asus.cvaproperties.DATA.DataApp;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class LatLonMaps extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private MarkerOptions marker;
    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lat_lon_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActivite(toolbar);

        final MapView map = this.findViewById(R.id.mapView);
        if(map!=null){
            map.onCreate(null);
            map.onResume();
            map.getMapAsync(this);
        }

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(marker != null){
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.put("lat_a",marker.getPosition().latitude);
                    params.put("lon_a",marker.getPosition().longitude);
                    client.patch(DataApp.REST_HOME_PATCH+"/5b4415566d92f4342708803c",params,new JsonHttpResponseHandler());
                    Toast.makeText(root,"Exitoso",Toast.LENGTH_LONG).show();
                }

            }
        });

        FloatingActionButton fab1 = (FloatingActionButton)findViewById(R.id.fab2);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salir = new Intent(root,MainActivity.class);
                root.startActivity(salir);
            }
        });
    }

    private void setSupportActivite(Toolbar toolbar) {
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

        // Add a marker in Sydney and move the camera
        LatLng potosi = new LatLng(-19.578297, -65.758633);

       // mMap.addMarker(new MarkerOptions().position(potosi).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi,14));
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(this,latLng.toString(),Toast.LENGTH_SHORT).show();
        mMap.clear();
        marker = new MarkerOptions();
        marker.position(latLng);
        marker.title("Homes");
        marker.draggable(true);
        mMap.addMarker(marker);
    }
}
