package com.example.asus.cvaproperties;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class Registrar_CVA extends AppCompatActivity implements View.OnClickListener {

    private Context root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__cv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        System.out.println("....................... dentro de registrar");


        Button btn = (Button) findViewById(R.id.registrarse_r);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("....................... click en registrarse");
                Intent intent = new Intent (v.getContext(), Formulario_Registro_CVA.class);
                startActivityForResult(intent, 0);
            }
        });

        //
        Button btn_ingresar = (Button) findViewById(R.id.btn_enviar_datos);
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("....................... click en login");
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("logear", 1);
                startActivity(intent);
            }
        });

       // loadcomponentes();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /*
    private void loadcomponentes() {
        Button btn = (Button)this.findViewById(R.id.btn_enviar_datos);
        btn.setOnClickListener(this);

    }*/




    @Override
    public void onClick(View v) {


        TextView email_r = (TextView)this.findViewById(R.id.email_r);
        TextView pasword_r = (TextView)this.findViewById(R.id.pasword_r);


    }
}
