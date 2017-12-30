package com.ppt.wsinventory.websocket;

import android.content.Context;
import android.content.Intent;

import com.ppt.wsinventory.services.WsService;

/**
 * Created by User on 30/12/2017.
 */

public class WsApi implements ServerConnection.ServerListener  {

    private Context mContext;

    public WsApi(Context mContext) {
        this.mContext = mContext;
    }

    public WsApi() {
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void TestWebSocket(){

            Intent intent = new Intent(mContext, WsService.class);
            intent.putExtra(WsService.SERVICE_TYPE, WsService.SERVICE_LOGIN);
            mContext.startService(intent);
    }



    @Override
    public void onNewMessage(String message) {

    }

    @Override
    public void onStatusChange(ServerConnection.ConnectionStatus status) {

    }
}
