package com.ppt.wsinventory.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by arkar on 2/1/2018.
 */

public class JsonHelper {
    public static Gson getGson(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        return  gson;
    }
    public static String getJsonString(JsonObject objJson, String fieldName){
        return  !objJson.get(fieldName).isJsonNull() ? objJson.get(fieldName).getAsString() : null;
    }
    public static int getJsonInteger(JsonObject objJson, String fieldName){
        return  !objJson.get(fieldName).isJsonNull() ? objJson.get(fieldName).getAsInt() : null;
    }
    public static Boolean getJsonBoolean(JsonObject objJson, String fieldName){
        return  !objJson.get(fieldName).isJsonNull() ? objJson.get(fieldName).getAsBoolean() : false;
    }
    public static double getJsonDouble(JsonObject objJson, String fieldName){
        return  !objJson.get(fieldName).isJsonNull() ? objJson.get(fieldName).getAsDouble() : null;
    }
    public static Date getJsonDate(JsonObject objJson, String fieldName, SimpleDateFormat dateFormat){
        try {
            return  !objJson.get(fieldName).isJsonNull() ? dateFormat.parse(objJson.get(fieldName).getAsString()) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
