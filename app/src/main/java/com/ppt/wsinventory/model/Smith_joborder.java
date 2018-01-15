package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Smith_joborder {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("joborder_no")
    @Expose
    private String joborderNo;
    @SerializedName("joborder_date")
    @Expose
    private Date joborderDate;
    @SerializedName("prejewelout")
    @Expose
    private Boolean prejewelout;
    @SerializedName("prejewelout_date")
    @Expose
    private Date prejeweloutDate;
    @SerializedName("date_target")
    @Expose
    private Date dateTarget;
    @SerializedName("date_start")
    @Expose
    private Date dateStart;
    @SerializedName("date_end")
    @Expose
    private Date dateEnd;
    @SerializedName("product_weight")
    @Expose
    private String productWeight;
    @SerializedName("remain_gold")
    @Expose
    private String remainGold;
    @SerializedName("remain_jewel")
    @Expose
    private String remainJewel;
    @SerializedName("diff_weight")
    @Expose
    private String diffWeight;
    @SerializedName("diff_k")
    @Expose
    private Integer diffK;
    @SerializedName("diff_p")
    @Expose
    private Integer diffP;
    @SerializedName("diff_y")
    @Expose
    private String diffY;
    @SerializedName("density")
    @Expose
    private String density;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("print_count")
    @Expose
    private Integer printCount;
    @SerializedName("save_count")
    @Expose
    private Integer saveCount;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("joborder_type")
    @Expose
    private Integer joborderType;
    @SerializedName("smith")
    @Expose
    private Integer smith;
    @SerializedName("jobstatus")
    @Expose
    private Integer jobstatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoborderNo() {
        return joborderNo;
    }

    public void setJoborderNo(String joborderNo) {
        this.joborderNo = joborderNo;
    }

    public Date getJoborderDate() {
        return joborderDate;
    }

    public void setJoborderDate(Date joborderDate) {
        this.joborderDate = joborderDate;
    }

    public Boolean getPrejewelout() {
        return prejewelout;
    }

    public void setPrejewelout(Boolean prejewelout) {
        this.prejewelout = prejewelout;
    }

    public Date getPrejeweloutDate() {
        return prejeweloutDate;
    }

    public void setPrejeweloutDate(Date prejeweloutDate) {
        this.prejeweloutDate = prejeweloutDate;
    }

    public Date getDateTarget() {
        return dateTarget;
    }

    public void setDateTarget(Date dateTarget) {
        this.dateTarget = dateTarget;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getRemainGold() {
        return remainGold;
    }

    public void setRemainGold(String remainGold) {
        this.remainGold = remainGold;
    }

    public String getRemainJewel() {
        return remainJewel;
    }

    public void setRemainJewel(String remainJewel) {
        this.remainJewel = remainJewel;
    }

    public String getDiffWeight() {
        return diffWeight;
    }

    public void setDiffWeight(String diffWeight) {
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

    public String getDiffY() {
        return diffY;
    }

    public void setDiffY(String diffY) {
        this.diffY = diffY;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPrintCount() {
        return printCount;
    }

    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    public Integer getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(Integer saveCount) {
        this.saveCount = saveCount;
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

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Integer getJoborderType() {
        return joborderType;
    }

    public void setJoborderType(Integer joborderType) {
        this.joborderType = joborderType;
    }

    public Integer getSmith() {
        return smith;
    }

    public void setSmith(Integer smith) {
        this.smith = smith;
    }

    public Integer getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(Integer jobstatus) {
        this.jobstatus = jobstatus;
    }

}