package com.ppt.wsinventory.model.inventory_receive_model;

import java.util.Date;

/**
 * Created by User on 08/02/2018.
 */

public class Inventory_receivedhdr {

    public static final String TABLE_INVENTORY_RECEIVEDHDR = "inventory_receivedhdr";
    public static final String COLUMN_RECEIVE_NO  = "receive_no";
    public static final String COLUMN_RECEIVE_DATE = "receive_date";
    public static final String COLUMN_IS_COMPLETED = "is_completed";
    public static final String COLUMN_IS_APPROVED = "is_approved";
    public static final String COLUMN_IS_VOID = "is_void";
    public static final String COLUMN_VOID_DATE = "void_date";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_DOCTYPE_ID = "doctype_id";
    public static final String COLUMN_LOCATION_ID = "location_id";
    public static final String COLUMN_SMITH_ID = "smith_id";
    public static final String COLUMN_STAFF_ID = "staff_id";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_RECEIVE_NO,
            COLUMN_RECEIVE_DATE,
            COLUMN_IS_COMPLETED,
            COLUMN_IS_APPROVED,
            COLUMN_IS_VOID,
            COLUMN_VOID_DATE,
            COLUMN_IS_DELETE,
            COLUMN_TS,
            COLUMN_DOCTYPE_ID,
            COLUMN_LOCATION_ID,
            COLUMN_SMITH_ID,
            COLUMN_STAFF_ID
    };

    private String receive_no;
    private Date receive_date;
    private boolean is_completed;
    private boolean is_approved;
    private boolean is_void;
    private Date void_date;
    private boolean is_delete;
    private Date ts;
    private int doctype_id;
    private String location_id;
    private int smith_id;
    private String staff_id;

    public String getReceive_no() {
        return receive_no;
    }

    public void setReceive_no(String receive_no) {
        this.receive_no = receive_no;
    }

    public Date getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(Date receive_date) {
        this.receive_date = receive_date;
    }

    public boolean getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(boolean is_completed) {
        this.is_completed = is_completed;
    }

    public boolean getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }

    public boolean getIs_void() {
        return is_void;
    }

    public void setIs_void(boolean is_void) {
        this.is_void = is_void;
    }

    public Date getVoid_date() {
        return void_date;
    }

    public void setVoid_date(Date void_date) {
        this.void_date = void_date;
    }

    public boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public int getDoctype_id() {
        return doctype_id;
    }

    public void setDoctype_id(int doctype_id) {
        this.doctype_id = doctype_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public int getSmith_id() {
        return smith_id;
    }

    public void setSmith_id(int smith_id) {
        this.smith_id = smith_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    @Override
    public String toString() {
        return "Inventory_receivedhdr{" +
                "receive_no='" + receive_no + '\'' +
                ", location_id='" + location_id + '\'' +
                ", staff_id='" + staff_id + '\'' +
                '}';
    }
}
