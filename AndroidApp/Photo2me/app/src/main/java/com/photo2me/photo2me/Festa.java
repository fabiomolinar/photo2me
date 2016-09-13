package com.photo2me.photo2me;

import com.orm.SugarRecord;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Fabio on 25/08/2016.
 */
public class Festa extends SugarRecord{
    private String apelido;
    private String nome;
    private String dataInicio;
    private String dataFim;
    private String timezone;
    private String idFestaUsuario;
    private Boolean ativa;
    private Boolean finalizada;

    //Adicionando construtor padrão necessário para o Sugar ORM
    public Festa(){

    }
    public Festa(String apelido, String nome, String dataInicio, String dataFim, String timezone){
        this.apelido = apelido;
        this.nome = nome;
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.timezone = timezone;
        this.ativa = false;
        this.finalizada = false;
    }
    public Festa(Festa festa){
        this.apelido = festa.getApelido();
        this.nome = festa.getNome();
        this.dataInicio = festa.getDataInicio();
        this.dataFim = festa.getDataFim();
        this.timezone = festa.getTimezone();
        this.idFestaUsuario = festa.getIdFestaUsuario();
        this.ativa = false;
        this.finalizada = false;
    }

    public String getApelido(){return apelido;}
    public String getNome(){return nome;}
    public String getDataInicio(){return dataInicio;}
    public LocalDateTime getDataInicioJoda(Locale locale){
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dtf.withLocale(locale);
        return dtf.parseLocalDateTime(this.dataInicio);
    }
    public String getDataFim(){return dataFim;}
    public LocalDateTime getDataFimJoda(Locale locale){
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dtf.withLocale(locale);
        return dtf.parseLocalDateTime(this.dataFim);
    }
    public Boolean getAtiva(){return ativa;}
    public String getTimezone(){return timezone;}
    public String getIdFestaUsuario(){return  idFestaUsuario;}
    public void setAtiva(Boolean ativa){this.ativa = ativa;}
    public void setFinalizada(Boolean finalizada){this.finalizada = finalizada;}
    public void setDataInicio(String data){this.dataInicio = data;}
    public void setDataFim(String data){this.dataFim = data;}
    public void setIdFestaUsuario(String id){this.idFestaUsuario = id;}

    public String toString(){
        return "classe Festa - " +
                "apelido: " + apelido + ";" +
                " nome: " + nome + ";" +
                " dataInicio: " + dataInicio + ";" +
                " dataFim: " + dataFim + ";" +
                " ativa: " + ativa + ";" +
                " timezoe: " + timezone;
    }
}
