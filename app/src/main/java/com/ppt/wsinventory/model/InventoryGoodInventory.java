package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/11/2018.
 */

public class InventoryGoodInventory {
    public static final String TABLE_INVENTORY_GOODSINVENTORY = "inventory_goodsinventory";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_K = "k";
    public static final String COLUMN_P = "p";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_LOCATION_ID = "location_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_UOM_ID = "uom_id";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_QTY ,
            COLUMN_WEIGHT ,
            COLUMN_K ,
            COLUMN_P ,
            COLUMN_Y ,
            COLUMN_IS_DELETE ,
            COLUMN_TS ,
            COLUMN_LOCATION_ID ,
            COLUMN_PRODUCT_ID,
            COLUMN_UOM_ID

    };

    private long id ;
    private double qty ;
    private double weight ;
    private int k ;
    private int p ;
    private double y ;
    private boolean is_delete ;
    private Date ts ;
    private String location_id ;
    private String product_id ;
    private String uom_id ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUom_id() {
        return uom_id;
    }

    public void setUom_id(String uom_id) {
        this.uom_id = uom_id;
    }

    @Override
    public String toString() {
        return "InventoryGoodInventory{" +
                "id=" + id +
                ", qty=" + qty +
                ", weight=" + weight +
                ", k=" + k +
                ", p=" + p +
                ", y=" + y +
                ", is_delete=" + is_delete +
                ", ts=" + ts +
                ", location_id='" + location_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", uom_id='" + uom_id + '\'' +
                '}';
    }
}
