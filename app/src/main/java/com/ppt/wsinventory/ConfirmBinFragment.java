package com.ppt.wsinventory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.ApiModel;
import com.ppt.wsinventory.model.InventoryBIN;
import com.ppt.wsinventory.services.WsSyncService;
import com.ppt.wsinventory.util.HexStringConverter;
import com.ppt.wsinventory.util.JsonHelper;
import com.ppt.wsinventory.util.MessageBox;
import com.ppt.wsinventory.websocket.WsApi;
import com.ppt.wsinventory.wsdb.DbAccess;

import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConfirmBinFragment extends Fragment {
    private GlobalVariables appContext;
    private Context mContext;
    public static final String TAG = "Ws-ConformBinFragment";
    DbAccess dbaccess;
    EditText name, type, descrription, counter_id, barcode, tag;
    //    TextView counter_id,barcode,tag;
    CheckBox active;
    Spinner location;
    String current_id;
    Button cancle, confirm;
    ProgressBar my_progress;
    ScrollView scrollView;
    LinearLayout linearlayout;
    public ConfirmBinFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context
                .getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview =
                inflater.inflate(R.layout.fragment_conform_bin, container, false);

//        counter_id = (TextView) rootview.findViewById(R.id.counter_id);
        scrollView =  rootview.findViewById(R.id.scrollView);
        linearlayout =  rootview.findViewById(R.id.linearlayout);
        my_progress = rootview.findViewById(R.id.confirm_progress);
        counter_id = (EditText) rootview.findViewById(R.id.counter_id);
        name = (EditText) rootview.findViewById(R.id.counter_name);
        type = (EditText) rootview.findViewById(R.id.counter_type);
        descrription = (EditText) rootview.findViewById(R.id.description);
        active = (CheckBox) rootview.findViewById(R.id.is_active);
        barcode = (EditText) rootview.findViewById(R.id.barcode);
        tag = (EditText) rootview.findViewById(R.id.tag);
        location = (Spinner) rootview.findViewById(R.id.location_id);
        cancle = (Button) rootview.findViewById(R.id.cancel_btn);
        confirm = (Button) rootview.findViewById(R.id.confirm_btn);
        String id = appContext.getBinid();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        BusinessLogic businesslogic = new BusinessLogic(getContext());
        InventoryBIN inventorybin = businesslogic.getInventoryBinById(id);

//        barcode= (TextView) rootview.findViewById(R.id.barcode);
//        tag = (TextView)rootview.findViewById(R.id.tag);

        counter_id.setText(inventorybin.getId());
        current_id = inventorybin.getId();
        name.setText(inventorybin.getBin_name());
        type.setText(inventorybin.getBin_type());
        descrription.setText(inventorybin.getBin_description());
        active.setChecked(inventorybin.isActive());
        barcode.setText(inventorybin.getBarcode());
        tag.setText(inventorybin.getTag());
        if (inventorybin.isActive()) {
            active.setChecked(true);
//            Toast.makeText(mContext, "isActive " + inventorybin.isActive(), Toast.LENGTH_SHORT).show();
        } else {
            active.setChecked(false);
//            Toast.makeText(mContext, "isActive " + inventorybin.isActive(), Toast.LENGTH_SHORT).show();

        }

        List<AdministrationLocations> locationslist = businesslogic.getAllLocation();
        LocationListAdapter locationlistadapter =
                new LocationListAdapter(getContext(),
                        android.R.layout.simple_spinner_item, locationslist);
//        ArrayAdapter<String> inventoryLocation = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_item, locationslist);
        location.setAdapter(locationlistadapter);
        int idx = 0;
        for (AdministrationLocations loc : locationslist) {
            if (loc.getId().equalsIgnoreCase(inventorybin.getLocation_id())) {
                idx = locationlistadapter.getPosition(loc);
                break;
            }
        }
        location.setSelection(idx);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canclefunction();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowProgress(true);
                updateBin();
            }
        });

        return rootview;
    }

    @Subscribe
    public void onReadBarcode(WsEvents.EventReadBarCode e) {
//        BusinessLogic businessLogic = new BusinessLogic(mContext);
//        businessLogic.openScreen(e);
        barcode.setText(e.getBarcode());

    }

    @Subscribe
    public void onReadRFID(WsEvents.EventReadRFID e) {

        tag.setText(e.getRfid());

    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getBus().unregister(this);
        super.onStop();
    }
    private BroadcastReceiver mBroadcastSendReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(WsSyncService.API_SERVICE_SEND)) {
                String msgtype = intent.getStringExtra(WsSyncService.SERVICE_TYPE);
                WsApi wsApi = new WsApi(context);
                if (msgtype.equalsIgnoreCase(WsSyncService.SERVICE_RESPONSE)) {
                    String response = appContext.getResponseMessage();
                    response = HexStringConverter.getHexStringConverterInstance().hexToString(response);
                    Log.i(TAG, "mBroadcastSendReceiver: " + response);
                    Gson gson = JsonHelper.getGson();
                    ApiModel apiModel = gson.fromJson(response, ApiModel.class);
                    if (apiModel.getName().equalsIgnoreCase(ApiModel.GETSENDDATALIST)) {
                        appContext.setActionLists(null);
                        List<ActionList> actionLists = new ArrayList<>();
                        Type listType = new TypeToken<ArrayList<ActionList>>() {
                        }.getType();
                        actionLists = gson.fromJson(apiModel.getMessage(), listType);
                        appContext.setActionLists(actionLists);
                        if (appContext.getActionLists().size() > 0)
                            wsApi.doSendData();
                    } else {
                        //TODO Implement Delete
                        // the tables records
                        wsApi.doSendData();
                    }

                } else if (msgtype.equalsIgnoreCase(WsSyncService.SERVICE_ERROR)) {
                    Log.i(TAG, "onReceive: Error" );
//                    Toast.makeText(mContext, appContext.getResponseMessage(), Toast.LENGTH_SHORT).show();
//                    MessageBox.ShowMessage(getFragmentManager(),
//                            appContext.getTranslation("ERROR"),
//                            appContext.getTranslation(appContext.getTranslation(appContext.getResponseMessage())),
//                            null,
//                            null,
//                            "OK");
                }

            }

        }
    };
    private void ShowProgress(boolean show) {
        my_progress.setVisibility(show ? View.VISIBLE : View.GONE);
        scrollView.setVisibility(show ? View.GONE : View.VISIBLE);
        linearlayout.setVisibility(show ? View.GONE : View.VISIBLE);

    }
    private void updateBin() {
        appContext.setShowalert(true);
        InventoryBIN inventorybin = new InventoryBIN();

        inventorybin.setId(counter_id.getText().toString());
        inventorybin.setBin_name(name.getText().toString());
        inventorybin.setBin_description(descrription.getText().toString());
        inventorybin.setBin_type(type.getText().toString());
        inventorybin.setBarcode(barcode.getText().toString());
        inventorybin.setTag(tag.getText().toString());
        AdministrationLocations loc = (AdministrationLocations) location.getSelectedItem();
        inventorybin.setLocation_id(loc.getId());
        inventorybin.setActive(active.isChecked());
        inventorybin.setUploaded(false);
        BusinessLogic businesslogic = new BusinessLogic(getContext());

        boolean success = businesslogic.updateInventoryBin(inventorybin);
        if (success) {
            businesslogic.sendAllDataToServer();
        } else {
            ShowProgress(false);
            MessageBox.ShowMessage(getChildFragmentManager(),
                    "Counter",
                    appContext.getTranslation("Confirmation Failed!"),
                    null,
                    null,
                    "OK"
            );
        }
    }
    @Subscribe
    public void ShowMessage(WsEvents.EventShowMessage e) {
        ShowProgress(false);
        MessageBox.ShowMessage(getChildFragmentManager(),
                "Counter",
                appContext.getTranslation("Counter Confirmed successfully!"),
                MessageBox.CLOSE_FOLDER,
                null,
                "OK"
        );
    }
    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(mContext.getApplicationContext())
                .unregisterReceiver(mBroadcastSendReceiver);
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(mContext.getApplicationContext())
                .registerReceiver(mBroadcastSendReceiver,
                        new IntentFilter(WsSyncService.API_SERVICE_SEND));
    }

    @Subscribe
    public void onMessageBoxResult(WsEvents.EventMessages e) {
        if (e.getMsgResult() == MessageBox.RESULT_OK && e.getAction() == MessageBox.CLOSE_FOLDER) {
            GlobalBus.getBus().post(
                    new WsEvents.EventOpenScreen("close_folder"));

        }
//        else if(e.getMsgResult() == MessageBox.RESULT_OK && e.getAction() == SYNCHRONIZATION_COMPLETED) {
////            ShowProgress(false);
//            LoadItemList();
//        }
    }
    private void canclefunction() {
        GlobalBus.getBus().post(
                new WsEvents.EventOpenScreen("close_folder"));
    }
//
//    inventoryBINS = dbaccess.getAllLocation();
//    ArrayAdapter<String> inventoryLocation = new ArrayAdapter<String>(getContext(),
//            android.R.layout.simple_spinner_item, inventoryBINS);
//        Log.i("APT", "onCreateView: " + inventoryBINS.size());
//
//        location.setAdapter(inventoryLocation);


}
