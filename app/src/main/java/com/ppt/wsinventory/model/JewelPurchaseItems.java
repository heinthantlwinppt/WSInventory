package com.ppt.wsinventory.model;

/**
 * Created by User on 07/02/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class JewelPurchaseItems {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("qty")
    @Expose
    private Double qty;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("row_no")
    @Expose
    private Integer rowNo;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("jewel_purchase")
    @Expose
    private String jewelPurchase;
    @SerializedName("jeweltype")
    @Expose
    private Integer jeweltype;
    @SerializedName("jewelshape")
    @Expose
    private Integer jewelshape;
    @SerializedName("jewellength")
    @Expose
    private Integer jewellength;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getJewelPurchase() {
        return jewelPurchase;
    }

    public void setJewelPurchase(String jewelPurchase) {
        this.jewelPurchase = jewelPurchase;
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

    public Integer getJewellength() {
        return jewellength;
    }

    public void setJewellength(Integer jewellength) {
        this.jewellength = jewellength;
    }

}