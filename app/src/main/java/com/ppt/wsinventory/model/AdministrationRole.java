package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by ThinkCentre on 1/9/2018.
 */

public class AdministrationRole {
    public static final String TABLE_ADMINISTRATION_ROLE = "administration_role";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ROLE_NAME  = "role_name";
    public static final String COLUMN_ACTIVE  = "active";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_ROLE_NAME ,
            COLUMN_ACTIVE
    };

    private String id ;
    private String role_name ;
    private boolean active ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "AdministrationRole{" +
                "role_name='" + role_name + '\'' +
                '}';
    }
}

