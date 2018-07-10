package com.example.asus.cvaproperties;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class LatLon_Mapas extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private MarkerOptions marker;
    private Context root;
    private Marker marcador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = this;
        //marker = new ArrayList<MarkerOptions>();
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MapView map = this.findViewById(R.id.mapView);
        if (map != null) {
            map.onCreate(null);
            map.onResume();
            // Set the map ready callback to receive the GoogleMap object
            map.getMapAsync(this);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (marker != null) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params= new RequestParams();
                    params.put("lat_a",marker.getPosition().latitude);
                    params.put("lon_a",marker.getPosition().longitude);
                    client.patch("http://192.168.1.6:5000/api/v1.0/mapa/5b4418486d92f4342708803e",params,new JsonHttpResponseHandler(){

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            // lleva a  camara
                            Intent camera = new Intent(root, MainActivity.class);
                            root.startActivity(camera);

                            //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
                        }
                    });
                }
            }
        });
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng potosi = new LatLng(-19.578297, -65.758633);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(potosi, 14));
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
