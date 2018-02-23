package com.ppt.wsinventory.model;

/**
 * Created by User on 03/01/2018.
 */

public class AdministrationWsdashboard {

    public static final String TABLE_ADMINISTRATION_WSDASHBOARD = "administration_wsdashboard";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IS_FOLDER = "is_folder";
    public static final String COLUMN_PARENT_ID = "parent_id";
    public static final String COLUMN_ACTIONNAME = "actionname";
    public static final String COLUMN_GROUPNAME = "groupname";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DELETE = "is_delete";
    public static final String COLUMN_DISPLAYNO = "displayno";
    public static final String COLUMN_SCREEN_ID = "screen_id";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_ID ,
            COLUMN_TITLE ,
            COLUMN_IS_FOLDER ,
            COLUMN_PARENT_ID ,
            COLUMN_ACTIONNAME ,
            COLUMN_GROUPNAME ,
            COLUMN_IMAGE ,
            COLUMN_TIMESTAMP ,
            COLUMN_DELETE ,
            COLUMN_DISPLAYNO ,
            COLUMN_SCREEN_ID
    };

    private String id;
    private String title;
    private String actionname;
    private boolean is_folder;
    private int parent_id;
    private String groupname;
    private String image;
    private String timestamp;
    private boolean is_delete;
    private int displayno;
    private int screen_id;

    public String getId() {
        return id;
    }

    public boolean isIs_folder() {
        return is_folder;
    }

    public void setIs_folder(boolean is_folder) {
        this.is_folder = is_folder;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public boolean isIs_delete() {
        return is_delete;
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

    public boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public int getDisplayno() {
        return displayno;
    }

    public void setDisplayno(int displayno) {
        this.displayno = displayno;
    }

    public int getScreen_id() {
        return screen_id;
    }

    public void setScreen_id(int screen_id) {
        this.screen_id = screen_id;
    }

    @Override
    public String toString() {
        return "AdministrationWsdashboard{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", actionname='" + actionname + '\'' +
                ", groupname='" + groupname + '\'' +
                ", image='" + image + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", screen_id='" + screen_id + '\'' +
                '}';
    }
}
