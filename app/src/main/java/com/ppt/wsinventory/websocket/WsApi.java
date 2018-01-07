package com.ppt.wsinventory.websocket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.ApiModel;
import com.ppt.wsinventory.model.ApiParam;
import com.ppt.wsinventory.model.Solution;
import com.ppt.wsinventory.model.TableToDelete;
import com.ppt.wsinventory.services.WsSyncService;
import com.ppt.wsinventory.util.HexStringConverter;
import com.ppt.wsinventory.util.JsonHelper;
import com.ppt.wsinventory.util.Utility;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by User on 30/12/2017.
 */

public class WsApi  {
    public final String WEBSOCKET_URL = "ws://52.230.10.246:9090/wsmessage";
    private static final String TAG = "Ws-WsApi";
    private Context mContext;
    private GlobalVariables appContext;
    public WsApi(Context mContext) {
        this.mContext = mContext;
        appContext = (GlobalVariables) mContext.getApplicationContext();
    }

    public void setAppContext(GlobalVariables appContext) {
        this.appContext = appContext;
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

            Intent intent = new Intent(mContext, WsSyncService.class);
            intent.putExtra(WsSyncService.SERVICE_TYPE, WsSyncService.SERVICE_LOGIN);
            mContext.startService(intent);
    }
    public void getActionList(){
        Intent intent = new Intent(mContext, WsSyncService.class);
        intent.putExtra(WsSyncService.SERVICE_TYPE, WsSyncService.SERVICE_REQUEST);
        mContext.startService(intent);
    }
    public void doSync(){
        Gson gson = JsonHelper.getGson();
        String jsonString = "";
        String response = appContext.getResponseMessage();
        response = HexStringConverter.getHexStringConverterInstance().hexToString(response);
        ApiModel apiModel = gson.fromJson(response,ApiModel.class);
        boolean hasmore = false;
        if(apiModel.getName().equalsIgnoreCase(ApiModel.GETTABLESTODELETE)){
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<TableToDelete>>(){}.getType();
            List<TableToDelete> tableToDeletes = gson.fromJson(jsonString, listType);
            for (TableToDelete toDelete: tableToDeletes){
                Log.i(TAG, "Delete Table: " + toDelete.getTablename());
            }
            RemoveActionList(apiModel.getName());

        }else if(apiModel.getName().equalsIgnoreCase(ApiModel.GETSOLUTIONS)){
            jsonString = apiModel.getMessage();
            Type listType = new TypeToken<ArrayList<Solution>>(){}.getType();
            List<Solution> solutionList = gson.fromJson(jsonString, listType);
            for (Solution solution: solutionList){
                Log.i(TAG, "Solution Name : " + solution.getSolutionName());
            }
            RemoveActionList(apiModel.getName());
        }
        if(appContext.getActionLists().size() > 0) {
            ActionList actionList = appContext.getActionLists().get(0);
            List<ApiParam> params = new ArrayList<>();
            params.add(
                    new ApiParam("actionname", actionList.getActionname())
            );
            Date ts = appContext.getTs(); // new GregorianCalendar(2001, 1, 1, 0, 0, 0).getTime();
            appContext.setTs(ts);
            params.add(
                    new ApiParam("solutionname", appContext.getSolutionname())
            );
            params.add(
                    new ApiParam("ts", Utility.dateFormat.format(ts))
            );

            jsonString = gson.toJson(params);
            Log.i(TAG, "dosync: " + jsonString);
            ApiModel apimodel= new ApiModel(1,actionList.getActionname(), ApiModel.TYPE_GET, jsonString );
            jsonString = gson.toJson(apimodel);
            String req = "";
            try {
                req =  HexStringConverter.getHexStringConverterInstance().stringToHex(jsonString);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            Log.i(TAG, "dosync: " + req);

//            String req = "7b226964223a312c226e616d65223a22676574416374696f6e4c697374222c2274797065223a22676574222c226d657373616765223a225b7b5c226e616d655c223a5c226e6577757365725c222c5c2276616c75655c223a5c22547275655c227d2c7b5c226e616d655c223a5c22736f6c7574696f6e6e616d655c222c5c2276616c75655c223a5c22574d535c227d5d227d";

            appContext.setRequestMessage(req);
            Intent intent = new Intent(mContext, WsSyncService.class);
            intent.putExtra(WsSyncService.SERVICE_TYPE, WsSyncService.SERVICE_REQUEST);
            mContext.startService(intent);
        }
    }
    public void RemoveActionList(String actionname){
        for(ActionList actionList: appContext.getActionLists()){
            if(actionList.getActionname().equalsIgnoreCase(actionname)){
                appContext.getActionLists().remove(actionList);
                break;
            }
        }
    }

}
