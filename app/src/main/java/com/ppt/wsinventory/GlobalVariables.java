package com.ppt.wsinventory;

import android.app.Application;

import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.util.RequestPackage;

import java.util.Date;
import java.util.List;

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

//    public AdministrationStaff getUser() {
//        return mUser;
//    }
//
//    public void setUser(Users user) {
//        this.mUser = user;
//    }

}
