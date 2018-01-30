package com.ppt.wsinventory;

import android.app.Application;

import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.util.RequestPackage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 28/12/2017.
 */

public class GlobalVariables extends Application {

    private RequestPackage requestpackage;
    private String CurrentFragmentTag;
    private String RequestMessage;
    private String ResponseMessage;
    private List<ActionList> actionLists;
    private Date ts;
    private String solutionname = "SALES-HH";
    private String deviceid ;
    private AdministrationStaff mStaff;
    private Map<String, String> translation = new HashMap<String, String>();
    private String productname;
    private String groupname;
    private String subgroupname;

    public String getSolutionname() {
        return solutionname;
    }

    public void setSolutionname(String solutionname) {
        this.solutionname = solutionname;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public RequestPackage getRequestpackage() {
        return requestpackage;
    }

    public void setRequestpackage(RequestPackage requestpackage) {
        this.requestpackage = requestpackage;
    }

    public String getCurrentFragmentTag() {
        return CurrentFragmentTag;
    }

    public void setCurrentFragmentTag(String currentFragmentTag) {
        CurrentFragmentTag = currentFragmentTag;
    }

    public String getRequestMessage() {
        return RequestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        RequestMessage = requestMessage;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    public List<ActionList> getActionLists() {
        return actionLists;
    }

    public void setActionLists(List<ActionList> actionLists) {
        this.actionLists = actionLists;
    }

    public AdministrationStaff getmStaff() {
        return mStaff;
    }

    public void setmStaff(AdministrationStaff mStaff) {
        this.mStaff = mStaff;
    }

    public String getTranslation(String key) {

        try {
            return translation.get(key.toUpperCase()).toString();
        } catch (Exception e) {
            return key;
        }
    }

    public void addTranslation(String key, String value) {
        this.translation.put(key.toUpperCase(), value);
    }


    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getSubgroupname() {
        return subgroupname;
    }

    public void setSubgroupname(String subgroupname) {
        this.subgroupname = subgroupname;
    }
}
