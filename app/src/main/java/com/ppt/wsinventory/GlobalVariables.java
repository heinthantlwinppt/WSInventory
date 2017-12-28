package com.ppt.wsinventory;

import android.app.Application;

/**
 * Created by User on 28/12/2017.
 */

public class GlobalVariables extends Application {

    private String CurrentFragmentTag;

    public String getCurrentFragmentTag() {
        return CurrentFragmentTag;
    }

    public void setCurrentFragmentTag(String currentFragmentTag) {
        CurrentFragmentTag = currentFragmentTag;
    }
}
