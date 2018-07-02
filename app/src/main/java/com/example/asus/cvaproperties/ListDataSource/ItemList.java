package com.example.asus.cvaproperties.ListDataSource;


public class ItemList {
    private String img_casa_i;
    private String precio_i;
    private String nombre_casa_i;
    private String ubicacion_i;
    private String superficie_i;
    private String num_habitaciones_i;
    private String num_plantas_i;
    private String idimdb;


    public ItemList(String img_casa_i,String precio_i,String nombre_casa_i, String ubicacion_i, String superficie_i, String num_habitaciones_i, String num_plantas_i, String idimdb){
        this.img_casa_i=img_casa_i;
        this.precio_i = precio_i;
        this.nombre_casa_i = nombre_casa_i;
        this.ubicacion_i = ubicacion_i;
        this.superficie_i = superficie_i;
        this.num_habitaciones_i = num_habitaciones_i;
        this.num_plantas_i = num_plantas_i;
        this.idimdb = idimdb;
    }

    public ItemList(String idimdb, String precio_i,String nombre_casa_i, String ubicacion_i, String superficie_i, String num_habitaciones_i, String num_plantas_i){
        //this.img_casa_i=img_casa_i;
        this.idimdb = idimdb;
        this.precio_i = precio_i;
        this.nombre_casa_i = nombre_casa_i;
        this.ubicacion_i = ubicacion_i;
        this.superficie_i = superficie_i;
        this.num_habitaciones_i = num_habitaciones_i;
        this.num_plantas_i = num_plantas_i;

    }

    public String getImg_casa_i() {
        return this.img_casa_i;

    }

    public String getPrecio_i()
    {
        return this.precio_i;
    }

    public String getNombre_casa_i()
    {
        return this.nombre_casa_i;
    }

    public String getUbicacion_i()
    {
        return this.ubicacion_i;
    }

    public String getSuperficie_i()
    {
        return this.superficie_i;
    }

    public String getNum_habitaciones_i()
    {
        return this.num_habitaciones_i;
    }

    public String getNum_plantas_i()
    {
        return this.num_plantas_i;
    }

    public String getIdimdb()
    {
        return this.idimdb;
    }
}