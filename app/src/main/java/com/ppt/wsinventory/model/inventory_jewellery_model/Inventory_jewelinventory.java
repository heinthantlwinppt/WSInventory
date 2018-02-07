package com.ppt.wsinventory.model.inventory_jewellery_model;

import java.util.Date;

/**
 * Created by User on 02/02/2018.
 */

public class Inventory_jewelinventory {
    public static final String TABLE_INVENTORY_JEWELINVENTORY = "inventory_jewelinventory";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_TS= "ts";
    public static final String COLUMN_JEWELLENGTH_ID = "jewellength_id";
    public static final String COLUMN_JEWELSHAPE_ID = "jewelshape_id";
    public static final String COLUMN_JEWELTYPE_ID = "jeweltype_id";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_QTY,
            COLUMN_TS,
            COLUMN_JEWELLENGTH_ID,
            COLUMN_JEWELSHAPE_ID,
            COLUMN_JEWELTYPE_ID

    };

    private double qty;
    private Date ts;
    private int jewellength_id;
    private int jewelshape_id;
    private int jeweltype_id;

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public int getJewellength_id() {
        return jewellength_id;
    }

    public void setJewellength_id(int jewellength_id) {
        this.jewellength_id = jewellength_id;
    }

    public int getJewelshape_id() {
        return jewelshape_id;
    }

    public void setJewelshape_id(int jewelshape_id) {
        this.jewelshape_id = jewelshape_id;
    }

    public int getJeweltype_id() {
        return jeweltype_id;
    }

    public void setJeweltype_id(int jeweltype_id) {
        this.jeweltype_id = jeweltype_id;
    }


}
