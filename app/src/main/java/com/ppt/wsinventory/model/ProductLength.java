package com.ppt.wsinventory.model;

/**
 * Created by User on 26/01/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductLength {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("plength")
    @Expose
    private String plength;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("psgroup")
    @Expose
    private Integer psgroup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlength() {
        return plength;
    }

    public void setPlength(String plength) {
        this.plength = plength;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getPsgroup() {
        return psgroup;
    }

    public void setPsgroup(Integer psgroup) {
        this.psgroup = psgroup;
    }

}
