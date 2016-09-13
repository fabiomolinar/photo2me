package com.photo2me.photo2me;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Fabio on 12/09/2016.
 */
public class SenderService extends IntentService {
    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public SenderService() {
        super("SenderService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
