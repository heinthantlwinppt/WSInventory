package com.ppt.wsinventory.model;

/**
 * Created by User on 08/01/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Settings {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("h1")
    @Expose
    private String h1;
    @SerializedName("h2")
    @Expose
    private String h2;
    @SerializedName("h3")
    @Expose
    private String h3;
    @SerializedName("h4")
    @Expose
    private String h4;
    @SerializedName("t1")
    @Expose
    private String t1;
    @SerializedName("t2")
    @Expose
    private String t2;
    @SerializedName("t3")
    @Expose
    private String t3;
    @SerializedName("t4")
    @Expose
    private String t4;
    @SerializedName("dateformat")
    @Expose
    private String dateformat;
    @SerializedName("timeformat")
    @Expose
    private String timeformat;
    @SerializedName("datetimeformat")
    @Expose
    private String datetimeformat;
    @SerializedName("system_date")
    @Expose
    private Date systemDate;
    @SerializedName("dashboarditempwith")
    @Expose
    private int dashboarditempwith;
    @SerializedName("dashboarditemlwith")
    @Expose
    private int dashboarditemlwith;
    @SerializedName("dashboardicon")
    @Expose
    private String dashboardicon;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("solution")
    @Expose
    private String solution;
    @SerializedName("staff")
    @Expose
    private String staff;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("devicetype")
    @Expose
    private String devicetype;
    @SerializedName("weighscales")
    @Expose
    private String weighscales;
    @SerializedName("rfidreader_mac")
    @Expose
    private String rfidreader_mac;
    @SerializedName("rfidreader_name")
    @Expose
    private String rfidreader_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public Date getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Date systemDate) {
        this.systemDate = systemDate;
    }

    public int getDashboarditempwith() {
        return dashboarditempwith;
    }

    public void setDashboarditempwith(int dashboarditempwith) {
        this.dashboarditempwith = dashboarditempwith;
    }

    public int getDashboarditemlwith() {
        return dashboarditemlwith;
    }

    public void setDashboarditemlwith(int dashboarditemlwith) {
        this.dashboarditemlwith = dashboarditemlwith;
    }

    public String getDashboardicon() {
        return dashboardicon;
    }

    public void setDashboardicon(String dashboardicon) {
        this.dashboardicon = dashboardicon;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getWeighscales() {
        return weighscales;
    }

    public void setWeighscales(String weighscales) {
        this.weighscales = weighscales;
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
}