package com.example.asus.cvaproperties.Data_agent;

public class agent_details {

    //private String img_a;
    private String nombre_ag;
    private String email_ag;
    private String telefono_ag;
    private String ano_ag;
    private String des_ag;

    public agent_details(
            //String img_a,
            String nombre_ag,
            String email_ag,
            String telefono_ag,
            String ano_ag,
            String des_ag

    ){
        //this.img_a = img_a;
        this.nombre_ag = nombre_ag;
        this.email_ag = email_ag;
        this.telefono_ag = telefono_ag;
        this.ano_ag = ano_ag;
        this.des_ag = des_ag;
    }

    public String getNombre_ag() {
        return this.nombre_ag;
    }

    public String getEmail_ag() {
        return this.email_ag;
    }

    public String getTelefono_ag() {
        return this.telefono_ag;
    }

    public String getAno_ag() {
        return this.ano_ag;
    }

    public String getDes_ag() {
        return this.des_ag;
    }
}
