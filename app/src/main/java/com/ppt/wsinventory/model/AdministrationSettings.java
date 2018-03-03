package com.ppt.wsinventory.model;

import java.util.Date;

/**
 * Created by User on 03/01/2018.
 */

public class AdministrationSettings {

    public static final String TABLE_ADMINISTRATION_SETTINGS = "administration_settings";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_H1 = "h1";
    public static final String COLUMN_H2 = "h2";
    public static final String COLUMN_H3 = "h3";
    public static final String COLUMN_H4 = "h4";
    public static final String COLUMN_T1 = "t1";
    public static final String COLUMN_T2 = "t2";
    public static final String COLUMN_T3 = "t3";
    public static final String COLUMN_T4 = "t4";
    public static final String COLUMN_DATEFORMAT = "dateformat";
    public static final String COLUMN_TIMEFORMAT = "timeformat";
    public static final String COLUMN_DATETIMEFORMAT = "datetimeformat";
    public static final String COLUMN_SYSTTEM_DATE = "system_date";
    public static final String COLUMN_ACTIVE = "active";
    public static final String COLUMN_LOCATION_ID = "location_id";
    public static final String COLUMN_SOLUTION_ID = "solution_id";
    public static final String COLUMN_STAFF_ID = "staff_id";
    public static final String COLUMN_WEIGHSCALES_ID = "weighscales_id";
    public static final String COLUMN_DASHBOARDITEMLWITH = "dashboarditemlwith";
    public static final String COLUMN_DASHBOARDITEMPWITH = "dashboarditempwith";
    public static final String COLUMN_DASHBOARDICON = "dashboardicon";
    public static final String COLUMN_DEVICE_ID = "device_id";
    public static final String COLUMN_DEVICETYPE_ID = "devicetype_id";
    public static final String COLUMN_RFIDREADER_MAC = "rfidreader_mac";
    public static final String COLUMN_RFIDREADER_NAME = "rfidreader_name";

    public static final String[] COLUMN_ALL = new String[]{

            COLUMN_ID,
            COLUMN_H1,
            COLUMN_H2,
            COLUMN_H3,
            COLUMN_H4,
            COLUMN_T1,
            COLUMN_T2,
            COLUMN_T3,
            COLUMN_T4,
            COLUMN_DATEFORMAT,
            COLUMN_TIMEFORMAT,
            COLUMN_DATETIMEFORMAT,
            COLUMN_SYSTTEM_DATE,
            COLUMN_ACTIVE,
            COLUMN_LOCATION_ID,
            COLUMN_SOLUTION_ID,
            COLUMN_STAFF_ID,
            COLUMN_WEIGHSCALES_ID,
            COLUMN_DASHBOARDITEMLWITH,
            COLUMN_DASHBOARDITEMPWITH,
            COLUMN_DASHBOARDICON,
            COLUMN_DEVICE_ID,
            COLUMN_DEVICETYPE_ID,
            COLUMN_RFIDREADER_MAC,
            COLUMN_RFIDREADER_NAME
    };

    private String id;
    private String h1;
    private String h2;
    private String h3;
    private String h4;
    private String t1;
    private String t2;
    private String t3;
    private String t4;
    private String dateformat;
    private String timeformat;
    private String datetimeformat;
    private Date system_date;
    private boolean active;
    private String location_id;
    private String solution_id;
    private String staff_id;
    private String weighscales_id;
    private int dashboarditemlwith;
    private int dashboarditempwith;
    private String dashboardicon;
    private String devicetype_id;
    private String device_id;
    private String rfidreader_mac;
    private String rfidreader_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public String getH4() {
        return h4;
    }

    public void setH4(String h4) {
        this.h4 = h4;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

    public String getT4() {
        return t4;
    }

    public void setT4(String t4) {
        this.t4 = t4;
    }

    public String getDateformat() {
        return dateformat;
    }

    public void setDateformat(String dateformat) {
        this.dateformat = dateformat;
    }

    public String getTimeformat() {
        return timeformat;
    }

    public void setTimeformat(String timeformat) {
        this.timeformat = timeformat;
    }

    public String getDatetimeformat() {
        return datetimeformat;
    }

    public void setDatetimeformat(String datetimeformat) {
        this.datetimeformat = datetimeformat;
    }

    public Date getSystem_date() {
        return system_date;
    }

    public void setSystem_date(Date system_date) {
        this.system_date = system_date;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getSolution_id() {
        return solution_id;
    }

    public void setSolution_id(String solution_id) {
        this.solution_id = solution_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getWeighscales_id() {
        return weighscales_id;
    }

    public void setWeighscales_id(String weighscales_id) {
        this.weighscales_id = weighscales_id;
    }

    public int getDashboarditemlwith() {
        return dashboarditemlwith;
    }

    public void setDashboarditemlwith(int dashboarditemlwith) {
        this.dashboarditemlwith = dashboarditemlwith;
    }

    public int getDashboarditempwith() {
        return dashboarditempwith;
    }

    public void setDashboarditempwith(int dashboarditempwith) {
        this.dashboarditempwith = dashboarditempwith;
    }

    public String getDashboardicon() {
        return dashboardicon;
    }

    public void setDashboardicon(String dashboardicon) {
        this.dashboardicon = dashboardicon;
    }

    public String getDevicetype_id() {
        return devicetype_id;
    }

    public void setDevicetype_id(String devicetype_id) {
        this.devicetype_id = devicetype_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getRfidreader_mac() {
        return rfidreader_mac;
    }

    public void setRfidreader_mac(String rfidreader_mac) {
        this.rfidreader_mac = rfidreader_mac;
    }

    public String getRfidreader_name() {
        return rfidreader_name;
    }

    public void setRfidreader_name(String rfidreader_name) {
        this.rfidreader_name = rfidreader_name;
    }

    @Override
    public String toString() {
        return "AdministrationSettings{" +
                "id='" + id + '\'' +
                ", h1='" + h1 + '\'' +
                ", h2='" + h2 + '\'' +
                ", h3='" + h3 + '\'' +
                ", h4='" + h4 + '\'' +
                ", t1='" + t1 + '\'' +
                ", t2='" + t2 + '\'' +
                ", t3='" + t3 + '\'' +
                ", t4='" + t4 + '\'' +
                ", dateformat='" + dateformat + '\'' +
                ", timeformat='" + timeformat + '\'' +
                ", datetimeformat='" + datetimeformat + '\'' +
                ", system_date=" + system_date +
                ", active=" + active +
                ", location_id='" + location_id + '\'' +
                ", solution_id='" + solution_id + '\'' +
                ", staff_id='" + staff_id + '\'' +
                ", weighscales_id='" + weighscales_id + '\'' +
                ", dashboarditemlwith=" + dashboarditemlwith +
                ", dashboarditempwith=" + dashboarditempwith +
                ", dashboardicon='" + dashboardicon + '\'' +
                ", devicetype_id='" + devicetype_id + '\'' +
                ", device_id='" + device_id + '\'' +
                ", rfidreader_mac='" + rfidreader_mac + '\'' +
                ", rfidreader_name='" + rfidreader_name + '\'' +
                '}';
    }
}

