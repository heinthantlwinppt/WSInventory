package com.ppt.wsinventory.model;

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
            COLUMN_IS_DELETE

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
    private String is_delete ;

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

    public String isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "Inventory_products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designname='" + designname + '\'' +
                ", photo='" + photo + '\'' +
                ", barcode='" + barcode + '\'' +
                ", tag='" + tag + '\'' +
                ", baseuom='" + baseuom + '\'' +
                ", minqty=" + minqty +
                ", maxqty=" + maxqty +
                ", active=" + active +
                ", is_delete=" + is_delete +
                '}';
    }
}
