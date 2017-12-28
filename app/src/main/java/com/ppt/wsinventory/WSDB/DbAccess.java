package com.ppt.wsinventory.WSDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;

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

    public Cursor readData(String sql) {
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




}
