package com.photo2me.photo2me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class TermsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(Html.fromHtml(getString(R.string.termos)));
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void aceitarClick(View botao){
        SharedPreferences minhasPreferencias = getSharedPreferences(Preferencias.MINHAS_PREFERENCIAS,0);
        SharedPreferences.Editor editor = minhasPreferencias.edit();
        editor.putBoolean(Preferencias.PREF_BOOL_ACEITOU_TERMOS,true);
        editor.apply();
        final Intent intent = new Intent(TermsActivity.this,MainActivity.class);
        TermsActivity.this.startActivity(intent);
        TermsActivity.this.finish();
    }
}
