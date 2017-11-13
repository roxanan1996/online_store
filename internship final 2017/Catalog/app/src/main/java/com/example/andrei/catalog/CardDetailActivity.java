package com.example.andrei.catalog;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CardDetailActivity extends AppCompatActivity {

    private final String WEBAPPURL = "http://10.0.2.2:8080/catalog-webapp";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        Intent intent = getIntent();
        String id = intent.getStringExtra("cardId");

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) {
                    view.loadUrl(url);
                    return true;
                } else {
                    return false;
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(WEBAPPURL + "/cardView/card/" + id);
    }
}
