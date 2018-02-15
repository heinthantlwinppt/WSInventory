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
    @SerializedName("labourcharge")
    @Expose
    private double labourcharge;
    @SerializedName("sellinglabourcharge")
    @Expose
    private double sellinglabourcharge;
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
    @SerializedName("jewelcost")
    @Expose
    private Double jewelcost;
    @SerializedName("jewelprice")
    @Expose
    private Double jewelprice;
    @SerializedName("diamondmeasurement")
    @Expose
    private Double diamondmeasurement;
    @SerializedName("diamondclarity")
    @Expose
    private Double diamondclarity;
    @SerializedName("diamondcaratweight")
    @Expose
    private Double diamondcaratweight;
    @SerializedName("diamond")
    @Expose
    private String diamond;
    @SerializedName("diamondprice")
    @Expose
    private Double diamondprice;
    @SerializedName("remarks")
    @Expose
    private String remarks;
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
    @SerializedName("jeweltype")
    @Expose
    private Integer jeweltype;
    @SerializedName("jewelshape")
    @Expose
    private Integer jewelshape;
    @SerializedName("jewellength")
    @Expose
    private Integer jewellength;
    @SerializedName("diamondtype")
    @Expose
    private Integer diamondtype;
    @SerializedName("diamondshape")
    @Expose
    private Integer diamondshape;
    @SerializedName("diamondcolor")
    @Expose
    private Integer diamondcolor;

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

    public double getLabourcharge() {
        return labourcharge;
    }

    public void setLabourcharge(double labourcharge) {
        this.labourcharge = labourcharge;
    }

    public Double getSellinglabourcharge() {
        return sellinglabourcharge;
    }

    public void setSellinglabourcharge(double sellinglabourcharge) {
        this.sellinglabourcharge = sellinglabourcharge;
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

    public Double getJewelcost() {
        return jewelcost;
    }

    public void setJewelcost(Double jewelcost) {
        this.jewelcost = jewelcost;
    }

    public Double getJewelprice() {
        return jewelprice;
    }

    public void setJewelprice(Double jewelprice) {
        this.jewelprice = jewelprice;
    }

    public Double getDiamondmeasurement() {
        return diamondmeasurement;
    }

    public void setDiamondmeasurement(Double diamondmeasurement) {
        this.diamondmeasurement = diamondmeasurement;
    }

    public Double getDiamondclarity() {
        return diamondclarity;
    }

    public void setDiamondclarity(Double diamondclarity) {
        this.diamondclarity = diamondclarity;
    }

    public Double getDiamondcaratweight() {
        return diamondcaratweight;
    }

    public void setDiamondcaratweight(Double diamondcaratweight) {
        this.diamondcaratweight = diamondcaratweight;
    }

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(String diamond) {
        this.diamond = diamond;
    }

    public Double getDiamondprice() {
        return diamondprice;
    }

    public void setDiamondprice(Double diamondprice) {
        this.diamondprice = diamondprice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Integer getJeweltype() {
        return jeweltype;
    }

    public void setJeweltype(Integer jeweltype) {
        this.jeweltype = jeweltype;
    }

    public Integer getJewelshape() {
        return jewelshape;
    }

    public void setJewelshape(Integer jewelshape) {
        this.jewelshape = jewelshape;
    }

    public Integer getJewellength() {
        return jewellength;
    }

    public void setJewellength(Integer jewellength) {
        this.jewellength = jewellength;
    }

    public Integer getDiamondtype() {
        return diamondtype;
    }

    public void setDiamondtype(Integer diamondtype) {
        this.diamondtype = diamondtype;
    }

    public Integer getDiamondshape() {
        return diamondshape;
    }

    public void setDiamondshape(Integer diamondshape) {
        this.diamondshape = diamondshape;
    }

    public Integer getDiamondcolor() {
        return diamondcolor;
    }

    public void setDiamondcolor(Integer diamondcolor) {
        this.diamondcolor = diamondcolor;
    }

}