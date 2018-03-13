package com.ppt.wsinventory.model;

import android.widget.SearchView;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/12/2018.
 */

public class Inventory_products {
    public static final String TABLE_INVENTORY_PRODUCTS = "inventory_products";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESIGNNAME = "designname";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_BARCODE = "barcode";
    public static final String COLUMN_TAG = "tag";
    public static final String COLUMN_BASEUOM = "baseuom";
    public static final String COLUMN_MINQTY = "minqty";
    public static final String COLUMN_MAXQTY = "maxqty";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_PGROUP_ID = "pgroup_id";
    public static final String COLUMN_ROW_NO = "row_no";
    public static final String COLUMN_PLENGTH_ID = "plength_id";
//    public static final String COLUMN_PREDUCE_ID = "preduce_id";
    public static final String COLUMN_PSUBGROUP_ID = "psubgroup_id";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_DIAMOND = "diamond";
    public static final String COLUMN_DIAMONDCARATWEIGHT = "diamondcaratweight";
    public static final String COLUMN_DIAMONDCLARITY = "diamonclarity";
    public static final String COLUMN_DIAMONDMEASUREMENT = "diamondmeasurement";
    public static final String COLUMN_DIAMONDPRICE = "diamondprice";
    public static final String COLUMN_JEWELCOST ="jewelcost";
    public static final String COLUMN_JEWELLENGTH_ID = "jewellength_id";
    public static final String COLUMN_JEWELPRICE = "jewelprice";
    public static final String COLUMN_JEWELSHAPE_ID = "jewelshape_id";
    public static final String COLUMN_JEWELTYPE_ID ="jeweltype_id";
    public static final String COLUMN_LABOURCHARE =" laboutcharge";
    public static final String COLUMN_REMARKS ="remarks";
    public static final String COLUMN_SELLINGLABOURCHARGE = "sellinglabourcharge";
    public static final String COLUMN_DIAMONDCOLOR_ID = "diamondcolor_id";
    public static final String COLUMN_DIAMONDSHAPE_ID = "diamondshape_id";
    public static final String COLUMN_DIAMONDTYPE_ID = "diamondtype_id";


    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_NAME ,
            COLUMN_DESIGNNAME ,
            COLUMN_PHOTO ,
            COLUMN_BARCODE ,
            COLUMN_TAG ,
            COLUMN_BASEUOM ,
            COLUMN_MINQTY ,
            COLUMN_MAXQTY ,
            COLUMN_ACTIVE ,
            COLUMN_IS_DELETE,
            COLUMN_PGROUP_ID,
            COLUMN_ROW_NO,
            COLUMN_PLENGTH_ID,
//            COLUMN_PREDUCE_ID,
            COLUMN_PSUBGROUP_ID,
            COLUMN_TS,
            COLUMN_DIAMOND,
            COLUMN_DIAMONDCARATWEIGHT,
            COLUMN_DIAMONDCLARITY,
            COLUMN_DIAMONDMEASUREMENT,
            COLUMN_DIAMONDPRICE,
            COLUMN_JEWELCOST,
            COLUMN_JEWELLENGTH_ID,
            COLUMN_JEWELPRICE,
            COLUMN_JEWELSHAPE_ID,
            COLUMN_JEWELTYPE_ID,
            COLUMN_LABOURCHARE,
            COLUMN_REMARKS,
            COLUMN_SELLINGLABOURCHARGE,
            COLUMN_DIAMONDCOLOR_ID,
            COLUMN_DIAMONDSHAPE_ID,
            COLUMN_DIAMONDTYPE_ID

    };

    private String id ;
    private String name ;
    private String designname ;
    private String photo ;
    private String barcode ;
    private String tag ;
    private String baseuom ;
    private int minqty ;
    private int maxqty ;
    private boolean active ;
    private boolean is_delete ;
    private int pgroup_id ;
    private int row_no ;
    private int plength_id ;
//    private int preduce_id ;
    private int psubgroup_id ;
    private Date ts ;

    private String diamond;
    private double diamondcaratweight;
    private double diamondclarity;
    private double diamondmeasurement;
    private double diamondprice;
    private double jewelcost;
    private int jewellength_id;
    private double jewelprice;
    private int jewelshape_id;
    private int jeweltype_id;
    private double labourcharge;
    private String remarks;
    private double sellinglabourcharge;
    private int diamondcolor_id;
    private int diamondshape_id;
    private int diamondtype_id;

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

    public int getMinqty() {
        return minqty;
    }

    public void setMinqty(int minqty) {
        this.minqty = minqty;
    }

    public int getMaxqty() {
        return maxqty;
    }

    public void setMaxqty(int maxqty) {
        this.maxqty = maxqty;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public int getPgroup_id() {
        return pgroup_id;
    }

    public void setPgroup_id(int pgroup_id) {
        this.pgroup_id = pgroup_id;
    }

    public int getRow_no() {
        return row_no;
    }

    public void setRow_no(int row_no) {
        this.row_no = row_no;
    }

    public int getPlength_id() {
        return plength_id;
    }

    public void setPlength_id(int plength_id) {
        this.plength_id = plength_id;
    }

//    public int getPreduce_id() {
//        return preduce_id;
//    }
//
//    public void setPreduce_id(int preduce_id) {
//        this.preduce_id = preduce_id;
//    }

    public int getPsubgroup_id() {
        return psubgroup_id;
    }

    public void setPsubgroup_id(int psubgroup_id) {
        this.psubgroup_id = psubgroup_id;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(String diamond) {
        this.diamond = diamond;
    }

    public double getDiamondcaratweight() {
        return diamondcaratweight;
    }

    public void setDiamondcaratweight(double diamondcaratweight) {
        this.diamondcaratweight = diamondcaratweight;
    }

    public double getDiamondclarity() {
        return diamondclarity;
    }

    public void setDiamondclarity(double diamondclarity) {
        this.diamondclarity = diamondclarity;
    }

    public double getDiamondmeasurement() {
        return diamondmeasurement;
    }

    public void setDiamondmeasurement(double diamondmeasurement) {
        this.diamondmeasurement = diamondmeasurement;
    }

    public double getDiamondprice() {
        return diamondprice;
    }

    public void setDiamondprice(double diamondprice) {
        this.diamondprice = diamondprice;
    }

    public double getJewelcost() {
        return jewelcost;
    }

    public void setJewelcost(double jewelcost) {
        this.jewelcost = jewelcost;
    }

    public int getJewellength_id() {
        return jewellength_id;
    }

    public void setJewellength_id(int jewellength_id) {
        this.jewellength_id = jewellength_id;
    }

    public double getJewelprice() {
        return jewelprice;
    }

    public void setJewelprice(double jewelprice) {
        this.jewelprice = jewelprice;
    }

    public int getJewelshape_id() {
        return jewelshape_id;
    }

    public void setJewelshape_id(int jewelshape_id) {
        this.jewelshape_id = jewelshape_id;
    }

    public int getJeweltype_id() {
        return jeweltype_id;
    }

    public void setJeweltype_id(int jeweltype_id) {
        this.jeweltype_id = jeweltype_id;
    }

    public double getLabourcharge() {
        return labourcharge;
    }

    public void setLabourcharge(double labourcharge) {
        this.labourcharge = labourcharge;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getSellinglabourcharge() {
        return sellinglabourcharge;
    }

    public void setSellinglabourcharge(double sellinglabourcharge) {
        this.sellinglabourcharge = sellinglabourcharge;
    }

    public int getDiamondcolor_id() {
        return diamondcolor_id;
    }

    public void setDiamondcolor_id(int diamondcolor_id) {
        this.diamondcolor_id = diamondcolor_id;
    }

    public int getDiamondshape_id() {
        return diamondshape_id;
    }

    public void setDiamondshape_id(int diamondshape_id) {
        this.diamondshape_id = diamondshape_id;
    }

    public int getDiamondtype_id() {
        return diamondtype_id;
    }

    public void setDiamondtype_id(int diamondtype_id) {
        this.diamondtype_id = diamondtype_id;
    }

    @Override
    public String toString() {
        return "Inventory_products{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", designname='" + designname + '\'' +
                ", photo='" + photo + '\'' +
                ", barcode='" + barcode + '\'' +
                ", tag='" + tag + '\'' +
                ", baseuom='" + baseuom + '\'' +
                ", diamond='" + diamond + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
