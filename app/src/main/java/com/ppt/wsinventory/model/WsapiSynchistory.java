package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by arkar on 6/3/2018.
 */

public class WsapiSynchistory {
    public static final String TABLE_WSAPI_SYNCHISTORY = "wsapi_synchistory";
    public static final String COLUMN_DEVICE_ID = "device_id";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_TABLENAME = "tablename";
    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_DEVICE_ID,
            COLUMN_TIMESTAMP,
            COLUMN_TABLENAME
    };
    private String device_id;
    private Date timestamp;
    private String tablename;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
}
