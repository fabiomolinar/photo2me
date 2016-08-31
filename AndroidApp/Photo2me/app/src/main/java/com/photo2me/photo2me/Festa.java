package com.photo2me.photo2me;

import java.util.Date;

/**
 * Created by Fabio on 25/08/2016.
 */
public class Festa {
    private String apelido;
    private String nome;
    private Date dataInicio;
    private Date dataFim;
    private String timezone;
    private Boolean ativa;

    public Festa(String apelido, String nome, Date dataInicio, Date dataFim, String timezone){
        this.apelido = apelido;
        this.nome = nome;
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.timezone = timezone;
        this.ativa = true;
    }

    public String getApelido(){return apelido;}
    public String getNome(){return nome;}
    public Date getDataInicio(){return dataInicio;}
    public Date getDataFim(){return dataFim;}
    public Boolean getAtiva(){return ativa;}
    public void setAtiva(Boolean ativa){this.ativa = ativa;}
}
