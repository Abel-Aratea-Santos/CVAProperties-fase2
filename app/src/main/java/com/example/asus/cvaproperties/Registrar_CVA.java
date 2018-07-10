package com.example.asus.cvaproperties;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;
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


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class Registrar_CVA extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private Context root;
    GoogleApiClient client;
    private int GOOGLE_CODE = 11235;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__cv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        client = new GoogleApiClient.Builder(this).enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,options)
                .build();

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

        loadcomponentes();


    }

    private void loadcomponentes() {
      SignInButton goglebtn = (SignInButton)this.findViewById(R.id.googlebutton);
      goglebtn.setOnClickListener(new View.OnClickListener(){

          @Override
          public void onClick(View v) {
               Intent intent = Auth.GoogleSignInApi.getSignInIntent(client);
               startActivityForResult(intent,GOOGLE_CODE);
          }
      });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GOOGLE_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                Intent login_r = new Intent(this, Login_Detalle.class);
                Uri photo = result.getSignInAccount().getPhotoUrl();
//                String url_foto = "https://lh3.googleusercontent.com" + photo.getPath();

  //              login_r.putExtra("avatar",url_foto);
                login_r.putExtra("nombre", result.getSignInAccount().getDisplayName());
                login_r.putExtra("email", result.getSignInAccount().getEmail());
                startActivity(login_r);
                //esult.getSignInAccount().

                Toast.makeText(this, "logueado", Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(this, "EROOR", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {




    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
