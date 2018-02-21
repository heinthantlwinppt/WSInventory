package com.ppt.wsinventory.model;

import com.ppt.wsinventory.ProductInfoDialog;

import java.io.File;

/**
 * Created by User on 21/02/2018.
 */

public class AdministratoryNoSerie
{
    public static final String TABLE_ADMINISTRATORY_NOSERIES="administration_noseries";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CLIENT_ID = "client_id";
    public static final String COLUMN_MODULE_NAME = "module_name";
    public static final String COLUMN_PREFIX = "prefix";
    public static final String COLUMN_DIY = "diy";
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_LAST ="last";
    public static final String COLUMN_LAST_DIY = "last_diy";
    public static final String COLUMN_DATE_START = "date_start";
    public static final String COLUMN_DATE_END = "date_end";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_SOLUTION = "solution";

    public static final String[] COLUMN_ALL = new String[]
            {
                    COLUMN_ID,
                    COLUMN_CLIENT_ID,
                    COLUMN_MODULE_NAME,
                    COLUMN_PREFIX,
                    COLUMN_DIY,
                    COLUMN_LENGTH,
                    COLUMN_LAST,
                    COLUMN_LAST_DIY,
                    COLUMN_DATE_START,
                    COLUMN_DATE_END,
                    COLUMN_ACTIVE,
                    COLUMN_SOLUTION
            };

    private String id;
    private String client_id;
    private String module_name;
    private String prefix;
    private boolean diy;
    private int length;
    private int last;
    private int lastDiy;
    private String dateStart;
    private String dateend;
    private boolean active;
    private String solution;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public boolean isDiy() {
        return diy;
    }

    public void setDiy(boolean diy) {
        this.diy = diy;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getLastDiy() {
        return lastDiy;
    }

    public void setLastDiy(int lastDiy) {
        this.lastDiy = lastDiy;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateend() {
        return dateend;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "AdministratoryNoSerie{" +
                "id='" + id + '\'' +
                ", client_id='" + client_id + '\'' +
                ", module_name='" + module_name + '\'' +
                ", prefix='" + prefix + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }
}
