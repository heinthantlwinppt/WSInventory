package com.ppt.wsinventory.model;

/**
 * Created by User on 21/02/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NoSeries {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("module_name")
    @Expose
    private String moduleName;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("diy")
    @Expose
    private Boolean diy;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("last")
    @Expose
    private Integer last;
    @SerializedName("last_diy")
    @Expose
    private Integer lastDiy;
    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("date_end")
    @Expose
    private String dateEnd;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("solution")
    @Expose
    private String solution;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Boolean getDiy() {
        return diy;
    }

    public void setDiy(Boolean diy) {
        this.diy = diy;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public Integer getLastDiy() {
        return lastDiy;
    }

    public void setLastDiy(Integer lastDiy) {
        this.lastDiy = lastDiy;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
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

}