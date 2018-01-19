package com.ppt.wsinventory.inventory.model;

import java.util.Date;

/**
 * Created by User on 19/01/2018.
 */

public class Inventory_SmithJob {

    public static final String COLUMN_JOBORDER_NO = "joborder_no";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_SMITH_ID = "smith_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NICKNAME = "nickname";
    public static final String COLUMN_DATE_START = "date_start";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_JOBORDER_NO,
            COLUMN_REMARKS,
            COLUMN_SMITH_ID,
            COLUMN_NAME,
            COLUMN_NICKNAME,
            COLUMN_DATE_START
    };

   private String joborder_no;
   private String remarks;
   private int smith_id;
   private String name;
   private String nickname;
   private Date date_start;

    public String getJoborder_no() {
        return joborder_no;
    }

    public void setJoborder_no(String joborder_no) {
        this.joborder_no = joborder_no;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getSmith_id() {
        return smith_id;
    }

    public void setSmith_id(int smith_id) {
        this.smith_id = smith_id;
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

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    @Override
    public String toString() {
        return "Inventory_SmithJob{" +
                "joborder_no='" + joborder_no + '\'' +
                ", remarks='" + remarks + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
