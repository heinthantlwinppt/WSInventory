package com.ppt.wsinventory.wsdb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 28/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "SW-DbHelper";
    private static final String DATABASE_NAME = "WS.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ITEMS = "Items";
    private static final String TABLE_CREATE_ITEMS =
            "CREATE TABLE Items \n" +
                    "(\n" +
                    " ItemName VARCHAR COLLATE NOCASE, \n" +
                    " ItemType VARCHAR COLLATE NOCASE\n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_SETTINGS = "administration_settings";
    private static final String TABLE_CREATE_ADMINISTRATION_SETTINGS =
            "CREATE TABLE administration_settings \n" +
                    "(\n" +
                    "id VARCHAR COLLATE NOCASE, \n" +
                    "h1 VARCHAR COLLATE NOCASE, \n" +
                    "h2 VARCHAR COLLATE NOCASE, \n" +
                    "h3 VARCHAR COLLATE NOCASE, \n" +
                    "h4 VARCHAR COLLATE NOCASE, \n" +
                    "t1 VARCHAR COLLATE NOCASE, \n" +
                    "t2 VARCHAR COLLATE NOCASE, \n" +
                    "t3 VARCHAR COLLATE NOCASE, \n" +
                    "t4 VARCHAR COLLATE NOCASE, \n" +
                    "dateformat VARCHAR COLLATE NOCASE, \n" +
                    "timeformat VARCHAR COLLATE NOCASE, \n" +
                    "datetimeformat VARCHAR COLLATE NOCASE, \n" +
                    "system_date DATETIME, \n" +
                    "active BOOL, \n" +
                    "location_id VARCHAR COLLATE NOCASE, \n" +
                    "solution_id VARCHAR COLLATE NOCASE, \n" +
                    "staff_id VARCHAR COLLATE NOCASE, \n" +
                    "weighscales_id VARCHAR COLLATE NOCASE, \n" +
                    "dashboarditemlwith INTEGER, \n" +
                    "dashboarditempwith INTEGER, \n" +
                    "dashboardicon VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_STAFF = "administration_staff";
    private static final String TABLE_CREATE_ADMINISTRATION_STAFF =
            "CREATE TABLE administration_staff \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " staff_id  VARCHAR COLLATE NOCASE, \n" +
                    " staff_name  VARCHAR COLLATE NOCASE, \n" +
                    " father_name  VARCHAR COLLATE NOCASE, \n" +
                    " nick_name  VARCHAR COLLATE NOCASE, \n" +
                    " nrc_no  VARCHAR COLLATE NOCASE, \n" +
                    " address  VARCHAR COLLATE NOCASE, \n" +
                    " phone_no  VARCHAR COLLATE NOCASE, \n" +
                    " home_phone_no   VARCHAR COLLATE NOCASE, \n" +
                    " staff_photo   VARCHAR COLLATE NOCASE, \n" +
                    " date_joined   DATETIME, \n" +
                    " date_left   DATETIME, \n" +
                    " user_id    INTEGER, \n" +
                    " active    BOOL\n, " +
                    " role_id    VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_INVENTORY_GOLD= "inventory_gold";
    private static final String TABLE_CREATE_INVENTORY_GOLD =
            "CREATE TABLE inventory_gold \n" +
                    "(\n" +
                    " id  INTERGER, \n" +
                    " name   VARCHAR COLLATE NOCASE, \n" +
                    " goldtype   SMALLINT, \n" +
                    " saleprice   NUMERIC, \n" +
                    " purchaseprice   NUMERIC, \n" +
                    " cost   NUMERIC, \n" +
                    " tax1   NUMERIC, \n" +
                    " tax2    NUMERIC, \n" +
                    " active    BOOL, \n" +
                    " code    VARCHAR COLLATE NOCASE\n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_WSDASHBOARD = "administration_wsdashboard";
    private static final String TABLE_CREATE_ADMINISTRATION_WSDASHBOARD =
            "CREATE TABLE administration_wsdashboard \n" +
                    "(\n" +
                    " id VARCHAR COLLATE NOCASE, \n" +
                    " title VARCHAR COLLATE NOCASE, \n" +
                    " actionname  VARCHAR COLLATE NOCASE, \n" +
                    " groupname  VARCHAR COLLATE NOCASE, \n" +
                    " image  VARCHAR COLLATE NOCASE, \n" +
                    " timestamp  DATETIME, \n" +
                    " delete  BOOL, \n" +
                    " displayno  INTEGER, \n" +
                    " screen_id  INTEGER \n" +
                    ")";


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_ITEMS);
        Log.i(TAG, "onCreate: Table " + TABLE_ITEMS + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_SETTINGS);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_SETTINGS + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_STAFF);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_STAFF + " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_GOLD);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_GOLD + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_WSDASHBOARD);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_WSDASHBOARD + " has been created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_SETTINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_STAFF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_GOLD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_WSDASHBOARD);
        onCreate(db);
        Log.i(TAG, "Database has been upgraded from " +
                oldVersion + " to " + newVersion);

    }


}
