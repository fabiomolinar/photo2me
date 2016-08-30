package com.photo2me.photo2me.api;

import android.content.pm.ActivityInfo;
import android.util.Log;
import android.widget.Toast;

import com.photo2me.photo2me.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Fabio on 29/08/2016.
 */
public class Post {
    OkHttpClient client = new OkHttpClient();
    public static final String URL_BASE = "http://photo.optykopoczno.pl/";

    public String pegarFesta(String apelido) throws Exception{
        String url = URL_BASE + "dadosFesta";
        Log.i(Post.class.getName(),"Criando post");
        RequestBody formBody = new FormBody.Builder()
                .add("apelido",apelido)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(Post.class.getName(),"onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(Post.class.getName(),"onResponse: " + response.body().string());
                Log.i(Post.class.getName(),"onResponse: " + response.body().toString());
            }
        });
        return "retorno do post";
    }
}
