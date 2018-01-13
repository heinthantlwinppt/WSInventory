package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GoodsInventory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("k")
    @Expose
    private Integer k;
    @SerializedName("p")
    @Expose
    private Integer p;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("uom")
    @Expose
    private String uom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

}