package com.leador.picassodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {
    WebView webView;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        uri = getIntent().getStringExtra("uri");
        initView();

    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); //设置能够执行JavaScript脚本
        webView.loadUrl(uri);
    }
}
