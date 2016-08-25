package com.photo2me.photo2me;

import java.util.Date;

/**
 * Created by Fabio on 25/08/2016.
 */
public class Foto {
    private String nome;
    private Date dataTirada;
    private Boolean comunicada;
    private Boolean enviada;

    public Foto(String nome, Date dataTirada, Boolean comunicada, Boolean enviada){
        this.nome = nome;
        this.dataTirada = dataTirada;
        this.comunicada = comunicada;
        this.enviada = enviada;
    }

    public String getNome(){return nome;}
    public Date getDataTirada(){return dataTirada;}
    public Boolean getComunicada(){return comunicada;}
    public Boolean getEnviada(){return enviada;}

}
