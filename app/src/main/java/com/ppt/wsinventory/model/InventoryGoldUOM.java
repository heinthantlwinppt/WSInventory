package com.ppt.wsinventory.model;

/**
 * Created by ThinkCentre on 1/11/2018.
 */

public class InventoryGoldUOM {
    public static final String TABLE_INVENTROY_GOLDUOM = "inventory_golduom";
    public static final String COLUMN_UOM = "uom";
    public static final String COLUMN_BASEQUANLITY  = "baseqty";
    public static final String COLUMN_GOLD_ID = "gold_id";
    public static final String COLUMN_ACTIVE  = "active";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_UOM ,
            COLUMN_BASEQUANLITY ,
            COLUMN_GOLD_ID ,
            COLUMN_ACTIVE ,
    };

    private String uom ;
    private double baseqty ;
    private int gold_id ;
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

    public int getGold_id() {
        return gold_id;
    }

    public void setGold_id(int gold_id) {
        this.gold_id = gold_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "InventoryGoldUOM{" +
                "uom='" + uom + '\'' +
                ", baseqty=" + baseqty +
                ", gold_id=" + gold_id +
                ", active=" + active +
                '}';
    }
}
