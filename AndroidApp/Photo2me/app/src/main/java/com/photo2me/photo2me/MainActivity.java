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

import com.photo2me.photo2me.api.Post;

import java.io.IOException;
import java.util.Date;

import okhttp3.OkHttpClient;

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
            try {
                //Fazendo POST request de forma asincrona
                Post post = new Post();
                String resposta = post.pegarFesta(apelido);
            } catch (Exception e) {
                Log.i(MainActivity.class.getName(),"exceção: " + e.getMessage());
                Log.i(MainActivity.class.getName(),"exceção: " + e.toString());
                msgErroConexao();
            }
        }
    }

    public void msgErroConexao(){
        //Mostrando mensagem de erro para o usuário
        String text;
        Toast toast;
        text = getResources().getString(R.string.problema_com_conexao);
        toast = Toast.makeText(this,text,Toast.LENGTH_LONG);
        toast.show();
    }

}
