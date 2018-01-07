package com.ppt.wsinventory.model;

/**
 * Created by arkar on 8/1/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Solution {

    @SerializedName("solution_name")
    @Expose
    private String solutionName;
    @SerializedName("active")
    @Expose
    private Boolean active;

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}