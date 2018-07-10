package com.example.asus.cvaproperties;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.cvaproperties.DATA.DataApp;
import com.example.asus.cvaproperties.Data_Propiedad_details.PropertiesDetails;
import com.example.asus.cvaproperties.ListDataSource.OnLoadImage;
import com.example.asus.cvaproperties.ListDataSource.Taskimg;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Mapa_Properties_Details extends AppCompatActivity implements OnLoadImage {
    public  String idImdb;
    protected TextView nombre_ma, email_ma, cel_ma, operacion_ma, tipo_ma, precio_ma, superficie_ma, hab_ma, banos_ma, plantas_ma, ascensor_ma, aire_ma, calefaccion_ma, frase_ma, ubicacion_ma, descripcion_ma;
    protected ImageView img_ma;
    protected PropertiesDetails DATA;
    protected Mapa_Properties_Details root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle b=getIntent().getExtras();
        root =this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa__properties__details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idImdb = b.getString("id");
        loadComponents();
        loadAsyncData ();


    }

    private void loadAsyncData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.1.6:5000/api/v1.0/datos_anuncio/"+this.idImdb,new JsonHttpResponseHandler(){
            // System.out.println("entro a la api");
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    String nombre_a = response.getString("nombre_a");
                    String email_a = response.getString("email_a");
                    String telefono_a = response.getString("telefono_a");
                    String operacion_a = response.getString("operacion_a");
                    String tipo_inmueble_a = response.getString("tipo_inmueble_a");
                    String precio_a = response.getString("precio_a");
                    String superficie_a = response.getString("superficie_a");
                    String num_hab_a = response.getString("num_hab_a");
                    String num_banos_a = response.getString("num_banos_a");
                    String num_plantas_a = response.getString("num_plantas_a");
                    String ascensor_a = response.getString("ascensor_a");
                    String aire_a = response.getString("aire_a");
                    String calefaccion_a = response.getString("calefaccion_a");
                    String frase_destacada_a = response.getString("frase_destacada_a");
                    String ubicacion_a = response.getString("ubicacion_a");
                    String observaciones_a = response.getString("observaciones_a");
                    JSONArray listGalery = response.getJSONArray("gallery");
                    ArrayList<String> urlList = new ArrayList<String>();
                    for (int j=0; j < listGalery.length(); j++){
                        urlList.add(DataApp.HOST + listGalery.getString(j));
                    }
                    DATA = new PropertiesDetails(urlList.get(0),"Nombre : "+nombre_a,"Email : "+email_a,"Telf : "+telefono_a,"Operacion : "+operacion_a,"Tipo : "+tipo_inmueble_a,precio_a+" $","Sup : "+superficie_a,"Hab : "+num_hab_a,"Baños : "+num_banos_a,"Plants : "+num_plantas_a,"Ascensor : "+ascensor_a,"AireAcon : "+aire_a,"Calefac : "+calefaccion_a,"Frase : "+frase_destacada_a,"Ubicacion : "+ubicacion_a,"OBS : "+observaciones_a);
                    root.setInformation();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    private void setInformation(){
        Taskimg imgload = new Taskimg();
        imgload.execute(DATA.getImg_a());
        imgload.setLoadImage(this.img_ma,this);

        this.nombre_ma.setText(DATA.getNombre_a());
        this.email_ma.setText(DATA.getEmail_a());
        this.cel_ma.setText(DATA.getTelefono_a());
        this.operacion_ma.setText(DATA.getOperacion_a());
        this.tipo_ma.setText(DATA.getTipo_inmueble_a());
        this.precio_ma.setText(DATA.getPrecio_a());
        this.superficie_ma.setText(DATA.getSuperficie_a());
        this.hab_ma.setText(DATA.getNum_hab_a());
        this.banos_ma.setText(DATA.getNum_banos_a());
        this.plantas_ma.setText(DATA.getNum_plantas_a());
        this.ascensor_ma.setText(DATA.getAscensor_a());
        this.aire_ma.setText(DATA.getAire_a());
        this.calefaccion_ma.setText(DATA.getCalefaccion_a());
        this.frase_ma.setText(DATA.getFrase_destacada_a());
        this.ubicacion_ma.setText(DATA.getUbicacion_a());
        this.descripcion_ma.setText(DATA.getObservaciones_a());

    }
    private void loadComponents() {
        this.nombre_ma = (TextView) this.findViewById(R.id.nombre_ma);
        this.email_ma = (TextView) this.findViewById(R.id.email_ma);
        this.cel_ma = (TextView) this.findViewById(R.id.cel_ma);
        this.operacion_ma = (TextView) this.findViewById(R.id.operacion_ma);
        this.tipo_ma = (TextView) this.findViewById(R.id.tipo_ma);
        this.precio_ma = (TextView) this.findViewById(R.id.precio_ma);
        this.superficie_ma = (TextView) this.findViewById(R.id.superficie_ma);
        this.hab_ma = (TextView) this.findViewById(R.id.hab_ma);
        this.banos_ma = (TextView) this.findViewById(R.id.banos_ma);
        this.plantas_ma = (TextView) this.findViewById(R.id.plantas_ma);
        this.ascensor_ma = (TextView) this.findViewById(R.id.ascensor_ma);
        this.aire_ma = (TextView) this.findViewById(R.id.aire_ma);
        this.calefaccion_ma = (TextView) this.findViewById(R.id.calefaccion_ma);
        this.frase_ma = (TextView) this.findViewById(R.id.frase_ma);
        this.ubicacion_ma = (TextView) this.findViewById(R.id.ubicacion_ma);
        this.descripcion_ma = (TextView) this.findViewById(R.id.descripcion_ma);

        this.img_ma = (ImageView)this.findViewById(R.id.img_pd);
    }


    @Override
    public void setLoadImage(ImageView container, Bitmap img) {
      // container.setImageBitmap(img);
    }
}
