package com.photo2me.photo2me;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import java.io.File;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FotoSenderService extends IntentService {
    private static final String TAG = "Photo2Me/" + FotoSenderService.class.getSimpleName();
    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public FotoSenderService() {
        super("FotoSenderService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(final Intent intent) {
        Log.d(FotoSenderService.class.getName(),"onHandleIntent");
        //Verificar se tem wifi
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo infoWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (infoWifi.isConnected()){
            String path = intent.getStringExtra(ManagerService.FOTO_PATH);
            String idFestaUsuario = intent.getStringExtra(ManagerService.FOTO_ID_USUARIO_FESTA);
            String apelido = intent.getStringExtra(ManagerService.FOTO_FESTA_ID);
            File foto = new File(path);
            //Preparando post HTTP
            OkHttpClient client = new OkHttpClient();
            RequestBody formBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("apelido",apelido)
                    .addFormDataPart("idUsuarioFesta",idFestaUsuario)
                    .addFormDataPart("imagem",foto.getName(),RequestBody.create(MediaType.parse("image"),foto))
                    .build();
            String url = getResources().getString(R.string.URL_BASE) + getResources().getString(R.string.API_ENVIAR_FOTO);
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
        } else {
            Log.d(TAG,"Não tem WiFi");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(FotoSenderService.class.getName(),"Serviço iniciado");
        //NÃO MODIFICAR O RETORNO ABAIXO. Seguindo a documentação do android, esse retorno não pode ser modificado.
        return super.onStartCommand(intent,flags,startId);
    }
}