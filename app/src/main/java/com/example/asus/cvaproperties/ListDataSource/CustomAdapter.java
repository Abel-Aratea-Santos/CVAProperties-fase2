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




public class CustomAdapter extends BaseAdapter {

    private Context CONTEXT;
    private ArrayList<ItemList> LIST;

    public CustomAdapter(Context context, ArrayList<ItemList> list)
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        System.out.println("------------ dentro del getView id => " + LIST.get(position).getIdimdb());
        System.out.println("------------ dentro del getView precio => " + LIST.get(position).getPrecio_i());
        System.out.println("------------ dentro del getView nombre => " + LIST.get(position).getNombre_casa_i());
        System.out.println("------------ dentro del getView ubicacion => " + LIST.get(position).getUbicacion_i());
        System.out.println("------------ dentro del getView superficie => " + LIST.get(position).getSuperficie_i());
        System.out.println("------------ dentro del getView habitaciones => " + LIST.get(position).getNum_habitaciones_i());
        System.out.println("------------ dentro del getView plantas => " + LIST.get(position).getNum_plantas_i());

        if (convertView == null) {
            LayoutInflater inflate = (LayoutInflater) this.CONTEXT.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.item_layout,null);

        }

        TextView precio_l = (TextView)convertView.findViewById(R.id.precio_l);
        TextView nombre_casa_l = (TextView)convertView.findViewById(R.id.nombre_casa_l);
        TextView ubicacion_l = (TextView)convertView.findViewById(R.id.ubicacion_l);
        TextView superficie_l = (TextView)convertView.findViewById(R.id.superficie_l);
        TextView num_habitaciones_l = (TextView)convertView.findViewById(R.id.num_habitaciones_l);
        TextView num_plantas_l = (TextView)convertView.findViewById(R.id.num_plantas_l);

        precio_l.setText(this.LIST.get(position).getPrecio_i());
        nombre_casa_l.setText(this.LIST.get(position).getNombre_casa_i());
        ubicacion_l.setText(this.LIST.get(position).getUbicacion_i());
        superficie_l.setText(this.LIST.get(position).getSuperficie_i());
        num_habitaciones_l.setText(this.LIST.get(position).getNum_habitaciones_i());
        num_plantas_l.setText(this.LIST.get(position).getNum_plantas_i());
/*
        ImageView img = (ImageView)convertView.findViewById(R.id.img_inmueble_l);

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
