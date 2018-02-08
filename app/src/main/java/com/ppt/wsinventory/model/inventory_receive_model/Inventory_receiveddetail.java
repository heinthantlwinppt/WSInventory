package com.ppt.wsinventory.model.inventory_receive_model;

import java.util.Date;

/**
 * Created by User on 08/02/2018.
 */

public class Inventory_receiveddetail {

    public static final String TABLE_INVENTORY_RECEIVEDDETAIL = "inventory_receiveddetail";
    public static final String COLUMN_LINENO = "lineno";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_K = "k";
    public static final String COLUMN_P = "p";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_DENSITY = "density";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_BIN_ID = "bin_id";
    public static final String COLUMN_PALLET_ID = "pallet_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_RECEIVEDHDR_ID = "receivedhdr_id";
    public static final String COLUMN_TOLOCATION_ID = "tolocation_id";
    public static final String COLUMN_UOM_ID = "uom_id";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_LINENO,
            COLUMN_QTY,
            COLUMN_WEIGHT,
            COLUMN_K,
            COLUMN_P,
            COLUMN_Y,
            COLUMN_DENSITY,
            COLUMN_REMARKS,
            COLUMN_IS_DELETE,
            COLUMN_TS,
            COLUMN_BIN_ID,
            COLUMN_PALLET_ID,
            COLUMN_PRODUCT_ID,
            COLUMN_RECEIVEDHDR_ID,
            COLUMN_TOLOCATION_ID,
            COLUMN_UOM_ID

    };

    private int lineno;
    private double qty;
    private double weight;
    private int k;
    private int p;
    private double y;
    private double density;
    private String remarks;
    private boolean is_delete;
    private Date ts;
    private String bin_id;
    private String pallet_id;
    private String product_id;
    private String receivedhdr_id;
    private String tolocation_id;
    private String uom_id;

    public int getLineno() {
        return lineno;
    }

    public void setLineno(int lineno) {
        this.lineno = lineno;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
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

    public double getY() {
        return y;
    }

    public void setY(double y) {
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

    public boolean isIs_delete() {
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

    public String getBin_id() {
        return bin_id;
    }

    public void setBin_id(String bin_id) {
        this.bin_id = bin_id;
    }

    public String getPallet_id() {
        return pallet_id;
    }

    public void setPallet_id(String pallet_id) {
        this.pallet_id = pallet_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getReceivedhdr_id() {
        return receivedhdr_id;
    }

    public void setReceivedhdr_id(String receivedhdr_id) {
        this.receivedhdr_id = receivedhdr_id;
    }

    public String getTolocation_id() {
        return tolocation_id;
    }

    public void setTolocation_id(String tolocation_id) {
        this.tolocation_id = tolocation_id;
    }

    public String getUom_id() {
        return uom_id;
    }

    public void setUom_id(String uom_id) {
        this.uom_id = uom_id;
    }

    @Override
    public String toString() {
        return "Inventory_receiveddetail{" +
                "remarks='" + remarks + '\'' +
                ", bin_id='" + bin_id + '\'' +
                ", pallet_id='" + pallet_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", receivedhdr_id='" + receivedhdr_id + '\'' +
                ", tolocation_id='" + tolocation_id + '\'' +
                ", uom_id='" + uom_id + '\'' +
                '}';
    }
}
