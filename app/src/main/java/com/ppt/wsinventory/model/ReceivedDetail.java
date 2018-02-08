package com.ppt.wsinventory.model;

/**
 * Created by User on 08/02/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ReceivedDetail {

    @SerializedName("lineno")
    @Expose
    private Integer lineno;
    @SerializedName("qty")
    @Expose
    private double qty;
    @SerializedName("weight")
    @Expose
    private double weight;
    @SerializedName("k")
    @Expose
    private Integer k;
    @SerializedName("p")
    @Expose
    private Integer p;
    @SerializedName("y")
    @Expose
    private double y;
    @SerializedName("density")
    @Expose
    private double density;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("receivedhdr")
    @Expose
    private String receivedhdr;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("pallet")
    @Expose
    private String pallet;
    @SerializedName("bin")
    @Expose
    private String bin;
    @SerializedName("tolocation")
    @Expose
    private String tolocation;
    @SerializedName("uom")
    @Expose
    private String uom;

    public Integer getLineno() {
        return lineno;
    }

    public void setLineno(Integer lineno) {
        this.lineno = lineno;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
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

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getReceivedhdr() {
        return receivedhdr;
    }

    public void setReceivedhdr(String receivedhdr) {
        this.receivedhdr = receivedhdr;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getTolocation() {
        return tolocation;
    }

    public void setTolocation(String tolocation) {
        this.tolocation = tolocation;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

}
