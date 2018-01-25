package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

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
    @SerializedName("row_no")
    @Expose
    private Integer rowNo;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("pgroup")
    @Expose
    private Integer pgroup;
    @SerializedName("psubgroup")
    @Expose
    private Integer psubgroup;
    @SerializedName("plength")
    @Expose
    private Integer plength;
    @SerializedName("preduce")
    @Expose
    private Integer preduce;
    @SerializedName("photo")
    @Expose
    private String photo;

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

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getPgroup() {
        return pgroup;
    }

    public void setPgroup(Integer pgroup) {
        this.pgroup = pgroup;
    }

    public Integer getPsubgroup() {
        return psubgroup;
    }

    public void setPsubgroup(Integer psubgroup) {
        this.psubgroup = psubgroup;
    }

    public Integer getPlength() {
        return plength;
    }

    public void setPlength(Integer plength) {
        this.plength = plength;
    }

    public Integer getPreduce() {
        return preduce;
    }

    public void setPreduce(Integer preduce) {
        this.preduce = preduce;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}