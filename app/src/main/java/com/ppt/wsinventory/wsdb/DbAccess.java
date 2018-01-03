package com.ppt.wsinventory.wsdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.ppt.wsinventory.model.AdministrationSettings;
import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.model.InventoryGold;
import com.ppt.wsinventory.model.Item;
import com.ppt.wsinventory.util.Utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28/12/2017.
 */

public class DbAccess {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;
    private File dbpath;

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
                ,null,null,null,null,null
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

    public Item insertItems(Item item){

        ContentValues values = new ContentValues();
        values.put(Item.COLUMN_ITEMNAME,item.getItemName());
        values.put(Item.COLUMN_ITEMTYPE,item.getItemType());

        long resultid = database.insert(Item.TABLE_NAME, null, values);
        item.setId(resultid);

        return item;
    }


    public AdministrationSettings insertSettings(AdministrationSettings administrationSettings) {
        ContentValues values = new ContentValues();
        values.put(AdministrationSettings.COLUMN_ID,administrationSettings.getId());
        values.put(AdministrationSettings.COLUMN_H1,administrationSettings.getH1());
        values.put(AdministrationSettings.COLUMN_H2,administrationSettings.getH2());
        values.put(AdministrationSettings.COLUMN_H3,administrationSettings.getH3());
        values.put(AdministrationSettings.COLUMN_H4,administrationSettings.getH4());
        values.put(AdministrationSettings.COLUMN_T1,administrationSettings.getT1());
        values.put(AdministrationSettings.COLUMN_T2,administrationSettings.getT2());
        values.put(AdministrationSettings.COLUMN_T3,administrationSettings.getT3());
        values.put(AdministrationSettings.COLUMN_T4,administrationSettings.getT4());
        values.put(AdministrationSettings.COLUMN_DATEFORMAT,administrationSettings.getDateformat());
        values.put(AdministrationSettings.COLUMN_TIMEFORMAT,administrationSettings.getTimeformat());
        values.put(AdministrationSettings.COLUMN_DATETIMEFORMAT,administrationSettings.getDatetimeformat());
        values.put(AdministrationSettings.COLUMN_SYSTTEM_DATE,Utility.dateFormat.format(administrationSettings.getSystem_date()));
        values.put(AdministrationSettings.COLUMN_ACTIVE,administrationSettings.getActive());
        values.put(AdministrationSettings.COLUMN_LOCATION_ID,administrationSettings.getLocation_id());
        values.put(AdministrationSettings.COLUMN_SOLUTION_ID,administrationSettings.getSolution_id());
        values.put(AdministrationSettings.COLUMN_STAFF_ID,administrationSettings.getStaff_id());
        values.put(AdministrationSettings.COLUMN_WEIGHSCALES_ID,administrationSettings.getWeighscales_id());
        values.put(AdministrationSettings.COLUMN_DASHBOARDITEMLWITH,administrationSettings.getDashboarditemlwith());
        values.put(AdministrationSettings.COLUMN_DASHBOARDITEMPWITH,administrationSettings.getDashboarditempwith());
        values.put(AdministrationSettings.COLUMN_DASHBOARDICON,administrationSettings.getDashboardicon());
        long resultid = database.insert(administrationSettings.TABLE_ADMINISTRATION_SETTINGS, null, values);
        administrationSettings.setId(resultid);
        return administrationSettings;
    }

    public AdministrationStaff insertSettings(AdministrationStaff administrationStaff) {
        ContentValues values = new ContentValues();
        values.put(AdministrationStaff.COLUMN_ID,administrationStaff.getId());
        values.put(AdministrationStaff.COLUMN_STAFF_ID ,administrationStaff.getStaff_id());
        values.put(AdministrationStaff.COLUMN_STAFF_NAME ,administrationStaff.getStaff_name());
        values.put(AdministrationStaff.COLUMN_FATHER_NAME ,administrationStaff.getFather_name());
        values.put(AdministrationStaff.COLUMN_NICK_NAME ,administrationStaff.getNick_name());
        values.put(AdministrationStaff.COLUMN_NRC_NO ,administrationStaff.getNrc_no());
        values.put(AdministrationStaff.COLUMN_ADDRESS ,administrationStaff.getAddress());
        values.put(AdministrationStaff.COLUMN_PHONE_NO ,administrationStaff.getPhone_no());
        values.put(AdministrationStaff.COLUMN_HOME_PHONE_NO ,administrationStaff.getHome_phone_no());
        values.put(AdministrationStaff.COLUMN_STAFF_PHOTO ,administrationStaff.getStaff_photo());
        values.put(AdministrationStaff.COLUMN_DATE_JOINED , Utility.dateFormat.format(administrationStaff.getDate_joined()));
        values.put(AdministrationStaff.COLUMN_DATE_LEFT ,Utility.dateFormat.format(administrationStaff.getDate_left()));
        values.put(AdministrationStaff.COLUMN_USER_ID ,administrationStaff.getUser_id());
        values.put(AdministrationStaff.COLUMN_ACTIVE ,administrationStaff.getActive());
        values.put(AdministrationStaff.COLUMN_ROLE_ID ,administrationStaff.getRole_id());

        long resultid = database.insert(administrationStaff.TABLE_ADMINISTRATION_STAFF, null, values);
        administrationStaff.setId(resultid);
        return administrationStaff;
    }

    public InventoryGold insertSettings(InventoryGold inventoryGold) {
        ContentValues values = new ContentValues();

        values.put(InventoryGold.COLUMN_ID,inventoryGold.getId());
        values.put(InventoryGold.COLUMN_NAME,inventoryGold.getName());
        values.put(InventoryGold.COLUMN_GOLDTYPE,inventoryGold.getGoldtype());
        values.put(InventoryGold.COLUMN_SALEPRICE,inventoryGold.getSaleprice());
        values.put(InventoryGold.COLUMN_PURCHASEPRICE,inventoryGold.getPurchaseprice());
        values.put(InventoryGold.COLUMN_COST,inventoryGold.getCost());
        values.put(InventoryGold.COLUMN_TAX1,inventoryGold.getTax1());
        values.put(InventoryGold.COLUMN_TAX2,inventoryGold.getTax2());
        values.put(InventoryGold.COLUMN_ACTIVE,inventoryGold.getActive());
        values.put(InventoryGold.COLUMN_CODE,inventoryGold.getCode());

        long resultid = database.insert(inventoryGold.TABLE_INVENTORY_GOLD, null, values);
        inventoryGold.setId(resultid);
        return inventoryGold;
    }
}
