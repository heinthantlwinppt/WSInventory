package com.ppt.wsinventory.model.inventory_jewellery_model;

/**
 * Created by User on 02/02/2018.
 */

public class Inventory_jewellength {

    public static final String TABLE_INVENTORY_JEWELLENGTH = "inventory_jewellength";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_JEWELSHAPE_ID = "jewelshape_id";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_ACTIVE,
            COLUMN_JEWELSHAPE_ID

    };

    private int id;
    private String name;
    private boolean active;
    private int jewelshape_id;

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

    public int getJewelshape_id() {
        return jewelshape_id;
    }

    public void setJewelshape_id(int jewelshape_id) {
        this.jewelshape_id = jewelshape_id;
    }

    @Override
    public String toString() {
        return "Inventory_jewellength{" +
                "name='" + name + '\'' +
                '}';
    }
}
