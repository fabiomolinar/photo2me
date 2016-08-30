package com.photo2me.photo2me;

/**
 * Created by Fabio on 27/08/2016.
 */
public class Usuario {
    private String idUsuarioFesta;
    private Boolean ativo;

    public Usuario(String idUsuarioFesta){
        this.idUsuarioFesta = idUsuarioFesta;
        this.ativo = true;
    }

    public String getIdUsuarioFesta(){return idUsuarioFesta;}
    public void setAtivo(Boolean ativo){this.ativo = ativo;}
}
