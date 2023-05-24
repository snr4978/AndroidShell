package com.kean;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.kean.util.ColorUtil;
import com.kean.util.JavascriptUtil;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    WebView webView;

    @Override
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.kean.ACTION_CALL_JAVASCRIPT");
        registerReceiver(new JavascriptReceiver(), intentFilter);

        linearLayout = findViewById(R.id.loading);
        webView = findViewById(R.id.content);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(new JavascriptUtil(this), "AndroidShell");
        webView.loadUrl("http://47.92.246.182:8888/");
    }

    @Override
    public void onBackPressed() {
        webView.evaluateJavascript("location.pathname=='/'||location.pathname=='/auth/login'?'':history.go(-1)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                if (!s.equals("null")) {
                    MainActivity.super.onBackPressed();
                }
            }
        });
    }

    private class JavascriptReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String data = intent.getStringExtra("data");
                new ColorUtil(MainActivity.this).setColor(Color.parseColor(data));
                linearLayout.setVisibility(View.GONE);
            }
        }
    }
}