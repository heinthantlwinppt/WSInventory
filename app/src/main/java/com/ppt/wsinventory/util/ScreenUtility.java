package com.ppt.wsinventory.util;

/**
 * Created by arkar on 1/1/2018.
 */


import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

public class ScreenUtility {

    float dpWidth;
    float dpHeight;
    float density;
    public ScreenUtility(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        density = activity.getResources().getDisplayMetrics().density;

        dpWidth = outMetrics.widthPixels / density;
        dpHeight = outMetrics.heightPixels / density;
    }

    public float getDpWidth() {
        return dpWidth;
    }

    public float getDpHeight() {
        return dpHeight;
    }

    public float getDensity() {
        return density;
    }
}