package com.ppt.wsinventory.common;

import android.content.Context;

import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.List;

/**
 * Created by User on 03/01/2018.
 */

public class BusinessLogic {
    private DbAccess dbaccess;
    private GlobalVariables appContext;
    private Context mContext ;
    public static String SOLUTION_NAME = "WMS-HH";

    public BusinessLogic(Context context) {
        this.appContext = (GlobalVariables) context.getApplicationContext();
        mContext = context;
    }
    public void doNewChangeUser(List<ActionList> actionLists){
        dbaccess = DbAccess.getInstance();

    }
}
