package com.ppt.wsinventory;

import com.ppt.wsinventory.model.Item;
import com.ppt.wsinventory.model.Manufacturing_smith_joborder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 28/12/2017.
 */

class StateManager {

    private String CurrentFragmentTag;
    private Map<String, String> translation = new HashMap<String, String>();
    private Manufacturing_smith_joborder mSelectedSmithJobOrder;

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

    public Manufacturing_smith_joborder getmSelectedSmithJobOrder() {
        return mSelectedSmithJobOrder;
    }

    public void setmSelectedSmithJobOrder(Manufacturing_smith_joborder mSelectedSmithJobOrder) {
        this.mSelectedSmithJobOrder = mSelectedSmithJobOrder;
    }

    private StateManager() {
    }
}
