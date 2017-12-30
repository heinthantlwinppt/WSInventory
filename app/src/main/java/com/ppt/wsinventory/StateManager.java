package com.ppt.wsinventory;

import com.ppt.wsinventory.model.Item;

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

    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    private static final StateManager ourInstance = new StateManager();

    static StateManager getInstance() {
        return ourInstance;
    }

    private StateManager() {
    }
}
