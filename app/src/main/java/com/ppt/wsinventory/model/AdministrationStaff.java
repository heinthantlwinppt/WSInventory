package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by User on 03/01/2018.
 */

public class AdministrationStaff {

    public static final String TABLE_NAME = "administration_staff";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STAFF_ID  = "staff_id";
    public static final String COLUMN_STAFF_NAME  = "staff_name";
    public static final String COLUMN_FATHER_NAME  = "father_name";
    public static final String COLUMN_NICK_NAME  = "nick_name";
    public static final String COLUMN_NRC_NO  = "nrc_no";
    public static final String COLUMN_ADDRESS  = "address";
    public static final String COLUMN_PHONE_NO  = "phone_no";
    public static final String COLUMN_HOME_PHONE_NO  = "home_phone_no";
    public static final String COLUMN_STAFF_PHOTO  = "staff_photo";
    public static final String COLUMN_DATE_JOINED  = "date_joined";
    public static final String COLUMN_DATE_LEFT  = "date_left";
    public static final String COLUMN_USER_ID  = "user_id";
    public static final String COLUMN_ACTIVE  = "active";
    public static final String COLUMN_ROLE_ID  = "role_id";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_STAFF_ID ,
            COLUMN_STAFF_NAME ,
            COLUMN_FATHER_NAME ,
            COLUMN_NICK_NAME ,
            COLUMN_NRC_NO ,
            COLUMN_ADDRESS ,
            COLUMN_PHONE_NO ,
            COLUMN_HOME_PHONE_NO ,
            COLUMN_STAFF_PHOTO ,
            COLUMN_DATE_JOINED ,
            COLUMN_DATE_LEFT ,
            COLUMN_USER_ID ,
            COLUMN_ACTIVE ,
            COLUMN_ROLE_ID
    };

    private String id ;
    private String staff_id ;
    private String staff_name ;
    private String father_name ;
    private String nick_name ;
    private String nrc_no ;
    private String address ;
    private String phone_no ;
    private String home_phone_no ;
    private String staff_photo ;
    private Date date_joined ;
    private Date date_left ;
    private int user_id ;
    private boolean active ;
    private String role_id ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getNrc_no() {
        return nrc_no;
    }

    public void setNrc_no(String nrc_no) {
        this.nrc_no = nrc_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getHome_phone_no() {
        return home_phone_no;
    }

    public void setHome_phone_no(String home_phone_no) {
        this.home_phone_no = home_phone_no;
    }

    public String getStaff_photo() {
        return staff_photo;
    }

    public void setStaff_photo(String staff_photo) {
        this.staff_photo = staff_photo;
    }

    public Date getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(Date date_joined) {
        this.date_joined = date_joined;
    }

    public Date getDate_left() {
        return date_left;
    }

    public void setDate_left(Date date_left) {
        this.date_left = date_left;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "AdministrationStaff{" +
                "id='" + id + '\'' +
                ", staff_id='" + staff_id + '\'' +
                ", staff_name='" + staff_name + '\'' +
                ", father_name='" + father_name + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", nrc_no='" + nrc_no + '\'' +
                ", address='" + address + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", home_phone_no='" + home_phone_no + '\'' +
                ", staff_photo='" + staff_photo + '\'' +
                ", role_id='" + role_id + '\'' +
                '}';
    }
}
