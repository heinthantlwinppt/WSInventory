package com.ppt.wsinventory.model;

/**
 * Created by User on 23/02/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StaffRole {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("staff")
    @Expose
    private String staff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

}