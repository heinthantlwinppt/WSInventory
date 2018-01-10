package com.ppt.wsinventory.model;

/**
 * Created by User on 08/01/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsDashboard {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("actionname")
    @Expose
    private String actionname;
    @SerializedName("groupname")
    @Expose
    private String groupname;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("displayno")
    @Expose
    private Integer displayno;
    @SerializedName("is_delete")
    @Expose
    private Boolean isDelete;
    @SerializedName("screen")
    @Expose
    private Integer screen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getDisplayno() {
        return displayno;
    }

    public void setDisplayno(Integer displayno) {
        this.displayno = displayno;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getScreen() {
        return screen;
    }

    public void setScreen(Integer screen) {
        this.screen = screen;
    }

}