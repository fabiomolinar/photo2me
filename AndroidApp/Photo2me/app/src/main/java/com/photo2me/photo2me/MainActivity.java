package com.photo2me.photo2me;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import net.danlew.android.joda.JodaTimeAndroid;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "photo2me/" + MainActivity.class.getName();
    private EditText identificador;
    private ProgressBar progressBar;
    public static final String FESTA_NOME_EXTRA = "com.photo2me.photo2me.Nome da festa";
    public static final String FESTA_DATA_INICIO_EXTRA = "com.photo2me.photo2me.Data de inicio";
    public static final String FESTA_DATA_FIM_EXTRA = "com.photo2me.photo2me.Data de fim";
    public static final String FESTA_APELIDO_EXTRA = "com.photo2me.photo2me.apelido";
    public static final String FESTA_TIMEZONE_EXTRA = "com.photo2me.photo2me.timezone";
    public static final String FESTA_ID_USUARIO_FESTA = "com.photo2me.photo2me.id_festa_usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Iniciando Joda DateTime library
        JodaTimeAndroid.init(this);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        identificador = (EditText) findViewById(R.id.etxIdentificador);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sobre) {
            Intent intent = new Intent(MainActivity.this,SobreNosActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.configuracoes){
            Intent intent = new Intent(MainActivity.this,PreferenciasActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buscarFestaClick(View botao){
        //Mostrando progress spinner
        progressBar.setVisibility(View.VISIBLE);
        //Verificar que existe internet
        ConnectivityManager check = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = check.getAllNetworkInfo();
        Boolean temNet = false;
        for (int i = 0; i<info.length; i++){
            if (info[i].getState() == NetworkInfo.State.CONNECTED){
                temNet = true;
            }
        }
        Toast toast;
        CharSequence text;
        if (!temNet){
            progressBar.setVisibility(View.INVISIBLE);
            text = getResources().getString(R.string.precisa_estar_conectado);
            toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
            toast.show();
        } else {
            String apelido = identificador.getText().toString();
            //Pegar dados da festa através da API
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("apelido",apelido)
                    .add("lingua",getResources().getConfiguration().locale.getDisplayLanguage())
                    .build();
            String url = getResources().getString(R.string.URL_BASE) + getResources().getString(R.string.API_DADOS_FESTA);
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            try {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //Jogando a mensagem para o Looper
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.INVISIBLE);
                                toastMessage(getResources().getString(R.string.problema_com_conexao));
                            }
                        });
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseString = response.body().string();
                        final int codigo = response.code();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject json = new JSONObject(responseString);
                                    if (codigo != 200){
                                        progressBar.setVisibility(View.INVISIBLE);
                                        toastMessage(json.getString("mensagem"));
                                    } else {
                                        //Intent para a próxima tela
                                        Intent intent = new Intent(MainActivity.this,StartActivity.class);
                                        intent.putExtra(MainActivity.FESTA_NOME_EXTRA,json.getString("nomeFesta"));
                                        intent.putExtra(MainActivity.FESTA_DATA_INICIO_EXTRA,json.getString("dataInicio"));
                                        intent.putExtra(MainActivity.FESTA_DATA_FIM_EXTRA,json.getString("dataFim"));
                                        intent.putExtra(MainActivity.FESTA_APELIDO_EXTRA,json.getString("apelido"));
                                        intent.putExtra(MainActivity.FESTA_TIMEZONE_EXTRA,json.getString("timezone"));
                                        intent.putExtra(MainActivity.FESTA_ID_USUARIO_FESTA,json.getString("idUsuarioFesta"));
                                        startActivity(intent);
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                } catch (Exception e) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    e.printStackTrace();
                                    toastMessage(getResources().getString(R.string.problema_com_conexao));
                                }
                            }
                        });
                    }
                });
            } catch (Exception e){
                progressBar.setVisibility(View.INVISIBLE);
                toastMessage(getResources().getString(R.string.problema_com_conexao));
            }

        }
    }

    public void toastMessage(String mensagem){
        //Mostrando mensagem de erro para o usuário
        Toast toast;
        toast = Toast.makeText(this,mensagem,Toast.LENGTH_LONG);
        toast.show();
    }

}
