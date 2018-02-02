package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Smith {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("nrc")
    @Expose
    private String nrc;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("refname")
    @Expose
    private String refname;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("mixjob")
    @Expose
    private Integer mixjob;
    @SerializedName("inhandjob")
    @Expose
    private Integer inhandjob;
    @SerializedName("k")
    @Expose
    private Integer k;
    @SerializedName("p")
    @Expose
    private Integer p;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("g")
    @Expose
    private String g;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("goldsaving")
    @Expose
    private String goldsaving;
    @SerializedName("date_joined")
    @Expose
    private Date dateJoined;
    @SerializedName("date_end")
    @Expose
    private Date dateEnd;
    @SerializedName("is_smith2")
    @Expose
    private Boolean isSmith2;
    @SerializedName("active")
    @Expose
    private Boolean active;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRefname() {
        return refname;
    }

    public void setRefname(String refname) {
        this.refname = refname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getMixjob() {
        return mixjob;
    }

    public void setMixjob(Integer mixjob) {
        this.mixjob = mixjob;
    }

    public Integer getInhandjob() {
        return inhandjob;
    }

    public void setInhandjob(Integer inhandjob) {
        this.inhandjob = inhandjob;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getGoldsaving() {
        return goldsaving;
    }

    public void setGoldsaving(String goldsaving) {
        this.goldsaving = goldsaving;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {

        this.dateEnd = dateEnd;
    }

    public Boolean getIsSmith2() {
        return isSmith2;
    }

    public void setIsSmith2(Boolean isSmith2) {
        this.isSmith2 = isSmith2;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}