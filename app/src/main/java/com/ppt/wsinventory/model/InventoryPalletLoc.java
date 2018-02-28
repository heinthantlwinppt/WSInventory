package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by User on 28/02/2018.
 */

public class InventoryPalletLoc {


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PALLET_NAME = "pallet_name";
    public static final String COLUMN_PALLET_DESCRIPTION = "pallet_description";
    public static final String COLUMN_PALLET_TYPE = "pallet_type";
    public static final String COLUMN_PALLET_BARCODE = "barcode";
    public static final String COLUMN_PALLET_TAG = "tag";
    public static final String COLUMN_PALLET_LOCATION_ID = "location_id";
    public static final String COLUMN_PALLET_WEIGHT = "weight";
    public static final String COLUMN_PALLET_ACTIVE = "active";
    public static final String COLUMN_PALLET_IS_USED = "is_used";
    public static final String COLUMN_PALLET_LOCATION = "loc_name";
    public static final String COLUMN_TS  = "ts";


    public static final String[] COLUMN_ALL
            = new String[]
            {
                    COLUMN_ID,
                    COLUMN_PALLET_NAME,
                    COLUMN_PALLET_DESCRIPTION,
                    COLUMN_PALLET_TYPE,
                    COLUMN_PALLET_BARCODE,
                    COLUMN_PALLET_TAG,
                    COLUMN_PALLET_LOCATION_ID,
                    COLUMN_PALLET_WEIGHT,
                    COLUMN_PALLET_ACTIVE,
                    COLUMN_TS,
                    COLUMN_PALLET_IS_USED,
                    COLUMN_PALLET_LOCATION
            };


    private String id;
    private String pallet_name;
    private String pallet_description;
    private String pallet_type;
    private String pallet_barcode;
    private String pallet_tag;
    private String pallet_location_id;
    private String pallet_weight;
    private boolean pallet_active;
    private boolean pallet_is_used;
    private String pallet_location;
    private Date ts ;

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "InventoryPalletLoc{" +
                "id='" + id + '\'' +
                ", pallet_name='" + pallet_name + '\'' +
                ", pallet_description='" + pallet_description + '\'' +
                ", pallet_type='" + pallet_type + '\'' +
                ", pallet_barcode='" + pallet_barcode + '\'' +
                ", pallet_tag='" + pallet_tag + '\'' +
                ", pallet_location_id='" + pallet_location_id + '\'' +
                ", pallet_weight='" + pallet_weight + '\'' +
                ", pallet_location='" + pallet_location + '\'' +
                '}';
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

    public String getPallet_barcode() {
        return pallet_barcode;
    }

    public void setPallet_barcode(String pallet_barcode) {
        this.pallet_barcode = pallet_barcode;
    }

    public String getPallet_tag() {
        return pallet_tag;
    }

    public void setPallet_tag(String pallet_tag) {
        this.pallet_tag = pallet_tag;
    }

    public String getPallet_location_id() {
        return pallet_location_id;
    }

    public void setPallet_location_id(String pallet_location_id) {
        this.pallet_location_id = pallet_location_id;
    }

    public String getPallet_weight() {
        return pallet_weight;
    }

    public void setPallet_weight(String pallet_weight) {
        this.pallet_weight = pallet_weight;
    }

    public Boolean getPallet_active() {
        return pallet_active;
    }

    public void setPallet_active(Boolean pallet_active) {
        this.pallet_active = pallet_active;
    }

    public boolean isPallet_is_used() {
        return pallet_is_used;
    }

    public void setPallet_is_used(boolean pallet_is_used) {
        this.pallet_is_used = pallet_is_used;
    }

    public String getPallet_location() {
        return pallet_location;
    }

    public void setPallet_location(String pallet_location) {
        this.pallet_location = pallet_location;
    }
}
