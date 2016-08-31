package com.photo2me.photo2me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class StartActivity extends AppCompatActivity {
    private Festa festa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intentOriginador = getIntent();
        DateFormat df = DateFormat.getDateTimeInstance();
        String festaApelido = intentOriginador.getStringExtra(MainActivity.FESTA_APELIDO_EXTRA);
        String festaNome = intentOriginador.getStringExtra(MainActivity.FESTA_NOME_EXTRA);
        String festaTimezone = intentOriginador.getStringExtra(MainActivity.FESTA_TIMEZONE_EXTRA);
        try {
            Date festaInicio = df.parse(intentOriginador.getStringExtra(MainActivity.FESTA_DATA_INICIO_EXTRA));
            Date festaFim = df.parse(intentOriginador.getStringExtra(MainActivity.FESTA_DATA_FIM_EXTRA));
            festa = new Festa(festaApelido,festaNome,festaInicio,festaFim,festaTimezone);
        } catch (ParseException e){
            Log.i(StartActivity.class.getName(),e.getMessage());
            toastMessage(getResources().getString(R.string.algo_deu_errado));
        }
    }

    private void toastMessage(String message){
        Toast toast;
        toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
        toast.show();
    }
}
