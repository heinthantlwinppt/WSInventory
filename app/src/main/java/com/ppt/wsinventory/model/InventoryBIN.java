package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/11/2018.
 */

public class InventoryBIN {
    public static final String TABLE_INVENTORY_BIN= "inventory_bin";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BIN_NAME  = "bin_name";
    public static final String COLUMN_BIN_DESCRIPTION  = "bin_description";
    public static final String COLUMN_BIN_TYPE  = "bin_type";
    public static final String COLUMN_BARCODE  = "barcode";
    public static final String COLUMN_TAG  = "tag";
    public static final String COLUMN_ADDRESS  = "address";
    public static final String COLUMN_LOCATION_ID  = "location_id";
    public static final String COLUMN_ACTIVE  = "active";
    public static final String COLUMN_TS  = "ts";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_BIN_NAME ,
            COLUMN_BIN_DESCRIPTION ,
            COLUMN_BIN_TYPE ,
            COLUMN_BARCODE ,
            COLUMN_TAG ,
            COLUMN_ADDRESS,
            COLUMN_LOCATION_ID ,
            COLUMN_ACTIVE ,
            COLUMN_TS
    };

    private String id ;
    private String bin_name ;
    private String bin_description ;
    private String bin_type ;
    private String barcode ;
    private String tag ;
    private String address ;
    private String location_id ;
    private boolean active ;
    private Date ts ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBin_name() {
        return bin_name;
    }

    public void setBin_name(String bin_name) {
        this.bin_name = bin_name;
    }

    public String getBin_description() {
        return bin_description;
    }

    public void setBin_description(String bin_description) {
        this.bin_description = bin_description;
    }

    public String getBin_type() {
        return bin_type;
    }

    public void setBin_type(String bin_type) {
        this.bin_type = bin_type;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "InventoryBIN{" +
                "id='" + id + '\'' +
                ", bin_name='" + bin_name + '\'' +
                ", bin_description='" + bin_description + '\'' +
                ", bin_type='" + bin_type + '\'' +
                ", barcode='" + barcode + '\'' +
                ", tag='" + tag + '\'' +
                ", address='" + address + '\'' +
                ", location_id='" + location_id + '\'' +
                ", active=" + active +
                ", ts=" + ts +
                '}';
    }
}
