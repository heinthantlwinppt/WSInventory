package com.ppt.wsinventory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ProductSerial {

    @SerializedName("serial_no")
    @Expose
    private String serialNo;
    @SerializedName("goodsid")
    @Expose
    private String goodsid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("serial_date")
    @Expose
    private Date serialDate;
    @SerializedName("plength")
    @Expose
    private String plength;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("k")
    @Expose
    private Integer k;
    @SerializedName("p")
    @Expose
    private Integer p;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("reduce_weight")
    @Expose
    private String reduceWeight;
    @SerializedName("reduce_k")
    @Expose
    private Integer reduceK;
    @SerializedName("reduce_p")
    @Expose
    private Integer reduceP;
    @SerializedName("reduce_y")
    @Expose
    private String reduceY;
    @SerializedName("jewel_weight")
    @Expose
    private String jewelWeight;
    @SerializedName("jewel_k")
    @Expose
    private Integer jewelK;
    @SerializedName("jewel_p")
    @Expose
    private Integer jewelP;
    @SerializedName("jewel_y")
    @Expose
    private String jewelY;
    @SerializedName("jewel_fee")
    @Expose
    private String jewelFee;
    @SerializedName("production_fee")
    @Expose
    private String productionFee;
    @SerializedName("jewel_name1")
    @Expose
    private String jewelName1;
    @SerializedName("jewel_name2")
    @Expose
    private String jewelName2;
    @SerializedName("jewel_name3")
    @Expose
    private String jewelName3;
    @SerializedName("jewel_name4")
    @Expose
    private String jewelName4;
    @SerializedName("jewel_name5")
    @Expose
    private String jewelName5;
    @SerializedName("jewel_weight1")
    @Expose
    private String jewelWeight1;
    @SerializedName("jewel_weight2")
    @Expose
    private String jewelWeight2;
    @SerializedName("jewel_weight3")
    @Expose
    private String jewelWeight3;
    @SerializedName("jewel_weight4")
    @Expose
    private String jewelWeight4;
    @SerializedName("jewel_weight5")
    @Expose
    private String jewelWeight5;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("delivered")
    @Expose
    private Boolean delivered;
    @SerializedName("is_delete")
    @Expose
    private boolean isDelete;
    @SerializedName("ts")
    @Expose
    private Date ts;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("gold")
    @Expose
    private Integer gold;
    @SerializedName("smith")
    @Expose
    private String smith;
    @SerializedName("staff")
    @Expose
    private String staff;
    @SerializedName("uom")
    @Expose
    private String uom;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("bin")
    @Expose
    private String bin;
    @SerializedName("pallet")
    @Expose
    private String pallet;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  Date  getSerialDate() {
        return serialDate;
    }

    public void setSerialDate(Date serialDate) {
        this.serialDate = serialDate;
    }

    public String getPlength() {
        return plength;
    }

    public void setPlength(String plength) {
        this.plength = plength;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getReduceWeight() {
        return reduceWeight;
    }

    public void setReduceWeight(String reduceWeight) {
        this.reduceWeight = reduceWeight;
    }

    public Integer getReduceK() {
        return reduceK;
    }

    public void setReduceK(Integer reduceK) {
        this.reduceK = reduceK;
    }

    public Integer getReduceP() {
        return reduceP;
    }

    public void setReduceP(Integer reduceP) {
        this.reduceP = reduceP;
    }

    public String getReduceY() {
        return reduceY;
    }

    public void setReduceY(String reduceY) {
        this.reduceY = reduceY;
    }

    public String getJewelWeight() {
        return jewelWeight;
    }

    public void setJewelWeight(String jewelWeight) {
        this.jewelWeight = jewelWeight;
    }

    public Integer getJewelK() {
        return jewelK;
    }

    public void setJewelK(Integer jewelK) {
        this.jewelK = jewelK;
    }

    public Integer getJewelP() {
        return jewelP;
    }

    public void setJewelP(Integer jewelP) {
        this.jewelP = jewelP;
    }

    public String getJewelY() {
        return jewelY;
    }

    public void setJewelY(String jewelY) {
        this.jewelY = jewelY;
    }

    public String getJewelFee() {
        return jewelFee;
    }

    public void setJewelFee(String jewelFee) {
        this.jewelFee = jewelFee;
    }

    public String getProductionFee() {
        return productionFee;
    }

    public void setProductionFee(String productionFee) {
        this.productionFee = productionFee;
    }

    public String getJewelName1() {
        return jewelName1;
    }

    public void setJewelName1(String jewelName1) {
        this.jewelName1 = jewelName1;
    }

    public String getJewelName2() {
        return jewelName2;
    }

    public void setJewelName2(String jewelName2) {
        this.jewelName2 = jewelName2;
    }

    public String getJewelName3() {
        return jewelName3;
    }

    public void setJewelName3(String jewelName3) {
        this.jewelName3 = jewelName3;
    }

    public String getJewelName4() {
        return jewelName4;
    }

    public void setJewelName4(String jewelName4) {
        this.jewelName4 = jewelName4;
    }

    public String getJewelName5() {
        return jewelName5;
    }

    public void setJewelName5(String jewelName5) {
        this.jewelName5 = jewelName5;
    }

    public String getJewelWeight1() {
        return jewelWeight1;
    }

    public void setJewelWeight1(String jewelWeight1) {
        this.jewelWeight1 = jewelWeight1;
    }

    public String getJewelWeight2() {
        return jewelWeight2;
    }

    public void setJewelWeight2(String jewelWeight2) {
        this.jewelWeight2 = jewelWeight2;
    }

    public String getJewelWeight3() {
        return jewelWeight3;
    }

    public void setJewelWeight3(String jewelWeight3) {
        this.jewelWeight3 = jewelWeight3;
    }

    public String getJewelWeight4() {
        return jewelWeight4;
    }

    public void setJewelWeight4(String jewelWeight4) {
        this.jewelWeight4 = jewelWeight4;
    }

    public String getJewelWeight5() {
        return jewelWeight5;
    }

    public void setJewelWeight5(String jewelWeight5) {
        this.jewelWeight5 = jewelWeight5;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public String getSmith() {
        return smith;
    }

    public void setSmith(String smith) {
        this.smith = smith;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getPallet() {
        return pallet;
    }

    public void setPallet(String pallet) {
        this.pallet = pallet;
    }

}