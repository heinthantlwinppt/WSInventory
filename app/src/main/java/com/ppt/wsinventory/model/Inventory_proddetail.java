package com.ppt.wsinventory.model;

import android.app.Fragment;

import java.util.Date;

/**
 * Created by User on 14/02/2018.
 */

public class Inventory_proddetail  {

    public static final String TABLE_INVENTORY_PRODDETAIL = "inventory_proddetail";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_RECEIVED_QTY = "received_qty";
    public static final String COLUMN_WEIGHT  = "weight";
    public static final String COLUMN_K = "k";
    public static final String COLUMN_P = "p";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_DENSITY = "density";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_PRODHDR_ID = "prodhdr_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_TOLOCATION_ID = "tolocation_id";
    public static final String COLUMN_UOM_ID = "uom_id";
    public static final String COLUMN_TS = "ts";

    public static final String[] COLUNM_ALL = new String[]
            {
                    COLUMN_ID,
                    COLUMN_QTY,
                    COLUMN_RECEIVED_QTY,
                    COLUMN_WEIGHT,
                    COLUMN_K,
                    COLUMN_P,
                    COLUMN_Y,
                    COLUMN_DENSITY,
                    COLUMN_REMARKS,
                    COLUMN_IS_DELETE,
                    COLUMN_PRODHDR_ID,
                    COLUMN_PRODUCT_ID,
                    COLUMN_TOLOCATION_ID,
                    COLUMN_UOM_ID,
                    COLUMN_TS
            };

    private int id;
    private double qty;
    private double received_qty;
    private double weight;
    private int k;
    private int p;
    private int y;
    private double density;
    private String remarks;
    private Boolean is_delete;
    private String prodhdr_id;
    private String product_id;
    private String tolocation_id;
    private String uon_id;
    private Date ts;

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

    public double getReceived_qty() {
        return received_qty;
    }

    public void setReceived_qty(double received_qty) {
        this.received_qty = received_qty;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public String getProdhdr_id() {
        return prodhdr_id;
    }

    public void setProdhdr_id(String prodhdr_id) {
        this.prodhdr_id = prodhdr_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getTolocation_id() {
        return tolocation_id;
    }

    public void setTolocation_id(String tolocation_id) {
        this.tolocation_id = tolocation_id;
    }

    public String getUon_id() {
        return uon_id;
    }

    public void setUon_id(String uon_id) {
        this.uon_id = uon_id;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Inventory_proddetail{" +
                "remarks='" + remarks + '\'' +
                ", prodhdr_id='" + prodhdr_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", tolocation_id='" + tolocation_id + '\'' +
                ", uon_id='" + uon_id + '\'' +
                '}';
    }
}
