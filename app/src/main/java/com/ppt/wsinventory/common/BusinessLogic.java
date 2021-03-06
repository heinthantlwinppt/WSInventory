package com.ppt.wsinventory.common;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ppt.wsinventory.DashboardSmithJobOrder;
import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.InventoryCounters;
import com.ppt.wsinventory.InventoryTrays;
import com.ppt.wsinventory.MainActivity;
import com.ppt.wsinventory.ProductionListActivity;
import com.ppt.wsinventory.inventory.model.Inventory_PalletLoc;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.AdministrationSettings;
import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.model.ApiModel;
import com.ppt.wsinventory.model.ApiParam;
import com.ppt.wsinventory.model.BIN;
import com.ppt.wsinventory.model.InventoryBIN;
import com.ppt.wsinventory.model.InventoryPallet;
import com.ppt.wsinventory.model.InventoryPalletLoc;
import com.ppt.wsinventory.model.Manufacturing_smith_joborder;
import com.ppt.wsinventory.model.Pallet;
import com.ppt.wsinventory.model.WsDashboardModel;
import com.ppt.wsinventory.model.WsapiSynchistory;
import com.ppt.wsinventory.util.HexStringConverter;
import com.ppt.wsinventory.util.JsonHelper;
import com.ppt.wsinventory.util.Utility;
import com.ppt.wsinventory.websocket.WsApi;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by User on 03/01/2018.
 */

public class BusinessLogic {
    private DbAccess dbaccess;
    private GlobalVariables appContext;
    private Context mContext;
    public static String SOLUTION_NAME = "WMS-HH";
    public static String GOODS_ID = "A1113080028";
    public static final String TAG = "Ws-BusinessLogic";

    public BusinessLogic(Context context) {
        this.appContext = (GlobalVariables) context.getApplicationContext();
        mContext = context;
    }

    public BusinessLogic() {
    }

    public void doNewChangeUser(List<ActionList> actionLists) {
        dbaccess = DbAccess.getInstance();

    }

    public void openScreen(WsEvents.EventOpenScreen e) {
        if (e.getActionname().equalsIgnoreCase(WsEvents.OPEN_RECEIVING_INVENTORY)) {

            Intent intent = new Intent(this.appContext, DashboardSmithJobOrder.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(intent);
            Toast.makeText(mContext, "Show Receiving Detail", Toast.LENGTH_SHORT).show();

        } else if (e.getActionname().equalsIgnoreCase(WsEvents.OPEN_TAGGING_INVENTORY)) {

            Toast.makeText(mContext, "Show Tagging Detail", Toast.LENGTH_SHORT).show();

        } else if (e.getActionname().equalsIgnoreCase(WsEvents.OPEN_PRORECEIVING_LIST)) {

//            Intent intent = new Intent(this.appContext, ProreceivingListActivity.class);
//            Intent intent = new Intent(this.appContext, ProreceivingListActivity.class);
            Intent intent = new Intent(this.appContext, ProductionListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(intent);
            Toast.makeText(mContext, "Show Proreceiving List", Toast.LENGTH_SHORT).show();
        } else if (e.getActionname().equalsIgnoreCase(WsEvents.OPEN_TRAY)) {
            Intent intent = new Intent(this.appContext, InventoryTrays.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(intent);

        } else if (e.getActionname().equalsIgnoreCase(WsEvents.OPEN_COUNTER)) {
            Intent intent = new Intent(this.appContext, InventoryCounters.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(intent);


        } else if (e.getActionname().equalsIgnoreCase(WsEvents.OPEN_DASHBOARD)) {
            Intent intent = new Intent(this.appContext, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(intent);


        } else if (e.getActionname().equalsIgnoreCase(WsEvents.OPEN_CONFIRM_COUNTER)) {
            Intent intent = new Intent(this.appContext, InventoryCounters.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(intent);


        }
    }

    public Manufacturing_smith_joborder openSmithJobScreen(WsEvents.EventOpenSmithJob event) {
        dbaccess = DbAccess.getInstance();
        Manufacturing_smith_joborder manufacturing_smith_joborder = new Manufacturing_smith_joborder();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
            Cursor cursor = dbaccess.readData(Manufacturing_smith_joborder.TABLE_MANUFACTURING_SMITH_JOBORDER
                    , Manufacturing_smith_joborder.COLUMN_ALL
                    , Manufacturing_smith_joborder.COLUMN_JOBORDER_NO + " = ?"
                    , new String[]{event.getJoborder_no()}, null, null, null
            );

            while (cursor.moveToNext()) {
                Manufacturing_smith_joborder item = new Manufacturing_smith_joborder();
                item.setJoborder_no(cursor.getString(cursor.getColumnIndex(item.COLUMN_JOBORDER_NO)));
                try {
                    item.setJoborder_date(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_JOBORDER_DATE))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                item.setPrejewelout(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(item.COLUMN_PREJEWELOUT))));
                try {
                    item.setPrejewelout_date(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_PREJEWELOUT_DATE))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    item.setDate_target(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_DATE_TARGET))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    item.setDate_start(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_DATE_START))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    item.setDate_end(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_DATE_END))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                item.setRemarks(cursor.getString(cursor.getColumnIndex(item.COLUMN_REMARKS)));
                item.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTIVE))));
                item.setIs_delete(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(item.COLUMN_IS_DELETE))));
                try {
                    item.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(item.COLUMN_TS))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                item.setJoborder_type_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_JOBORDER_TYPE_ID))));
                item.setJobstatus_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_JOBSTATUS_ID))));
                item.setSmith_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_SMITH_ID))));
                item.setDensity(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_DENSITY))));
                item.setDiff_k(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_DIFF_K))));
                item.setDiff_p(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_DIFF_P))));
                item.setDiff_weight(Double.parseDouble(String.valueOf(cursor.getColumnIndex(item.COLUMN_DIFF_WEIGHT))));
                item.setDiff_y(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_DIFF_Y))));
                item.setPrint_count(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_PRINT_COUNT))));
                item.setProduct_weight(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_PRODUCT_WEIGHT))));
                item.setRemain_gold(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_REMAIN_GOLD))));
                item.setRemain_jewel(Double.parseDouble(cursor.getString(cursor.getColumnIndex(item.COLUMN_REMAIN_JEWEL))));
                item.setSave_count(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_SAVE_COUNT))));

                break;

            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

        }
        return manufacturing_smith_joborder;

    }
//        if(e.getJoborder_no().equalsIgnoreCase(WsEvents.OPEN_RECEIVING_INVENTORY)){
//
//            Intent intent = new Intent(this.appContext, DashboardSmithJobOrder.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            appContext.startActivity(intent);
//            Toast.makeText(mContext, "Show Receiving Detail", Toast.LENGTH_SHORT).show();
//        }
//        else if(e.getActionname().equalsIgnoreCase(WsEvents.OPEN_TAGGING_INVENTORY)){
//
//            Toast.makeText(mContext, "Show Tagging Detail", Toast.LENGTH_SHORT).show();
//        }
//    }

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


    public AdministrationStaff checkLoginUser(String staffid, String password) {
        AdministrationStaff staff = null;
//        appContext = (GlobalVariables) context.getApplicationContext();
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            Cursor cursor = dbaccess.readData(AdministrationStaff.TABLE_ADMINISTRATION_STAFF
                    , AdministrationStaff.COLUMN_ALL
                    , AdministrationStaff.COLUMN_NICK_NAME + " = ? and " + AdministrationStaff.COLUMN_NICK_NAME + " = ?"
                    , new String[]{staffid, password}, null, null, null
            );
            while (cursor.moveToNext()) {
                staff = new AdministrationStaff();
                staff.setId(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_ID)));
                staff.setStaff_name(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_STAFF_NAME)));
                staff.setFather_name(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_FATHER_NAME)));
                staff.setNick_name(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_NICK_NAME)));
                staff.setNrc_no(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_NRC_NO)));
                staff.setAddress(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_ADDRESS)));
                staff.setPhone_no(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_PHONE_NO)));
                staff.setHome_phone_no(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_HOME_PHONE_NO)));
                staff.setStaff_photo(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_STAFF_PHOTO)));
                try {
                    staff.setDate_joined(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_DATE_JOINED))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    staff.setDate_left(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_DATE_LEFT))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                staff.setUser_id(cursor.getInt(cursor.getColumnIndex(AdministrationStaff.COLUMN_USER_ID)));
                staff.setPassword(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_PASSWORD)));
                staff.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_ACTIVE))));
                staff.setRole(cursor.getString(cursor.getColumnIndex(AdministrationStaff.COLUMN_ROLE)));
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }

        }
        return staff;
    }

    public List<InventoryBIN> getAllInventoryBin() {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<InventoryBIN> inventory_binLocs = new ArrayList<>();
        String sql = "select t1.loc_name, t2.* from administration_locations as t1 \n" +
                "inner join inventory_bin as t2 \n" +
                "on t1.id = t2.location_id";
        Cursor cursor = dbaccess.readData(sql, null);
        while (cursor.moveToNext()) {
            InventoryBIN binLoc = new InventoryBIN();
            binLoc.setId(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_ID)));
            binLoc.setBin_name(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_NAME)));
            binLoc.setBin_description(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_DESCRIPTION)));
            binLoc.setBin_type(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_TYPE)));
            binLoc.setBarcode(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BARCODE)));
            binLoc.setTag(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_TAG)));
            binLoc.setLocation_id(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_LOCATION_ID)));
            binLoc.setNo_of_pallets(cursor.getInt(cursor.getColumnIndex(binLoc.COLUMN_NO_OF_PALLETS)));
            binLoc.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_ACTIVE))));
            try {
                binLoc.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            binLoc.setLocation_name(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_LOCATION_NAME)));

            inventory_binLocs.add(binLoc);

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return inventory_binLocs;
    }

    public boolean updateInventoryBin(InventoryBIN inventorybin) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        ContentValues values = new ContentValues();
        values.put(InventoryBIN.COLUMN_ID, inventorybin.getId());
        values.put(InventoryBIN.COLUMN_BIN_NAME, inventorybin.getBin_name());
        values.put(InventoryBIN.COLUMN_BIN_DESCRIPTION, inventorybin.getBin_description());
        values.put(InventoryBIN.COLUMN_BIN_TYPE, inventorybin.getBin_type());
        values.put(InventoryBIN.COLUMN_BARCODE, inventorybin.getBarcode());
        values.put(InventoryBIN.COLUMN_TAG, inventorybin.getTag());
        values.put(InventoryBIN.COLUMN_LOCATION_ID, inventorybin.getLocation_id());
        values.put(InventoryBIN.COLUMN_UPLOADED, (inventorybin.isUploaded() ? 1 : 0));
        values.put(InventoryBIN.COLUMN_ACTIVE, (inventorybin.isActive() ? 1 : 0));
        return dbaccess.updateData(InventoryBIN.TABLE_INVENTORY_BIN, values, InventoryBIN.COLUMN_ID + "= ?", new String[]{inventorybin.getId()});
    }
    public boolean updateInventoryPallet(InventoryPallet inventorypallet) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }

        ContentValues values = new ContentValues();
        values.put(InventoryPalletLoc.COLUMN_ID,inventorypallet.getId());
        values.put(InventoryPalletLoc.COLUMN_PALLET_NAME,inventorypallet.getPallet_name());
        values.put(InventoryPalletLoc.COLUMN_PALLET_DESCRIPTION,inventorypallet.getPallet_description());
        values.put(InventoryPalletLoc.COLUMN_PALLET_TYPE,inventorypallet.getPallet_type());
        values.put(InventoryPalletLoc.COLUMN_PALLET_BARCODE,inventorypallet.getBarcode());
        values.put(InventoryPalletLoc.COLUMN_PALLET_TAG,inventorypallet.getTag());
        values.put(InventoryPalletLoc.COLUMN_PALLET_WEIGHT,inventorypallet.getWeight());
        values.put(InventoryPalletLoc.COLUMN_PALLET_ACTIVE, (inventorypallet.isActive() ? 1 : 0));
        values.put(InventoryPalletLoc.COLUMN_PALLET_IS_USED, (inventorypallet.isUsed() ? 1 : 0));
        values.put(InventoryPallet.COLUMN_UPLOADED,(inventorypallet.isUploaded() ? 1 : 0));
        values.put(InventoryPallet.COLUMN_TS,Utility.dateFormat.format(inventorypallet.getTs()));
        return dbaccess.updateData(InventoryPallet.TABLE_INVENTORY_PALLET,values,InventoryPallet.COLUMN_ID + "= ?", new String[]{inventorypallet.getId()});

    }

    public List<BIN> getAllBinToSend(boolean uploaded) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<BIN> inventory_binLocs = new ArrayList<>();
        Cursor cursor = dbaccess.readData(InventoryBIN.TABLE_INVENTORY_BIN
                , InventoryBIN.COLUMN_ALL
                , InventoryBIN.COLUMN_UPLOADED + " = ? ", new String[]{(uploaded) ? "1" : "0"}
                , null, null, null);
        while (cursor.moveToNext()) {
            BIN binLoc = new BIN();
            binLoc.setId(cursor.getString(cursor.getColumnIndex(InventoryBIN.COLUMN_ID)));
            binLoc.setBinName(cursor.getString(cursor.getColumnIndex(InventoryBIN.COLUMN_BIN_NAME)));
            binLoc.setBinDescription(cursor.getString(cursor.getColumnIndex(InventoryBIN.COLUMN_BIN_DESCRIPTION)));
            binLoc.setBinType(cursor.getString(cursor.getColumnIndex(InventoryBIN.COLUMN_BIN_TYPE)));
            binLoc.setBarcode(cursor.getString(cursor.getColumnIndex(InventoryBIN.COLUMN_BARCODE)));
            binLoc.setTag(cursor.getString(cursor.getColumnIndex(InventoryBIN.COLUMN_TAG)));
            binLoc.setLocation(cursor.getString(cursor.getColumnIndex(InventoryBIN.COLUMN_LOCATION_ID)));
            binLoc.setNo_of_pallets(cursor.getInt(cursor.getColumnIndex(InventoryBIN.COLUMN_NO_OF_PALLETS)));
            binLoc.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(InventoryBIN.COLUMN_ACTIVE))));
            try {
                binLoc.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(InventoryBIN.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
//            binLoc.setLocation_name(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_LOCATION_NAME)));

            inventory_binLocs.add(binLoc);

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return inventory_binLocs;
    }
    public List<Pallet> getAllPalletToSend(boolean uploaded) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<Pallet> pallets = new ArrayList<>();
        Cursor cursor = dbaccess.readData(InventoryPallet.TABLE_INVENTORY_PALLET
                , InventoryPallet.COLUMN_ALL
                , InventoryPallet.COLUMN_UPLOADED + " = ? ", new String[]{(uploaded) ? "1" : "0"}
                , null, null, null);
        while (cursor.moveToNext()) {
            Pallet pallet = new Pallet();
            pallet.setId(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_ID)));
            pallet.setPalletName(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_PALLET_NAME)));
            pallet.setPalletDescription(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_PALLET_DESCRIPTION)));
            pallet.setPalletType(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_PALLET_TYPE)));
            pallet.setBarcode(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_BARCODE)));
            pallet.setTag(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_TAG)));
            pallet.setLocation(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_LOCATION_ID)));
            pallet.setWeight(cursor.getDouble(cursor.getColumnIndex(InventoryPallet.COLUMN_WEIGHT)));
            pallet.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_ACTIVE))));
            pallet.setIsUsed(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_IS_USED))));
            try {
                pallet.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pallet.setLocation(cursor.getString(cursor.getColumnIndex(InventoryPallet.COLUMN_LOCATION_ID)));

            pallets.add(pallet);

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return pallets;
    }

    public void sendAllDataToServer() {
        appContext.setNewUser(false);
        String req = "";
        Gson gson = JsonHelper.getGson();
        List<ApiParam> params = new ArrayList<>();
        appContext.setSolutionname(appContext.getWssetting().getSolution_id());
        appContext.setDeviceid(appContext.getWssetting().getDevice_id());

        if (appContext.isNewUser()) {
            params.add(
                    new ApiParam("newuser", "True")
            );

        } else {
            params.add(
                    new ApiParam("newuser", "False")
            );
        }

        params.add(
                new ApiParam("solutionname", appContext.getSolutionname())
        );
        params.add(
                new ApiParam("deviceid", appContext.getDeviceid())
        );
//            Date toDate = new Date(System.currentTimeMillis());
        Date ts = new GregorianCalendar(2001, 0, 1, 0, 0, 0).getTime();
        appContext.setTs(ts);
        String jsonString = gson.toJson(params);
        ApiModel apimodel = new ApiModel(1, ApiModel.GETSENDDATALIST, ApiModel.TYPE_GET, jsonString);
        jsonString = gson.toJson(apimodel);
        try {
            req = HexStringConverter.getHexStringConverterInstance().stringToHex(jsonString);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        appContext.setRequestMessage(req);
        WsApi wsapi = new WsApi(appContext);
        wsapi.getSendDataList();
    }

    public List<InventoryBIN> getAllbinByLocation(String id) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<InventoryBIN> inventory_binLocs = new ArrayList<>();
        String sql = "select * from administration_locations as t1 \n" +
                "inner join inventory_bin as t2 \n" +
                "on t1.id = t2.location_id  where t1.id = ?";
        Cursor cursor = null;
        try {
            cursor = dbaccess.readDataSQL(sql, new String[]{id});
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (cursor.moveToNext()) {
            InventoryBIN binLoc = new InventoryBIN();
            binLoc.setId(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_ID)));
            binLoc.setBin_name(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_NAME)));
            binLoc.setBin_description(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_DESCRIPTION)));
            binLoc.setBin_type(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_TYPE)));
            binLoc.setBarcode(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BARCODE)));
            binLoc.setTag(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_TAG)));
            binLoc.setLocation_id(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_LOCATION_ID)));
            binLoc.setActive(cursor.getInt(cursor.getColumnIndex(binLoc.COLUMN_ACTIVE)) > 0);
            try {
                binLoc.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            binLoc.setNo_of_pallets(cursor.getInt(cursor.getColumnIndex(binLoc.COLUMN_NO_OF_PALLETS)));
            binLoc.setLocation_name(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_LOCATION_NAME)));
            inventory_binLocs.add(binLoc);

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return inventory_binLocs;

    }

    public InventoryBIN getInventoryBinByBarcode(String barcode) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        InventoryBIN inventory_binLocs = null;
        String sql = "select t1.*, t2.* from administration_locations as t1 \n" +
                "                inner join inventory_bin as t2  \n" +
                "                on t1.id = t2.location_id where t2.barcode = ?";
        Cursor cursor = dbaccess.readDataSQL(sql, new String[]{barcode});

        while (cursor.moveToNext()) {
            InventoryBIN binLoc = new InventoryBIN();
            binLoc.setId(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_ID)));
            binLoc.setBin_name(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_NAME)));
            binLoc.setBin_description(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_DESCRIPTION)));
            binLoc.setBin_type(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_TYPE)));
            binLoc.setBarcode(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BARCODE)));
            binLoc.setTag(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_TAG)));
            binLoc.setLocation_id(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_LOCATION_ID)));
            binLoc.setNo_of_pallets(cursor.getInt(cursor.getColumnIndex(binLoc.COLUMN_NO_OF_PALLETS)));
            binLoc.setActive(cursor.getInt(cursor.getColumnIndex(binLoc.COLUMN_ACTIVE)) > 0);
            try {
                binLoc.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            binLoc.setLocation_name(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_LOCATION_NAME)));

            inventory_binLocs = binLoc;

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return inventory_binLocs;
    }

    public InventoryBIN getInventoryBinById(String id) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        InventoryBIN inventory_binLocs = null;
        String sql = "select t1.*, t2.* from administration_locations as t1 \n" +
                "                inner join inventory_bin as t2  \n" +
                "                on t1.id = t2.location_id where t2.id = ?";
        Cursor cursor = dbaccess.readDataSQL(sql, new String[]{id});

        while (cursor.moveToNext()) {
            InventoryBIN binLoc = new InventoryBIN();
            binLoc.setId(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_ID)));
            binLoc.setBin_name(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_NAME)));
            binLoc.setBin_description(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_DESCRIPTION)));
            binLoc.setBin_type(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BIN_TYPE)));
            binLoc.setBarcode(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_BARCODE)));
            binLoc.setTag(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_TAG)));
            binLoc.setLocation_id(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_LOCATION_ID)));
            binLoc.setNo_of_pallets(cursor.getInt(cursor.getColumnIndex(binLoc.COLUMN_NO_OF_PALLETS)));
            binLoc.setActive(cursor.getInt(cursor.getColumnIndex(binLoc.COLUMN_ACTIVE)) > 0);
            try {
                binLoc.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            binLoc.setLocation_name(cursor.getString(cursor.getColumnIndex(binLoc.COLUMN_LOCATION_NAME)));

            inventory_binLocs = binLoc;

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return inventory_binLocs;
    }

    public InventoryPalletLoc getPalletById(String id) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        InventoryPalletLoc result = null;
        String sql = "select t1.*, t2.* from administration_locations as t1 \n" +
                "                              inner join inventory_pallet as t2\n" +
                "                              on t1.id = t2.location_id where t2.id = ?";
        Cursor cursor = dbaccess.readDataSQL(sql, new String[]{id});

        while (cursor.moveToNext()) {
            InventoryPalletLoc palletLoc = new InventoryPalletLoc();
            palletLoc.setId(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_ID)));
            palletLoc.setPallet_name(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_NAME)));
            palletLoc.setPallet_description(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_DESCRIPTION)));
            palletLoc.setPallet_type(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_TYPE)));
            palletLoc.setPallet_barcode(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_BARCODE)));
            palletLoc.setPallet_tag(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_TAG)));
            palletLoc.setPallet_location_id(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_LOCATION_ID)));
            palletLoc.setPallet_active(cursor.getInt(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_ACTIVE)) > 0);
            palletLoc.setPallet_weight(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_WEIGHT)));
            palletLoc.setPallet_is_used(cursor.getInt(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_IS_USED)) > 0);
            try {
                palletLoc.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            palletLoc.setPallet_location(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_LOCATION)));

            result = palletLoc;

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }


        return result;
    }

    public List<WsDashboardModel> getAllDashboardItems() {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<WsDashboardModel> dashboarditems = new ArrayList<>();
        String sql = "select id, title, image, is_folder, actionname from administration_wsdashboard where parent_id = 0 order by displayno";
        Cursor cursor = dbaccess.readDataSQL(sql, null);

        while (cursor.moveToNext()) {
            WsDashboardModel item = new WsDashboardModel();
            item.setTitle(cursor.getString(cursor.getColumnIndex(item.COLUMN_TITLE)));
            item.setImage(cursor.getString(cursor.getColumnIndex(item.COLUMN_IMAGE)));
            item.setActionname(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTION_NAME)));
            item.setFolder(cursor.getInt(cursor.getColumnIndex(item.COLUMN_IS_FOLDRE)) > 0);
            item.setId(cursor.getInt(cursor.getColumnIndex(item.COLUMN_ID)));
            dashboarditems.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return dashboarditems;


    }

    public WsDashboardModel getDashboardItem(int id) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        String sql = "select id, title, image, is_folder, actionname, parent_id from administration_wsdashboard where id = " + id + " order by displayno";
        Cursor cursor = dbaccess.readDataSQL(sql, null);

        WsDashboardModel item = null;
        while (cursor.moveToNext()) {
            item = new WsDashboardModel();
            item.setTitle(cursor.getString(cursor.getColumnIndex(item.COLUMN_TITLE)));
            item.setImage(cursor.getString(cursor.getColumnIndex(item.COLUMN_IMAGE)));
            item.setActionname(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTION_NAME)));
            item.setFolder(cursor.getInt(cursor.getColumnIndex(item.COLUMN_IS_FOLDRE)) > 0);
            item.setId(cursor.getInt(cursor.getColumnIndex(item.COLUMN_ID)));
            item.setParent_id(cursor.getInt(cursor.getColumnIndex(item.COLUMN_PARENT_ID)));

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return item;


    }

    public List<WsDashboardModel> getDashboardItems(int id) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<WsDashboardModel> dashboarditems = new ArrayList<>();
        String sql = "select id, title, image, is_folder, actionname, parent_id from administration_wsdashboard where parent_id = " + id + " order by displayno";
        Cursor cursor = dbaccess.readDataSQL(sql, null);

        while (cursor.moveToNext()) {
            WsDashboardModel item = new WsDashboardModel();
            item.setTitle(cursor.getString(cursor.getColumnIndex(item.COLUMN_TITLE)));
            item.setImage(cursor.getString(cursor.getColumnIndex(item.COLUMN_IMAGE)));
            item.setActionname(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTION_NAME)));
            item.setFolder(cursor.getInt(cursor.getColumnIndex(item.COLUMN_IS_FOLDRE)) > 0);
            item.setId(cursor.getInt(cursor.getColumnIndex(item.COLUMN_ID)));
            item.setParent_id(cursor.getInt(cursor.getColumnIndex(item.COLUMN_PARENT_ID)));
            appContext.setParentid(item.getParent_id());
            dashboarditems.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return dashboarditems;


    }
    public List<Inventory_PalletLoc> getAllinventoryPalletLocation(String id){
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<Inventory_PalletLoc> inventory_palletLocs = new ArrayList<>();
        String sql = "select invP.* , admloc.loc_name as location_name\n" +
                "from inventory_pallet as invP\n" +
                "inner join administration_locations as admloc\n" +
                "on invP.location_id = admloc.id\n";

        if(!id.isEmpty() && !id.equals("") && !id.equals("All Shop")){

            sql = sql + " where admloc.id = '"+ id+"'";
        }

        Cursor cursor = dbaccess.readDataSQL(sql, null);

        while (cursor.moveToNext()) {
            Inventory_PalletLoc palletLoc = new Inventory_PalletLoc();
            palletLoc.setId(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_ID)));
            palletLoc.setPallet_name(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_NAME)));
            palletLoc.setPallet_description(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_DESCRIPTION)));
            palletLoc.setPallet_type(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_TYPE)));
            palletLoc.setBarcode(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_BARCODE)));
            palletLoc.setTag(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_TAG)));
            palletLoc.setIs_used(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_IS_USED))));
            palletLoc.setLocation_id(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_LOCATION_ID)));
            palletLoc.setWeight(Double.parseDouble(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_WEIGHT))));
            try {
                palletLoc.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            palletLoc.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_ACTIVE))));
            palletLoc.setLocation_name(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_LOCATION_NAME)));

            inventory_palletLocs.add(palletLoc);

        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return inventory_palletLocs;
    }
    public Inventory_PalletLoc getInventoryPalletLocationByBarcode(String barcode){
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        Inventory_PalletLoc palletLoc = null;
        String sql = "select invP.* , admloc.loc_name as location_name\n" +
                "from inventory_pallet as invP\n" +
                "inner join administration_locations as admloc\n" +
                "on invP.location_id = admloc.id and invP.barcode = ?\n";

        Cursor cursor = dbaccess.readDataSQL(sql, new String[]{barcode});

        while (cursor.moveToNext()) {
            palletLoc = new Inventory_PalletLoc();
            palletLoc.setId(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_ID)));
            palletLoc.setPallet_name(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_NAME)));
            palletLoc.setPallet_description(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_DESCRIPTION)));
            palletLoc.setPallet_type(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_PALLET_TYPE)));
            palletLoc.setBarcode(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_BARCODE)));
            palletLoc.setTag(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_TAG)));
            palletLoc.setIs_used(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_IS_USED))));
            palletLoc.setLocation_id(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_LOCATION_ID)));
            palletLoc.setWeight(Double.parseDouble(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_WEIGHT))));
            try {
                palletLoc.setTs(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_TS))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            palletLoc.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_ACTIVE))));
            palletLoc.setLocation_name(cursor.getString(cursor.getColumnIndex(palletLoc.COLUMN_LOCATION_NAME)));

            break;
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return palletLoc;
    }
    public List<AdministrationLocations> getAllLocation() {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<AdministrationLocations> administrationlocations = new ArrayList<>();
//        String sql = "select id, title, image, is_folder, actionname from administration_wsdashboard where id = "+ latest_parient_id +" order by displayno";
//        String sql = "select loc_name from administration_locations";

//        Cursor cursor = dbaccess.readData(sql, null);
        Cursor cursor = dbaccess.readData(AdministrationLocations.TABLE_ADMINISTRATION_LOCATIONS
                , AdministrationLocations.COLUMN_ALL
                , AdministrationLocations.COLUMN_LOC_NAME
        );
        while (cursor.moveToNext()) {
            AdministrationLocations item = new AdministrationLocations();
            item.setId(cursor.getString(cursor.getColumnIndex(item.COLUMN_ID)));
            item.setLoc_name(cursor.getString(cursor.getColumnIndex(item.COLUMN_LOC_NAME)));
            item.setLoc_addr(cursor.getString(cursor.getColumnIndex(item.COLUMN_LOC_ADDR)));
            item.setLongitude(cursor.getDouble(cursor.getColumnIndex(item.COLUMN_LONGITUDE)));
            item.setLatitude(cursor.getDouble(cursor.getColumnIndex(item.COLUMN_LATITUDE)));
            item.setReceiving_bin(cursor.getString(cursor.getColumnIndex(item.COLUMN_RECEIVING_BIN)));
            item.setActive(cursor.getInt(cursor.getColumnIndex(item.COLUMN_ACTIVE)) > 0);

            administrationlocations.add(item);
        }

        return administrationlocations;
    }

    public List<WsDashboardModel> getAllChild(int id) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<WsDashboardModel> dashboarditems = new ArrayList<>();
        String sql = "select id, title, image, is_folder, actionname, parent_id from administration_wsdashboard where parent_id = " + id + " order by displayno";

        Cursor cursor = dbaccess.readData(sql, null);

        while (cursor.moveToNext()) {
            WsDashboardModel item = new WsDashboardModel();
            item.setTitle(cursor.getString(cursor.getColumnIndex(item.COLUMN_TITLE)));
            item.setImage(cursor.getString(cursor.getColumnIndex(item.COLUMN_IMAGE)));
            item.setActionname(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTION_NAME)));
            item.setFolder(cursor.getInt(cursor.getColumnIndex(item.COLUMN_IS_FOLDRE)) > 0);
            item.setId(cursor.getInt(cursor.getColumnIndex(item.COLUMN_ID)));
            item.setParent_id(cursor.getInt(cursor.getColumnIndex(item.COLUMN_PARENT_ID)));
            dashboarditems.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return dashboarditems;
    }

    public List<WsDashboardModel> getparient(int latest_parient_id) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        List<WsDashboardModel> dashboarditems = new ArrayList<>();
//        String sql = "select id, title, image, is_folder, actionname from administration_wsdashboard where id = "+ latest_parient_id +" order by displayno";
        String sql = "select t1.* \n" +
                "from administration_wsdashboard t1 inner join administration_wsdashboard t2 \n" +
                "on t2.parent_id = t1.parent_id\n" +
                "where t2.id = " + latest_parient_id;

        Cursor cursor = dbaccess.readData(sql, null);

        while (cursor.moveToNext()) {
            WsDashboardModel item = new WsDashboardModel();
            item.setTitle(cursor.getString(cursor.getColumnIndex(item.COLUMN_TITLE)));
            item.setImage(cursor.getString(cursor.getColumnIndex(item.COLUMN_IMAGE)));
            item.setActionname(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTION_NAME)));
            item.setFolder(cursor.getInt(cursor.getColumnIndex(item.COLUMN_IS_FOLDRE)) > 0);
            item.setId(cursor.getInt(cursor.getColumnIndex(item.COLUMN_ID)));
            dashboarditems.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return dashboarditems;

    }

    public WsapiSynchistory getSynchistoryByTableName(
            String tablename
    ) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        Cursor cursor = dbaccess.readData(WsapiSynchistory.TABLE_WSAPI_SYNCHISTORY
                , WsapiSynchistory.COLUMN_ALL
                , WsapiSynchistory.COLUMN_TABLENAME + "=?"
                , new String[]{tablename}
                , null, null, null
        );
        WsapiSynchistory wsapisynchistory = null;
        while (cursor.moveToNext()) {
            wsapisynchistory = new WsapiSynchistory();
            wsapisynchistory.setDevice_id(cursor.getString(cursor.getColumnIndex(wsapisynchistory.COLUMN_DEVICE_ID)));
            wsapisynchistory.setTablename(cursor.getString(cursor.getColumnIndex(wsapisynchistory.COLUMN_TABLENAME)));
            try {
                wsapisynchistory.setTimestamp(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(wsapisynchistory.COLUMN_TIMESTAMP))));
            } catch (ParseException e) {
                wsapisynchistory.setTimestamp(new Date(System.currentTimeMillis()));
            }
            break;
        }
        return wsapisynchistory;
    }

    public List<WsapiSynchistory> getSynchistory() {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        Cursor cursor = dbaccess.readData(WsapiSynchistory.TABLE_WSAPI_SYNCHISTORY
                , WsapiSynchistory.COLUMN_ALL
                , WsapiSynchistory.COLUMN_TABLENAME
        );
        List<WsapiSynchistory> wsapisynchistorys = new ArrayList<>();
        while (cursor.moveToNext()) {
            WsapiSynchistory wsapisynchistory = new WsapiSynchistory();
            wsapisynchistory.setDevice_id(cursor.getString(cursor.getColumnIndex(wsapisynchistory.COLUMN_DEVICE_ID)));
            wsapisynchistory.setTablename(cursor.getString(cursor.getColumnIndex(wsapisynchistory.COLUMN_TABLENAME)));
            try {
                wsapisynchistory.setTimestamp(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(wsapisynchistory.COLUMN_TIMESTAMP))));
            } catch (ParseException e) {
                wsapisynchistory.setTimestamp(new Date(System.currentTimeMillis()));
            }
            wsapisynchistorys.add(wsapisynchistory);
        }
        return wsapisynchistorys;
    }

    public boolean insertSyncHistory(WsapiSynchistory wsapisynchistory) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        return dbaccess.insertWsapiSynchistory(wsapisynchistory) > 0;
    }

    public boolean deleteSyncHistory(WsapiSynchistory wsapisynchistory) {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
        return dbaccess.deleteWsapiSynchistory(wsapisynchistory) > 0;
    }

    public AdministrationSettings getAdministrationSettings() {
        dbaccess = DbAccess.getInstance();
        if (dbaccess != null) {
            if (!dbaccess.isOpen())
                dbaccess.open();
        }
//        AdministrationSettings administrationSettings = new AdministrationSettings();
        AdministrationSettings administrationSetting = null;
        Cursor cursor = dbaccess.readData(AdministrationSettings.TABLE_ADMINISTRATION_SETTINGS
                , AdministrationSettings.COLUMN_ALL
                , null, null, null, null, null
//                , new String[]{"1"}, null, null, null
        );

        while (cursor.moveToNext()) {
            administrationSetting = new AdministrationSettings();
            administrationSetting.setId(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_ID)));
            administrationSetting.setH1(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H1)));
            administrationSetting.setH2(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H2)));
            administrationSetting.setH3(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H3)));
            administrationSetting.setH4(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_H4)));
            administrationSetting.setT1(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T1)));
            administrationSetting.setT2(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T2)));
            administrationSetting.setT3(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T3)));
            administrationSetting.setT4(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_T4)));
            administrationSetting.setDateformat(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DATEFORMAT)));
            administrationSetting.setTimeformat(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_TIMEFORMAT)));
            administrationSetting.setDatetimeformat(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DATETIMEFORMAT)));
            try {
                administrationSetting.setSystem_date(Utility.dateFormat.parse(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_SYSTTEM_DATE))));
            } catch (ParseException e) {
                administrationSetting.setSystem_date(new Date(System.currentTimeMillis()));
            }
            administrationSetting.setActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_ACTIVE))));
            administrationSetting.setLocation_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_LOCATION_ID)));
            administrationSetting.setSolution_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_SOLUTION_ID)));
            administrationSetting.setStaff_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_STAFF_ID)));
            administrationSetting.setWeighscales_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_WEIGHSCALES_ID)));
            administrationSetting.setDashboarditemlwith(Integer.parseInt(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DASHBOARDITEMLWITH))));
            administrationSetting.setDashboarditempwith(Integer.parseInt(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DASHBOARDITEMPWITH))));
            administrationSetting.setDashboardicon(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DASHBOARDICON)));
            administrationSetting.setDevicetype_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DEVICETYPE_ID)));
            administrationSetting.setDevice_id(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_DEVICE_ID)));
            administrationSetting.setRfidreader_name(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_RFIDREADER_NAME)));
            administrationSetting.setRfidreader_mac(cursor.getString(cursor.getColumnIndex(administrationSetting.COLUMN_RFIDREADER_MAC)));
            break;

//            administrationSettings.add(administrationSetting);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return administrationSetting;
    }

}
