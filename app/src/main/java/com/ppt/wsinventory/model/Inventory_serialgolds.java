package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/16/2018.
 */

public class Inventory_serialgolds {
    public static final String TABLE_INVENTORY_SERIALGOLDS= "inventory_serialgolds";
    public static final String COLUMN_SERIAL_ID = "serial_id";
    public static final String COLUMN_REDUCE_WEIGHT = "reduce_weight";
    public static final String COLUMN_REDUCE_K = "reduce_k";
    public static final String COLUMN_REDUCE_P = "reduce_p";
    public static final String COLUMN_REDUCE_Y = "reduce_y";
    public static final String COLUMN_PRODUCTION_FEE = "production_fee";
    public static final String COLUMN_COST_REDUCE_WEIGHT = "cost_reduce_weight";
    public static final String COLUMN_COST_REDUCE_K = "cost_reduce_k";
    public static final String COLUMN_COST_REDUCE_P = "cost_reduce_p";
    public static final String COLUMN_COST_REDUCE_Y = "cost_reduce_y";
    public static final String COLUMN_COST_PRODUCTIONFEE = "cost_productionfee";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_GOLD_ID = "gold_id";
    public static final String COLUMN_TS = "ts";
    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_SERIAL_ID,
            COLUMN_REDUCE_WEIGHT,
            COLUMN_REDUCE_K,
            COLUMN_REDUCE_P,
            COLUMN_REDUCE_Y,
            COLUMN_PRODUCTION_FEE,
            COLUMN_COST_REDUCE_WEIGHT,
            COLUMN_COST_REDUCE_K,
            COLUMN_COST_REDUCE_P,
            COLUMN_COST_PRODUCTIONFEE,
            COLUMN_IS_DELETE,
            COLUMN_GOLD_ID,
            COLUMN_COST_REDUCE_Y,
            COLUMN_TS

    };

    private String serial_id;
    private double reduce_weight;
    private int reduce_k;
    private int reduce_p;
    private double reduce_y;
    private double production_fee;
    private double cost_reduce_weight;
    private int cost_reduce_k;
    private int cost_reduce_p;
    private double cost_reduce_y;
    private double cost_productionfee;
    private boolean is_delete;
    private int gold_id;
    private Date ts;

    public String getSerial_id() {
        return serial_id;
    }

    public void setSerial_id(String serial_id) {
        this.serial_id = serial_id;
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

    public double getProduction_fee() {
        return production_fee;
    }

    public void setProduction_fee(double production_fee) {
        this.production_fee = production_fee;
    }

    public double getCost_reduce_weight() {
        return cost_reduce_weight;
    }

    public void setCost_reduce_weight(double cost_reduce_weight) {
        this.cost_reduce_weight = cost_reduce_weight;
    }

    public int getCost_reduce_k() {
        return cost_reduce_k;
    }

    public void setCost_reduce_k(int cost_reduce_k) {
        this.cost_reduce_k = cost_reduce_k;
    }

    public int getCost_reduce_p() {
        return cost_reduce_p;
    }

    public void setCost_reduce_p(int cost_reduce_p) {
        this.cost_reduce_p = cost_reduce_p;
    }

    public double getCost_reduce_y() {
        return cost_reduce_y;
    }

    public void setCost_reduce_y(double cost_reduce_y) {
        this.cost_reduce_y = cost_reduce_y;
    }

    public double getCost_productionfee() {
        return cost_productionfee;
    }

    public void setCost_productionfee(double cost_productionfee) {
        this.cost_productionfee = cost_productionfee;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public int getGold_id() {
        return gold_id;
    }

    public void setGold_id(int gold_id) {
        this.gold_id = gold_id;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "Inventory_serialgolds{" +
                "serial_id='" + serial_id + '\'' +
                ", reduce_weight=" + reduce_weight +
                ", reduce_k=" + reduce_k +
                ", reduce_p=" + reduce_p +
                ", reduce_y=" + reduce_y +
                ", production_fee=" + production_fee +
                ", cost_reduce_weight=" + cost_reduce_weight +
                ", cost_reduce_k=" + cost_reduce_k +
                ", cost_reduce_p=" + cost_reduce_p +
                ", cost_reduce_y=" + cost_reduce_y +
                ", cost_productionfee=" + cost_productionfee +
                ", is_delete=" + is_delete +
                ", gold_id=" + gold_id +
                ", ts=" + ts +
                '}';
    }
}
