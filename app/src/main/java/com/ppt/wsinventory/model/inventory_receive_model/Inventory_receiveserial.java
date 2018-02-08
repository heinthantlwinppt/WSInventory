package com.ppt.wsinventory.model.inventory_receive_model;

import java.util.Date;

/**
 * Created by User on 08/02/2018.
 */

public class Inventory_receiveserial {
    public static final String TABLE_INVENTORY_RECEIVESERIAL = "inventory_receiveserial";
    public static final String COLUMN_PRODUCTSERIAL_ID = "productserial_id";
    public static final String COLUMN_LINENO = "lineno";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_TS = "ts ";

    public static final String[] COLUMN_ALL = new String[] {

            COLUMN_PRODUCTSERIAL_ID,
            COLUMN_LINENO,
            COLUMN_IS_DELETE,
            COLUMN_TS
    };

    private String productserial_id;
    private int lineno;
    private boolean is_delete;
    private Date ts;

    public String getProductserial_id() {
        return productserial_id;
    }

    public void setProductserial_id(String productserial_id) {
        this.productserial_id = productserial_id;
    }

    public int getLineno() {
        return lineno;
    }

    public void setLineno(int lineno) {
        this.lineno = lineno;
    }

    public boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Inventory_receiveserial{" +
                "productserial_id='" + productserial_id + '\'' +
                '}';
    }
}
