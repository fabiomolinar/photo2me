package com.photo2me.photo2me;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class FotoSenderService extends IntentService {

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
    protected void onHandleIntent(Intent intent) {
        Log.d(FotoSenderService.class.getName(),"onHandleIntent");
        //Fazer trabalho aqui

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(FotoSenderService.class.getName(),"Serviço iniciado");
        //NÃO MODIFICAR O RETORNO ABAIXO. Seguindo a documentação do android, esse retorno não pode ser modificado.
        return super.onStartCommand(intent,flags,startId);
    }
}