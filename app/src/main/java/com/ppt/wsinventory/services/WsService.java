package com.ppt.wsinventory.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.websocket.ServerConnection;
import com.ppt.wsinventory.websocket.WsApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class WsService extends IntentService {
    public static final String API_SERVICE_SYNC = "api_service_sync";
    public static final String API_SERVICE_MESSAGE = "api_service_message";
    public static final String SERVICE_TYPE = "service_type";
    public static final String SERVICE_LOGIN = "login";
    public static final String SERVICE_GOODSID = "goodsid";
    public static final String SERVICE_REQUEST = "service_request";
    public static final String SERVICE_RESPONSE = "service_response";
    public static final String SERVICE_ERROR = "service_error";
    private static final String TAG = "WS-WsSyncService";
    public final String WEBSOCKET_URL = "ws://52.230.10.246:9090/wsmessage";
//    public final String WEBSOCKET_URL = "ws://192.168.1.9:9090/wsmessage";
    public final String WEBSOCKET_SHOP_URL = "ws://192.168.1.9:9090/wsmessage";
    private ServerConnection mServerConnection;
    private boolean bopen = false;
    String msgtype;

//    private WebSocket mWebSocket;
//    private OkHttpClient mClient;
//    private String mServerUrl;
//    private Handler mMessageHandler;
//    private Handler mStatusHandler;

    public WsService() {
        super("WsService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        msgtype = intent.getStringExtra(SERVICE_TYPE);
        GlobalVariables appContext = (GlobalVariables) getApplicationContext();
        String requestmessage = appContext.getRequestMessage();
        SendMessage(requestmessage);
        appContext.setRequestMessage("");
    }

    private void SendMessage(String text) {
        OkHttpClient mClient;
        String mServerUrl;
        Handler mMessageHandler;
        Handler mStatusHandler;

        mClient = new OkHttpClient.Builder()
                .readTimeout(180, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
        if (msgtype.equalsIgnoreCase(SERVICE_GOODSID)) {
            mServerUrl = WEBSOCKET_SHOP_URL;

        } else {
            mServerUrl = WEBSOCKET_URL;
        }
        Request request = new Request.Builder()
                .url(mServerUrl)
                .build();
        WebSocket mWebSocket = mClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                Log.i(TAG, "onMessage: " + text);
                GlobalVariables appContext = (GlobalVariables) getApplicationContext();
                String responsemessage = text;
                appContext.setResponseMessage(responsemessage);
                Intent messageIntent = new Intent(API_SERVICE_MESSAGE);
                messageIntent.putExtra(SERVICE_TYPE, SERVICE_RESPONSE);
                LocalBroadcastManager manager =
                        LocalBroadcastManager.getInstance(getApplicationContext());
                manager.sendBroadcast(messageIntent);
                webSocket.close(1000, "OK");
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                super.onMessage(webSocket, bytes);
                Log.i(TAG, "onMessage wit bytes: ");
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                super.onClosing(webSocket, code, reason);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                webSocket.cancel();
                String responsemessage = text;
                Log.i("WsSerivice","server respon"+ text);
                GlobalVariables appContext = (GlobalVariables) getApplicationContext();
                appContext.setResponseMessage("Cannot connect to server.");
                Intent messageIntent = new Intent(API_SERVICE_SYNC);
                messageIntent.putExtra(SERVICE_TYPE, SERVICE_ERROR);
                LocalBroadcastManager manager =
                        LocalBroadcastManager.getInstance(getApplicationContext());
                manager.sendBroadcast(messageIntent);
            }
        });

        mWebSocket.send(text);
    }

}
