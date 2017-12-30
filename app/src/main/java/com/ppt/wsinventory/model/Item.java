package com.ppt.wsinventory.model;

/**
 * Created by User on 28/12/2017.
 */

public class Item {

    public static final String TABLE_NAME = "Items";
    public static final String COLUMN_ITEMNAME = "ItemName";
    public static final String COLUMN_ITEMTYPE = "ItemType";

    public static final String[] COLUMN_ALL = new String[]{

             COLUMN_ITEMNAME,
             COLUMN_ITEMTYPE

    };

    private long id;
    private String ItemName;
    private String ItemType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemName='" + ItemName + '\'' +
                ", ItemType='" + ItemType + '\'' +
                '}';
    }
}
