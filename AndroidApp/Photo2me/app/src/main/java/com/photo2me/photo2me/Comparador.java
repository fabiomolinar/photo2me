package com.photo2me.photo2me;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

/**
 * Created by Fabio on 12/09/2016.
 */
public class Comparador {
    private DateTime inicio;
    private DateTime fim;
    public String apelido;
    public String idFestaUsuario;

    public Comparador(String inicio, String fim, String timezone, Locale locale, String apelido, String idFestaUsuario){
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dtf.withLocale(locale);
        DateTimeZone timezoneObject = DateTimeZone.forID(timezone);
        LocalDateTime lInicio = dtf.parseLocalDateTime(inicio);
        LocalDateTime lFim = dtf.parseLocalDateTime(fim);
        this.inicio = new DateTime(lInicio.getYear(),lInicio.getMonthOfYear(),lInicio.getDayOfMonth(),
                lInicio.getHourOfDay(),lInicio.getMinuteOfHour(),lInicio.getSecondOfMinute(),lInicio.getMillisOfSecond(),timezoneObject);
        this.fim = new DateTime(lFim.getYear(),lFim.getMonthOfYear(),lFim.getDayOfMonth(),
                lFim.getHourOfDay(),lFim.getMinuteOfHour(),lFim.getSecondOfMinute(),lFim.getMillisOfSecond(),timezoneObject);
        this.apelido = apelido;
        this.idFestaUsuario = idFestaUsuario;
    }

    public Boolean entreInicioEFim(long toTest){
        if (toTest > inicio.getMillis()){
            if (toTest < fim.getMillis()){
                return true;
            }
        }
        return false;
    }
    public String toString(){
        return "classe comparador - " +
                "inicio: " + this.inicio.toString() + ";" +
                "fim: " + this.fim.toString() + ";";
    }
}
