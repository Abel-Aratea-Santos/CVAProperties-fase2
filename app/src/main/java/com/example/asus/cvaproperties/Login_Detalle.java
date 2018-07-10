package com.example.asus.cvaproperties;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.cvaproperties.ListDataSource.OnLoadImage;
import com.example.asus.cvaproperties.ListDataSource.Taskimg;

public class Login_Detalle extends AppCompatActivity implements OnLoadImage {
    private String avatar_ld, nombre_ld, email_ld;
    private Context root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__detalle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        avatar_ld = this.getIntent().getExtras().getString("avatar");
        nombre_ld = this.getIntent().getExtras().getString("nombre");
        email_ld = this.getIntent().getExtras().getString("email");


        loadComponents();

    }

    private void loadComponents() {
        TextView name_l = (TextView)this.findViewById(R.id.nombre_login);
        TextView email_l = (TextView)this.findViewById(R.id.email_login);
        ImageView img_ld = (ImageView) this.findViewById(R.id.img_login);
        Button btn = (Button) this.findViewById(R.id.btn_login_2);
        Button btn1 = (Button)this.findViewById(R.id.buscar_lo_que_quiero);
        Button btn2 = (Button)this.findViewById(R.id.btn_agentes);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("....................... click en registrarse");
                Intent intent = new Intent (v.getContext(), Buscar_Propiedad.class);
                startActivityForResult(intent, 0);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("....................... click en registrarse");
                Intent intent = new Intent (v.getContext(), Registrar_Agente.class);
                startActivityForResult(intent, 0);
            }
        });



        name_l.setText(nombre_ld);
        email_l.setText(email_ld);
        Taskimg loadimag = new Taskimg();
        loadimag.execute(avatar_ld);
        loadimag.setLoadImage(img_ld, this );
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(root,MainActivity.class);
                root.startActivity(main);
            }
        });
    }


    @Override
    public void setLoadImage(ImageView container, Bitmap img) {
        container.setImageBitmap(img);

    }
}
