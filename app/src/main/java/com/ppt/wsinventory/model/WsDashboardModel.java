package com.ppt.wsinventory.model;

/**
 * Created by User on 23/02/2018.
 */

public class WsDashboardModel {

    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_ACTION_NAME ="actionname";


    public static final String[] COLUMN_ALL = new String[]
            {
                    COLUMN_TITLE,
                    COLUMN_IMAGE,
                    COLUMN_ACTION_NAME
            };

    private String title;
    private String image;
    private String actionname;

    public String getActionname() {
        return actionname;
    }

    public void setActionname(String actionname) {
        this.actionname = actionname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "WsDashboardModel{" +
                "title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", actionname='" + actionname + '\'' +
                '}';
    }
}
