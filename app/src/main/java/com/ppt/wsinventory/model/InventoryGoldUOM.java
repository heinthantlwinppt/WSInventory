package com.ppt.wsinventory.model;

/**
 * Created by ThinkCentre on 1/11/2018.
 */

public class InventoryGoldUOM {
    public static final String TABLE_INVENTORY_GOLD = "inventory_gold";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GOLDTYPE = "goldtype";
    public static final String COLUMN_SALEPRICE = "saleprice";
    public static final String COLUMN_PURCHASEPRICE = "purchaseprice";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_TAX1 = "tax1";
    public static final String COLUMN_TAX2 = "tax2";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_CODE = "code";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_NAME ,
            COLUMN_GOLDTYPE ,
            COLUMN_SALEPRICE ,
            COLUMN_PURCHASEPRICE ,
            COLUMN_COST ,
            COLUMN_TAX1 ,
            COLUMN_TAX2 ,
            COLUMN_ACTIVE ,
            COLUMN_CODE

    };

    private long id ;
    private String name ;
    private int goldtype ;
    private double saleprice ;
    private double purchaseprice ;
    private double cost ;
    private double tax1 ;
    private double tax2 ;
    private boolean active ;
    private String code ;
}
