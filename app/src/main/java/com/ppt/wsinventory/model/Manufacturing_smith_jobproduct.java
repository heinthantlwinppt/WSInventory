package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/15/2018.
 */

public class Manufacturing_smith_jobproduct {
    public static final String TABLE_MANUFACTURING_SMITH_JOBPRODUCT = "manufacturing_smith_joborder";
    public static final String COLUMN_PLENGTH = "plength";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_K = "k";
    public static final String COLUMN_P = "p";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_REDUCE_WEIGHT = "reduce_weight";
    public static final String COLUMN_REDUCE_K = "reduce_k";
    public static final String COLUMN_REDUCE_P = "reduce_p";
    public static final String COLUMN_REDUCE_Y = "reduce_y";
    public static final String COLUMN_TARGET_DATE = "target_date";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_WAGES = "wages";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_PRODUCTS_ID = "products_id";
    public static final String COLUMN_SMITH_JOBORDER_ID = "smith_joborder_id";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_ROW_NO = "row_no";
    public static final String COLUMN_TO_LOCATION_ID = "to_location_id";
    public static final String COLUMN_UOM_ID = "uom_id";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_PLENGTH,
            COLUMN_WEIGHT,
            COLUMN_K,
            COLUMN_P,
            COLUMN_Y,
            COLUMN_QTY,
            COLUMN_REDUCE_WEIGHT,
            COLUMN_REDUCE_K,
            COLUMN_REDUCE_P,
            COLUMN_REDUCE_Y,
            COLUMN_TARGET_DATE,
            COLUMN_START_DATE,
            COLUMN_END_DATE,
            COLUMN_WAGES,
            COLUMN_REMARKS,
            COLUMN_PRODUCTS_ID,
            COLUMN_SMITH_JOBORDER_ID,
            COLUMN_IS_DELETE,
            COLUMN_ROW_NO,
            COLUMN_TO_LOCATION_ID,
            COLUMN_UOM_ID
    };

    private String plength;
    private double weight;
    private int k;
    private int p;
    private int y;
    private double qty;
    private double reduce_weight;
    private int reduce_k;
    private int reduce_p;
    private double reduce_y;
    private Date target_date;
    private Date start_date;
    private Date end_date;
    private double wages;
    private String remarks;
    private String products_id;
    private String smith_joborder_id;
    private boolean is_delete;
    private int row_no;
    private String to_location_id;
    private String uom_id;

    public String getPlength() {
        return plength;
    }

    public void setPlength(String plength) {
        this.plength = plength;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getReduce_weight() {
        return reduce_weight;
    }

    public void setReduce_weight(double reduce_weight) {
        this.reduce_weight = reduce_weight;
    }

    public int getReduce_k() {
        return reduce_k;
    }

    public void setReduce_k(int reduce_k) {
        this.reduce_k = reduce_k;
    }

    public int getReduce_p() {
        return reduce_p;
    }

    public void setReduce_p(int reduce_p) {
        this.reduce_p = reduce_p;
    }

    public double getReduce_y() {
        return reduce_y;
    }

    public void setReduce_y(double reduce_y) {
        this.reduce_y = reduce_y;
    }

    public Date getTarget_date() {
        return target_date;
    }

    public void setTarget_date(Date target_date) {
        this.target_date = target_date;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public double getWages() {
        return wages;
    }

    public void setWages(double wages) {
        this.wages = wages;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProducts_id() {
        return products_id;
    }

    public void setProducts_id(String products_id) {
        this.products_id = products_id;
    }

    public String getSmith_joborder_id() {
        return smith_joborder_id;
    }

    public void setSmith_joborder_id(String smith_joborder_id) {
        this.smith_joborder_id = smith_joborder_id;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public int getRow_no() {
        return row_no;
    }

    public void setRow_no(int row_no) {
        this.row_no = row_no;
    }

    public String getTo_location_id() {
        return to_location_id;
    }

    public void setTo_location_id(String to_location_id) {
        this.to_location_id = to_location_id;
    }

    public String getUom_id() {
        return uom_id;
    }

    public void setUom_id(String uom_id) {
        this.uom_id = uom_id;
    }

    @Override
    public String toString() {
        return "Smith_jobproduct{" +
                "plength='" + plength + '\'' +
                ", remarks='" + remarks + '\'' +
                ", smith_joborder_id='" + smith_joborder_id + '\'' +
                ", to_location_id='" + to_location_id + '\'' +
                ", uom_id='" + uom_id + '\'' +
                '}';
    }
}
