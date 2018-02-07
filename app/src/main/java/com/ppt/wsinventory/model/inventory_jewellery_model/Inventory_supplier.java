package com.ppt.wsinventory.model.inventory_jewellery_model;

/**
 * Created by User on 07/02/2018.
 */

public class Inventory_supplier {
    public static final String TABLE_INVENTORY_SUPPLIER = "inventory_supplier";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_SUPPLIER_GROUP_ID = "supplier_group_id";

    public static final String [] COLUMN_ALL = new String[]{
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_ADDRESS,
            COLUMN_PHONE,
            COLUMN_ACTIVE,
            COLUMN_SUPPLIER_GROUP_ID
    };

    private int id;
    private String name;
    private String address;
    private String phone;
    private boolean active;
    private int supplier_group_id;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSupplier_group_id() {
        return supplier_group_id;
    }

    public void setSupplier_group_id(int supplier_group_id) {
        this.supplier_group_id = supplier_group_id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Inventory_supplier{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
