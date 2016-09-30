package com.photo2me.photo2me;

/**
 * Created by Fabio on 24/08/2016.
 */
public class Preferencias {
    //Nome do arquivo que irá guardar as preferências
    public static final String MINHAS_PREFERENCIAS = "MinhasPreferencias";
    //Nome das variáveis que irão guardar as preferências
    public static final String PREF_BOOL_ACEITOU_TERMOS = "AceitouTermos";
    public static final String PREF_FESTA_ATIVA = "FestaAtiva";
    public static final String PREF_FESTA_PAUSADA = "FestaPausada";
    public static final String PREF_FESTA_INICIO = "FestaInicio";
    public static final String PREF_FESTA_FIM = "FestaFim";

    //Shared preferences
    //Se a variável abaixo for modificada, modificar também o recurso xml chamado "app_preferencias"!!!!!!!
    public static final String APP_UPLOAD_SO_WIFI = "UploadSoWifi";
}
