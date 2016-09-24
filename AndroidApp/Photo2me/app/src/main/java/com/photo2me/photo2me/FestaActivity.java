package com.photo2me.photo2me;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import org.joda.time.format.ISODateTimeFormat;

import java.util.Locale;

public class FestaActivity extends AppCompatActivity {
    private static final String TAG = "photo2me/" + FestaActivity.class.getName();
    TextView nomeFesta;
    TextView dataInicio;
    TextView dataFim;
    TextView horaInicio;
    TextView horaFim;
    TextView status;
    Button pausar;
    Button parar;
    Locale locale;
    DateTime inicio;
    DateTime fim;
    Context contexto;
    long idFesta;
    Festa festaOriginal;
    DateTimeZone timezoneObject;
    NotificationCompat.Builder mNota;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festa);
        JodaTimeAndroid.init(this);
        contexto = getApplicationContext();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }

        nomeFesta = (TextView) findViewById(R.id.txtNomeFesta);
        dataInicio = (TextView) findViewById(R.id.txtDataInicio);
        dataFim = (TextView) findViewById(R.id.txtDataFim);
        horaInicio = (TextView) findViewById(R.id.txtHoraInicio);
        horaFim = (TextView) findViewById(R.id.txtHoraFim);
        status = (TextView) findViewById(R.id.txtStatus);
        pausar = (Button) findViewById(R.id.btnPausar);
        parar = (Button) findViewById(R.id.btnParar);

        //Lógica abaixo irá decidir se devo mostrar esse botão ou não.
        pausar.setVisibility(View.INVISIBLE);

        Intent intentOriginador = getIntent();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dtf.withLocale(locale);
        try{
            //Pegando ID da festa
            idFesta = intentOriginador.getLongExtra(StartActivity.FESTA_TABLE_ID,0);
            Log.d(FestaActivity.class.getName(),"Id da festa: " + idFesta);
            //Criando variáveis de tempo com o fuso da festa
            LocalDateTime ldtInicio = dtf.parseLocalDateTime(intentOriginador.getStringExtra(MainActivity.FESTA_DATA_INICIO_EXTRA));
            LocalDateTime ldtFim = dtf.parseLocalDateTime(intentOriginador.getStringExtra(MainActivity.FESTA_DATA_FIM_EXTRA));
            timezoneObject = DateTimeZone.forID(intentOriginador.getStringExtra(MainActivity.FESTA_TIMEZONE_EXTRA));
            inicio = new DateTime(ldtInicio.getYear(),ldtInicio.getMonthOfYear(),ldtInicio.getDayOfMonth(),
                    ldtInicio.getHourOfDay(),ldtInicio.getMinuteOfHour(),timezoneObject);
            fim = new DateTime(ldtFim.getYear(),ldtFim.getMonthOfYear(),ldtFim.getDayOfMonth(),
                    ldtFim.getHourOfDay(),ldtFim.getMinuteOfHour(),timezoneObject);
            //Criando modelo da festa
            festaOriginal = new Festa(
                    intentOriginador.getStringExtra(MainActivity.FESTA_APELIDO_EXTRA),
                    intentOriginador.getStringExtra(MainActivity.FESTA_NOME_EXTRA),
                    ldtInicio.toString(),
                    ldtFim.toString(),
                    intentOriginador.getStringExtra(MainActivity.FESTA_TIMEZONE_EXTRA)
            );
            festaOriginal.setIdFestaUsuario(intentOriginador.getStringExtra(MainActivity.FESTA_ID_USUARIO_FESTA));
            //Criando datas e horas locais
            LocalDate inicioData = new LocalDate(inicio.withZone(DateTimeZone.getDefault()).toInstant());
            LocalDate fimData = new LocalDate(fim.withZone(DateTimeZone.getDefault()).toInstant());
            LocalTime inicioHora = new LocalTime(inicio.withZone(DateTimeZone.getDefault()).toInstant());
            LocalTime fimHora = new LocalTime(fim.withZone(DateTimeZone.getDefault()).toInstant());
            //Atualizando a tela com dados
            DateTimeFormatter diaFormatter = DateTimeFormat.mediumDate();
            DateTimeFormatter horaFormatter = DateTimeFormat.shortTime();
            nomeFesta.setText(intentOriginador.getStringExtra(MainActivity.FESTA_NOME_EXTRA));
            dataInicio.setText(diaFormatter.print(inicioData));
            dataFim.setText(diaFormatter.print(fimData));
            horaInicio.setText(horaFormatter.print(inicioHora));
            horaFim.setText(horaFormatter.print(fimHora));

            //Criando notification manager
            mNota = new NotificationCompat.Builder(this);
            mNota.setSmallIcon(R.drawable.logo);
            mNota.setContentText(intentOriginador.getStringExtra(MainActivity.FESTA_NOME_EXTRA));
            mNota.setContentText(getResources().getString(R.string.coletando));
            mNota.setOngoing(true);
            Intent notaIntent = new Intent(this, FestaActivity.class);
            //Flags abaixo garantem que irei chamar a atividade que já está aberta
            notaIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    notaIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mNota.setContentIntent(pendingIntent);
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(getResources().getInteger(R.integer.notification_festa_status),mNota.build());
        } catch (Exception e){
            Log.d(FestaActivity.class.getName(),"Erro fazendo o parse das datas");
            e.printStackTrace();
            toastMessage(getResources().getString(R.string.ops_erro_tente_novamente_mais_tarde));
        }
        //Criando thread que irá ficar dando update na tela do dispositivo
        Thread t = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    while (true){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                atualizarTela();
                            }
                        });
                    }
                } catch (Exception e){
                    Log.d(FestaActivity.class.getName(),e.getMessage());
                    e.printStackTrace();
                    toastMessage(getResources().getString(R.string.erro_ao_atualizar_status_festa));
                }
            }
        };
        t.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Atividade sendo destruida");
        SharedPreferences minhasPreferencias = getSharedPreferences(Preferencias.MINHAS_PREFERENCIAS,0);
        SharedPreferences.Editor editor = minhasPreferencias.edit();
        editor.putBoolean(Preferencias.PREF_FESTA_PAUSADA,true);
        editor.putBoolean(Preferencias.PREF_FESTA_ATIVA,false);
        editor.apply();
        //Atualizando banco de dados
        DateTime dataAtual = new DateTime();
        Festa festa = Festa.findById(Festa.class,idFesta);
        LocalDateTime novaDataFim = new LocalDateTime(dataAtual.getMillis(),timezoneObject);
        festa.setDataFim(novaDataFim.toString());
        festa.setAtiva(false);
        festa.save();
    }

    private void toastMessage(String message){
        Toast toast;
        toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
        toast.show();
    }
    private void atualizarTela(){
        SharedPreferences minhasPreferencias = getSharedPreferences(Preferencias.MINHAS_PREFERENCIAS,0);
        Boolean ativa = minhasPreferencias.getBoolean(Preferencias.PREF_FESTA_ATIVA,false);
        Boolean pausada = minhasPreferencias.getBoolean(Preferencias.PREF_FESTA_PAUSADA,false);
        if (!ativa){
            status.setText(getResources().getString(R.string.desativado));
            status.setTextColor(ContextCompat.getColor(contexto,R.color.vermelho));
            pausar.setVisibility(View.INVISIBLE);
            pausar.setEnabled(false);
        } else {
            DateTime horaAtual = new DateTime();
            if (inicio.getMillis() - horaAtual.getMillis() > 0){
                status.setText(getResources().getString(R.string.esperando_festa_comecar));
                status.setTextColor(ContextCompat.getColor(contexto,R.color.laranjaOficial));
                pausar.setVisibility(View.INVISIBLE);
                pausar.setEnabled(false);
            } else if (horaAtual.getMillis() - fim.getMillis() > 0){
                status.setText(getResources().getString(R.string.festa_ja_acabou));
                status.setTextColor(ContextCompat.getColor(contexto,R.color.laranjaOficial));
                pausar.setVisibility(View.INVISIBLE);
                pausar.setEnabled(false);
            } else {
                if (pausada){
                    status.setText(getResources().getString(R.string.festa_pausada));
                    status.setTextColor(ContextCompat.getColor(contexto,R.color.azul));
                    pausar.setVisibility(View.VISIBLE);
                    pausar.setEnabled(true);
                    pausar.setText(getResources().getString(R.string.reiniciar));
                } else {
                    status.setText(getResources().getString(R.string.festa_ativa));
                    status.setTextColor(ContextCompat.getColor(contexto,R.color.verde));
                    pausar.setVisibility(View.VISIBLE);
                    pausar.setEnabled(true);
                    pausar.setText(getResources().getString(R.string.pausar));
                }
            }
        }
    }
    public void pausarClick(View botao){
        SharedPreferences minhasPreferencias = getSharedPreferences(Preferencias.MINHAS_PREFERENCIAS,0);
        boolean pausada = minhasPreferencias.getBoolean(Preferencias.PREF_FESTA_PAUSADA,false);
        SharedPreferences.Editor editor = minhasPreferencias.edit();
        DateTime dataAtual = new DateTime();
        Festa festa;
        if (pausada){
            //Reiniciar atividade
            //Setando preferências
            editor.putBoolean(Preferencias.PREF_FESTA_PAUSADA,false);
            editor.apply();
            pausar.setText(getResources().getString(R.string.pausar));
            //Adicionar nova linha ao BD
            festa = new Festa(festaOriginal);
            LocalDateTime novaDataInicio = new LocalDateTime(dataAtual.getMillis(),timezoneObject);
            festa.setDataInicio(novaDataInicio.toString());
            festa.setAtiva(true);
            idFesta = festa.save();
        } else {
            //Pusar a atividade
            //Setando preferências
            editor.putBoolean(Preferencias.PREF_FESTA_PAUSADA,true);
            editor.apply();
            pausar.setText(getResources().getString(R.string.reiniciar));
            //Salvar nova data de fim ao banco de dados
            festa = Festa.findById(Festa.class,idFesta);
            LocalDateTime novaDataFim = new LocalDateTime(dataAtual.getMillis(),timezoneObject);
            festa.setDataFim(novaDataFim.toString());
            festa.setAtiva(false);
            festa.save();
        }

    }
    public void pararClick(View botao){
        SharedPreferences minhasPreferencias = getSharedPreferences(Preferencias.MINHAS_PREFERENCIAS,0);
        SharedPreferences.Editor editor = minhasPreferencias.edit();
        editor.putBoolean(Preferencias.PREF_FESTA_PAUSADA,true);
        editor.putBoolean(Preferencias.PREF_FESTA_ATIVA,false);
        editor.apply();
        //Atualizando banco de dados
        DateTime dataAtual = new DateTime();
        Festa festa = Festa.findById(Festa.class,idFesta);
        LocalDateTime novaDataFim = new LocalDateTime(dataAtual.getMillis(),timezoneObject);
        festa.setDataFim(novaDataFim.toString());
        festa.setAtiva(false);
        festa.save();
        //Intent para atividade principal
        Intent intent = new Intent(FestaActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getResources().getString(R.string.sair_da_festa))
                .setMessage(getResources().getString(R.string.certeza_sair_festa))
                .setPositiveButton(getResources().getString(R.string.sim), new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton(getResources().getString(R.string.nao),null)
                .show();
    }
}
