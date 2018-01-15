package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/15/2018.
 */

public class Manufacturing_smith_joborder {
    public static final String TABLE_MANUFACTURING_SMITH_JOBORDER = "manufacturing_smith_joborder";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JOBORDER_NO = "joborder_no";
    public static final String COLUMN_JOBORDER_DATE = "joborder_date";
    public static final String COLUMN_PREJEWELOUT = "prejewelout";
    public static final String COLUMN_PREJEWELOUT_DATE = "prejewelout_date";
    public static final String COLUMN_DATE_TARGET = "date_target";
    public static final String COLUMN_DATE_START = "date_start";
    public static final String COLUMN_DATE_END = "date_end";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_JOBORDER_TYPE_ID = "joborder_type_id";
    public static final String COLUMN_JOBSTATUS_ID = "jobstatus_id";
    public static final String COLUMN_SMITH_ID = "smith_id";
    public static final String COLUMN_DENSITY = "density";
    public static final String COLUMN_DIFF_K = "diff_k";
    public static final String COLUMN_DIFF_P = "diff_p";
    public static final String COLUMN_DIFF_WEIGHT = "diff_weight";
    public static final String COLUMN_DIFF_Y = "diff_y";
    public static final String COLUMN_PRINT_COUNT = "print_count";
    public static final String COLUMN_PRODUCT_WEIGHT = "product_weight";
    public static final String COLUMN_REMAIN_GOLD = "remain_gold";
    public static final String COLUMN_REMAIN_JEWEL = "remain_jewel";
    public static final String COLUMN_SAVE_COUNT = "save_count";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID,
            COLUMN_JOBORDER_NO,
            COLUMN_JOBORDER_DATE,
            COLUMN_PREJEWELOUT,
            COLUMN_PREJEWELOUT_DATE,
            COLUMN_DATE_TARGET,
            COLUMN_DATE_START,
            COLUMN_DATE_END,
            COLUMN_REMARKS,
            COLUMN_ACTIVE,
            COLUMN_IS_DELETE,
            COLUMN_TS,
            COLUMN_JOBORDER_TYPE_ID,
            COLUMN_JOBSTATUS_ID,
            COLUMN_SMITH_ID,
            COLUMN_DENSITY,
            COLUMN_DIFF_K,
            COLUMN_DIFF_P,
            COLUMN_DIFF_WEIGHT,
            COLUMN_DIFF_Y,
            COLUMN_PRINT_COUNT,
            COLUMN_PRODUCT_WEIGHT,
            COLUMN_REMAIN_GOLD,
            COLUMN_REMAIN_JEWEL,
            COLUMN_SAVE_COUNT

    };

    private String id;
    private String joborder_no;
    private Date joborder_date;
    private boolean prejewelout;
    private Date prejewelout_date;
    private Date date_target;
    private String remarks;
    private Date date_start;
    private Date date_end;
    private boolean active;
    private boolean is_delete;
    private Date ts;
    private int joborder_type_id;
    private int jobstatus_id;
    private int smith_id;
    private double density;
    private int diff_k;
    private int diff_p;
    private double diff_weight;
    private double diff_y;
    private int print_count;
    private double product_weight;
    private double remain_gold;
    private double remain_jewel;
    private int save_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoborder_no() {
        return joborder_no;
    }

    public void setJoborder_no(String joborder_no) {
        this.joborder_no = joborder_no;
    }

    public Date getJoborder_date() {
        return joborder_date;
    }

    public void setJoborder_date(Date joborder_date) {
        this.joborder_date = joborder_date;
    }

    public boolean isPrejewelout() {
        return prejewelout;
    }

    public void setPrejewelout(boolean prejewelout) {
        this.prejewelout = prejewelout;
    }

    public Date getPrejewelout_date() {
        return prejewelout_date;
    }

    public void setPrejewelout_date(Date prejewelout_date) {
        this.prejewelout_date = prejewelout_date;
    }

    public Date getDate_target() {
        return date_target;
    }

    public void setDate_target(Date date_target) {
        this.date_target = date_target;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isIs_delete() {
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

    public int getJoborder_type_id() {
        return joborder_type_id;
    }

    public void setJoborder_type_id(int joborder_type_id) {
        this.joborder_type_id = joborder_type_id;
    }

    public int getJobstatus_id() {
        return jobstatus_id;
    }

    public void setJobstatus_id(int jobstatus_id) {
        this.jobstatus_id = jobstatus_id;
    }

    public int getSmith_id() {
        return smith_id;
    }

    public void setSmith_id(int smith_id) {
        this.smith_id = smith_id;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public int getDiff_k() {
        return diff_k;
    }

    public void setDiff_k(int diff_k) {
        this.diff_k = diff_k;
    }

    public int getDiff_p() {
        return diff_p;
    }

    public void setDiff_p(int diff_p) {
        this.diff_p = diff_p;
    }

    public double getDiff_weight() {
        return diff_weight;
    }

    public void setDiff_weight(double diff_weight) {
        this.diff_weight = diff_weight;
    }

    public double getDiff_y() {
        return diff_y;
    }

    public void setDiff_y(double diff_y) {
        this.diff_y = diff_y;
    }

    public int getPrint_count() {
        return print_count;
    }

    public void setPrint_count(int print_count) {
        this.print_count = print_count;
    }

    public double getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(double product_weight) {
        this.product_weight = product_weight;
    }

    public double getRemain_gold() {
        return remain_gold;
    }

    public void setRemain_gold(double remain_gold) {
        this.remain_gold = remain_gold;
    }

    public double getRemain_jewel() {
        return remain_jewel;
    }

    public void setRemain_jewel(double remain_jewel) {
        this.remain_jewel = remain_jewel;
    }

    public int getSave_count() {
        return save_count;
    }

    public void setSave_count(int save_count) {
        this.save_count = save_count;
    }

    @Override
    public String toString() {
        return "Manufacturing_smith_joborder{" +
                "id='" + id + '\'' +
                ", joborder_no='" + joborder_no + '\'' +
                ", joborder_date=" + joborder_date +
                ", prejewelout=" + prejewelout +
                ", prejewelout_date=" + prejewelout_date +
                ", date_target=" + date_target +
                ", remarks='" + remarks + '\'' +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", active=" + active +
                ", is_delete=" + is_delete +
                ", ts=" + ts +
                ", joborder_type_id=" + joborder_type_id +
                ", jobstatus_id=" + jobstatus_id +
                ", smith_id=" + smith_id +
                ", density=" + density +
                ", diff_k=" + diff_k +
                ", diff_p=" + diff_p +
                ", diff_weight=" + diff_weight +
                ", print_count=" + print_count +
                ", product_weight=" + product_weight +
                ", remain_gold=" + remain_gold +
                ", remain_jewel=" + remain_jewel +
                ", save_count=" + save_count +
                '}';
    }
}
