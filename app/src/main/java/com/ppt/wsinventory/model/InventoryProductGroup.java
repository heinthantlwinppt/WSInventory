package com.ppt.wsinventory.model;

/**
 * Created by ThinkCentre on 1/11/2018.
 */

public class InventoryProductGroup {
    public static final String TABLE_PRODUCTGROUP = "inventory_productgroups";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ACTIVE = "active";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_NAME ,
            COLUMN_ACTIVE ,
    };

    private long id ;
    private String name ;
    private boolean active ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "InventoryProductGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }
}
