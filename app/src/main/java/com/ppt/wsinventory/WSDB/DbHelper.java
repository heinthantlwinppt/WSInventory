package com.ppt.wsinventory.WSDB;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 28/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "Truckkit-DbHelper";
    private static final String DATABASE_NAME = "truckkit.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ITEMS = "Items";
    private static final String TABLE_CREATE_ITEMS =
            "CREATE TABLE Items \n" +
                    "(\n" +
                    " DocNo VARCHAR COLLATE NOCASE, \n" +
                    " DocDate DATETIME, \n" +
                    " UserID VARCHAR COLLATE NOCASE, \n" +
                    " ReasonID VARCHAR COLLATE NOCASE, \n" +
                    " Remarks VARCHAR COLLATE NOCASE, \n" +
                    " Amount NUMERIC, \n" +
                    " Status VARCHAR COLLATE NOCASE, \n" +
                    " ApprovedBy VARCHAR COLLATE NOCASE, \n" +
                    " DeviceID VARCHAR COLLATE NOCASE, \n" +
                    " Uploaded BOOL\n" +
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
        Log.i(TAG, "Database has been upgraded from " +
                oldVersion + " to " + newVersion);

    }


}
