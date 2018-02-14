package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by User on 14/02/2018.
 */

public class Inventory_prodhdr  {

    public static final String TABLE_INVENTORY_PRODHDR = "inventory_prodhdr";
    public static final String COLUMN_PROD_NO = "prod_no";
    public static final String COLUMN_PROD_DATE ="pord_date";
    public static final String COLUMN_VOUCHER_NO = "voucher_no";
    public static final String COLUMN_IS_DELIVERED = "is_delivered";
    public static final String COLUMN_IS_CONFIRMED = "is_confirmed";
    public static final String COLUMN_CONFIRMED_BY = "confirmed_by";
    public static final String COLUMN_IS_VOID = "is_void";
    public static final String COLUMN_VOID_DATE = "void_date";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_LOCATION_ID = "location_id";
    public static final String COLUMN_SMIT_ID = "smit_id";
    public static final String COLUMN_STAFF_ID = "staff_id";


    public static final String[] COLUMN_ALL = new String[]
            {
                    COLUMN_PROD_NO,
                    COLUMN_PROD_DATE,
                    COLUMN_VOUCHER_NO,
                    COLUMN_IS_DELIVERED,
                    COLUMN_IS_CONFIRMED,
                    COLUMN_CONFIRMED_BY,
                    COLUMN_IS_VOID,
                    COLUMN_VOID_DATE,
                    COLUMN_TS,
                    COLUMN_LOCATION_ID,
                    COLUMN_SMIT_ID,
                    COLUMN_STAFF_ID
            };

    private String pord_no;
    private Date prod_date;
    private String voucher_no;
    private Boolean is_delivered;
    private Boolean is_confirmed;
    private String confirmedby;
    private Boolean is_void;
    private Date void_date;
    private Date ts;
    private String location_id;
    private String smit_id;
    private String staff_id;

    public String getPord_no() {
        return pord_no;
    }

    public void setPord_no(String pord_no) {
        this.pord_no = pord_no;
    }

    public Date getProd_date() {
        return prod_date;
    }

    public void setProd_date(Date prod_date) {
        this.prod_date = prod_date;
    }

    public String getVoucher_no() {
        return voucher_no;
    }

    public void setVoucher_no(String voucher_no) {
        this.voucher_no = voucher_no;
    }

    public Boolean getIs_delivered() {
        return is_delivered;
    }

    public void setIs_delivered(Boolean is_delivered) {
        this.is_delivered = is_delivered;
    }

    public Boolean getIs_confirmed() {
        return is_confirmed;
    }

    public void setIs_confirmed(Boolean is_confirmed) {
        this.is_confirmed = is_confirmed;
    }

    public String getConfirmedby() {
        return confirmedby;
    }

    public void setConfirmedby(String confirmedby) {
        this.confirmedby = confirmedby;
    }

    public Boolean getIs_void() {
        return is_void;
    }

    public void setIs_void(Boolean is_void) {
        this.is_void = is_void;
    }

    public Date getVoid_date() {
        return void_date;
    }

    public void setVoid_date(Date void_date) {
        this.void_date = void_date;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getSmit_id() {
        return smit_id;
    }

    public void setSmit_id(String smit_id) {
        this.smit_id = smit_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }


    @Override
    public String toString() {
        return "Inventory_prodhdr{" +
                "pord_no='" + pord_no + '\'' +
                ", voucher_no='" + voucher_no + '\'' +
                ", confirmedby='" + confirmedby + '\'' +
                ", location_id='" + location_id + '\'' +
                ", smit_id='" + smit_id + '\'' +
                ", staff_id='" + staff_id + '\'' +
                '}';
    }
}
