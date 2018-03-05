package com.ppt.wsinventory.model;

/**
 * Created by ThinkCentre on 1/11/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BIN {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("bin_name")
    @Expose
    private String binName;
    @SerializedName("bin_description")
    @Expose
    private String binDescription;
    @SerializedName("bin_type")
    @Expose
    private String binType;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("no_of_pallets")
    @Expose
    private int no_of_pallets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBinName() {
        return binName;
    }

    public void setBinName(String binName) {
        this.binName = binName;
    }

    public String getBinDescription() {
        return binDescription;
    }

    public void setBinDescription(String binDescription) {
        this.binDescription = binDescription;
    }

    public String getBinType() {
        return binType;
    }

    public void setBinType(String binType) {
        this.binType = binType;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public int getNo_of_pallets() {
        return no_of_pallets;
    }

    public void setNo_of_pallets(int no_of_pallets) {
        this.no_of_pallets = no_of_pallets;
    }
}


