package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UOM {

    @SerializedName("uom")
    @Expose
    private String uom;
    @SerializedName("baseqty")
    @Expose
    private String baseqty;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("product")
    @Expose
    private String product;

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getBaseqty() {
        return baseqty;
    }

    public void setBaseqty(String baseqty) {
        this.baseqty = baseqty;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

}