package com.ppt.wsinventory.model;

/**
 * Created by arkar on 21/1/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Goods {

    @SerializedName("goodsid")
    @Expose
    private String goodsid;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("k")
    @Expose
    private String k;
    @SerializedName("p")
    @Expose
    private String p;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("reduce_k")
    @Expose
    private String reduceK;
    @SerializedName("reduce_p")
    @Expose
    private String reduceP;
    @SerializedName("reduce_y")
    @Expose
    private String reduceY;
    @SerializedName("jewel_k")
    @Expose
    private String jewelK;
    @SerializedName("jewel_p")
    @Expose
    private String jewelP;
    @SerializedName("jewel_y")
    @Expose
    private String jewelY;
    @SerializedName("jewelfee")
    @Expose
    private String jewelfee;
    @SerializedName("productionfee")
    @Expose
    private String productionfee;
    @SerializedName("jewelcurrencykyat")
    @Expose
    private Boolean jewelcurrencykyat;
    @SerializedName("jname1")
    @Expose
    private String jname1;
    @SerializedName("jname2")
    @Expose
    private String jname2;
    @SerializedName("jname3")
    @Expose
    private String jname3;
    @SerializedName("jname4")
    @Expose
    private String jname4;
    @SerializedName("jname5")
    @Expose
    private String jname5;
    @SerializedName("jweight1")
    @Expose
    private String jweight1;
    @SerializedName("jweight2")
    @Expose
    private String jweight2;
    @SerializedName("jweight3")
    @Expose
    private String jweight3;
    @SerializedName("jweight4")
    @Expose
    private String jweight4;
    @SerializedName("jweight5")
    @Expose
    private String jweight5;
    @SerializedName("jprice1")
    @Expose
    private String jprice1;
    @SerializedName("jprice2")
    @Expose
    private String jprice2;
    @SerializedName("jprice3")
    @Expose
    private String jprice3;
    @SerializedName("jprice4")
    @Expose
    private String jprice4;
    @SerializedName("jprice5")
    @Expose
    private String jprice5;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("printmark")
    @Expose
    private String printmark;
    @SerializedName("loginname")
    @Expose
    private String loginname;
    @SerializedName("searchprint")
    @Expose
    private Boolean searchprint;
    @SerializedName("goodtype")
    @Expose
    private Integer goodtype;
    @SerializedName("datamatrixcode")
    @Expose
    private String datamatrixcode;
    @SerializedName("transfercounterid")
    @Expose
    private String transfercounterid;
    @SerializedName("outdate")
    @Expose
    private String outdate;
    @SerializedName("oldcounterid")
    @Expose
    private String oldcounterid;
    @SerializedName("pimage")
    @Expose
    private String pimage;
    @SerializedName("goldcost")
    @Expose
    private String goldcost;
    @SerializedName("jewelcost")
    @Expose
    private String jewelcost;
    @SerializedName("wagescost")
    @Expose
    private String wagescost;
    @SerializedName("isg")
    @Expose
    private Boolean isg;
    @SerializedName("counterid")
    @Expose
    private String counterid;
    @SerializedName("goldid")
    @Expose
    private String goldid;
    @SerializedName("pid2")
    @Expose
    private Integer pid2;
    @SerializedName("gsid")
    @Expose
    private Integer gsid;

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getReduceK() {
        return reduceK;
    }

    public void setReduceK(String reduceK) {
        this.reduceK = reduceK;
    }

    public String getReduceP() {
        return reduceP;
    }

    public void setReduceP(String reduceP) {
        this.reduceP = reduceP;
    }

    public String getReduceY() {
        return reduceY;
    }

    public void setReduceY(String reduceY) {
        this.reduceY = reduceY;
    }

    public String getJewelK() {
        return jewelK;
    }

    public void setJewelK(String jewelK) {
        this.jewelK = jewelK;
    }

    public String getJewelP() {
        return jewelP;
    }

    public void setJewelP(String jewelP) {
        this.jewelP = jewelP;
    }

    public String getJewelY() {
        return jewelY;
    }

    public void setJewelY(String jewelY) {
        this.jewelY = jewelY;
    }

    public String getJewelfee() {
        return jewelfee;
    }

    public void setJewelfee(String jewelfee) {
        this.jewelfee = jewelfee;
    }

    public String getProductionfee() {
        return productionfee;
    }

    public void setProductionfee(String productionfee) {
        this.productionfee = productionfee;
    }

    public Boolean getJewelcurrencykyat() {
        return jewelcurrencykyat;
    }

    public void setJewelcurrencykyat(Boolean jewelcurrencykyat) {
        this.jewelcurrencykyat = jewelcurrencykyat;
    }

    public String getJname1() {
        return jname1;
    }

    public void setJname1(String jname1) {
        this.jname1 = jname1;
    }

    public String getJname2() {
        return jname2;
    }

    public void setJname2(String jname2) {
        this.jname2 = jname2;
    }

    public String getJname3() {
        return jname3;
    }

    public void setJname3(String jname3) {
        this.jname3 = jname3;
    }

    public String getJname4() {
        return jname4;
    }

    public void setJname4(String jname4) {
        this.jname4 = jname4;
    }

    public String getJname5() {
        return jname5;
    }

    public void setJname5(String jname5) {
        this.jname5 = jname5;
    }

    public String getJweight1() {
        return jweight1;
    }

    public void setJweight1(String jweight1) {
        this.jweight1 = jweight1;
    }

    public String getJweight2() {
        return jweight2;
    }

    public void setJweight2(String jweight2) {
        this.jweight2 = jweight2;
    }

    public String getJweight3() {
        return jweight3;
    }

    public void setJweight3(String jweight3) {
        this.jweight3 = jweight3;
    }

    public String getJweight4() {
        return jweight4;
    }

    public void setJweight4(String jweight4) {
        this.jweight4 = jweight4;
    }

    public String getJweight5() {
        return jweight5;
    }

    public void setJweight5(String jweight5) {
        this.jweight5 = jweight5;
    }

    public String getJprice1() {
        return jprice1;
    }

    public void setJprice1(String jprice1) {
        this.jprice1 = jprice1;
    }

    public String getJprice2() {
        return jprice2;
    }

    public void setJprice2(String jprice2) {
        this.jprice2 = jprice2;
    }

    public String getJprice3() {
        return jprice3;
    }

    public void setJprice3(String jprice3) {
        this.jprice3 = jprice3;
    }

    public String getJprice4() {
        return jprice4;
    }

    public void setJprice4(String jprice4) {
        this.jprice4 = jprice4;
    }

    public String getJprice5() {
        return jprice5;
    }

    public void setJprice5(String jprice5) {
        this.jprice5 = jprice5;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrintmark() {
        return printmark;
    }

    public void setPrintmark(String printmark) {
        this.printmark = printmark;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public Boolean getSearchprint() {
        return searchprint;
    }

    public void setSearchprint(Boolean searchprint) {
        this.searchprint = searchprint;
    }

    public Integer getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(Integer goodtype) {
        this.goodtype = goodtype;
    }

    public String getDatamatrixcode() {
        return datamatrixcode;
    }

    public void setDatamatrixcode(String datamatrixcode) {
        this.datamatrixcode = datamatrixcode;
    }

    public String getTransfercounterid() {
        return transfercounterid;
    }

    public void setTransfercounterid(String transfercounterid) {
        this.transfercounterid = transfercounterid;
    }

    public String getOutdate() {
        return outdate;
    }

    public void setOutdate(String outdate) {
        this.outdate = outdate;
    }

    public String getOldcounterid() {
        return oldcounterid;
    }

    public void setOldcounterid(String oldcounterid) {
        this.oldcounterid = oldcounterid;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getGoldcost() {
        return goldcost;
    }

    public void setGoldcost(String goldcost) {
        this.goldcost = goldcost;
    }

    public String getJewelcost() {
        return jewelcost;
    }

    public void setJewelcost(String jewelcost) {
        this.jewelcost = jewelcost;
    }

    public String getWagescost() {
        return wagescost;
    }

    public void setWagescost(String wagescost) {
        this.wagescost = wagescost;
    }

    public Boolean getIsg() {
        return isg;
    }

    public void setIsg(Boolean isg) {
        this.isg = isg;
    }

    public String getCounterid() {
        return counterid;
    }

    public void setCounterid(String counterid) {
        this.counterid = counterid;
    }

    public String getGoldid() {
        return goldid;
    }

    public void setGoldid(String goldid) {
        this.goldid = goldid;
    }

    public Integer getPid2() {
        return pid2;
    }

    public void setPid2(Integer pid2) {
        this.pid2 = pid2;
    }

    public Integer getGsid() {
        return gsid;
    }

    public void setGsid(Integer gsid) {
        this.gsid = gsid;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsid='" + goodsid + '\'' +
                ", k='" + k + '\'' +
                ", p='" + p + '\'' +
                ", y='" + y + '\'' +
                ", counterid='" + counterid + '\'' +
                '}';
    }
}