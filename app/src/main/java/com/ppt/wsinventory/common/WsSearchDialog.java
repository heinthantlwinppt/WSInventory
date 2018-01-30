package com.ppt.wsinventory.common;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ppt.wsinventory.GlobalVariables;
import com.ppt.wsinventory.ProductListAdapter;
import com.ppt.wsinventory.ProductSearchListAdapter;
import com.ppt.wsinventory.R;
import com.ppt.wsinventory.inventory.model.InventoryAllProducts;
import com.ppt.wsinventory.model.CodeValue;
import com.ppt.wsinventory.model.LoginToken;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29/01/2018.
 */

public class WsSearchDialog extends DialogFragment {

    public static final String ACTION_NAME = "action_name";
    public static final String ACTION_ENTER_BARCODE = "action_enter_barcode";
    public static final String ACTION_ENTER_SEARCH = "action_enter_search";
    private static final String TAG = "TruckKit-SearchDialog";
    TextView tvTitle, tvSolution;
    EditText txtValue, group_name, txtSubGroup;
    Button btnOK;
    Button btnCancel;
    Spinner sp_groupname, sp_subgroupname;
    DbAccess dbaccess;
    private Context mContext;
    private GlobalVariables appContext;
    String groupname = new String("");
    String subgroupname;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context.getApplicationContext();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.ws_search_dialog,
                container,
                false);
        dbaccess = new DbAccess(getActivity());
        dbaccess.open();
        Bundle args = getArguments();
        final String mAction = args.getString(ACTION_NAME);

        tvTitle = (TextView) rootView.findViewById(R.id.tvHeader);
        tvSolution = (TextView) rootView.findViewById(R.id.solution);
        txtValue = (EditText) rootView.findViewById(R.id.txtValue);
        sp_groupname = rootView.findViewById(R.id.group_name);
        sp_subgroupname = rootView.findViewById(R.id.SubGroup_name);
        btnOK = (Button) rootView.findViewById(R.id.btnOk);
        btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
//        txtSolution.setText(BusinessLogic.SOLUTION_NAME);
        LoadGroupSpinnerList();
        LoadSubGroupSpinnerList(groupname);

        sp_groupname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                groupname =  sp_groupname.getSelectedItem().toString();
//                LoadSubGroupSpinnerList(groupname);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_subgroupname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subgroupname = sp_subgroupname.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TAG, "onInputEvent: " + group_name.getText().toString());
                GlobalBus.getBus().post(
                        new WsEvents.EventSearchProduct(
                                txtValue.getText().toString(),
                                groupname,
                                subgroupname,
                                mAction)

                );
                dismiss();

            }
        });

        return rootView;

//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void LoadGroupSpinnerList() {

        List<CodeValue> codeValues = new ArrayList<>();
        CodeValue all = new CodeValue();
        all.setCode("ALL");
        all.setValue(appContext.getTranslation("All Group"));
        codeValues = dbaccess.getProductGroupList();
        codeValues.add(all);
        ProductSearchListAdapter dataAdapter =
                new ProductSearchListAdapter(mContext,
                        android.R.layout.simple_spinner_item, codeValues);

        int idx = 0;
        for (CodeValue cv : codeValues) {
            if (cv.getCode().equalsIgnoreCase("ALL")) {
                idx = dataAdapter.getPosition(cv);
                break;
            }
        }

        sp_groupname.setAdapter(dataAdapter);
        sp_groupname.setSelection(idx);
    }

    private void LoadSubGroupSpinnerList(String groupname){
        List<CodeValue> codeValues = new ArrayList<>();
        CodeValue all = new CodeValue();
        all.setCode("ALL");
        all.setValue(appContext.getTranslation("All SubGroup"));

        codeValues = dbaccess.getProductSubGroupList();
        codeValues.add(all);
        ProductSearchListAdapter dataAdapter =
                new ProductSearchListAdapter(mContext,
                        android.R.layout.simple_spinner_item, codeValues);

        int idx = 0;
        for (CodeValue cv : codeValues) {
            if (cv.getCode().equalsIgnoreCase("ALL")) {
                idx = dataAdapter.getPosition(cv);
                break;
            }
        }

        sp_subgroupname.setAdapter(dataAdapter);
        sp_subgroupname.setSelection(idx);




    }
}
