package com.photo2me.photo2me;

import com.orm.SugarRecord;

import org.joda.time.LocalDateTime;

import java.util.Date;

/**
 * Created by Fabio on 25/08/2016.
 */
public class Festa extends SugarRecord{
    private String apelido;
    private String nome;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String timezone;
    private Boolean ativa;
    private Boolean finalizada;

    //Adicionando construtor padrão necessário para o Sugar ORM
    public Festa(){

    }
    public Festa(String apelido, String nome, LocalDateTime dataInicio, LocalDateTime dataFim, String timezone){
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
        this.ativa = false;
        this.finalizada = false;
    }

    public String getApelido(){return apelido;}
    public String getNome(){return nome;}
    public LocalDateTime getDataInicio(){return dataInicio;}
    public LocalDateTime getDataFim(){return dataFim;}
    public Boolean getAtiva(){return ativa;}
    public String getTimezone(){return timezone;}
    public void setAtiva(Boolean ativa){this.ativa = ativa;}
    public void setFinalizada(Boolean finalizada){this.finalizada = finalizada;}
    public void setDataInicio(LocalDateTime data){this.dataInicio = data;}
    public void setDataFim(LocalDateTime data){this.dataFim = data;}

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
