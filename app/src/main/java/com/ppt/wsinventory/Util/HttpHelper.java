package com.ppt.wsinventory.util;

import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by arkar on 9/12/2017.
 */

public class HttpHelper {

    public static String downloadFromFeed(RequestPackage requestPackage)
            throws IOException {

        String address = requestPackage.getEndpoint();
        String encodedParams = requestPackage.getEncodedParams();

        if (requestPackage.getMethod().equals("GET") &&
                encodedParams.length() > 0) {
            address = String.format("%s?%s", address, encodedParams);
        }

        OkHttpClient client = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder()
                .url(address);

        if (requestPackage.getMethod().equals("POST")) {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            Map<String, String> params = requestPackage.getParams();
            for (String key : params.keySet()) {
                builder.addFormDataPart(key, params.get(key));
            }
            RequestBody requestBody = builder
                    .build();
            requestBuilder.method("POST",requestBody);
        }

        Request request = requestBuilder.build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Exception: response code " + response.code());
        }
    }
    public static String requestXPay(RequestPackage requestPackage)
            throws IOException {

        String address = requestPackage.getEndpoint();
        String encodedParams = requestPackage.getEncodedParams();

        if (requestPackage.getMethod().equals("GET") &&
                encodedParams.length() > 0) {
            address = String.format("%s?%s", address, encodedParams);
        }

        OkHttpClient client = new OkHttpClient();

        Request.Builder requestBuilder = new Request.Builder()
                .url(address);

        if (requestPackage.getMethod().equals("POST")) {
            MediaType JSON
                    = MediaType.parse("application/json; charset=utf-8");
            String token = "";
            String body ="";
            Map<String, String> params = requestPackage.getParams();

            for (String key : params.keySet()) {
                if(key.equalsIgnoreCase("Authorization")){
                    token = params.get(key);
                }
                else if(key.equalsIgnoreCase("body")){
                    body = params.get(key);
                }
            }
            requestBuilder.addHeader("Content-Type","application/json");
            if(!TextUtils.isEmpty(token))
            requestBuilder.addHeader("Authorization",token);
            RequestBody requestBody = RequestBody.create(JSON,body);
            requestBuilder.method("POST",requestBody);
        }

        Request request = requestBuilder
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Exception: response code " + response.code());
        }
    }

}
