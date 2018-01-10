package com.ppt.wsinventory.model;

/**
 * Created by arkar on 2/1/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiModel {
    public static String GETACTIONLIST = "getActionList";
    public static String DOSYNC = "dosync";
    public static String SENDNEWACTION = "sendnewaction";
    public static String GETTABLESTODELETE = "getTablesToDelete";
    public static String GETSOLUTIONS = "getSolutions";
    public static String GETSETTINGS = "getSettings";
    public static String GETWSDASHBOARD = "getWsDashboard";
    public static String GETWSSTAFF = "getStaffList";
    public static String GETWSROLE = "getRoleList";
    public static String TYPE_GET = "get";


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("message")
    @Expose
    private String message;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiModel(Integer id, String name, String type, String message) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.message = message;
    }
}