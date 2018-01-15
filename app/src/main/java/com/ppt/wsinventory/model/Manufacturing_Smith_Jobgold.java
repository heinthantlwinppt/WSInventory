package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/15/2018.
 */

public class Manufacturing_Smith_Jobgold {
    public static final String TABLE_MANUFACTURING_SMITH_JOBGOLD = "manufacturing_smith";
    public static final String COLUMN_SMITH_JOBORDER_ID = "smith_joborder_id";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_QTY = "qty";
    public static final String COLUMN_GOLDSAVING = "goldsaving";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_ROW_NO = "row_no";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_GOLD_ID = "gold_id";
    public static final String COLUMN_UOM_ID = "uom_id";
    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_SMITH_JOBORDER_ID,
            COLUMN_WEIGHT,
            COLUMN_QTY,
            COLUMN_GOLDSAVING,
            COLUMN_REMARKS,
            COLUMN_ROW_NO,
            COLUMN_IS_DELETE,
            COLUMN_GOLD_ID,
            COLUMN_UOM_ID

    };

    private String smith_joborder_id;
    private Double weight;
    private Double qty;
    private Double goldsaving;
    private String remarks;
    private int row_no;
    private Boolean is_delete;
    private int gold_id;
    private String uom_id;

    public String getSmith_joborder_id() {
        return smith_joborder_id;
    }

    public void setSmith_joborder_id(String smith_joborder_id) {
        this.smith_joborder_id = smith_joborder_id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getGoldsaving() {
        return goldsaving;
    }

    public void setGoldsaving(Double goldsaving) {
        this.goldsaving = goldsaving;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getRow_no() {
        return row_no;
    }

    public void setRow_no(int row_no) {
        this.row_no = row_no;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public int getGold_id() {
        return gold_id;
    }

    public void setGold_id(int gold_id) {
        this.gold_id = gold_id;
    }

    public String getUom_id() {
        return uom_id;
    }

    public void setUom_id(String uom_id) {
        this.uom_id = uom_id;
    }

    @Override
    public String toString() {
        return "Manufacturing_Smith_Jobgold{" +
                "smith_joborder_id='" + smith_joborder_id + '\'' +
                ", weight=" + weight +
                ", qty=" + qty +
                ", goldsaving=" + goldsaving +
                ", remarks='" + remarks + '\'' +
                ", row_no=" + row_no +
                ", is_delete=" + is_delete +
                ", gold_id=" + gold_id +
                ", uom_id='" + uom_id + '\'' +
                '}';
    }
}
