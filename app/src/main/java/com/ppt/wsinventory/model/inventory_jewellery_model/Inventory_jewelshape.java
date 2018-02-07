package com.ppt.wsinventory.model.inventory_jewellery_model;

/**
 * Created by User on 05/02/2018.
 */

public class Inventory_jewelshape {

    public static final String TABLE_INVENTORY_JEWELSHAPE = "inventory_jewelshape";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_JEWELTYPE_ID = "jeweltype_id";
    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_ACTIVE,
            COLUMN_JEWELTYPE_ID
    };

    private int id;
    private String name;
    private boolean active;
    private int jeweltype_id;

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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getJeweltype_id() {
        return jeweltype_id;
    }

    public void setJeweltype_id(int jeweltype_id) {
        this.jeweltype_id = jeweltype_id;
    }

    @Override
    public String toString() {
        return "Inventory_jewelshape{" +
                "name='" + name + '\'' +
                '}';
    }
}
