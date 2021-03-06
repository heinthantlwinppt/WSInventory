package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/11/2018.
 */

public class InventoryPallet {
    public static final String TABLE_INVENTORY_PALLET= "inventory_pallet";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PALLET_NAME  = "pallet_name";
    public static final String COLUMN_PALLET_DESCRIPTION  = "pallet_description";
    public static final String COLUMN_PALLET_TYPE  = "pallet_type";
    public static final String COLUMN_BARCODE  = "barcode";
    public static final String COLUMN_TAG  = "tag";
    public static final String COLUMN_ACTIVE  = "active";
    public static final String COLUMN_LOCATION_ID  = "location_id";
    public static final String COLUMN_IS_USED  = "is_used";
    public static final String COLUMN_WEIGHT  = "weight";
    public static final String COLUMN_TS  = "ts";
    public static final String COLUMN_UPLOADED = "uploaded";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_ID ,
            COLUMN_PALLET_NAME ,
            COLUMN_PALLET_DESCRIPTION ,
            COLUMN_PALLET_TYPE ,
            COLUMN_BARCODE ,
            COLUMN_TAG ,
            COLUMN_LOCATION_ID ,
            COLUMN_ACTIVE ,
            COLUMN_IS_USED ,
            COLUMN_WEIGHT ,
            COLUMN_TS,
            COLUMN_UPLOADED
    };

    private String id ;
    private String pallet_name ;
    private String pallet_description ;
    private String pallet_type ;
    private String barcode ;
    private String tag ;
    private boolean is_used ;
    private String location_id ;
    private double weight ;
    private Date ts ;
    private boolean active ;
    private boolean uploaded;

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean updated) {
        this.uploaded = updated;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPallet_name() {
        return pallet_name;
    }

    public void setPallet_name(String pallet_name) {
        this.pallet_name = pallet_name;
    }

    public String getPallet_description() {
        return pallet_description;
    }

    public void setPallet_description(String pallet_description) {
        this.pallet_description = pallet_description;
    }

    public String getPallet_type() {
        return pallet_type;
    }

    public void setPallet_type(String pallet_type) {
        this.pallet_type = pallet_type;
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

    public boolean isUsed() {
        return is_used;
    }

    public void setIs_used(boolean is_used) {
        this.is_used = is_used;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "InventoryPallet{" +
                "id='" + id + '\'' +
                ", pallet_name='" + pallet_name + '\'' +
                ", pallet_description='" + pallet_description + '\'' +
                ", pallet_type='" + pallet_type + '\'' +
                ", barcode='" + barcode + '\'' +
                ", tag='" + tag + '\'' +
                ", is_used=" + is_used +
                ", location_id='" + location_id + '\'' +
                ", weight=" + weight +
                ", ts=" + ts +
                ", active=" + active +
                '}';
    }
}
