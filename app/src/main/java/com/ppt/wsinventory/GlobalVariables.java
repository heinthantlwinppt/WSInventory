package com.ppt.wsinventory;

import android.app.Application;

import com.ppt.wsinventory.util.RequestPackage;

/**
 * Created by User on 28/12/2017.
 */

public class GlobalVariables extends Application {

    private RequestPackage requestpackage;
    private String CurrentFragmentTag;

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
}
