package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/11/2018.
 */

public class InventoryUOM {
    public static final String TABLE_INVENTROY_UOM = "inventory_uom";
    public static final String COLUMN_UOM = "uom";
    public static final String COLUMN_BASEQUANLITY  = "baseqty";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_ACTIVE  = "active";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_UOM ,
            COLUMN_BASEQUANLITY ,
            COLUMN_PRODUCT_ID ,
            COLUMN_ACTIVE ,
    };

    private String uom ;
    private double baseqty ;
    private String product_id ;
    private boolean active ;

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public double getBaseqty() {
        return baseqty;
    }

    public void setBaseqty(double baseqty) {
        this.baseqty = baseqty;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "InventoryUOM{" +
                "product_id='" + product_id + '\'' +
                '}';
    }
}
