package com.ppt.wsinventory.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

/**
 * Created by User on 17/01/2018.
 */

public class MessageBox {

    public static final int RESULT_OK= 1;
    public static final int RESULT_CANCEL= 0;
    public static final int RESULT_YES= 2;
    public static final int RESULT_NO= 3;
    private static final String SHOW_MESSAGEBOX = "SHOW_MESSAGEBOX";
    public static final String CLOSE_FOLDER = "close_folder";
    public static final String SEND_DATA = "send_data";


    public static void ShowMessage(FragmentManager fragmentManager, String title, String caption, @Nullable String action, @Nullable String ButtonLeft, String ButtonRight){
        WsAlertDialog wsAlertDialog = new WsAlertDialog();
        Bundle args = new Bundle();
        wsAlertDialog.setArguments(args);
        args.putString(WsAlertDialog.KEY_TITLE, title);
        args.putString(WsAlertDialog.KEY_MESSAGE, caption);
        args.putString(WsAlertDialog.KEY_ACTION, (action != null)? action: "" );
        args.putString(WsAlertDialog.KEY_BUTTON_LEFT, ButtonLeft);
        args.putString(WsAlertDialog.KEY_BUTTON_RIGHT, ButtonRight);
        wsAlertDialog.show(fragmentManager,SHOW_MESSAGEBOX);
    }
}
