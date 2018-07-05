package com.example.asus.cvaproperties;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.cvaproperties.DATA.DataApp;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Formulario_Registro_CVA extends AppCompatActivity implements View.OnClickListener {

    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__registro__cv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LoadComponents();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();




            }
        });
    }

    private void LoadComponents() {
        Button btn = (Button) this.findViewById(R.id.btn_enviar_form);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView nombre_f = (TextView) this.findViewById(R.id.nombre_f);
        TextView email_f = (TextView) this.findViewById(R.id.email_f);
        TextView contrasena_f = (TextView) this.findViewById(R.id.contraseña_f);
        TextView contrasena_f_c = (TextView) this.findViewById(R.id.repetir_contraseña_f);


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("nombre_u", nombre_f.getText());
        params.put("email_u", email_f.getText());
        params.put("contrasena_u", contrasena_f.getText());
        params.put("contrasena_u_c", contrasena_f_c.getText());


        client.post(DataApp.REST_USER_POST, params, new JsonHttpResponseHandler() {

        });
    }

}