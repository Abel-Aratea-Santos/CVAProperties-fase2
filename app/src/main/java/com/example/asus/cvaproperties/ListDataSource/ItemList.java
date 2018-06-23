package com.example.asus.cvaproperties.ListDataSource;


public class ItemList {
    private String img_casa;
    private String precio;
    private String caracteristicas;

    public ItemList(String img_casa,String precio,String caracteristicas){
        this.img_casa=img_casa;
        this.precio=precio;
        this.caracteristicas=caracteristicas;
    }

    public String getImg_casa() {
        return this.img_casa;

    }

    public String getPrecio() {

        return this.precio;
    }

    public String getCaracteristicas()
    {
        return this.caracteristicas;
    }
}