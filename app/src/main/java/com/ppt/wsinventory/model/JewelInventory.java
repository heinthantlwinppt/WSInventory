package com.ppt.wsinventory.model;

/**
 * Created by User on 07/02/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class JewelInventory {

    @SerializedName("jewellength")
    @Expose
    private Integer jewellength;
    @SerializedName("qty")
    @Expose
    private Double qty;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("jeweltype")
    @Expose
    private Integer jeweltype;
    @SerializedName("jewelshape")
    @Expose
    private Integer jewelshape;

    public Integer getJewellength() {
        return jewellength;
    }

    public void setJewellength(Integer jewellength) {
        this.jewellength = jewellength;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Integer getJeweltype() {
        return jeweltype;
    }

    public void setJeweltype(Integer jeweltype) {
        this.jeweltype = jeweltype;
    }

    public Integer getJewelshape() {
        return jewelshape;
    }

    public void setJewelshape(Integer jewelshape) {
        this.jewelshape = jewelshape;
    }

}
