package com.kean.util;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.Window;

public class ColorUtil {
    private Activity _activity;

    public ColorUtil(Activity activity) {
        _activity = activity;
    }

    public void setColor(int color) {
        Window window = _activity.getWindow();
        window.setStatusBarColor(color);
        window.getDecorView().setSystemUiVisibility(isLight(color) ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : View.SYSTEM_UI_FLAG_VISIBLE);
    }

    public static boolean isLight(int color) {
        return 0.213 * Color.red(color) + 0.715 * Color.green(color) + 0.072 * Color.blue(color) > 255 / 2;
    }
}
