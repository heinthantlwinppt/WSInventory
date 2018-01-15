package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/15/2018.
 */

public class Manufacturing_smithmembers {
    public static final String TABLE_MANUFACTURING_SMITHMEMBERS = "manufacturing_smithmembers";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NICKNAME = "nickname";
    public static final String COLUMN_NRC = "nrc";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_SMITH_ID = "smith_id";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_NICKNAME,
            COLUMN_NRC,
            COLUMN_ADDRESS,
            COLUMN_PHONE,
            COLUMN_ACTIVE,
            COLUMN_SMITH_ID,
            COLUMN_PHOTO

    };

    private int id;
    private String name;
    private String nickname;
    private String nrc;
    private String address;
    private String phone;
    private String smith_id;
    private boolean active;
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

    public String getSmith_id() {
        return smith_id;
    }

    public void setSmith_id(String smith_id) {
        this.smith_id = smith_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Manufacturing_smithmembers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", nrc='" + nrc + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", smith_id='" + smith_id + '\'' +
                ", active=" + active +
                ", photo='" + photo + '\'' +
                '}';
    }
}
