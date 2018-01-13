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
                    "dashboarditemlwith INTEGER, \n" +
                    "dashboarditempwith INTEGER, \n" +
                    "dashboardicon VARCHAR COLLATE NOCASE, \n" +
                    "active BOOL, \n" +
                    "location_id VARCHAR COLLATE NOCASE, \n" +
                    "solution_id VARCHAR COLLATE NOCASE, \n" +
                    "staff_id VARCHAR COLLATE NOCASE, \n" +
                    "weighscales_id VARCHAR COLLATE NOCASE, \n" +
                    "device_id VARCHAR COLLATE NOCASE, \n" +
                    "devicetype_id VARCHAR COLLATE NOCASE \n" +
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
                    " password   VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL, \n " +
                    " role    VARCHAR COLLATE NOCASE \n" +
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
                    " is_delete  BOOL, \n" +
                    " displayno  INTEGER, \n" +
                    " screen_id  INTEGER \n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_SOLUTIONS = "administration_solutions";
    private static final String TABLE_CREATE_ADMINISTRATION_SOLUTIONS=
            "CREATE TABLE administration_solutions \n" +
                    "(\n" +
                    " solution_name  VARCHAR COLLATE NOCASE, \n" +
                    " active   BOOL\n" +
                    ")";

    private static final String TABLE_ADMINISTRATION_ROLE = "administration_role";
    private static final String TABLE_CREATE_ADMINISTRATION_ROLE =
            "CREATE TABLE administration_role \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " role_name  VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL \n " +
                    ")";
    private static final String TABLE_ADMINISTRATION_LOCATIONS= "administration_locations";
    private static final String TABLE_CREATE_ADMINISTRATION_LOCATIONS =
            "CREATE TABLE administration_locations \n" +
                    "(\n" +
                    " id  INTERGER, \n" +
                    " loc_name   VARCHAR COLLATE NOCASE, \n" +
                    " loc_addr   VARCHAR COLLATE NOCASE, \n" +
                    " longitude   NUMERIC, \n" +
                    " latitude   NUMERIC, \n" +
                    " receiving_bin   VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTROY_UOM= "inventory_uom";
    private static final String TABLE_CREATE_INVENTROY_UOM =
            "CREATE TABLE inventory_uom \n" +
                    "(\n" +
                    " uom  VARCHAR COLLATE NOCASE, \n" +
                    " baseqty   NUMERIC, \n" +
                    " product_id   VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTROY_BIN= "inventory_bin";
    private static final String TABLE_CREATE_INVENTROY_BIN =
            "CREATE TABLE inventory_bin \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " bin_name  VARCHAR COLLATE NOCASE, \n" +
                    " bin_description   VARCHAR COLLATE NOCASE, \n" +
                    " bin_type   VARCHAR COLLATE NOCASE, \n" +
                    " barcode   VARCHAR COLLATE NOCASE, \n" +
                    " tag   VARCHAR COLLATE NOCASE, \n" +
                    " location_id   VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL, \n" +
                    " ts DATETIME \n" +
                    ")";
    private static final String TABLE_INVENTROY_PALLET= "inventory_pallet";
    private static final String TABLE_CREATE_INVENTROY_PALLET =
            "CREATE TABLE inventory_pallet \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " pallet_name  VARCHAR COLLATE NOCASE, \n" +
                    " pallet_description   VARCHAR COLLATE NOCASE, \n" +
                    " pallet_type   VARCHAR COLLATE NOCASE, \n" +
                    " barcode   VARCHAR COLLATE NOCASE, \n" +
                    " tag   VARCHAR COLLATE NOCASE, \n" +
                    " location_id   VARCHAR COLLATE NOCASE, \n" +
                    " weight   NUMERIC, \n" +
                    " ts   DATETIME, \n" +
                    " active    BOOL, \n" +
                    " is_used    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTROY_PRODUCTS= "inventory_products";
    private static final String TABLE_CREATE_INVENTROY_PRODUCTS =
            "CREATE TABLE inventory_products \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " name  VARCHAR COLLATE NOCASE, \n" +
                    " designname   VARCHAR COLLATE NOCASE, \n" +
                    " photo   VARCHAR COLLATE NOCASE, \n" +
                    " barcode   VARCHAR COLLATE NOCASE, \n" +
                    " tag   VARCHAR COLLATE NOCASE, \n" +
                    " baseuom   VARCHAR COLLATE NOCASE, \n" +
                    " minqty   INTEGER, \n" +
                    " maxqty   INTEGER, \n" +
                    " active    BOOL, \n" +
                    " is_delete    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTORY_PRODUCTGROUPS= "inventory_productgroups";
    private static final String TABLE_CREATE_INVENTORY_PRODUCTGROUPS =
            "CREATE TABLE inventory_productgroups \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " name  VARCHAR COLLATE NOCASE, \n" +
                    " active    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTROY_GOLDUOM = "inventory_golduom";
    private static final String TABLE_CREATE_INVENTROY_GOLDUOM =
            "CREATE TABLE inventory_golduom \n" +
                    "(\n" +
                    " uom  VARCHAR COLLATE NOCASE, \n" +
                    " baseqty  NUMERIC, \n" +
                    " gold_id  INTEGER, \n" +
                    " active    BOOL \n" +
                    ")";

    private static final String TABLE_INVENTORY_PRODUCTSERIAL = "inventory_productserial";
    private static final String TABLE_CREATE_INVENTORY_PRODUCTSERIAL =
            "CREATE TABLE inventory_productserial \n" +
                    "(\n" +
                    "serial_no VARCHAR COLLATE NOCASE, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "serial_date DATETIME, \n" +
                    "plength VARCHAR COLLATE NOCASE, \n" +
                    "photo VARCHAR COLLATE NOCASE, \n" +
                    "barcode VARCHAR COLLATE NOCASE, \n" +
                    "tag VARCHAR COLLATE NOCASE, \n" +
                    "weight NUMERIC, \n" +
                    "k INTEGER, \n" +
                    "p INTEGER, \n" +
                    "y NUMERIC, \n" +
                    "reduce_weight NUMERIC, \n" +
                    "reduce_k INTEGER, \n" +
                    "reduce_p INTEGER, \n" +
                    "reduce_y NUMERIC, \n" +
                    "jewel_weight NUMERIC, \n" +
                    "jewel_k INTEGER, \n" +
                    "jewel_p INTEGER, \n" +
                    "jewel_y NUMERIC, \n" +
                    "jewel_fee NUMERIC, \n" +
                    "production_fee NUMERIC, \n" +
                    "jewel_name1 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_name2 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_name3 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_name4 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_name5 VARCHAR COLLATE NOCASE, \n" +
                    "jewel_weight1 NUMERIC, \n" +
                    "jewel_weight2 NUMERIC, \n" +
                    "jewel_weight3 NUMERIC, \n" +
                    "jewel_weight4 NUMERIC, \n" +
                    "jewel_weight5 NUMERIC, \n" +
                    "remarks VARCHAR COLLATE NOCASE, \n" +
                    "ts DATETIME, \n" +
                    "delivered BOOL, \n" +
                    "goodsid VARCHAR COLLATE NOCASE, \n" +
                    "is_delete BOOL, \n" +
                    "bin_id VARCHAR COLLATE NOCASE, \n" +
                    "gold_id INTEGER, \n" +
                    "location_id VARCHAR COLLATE NOCASE, \n" +
                    "pallet_id VARCHAR COLLATE NOCASE, \n" +
                    "product_id VARCHAR COLLATE NOCASE, \n" +
                    "staff_id VARCHAR COLLATE NOCASE, \n" +
                    "smith_id INTEGER, \n" +
                    "uom_id VARCHAR COLLATE NOCASE \n" +
                    ")";

    private static final String TABLE_MANUFACTURING_SMITH = "manufacturing_smith";
    private static final String TABLE_CREATE_MANUFACTURING_SMITH =
            "CREATE TABLE manufacturing_smith \n" +
                    "(\n" +
                    "id INTEGER, \n" +
                    "name VARCHAR COLLATE NOCASE, \n" +
                    "nickname VARCHAR COLLATE NOCASE, \n" +
                    "nrc VARCHAR COLLATE NOCASE, \n" +
                    "address VARCHAR COLLATE NOCASE, \n" +
                    "phone VARCHAR COLLATE NOCASE, \n" +
                    "refname VARCHAR COLLATE NOCASE, \n" +
                    "mixjob VARCHAR COLLATE NOCASE, \n" +
                    "k INTEGER, \n" +
                    "p INTEGER, \n" +
                    "y NUMERIC, \n" +
                    "g NUMERIC, \n" +
                    "cost NUMERIC, \n" +
                    "date_joined DATETIME, \n" +
                    "date_end DATETIME, \n" +
                    "is_smith2 BOOL, \n" +
                    "active BOOL, \n" +
                    "goldsaving NUMERIC, \n" +
                    "inhandjob INTEGER, \n" +
                    "photo VARCHAR COLLATE NOCASE \n" +
                    ")";
//    private static final String TABLE_INVENTROY_GOLDUOM= "inventory_golduom";
//    private static final String TABLE_CREATE_INVENTROY_GOLDUOM =
//            "CREATE TABLE inventory_golduom \n" +
//                    "(\n" +
//                    " uom  VARCHAR COLLATE NOCASE, \n" +
//                    " baseqty   NUMERIC, \n" +
//                    " gold_id   VARCHAR COLLATE NOCASE, \n" +
//                    " active    BOOL \n" +
//                    ")";
    private static final String TABLE_INVENTORY_GOODSINVENTORY = "inventory_goodsinventory";
    private static final String TABLE_CREATE_INVENTORY_GOODSINVENTORY =
            "CREATE TABLE inventory_goodsinventory \n" +
                    "(\n" +
                    " id  VARCHAR COLLATE NOCASE, \n" +
                    " qty  NUMERIC, \n" +
                    " weight   NUMERIC, \n" +
                    " k   INTEGER, \n" +
                    " p  INTEGER, \n" +
                    " y   INTEGER, \n" +
                    " ts   DATETIME, \n" +
                    " location_id   VARCHAR COLLATE NOCASE, \n" +
                    " product_id   VARCHAR COLLATE NOCASE, \n" +
                    " uom_id   VARCHAR COLLATE NOCASE, \n" +
                    " is_delete    BOOL \n" +
                    ")";
//    private static final String TABLE_INVENTROY_BIN= "inventory_bin";
//    private static final String TABLE_CREATE_INVENTROY_BIN =
//            "CREATE TABLE inventory_bin \n" +
//                    "(\n" +
//                    " id  VARCHAR COLLATE NOCASE, \n" +
//                    " bin_name  VARCHAR COLLATE NOCASE, \n" +
//                    " bin_description   VARCHAR COLLATE NOCASE, \n" +
//                    " bin_type   VARCHAR COLLATE NOCASE, \n" +
//                    " barcode   VARCHAR COLLATE NOCASE, \n" +
//                    " tag   VARCHAR COLLATE NOCASE, \n" +
//                    " location_id   VARCHAR COLLATE NOCASE, \n" +
//                    " active    BOOL \n" +
//                    ")";
//    private static final String TABLE_INVENTROY_BIN= "inventory_bin";
//    private static final String TABLE_CREATE_INVENTROY_BIN =
//            "CREATE TABLE inventory_bin \n" +
//                    "(\n" +
//                    " id  VARCHAR COLLATE NOCASE, \n" +
//                    " bin_name  VARCHAR COLLATE NOCASE, \n" +
//                    " bin_description   VARCHAR COLLATE NOCASE, \n" +
//                    " bin_type   VARCHAR COLLATE NOCASE, \n" +
//                    " barcode   VARCHAR COLLATE NOCASE, \n" +
//                    " tag   VARCHAR COLLATE NOCASE, \n" +
//                    " location_id   VARCHAR COLLATE NOCASE, \n" +
//                    " active    BOOL \n" +
//                    ")";
//    private static final String TABLE_INVENTROY_BIN= "inventory_bin";
//    private static final String TABLE_CREATE_INVENTROY_BIN =
//            "CREATE TABLE inventory_bin \n" +
//                    "(\n" +
//                    " id  VARCHAR COLLATE NOCASE, \n" +
//                    " bin_name  VARCHAR COLLATE NOCASE, \n" +
//                    " bin_description   VARCHAR COLLATE NOCASE, \n" +
//                    " bin_type   VARCHAR COLLATE NOCASE, \n" +
//                    " barcode   VARCHAR COLLATE NOCASE, \n" +
//                    " tag   VARCHAR COLLATE NOCASE, \n" +
//                    " location_id   VARCHAR COLLATE NOCASE, \n" +
//                    " active    BOOL \n" +
//                    ")";


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
        db.execSQL(TABLE_CREATE_ADMINISTRATION_SOLUTIONS);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_SOLUTIONS + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_ROLE);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_ROLE + " has been created");
        db.execSQL(TABLE_CREATE_ADMINISTRATION_LOCATIONS);
        Log.i(TAG, "onCreate: Table " + TABLE_ADMINISTRATION_LOCATIONS + " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_UOM);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_UOM + " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_BIN);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_BIN + " has been created");
//        db.execSQL(TABLE_CREATE_INVENTORY_GOODSINVENTORY);
//        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_GOODSINVENTORY + " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_PALLET);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_PALLET+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_PRODUCTS);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_PRODUCTS+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_PRODUCTGROUPS);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_PRODUCTGROUPS+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTROY_GOLDUOM);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTROY_GOLDUOM+ " has been created");
        db.execSQL(TABLE_CREATE_INVENTORY_PRODUCTSERIAL);
        Log.i(TAG, "onCreate: Table " + TABLE_INVENTORY_PRODUCTSERIAL+ " has been created");
        db.execSQL(TABLE_CREATE_MANUFACTURING_SMITH);
        Log.i(TAG, "onCreate: Table " + TABLE_MANUFACTURING_SMITH+ " has been created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_SETTINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_STAFF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_GOLD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_WSDASHBOARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_SOLUTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_ROLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATION_LOCATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTROY_BIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTROY_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_PRODUCTGROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTROY_PALLET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTROY_GOLDUOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_PRODUCTSERIAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MANUFACTURING_SMITH);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY_GOODSINVENTORY);
        onCreate(db);
        Log.i(TAG, "Database has been upgraded from " +
                oldVersion + " to " + newVersion);

    }

}
