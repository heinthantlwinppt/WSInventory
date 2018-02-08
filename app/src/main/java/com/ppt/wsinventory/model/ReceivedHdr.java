package com.ppt.wsinventory.model;

/**
 * Created by User on 08/02/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ReceivedHdr {

    @SerializedName("receive_no")
    @Expose
    private String receiveNo;
    @SerializedName("receive_date")
    @Expose
    private Date receiveDate;
    @SerializedName("is_completed")
    @Expose
    private Boolean isCompleted;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;
    @SerializedName("is_void")
    @Expose
    private Boolean isVoid;
    @SerializedName("void_date")
    @Expose
    private Date voidDate;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("smith")
    @Expose
    private Integer smith;
    @SerializedName("doctype")
    @Expose
    private Integer doctype;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("staff")
    @Expose
    private String staff;

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
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

    public Integer getSmith() {
        return smith;
    }

    public void setSmith(Integer smith) {
        this.smith = smith;
    }

    public Integer getDoctype() {
        return doctype;
    }

    public void setDoctype(Integer doctype) {
        this.doctype = doctype;
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
