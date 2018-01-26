package com.ppt.wsinventory.model;

/**
 * Created by User on 25/01/2018.
 */

public class InventoryProductlength {

    public static final String TABLE_INVENTORY_PRODUCTLENGTH = "inventory_productlength";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PLENGTH = "plength";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_PSGROUP_ID = "psgroup_id";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID,
            COLUMN_PLENGTH,
            COLUMN_ACTIVE,
            COLUMN_IS_DELETE,
            COLUMN_PSGROUP_ID
    } ;

    private int id;
    private String plength;
    private boolean active;
    private boolean is_delete;
    private int psgroup_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlength() {
        return plength;
    }

    public void setPlength(String plength) {
        this.plength = plength;
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

    public int getPsgroup_id() {
        return psgroup_id;
    }

    public void setPsgroup_id(int psgroup_id) {
        this.psgroup_id = psgroup_id;
    }

    @Override
    public String toString() {
        return "InventoryProductlength{" +
                "plength='" + plength + '\'' +
                '}';
    }
}
