package com.ppt.wsinventory.model;

/**
 * Created by User on 07/02/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JewelType {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("unittype")
    @Expose
    private String unittype;
    @SerializedName("purchase_unittype")
    @Expose
    private String purchaseUnittype;
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

    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getPurchaseUnittype() {
        return purchaseUnittype;
    }

    public void setPurchaseUnittype(String purchaseUnittype) {
        this.purchaseUnittype = purchaseUnittype;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
