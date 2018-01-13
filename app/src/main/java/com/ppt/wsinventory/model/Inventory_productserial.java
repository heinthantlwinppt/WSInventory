package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/12/2018.
 */

public class Inventory_productserial {
    public static final String TABLE_INVENTORY_PRODUCTSERIAL= "inventory_productserial";
    public static final String COLUMN_SERIAL_NO = "serial_no";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SERIAL_DATE = "serial_date";
    public static final String COLUMN_PLENGTH = "plength";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_BARCODE = "barcode";
    public static final String COLUMN_TAG = "tag";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_K = "k";
    public static final String COLUMN_P = "p";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_REDUCE_WEIGHT = "reduce_weight";
    public static final String COLUMN_REDUCE_K = "reduce_k";
    public static final String COLUMN_REDUCE_P = "reduce_p";
    public static final String COLUMN_REDUCE_Y = "reduce_y";
    public static final String COLUMN_JEWEL_WEIGHT = "jewel_weight";
    public static final String COLUMN_JEWEL_K = "jewel_k";
    public static final String COLUMN_JEWEL_P = "jewel_p";
    public static final String COLUMN_JEWEL_Y = "jewel_y";
    public static final String COLUMN_JEWEL_FEE = "jewel_fee";
    public static final String COLUMN_PRODUCTION_FEE = "production_fee";
    public static final String COLUMN_JEWEL_NAME1 = "jewel_name1";
    public static final String COLUMN_JEWEL_NAME2 = "jewel_name2";
    public static final String COLUMN_JEWEL_NAME3 = "jewel_name3";
    public static final String COLUMN_JEWEL_NAME4 = "jewel_name4";
    public static final String COLUMN_JEWEL_NAME5 = "jewel_name5";
    public static final String COLUMN_JEWEL_WEIGHT1 = "jewel_weight1";
    public static final String COLUMN_JEWEL_WEIGHT2 = "jewel_weight2";
    public static final String COLUMN_JEWEL_WEIGHT3 = "jewel_weight3";
    public static final String COLUMN_JEWEL_WEIGHT4 = "jewel_weight4";
    public static final String COLUMN_JEWEL_WEIGHT5 = "jewel_weight5";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_TS = "ts";
    public static final String COLUMN_DELIVERED = "delivered";
    public static final String COLUMN_GOODSID = "goodsid";
    public static final String COLUMN_IS_DELETE = "is_delete";
    public static final String COLUMN_BIN_ID = "bin_id";
    public static final String COLUMN_GOLD_ID = "gold_id";
    public static final String COLUMN_LOCATION_ID = "location_id";
    public static final String COLUMN_PALLET_ID = "pallet_id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_STAFF_ID = "staff_id";
    public static final String COLUMN_SMITH_ID = "smith_id";
    public static final String COLUMN_UOM_ID = "uom_id";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_SERIAL_NO,
            COLUMN_NAME,
            COLUMN_SERIAL_DATE,
            COLUMN_PLENGTH,
            COLUMN_PHOTO,
            COLUMN_BARCODE,
            COLUMN_TAG,
            COLUMN_WEIGHT,
            COLUMN_K,
            COLUMN_P,
            COLUMN_Y,
            COLUMN_REDUCE_WEIGHT,
            COLUMN_REDUCE_K,
            COLUMN_REDUCE_P,
            COLUMN_REDUCE_Y,
            COLUMN_JEWEL_WEIGHT,
            COLUMN_JEWEL_K,
            COLUMN_JEWEL_P,
            COLUMN_JEWEL_Y,
            COLUMN_JEWEL_FEE,
            COLUMN_PRODUCTION_FEE,
            COLUMN_JEWEL_NAME1,
            COLUMN_JEWEL_NAME2,
            COLUMN_JEWEL_NAME3,
            COLUMN_JEWEL_NAME4,
            COLUMN_JEWEL_NAME5,
            COLUMN_JEWEL_WEIGHT1,
            COLUMN_JEWEL_WEIGHT2,
            COLUMN_JEWEL_WEIGHT3,
            COLUMN_JEWEL_WEIGHT4,
            COLUMN_JEWEL_WEIGHT5,
            COLUMN_TS,
            COLUMN_DELIVERED,
            COLUMN_GOODSID,
            COLUMN_IS_DELETE,
            COLUMN_BIN_ID,
            COLUMN_GOLD_ID,
            COLUMN_LOCATION_ID,
            COLUMN_PALLET_ID,
            COLUMN_PRODUCT_ID,
            COLUMN_STAFF_ID,
            COLUMN_SMITH_ID,
            COLUMN_UOM_ID

    };

    private String serial_no;
    private String name;
    private Date serial_date;
    private String plength;
    private String photo;
    private String barcode;
    private String tag;
    private double weight;
    private int k;
    private int p;
    private double y;
    private double reduce_weight;
    private int reduce_k;
    private int reduce_p;
    private double reduce_y;
    private double jewel_weight;
    private int jewel_k;
    private int jewel_p;
    private double jewel_y;
    private double jewel_fee;
    private double production_fee;
    private String jewel_name1;
    private String jewel_name2;
    private String jewel_name3;
    private String jewel_name4;
    private String jewel_name5;
    private double jewel_weight1;
    private double jewel_weight2;
    private double jewel_weight3;
    private double jewel_weight4;
    private double jewel_weight5;
    private String remarks;
    private Date ts;
    private boolean delivered;
    private String goodsid;
    private boolean is_delete;
    private String bin_id;
    private int gold_id;
    private String location_id;
    private String pallet_id;
    private String product_id;
    private String staff_id;
    private String smith_id;
    private String uom_id;

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSerial_date() {
        return serial_date;
    }

    public void setSerial_date(Date serial_date) {
        this.serial_date = serial_date;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getReduce_weight() {
        return reduce_weight;
    }

    public void setReduce_weight(double reduce_weight) {
        this.reduce_weight = reduce_weight;
    }

    public int getReduce_k() {
        return reduce_k;
    }

    public void setReduce_k(int reduce_k) {
        this.reduce_k = reduce_k;
    }

    public int getReduce_p() {
        return reduce_p;
    }

    public void setReduce_p(int reduce_p) {
        this.reduce_p = reduce_p;
    }

    public double getReduce_y() {
        return reduce_y;
    }

    public void setReduce_y(double reduce_y) {
        this.reduce_y = reduce_y;
    }

    public double getJewel_weight() {
        return jewel_weight;
    }

    public void setJewel_weight(double jewel_weight) {
        this.jewel_weight = jewel_weight;
    }

    public int getJewel_k() {
        return jewel_k;
    }

    public void setJewel_k(int jewel_k) {
        this.jewel_k = jewel_k;
    }

    public int getJewel_p() {
        return jewel_p;
    }

    public void setJewel_p(int jewel_p) {
        this.jewel_p = jewel_p;
    }

    public double getJewel_y() {
        return jewel_y;
    }

    public void setJewel_y(double jewel_y) {
        this.jewel_y = jewel_y;
    }

    public double getJewel_fee() {
        return jewel_fee;
    }

    public void setJewel_fee(double jewel_fee) {
        this.jewel_fee = jewel_fee;
    }

    public double getProduction_fee() {
        return production_fee;
    }

    public void setProduction_fee(double production_fee) {
        this.production_fee = production_fee;
    }

    public String getJewel_name1() {
        return jewel_name1;
    }

    public void setJewel_name1(String jewel_name1) {
        this.jewel_name1 = jewel_name1;
    }

    public String getJewel_name2() {
        return jewel_name2;
    }

    public void setJewel_name2(String jewel_name2) {
        this.jewel_name2 = jewel_name2;
    }

    public String getJewel_name3() {
        return jewel_name3;
    }

    public void setJewel_name3(String jewel_name3) {
        this.jewel_name3 = jewel_name3;
    }

    public String getJewel_name4() {
        return jewel_name4;
    }

    public void setJewel_name4(String jewel_name4) {
        this.jewel_name4 = jewel_name4;
    }

    public String getJewel_name5() {
        return jewel_name5;
    }

    public void setJewel_name5(String jewel_name5) {
        this.jewel_name5 = jewel_name5;
    }

    public double getJewel_weight1() {
        return jewel_weight1;
    }

    public void setJewel_weight1(double jewel_weight1) {
        this.jewel_weight1 = jewel_weight1;
    }

    public double getJewel_weight2() {
        return jewel_weight2;
    }

    public void setJewel_weight2(double jewel_weight2) {
        this.jewel_weight2 = jewel_weight2;
    }

    public double getJewel_weight3() {
        return jewel_weight3;
    }

    public void setJewel_weight3(double jewel_weight3) {
        this.jewel_weight3 = jewel_weight3;
    }

    public double getJewel_weight4() {
        return jewel_weight4;
    }

    public void setJewel_weight4(double jewel_weight4) {
        this.jewel_weight4 = jewel_weight4;
    }

    public double getJewel_weight5() {
        return jewel_weight5;
    }

    public void setJewel_weight5(double jewel_weight5) {
        this.jewel_weight5 = jewel_weight5;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public String getBin_id() {
        return bin_id;
    }

    public void setBin_id(String bin_id) {
        this.bin_id = bin_id;
    }

    public int getGold_id() {
        return gold_id;
    }

    public void setGold_id(int gold_id) {
        this.gold_id = gold_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getPallet_id() {
        return pallet_id;
    }

    public void setPallet_id(String pallet_id) {
        this.pallet_id = pallet_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getSmith_id() {
        return smith_id;
    }

    public void setSmith_id(String smith_id) {
        this.smith_id = smith_id;
    }

    public String getUom_id() {
        return uom_id;
    }

    public void setUom_id(String uom_id) {
        this.uom_id = uom_id;
    }

    @Override
    public String toString() {
        return "Inventory_productserial{" +
                "serial_no='" + serial_no + '\'' +
                ", name='" + name + '\'' +
                ", serial_date=" + serial_date +
                ", plength='" + plength + '\'' +
                ", photo='" + photo + '\'' +
                ", barcode='" + barcode + '\'' +
                ", tag='" + tag + '\'' +
                ", weight=" + weight +
                ", k=" + k +
                ", p=" + p +
                ", y=" + y +
                ", reduce_weight=" + reduce_weight +
                ", reduce_k=" + reduce_k +
                ", reduce_p=" + reduce_p +
                ", reduce_y=" + reduce_y +
                ", jewel_weight=" + jewel_weight +
                ", jewel_k=" + jewel_k +
                ", jewel_p=" + jewel_p +
                ", jewel_y=" + jewel_y +
                ", jewel_fee=" + jewel_fee +
                ", production_fee=" + production_fee +
                ", jewel_name1='" + jewel_name1 + '\'' +
                ", jewel_name2='" + jewel_name2 + '\'' +
                ", jewel_name3='" + jewel_name3 + '\'' +
                ", jewel_name4='" + jewel_name4 + '\'' +
                ", jewel_name5='" + jewel_name5 + '\'' +
                ", jewel_weight1=" + jewel_weight1 +
                ", jewel_weight2=" + jewel_weight2 +
                ", jewel_weight3=" + jewel_weight3 +
                ", jewel_weight4=" + jewel_weight4 +
                ", jewel_weight5=" + jewel_weight5 +
                ", remarks='" + remarks + '\'' +
                ", ts=" + ts +
                ", delivered=" + delivered +
                ", goodsid='" + goodsid + '\'' +
                ", is_delete=" + is_delete +
                ", bin_id='" + bin_id + '\'' +
                ", gold_id=" + gold_id +
                ", location_id='" + location_id + '\'' +
                ", pallet_id='" + pallet_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", staff_id='" + staff_id + '\'' +
                ", smith_id=" + smith_id +
                ", uom_id='" + uom_id + '\'' +
                '}';
    }
}

