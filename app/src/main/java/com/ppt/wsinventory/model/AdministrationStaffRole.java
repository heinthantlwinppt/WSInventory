package com.ppt.wsinventory.model;

import android.app.DatePickerDialog;

import com.ppt.wsinventory.ProductInfoDialog;

/**
 * Created by User on 23/02/2018.
 */

public class AdministrationStaffRole
{
    public static final String TABLE_ADMINISTRATION_STAFF_ROLE ="administration_staffrole";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_STAFF = "staff";
    public static final String[] COLUMN_ALL = new String[]
            {
                    COLUMN_ID,
                    COLUMN_ACTIVE,
                    COLUMN_ROLE,
                    COLUMN_STAFF
            };


    private String id;
    private boolean active;
    private String role;
    private String staff;

    public String getStaff() {

        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AdministrationStaffRole{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                ", staff='" + staff + '\'' +
                '}';
    }
}
