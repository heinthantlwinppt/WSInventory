package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Smith_JobGold{

    @SerializedName("smith_joborder")
    @Expose
    private String smithJoborder;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("goldsaving")
    @Expose
    private String goldsaving;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("row_no")
    @Expose
    private Integer rowNo;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("uom")
    @Expose
    private String uom;

    public String getSmithJoborder() {
        return smithJoborder;
    }

    public void setSmithJoborder(String smithJoborder) {
        this.smithJoborder = smithJoborder;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getGoldsaving() {
        return goldsaving;
    }

    public void setGoldsaving(String goldsaving) {
        this.goldsaving = goldsaving;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
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

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

}