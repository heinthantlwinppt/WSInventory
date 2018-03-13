package com.ppt.wsinventory.model;

/**
 * Created by User on 26/01/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ProductReduce {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("reduce_g")
    @Expose
    private Double reduceG;
    @SerializedName("reduce_k")
    @Expose
    private Integer reduceK;
    @SerializedName("reduce_p")
    @Expose
    private Integer reduceP;
    @SerializedName("reduce_y")
    @Expose
    private Double reduceY;
    @SerializedName("production_fee")
    @Expose
    private Double productionFee;
    @SerializedName("cost_reduce_g")
    @Expose
    private Double costReduceG;
    @SerializedName("cost_reduce_k")
    @Expose
    private Integer costReduceK;
    @SerializedName("cost_reduce_p")
    @Expose
    private Integer costReduceP;
    @SerializedName("cost_reduce_y")
    @Expose
    private Double costReduceY;
    @SerializedName("cost_production_fee")
    @Expose
    private Double costProductionFee;
    @SerializedName("ws_reduce_g")
    @Expose
    private Double wsReduceG;
    @SerializedName("ws_reduce_k")
    @Expose
    private Integer wsReduceK;
    @SerializedName("ws_reduce_p")
    @Expose
    private Integer wsReduceP;
    @SerializedName("ws_reduce_y")
    @Expose
    private Double wsReduceY;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("plength")
    @Expose
    private Integer plength;
    @SerializedName("ts")
    @Expose
    private Date ts;

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getReduceG() {
        return reduceG;
    }

    public void setReduceG(Double reduceG) {
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

    public Double getReduceY() {
        return reduceY;
    }

    public void setReduceY(Double reduceY) {
        this.reduceY = reduceY;
    }

    public Double getProductionFee() {
        return productionFee;
    }

    public void setProductionFee(Double productionFee) {
        this.productionFee = productionFee;
    }

    public Double getCostReduceG() {
        return costReduceG;
    }

    public void setCostReduceG(Double costReduceG) {
        this.costReduceG = costReduceG;
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

    public Double getCostReduceY() {
        return costReduceY;
    }

    public void setCostReduceY(Double costReduceY) {
        this.costReduceY = costReduceY;
    }

    public Double getCostProductionFee() {
        return costProductionFee;
    }

    public void setCostProductionFee(Double costProductionFee) {
        this.costProductionFee = costProductionFee;
    }

    public Double getWsReduceG() {
        return wsReduceG;
    }

    public void setWsReduceG(Double wsReduceG) {
        this.wsReduceG = wsReduceG;
    }

    public Integer getWsReduceK() {
        return wsReduceK;
    }

    public void setWsReduceK(Integer wsReduceK) {
        this.wsReduceK = wsReduceK;
    }

    public Integer getWsReduceP() {
        return wsReduceP;
    }

    public void setWsReduceP(Integer wsReduceP) {
        this.wsReduceP = wsReduceP;
    }

    public Double getWsReduceY() {
        return wsReduceY;
    }

    public void setWsReduceY(Double wsReduceY) {
        this.wsReduceY = wsReduceY;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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
