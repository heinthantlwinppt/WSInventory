package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/15/2018.
 */

public class Manufacturing_smith_jobtype {
    public static final String TABLE_MANUFACTURING_SMITH_JOBTYPE = "manufacturing_smith_jobtype";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_JOBTYPE_GROUP = "jobtype_group";
    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_DESCRIPTION,
            COLUMN_ACTIVE,
            COLUMN_JOBTYPE_GROUP
    };

    private int id;
    private String name;
    private String description;
    private boolean active;
    private String jobtype_group;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getJobtype_group() {
        return jobtype_group;
    }

    public void setJobtype_group(String jobtype_group) {
        this.jobtype_group = jobtype_group;
    }

    @Override
    public String toString() {
        return "Manufacturing_smith_jobtype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", jobtype_group='" + jobtype_group + '\'' +
                '}';
    }
}
