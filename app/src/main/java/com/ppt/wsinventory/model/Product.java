package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("designname")
    @Expose
    private String designname;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("baseuom")
    @Expose
    private String baseuom;
    @SerializedName("minqty")
    @Expose
    private Integer minqty;
    @SerializedName("maxqty")
    @Expose
    private Integer maxqty;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("is_delete")
    @Expose
    private String isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignname() {
        return designname;
    }

    public void setDesignname(String designname) {
        this.designname = designname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getBaseuom() {
        return baseuom;
    }

    public void setBaseuom(String baseuom) {
        this.baseuom = baseuom;
    }

    public Integer getMinqty() {
        return minqty;
    }

    public void setMinqty(Integer minqty) {
        this.minqty = minqty;
    }

    public Integer getMaxqty() {
        return maxqty;
    }

    public void setMaxqty(Integer maxqty) {
        this.maxqty = maxqty;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

}