package com.example.asus.cvaproperties.ListDataSource;


public class ItemList {
    private String id_age;
    private String nombre_agente;
    private String email_agente;
    private String tel_agente;
    private String exp_agente;
    private String des_agent;
    private String img_casa_i;
    private String precio_i;
    private String nombre_casa_i;
    private String ubicacion_i;
    private String superficie_i;
    private String num_habitaciones_i;
    private String num_plantas_i;
    private String idimdb;
    private String tipo_inmu_i;
    private String operacion_i;


    public ItemList(String img_casa_i,String precio_i,String nombre_casa_i, String ubicacion_i, String superficie_i, String num_habitaciones_i, String num_plantas_i, String idimdb, String tipo_inmu_i,String operacion_i){
        this.img_casa_i=img_casa_i;
        this.precio_i = precio_i;
        this.nombre_casa_i = nombre_casa_i;
        this.ubicacion_i = ubicacion_i;
        this.superficie_i = superficie_i;
        this.num_habitaciones_i = num_habitaciones_i;
        this.num_plantas_i = num_plantas_i;
        this.idimdb = idimdb;
        this.tipo_inmu_i = tipo_inmu_i;
        this.operacion_i = operacion_i;




    }

    public ItemList(String img_casa_i, String idimdb, String precio_i,String nombre_casa_i, String ubicacion_i, String superficie_i, String num_habitaciones_i, String num_plantas_i){
        this.img_casa_i=img_casa_i;
        this.idimdb = idimdb;
        this.precio_i = precio_i;
        this.nombre_casa_i = nombre_casa_i;
        this.ubicacion_i = ubicacion_i;
        this.superficie_i = superficie_i;
        this.num_habitaciones_i = num_habitaciones_i;
        this.num_plantas_i = num_plantas_i;

    }

    public ItemList(String id_age, String nombre_agente, String email_agente, String tel_agente, String exp_agente, String des_agent){
        this.id_age = id_age;
        this.nombre_agente = nombre_agente;
        this.email_agente = email_agente;
        this.tel_agente = tel_agente;
        this.exp_agente = exp_agente;
        this.des_agent = des_agent;
    }

    public String getDes_agent() {
        return this.des_agent;
    }

    public String getId_age() {
        return this.id_age;
    }

    public String getNombre_agente() {
        return this.nombre_agente;
    }

    public String getEmail_agente() {
        return this.email_agente;
    }

    public String getTel_agente() {
        return this.tel_agente;
    }

    public String getExp_agente() {
        return this.exp_agente;
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

    public String getTipo_inmu_i() {
        return this.tipo_inmu_i;
    }

    public String getOperacion_i() {
        return this.operacion_i;
    }
}