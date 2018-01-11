package com.ppt.wsinventory.model;

/**
 * Created by ThinkCentre on 1/11/2018.
 */

public class InventoryPallet {
    public static final String TABLE_INVENTORY_PALLET= "inventory_pallet";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BIN_NAME  = "bin_name";
    public static final String COLUMN_BIN_DESCRIPTION  = "bin_description";
    public static final String COLUMN_BIN_TYPE  = "bin_type";
    public static final String COLUMN_BARCODE  = "barcode";
    public static final String COLUMN_TAG  = "tag";
    public static final String COLUMN_LOCATION_ID  = "location_id";
    public static final String COLUMN_ACTIVE  = "active";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_BIN_NAME ,
            COLUMN_BIN_DESCRIPTION ,
            COLUMN_BIN_TYPE ,
            COLUMN_BARCODE ,
            COLUMN_TAG ,
            COLUMN_LOCATION_ID ,
            COLUMN_ACTIVE ,
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
}
