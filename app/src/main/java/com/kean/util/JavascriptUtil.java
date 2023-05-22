package com.kean.util;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class JavascriptUtil extends Object {
    private Context _context;

    public JavascriptUtil(Context context) {
        _context = context;
    }

    @JavascriptInterface
    public void setStatusBarColor(String s) {
        Intent intent = new Intent();
        intent.setAction("com.kean.ACTION_CALL_JAVASCRIPT");
        intent.putExtra("data", s);
        _context.sendBroadcast(intent);
    }
}
