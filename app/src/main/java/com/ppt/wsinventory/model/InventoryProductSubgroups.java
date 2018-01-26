package com.ppt.wsinventory.model;

/**
 * Created by User on 25/01/2018.
 */

public class InventoryProductSubgroups {
    public static final String TABLE_INVENTORY_PRODUCTSUBGROUPS = "inventory_productsubgroups";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_PGROUP_ID = "pgroup_id";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_ACTIVE,
            COLUMN_IS_DELETE,
            COLUMN_PGROUP_ID

    };

    private int id;
    private String name;
    private boolean active;
    private boolean is_delete;
    private int pgroup_id;

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

    public boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public int getPgroup_id() {
        return pgroup_id;
    }

    public void setPgroup_id(int pgroup_id) {
        this.pgroup_id = pgroup_id;
    }

    @Override
    public String toString() {
        return "InventoryProductSubgroups{" +
                "name='" + name + '\'' +
                '}';
    }
}
