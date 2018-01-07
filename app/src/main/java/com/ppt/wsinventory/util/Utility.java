package com.ppt.wsinventory.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by User on 03/01/2018.
 */

public class Utility {

    public static final int LOGIN_REQUEST = 1;
    public static final int APPEXIT_REQUEST = 0;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    public static final SimpleDateFormat dateFormatDoc = new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.US);
    public static final SimpleDateFormat dateFormatddMMyyyy = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean isOnline(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    public static  boolean isNumeric(String value){
        return  value.matches("\\d+(?:\\.\\d+)?");
    }
}
