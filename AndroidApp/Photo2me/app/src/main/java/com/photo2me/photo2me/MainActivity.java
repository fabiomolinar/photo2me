package com.photo2me.photo2me;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText identificador;
    private HttpURLConnection conexao;
    private URL url;

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
            String site = getResources().getString(R.string.site);
            String apiLink = getResources().getString(R.string.api_dados_festa);
            Log.i(MainActivity.class.getName(),"link da api: " + site + apiLink);
            String apelido = identificador.getText().toString();
            Log.i(MainActivity.class.getName(),"apelido: " + apelido);
            //Pegar dados da festa através da API
            try {
                url = new URL("http",site,80,apiLink);
                conexao = (HttpURLConnection) url.openConnection();
                //Para fazer upload de um body
                conexao.setDoOutput(true);
                conexao.setDoInput(true);
                conexao.setRequestProperty("Content-Type", "form-data");
                conexao.setRequestProperty("Accept", "application/json");
                conexao.setRequestMethod("POST");
                //Criando body do post
                String data = "apelido=" + apelido;
                byte[] dataEmBytes = data.getBytes("UTF-8");
                OutputStream os = conexao.getOutputStream();
                os.write(dataEmBytes);
                os.close();
            } catch (Exception e) {
                text = getResources().getString(R.string.problema_com_conexao);
                toast = Toast.makeText(this,text,Toast.LENGTH_LONG);
                toast.show();
                return;
            }


        }

    }
}
