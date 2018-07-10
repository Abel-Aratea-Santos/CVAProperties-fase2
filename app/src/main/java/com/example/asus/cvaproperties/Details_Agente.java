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
import com.example.asus.cvaproperties.Data_agent.agent_details;
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

public class Details_Agente extends AppCompatActivity implements OnLoadImage{

    public  String idImdb;
    protected TextView nombre_age,email_age,tel_age,anos_age,des_age;
    protected ImageView img_age;
    protected agent_details DATA;
    protected Details_Agente root;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__agente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idImdb = this.getIntent().getExtras().getString("id");
        loadAsyncData();
    }

        private void loadAsyncData() {
            AsyncHttpClient client = new AsyncHttpClient();
            client.get("http://192.168.1.6:5000/api/v1.0/agente/"+this.idImdb,new JsonHttpResponseHandler(){
                // System.out.println("entro a la api");
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {
                        String nombre_ag = response.getString("nombre_a");
                        String email_ag = response.getString("email_a");
                        String telefono_ag = response.getString("telefono_a");
                        String anos_ag = response.getString("operacion_a");
                        String des_ag = response.getString("tipo_inmueble_a");


                       /* JSONArray listGalery = response.getJSONArray("galery");
                        ArrayList<String> urlList = new ArrayList<String>();
                        for (int j=0; j < listGalery.length(); j++){
                            urlList.add(DataApp.HOST + listGalery.getString(j));
                        }*/
                        DATA = new agent_details (nombre_ag,email_ag,telefono_ag,anos_ag,des_ag);
                        root.setInformation();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
            private void setInformation(){
              /*  Taskimg imgload = new Taskimg();
                imgload.execute(DATA.getImg_a());
                imgload.setLoadImage(this.img_pd,this);
*/
                this.nombre_age.setText(DATA.getNombre_ag());
                this.email_age.setText(DATA.getEmail_ag());
                this.tel_age.setText(DATA.getTelefono_ag());
                this.anos_age.setText(DATA.getAno_ag());
                this.des_age.setText(DATA.getDes_ag());


            }

            private void loadComponents() {
                this.nombre_age = (TextView) this.findViewById(R.id.NOMBRE_AGE);
                this.email_age = (TextView) this.findViewById(R.id.EMA_AGE);
                this.tel_age = (TextView) this.findViewById(R.id.TEL_AGE);
                this.anos_age = (TextView) this.findViewById(R.id.ANO_AGE);
                this.des_age = (TextView) this.findViewById(R.id.DES_AGE);
                //this.precio_pd = (TextView) this.findViewById(R.id.precio_pd);


                //this.img_pd = (ImageView)this.findViewById(R.id.img_pd);
            }

            @Override
            public void setLoadImage(ImageView container, Bitmap img) {
                //container.setImageBitmap(img);
            }


    }