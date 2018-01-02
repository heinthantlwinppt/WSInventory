package com.ppt.wsinventory.common;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ppt.wsinventory.R;

/**
 * Created by User on 02/01/2018.
 */

public class WsNewChangeDialog extends DialogFragment {

    public static final String ACTION_NAME = "action_name";
    public static final String ACTION_ENTER_BARCODE = "action_enter_barcode";
    public static final String ACTION_ENTER_NEWCHANGE = "action_enter_newchange";

    private static final String TAG = "TruckKit-InputDialog";
    TextView tvTitle,tvSolution;
    EditText txtValue,txtSolution;
    Button btnOK;
    Button btnCancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.ws_input_dialog,
                container,
                false);

        Bundle args = getArguments();
        final String mAction = args.getString(ACTION_NAME);

        tvTitle = (TextView) rootView.findViewById(R.id.tvHeader);
        tvSolution = (TextView) rootView.findViewById(R.id.solution);
        txtValue = (EditText) rootView.findViewById(R.id.txtValue);
        txtSolution = (EditText) rootView.findViewById(R.id.solution_name);
        btnOK = (Button) rootView.findViewById(R.id.btnOk);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalBus.getBus().post(
                        new WsEvents.EventNewChange(
                                txtValue.getText().toString(),
                                txtSolution.getText().toString(),
                                mAction)
                );
                dismiss();
            }
        });

        return rootView;

//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
