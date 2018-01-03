package com.ppt.wsinventory.model;

/**
 * Created by User on 03/01/2018.
 */

public class InventoryGold {

    public static final String TABLE_NAME = "inventory_gold";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GOLDTYPE = "goldtype";
    public static final String COLUMN_SALEPRICE = "saleprice";
    public static final String COLUMN_PURCHASEPRICE = "purchaseprice";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_TAX1 = "tax1";
    public static final String COLUMN_TAX2 = "tax2";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_CODE = "code";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_NAME ,
            COLUMN_GOLDTYPE ,
            COLUMN_SALEPRICE ,
            COLUMN_PURCHASEPRICE ,
            COLUMN_COST ,
            COLUMN_TAX1 ,
            COLUMN_TAX2 ,
            COLUMN_ACTIVE ,
            COLUMN_CODE

    };

    private int id ;
    private String name ;
    private int goldtype ;
    private double saleprice ;
    private double purchaseprice ;
    private double cost ;
    private double tax1 ;
    private double tax2 ;
    private boolean active ;
    private String code ;

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

    public int getGoldtype() {
        return goldtype;
    }

    public void setGoldtype(int goldtype) {
        this.goldtype = goldtype;
    }

    public double getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(double saleprice) {
        this.saleprice = saleprice;
    }

    public double getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(double purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTax1() {
        return tax1;
    }

    public void setTax1(double tax1) {
        this.tax1 = tax1;
    }

    public double getTax2() {
        return tax2;
    }

    public void setTax2(double tax2) {
        this.tax2 = tax2;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "InventoryGold{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
