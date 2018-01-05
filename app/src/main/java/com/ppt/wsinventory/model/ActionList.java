package com.ppt.wsinventory.model;

/**
 * Created by User on 05/01/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActionList {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tablename")
    @Expose
    private String tablename;
    @SerializedName("actionname")
    @Expose
    private String actionname;
    @SerializedName("displayno")
    @Expose
    private Integer displayno;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("solution")
    @Expose
    private String solution;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public Integer getDisplayno() {
        return displayno;
    }

    public void setDisplayno(Integer displayno) {
        this.displayno = displayno;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}