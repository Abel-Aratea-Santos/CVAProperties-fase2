package com.example.asus.cvaproperties;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {


    //private ImageView avatar;
    private TextView nombre;
    private TextView email;
    private TextView identificador;
    private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //avatar = (ImageView) findViewById(R.id.avatar);
        nombre = (TextView)findViewById(R.id.nombre);
        email = (TextView)findViewById(R.id.email);
        identificador = (TextView)findViewById(R.id.identificador);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();



/*
        portada = this.getIntent().getExtras().getString("portada");
        email = this.getIntent().getExtras().getString("email");
        nombre = this.getIntent().getExtras().getString("nombre");
*/
/*
        //VERIFICO SI SE LEGEO
        Bundle datos = this.getIntent().getExtras();
        //int aux_login = datos.getInt("logear", 0);
        if(datos == null)
        {
            Intent intencion = new Intent(this, Registrar_CVA.class);
            startActivity(intencion);
            finish();

        }
        //FIN DE VERIFICO
*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
     if(result.isSuccess()){
         GoogleSignInAccount account = result.getSignInAccount();
         nombre.setText(account.getDisplayName());
         email.setText(account.getEmail());
         identificador.setText(account.getId());

         //Glide.with(this).load(account.getPhotoUrl()).into(avatar);
        }else{
         goLoginInScreen();
     }

    }

    private void goLoginInScreen() {
        Intent intent = new Intent(this,Registrar_CVA.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.home:
                i = new Intent(MainActivity.this, Home_CVA.class);
                startActivity(i);
                //finish();
                return true;
            case R.id.registro:
                i = new Intent(MainActivity.this, Registrar_CVA.class);
                startActivity(i);
                //finish();
                return true;
            case R.id.publicar_anuncio:
                i = new Intent(MainActivity.this, Publicar_anuncio_CVA.class);
                startActivity(i);
                //finish();
                return true;

            case R.id.propiedad:
                i = new Intent(MainActivity.this, Buscar_Propiedad.class);
                startActivity(i);
                //finish();
                return true;

            case R.id.map:
                i = new Intent(MainActivity.this, Buscar_Mapa.class);
                startActivity(i);
                //finish();
                return true;

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void CerrarSesion(View view) {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if(status.isSuccess()){
                    goLoginInScreen();
                }else{
                    Toast.makeText(getApplicationContext(),R.string.CerrarSesion,Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void Delete(View view) {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if(status.isSuccess()){
                    goLoginInScreen();
                }else{
                    Toast.makeText(getApplicationContext(),"Error al Revocar",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
