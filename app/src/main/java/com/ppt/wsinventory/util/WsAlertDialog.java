package com.ppt.wsinventory.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;

/**
 * Created by User on 17/01/2018.
 */

public class WsAlertDialog extends DialogFragment {
    public static final int RESULT_OK= 1;
    public static final int RESULT_CANCEL= 0;
    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_ACTION = "KEY_ACTION";
    public static final String KEY_MESSAGE = "KEY_MESSAGE";
    public static final String KEY_BUTTON_LEFT = "KEY_BUTTON_LEFT";
    public static final String KEY_BUTTON_RIGHT = "KEY_BUTTON_RIGHT";
    String mTitle;
    String mMessage;
    String mButtonLeft;
    String mButtonRight;
    String mAction;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        mTitle = args.getString(KEY_TITLE);
        mMessage = args.getString(KEY_MESSAGE);
        mAction = args.getString(KEY_ACTION);
        try {
            mButtonLeft = args.getString(KEY_BUTTON_LEFT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mButtonRight = args.getString(KEY_BUTTON_RIGHT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        Bundle arguments = getArguments();
        alertDialogBuilder.setTitle(mTitle);
        alertDialogBuilder.setMessage(mMessage);
        alertDialogBuilder.setPositiveButton(mButtonRight, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GlobalBus.getBus().post(
                        new WsEvents.EventMessages(mAction, MessageBox.RESULT_OK)
                );
            }
        });
        if(mButtonLeft != null){
            alertDialogBuilder.setNegativeButton(mButtonLeft, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    GlobalBus.getBus().post(
                            new WsEvents.EventMessages(mAction, MessageBox.RESULT_CANCEL)
                    );
                }
            });
        }
        return alertDialogBuilder.create();
    }


}
