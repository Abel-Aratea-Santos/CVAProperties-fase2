package com.example.asus.cvaproperties;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.cvaproperties.DATA.DataApp;
import com.example.asus.cvaproperties.DATA.UserData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Publicar_anuncio_CVA extends AppCompatActivity implements View.OnClickListener {

private Context root;
//Button  registrar_x;

String r_op_aux = "", r_as_aux ="", r_ai_aux="", r_ca_aux="";
String s_tipo_inmuheble , s_num_hab, s_num_ba, s_num_pla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        root =this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_anuncio__cv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        System.out.println("///////////// dentro de publicar anuncio");


        LoadComponents();


        Spinner spinner = (Spinner) findViewById(R.id.Spinner_tipo_inmueble_B);
        final String[] letra = { "Departamento","Casa","Terreno/Lote","Oficina","Bodega","Otros"};
        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, letra));
        String text = spinner.getSelectedItem().toString();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                System.out.println("///////////// departamentossssssssss  "+ id);
                s_tipo_inmuheble = letra[pos];
                System.out.println("///////////// tipo inmueble = " + s_tipo_inmuheble);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.num_habitaciones_B);
        final String[] t_n = { "1","2","3","4","5","6","7","8","9","10"};
        spinner1.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t_n));

        String text1 = spinner1.getSelectedItem().toString();
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                System.out.println("///////////// departamentossssssssss");
                s_num_hab = t_n[pos];
                System.out.println("///////////// num_habitaciones = " + s_num_hab);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Spinner spinner2 = (Spinner) findViewById(R.id.num_banos_B);
        spinner2.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t_n));
        String text2 = spinner.getSelectedItem().toString();
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                System.out.println("///////////// departamentossssssssss");
                s_num_ba = t_n[pos];
                //System.out.println("///////////// tipo inmueble = " + s_tipo_inmuheble);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Spinner spinner3 = (Spinner) findViewById(R.id.num_plantas_B);
        spinner3.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t_n));
        String text3 = spinner.getSelectedItem().toString();
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                System.out.println("///////////// departamentossssssssss");
                s_num_pla = t_n[pos];
                //System.out.println("///////////// tipo inmueble = " + s_tipo_inmuheble);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




    }
    public void onRadioButtonClickedOperacion(View view) {

        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();
        r_op_aux =  ((RadioButton) view).getText().toString();
        System.out.println("///////////// r_operacion = " + r_op_aux);
        // hacemos un case con lo que ocurre cada vez que pulsemos un botón

    }

    public void onRadioButtonClickedAscensor(View view) {

        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();
        r_as_aux =  ((RadioButton) view).getText().toString();
        System.out.println("///////////// r_ascensor = " + r_as_aux);
        // hacemos un case con lo que ocurre cada vez que pulsemos un botón

    }

    public void onRadioButtonClickedAireacondicionado(View view) {

        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();
        r_ai_aux =  ((RadioButton) view).getText().toString();
        System.out.println("///////////// r_aire = " + r_ai_aux);
        // hacemos un case con lo que ocurre cada vez que pulsemos un botón

    }

    public void onRadioButtonClickedcalefaccion(View view) {

        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();
        r_ca_aux =  ((RadioButton) view).getText().toString();
        System.out.println("///////////// r_calefaccion = " + r_ca_aux);
        // hacemos un case con lo que ocurre cada vez que pulsemos un botón

    }



    private void LoadComponents() {
        Button btn = (Button)this.findViewById(R.id.registrar_x);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView nombre_x = (TextView)this.findViewById(R.id.nombre_x);
        TextView email_x = (TextView)this.findViewById(R.id.email_x);
        TextView tel_x = (TextView)this.findViewById(R.id.tel_x);
        TextView precio_x = (TextView)this.findViewById(R.id.precio_x);
        TextView superficie_x = (TextView)this.findViewById(R.id.superficie_x);
        TextView frase_x = (TextView)this.findViewById(R.id.frase_x);
        TextView ubicacion_x = (TextView)this.findViewById(R.id.ubicacion_x);
        TextView observaciones_x = (TextView)this.findViewById(R.id.observaciones_x);


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("nombre_a",nombre_x.getText());
        params.put("email_a",email_x.getText());
        params.put("telefono_a",tel_x.getText());
        params.put("operacion_a",r_op_aux);
        params.put("tipo_inmueble_a",s_tipo_inmuheble);
        params.put("precio_a",precio_x.getText());
        params.put("superficie_a",superficie_x.getText());
        params.put("num_hab_a",s_num_hab);
        params.put("num_banos_a",s_num_ba);
        params.put("num_plantas_a",s_num_pla);
        params.put("ascensor_a",r_as_aux);
        params.put("aire_a",r_ai_aux);
        params.put("calefaccion_a",r_ca_aux);
        params.put("frase_destacada_a",frase_x.getText());
        params.put("ubicacion_a",ubicacion_x.getText());
        params.put("observaciones_a",observaciones_x.getText());



        client.post(DataApp.REST_INMUEBLE_POST, params, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    String msn = response.getString("msn");
                    String id = response.getString("imn");

                    Toast.makeText(root, "msn......................  "+msn, Toast.LENGTH_LONG).show();
                    Toast.makeText(root, "msn......................  "+id, Toast.LENGTH_LONG).show();
                    System.out.println("///////////// r_op = " + msn);
                    System.out.println("///////////// r_op = " + id);
                    UserData.ID = id;
                    if (msn != null) {

                        Intent camera = new Intent(root, Camera_Form.class);
                        root.startActivity(camera);
                    } else {
                        Toast.makeText(root, "ERROR AL enviar los datos", Toast.LENGTH_LONG).show();
                    }

                    //-----------------------------

                    if(msn != null){
                        Intent coor = new Intent(root,Lat_lon_Mapas.class);
                        root.startActivity(coor);
                    }else{
                        Toast.makeText(root,"Error al Ubicar casa",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }
        });

    }



    }

