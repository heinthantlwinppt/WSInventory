package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Pallet {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("pallet_name")
    @Expose
    private String palletName;
    @SerializedName("pallet_description")
    @Expose
    private String palletDescription;
    @SerializedName("pallet_type")
    @Expose
    private String palletType;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("is_used")
    @Expose
    private Boolean isUsed;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("location")
    @Expose
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPalletName() {
        return palletName;
    }

    public void setPalletName(String palletName) {
        this.palletName = palletName;
    }

    public String getPalletDescription() {
        return palletDescription;
    }

    public void setPalletDescription(String palletDescription) {
        this.palletDescription = palletDescription;
    }

    public String getPalletType() {
        return palletType;
    }

    public void setPalletType(String palletType) {
        this.palletType = palletType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}