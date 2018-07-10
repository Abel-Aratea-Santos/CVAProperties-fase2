package com.example.asus.cvaproperties.ListDataSource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.cvaproperties.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomAdapterA extends BaseAdapter {
    private Context CONTEXT;
    private ArrayList<ItemList> LIST;

    public CustomAdapterA(Context context, ArrayList<ItemList> list)
    {
        System.out.println("----------------- entro aqui tamanio lista => " + list.size());
        this.CONTEXT = context;
        this.LIST = list;
    }
    @Override
    public int getCount() {
        return this.LIST.size();
    }

    @Override
    public Object getItem(int position) {
        return this.LIST.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView == null) {
            LayoutInflater inflate = (LayoutInflater) this.CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.item_layout_agente,null);

            }
        TextView nombre_a = (TextView)convertView.findViewById(R.id.name_agente);
        TextView email_a = (TextView)convertView.findViewById(R.id.email_agente);
        TextView tel_a = (TextView)convertView.findViewById(R.id.tel_agente);
        TextView exp_a = (TextView)convertView.findViewById(R.id.exp_agente);
        TextView des_a = (TextView)convertView.findViewById(R.id.des_agent);

        nombre_a.setText(this.LIST.get(position).getNombre_agente());
        email_a.setText(this.LIST.get(position).getEmail_agente());
        tel_a.setText(this.LIST.get(position).getTel_agente());
        exp_a.setText(this.LIST.get(position).getExp_agente());
        des_a.setText(this.LIST.get(position).getDes_agent());

        ImageView img = (ImageView)convertView.findViewById(R.id.img_agente);
/*
        try {
            URL url = new URL(this.LIST.get(position).getImg_casa_i());
            InputStream stream =url.openConnection().getInputStream();
            Bitmap imageBitmap = BitmapFactory.decodeStream(stream);
            img.setImageBitmap(imageBitmap);

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        return convertView;
    }









}
