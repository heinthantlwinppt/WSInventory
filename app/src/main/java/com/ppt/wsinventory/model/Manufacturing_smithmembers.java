package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/15/2018.
 */

public class Manufacturing_smithmembers {
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
}
