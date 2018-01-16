package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Serialgolds {

    @SerializedName("serial")
    @Expose
    private String serial;
    @SerializedName("reduce_weight")
    @Expose
    private String reduceWeight;
    @SerializedName("reduce_k")
    @Expose
    private Integer reduceK;
    @SerializedName("reduce_p")
    @Expose
    private Integer reduceP;
    @SerializedName("reduce_y")
    @Expose
    private String reduceY;
    @SerializedName("production_fee")
    @Expose
    private String productionFee;
    @SerializedName("cost_reduce_weight")
    @Expose
    private String costReduceWeight;
    @SerializedName("cost_reduce_k")
    @Expose
    private Integer costReduceK;
    @SerializedName("cost_reduce_p")
    @Expose
    private Integer costReduceP;
    @SerializedName("cost_reduce_y")
    @Expose
    private String costReduceY;
    @SerializedName("cost_productionfee")
    @Expose
    private String costProductionfee;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("gold")
    @Expose
    private Integer gold;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getReduceWeight() {
        return reduceWeight;
    }

    public void setReduceWeight(String reduceWeight) {
        this.reduceWeight = reduceWeight;
    }

    public Integer getReduceK() {
        return reduceK;
    }

    public void setReduceK(Integer reduceK) {
        this.reduceK = reduceK;
    }

    public Integer getReduceP() {
        return reduceP;
    }

    public void setReduceP(Integer reduceP) {
        this.reduceP = reduceP;
    }

    public String getReduceY() {
        return reduceY;
    }

    public void setReduceY(String reduceY) {
        this.reduceY = reduceY;
    }

    public String getProductionFee() {
        return productionFee;
    }

    public void setProductionFee(String productionFee) {
        this.productionFee = productionFee;
    }

    public String getCostReduceWeight() {
        return costReduceWeight;
    }

    public void setCostReduceWeight(String costReduceWeight) {
        this.costReduceWeight = costReduceWeight;
    }

    public Integer getCostReduceK() {
        return costReduceK;
    }

    public void setCostReduceK(Integer costReduceK) {
        this.costReduceK = costReduceK;
    }

    public Integer getCostReduceP() {
        return costReduceP;
    }

    public void setCostReduceP(Integer costReduceP) {
        this.costReduceP = costReduceP;
    }

    public String getCostReduceY() {
        return costReduceY;
    }

    public void setCostReduceY(String costReduceY) {
        this.costReduceY = costReduceY;
    }

    public String getCostProductionfee() {
        return costProductionfee;
    }

    public void setCostProductionfee(String costProductionfee) {
        this.costProductionfee = costProductionfee;
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

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

}