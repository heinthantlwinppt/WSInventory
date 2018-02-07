package com.ppt.wsinventory.model.inventory_jewellery_model;

import java.util.Date;

/**
 * Created by User on 02/02/2018.
 */

public class Inventory_jewelpurchase {
    public static final String TABLE_INVENTORY_JEWELPURCHASE = "inventory_jewelpurchase";
    public static final String COLUMN_PURCHASE_NO = "purchase_no";
    public static final String COLUMN_PURCHASE_DATE = "purchase_date";
    public static final String COLUMN_REFERENCE_NO = "reference_no";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_PAID_AMOUNT = "paid_amount";
    public static final String COLUMN_DEDUCTION = "deduction";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_SUPPLIER_ID = "supplier_id";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_PURCHASE_NO,
            COLUMN_PURCHASE_DATE,
            COLUMN_REFERENCE_NO,
            COLUMN_AMOUNT,
            COLUMN_PAID_AMOUNT,
            COLUMN_DEDUCTION,
            COLUMN_REMARKS,
            COLUMN_IS_DELETE,
            COLUMN_TS,
            COLUMN_SUPPLIER_ID
    };

    private String purchase_no;
    private Date purchase_date;
    private String reference_no;
    private Double amount;
    private Double paid_amount;
    private Double deduction;
    private String remarks;
    private boolean is_delete;
    private Date ts;
    private int supplier_id;

    public String getPurchase_no() {
        return purchase_no;
    }

    public void setPurchase_no(String purchase_no) {
        this.purchase_no = purchase_no;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getReference_no() {
        return reference_no;
    }

    public void setReference_no(String reference_no) {
        this.reference_no = reference_no;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPaid_amount() {
        return paid_amount;
    }

    public void setPaid_amount(Double paid_amount) {
        this.paid_amount = paid_amount;
    }

    public Double getDeduction() {
        return deduction;
    }

    public void setDeduction(Double deduction) {
        this.deduction = deduction;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    @Override
    public String toString() {
        return "Inventory_jewelpurchase{" +
                "purchase_no='" + purchase_no + '\'' +
                ", reference_no='" + reference_no + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
