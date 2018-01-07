package com.ppt.wsinventory.model;

/**
 * Created by arkar on 8/1/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableToDelete {

    @SerializedName("tablename")
    @Expose
    private String tablename;
    @SerializedName("solution")
    @Expose
    private String solution;

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}