package com.ppt.wsinventory.model.inventory_jewellery_model;

/**
 * Created by User on 05/02/2018.
 */

public class Inventory_jeweltype {
    public static final String TABLE_INVENTORY_JEWELTYPE = "inventory_jeweltype";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_UNITTYPE = "unittype";
    public static final String COLUMN_PURCHASE_UNITTYPE = "purchase_unittype";
    public static final String COLUMN_ACTIVE = "active";

    public static final String[] COLUMN_ALL = new String[] {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_UNITTYPE,
            COLUMN_PURCHASE_UNITTYPE,
            COLUMN_ACTIVE
    };

    private int id;
    private String name;
    private String unittype;
    private String purchase_unittype;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getPurchase_unittype() {
        return purchase_unittype;
    }

    public void setPurchase_unittype(String purchase_unittype) {
        this.purchase_unittype = purchase_unittype;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Inventory_jeweltype{" +
                "name='" + name + '\'' +
                ", unittype='" + unittype + '\'' +
                ", purchase_unittype='" + purchase_unittype + '\'' +
                '}';
    }
}
