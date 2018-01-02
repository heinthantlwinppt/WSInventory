package com.ppt.wsinventory.common;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by User on 02/01/2018.
 */

public class GlobalBus {

    private static EventBus mBus;

    public static EventBus getBus() {
        if (mBus == null) {
            mBus = EventBus.getDefault();
        }
        return mBus;
    }
}
