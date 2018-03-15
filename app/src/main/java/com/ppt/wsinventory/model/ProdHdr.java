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
    @SerializedName("product_weight")
    @Expose
    private Double productWeight;
    @SerializedName("remnantgold")
    @Expose
    private Double remnantgold;
    @SerializedName("remnantjewel")
    @Expose
    private Double remnantjewel;
    @SerializedName("density")
    @Expose
    private Double density;
    @SerializedName("diff_weight")
    @Expose
    private Double diffWeight;
    @SerializedName("diff_k")
    @Expose
    private Integer diffK;
    @SerializedName("diff_p")
    @Expose
    private Integer diffP;
    @SerializedName("diff_y")
    @Expose
    private Double diffY;
    @SerializedName("prejewelout_date")
    @Expose
    private Date prejeweloutDate;
    @SerializedName("is_prejewelout")
    @Expose
    private Boolean isPrejewelout;
    @SerializedName("is_finished")
    @Expose
    private Boolean isFinished;
    @SerializedName("save_count")
    @Expose
    private Integer saveCount;
    @SerializedName("received_date")
    @Expose
    private Date receivedDate;
    @SerializedName("is_received")
    @Expose
    private Boolean isReceived;
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
    private Integer smith;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("staff")
    @Expose
    private String staff;
    @SerializedName("jobtype")
    @Expose
    private Integer jobtype;

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

    public Double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Double productWeight) {
        this.productWeight = productWeight;
    }

    public Double getRemnantgold() {
        return remnantgold;
    }

    public void setRemnantgold(Double remnantgold) {
        this.remnantgold = remnantgold;
    }

    public Double getRemnantjewel() {
        return remnantjewel;
    }

    public void setRemnantjewel(Double remnantjewel) {
        this.remnantjewel = remnantjewel;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Double getDiffWeight() {
        return diffWeight;
    }

    public void setDiffWeight(Double diffWeight) {
        this.diffWeight = diffWeight;
    }

    public Integer getDiffK() {
        return diffK;
    }

    public void setDiffK(Integer diffK) {
        this.diffK = diffK;
    }

    public Integer getDiffP() {
        return diffP;
    }

    public void setDiffP(Integer diffP) {
        this.diffP = diffP;
    }

    public Double getDiffY() {
        return diffY;
    }

    public void setDiffY(Double diffY) {
        this.diffY = diffY;
    }

    public Date getPrejeweloutDate() {
        return prejeweloutDate;
    }

    public void setPrejeweloutDate(Date prejeweloutDate) {
        this.prejeweloutDate = prejeweloutDate;
    }

    public Boolean getIsPrejewelout() {
        return isPrejewelout;
    }

    public void setIsPrejewelout(Boolean isPrejewelout) {
        this.isPrejewelout = isPrejewelout;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public Integer getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(Integer saveCount) {
        this.saveCount = saveCount;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Boolean getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(Boolean isReceived) {
        this.isReceived = isReceived;
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

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Integer getSmith() {
        return smith;
    }

    public void setSmith(Integer smith) {
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

    public Integer getJobtype() {
        return jobtype;
    }

    public void setJobtype(Integer jobtype) {
        this.jobtype = jobtype;
    }

}