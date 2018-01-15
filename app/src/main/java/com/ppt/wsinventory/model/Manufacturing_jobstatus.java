package com.ppt.wsinventory.model;

/**
 * Created by ThinkCentre on 1/15/2018.
 */

public class Manufacturing_jobstatus {
    public static final String TABLE_MANUFACTURING_JOBSTATUS = "manufacturing_jobstatus";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STATUS_TYPE = "status_type";
    public static final String COLUMN_ACTIVE = "active";
    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_DESCRIPTION,
            COLUMN_STATUS_TYPE,
            COLUMN_ACTIVE,

    };

    private int id;
    private String name;
    private String description;
    private int status_type;
    private boolean active;

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

    public int getStatus_type() {
        return status_type;
    }

    public void setStatus_type(Integer status_type) {
        this.status_type = status_type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Manufacturing_jobstatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status_type='" + status_type + '\'' +
                ", active=" + active +
                '}';
    }
}
