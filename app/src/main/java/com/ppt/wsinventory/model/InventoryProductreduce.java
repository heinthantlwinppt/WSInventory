package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by User on 25/01/2018.
 */

public class InventoryProductreduce {
    public static final String TABLE_INVENTORY_PRODUCTREDUCE = "inventory_productreduce";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_REDUCE_G = "reduce_g";
    public static final String COLUMN_REDUCE_K = "reduce_k";
    public static final String COLUMN_REDUCE_P = "reduce_p";
    public static final String COLUMN_REDUCE_Y = "reduce_y";
    public static final String COLUMN_PRODUCTION_FEE = "production_fee";
    public static final String COLUMN_COST_REDUCE_K = "cost_reduce_k";
    public static final String COLUMN_COST_REDUCE_P = "cost_reduce_p";
    public static final String COLUMN_COST_REDUCE_Y = "cost_reduce_y";
    public static final String COLUMN_COST_PRODUCTION_FEE = "cost_production_fee";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_GOLD_ID = "gold_id";
    public static final String COLUMN_PLENGTH_ID = "plength_id";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_ID,
            COLUMN_REDUCE_G,
            COLUMN_REDUCE_K,
            COLUMN_REDUCE_P,
            COLUMN_REDUCE_Y,
            COLUMN_PRODUCTION_FEE,
            COLUMN_COST_REDUCE_K,
            COLUMN_COST_REDUCE_P,
            COLUMN_COST_REDUCE_Y,
            COLUMN_COST_PRODUCTION_FEE,
            COLUMN_REMARKS,
            COLUMN_ACTIVE,
            COLUMN_IS_DELETE,
            COLUMN_GOLD_ID,
            COLUMN_PLENGTH_ID

    };


    private int id ;
    private int reduce_g ;
    private int reduce_k ;
    private int reduce_p ;
    private int reduce_y ;
    private int production_fee ;
    private int cost_reduce_k ;
    private int cost_reduce_p ;
    private int cost_reduce_y ;
    private int cost_production_fee ;
    private String remarks ;
    private boolean active ;
    private boolean is_delete ;
    private int gold_id ;
    private int plength_id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReduce_g() {
        return reduce_g;
    }

    public void setReduce_g(int reduce_g) {
        this.reduce_g = reduce_g;
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

    public int getReduce_y() {
        return reduce_y;
    }

    public void setReduce_y(int reduce_y) {
        this.reduce_y = reduce_y;
    }

    public int getProduction_fee() {
        return production_fee;
    }

    public void setProduction_fee(int production_fee) {
        this.production_fee = production_fee;
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

    public int getCost_reduce_y() {
        return cost_reduce_y;
    }

    public void setCost_reduce_y(int cost_reduce_y) {
        this.cost_reduce_y = cost_reduce_y;
    }

    public int getCost_production_fee() {
        return cost_production_fee;
    }

    public void setCost_production_fee(int cost_production_fee) {
        this.cost_production_fee = cost_production_fee;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getIs_delete() {
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

    public int getPlength_id() {
        return plength_id;
    }

    public void setPlength_id(int plength_id) {
        this.plength_id = plength_id;
    }

    @Override
    public String toString() {
        return "InventoryProductreduce{" +
                "remarks='" + remarks + '\'' +
                '}';
    }
}
