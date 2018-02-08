package com.ppt.wsinventory.model.inventory_jewellery_model;

import java.util.Date;

/**
 * Created by User on 02/02/2018.
 */

public class Inventory_jewelpurchaseitems {

    public static final String TABLE_INVENTORY_JEWELPURCHASEITEMS = "inventory_jewelpurchaseitems";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_ROW_NO = "row_no";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_JEWEL_PURCHASE_ID = "jewel_purchase_id";
    public static final String COLUMN_JEWELLENGTH_ID = "jewellength_id";
    public static final String COLUMN_JEWELSHAPE_ID = "jewelshape_id";
    public static final String COLUMN_JEWELTYPE_ID = "jeweltype_id";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_ID,
            COLUMN_QTY,
            COLUMN_PRICE,
            COLUMN_AMOUNT,
            COLUMN_REMARKS,
            COLUMN_ROW_NO,
            COLUMN_IS_DELETE,
            COLUMN_TS,
            COLUMN_JEWEL_PURCHASE_ID,
            COLUMN_JEWELLENGTH_ID,
            COLUMN_JEWELSHAPE_ID,
            COLUMN_JEWELTYPE_ID

    };

    private int id;
    private double  qty;
    private double price;
    private double amount;
    private String remarks;
    private int row_no;
    private boolean is_delete;
    private Date ts;
    private String jewel_purchase_id;
    private int jewellength_id;
    private int jewelshape_id;
    private int jeweltype_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getRow_no() {
        return row_no;
    }

    public void setRow_no(int row_no) {
        this.row_no = row_no;
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

    public String getJewel_purchase_id() {
        return jewel_purchase_id;
    }

    public void setJewel_purchase_id(String jewel_purchase_id) {
        this.jewel_purchase_id = jewel_purchase_id;
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

    @Override
    public String toString() {
        return "Inventory_jewelpurchaseitems{" +
                "remarks='" + remarks + '\'' +
                ", jewel_purchase_id='" + jewel_purchase_id + '\'' +
                '}';
    }
}
