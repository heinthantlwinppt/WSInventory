package com.ppt.wsinventory.model;

/**
 * Created by User on 14/02/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ProdHdr {

    @SerializedName("prod_no")
    @Expose
    private String prodNo;
    @SerializedName("prod_date")
    @Expose
    private Date prodDate;
    @SerializedName("voucher_no")
    @Expose
    private String voucherNo;
    @SerializedName("save_count")
    @Expose
    private Integer saveCount;
    @SerializedName("is_delivered")
    @Expose
    private Boolean isDelivered;
    @SerializedName("is_confirmed")
    @Expose
    private Boolean isConfirmed;
    @SerializedName("confirmedby")
    @Expose
    private String confirmedby;
    @SerializedName("is_void")
    @Expose
    private Boolean isVoid;
    @SerializedName("void_date")
    @Expose
    private Date voidDate;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("smith")
    @Expose
    private String  smith;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("staff")
    @Expose
    private String staff;

    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Boolean getIsDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(Boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getConfirmedby() {
        return confirmedby;
    }

    public void setConfirmedby(String confirmedby) {
        this.confirmedby = confirmedby;
    }

    public Boolean getIsVoid() {
        return isVoid;
    }

    public void setIsVoid(Boolean isVoid) {
        this.isVoid = isVoid;
    }

    public Date getVoidDate() {
        return voidDate;
    }

    public void setVoidDate(Date voidDate) {
        this.voidDate = voidDate;
    }

    public Integer getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(Integer saveCount) {
        this.saveCount = saveCount;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getSmith() {
        return smith;
    }

    public void setSmith(String  smith) {
        this.smith = smith;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

}