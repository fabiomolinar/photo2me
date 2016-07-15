package com.photo2me.photo2me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
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
}
