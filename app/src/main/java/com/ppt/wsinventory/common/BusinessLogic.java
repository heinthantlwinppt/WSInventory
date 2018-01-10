package com.ppt.wsinventory.common;

import android.content.Context;
import android.database.Cursor;

import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationSettings;
import com.ppt.wsinventory.model.Item;
import com.ppt.wsinventory.util.Utility;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 03/01/2018.
 */

public class BusinessLogic {
    private DbAccess dbaccess;
    private GlobalVariables appContext;
    private Context mContext;
    public static String SOLUTION_NAME = "WMS-HH";

    public BusinessLogic(Context context) {
        this.appContext = (GlobalVariables) context.getApplicationContext();
        mContext = context;
    }

    public void doNewChangeUser(List<ActionList> actionLists) {
        dbaccess = DbAccess.getInstance();

    }

//    public AdministrationSettings getAdministrationSettings() {
////        AdministrationSettings administrationSettings = new AdministrationSettings();
//        AdministrationSettings administrationSetting = null;
//        dbaccess = DbAccess.getInstance();
//        if (dbaccess != null) {
//            if (!dbaccess.isOpen())
//                dbaccess.open();
//            Cursor cursor = dbaccess.readData(AdministrationSettings.TABLE_ADMINISTRATION_SETTINGS
//                    , Item.COLUMN_ALL
//                    , null, null, null, null, null
////                , new String[]{"1"}, null, null, null
//            );
//
//            while (cursor.moveToNext()) {
//                administrationSetting = new AdministrationSettings();
//                administrationSetting.setId(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_ID)));
//                administrationSetting.setH1(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H1)));
//                administrationSetting.setH2(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H2)));
//                administrationSetting.setH3(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H3)));
//                administrationSetting.setH4(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H4)));
//                administrationSetting.setT1(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T1)));
//                administrationSetting.setT2(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T2)));
//                administrationSetting.setT3(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T3)));
//                administrationSetting.setT4(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T4)));
//                administrationSetting.setDateformat(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DATEFORMAT)));
//                administrationSetting.setTimeformat(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_TIMEFORMAT)));
//                administrationSetting.setDatetimeformat(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DATETIMEFORMAT)));
//                try {
//                    administrationSetting.setSystem_date(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_SYSTTEM_DATE))));
//                } catch (ParseException e) {
//                   administrationSetting.setSystem_date(new Date(System.currentTimeMillis()));
//                }
//                administrationSetting.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_ACTIVE))));
//                administrationSetting.setLocation_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_LOCATION_ID)));
//                administrationSetting.setSolution_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_SOLUTION_ID)));
//                administrationSetting.setStaff_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_STAFF_ID)));
//                administrationSetting.setWeighscales_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_WEIGHSCALES_ID)));
//                administrationSetting.setDashboarditemlwith(Integer.parseInt(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DASHBOARDITEMLWITH))));
//                administrationSetting.setDashboarditempwith(Integer.parseInt(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DASHBOARDITEMPWITH))));
//                administrationSetting.setDashboardicon(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DASHBOARDICON)));
//                administrationSetting.setDevicetype_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DEVICETYPE_ID)));
//                administrationSetting.setDevice_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DEVICE_ID)));
//
////            administrationSettings.add(administrationSetting);
//            }
//
//            if (cursor != null && !cursor.isClosed()) {
//                cursor.close();
//            }
//        }
//        return administrationSetting;
//    }
}
