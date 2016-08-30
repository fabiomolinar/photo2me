package com.photo2me.photo2me;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText identificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buscarFestaClick(View botao){
        //Verificar que existe internet
        ConnectivityManager check = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = check.getAllNetworkInfo();
        Boolean temNet = false;
        for (int i = 0; i<info.length; i++){
            if (info[i].getState() == NetworkInfo.State.CONNECTED){
                temNet = true;
            }
        }
        Log.i(MainActivity.class.getName(),"Tem internet: " + temNet);
        Toast toast;
        CharSequence text;
        if (!temNet){
            text = getResources().getString(R.string.precisa_estar_conectado);
            toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
            toast.show();
        } else {
            String apelido = identificador.getText().toString();
            Log.i(MainActivity.class.getName(),"apelido: " + apelido);
            //Pegar dados da festa através da API
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("apelido",apelido)
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
                        Log.i(MainActivity.class.getName(),"on Failure: " + e.getMessage());
                        //Jogando a mensagem para o Looper
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                msgErroConexao(getResources().getString(R.string.problema_com_conexao));
                            }
                        });
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseString = response.body().string();
                        Log.i(MainActivity.class.getName(),"onResponse: " + responseString);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject json = new JSONObject(responseString);
                                    Log.i(MainActivity.class.getName(),json.getString("mensagem"));
                                } catch (Exception e) {
                                    Log.i(MainActivity.class.getName(),"erro com JSON: " + e.getMessage());
                                    e.printStackTrace();
                                    msgErroConexao(getResources().getString(R.string.problema_com_conexao));
                                }
                            }
                        });
                    }
                });
            } catch (Exception e){
                Log.i(MainActivity.class.getName(),"post não funcionou");
                msgErroConexao(getResources().getString(R.string.problema_com_conexao));
            }

        }
    }

    public void msgErroConexao(String mensagem){
        //Mostrando mensagem de erro para o usuário
        Toast toast;
        toast = Toast.makeText(this,mensagem,Toast.LENGTH_LONG);
        toast.show();
    }

}
