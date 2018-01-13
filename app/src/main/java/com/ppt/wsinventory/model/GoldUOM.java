package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoldUOM {

    @SerializedName("uom")
    @Expose
    private String uom;
    @SerializedName("baseqty")
    @Expose
    private String baseqty;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("gold")
    @Expose
    private Integer gold;

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

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

}