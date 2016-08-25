package com.photo2me.photo2me;

import java.util.Date;

/**
 * Created by Fabio on 25/08/2016.
 */
public class Festa {
    private String apelido;
    private Date dataInicio;
    private Date dataFim;
    private Boolean ativa;

    public Festa(String apelido, Date dataInicio, Date dataFim, Boolean ativa){
        this.apelido = apelido;
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.ativa = ativa;
    }

    public String getApelido(){return apelido;}
    public Date getDataInicio(){return dataInicio;}
    public Date getDataFim(){return dataFim;}
    public Boolean getAtiva(){return ativa;}
}
