package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("loc_name")
    @Expose
    private String locName;
    @SerializedName("loc_addr")
    @Expose
    private String locAddr;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("receiving_bin")
    @Expose
    private String receivingBin;
    @SerializedName("active")
    @Expose
    private Boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getLocAddr() {
        return locAddr;
    }

    public void setLocAddr(String locAddr) {
        this.locAddr = locAddr;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getReceivingBin() {
        return receivingBin;
    }

    public void setReceivingBin(String receivingBin) {
        this.receivingBin = receivingBin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}