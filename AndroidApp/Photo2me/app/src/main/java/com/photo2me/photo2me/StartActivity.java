package com.photo2me.photo2me;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    private Festa festa;
    private Locale locale;
    private TextView textoDetalhes;
    private TextView textoNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }

        Log.i(StartActivity.class.getName(),locale.getCountry());

        textoDetalhes = (TextView) findViewById(R.id.txtDetalhes);
        textoNome = (TextView) findViewById(R.id.txtNomeFesta);

        Intent intentOriginador = getIntent();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        dtf.withLocale(locale);
        String festaApelido = intentOriginador.getStringExtra(MainActivity.FESTA_APELIDO_EXTRA);
        String festaNome = intentOriginador.getStringExtra(MainActivity.FESTA_NOME_EXTRA);
        String festaTimezone = intentOriginador.getStringExtra(MainActivity.FESTA_TIMEZONE_EXTRA);
        try {
            LocalDateTime festaInicio = dtf.parseLocalDateTime(intentOriginador.getStringExtra(MainActivity.FESTA_DATA_INICIO_EXTRA));
            LocalDateTime festaFim = dtf.parseLocalDateTime(intentOriginador.getStringExtra(MainActivity.FESTA_DATA_FIM_EXTRA));
            festa = new Festa(festaApelido,festaNome,festaInicio,festaFim,festaTimezone);
            Log.i(StartActivity.class.getName(),festa.toString());
            String textoApresentacao = criarTextoApresentacao();
            textoDetalhes.setText(Html.fromHtml(textoApresentacao));
            textoNome.setText(festaNome);
        } catch (Exception e){
            Log.i(StartActivity.class.getName(),e.getMessage());
            toastMessage(getResources().getString(R.string.algo_deu_errado));
        }
    }

    private String criarTextoApresentacao() {
        Log.i(StartActivity.class.getName(),locale.toString());
        String texto;
        DateTimeZone timezone = DateTimeZone.forID(festa.getTimezone());
        DateTimeFormatter diaFormatter = DateTimeFormat.mediumDate();
        DateTimeFormatter horaFormatter = DateTimeFormat.shortTime();
        DateTime dataInicio = new DateTime(festa.getDataInicio().getYear(),
                festa.getDataInicio().getMonthOfYear(),
                festa.getDataInicio().getDayOfMonth(),
                festa.getDataInicio().getHourOfDay(),
                festa.getDataInicio().getMinuteOfHour(),
                timezone);
        //Transformando para valores com a timezone do usuário
        LocalDate diaInicio = new LocalDate(dataInicio.withZone(DateTimeZone.getDefault()).toInstant());
        LocalTime horaInicio = new LocalTime(dataInicio.withZone(DateTimeZone.getDefault()).toInstant());
        DateTime dataFim = new DateTime(festa.getDataFim().getYear(),
                festa.getDataFim().getMonthOfYear(),
                festa.getDataFim().getDayOfMonth(),
                festa.getDataFim().getHourOfDay(),
                festa.getDataFim().getMinuteOfHour(),
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
        festa.save();
        //Preparar e iniciar serviços
        //Preparar e iniciar serviços
        Intent intent = new Intent(StartActivity.this,FestaActivity.class);
        intent.putExtra(MainActivity.FESTA_NOME_EXTRA,festa.getNome());
        intent.putExtra(MainActivity.FESTA_DATA_INICIO_EXTRA,festa.getDataInicio().toString());
        intent.putExtra(MainActivity.FESTA_DATA_FIM_EXTRA,festa.getDataFim().toString());
        intent.putExtra(MainActivity.FESTA_APELIDO_EXTRA,festa.getApelido());
        intent.putExtra(MainActivity.FESTA_TIMEZONE_EXTRA,festa.getTimezone());
        startActivity(intent);
        this.finish();
    }
}
