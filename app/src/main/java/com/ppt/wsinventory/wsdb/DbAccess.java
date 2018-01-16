package com.ppt.wsinventory.wsdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.AdministrationRole;
import com.ppt.wsinventory.model.AdministrationSettings;
import com.ppt.wsinventory.model.AdministrationSolutions;
import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.model.GoodsInventory;
import com.ppt.wsinventory.model.InventoryBIN;
import com.ppt.wsinventory.model.InventoryGold;
import com.ppt.wsinventory.model.InventoryGoldUOM;
import com.ppt.wsinventory.model.InventoryGoodInventory;
import com.ppt.wsinventory.model.InventoryPallet;
import com.ppt.wsinventory.model.InventoryProductGroup;
import com.ppt.wsinventory.model.InventoryUOM;
import com.ppt.wsinventory.model.Inventory_products;
import com.ppt.wsinventory.model.Inventory_productserial;
import com.ppt.wsinventory.model.Inventory_serialgolds;
import com.ppt.wsinventory.model.Item;
import com.ppt.wsinventory.model.ManufacturingSmith;
import com.ppt.wsinventory.model.Manufacturing_Smith_Jobgold;
import com.ppt.wsinventory.model.Manufacturing_jobstatus;
import com.ppt.wsinventory.model.Manufacturing_smith_joborder;
import com.ppt.wsinventory.model.Manufacturing_smith_jobproduct;
import com.ppt.wsinventory.model.Manufacturing_smith_jobtype;
import com.ppt.wsinventory.model.Manufacturing_smithmembers;
import com.ppt.wsinventory.util.Utility;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 28/12/2017.
 */

public class DbAccess {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;
    private File dbpath;
    String dashboard;

    public File getDbFile() {
        return dbpath;
    }

    public File getDbbackupDirectory() {
        return new File(Environment.getExternalStorageDirectory(), "/database/");
//        return new File("/storage/sdcard1/","/database/" );
    }

    public String getDbName() {
        return dbhelper.getDatabaseName();
    }

    private static final String TAG = "Truckkit-DbAccess";
    private static DbAccess m_dbaccess;

    public DbAccess(Context context) {
        dbhelper = new DbHelper(context);
        dbpath = context.getDatabasePath(dbhelper.getDatabaseName());
        Log.i(TAG, "DbAccess: ");
        m_dbaccess = this;
    }

    public static DbAccess getInstance() {

        return m_dbaccess;
    }

    public boolean isOpen() {
        return database.isOpen();
    }

    public void open() {
        Log.i(TAG, "open: ");
        database = dbhelper.getWritableDatabase();
    }

    public void execSQL(String sql) {
        database.execSQL(sql);
    }

    public void execSQL(String sql, Object[] bindArgs) {
        database.execSQL(sql, bindArgs);
    }

    public boolean updateData(String table, ContentValues values, String whereClause, String[] whereArgs) {
        int result = database.update(table, values, whereClause, whereArgs);
        return (result >= 1);
    }

//    public boolean updateLatLng(Double lat ,Double lng,String CName){
//        SQLiteDatabase db = dbhelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("Latitude", lat);
//        contentValues.put("Longitude", lng);
//        db.update("Customers", contentValues, "CustName = ?",new String[] { CName });
//        return true;
//    }

    public boolean deleteData(String table) {
        Log.i(TAG, "deleteData: " + table);
        return deleteData(table, null, null);
    }

    public boolean deleteData(String table, String whereClause) {
        return deleteData(table, whereClause, null);
    }


    public boolean deleteData(String table, String whereClause, String[] whereArgs) {
        int result = database.delete(table, whereClause, whereArgs);
        return (result == 1);
    }

    public boolean checkData(String sql, String[] selectionArgs) {
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Cursor readData(String tableName, String[] columnAll, String[] strings, Object o1, Object o, String sql) {
        return readData(sql, null);
    }

    public Cursor readData(String sql, String[] selectionArgs) {
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        return cursor;
    }

    public Cursor readData(String table, String[] columns
            , String selection
            , String[] selectionArgs
            , String groupBy
            , String having
            , String orderBy) {
        Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        return cursor;
    }

    public Cursor readData(String table
            , String[] columns
            , String orderBy) {
        Cursor cursor = database.query(table, columns, null, null, null, null, orderBy);
        return cursor;
    }

    public void close() {
        Log.i(TAG, "close: ");
        if (database != null && database.isOpen()) {
            database.close();
        }
//        dbhelper.close();
    }

//    public List<Item> getAllConfirmedQueue() {
//        List<Item> queues = new ArrayList<>();
//        Cursor cursor = readData(QueueTable.TABLE_NAME
//                , QueueTable.COLUMN_ALL
//                , QueueTable.COLUMN_IS_SUBMIT + " = ?"
//                , new String[]{"1"}, null, null, null
//        );


    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        Cursor cursor = readData(Item.TABLE_NAME
                , Item.COLUMN_ALL
                , null, null, null, null, null
//                , new String[]{"1"}, null, null, null
        );

        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setItemName(cursor.getString(cursor.getColumnIndex(item.COLUMN_ITEMNAME)));
            item.setItemType(cursor.getString(cursor.getColumnIndex(item.COLUMN_ITEMTYPE)));

            items.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return items;


    }

    public List<AdministrationWsdashboard> getAllDashboardItems() {
        List<AdministrationWsdashboard> dashboarditems = new ArrayList<>();
        Cursor cursor = readData(AdministrationWsdashboard.TABLE_ADMINISTRATION_WSDASHBOARD
                , AdministrationWsdashboard.COLUMN_ALL
                , null, null, null, null, null
//                , new String[]{"1"}, null, null, null
        );

        while (cursor.moveToNext()) {
            AdministrationWsdashboard item = new AdministrationWsdashboard();
            item.setTitle(cursor.getString(cursor.getColumnIndex(item.COLUMN_TITLE)));
            item.setActionname(cursor.getString(cursor.getColumnIndex(item.COLUMN_ACTIONNAME)));
            item.setGroupname(cursor.getString(cursor.getColumnIndex(item.COLUMN_GROUPNAME)));
            item.setImage(cursor.getString(cursor.getColumnIndex(item.COLUMN_IMAGE)));
            item.setTimestamp(cursor.getString(cursor.getColumnIndex(item.COLUMN_TIMESTAMP)));
            item.setIs_delete(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(item.COLUMN_DELETE))));
            item.setDisplayno(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_DISPLAYNO))));
            item.setScreen_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(item.COLUMN_SCREEN_ID))));

            dashboarditems.add(item);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return dashboarditems;


    }

    public AdministrationSettings getAdministrationSettings() {
//        AdministrationSettings administrationSettings = new AdministrationSettings();
        AdministrationSettings administrationSetting = new AdministrationSettings();
        Cursor cursor = readData(AdministrationSettings.TABLE_ADMINISTRATION_SETTINGS
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

//            administrationSettings.add(administrationSetting);
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return administrationSetting;
    }

    public Item insertItems(Item item) {

        ContentValues values = new ContentValues();
        values.put(Item.COLUMN_ITEMNAME, item.getItemName());
        values.put(Item.COLUMN_ITEMTYPE, item.getItemType());

        long resultid = database.insert(Item.TABLE_NAME, null, values);
        item.setId(resultid);

        return item;
    }


    public AdministrationSettings insertSettings(AdministrationSettings administrationSettings) {
        ContentValues values = new ContentValues();
        values.put(AdministrationSettings.COLUMN_ID, administrationSettings.getId());
        values.put(AdministrationSettings.COLUMN_H1, administrationSettings.getH1());
        values.put(AdministrationSettings.COLUMN_H2, administrationSettings.getH2());
        values.put(AdministrationSettings.COLUMN_H3, administrationSettings.getH3());
        values.put(AdministrationSettings.COLUMN_H4, administrationSettings.getH4());
        values.put(AdministrationSettings.COLUMN_T1, administrationSettings.getT1());
        values.put(AdministrationSettings.COLUMN_T2, administrationSettings.getT2());
        values.put(AdministrationSettings.COLUMN_T3, administrationSettings.getT3());
        values.put(AdministrationSettings.COLUMN_T4, administrationSettings.getT4());
        values.put(AdministrationSettings.COLUMN_DATEFORMAT, administrationSettings.getDateformat());
        values.put(AdministrationSettings.COLUMN_TIMEFORMAT, administrationSettings.getTimeformat());
        values.put(AdministrationSettings.COLUMN_DATETIMEFORMAT, administrationSettings.getDatetimeformat());
        values.put(AdministrationSettings.COLUMN_SYSTTEM_DATE, Utility.dateFormat.format(administrationSettings.getSystem_date()));
        values.put(AdministrationSettings.COLUMN_ACTIVE, administrationSettings.getActive());
        values.put(AdministrationSettings.COLUMN_LOCATION_ID, administrationSettings.getLocation_id());
        values.put(AdministrationSettings.COLUMN_SOLUTION_ID, administrationSettings.getSolution_id());
        values.put(AdministrationSettings.COLUMN_STAFF_ID, administrationSettings.getStaff_id());
        values.put(AdministrationSettings.COLUMN_WEIGHSCALES_ID, administrationSettings.getWeighscales_id());
        values.put(AdministrationSettings.COLUMN_DASHBOARDITEMLWITH, administrationSettings.getDashboarditemlwith());
        values.put(AdministrationSettings.COLUMN_DASHBOARDITEMPWITH, administrationSettings.getDashboarditempwith());
        values.put(AdministrationSettings.COLUMN_DASHBOARDICON, administrationSettings.getDashboardicon());
        values.put(AdministrationSettings.COLUMN_DEVICE_ID, administrationSettings.getDevice_id());
        values.put(AdministrationSettings.COLUMN_DEVICETYPE_ID, administrationSettings.getDevicetype_id());
        long resultid = database.insert(administrationSettings.TABLE_ADMINISTRATION_SETTINGS, null, values);
//        administrationSettings.setId(resultid);
        return administrationSettings;
    }

    public AdministrationStaff insertAdministrationStaff(AdministrationStaff administrationStaff) {
        ContentValues values = new ContentValues();
        values.put(AdministrationStaff.COLUMN_ID, administrationStaff.getId());
        values.put(AdministrationStaff.COLUMN_STAFF_ID, administrationStaff.getStaff_id());
        values.put(AdministrationStaff.COLUMN_STAFF_NAME, administrationStaff.getStaff_name());
        values.put(AdministrationStaff.COLUMN_FATHER_NAME, administrationStaff.getFather_name());
        values.put(AdministrationStaff.COLUMN_NICK_NAME, administrationStaff.getNick_name());
        values.put(AdministrationStaff.COLUMN_NRC_NO, administrationStaff.getNrc_no());
        values.put(AdministrationStaff.COLUMN_ADDRESS, administrationStaff.getAddress());
        values.put(AdministrationStaff.COLUMN_PHONE_NO, administrationStaff.getPhone_no());
        values.put(AdministrationStaff.COLUMN_HOME_PHONE_NO, administrationStaff.getHome_phone_no());
        values.put(AdministrationStaff.COLUMN_STAFF_PHOTO, administrationStaff.getStaff_photo());
        values.put(AdministrationStaff.COLUMN_DATE_JOINED, Utility.dateFormat.format(administrationStaff.getDate_joined()));
        values.put(AdministrationStaff.COLUMN_DATE_LEFT, Utility.dateFormat.format(administrationStaff.getDate_left()));
        values.put(AdministrationStaff.COLUMN_USER_ID, administrationStaff.getUser_id());
        values.put(AdministrationStaff.COLUMN_ACTIVE, administrationStaff.getActive());
        values.put(AdministrationStaff.COLUMN_ROLE, administrationStaff.getRole());

        long resultid = database.insert(administrationStaff.TABLE_ADMINISTRATION_STAFF, null, values);
//        administrationStaff.setId(resultid);
        return administrationStaff;
    }

    public AdministrationLocations insertAdministrationLocation(AdministrationLocations administrationLocations) {
        ContentValues values = new ContentValues();
        values.put(AdministrationLocations.COLUMN_ID, administrationLocations.getId());
        values.put(AdministrationLocations.COLUMN_LOC_NAME, administrationLocations.getLoc_name());
        values.put(AdministrationLocations.COLUMN_LOC_ADDR, administrationLocations.getLoc_addr());
        values.put(AdministrationLocations.COLUMN_LATITUDE, administrationLocations.getLatitude());
        values.put(AdministrationLocations.COLUMN_LONGITUDE, administrationLocations.getLongitude());
        values.put(AdministrationLocations.COLUMN_RECEIVING_BIN, administrationLocations.getReceiving_bin());
        values.put(AdministrationLocations.COLUMN_ACTIVE, administrationLocations.isActive());
        long resultid = database.insert(administrationLocations.TABLE_ADMINISTRATION_LOCATIONS, null, values);
//        administrationStaff.setId(resultid);
        return administrationLocations;
    }

    public AdministrationRole insertAdministrationRole(AdministrationRole administrationRole) {
        ContentValues values = new ContentValues();
        values.put(AdministrationRole.COLUMN_ID, administrationRole.getId());
        values.put(AdministrationRole.COLUMN_ROLE_NAME, administrationRole.getRole_name());
        values.put(AdministrationRole.COLUMN_ACTIVE, administrationRole.isActive());

        long resultid = database.insert(administrationRole.TABLE_ADMINISTRATION_ROLE, null, values);
//        administrationStaff.setId(resultid);
        return administrationRole;
    }

    public InventoryGold insertInventoryGold(InventoryGold inventoryGold) {
        ContentValues values = new ContentValues();

        values.put(InventoryGold.COLUMN_ID, inventoryGold.getId());
        values.put(InventoryGold.COLUMN_NAME, inventoryGold.getName());
        values.put(InventoryGold.COLUMN_GOLDTYPE, inventoryGold.getGoldtype());
        values.put(InventoryGold.COLUMN_SALEPRICE, inventoryGold.getSaleprice());
        values.put(InventoryGold.COLUMN_PURCHASEPRICE, inventoryGold.getPurchaseprice());
        values.put(InventoryGold.COLUMN_COST, inventoryGold.getCost());
        values.put(InventoryGold.COLUMN_TAX1, inventoryGold.getTax1());
        values.put(InventoryGold.COLUMN_TAX2, inventoryGold.getTax2());
        values.put(InventoryGold.COLUMN_ACTIVE, inventoryGold.getActive());
        values.put(InventoryGold.COLUMN_CODE, inventoryGold.getCode());

        long resultid = database.insert(inventoryGold.TABLE_INVENTORY_GOLD, null, values);
        inventoryGold.setId(resultid);
        return inventoryGold;
    }

    public InventoryProductGroup insertInventoryProductGroup(InventoryProductGroup inventoryProductGroup) {
        ContentValues values = new ContentValues();

        values.put(InventoryProductGroup.COLUMN_ID, inventoryProductGroup.getId());
        values.put(InventoryProductGroup.COLUMN_NAME, inventoryProductGroup.getName());
        values.put(InventoryProductGroup.COLUMN_ACTIVE, inventoryProductGroup.isActive());

        long resultid = database.insert(inventoryProductGroup.TABLE_PRODUCTGROUP, null, values);
        inventoryProductGroup.setId(resultid);
        return inventoryProductGroup;
    }

    public Inventory_products insertInventory_products(Inventory_products inventory_products) {
        ContentValues values = new ContentValues();

        values.put(Inventory_products.COLUMN_ID, inventory_products.getId());
        values.put(Inventory_products.COLUMN_NAME, inventory_products.getName());
        values.put(Inventory_products.COLUMN_DESIGNNAME, inventory_products.getDesignname());
        values.put(Inventory_products.COLUMN_PHOTO, inventory_products.getPhoto());
        values.put(Inventory_products.COLUMN_BARCODE, inventory_products.getBarcode());
        values.put(Inventory_products.COLUMN_TAG, inventory_products.getTag());
        values.put(Inventory_products.COLUMN_BASEUOM, inventory_products.getBaseuom());
        values.put(Inventory_products.COLUMN_MINQTY, inventory_products.getMinqty());
        values.put(Inventory_products.COLUMN_MAXQTY, inventory_products.getMaxqty());
        values.put(Inventory_products.COLUMN_ACTIVE, inventory_products.isActive());
        values.put(Inventory_products.COLUMN_IS_DELETE, inventory_products.isIs_delete());

        long resultid = database.insert(inventory_products.TABLE_INVENTORY_PRODUCTS, null, values);
//        inventory_products.setId(resultid);
        return inventory_products;
    }

    public AdministrationSolutions insertAdministrationSolutions(AdministrationSolutions administrationSolutions) {
        ContentValues values = new ContentValues();
        values.put(AdministrationSolutions.COLUMN_SOLUTION_NAME, administrationSolutions.getSolution_name());
        values.put(AdministrationSolutions.COLUMN_active, administrationSolutions.getActive());

        long resultid = database.insert(administrationSolutions.TABLE_ADMINISTRATION_SOLUTIONS, null, values);
        administrationSolutions.setId(resultid);
        return administrationSolutions;
    }

    public AdministrationWsdashboard insertAdministrationWsdashboard(AdministrationWsdashboard administrationWsdashboard) {
        ContentValues values = new ContentValues();
        values.put(AdministrationWsdashboard.COLUMN_ID, administrationWsdashboard.getId());
        values.put(AdministrationWsdashboard.COLUMN_TITLE, administrationWsdashboard.getTitle());
        values.put(AdministrationWsdashboard.COLUMN_ACTIONNAME, administrationWsdashboard.getActionname());
        values.put(AdministrationWsdashboard.COLUMN_GROUPNAME, administrationWsdashboard.getGroupname());
        values.put(AdministrationWsdashboard.COLUMN_IMAGE, administrationWsdashboard.getImage());
        values.put(AdministrationWsdashboard.COLUMN_TIMESTAMP, administrationWsdashboard.getTimestamp());
        values.put(AdministrationWsdashboard.COLUMN_DELETE, administrationWsdashboard.getIs_delete());
        values.put(AdministrationWsdashboard.COLUMN_DISPLAYNO, administrationWsdashboard.getDisplayno());
        values.put(AdministrationWsdashboard.COLUMN_SCREEN_ID, administrationWsdashboard.getScreen_id());

        long resultid = database.insert(administrationWsdashboard.TABLE_ADMINISTRATION_WSDASHBOARD, null, values);
//        administrationSolutions.setId(resultid);
        return administrationWsdashboard;
    }

    public InventoryUOM insertInventoryUOM(InventoryUOM inventoryUOM) {
        ContentValues values = new ContentValues();
        values.put(InventoryUOM.COLUMN_UOM, inventoryUOM.getUom());
        values.put(InventoryUOM.COLUMN_BASEQUANLITY, inventoryUOM.getBaseqty());
        values.put(InventoryUOM.COLUMN_PRODUCT_ID, inventoryUOM.getProduct_id());
        values.put(InventoryUOM.COLUMN_ACTIVE, inventoryUOM.isActive());

        long resultid = database.insert(inventoryUOM.TABLE_INVENTROY_UOM, null, values);
//        administrationStaff.setId(resultid);
        return inventoryUOM;
    }

    public InventoryGoldUOM insertInventoryGoldUOM(InventoryGoldUOM inventoryGoldUOM) {
        ContentValues values = new ContentValues();
        values.put(InventoryGoldUOM.COLUMN_UOM, inventoryGoldUOM.getUom());
        values.put(InventoryGoldUOM.COLUMN_BASEQUANLITY, inventoryGoldUOM.getBaseqty());
        values.put(InventoryGoldUOM.COLUMN_GOLD_ID, inventoryGoldUOM.getGold_id());
        values.put(InventoryGoldUOM.COLUMN_ACTIVE, inventoryGoldUOM.isActive());

        long resultid = database.insert(inventoryGoldUOM.TABLE_INVENTROY_GOLDUOM, null, values);
//        administrationStaff.setId(resultid);
        return inventoryGoldUOM;
    }

    public long insertInventoryBIN(InventoryBIN inventoryBIN) {
        ContentValues values = new ContentValues();
        values.put(InventoryBIN.COLUMN_ID, inventoryBIN.getId());
        values.put(InventoryBIN.COLUMN_BIN_NAME, inventoryBIN.getBin_name());
        values.put(InventoryBIN.COLUMN_BIN_DESCRIPTION, inventoryBIN.getBin_description());
        values.put(InventoryBIN.COLUMN_BIN_TYPE, inventoryBIN.getBin_type());
        values.put(InventoryBIN.COLUMN_BARCODE, inventoryBIN.getBarcode());
        values.put(InventoryBIN.COLUMN_TAG, inventoryBIN.getTag());
        values.put(InventoryBIN.COLUMN_LOCATION_ID, inventoryBIN.getLocation_id());
        values.put(InventoryBIN.COLUMN_ACTIVE, inventoryBIN.isActive());
        values.put(InventoryBIN.COLUMN_TS, Utility.dateFormat.format(inventoryBIN.getTs()));

        long resultid = database.insert(inventoryBIN.TABLE_INVENTORY_BIN, null, values);
//        administrationStaff.setId(resultid);
        return resultid;
    }

    public long insertGoodsInventory(InventoryGoodInventory goodsinventory) {
        ContentValues values = new ContentValues();
        values.put(InventoryGoodInventory.COLUMN_ID, goodsinventory.getId());
        values.put(InventoryGoodInventory.COLUMN_QTY, goodsinventory.getQty());
        values.put(InventoryGoodInventory.COLUMN_WEIGHT, goodsinventory.getWeight());
        values.put(InventoryGoodInventory.COLUMN_K, goodsinventory.getK());
        values.put(InventoryGoodInventory.COLUMN_P, goodsinventory.getP());
        values.put(InventoryGoodInventory.COLUMN_Y, goodsinventory.getY());
        values.put(InventoryGoodInventory.COLUMN_IS_DELETE, goodsinventory.isIs_delete());
        values.put(InventoryGoodInventory.COLUMN_TS, Utility.dateFormat.format(goodsinventory.getTs()));
        values.put(InventoryGoodInventory.COLUMN_LOCATION_ID, goodsinventory.getLocation_id());
        values.put(InventoryGoodInventory.COLUMN_PRODUCT_ID,goodsinventory.getProduct_id());
        values.put(InventoryGoodInventory.COLUMN_UOM_ID,goodsinventory.getUom_id());

        long resultid = database.insert(goodsinventory.TABLE_INVENTORY_GOODSINVENTORY, null, values);
//        goodsinventory.setId(resultid);
        return resultid;
    }

    public long insertInventoryPallet(InventoryPallet inventoryPallet) {
        ContentValues values = new ContentValues();
        values.put(InventoryPallet.COLUMN_ID, inventoryPallet.getId());
        values.put(InventoryPallet.COLUMN_PALLET_NAME, inventoryPallet.getPallet_name());
        values.put(InventoryPallet.COLUMN_PALLET_DESCRIPTION, inventoryPallet.getPallet_description());
        values.put(InventoryPallet.COLUMN_PALLET_TYPE, inventoryPallet.getPallet_type());
        values.put(InventoryPallet.COLUMN_BARCODE, inventoryPallet.getBarcode());
        values.put(InventoryPallet.COLUMN_TAG, inventoryPallet.getTag());
        values.put(InventoryPallet.COLUMN_LOCATION_ID, inventoryPallet.getLocation_id());
        values.put(InventoryPallet.COLUMN_WEIGHT, inventoryPallet.getWeight());
        values.put(InventoryPallet.COLUMN_IS_USED, inventoryPallet.isIs_used());
        values.put(InventoryPallet.COLUMN_ACTIVE, inventoryPallet.isActive());
        values.put(InventoryPallet.COLUMN_TS, Utility.dateFormat.format(inventoryPallet.getTs()));

        long resultid = database.insert(inventoryPallet.TABLE_INVENTORY_PALLET, null, values);
//        administrationStaff.setId(resultid);
        return resultid;
    }

    public long insertInventory_productserial(Inventory_productserial inventory_productserial) {
        ContentValues values = new ContentValues();
        values.put(Inventory_productserial.COLUMN_SERIAL_NO, inventory_productserial.getSerial_no());
        values.put(Inventory_productserial.COLUMN_NAME, inventory_productserial.getName());
        values.put(Inventory_productserial.COLUMN_PLENGTH, inventory_productserial.getPlength());
        values.put(Inventory_productserial.COLUMN_PHOTO, inventory_productserial.getPhoto());
        values.put(Inventory_productserial.COLUMN_BARCODE, inventory_productserial.getBarcode());
        values.put(Inventory_productserial.COLUMN_TAG, inventory_productserial.getTag());
        values.put(Inventory_productserial.COLUMN_WEIGHT, inventory_productserial.getWeight());
        values.put(Inventory_productserial.COLUMN_K, inventory_productserial.getK());
        values.put(Inventory_productserial.COLUMN_P, inventory_productserial.getP());
        values.put(Inventory_productserial.COLUMN_Y, inventory_productserial.getY());
        values.put(Inventory_productserial.COLUMN_REDUCE_WEIGHT, inventory_productserial.getReduce_weight());
        values.put(Inventory_productserial.COLUMN_REDUCE_K, inventory_productserial.getReduce_k());
        values.put(Inventory_productserial.COLUMN_REDUCE_P, inventory_productserial.getReduce_p());
        values.put(Inventory_productserial.COLUMN_REDUCE_Y, inventory_productserial.getReduce_y());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT, inventory_productserial.getJewel_weight());
        values.put(Inventory_productserial.COLUMN_JEWEL_K, inventory_productserial.getJewel_k());
        values.put(Inventory_productserial.COLUMN_JEWEL_P, inventory_productserial.getJewel_p());
        values.put(Inventory_productserial.COLUMN_JEWEL_Y, inventory_productserial.getJewel_y());
        values.put(Inventory_productserial.COLUMN_JEWEL_FEE, inventory_productserial.getJewel_fee());
        values.put(Inventory_productserial.COLUMN_PRODUCTION_FEE, inventory_productserial.getProduction_fee());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME1, inventory_productserial.getJewel_name1());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME2, inventory_productserial.getJewel_name2());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME3, inventory_productserial.getJewel_name3());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME4, inventory_productserial.getJewel_name4());
        values.put(Inventory_productserial.COLUMN_JEWEL_NAME5, inventory_productserial.getJewel_name5());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT1, inventory_productserial.getJewel_weight1());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT2, inventory_productserial.getJewel_weight2());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT3, inventory_productserial.getJewel_weight3());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT4, inventory_productserial.getJewel_weight4());
        values.put(Inventory_productserial.COLUMN_JEWEL_WEIGHT5, inventory_productserial.getJewel_weight5());
        values.put(Inventory_productserial.COLUMN_REMARKS, inventory_productserial.getRemarks());
        values.put(Inventory_productserial.COLUMN_IS_DELETE, inventory_productserial.isIs_delete());
        values.put(Inventory_productserial.COLUMN_TS, Utility.dateFormat.format(inventory_productserial.getTs()));
        values.put(Inventory_productserial.COLUMN_BIN_ID, inventory_productserial.getBin_id());
        values.put(Inventory_productserial.COLUMN_GOLD_ID, inventory_productserial.getGold_id());
        values.put(Inventory_productserial.COLUMN_LOCATION_ID, inventory_productserial.getLocation_id());
        values.put(Inventory_productserial.COLUMN_PALLET_ID, inventory_productserial.getPallet_id());
        values.put(Inventory_productserial.COLUMN_PRODUCT_ID, inventory_productserial.getProduct_id());
        values.put(Inventory_productserial.COLUMN_SMITH_ID, inventory_productserial.getSmith_id());
        values.put(Inventory_productserial.COLUMN_STAFF_ID, inventory_productserial.getStaff_id());
        values.put(Inventory_productserial.COLUMN_UOM_ID, inventory_productserial.getUom_id());
        values.put(Inventory_productserial.COLUMN_DELIVERED, inventory_productserial.isDelivered());
        values.put(Inventory_productserial.COLUMN_GOODSID, inventory_productserial.getGoodsid());
        long resultid = database.insert(inventory_productserial.TABLE_INVENTORY_PRODUCTSERIAL, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertInventory_serialgolds(Inventory_serialgolds inventory_serialgolds) {
        ContentValues values = new ContentValues();
        values.put(Inventory_serialgolds.COLUMN_SERIAL_ID, inventory_serialgolds.getSerial_id());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_WEIGHT, inventory_serialgolds.getReduce_weight());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_K, inventory_serialgolds.getReduce_k());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_P, inventory_serialgolds.getReduce_p());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_Y, inventory_serialgolds.getReduce_y());
        values.put(Inventory_serialgolds.COLUMN_PRODUCTION_FEE, inventory_serialgolds.getProduction_fee());
        values.put(Inventory_serialgolds.COLUMN_COST_REDUCE_WEIGHT, inventory_serialgolds.getCost_reduce_weight());
        values.put(Inventory_serialgolds.COLUMN_COST_REDUCE_K, inventory_serialgolds.getCost_reduce_k());
        values.put(Inventory_serialgolds.COLUMN_REDUCE_P, inventory_serialgolds.getCost_reduce_p());
        values.put(Inventory_serialgolds.COLUMN_COST_REDUCE_Y, inventory_serialgolds.getCost_reduce_y());
        values.put(Inventory_serialgolds.COLUMN_COST_PRODUCTIONFEE, inventory_serialgolds.getCost_productionfee());
        values.put(Inventory_serialgolds.COLUMN_IS_DELETE, inventory_serialgolds.isIs_delete());
        values.put(Inventory_serialgolds.COLUMN_GOLD_ID, inventory_serialgolds.getGold_id());
        values.put(Inventory_serialgolds.COLUMN_TS, Utility.dateFormat.format(inventory_serialgolds.getTs()));

        long resultid = database.insert(inventory_serialgolds.TABLE_INVENTORY_SERIALGOLDS, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturingSmith(ManufacturingSmith manufacturingsmith) {
        ContentValues values = new ContentValues();
        values.put(ManufacturingSmith.COLUMN_ID, manufacturingsmith.getId());
        values.put(ManufacturingSmith.COLUMN_NAME, manufacturingsmith.getName());
        values.put(ManufacturingSmith.COLUMN_NICKNAME, manufacturingsmith.getNickname());
        values.put(ManufacturingSmith.COLUMN_NRC, manufacturingsmith.getNrc());
        values.put(ManufacturingSmith.COLUMN_ADDRESS, manufacturingsmith.getAddress());
        values.put(ManufacturingSmith.COLUMN_PHONE, manufacturingsmith.getPhone());
        values.put(ManufacturingSmith.COLUMN_REFNAME, manufacturingsmith.getRefname());
        values.put(ManufacturingSmith.COLUMN_MIXJOB, manufacturingsmith.getMixjob());
        values.put(ManufacturingSmith.COLUMN_K, manufacturingsmith.getK());
        values.put(ManufacturingSmith.COLUMN_P, manufacturingsmith.getP());
        values.put(ManufacturingSmith.COLUMN_Y, manufacturingsmith.getY());
        values.put(ManufacturingSmith.COLUMN_G, manufacturingsmith.getG());
        values.put(ManufacturingSmith.COLUMN_COST, manufacturingsmith.getCost());
        values.put(ManufacturingSmith.COLUMN_DATE_JOINED, Utility.dateFormat.format(manufacturingsmith.getDate_joined()));
        values.put(ManufacturingSmith.COLUMN_DATE_END, Utility.dateFormat.format(manufacturingsmith.getDate_end()));
        values.put(ManufacturingSmith.COLUMN_IS_SMITH2, manufacturingsmith.isIs_smith2());
        values.put(ManufacturingSmith.COLUMN_ACTIVE, manufacturingsmith.isActive());
        values.put(ManufacturingSmith.COLUMN_GOLDSAVING, manufacturingsmith.getGoldsaving());
        values.put(ManufacturingSmith.COLUMN_INHANDJOB, manufacturingsmith.getInhandjob());
        values.put(ManufacturingSmith.COLUMN_PHOTO, manufacturingsmith.getPhoto());
        long resultid = database.insert(manufacturingsmith.TABLE_MANUFACTURING_SMITH, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_smithmembers(Manufacturing_smithmembers manufacturing_smithmembers) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_smithmembers.COLUMN_ID, manufacturing_smithmembers.getId());
        values.put(Manufacturing_smithmembers.COLUMN_NAME, manufacturing_smithmembers.getName());
        values.put(Manufacturing_smithmembers.COLUMN_NICKNAME, manufacturing_smithmembers.getNickname());
        values.put(Manufacturing_smithmembers.COLUMN_NRC, manufacturing_smithmembers.getNrc());
        values.put(Manufacturing_smithmembers.COLUMN_ADDRESS, manufacturing_smithmembers.getAddress());
        values.put(Manufacturing_smithmembers.COLUMN_PHONE, manufacturing_smithmembers.getPhone());
        values.put(Manufacturing_smithmembers.COLUMN_ACTIVE, manufacturing_smithmembers.isActive());
        values.put(Manufacturing_smithmembers.COLUMN_SMITH_ID, manufacturing_smithmembers.getSmith_id());
        values.put(Manufacturing_smithmembers.COLUMN_PHOTO, manufacturing_smithmembers.getPhoto());
        long resultid = database.insert(manufacturing_smithmembers.TABLE_MANUFACTURING_SMITHMEMBERS, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_jobstatus(Manufacturing_jobstatus manufacturing_jobstatus) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_jobstatus.COLUMN_ID, manufacturing_jobstatus.getId());
        values.put(Manufacturing_jobstatus.COLUMN_NAME, manufacturing_jobstatus.getName());
        values.put(Manufacturing_jobstatus.COLUMN_DESCRIPTION, manufacturing_jobstatus.getDescription());
        values.put(Manufacturing_jobstatus.COLUMN_STATUS_TYPE, manufacturing_jobstatus.getStatus_type());
        values.put(Manufacturing_jobstatus.COLUMN_ACTIVE, manufacturing_jobstatus.isActive());
        long resultid = database.insert(manufacturing_jobstatus.TABLE_MANUFACTURING_JOBSTATUS, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_Smith_Jobgold(Manufacturing_Smith_Jobgold manufacturing_smith_jobgold) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_Smith_Jobgold.COLUMN_SMITH_JOBORDER_ID, manufacturing_smith_jobgold.getSmith_joborder_id());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_WEIGHT, manufacturing_smith_jobgold.getWeight());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_QTY, manufacturing_smith_jobgold.getQty());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_GOLDSAVING, manufacturing_smith_jobgold.getGoldsaving());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_REMARKS, manufacturing_smith_jobgold.getRemarks());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_ROW_NO, manufacturing_smith_jobgold.getRow_no());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_IS_DELETE, manufacturing_smith_jobgold.getIs_delete());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_GOLD_ID, manufacturing_smith_jobgold.getGold_id());
        values.put(Manufacturing_Smith_Jobgold.COLUMN_UOM_ID, manufacturing_smith_jobgold.getUom_id());
        long resultid = database.insert(manufacturing_smith_jobgold.TABLE_MANUFACTURING_SMITH_JOBGOLD, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_smith_joborder(Manufacturing_smith_joborder manufacturing_smith_joborder) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_smith_joborder.COLUMN_ID, manufacturing_smith_joborder.getId());
        values.put(Manufacturing_smith_joborder.COLUMN_JOBORDER_NO, manufacturing_smith_joborder.getJoborder_no());
        values.put(Manufacturing_smith_joborder.COLUMN_JOBORDER_DATE, Utility.dateFormat.format(manufacturing_smith_joborder.getJoborder_date()));
        values.put(Manufacturing_smith_joborder.COLUMN_PREJEWELOUT, manufacturing_smith_joborder.isPrejewelout());
        values.put(Manufacturing_smith_joborder.COLUMN_PREJEWELOUT_DATE, Utility.dateFormat.format(manufacturing_smith_joborder.getPrejewelout_date()));
        values.put(Manufacturing_smith_joborder.COLUMN_DATE_TARGET, Utility.dateFormat.format(manufacturing_smith_joborder.getDate_target()));
        values.put(Manufacturing_smith_joborder.COLUMN_DATE_START, Utility.dateFormat.format(manufacturing_smith_joborder.getDate_start()));
        values.put(Manufacturing_smith_joborder.COLUMN_DATE_END, Utility.dateFormat.format(manufacturing_smith_joborder.getDate_end()));
        values.put(Manufacturing_smith_joborder.COLUMN_REMARKS, manufacturing_smith_joborder.getRemarks());
        values.put(Manufacturing_smith_joborder.COLUMN_ACTIVE, manufacturing_smith_joborder.isActive());
        values.put(Manufacturing_smith_joborder.COLUMN_IS_DELETE, manufacturing_smith_joborder.isIs_delete());
        values.put(Manufacturing_smith_joborder.COLUMN_TS, Utility.dateFormat.format(manufacturing_smith_joborder.getTs()));
        values.put(Manufacturing_smith_joborder.COLUMN_JOBORDER_TYPE_ID, manufacturing_smith_joborder.getJoborder_type_id());
        values.put(Manufacturing_smith_joborder.COLUMN_JOBSTATUS_ID, manufacturing_smith_joborder.getJobstatus_id());
        values.put(Manufacturing_smith_joborder.COLUMN_SMITH_ID, manufacturing_smith_joborder.getSmith_id());
        values.put(Manufacturing_smith_joborder.COLUMN_DENSITY, manufacturing_smith_joborder.getDensity());
        values.put(Manufacturing_smith_joborder.COLUMN_DIFF_K, manufacturing_smith_joborder.getDiff_k());
        values.put(Manufacturing_smith_joborder.COLUMN_DIFF_P, manufacturing_smith_joborder.getDiff_p());
        values.put(Manufacturing_smith_joborder.COLUMN_PRODUCT_WEIGHT, manufacturing_smith_joborder.getDiff_weight());
        values.put(Manufacturing_smith_joborder.COLUMN_DIFF_Y, manufacturing_smith_joborder.getDiff_y());
        values.put(Manufacturing_smith_joborder.COLUMN_PRINT_COUNT, manufacturing_smith_joborder.getPrint_count());
        values.put(Manufacturing_smith_joborder.COLUMN_PRODUCT_WEIGHT, manufacturing_smith_joborder.getProduct_weight());
        values.put(Manufacturing_smith_joborder.COLUMN_REMAIN_GOLD, manufacturing_smith_joborder.getRemain_gold());
        values.put(Manufacturing_smith_joborder.COLUMN_REMAIN_JEWEL, manufacturing_smith_joborder.getRemain_jewel());
        values.put(Manufacturing_smith_joborder.COLUMN_SAVE_COUNT, manufacturing_smith_joborder.getSave_count());
        long resultid = database.insert(manufacturing_smith_joborder.TABLE_MANUFACTURING_SMITH_JOBORDER, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_smith_jobproduct(Manufacturing_smith_jobproduct manufacturing_smith_jobproduct) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_smith_jobproduct.COLUMN_PLENGTH, manufacturing_smith_jobproduct.getPlength());
        values.put(Manufacturing_smith_jobproduct.COLUMN_WEIGHT, manufacturing_smith_jobproduct.getWeight());
        values.put(Manufacturing_smith_jobproduct.COLUMN_K, manufacturing_smith_jobproduct.getK());
        values.put(Manufacturing_smith_jobproduct.COLUMN_P, manufacturing_smith_jobproduct.getP());
        values.put(Manufacturing_smith_jobproduct.COLUMN_Y, manufacturing_smith_jobproduct.getY());
        values.put(Manufacturing_smith_jobproduct.COLUMN_QTY, manufacturing_smith_jobproduct.getQty());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REDUCE_WEIGHT, manufacturing_smith_jobproduct.getReduce_weight());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REDUCE_K, manufacturing_smith_jobproduct.getReduce_k());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REDUCE_P, manufacturing_smith_jobproduct.getReduce_p());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REDUCE_Y, manufacturing_smith_jobproduct.getReduce_y());
        values.put(Manufacturing_smith_jobproduct.COLUMN_TARGET_DATE, Utility.dateFormat.format(manufacturing_smith_jobproduct.getTarget_date()));
        values.put(Manufacturing_smith_jobproduct.COLUMN_START_DATE, Utility.dateFormat.format(manufacturing_smith_jobproduct.getStart_date()));
        values.put(Manufacturing_smith_jobproduct.COLUMN_END_DATE, Utility.dateFormat.format(manufacturing_smith_jobproduct.getEnd_date()));
        values.put(Manufacturing_smith_jobproduct.COLUMN_WAGES, manufacturing_smith_jobproduct.getWages());
        values.put(Manufacturing_smith_jobproduct.COLUMN_REMARKS, manufacturing_smith_jobproduct.getRemarks());
        values.put(Manufacturing_smith_jobproduct.COLUMN_PRODUCTS_ID, manufacturing_smith_jobproduct.getProducts_id());
        values.put(Manufacturing_smith_jobproduct.COLUMN_SMITH_JOBORDER_ID, manufacturing_smith_jobproduct.getSmith_joborder_id());
        values.put(Manufacturing_smith_jobproduct.COLUMN_IS_DELETE, manufacturing_smith_jobproduct.isIs_delete());
        values.put(Manufacturing_smith_jobproduct.COLUMN_ROW_NO, manufacturing_smith_jobproduct.getRow_no());
        values.put(Manufacturing_smith_jobproduct.COLUMN_TO_LOCATION_ID, manufacturing_smith_jobproduct.getTo_location_id());
        values.put(Manufacturing_smith_jobproduct.COLUMN_UOM_ID, manufacturing_smith_jobproduct.getUom_id());
        long resultid = database.insert(manufacturing_smith_jobproduct.TABLE_MANUFACTURING_SMITH_JOBPRODUCT, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public long insertManufacturing_smith_jobtype(Manufacturing_smith_jobtype manufacturing_smith_jobtype) {
        ContentValues values = new ContentValues();
        values.put(Manufacturing_smith_jobtype.COLUMN_ID, manufacturing_smith_jobtype.getId());
        values.put(Manufacturing_smith_jobtype.COLUMN_NAME, manufacturing_smith_jobtype.getName());
        values.put(Manufacturing_smith_jobtype.COLUMN_DESCRIPTION, manufacturing_smith_jobtype.getDescription());
        values.put(Manufacturing_smith_jobtype.COLUMN_JOBTYPE_GROUP, manufacturing_smith_jobtype.getJobtype_group());
        values.put(Manufacturing_smith_jobtype.COLUMN_ACTIVE, manufacturing_smith_jobtype.isActive());
        long resultid = database.insert(manufacturing_smith_jobtype.TABLE_MANUFACTURING_SMITH_JOBTYPE, null, values);
//        Inventory_productserial.set(resultid);
        return resultid;
    }

    public boolean deleteAdministrationWsdashboard(String table, String whereArgs) {
        database.delete(table, "id = ?", new String[]{whereArgs});
        return true;
    }
}
