package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by User on 14/02/2018.
 */

public class Inventory_prodhdr  {

    public static final String TABLE_INVENTORY_PRODHDR = "inventory_prodhdr";
    public static final String COLUMN_PROD_NO="prod_no";
    public static final String COLUMN_PROD_DATE="prod_date";
    public static final String COLUMN_VOUCHER_NO="voucher_no";
    public static final String COLUMN_SAVE_COUNT="save_count";
    public static final String COLUMN_IS_FINISHED="is_finished";
    public static final String COLUMN_IS_CONFIRMED="is_confirmed";
    public static final String COLUMN_CONFIRMEDBY="confirmedby";
    public static final String COLUMN_IS_VOID="is_void";
    public static final String COLUMN_VOID_DATE="void_date";
    public static final String COLUMN_TS="ts";
    public static final String COLUMN_LOCATION_ID="location_id";
    public static final String COLUMN_SMITH_ID="smith_id";
    public static final String COLUMN_STAFF_ID="staff_id";
    public static final String COLUMN_DENSITY="density";
    public static final String COLUMN_DIFF_K="diff_k";
    public static final String COLUMN_DIFF_P="diff_p";
    public static final String COLUMN_DIFF_WEIGHT="diff_weight";
    public static final String COLUMN_DIFF_Y="diff_y";
    public static final String COLUMN_IS_PREJEWELOUT="is_prejewelout";
    public static final String COLUMN_IS_RECEIVED="is_received";
    public static final String COLUMN_JOBTYPE_ID="jobtype_id";
    public static final String COLUMN_PREJEWELOUT_DATE="prejewelout_date";
    public static final String COLUMN_PRODUCT_WEIGHT="product_weight";
    public static final String COLUMN_RECEIVED_DATE="received_date";
    public static final String COLUMN_REMNANTGOLD="remnantgold";
    public static final String COLUMN_REMNANTJEWEL="remnantjewel";



    public static final String[] COLUMN_ALL = new String[]
            {
                    COLUMN_PROD_NO,
                    COLUMN_PROD_DATE,
                    COLUMN_VOUCHER_NO,
                    COLUMN_SAVE_COUNT,
                    COLUMN_IS_FINISHED,
                    COLUMN_IS_CONFIRMED,
                    COLUMN_CONFIRMEDBY,
                    COLUMN_IS_VOID,
                    COLUMN_VOID_DATE,
                    COLUMN_TS,
                    COLUMN_LOCATION_ID,
                    COLUMN_SMITH_ID,
                    COLUMN_STAFF_ID,
                    COLUMN_DENSITY,
                    COLUMN_DIFF_K,
                    COLUMN_DIFF_P,
                    COLUMN_DIFF_WEIGHT,
                    COLUMN_DIFF_Y,
                    COLUMN_IS_PREJEWELOUT,
                    COLUMN_IS_RECEIVED,
                    COLUMN_JOBTYPE_ID,
                    COLUMN_PREJEWELOUT_DATE,
                    COLUMN_PRODUCT_WEIGHT,
                    COLUMN_RECEIVED_DATE,
                    COLUMN_REMNANTGOLD,
                    COLUMN_REMNANTJEWEL
            };

    private String prod_no;
    private Date prod_date;
    private String voucher_no;
    private int save_count;
    private boolean is_finished;
    private boolean is_confirmed;
    private String confirmedby;
    private boolean is_void;
    private Date void_date;
    private Date ts;
    private String location_id;
    private int smith_id;
    private String staff_id;
    private double density;
    private int diff_k;
    private int diff_p;
    private double diff_weight;
    private double diff_y;
    private boolean is_prejewelout;
    private boolean is_received;
    private int jobtype_id;
    private Date prejewelout_date;
    private double product_weight;
    private Date received_date;
    private double remnantgold;
    private double remnantjewel;

    public String getProd_no() {
        return prod_no;
    }

    public void setProd_no(String prod_no) {
        this.prod_no = prod_no;
    }

    public Date getProd_date() {
        return prod_date;
    }

    public void setProd_date(Date prod_date) {
        this.prod_date = prod_date;
    }

    public String getVoucher_no() {
        return voucher_no;
    }

    public void setVoucher_no(String voucher_no) {
        this.voucher_no = voucher_no;
    }

    public int getSave_count() {
        return save_count;
    }

    public void setSave_count(int save_count) {
        this.save_count = save_count;
    }

    public boolean Is_finished() {
        return is_finished;
    }

    public void setIs_finished(boolean is_finished) {
        this.is_finished = is_finished;
    }

    public boolean Is_confirmed() {
        return is_confirmed;
    }

    public void setIs_confirmed(boolean is_confirmed) {
        this.is_confirmed = is_confirmed;
    }

    public String getConfirmedby() {
        return confirmedby;
    }

    public void setConfirmedby(String confirmedby) {
        this.confirmedby = confirmedby;
    }

    public boolean Is_void() {
        return is_void;
    }

    public void setIs_void(boolean is_void) {
        this.is_void = is_void;
    }

    public Date getVoid_date() {
        return void_date;
    }

    public void setVoid_date(Date void_date) {
        this.void_date = void_date;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public int getSmith_id() {
        return smith_id;
    }

    public void setSmith_id(int smith_id) {
        this.smith_id = smith_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
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

    public boolean Is_prejewelout() {
        return is_prejewelout;
    }

    public void setIs_prejewelout(boolean is_prejewelout) {
        this.is_prejewelout = is_prejewelout;
    }

    public boolean Is_received() {
        return is_received;
    }

    public void setIs_received(boolean is_received) {
        this.is_received = is_received;
    }

    public int getJobtype_id() {
        return jobtype_id;
    }

    public void setJobtype_id(int jobtype_id) {
        this.jobtype_id = jobtype_id;
    }

    public Date getPrejewelout_date() {
        return prejewelout_date;
    }

    public void setPrejewelout_date(Date prejewelout_date) {
        this.prejewelout_date = prejewelout_date;
    }

    public double getProduct_weight() {
        return product_weight;
    }

    public void setProduct_weight(double product_weight) {
        this.product_weight = product_weight;
    }

    public Date getReceived_date() {
        return received_date;
    }

    public void setReceived_date(Date received_date) {
        this.received_date = received_date;
    }

    public double getRemnantgold() {
        return remnantgold;
    }

    public void setRemnantgold(double remnantgold) {
        this.remnantgold = remnantgold;
    }

    public double getRemnantjewel() {
        return remnantjewel;
    }

    public void setRemnantjewel(double remnantjewel) {
        this.remnantjewel = remnantjewel;
    }

    @Override
    public String toString() {
        return "Inventory_prodhdr{" +
                "prod_no='" + prod_no + '\'' +
                ", prod_date=" + prod_date +
                ", voucher_no='" + voucher_no + '\'' +
                ", is_received=" + is_received +
                '}';
    }
}
