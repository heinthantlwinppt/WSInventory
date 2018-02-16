package com.ppt.wsinventory.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by User on 03/01/2018.
 */

public class Utility {

    public static final int LOGIN_REQUEST = 1;
    public static final int APPEXIT_REQUEST = 0;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
    public static final SimpleDateFormat dateFormatDoc = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.US);
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
    public static Date getDateBegin(){return new GregorianCalendar(2001, 0, 1, 0, 0, 0).getTime();}
    public static  boolean isNumeric(String value){
        return  value.matches("\\d+(?:\\.\\d+)?");
    }

    public static File creatdesignfolder(String foldername){
        File sd = Environment.getExternalStorageDirectory();
//        File sd = new File("/mnt/sdcard/wsimages");
        File folder = new File(sd.getAbsolutePath()  , "WsImages");
        if(!folder.exists()) {
            folder.mkdir();
        }

        File subfolder = new File(folder,foldername);

        if(!subfolder.exists()){

            subfolder.mkdir();
        }
        return subfolder;

    }

//    public static void creatdesignfolder(){
//        File sd = Environment.getExternalStorageDirectory();
////        File sd = new File("/mnt/sdcard");
//        File folder = new File(sd.getAbsolutePath() + "/design");
//        folder.mkdir();
//    }

}
