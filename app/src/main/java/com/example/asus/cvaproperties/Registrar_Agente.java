package com.example.asus.cvaproperties;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.cvaproperties.DATA.DataApp;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Registrar_Agente extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__agente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        loadComponents ();
    }

    private void loadComponents() {
        Button btn = (Button) this.findViewById(R.id.btn_enviar_form);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        TextView nombre_a = (TextView) this.findViewById(R.id.nombre_agente_f);
        TextView email_a = (TextView) this.findViewById(R.id.email_agente_f);
        TextView tel_a = (TextView) this.findViewById(R.id.tel_agente_f);
        TextView anos_a = (TextView) this.findViewById(R.id.anos_experiencia_f);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("nombre_agente", nombre_a.getText());
        params.put("email_agente", email_a.getText());
        params.put("tel_agente", tel_a.getText());
        params.put("años_agente", anos_a.getText());


        client.post(DataApp.REST_AGENTE_POST, params, new JsonHttpResponseHandler() {


        });

        Toast.makeText(getApplicationContext()," Registrado con exito ",Toast.LENGTH_LONG).show();

    }
}
