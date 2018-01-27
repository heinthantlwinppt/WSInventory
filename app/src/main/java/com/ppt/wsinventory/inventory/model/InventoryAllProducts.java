package com.ppt.wsinventory.inventory.model;

/**
 * Created by User on 25/01/2018.
 */

public class InventoryAllProducts {
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCTLENGTH_ID = "productlength_id";
    public static final String COLUMN_PRODUCTREDUCE_ID = "productreduce_id";
    public static final String COLUMN_PRODUCTSUBGROUPS_ID = "productsubgroups_id";
    public static final String COLUMN_PRODUCTGROUPS_ID = "productgroups_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_DESIGNNAME = "design_name";
    public static final String COLUMN_GNAME = "group_name";
    public static final String COLUMN_SUBGNAME = "subgroup_name";
    public static final String COLUMN_PLENGTH = "plength";
    public static final String COLUMN_REDUCE_G = "reduce_g";
    public static final String COLUMN_REDUCE_K = "reduce_k";
    public static final String COLUMN_REDUCE_P = "reduce_p";
    public static final String COLUMN_REDUCE_Y = "reduce_y";
    public static final String COLUMN_COST_REDUCE_K = "cost_reduce_k";
    public static final String COLUMN_COST_REDUCE_P = "cost_reduce_p";
    public static final String COLUMN_COST_REDUCE_Y = "cost_reduce_y";
    public static final String COLUMN_MINQTY = "minqty";
    public static final String COLUMN_MAXQTY = "maxqty";
    public static final String COLUMN_PHOTO_ID = "photo_id";
    public static final String COLUMN_PHOTO_NAME = "photo_name";
    public static final String COLUMN_PHOTO_TYPE = "photo_type";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_PRODUCT_ID,
            COLUMN_PRODUCTLENGTH_ID,
            COLUMN_PRODUCTREDUCE_ID,
            COLUMN_PRODUCTSUBGROUPS_ID,
            COLUMN_PRODUCTGROUPS_ID,
            COLUMN_PRODUCT_NAME,
            COLUMN_DESIGNNAME,
            COLUMN_GNAME,
            COLUMN_SUBGNAME,
            COLUMN_PLENGTH,
            COLUMN_REDUCE_G,
            COLUMN_REDUCE_K,
            COLUMN_REDUCE_P,
            COLUMN_REDUCE_Y,
            COLUMN_COST_REDUCE_K,
            COLUMN_COST_REDUCE_P,
            COLUMN_COST_REDUCE_Y,
            COLUMN_MINQTY,
            COLUMN_MAXQTY,
            COLUMN_PHOTO_ID,
            COLUMN_PHOTO_NAME,
            COLUMN_PHOTO_TYPE
    };

    private String product_id;
    private int productlength_id;
    private int productreduce_id;
    private int productsubgroups_id;
    private int productgroups_id;
    private String product_name;
    private String design_name;
    private String group_name;
    private String subgroup_name;
    private String plength;
    private int reduce_g;
    private int reduce_k;
    private int reduce_p;
    private int reduce_y;
    private int cost_reduce_k;
    private int cost_reduce_p;
    private int cost_reduce_y;
    private int minqty;
    private int maxqty;
    private String photo_id;
    private String photo_name;
    private String photo_type;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getProductlength_id() {
        return productlength_id;
    }

    public void setProductlength_id(int productlength_id) {
        this.productlength_id = productlength_id;
    }

    public int getProductreduce_id() {
        return productreduce_id;
    }

    public void setProductreduce_id(int productreduce_id) {
        this.productreduce_id = productreduce_id;
    }

    public int getProductsubgroups_id() {
        return productsubgroups_id;
    }

    public void setProductsubgroups_id(int productsubgroups_id) {
        this.productsubgroups_id = productsubgroups_id;
    }

    public int getProductgroups_id() {
        return productgroups_id;
    }

    public void setProductgroups_id(int productgroups_id) {
        this.productgroups_id = productgroups_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDesign_name() {
        return design_name;
    }

    public void setDesign_name(String design_name) {
        this.design_name = design_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getSubgroup_name() {
        return subgroup_name;
    }

    public void setSubgroup_name(String subgroup_name) {
        this.subgroup_name = subgroup_name;
    }

    public String getPlength() {
        return plength;
    }

    public void setPlength(String plength) {
        this.plength = plength;
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

    public int getMinqty() {
        return minqty;
    }

    public void setMinqty(int minqty) {
        this.minqty = minqty;
    }

    public int getMaxqty() {
        return maxqty;
    }

    public void setMaxqty(int maxqty) {
        this.maxqty = maxqty;
    }

    public String getPhoto() {
        return photo_id;
    }

    public void setPhoto(String photo) {
        this.photo_id = photo;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public String getPhoto_type() {
        return photo_type;
    }

    public void setPhoto_type(String photo_type) {
        this.photo_type = photo_type;
    }

    @Override
    public String toString() {
        return "InventoryAllProducts{" +
                "product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", design_name='" + design_name + '\'' +
                ", group_name='" + group_name + '\'' +
                ", subgroup_name='" + subgroup_name + '\'' +
                ", plength='" + plength + '\'' +
                ", photo_id='" + photo_id + '\'' +
                ", photo_name='" + photo_name + '\'' +
                ", photo_type='" + photo_type + '\'' +
                '}';
    }
}
