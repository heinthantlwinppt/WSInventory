package com.ppt.wsinventory.inventory.model;

import java.util.Date;

/**
 * Created by User on 19/01/2018.
 */

public class Inventory_SmithJob {
    public static final String COLUMN_JOBORDER_NO = "joborder_no";
    public static final String COLUMN_REMARKS = "remarks";
    public static final String COLUMN_SMITH_ID = "smith_id";
    public static final String COLUMN_SMITH_NAME = "smith_name";
    public static final String COLUMN_SMITH_NICKNAME = "smith_nickname";
    public static final String COLUMN_DATE_START = "date_start";
    public static final String COLUMN_DATE_END = "date_end";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_JOBTYPE_NAME = "Jobtype_name";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_JOBORDER_NO,
            COLUMN_REMARKS,
            COLUMN_SMITH_ID,
            COLUMN_SMITH_NAME,
            COLUMN_SMITH_NICKNAME,
            COLUMN_DATE_START,
            COLUMN_DESCRIPTION,
            COLUMN_JOBTYPE_NAME,
            COLUMN_DATE_END
    };

   private String joborder_no;
   private String remarks;
   private int smith_id;
   private String smith_name;
   private String smith_nickname;
   private Date date_start;
   private String description;
   private String jobtype_name;
   private Date date_end;

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
        return smith_name;
    }

    public void setName(String name) {
        this.smith_name = name;
    }

    public String getNickname() {
        return smith_nickname;
    }

    public void setNickname(String nickname) {
        this.smith_nickname = nickname;
    }

    public Date getDate_start() { return date_start; }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobtype_name() {
        return jobtype_name;
    }

    public void setJobtype_name(String jobtype_name) {
        this.jobtype_name = jobtype_name;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    @Override
    public String toString() {
        return "Inventory_SmithJob{" +
                "joborder_no='" + joborder_no + '\'' +
                ", remarks='" + remarks + '\'' +
                ", smith_name='" + smith_name + '\'' +
                ", smith_nickname='" + smith_nickname + '\'' +
                ", description='" + description + '\'' +
                ", jobtype_name='" + jobtype_name + '\'' +
                '}';
    }
}
