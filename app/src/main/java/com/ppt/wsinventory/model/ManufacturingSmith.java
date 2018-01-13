package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/13/2018.
 */

public class ManufacturingSmith {
    public static final String TABLE_MANUFACTURING_SMITH = "manufacturing_smith";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NICKNAME = "nickname";
    public static final String COLUMN_NRC = "nrc";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_REFNAME = "refname";
    public static final String COLUMN_MIXJOB = "mixjob";
    public static final String COLUMN_K = "k";
    public static final String COLUMN_P = "p";
    public static final String COLUMN_Y = "y";
    public static final String COLUMN_G = "g";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_DATE_JOINED = "date_joined";
    public static final String COLUMN_DATE_END = "date_end";
    public static final String COLUMN_IS_SMITH2 = "is_smith2";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_GOLDSAVING = "goldsaving";
    public static final String COLUMN_INHANDJOB = "inhandjob";
    public static final String COLUMN_PHOTO = "photo";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_NICKNAME,
            COLUMN_NRC,
            COLUMN_ADDRESS,
            COLUMN_PHONE,
            COLUMN_REFNAME,
            COLUMN_MIXJOB,
            COLUMN_K,
            COLUMN_P,
            COLUMN_Y,
            COLUMN_G,
            COLUMN_COST,
            COLUMN_DATE_JOINED,
            COLUMN_DATE_END,
            COLUMN_IS_SMITH2,
            COLUMN_ACTIVE,
            COLUMN_GOLDSAVING,
            COLUMN_INHANDJOB,
            COLUMN_PHOTO

    };

    private int id;
    private String name;
    private String nickname;
    private String nrc;
    private String address;
    private String phone;
    private String refname;
    private int mixjob;
    private int k;
    private int p;
    private double y;
    private double g;
    private double cost;
    private Date date_joined;
    private Date date_end;
    private boolean is_smith2;
    private boolean active;
    private double goldsaving;
    private int inhandjob;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRefname() {
        return refname;
    }

    public void setRefname(String refname) {
        this.refname = refname;
    }

    public int getMixjob() {
        return mixjob;
    }

    public void setMixjob(int mixjob) {
        this.mixjob = mixjob;
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

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(Date date_joined) {
        this.date_joined = date_joined;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public boolean isIs_smith2() {
        return is_smith2;
    }

    public void setIs_smith2(boolean is_smith2) {
        this.is_smith2 = is_smith2;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getGoldsaving() {
        return goldsaving;
    }

    public void setGoldsaving(double goldsaving) {
        this.goldsaving = goldsaving;
    }

    public int getInhandjob() {
        return inhandjob;
    }

    public void setInhandjob(int inhandjob) {
        this.inhandjob = inhandjob;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "ManufacturingSmith{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", nrc='" + nrc + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", refname='" + refname + '\'' +
                ", mixjob=" + mixjob +
                ", k=" + k +
                ", p=" + p +
                ", y=" + y +
                ", g=" + g +
                ", cost=" + cost +
                ", date_joined=" + date_joined +
                ", date_end=" + date_end +
                ", is_smith2=" + is_smith2 +
                ", active=" + active +
                ", goldsaving=" + goldsaving +
                ", inhandjob=" + inhandjob +
                ", photo='" + photo + '\'' +
                '}';
    }
}
