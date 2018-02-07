package com.ppt.wsinventory.model.inventory_jewellery_model;

/**
 * Created by User on 07/02/2018.
 */

public class Inventory_suppliergroup {
    public static final String TABLE_INVENTORY_SUPPLIERGROUP= "inventory_suppliergroup";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ACTIVE = "active";

    public static final String [] COLUMN_ALL = new String[] {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_ACTIVE
    };

    private int id;
    private String name;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Inventory_suppliergroup{" +
                "name='" + name + '\'' +
                '}';
    }
}
