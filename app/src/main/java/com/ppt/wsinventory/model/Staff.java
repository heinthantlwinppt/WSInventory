package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Staff {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("staff_id")
    @Expose
    private String staffId;
    @SerializedName("staff_name")
    @Expose
    private String staffName;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("nick_name")
    @Expose
    private String nickName;
    @SerializedName("nrc_no")
    @Expose
    private String nrcNo;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("home_phone_no")
    @Expose
    private String homePhoneNo;
    @SerializedName("staff_photo")
    @Expose
    private String staffPhoto;
    @SerializedName("date_joined")
    @Expose
    private Date dateJoined;
    @SerializedName("date_left")
    @Expose
    private Date dateLeft;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("role")
    @Expose
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getHomePhoneNo() {
        return homePhoneNo;
    }

    public void setHomePhoneNo(String homePhoneNo) {
        this.homePhoneNo = homePhoneNo;
    }

    public String getStaffPhoto() {
        return staffPhoto;
    }

    public void setStaffPhoto(String staffPhoto) {
        this.staffPhoto = staffPhoto;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDateLeft() {
        return dateLeft;
    }

    public void setDateLeft(Date dateLeft) {
        this.dateLeft = dateLeft;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}