package com.ppt.wsinventory.model;

/**
 * Created by User on 07/02/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jewellength {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("jewelshape")
    @Expose
    private Integer jewelshape;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getJewelshape() {
        return jewelshape;
    }

    public void setJewelshape(Integer jewelshape) {
        this.jewelshape = jewelshape;
    }

}