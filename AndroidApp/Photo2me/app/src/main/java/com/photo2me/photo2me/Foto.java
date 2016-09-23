package com.photo2me.photo2me;

import com.orm.SugarRecord;

import java.io.File;
import java.util.Date;

/**
 * Created by Fabio on 25/08/2016.
 */
public class Foto extends SugarRecord{
    private String nome;
    private String uri;
    private long lastModified;
    private Boolean comunicada;
    private Boolean enviada;

    //Adicionando construtor padrão necessário para o Sugar ORM
    public Foto(){

    }
    public Foto(String nome, String uri, long lastModified){
        this.nome = nome;
        this.uri = uri;
        this.lastModified = lastModified;
    }
    public Foto(File foto){
        this.nome = foto.getName();
        this.uri = foto.getAbsolutePath();
        this.lastModified = foto.lastModified();
    }

    public String getNome(){return nome;}
    public String getUri(){return uri;}
    public long getLastModified(){return lastModified;}
    public Boolean getComunicada(){return comunicada;}
    public Boolean getEnviada(){return enviada;}
    public void setEnviada(Boolean enviada){this.enviada = enviada;}
    public String toString(){
        return "classe Foto - " +
                "nome: " + nome + ";" +
                " uri: " + uri + ";" +
                " lastModified: " + lastModified;
    }

}
