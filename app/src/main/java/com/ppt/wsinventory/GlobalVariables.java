package com.ppt.wsinventory;

import android.app.Application;

import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationSettings;
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
    private String smith_id;
    private String product_no;
    private String smith_name;
    private int parientid;
    private int currentid;
    private String Binid;
    private String palletId;
    private AdministrationSettings wssetting;
    private boolean isNewUser = false;

    private boolean showalert= false;

    public boolean isShowalert() {
        return showalert;
    }

    public void setShowalert(boolean showalert) {
        this.showalert = showalert;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean newUser) {
        isNewUser = newUser;
    }

    public AdministrationSettings getWssetting() {
        return wssetting;
    }

    public void setWssetting(AdministrationSettings wssetting) {
        this.wssetting = wssetting;
    }

    public String getPalletId() {
        return palletId;
    }

    public void setPalletId(String palletId) {
        this.palletId = palletId;
    }

    public String getBinid() {
        return Binid;
    }

    public void setBinid(String binid) {
        Binid = binid;
    }

    public int getParentid() {
        return parientid;
    }

    public void setParentid(int parientid) {
        this.parientid = parientid;
    }

    public int getCurrentid() {
        return currentid;
    }

    public void setCurrentid(int currentid) {
        this.currentid = currentid;
    }

    public String getSmith_name() {
        return smith_name;
    }

    public void setSmith_name(String smith_name) {
        this.smith_name = smith_name;
    }
    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getSmith_id() {
        return smith_id;
    }

    public void setSmith_id(String smith_id) {
        this.smith_id = smith_id;
    }

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
