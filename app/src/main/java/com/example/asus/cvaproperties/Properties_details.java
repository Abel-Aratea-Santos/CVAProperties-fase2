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

public class Properties_details extends AppCompatActivity implements OnLoadImage{

    public  String idImdb;
    protected TextView nombre_pd, email_pd, cel_pd, operacion_pd, tipo_pd, precio_pd, superficie_pd, hab_pd, banos_pd, plantas_pd, ascensor_pd, aire_pd, calefaccion_pd, frase_pd, ubicacion_pd, descripcion_pd;
    protected ImageView img_pd;
    protected PropertiesDetails DATA;
    protected Properties_details root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root =this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idImdb = this.getIntent().getExtras().getString("id");
        loadComponents();
        loadAsyncData ();


    }

    private void loadAsyncData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.43.47:5000/api/v1.0/datos_anuncio/"+this.idImdb,new JsonHttpResponseHandler(){
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
        imgload.setLoadImage(this.img_pd,this);

        this.nombre_pd.setText(DATA.getNombre_a());
        this.email_pd.setText(DATA.getEmail_a());
        this.cel_pd.setText(DATA.getTelefono_a());
        this.operacion_pd.setText(DATA.getOperacion_a());
        this.tipo_pd.setText(DATA.getTipo_inmueble_a());
        this.precio_pd.setText(DATA.getPrecio_a());
        this.superficie_pd.setText(DATA.getSuperficie_a());
        this.hab_pd.setText(DATA.getNum_hab_a());
        this.banos_pd.setText(DATA.getNum_banos_a());
        this.plantas_pd.setText(DATA.getNum_plantas_a());
        this.ascensor_pd.setText(DATA.getAscensor_a());
        this.aire_pd.setText(DATA.getAire_a());
        this.calefaccion_pd.setText(DATA.getCalefaccion_a());
        this.frase_pd.setText(DATA.getFrase_destacada_a());
        this.ubicacion_pd.setText(DATA.getUbicacion_a());
        this.descripcion_pd.setText(DATA.getObservaciones_a());

    }
    private void loadComponents() {
        this.nombre_pd = (TextView) this.findViewById(R.id.nombre_pd);
        this.email_pd = (TextView) this.findViewById(R.id.email_pd);
        this.cel_pd = (TextView) this.findViewById(R.id.cel_pd);
        this.operacion_pd = (TextView) this.findViewById(R.id.operacion_pd);
        this.tipo_pd = (TextView) this.findViewById(R.id.tipo_pd);
        this.precio_pd = (TextView) this.findViewById(R.id.precio_pd);
        this.superficie_pd = (TextView) this.findViewById(R.id.superficie_pd);
        this.hab_pd = (TextView) this.findViewById(R.id.hab_pd);
        this.banos_pd = (TextView) this.findViewById(R.id.banos_pd);
        this.plantas_pd = (TextView) this.findViewById(R.id.plantas_pd);
        this.ascensor_pd = (TextView) this.findViewById(R.id.ascensor_pd);
        this.aire_pd = (TextView) this.findViewById(R.id.aire_pd);
        this.calefaccion_pd = (TextView) this.findViewById(R.id.calefaccion_pd);
        this.frase_pd = (TextView) this.findViewById(R.id.frase_pd);
        this.ubicacion_pd = (TextView) this.findViewById(R.id.ubicacion_pd);
        this.descripcion_pd = (TextView) this.findViewById(R.id.descripcion_pd);

        this.img_pd = (ImageView)this.findViewById(R.id.img_pd);
     }

    @Override
    public void setLoadImage(ImageView container, Bitmap img) {
        container.setImageBitmap(img);
    }
}
