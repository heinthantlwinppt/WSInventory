package com.ppt.wsinventory.model.administration.design;

import java.util.Date;

/**
 * Created by User on 22/01/2018.
 */

public class AdministrationWsimages {

    public static final String TABLE_ADMINISTRATION_WSIMAGES = "administration_wsimages";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME  = "name";
    public static final String COLUMN_PATH  = "path";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DELETE  = "delete";
    public static final String COLUMN_SOLUTION_ID = "solution_id";
    public static final String COLUMN_TYPE_ID = "type_id";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID ,
            COLUMN_NAME,
            COLUMN_PATH ,
            COLUMN_TIMESTAMP ,
            COLUMN_DELETE ,
            COLUMN_SOLUTION_ID ,
            COLUMN_TYPE_ID
    };

    private String id ;
    private String name ;
    private String path ;
    private Date timestamp ;
    private boolean is_delete ;
    private String solution_id ;
    private String type_id ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public String getSolution_id() {
        return solution_id;
    }

    public void setSolution_id(String solution_id) {
        this.solution_id = solution_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "AdministrationWsimages{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", solution_id='" + solution_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}
