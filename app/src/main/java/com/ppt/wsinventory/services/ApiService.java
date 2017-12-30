package com.ppt.wsinventory.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.Util.HttpHelper;
import com.ppt.wsinventory.Util.RequestPackage;
import com.ppt.wsinventory.model.LoginToken;
import com.ppt.wsinventory.model.QueueMessage;
import com.ppt.wsinventory.model.QueueUserMessage;

import java.io.IOException;




/**
 * Created by arkar on 9/12/2017.
 */

public class ApiService extends IntentService {
    public static final String TAG = "XPAY-ApiService";
    public static final String API_SERVICE_MESSAGE = "api_service_message";
    public static final String SERVICE_TYPE = "service_type";
    public static final String SERVICE_ERROR = "service_error";
    public static final String REQUEST_PACKAGE = "requestPackage";
    public static final String SERVICE_LOGIN = "login";
    public static final String SERVICE_HOMESUMMARY = "service_homesummary";
    public static final String SERVICE_CARD_ACTIVATION = "service_card_activation";
    public static final String SERVICE_GETREGISTERPROFILE = "service_getregisterprofile";
    public static final String SERVICE_SAVEREGISTERPROFILE = "service_saveregisterprofile";
    public static final String SERVICE_CREATEQUEUE = "service_editprofile";
    public static final String SERVICE_EDITPROFILE = "service_createqueue";
    public static final String SERVICE_CREATEQUEUEUSER = "createQueueUser";
    public static final String SERVICE_GETNOTIFICATIONS = "service_getnotifications";
    public static final String SERVICE_DELETESYSTEMMESSAGE = "service_deletesystemmessage";
    public static final String SERVICE_GETTOPUPTRANSBYACCOUNTIDDATE = "service_gettopuptransbyaccountiddate";
    public static final String SERVICE_GETETCTRANSBYACCOUNTIDDATE = "service_getetctransbyaccountiddate";

    public ApiService() {
        super("ApiService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent messageIntent = new Intent(API_SERVICE_MESSAGE);
        String error = "";
        RequestPackage requestPackage;
        String msgtype = intent.getStringExtra(SERVICE_TYPE);
        if (msgtype.equalsIgnoreCase(SERVICE_CREATEQUEUE)) {
            GlobalVariables appContext = (GlobalVariables) getApplicationContext();
            requestPackage = appContext.getRequestpackage();

        }else{
            requestPackage =
                    intent.getParcelableExtra(REQUEST_PACKAGE);
        }



        String response = null;
        try {
            if (msgtype.equalsIgnoreCase(SERVICE_LOGIN)) {
                response = HttpHelper.downloadFromFeed(requestPackage);
            }else if (msgtype.equalsIgnoreCase(SERVICE_CREATEQUEUE)) {
                response = HttpHelper.requestXPay(requestPackage);
            }
            else {
                response = HttpHelper.requestXPay(requestPackage);
            }

        } catch (IOException e) {
            e.printStackTrace();
            error = e.getMessage();
        }

        Gson gson = new Gson();


        if (msgtype.equalsIgnoreCase(SERVICE_LOGIN)) {
            LoginToken logintoken = null;
            if (!TextUtils.isEmpty(response))
                logintoken = gson.fromJson(response, LoginToken.class);
            messageIntent.putExtra(SERVICE_LOGIN, logintoken);

        } else if (msgtype.equalsIgnoreCase(SERVICE_CREATEQUEUEUSER)) {
            QueueUserMessage queueusermessage = null;
            if (!TextUtils.isEmpty(response))
                queueusermessage = gson.fromJson(response, QueueUserMessage.class);
            messageIntent.putExtra(SERVICE_CREATEQUEUEUSER, queueusermessage);
        }else if (msgtype.equalsIgnoreCase(SERVICE_CREATEQUEUE)) {
            QueueMessage queueMessage = null;
            if (!TextUtils.isEmpty(response))
                queueMessage = gson.fromJson(response, QueueMessage.class);
                messageIntent.putExtra(SERVICE_CREATEQUEUE, queueMessage);
        }
//        else if(msgtype.equalsIgnoreCase(SERVICE_HOMESUMMARY)){
//            HomeSummary homesummary = null;
//            if(!TextUtils.isEmpty(response))
//                homesummary = gson.fromJson(response, HomeSummary.class);
//            messageIntent.putExtra(SERVICE_HOMESUMMARY, homesummary);
//
//        }else if(msgtype.equalsIgnoreCase(SERVICE_CARD_ACTIVATION)){
//            ActivatedCardMessage activatedcardmessage = null;
//            if(!TextUtils.isEmpty(response))
//                activatedcardmessage = gson.fromJson(response, ActivatedCardMessage.class);
//            messageIntent.putExtra(SERVICE_CARD_ACTIVATION, activatedcardmessage);
//
//        }else if(msgtype.equalsIgnoreCase(SERVICE_GETREGISTERPROFILE)){
//            ProfileRegisterMessage profileregistermessage = null;
//            if(!TextUtils.isEmpty(response)) {
//                profileregistermessage = gson.fromJson(response, ProfileRegisterMessage.class);
//                messageIntent.putExtra(SERVICE_GETREGISTERPROFILE, profileregistermessage);
//            }
//
//        }else if(msgtype.equalsIgnoreCase(SERVICE_SAVEREGISTERPROFILE)){
//            SaveProfileRegisterMessage saveprofileregistermessage = null;
//            if(!TextUtils.isEmpty(response))
//                saveprofileregistermessage = gson.fromJson(response, SaveProfileRegisterMessage.class);
//            messageIntent.putExtra(SERVICE_SAVEREGISTERPROFILE, saveprofileregistermessage);
//
//        }else if(msgtype.equalsIgnoreCase(SERVICE_EDITPROFILE)){
//            SaveProfileRegisterMessage saveprofileregistermessage = null;
//            if(!TextUtils.isEmpty(response))
//                saveprofileregistermessage = gson.fromJson(response, SaveProfileRegisterMessage.class);
//            messageIntent.putExtra(SERVICE_EDITPROFILE, saveprofileregistermessage);
//        }else if(msgtype.equalsIgnoreCase(SERVICE_GETNOTIFICATIONS)){
//            NotiSystem notisystem = null;
//            if(!TextUtils.isEmpty(response))
//                notisystem = gson.fromJson(response, NotiSystem.class);
//            messageIntent.putExtra(SERVICE_GETNOTIFICATIONS, notisystem);
//        }else if(msgtype.equalsIgnoreCase(SERVICE_DELETESYSTEMMESSAGE)){
//            DeletedSystemMessage deletedSystemMessage = null;
//            if(!TextUtils.isEmpty(response))
//                deletedSystemMessage = gson.fromJson(response, DeletedSystemMessage.class);
//            messageIntent.putExtra(SERVICE_DELETESYSTEMMESSAGE, deletedSystemMessage);
//        }else if(msgtype.equalsIgnoreCase(SERVICE_GETTOPUPTRANSBYACCOUNTIDDATE)){
//            TopupHistoryMessage topupHistoryMessage = null;
//            if(!TextUtils.isEmpty(response))
//                topupHistoryMessage = gson.fromJson(response, TopupHistoryMessage.class);
//            messageIntent.putExtra(SERVICE_GETTOPUPTRANSBYACCOUNTIDDATE, topupHistoryMessage);
//        }else if(msgtype.equalsIgnoreCase(SERVICE_GETETCTRANSBYACCOUNTIDDATE)){
//            ETCHistoryMessage etcHistoryMessage = null;
//            if(!TextUtils.isEmpty(response))
//                etcHistoryMessage = gson.fromJson(response, ETCHistoryMessage.class);
//            messageIntent.putExtra(SERVICE_GETETCTRANSBYACCOUNTIDDATE, etcHistoryMessage);
//        }
        messageIntent.putExtra(SERVICE_TYPE, msgtype);
        messageIntent.putExtra(SERVICE_ERROR, error);
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}
