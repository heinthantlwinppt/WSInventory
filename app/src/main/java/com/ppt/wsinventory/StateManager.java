package com.ppt.wsinventory;

/**
 * Created by User on 28/12/2017.
 */

class StateManager {

    private String CurrentFragmentTag;

    public String getCurrentFragmentTag() {
        return CurrentFragmentTag;
    }

    public void setCurrentFragmentTag(String currentFragmentTag) {
        CurrentFragmentTag = currentFragmentTag;
    }

    private static final StateManager ourInstance = new StateManager();

    static StateManager getInstance() {
        return ourInstance;
    }

    private StateManager() {
    }
}
