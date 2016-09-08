package com.photo2me.photo2me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class StartActivity extends AppCompatActivity {
    public static final String FESTA_TABLE_ID = "FestaTableId";

    private Festa festa;
    private Locale locale;
    private TextView textoDetalhes;
    private TextView textoNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        JodaTimeAndroid.init(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }

        Log.d(StartActivity.class.getName(),locale.getCountry());

        textoDetalhes = (TextView) findViewById(R.id.txtDetalhes);
        textoNome = (TextView) findViewById(R.id.txtNomeFesta);

        Intent intentOriginador = getIntent();
        //O formatador abaixo é necessário pois os dados do banco de dados são formatados de forma diferente
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        dtf.withLocale(locale);
        String festaApelido = intentOriginador.getStringExtra(MainActivity.FESTA_APELIDO_EXTRA);
        String festaNome = intentOriginador.getStringExtra(MainActivity.FESTA_NOME_EXTRA);
        String festaTimezone = intentOriginador.getStringExtra(MainActivity.FESTA_TIMEZONE_EXTRA);
        try {
            DateTime dataAtual = new DateTime();
            DateTimeZone timezoneObject = DateTimeZone.forID(festaTimezone);
            LocalDateTime festaInicio = new LocalDateTime(dataAtual.getMillis(),timezoneObject);
            LocalDateTime festaFim = dtf.parseLocalDateTime(intentOriginador.getStringExtra(MainActivity.FESTA_DATA_FIM_EXTRA));
            festa = new Festa(festaApelido,festaNome,festaInicio.toString(),festaFim.toString(),festaTimezone);
            Log.d(StartActivity.class.getName(),festa.toString());
            String textoApresentacao = criarTextoApresentacao();
            textoDetalhes.setText(Html.fromHtml(textoApresentacao));
            textoNome.setText(festaNome);
        } catch (Exception e){
            Log.d(StartActivity.class.getName(),e.getMessage());
            toastMessage(getResources().getString(R.string.algo_deu_errado));
        }
    }

    private String criarTextoApresentacao() {
        Log.d(StartActivity.class.getName(),locale.toString());
        String texto;
        DateTimeZone timezone = DateTimeZone.forID(festa.getTimezone());
        DateTimeFormatter diaFormatter = DateTimeFormat.mediumDate();
        DateTimeFormatter horaFormatter = DateTimeFormat.shortTime();
        DateTime dataInicio = new DateTime(festa.getDataInicioJoda(locale).getYear(),
                festa.getDataInicioJoda(locale).getMonthOfYear(),
                festa.getDataInicioJoda(locale).getDayOfMonth(),
                festa.getDataInicioJoda(locale).getHourOfDay(),
                festa.getDataInicioJoda(locale).getMinuteOfHour(),
                timezone);
        //Transformando para valores com a timezone do usuário
        LocalDate diaInicio = new LocalDate(dataInicio.withZone(DateTimeZone.getDefault()).toInstant());
        LocalTime horaInicio = new LocalTime(dataInicio.withZone(DateTimeZone.getDefault()).toInstant());
        DateTime dataFim = new DateTime(festa.getDataFimJoda(locale).getYear(),
                festa.getDataFimJoda(locale).getMonthOfYear(),
                festa.getDataFimJoda(locale).getDayOfMonth(),
                festa.getDataFimJoda(locale).getHourOfDay(),
                festa.getDataFimJoda(locale).getMinuteOfHour(),
                timezone);
        //Transformando para valores com a timezone do usuário
        LocalDate diaFim = new LocalDate(dataFim.withZone(DateTimeZone.getDefault()).toInstant());
        LocalTime horaFim = new LocalTime(dataFim.withZone(DateTimeZone.getDefault()).toInstant());
        texto = getResources().getString(R.string.texto_apresentacao_1) +
                "<b> " + diaFormatter.print(diaInicio) + "</b> " +
                getResources().getString(R.string.texto_apresentacao_2) +
                "<b> " + horaFormatter.print(horaInicio) + "</b> " +
                getResources().getString(R.string.texto_apresentacao_3) +
                "<b> " + diaFormatter.print(diaFim) + "</b> " +
                getResources().getString(R.string.texto_apresentacao_2) +
                "<b> " + horaFormatter.print(horaFim) + "</b><br><br>" +
                getResources().getString(R.string.texto_apresentacao_4) + "<br><br>" +
                getResources().getString(R.string.texto_apresentacao_5) + "<br><br>" +
                getResources().getString(R.string.texto_apresentacao_6);
        return texto;
    }

    private void toastMessage(String message){
        Toast toast;
        toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
        toast.show();
    }

    public void comecarClick(View botao){
        //Salvar festa no banco de dados
        festa.setAtiva(true);
        try {
            long idFesta;
            idFesta = festa.save();
            SharedPreferences minhasPreferencias = getSharedPreferences(Preferencias.MINHAS_PREFERENCIAS,0);
            SharedPreferences.Editor editor = minhasPreferencias.edit();
            editor.putBoolean(Preferencias.PREF_FESTA_ATIVA,true);
            editor.putBoolean(Preferencias.PREF_FESTA_PAUSADA,false);
            editor.apply();
            Intent intent = new Intent(StartActivity.this,FestaActivity.class);
            intent.putExtra(MainActivity.FESTA_NOME_EXTRA,festa.getNome());
            intent.putExtra(MainActivity.FESTA_DATA_INICIO_EXTRA,festa.getDataInicio().toString());
            intent.putExtra(MainActivity.FESTA_DATA_FIM_EXTRA,festa.getDataFim().toString());
            intent.putExtra(MainActivity.FESTA_APELIDO_EXTRA,festa.getApelido());
            intent.putExtra(MainActivity.FESTA_TIMEZONE_EXTRA,festa.getTimezone());
            intent.putExtra(StartActivity.FESTA_TABLE_ID,idFesta);
            startActivity(intent);
            this.finish();
        } catch (Exception e){
            toastMessage(getResources().getString(R.string.ops_erro_tente_novamente_mais_tarde));
            Log.d(StartActivity.class.getName(),e.getMessage());
        }

    }
}
