package com.ppt.wsinventory.model.administration.design;

/**
 * Created by User on 22/01/2018.
 */

public class AdministrationWsimagestype {

    public static final String TABLE_ADMINISTRATION_WSIMAGESTYPE = "administration_wsimagestype";
    public static final String COLUMN_NAME = "name";

    public static final String[] COLUMN_ALL = new String[]{
            COLUMN_NAME,

    };

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdministrationWsimagestype{" +
                "name='" + name + '\'' +
                '}';
    }
}
