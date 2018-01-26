package com.ppt.wsinventory.model;

/**
 * Created by User on 26/01/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductReduce {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("reduce_g")
    @Expose
    private Integer reduceG;
    @SerializedName("reduce_k")
    @Expose
    private Integer reduceK;
    @SerializedName("reduce_p")
    @Expose
    private Integer reduceP;
    @SerializedName("reduce_y")
    @Expose
    private Integer reduceY;
    @SerializedName("production_fee")
    @Expose
    private Integer productionFee;
    @SerializedName("cost_reduce_k")
    @Expose
    private Integer costReduceK;
    @SerializedName("cost_reduce_p")
    @Expose
    private Integer costReduceP;
    @SerializedName("cost_reduce_y")
    @Expose
    private Integer costReduceY;
    @SerializedName("cost_production_fee")
    @Expose
    private Integer costProductionFee;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("plength")
    @Expose
    private Integer plength;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReduceG() {
        return reduceG;
    }

    public void setReduceG(Integer reduceG) {
        this.reduceG = reduceG;
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

    public Integer getReduceY() {
        return reduceY;
    }

    public void setReduceY(Integer reduceY) {
        this.reduceY = reduceY;
    }

    public Integer getProductionFee() {
        return productionFee;
    }

    public void setProductionFee(Integer productionFee) {
        this.productionFee = productionFee;
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

    public Integer getCostReduceY() {
        return costReduceY;
    }

    public void setCostReduceY(Integer costReduceY) {
        this.costReduceY = costReduceY;
    }

    public Integer getCostProductionFee() {
        return costProductionFee;
    }

    public void setCostProductionFee(Integer costProductionFee) {
        this.costProductionFee = costProductionFee;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getPlength() {
        return plength;
    }

    public void setPlength(Integer plength) {
        this.plength = plength;
    }

}