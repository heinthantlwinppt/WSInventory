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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.ApiModel;
import com.ppt.wsinventory.model.InventoryPallet;
import com.ppt.wsinventory.model.InventoryPalletLoc;
import com.ppt.wsinventory.services.WsSyncService;
import com.ppt.wsinventory.util.HexStringConverter;
import com.ppt.wsinventory.util.JsonHelper;
import com.ppt.wsinventory.util.MessageBox;
import com.ppt.wsinventory.websocket.WsApi;
import com.ppt.wsinventory.wsdb.DbAccess;

import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConfirmPalletFragment extends Fragment {
    public static final String TAG = "Ws-CfmPalletFrag";
    EditText pallet_id,pallet_name,pallet_type,pallet_description,barcode,tag,weight;
    CheckBox isactive,isused;
    Button confirm,cancel;
    Button weightButton;
    GlobalVariables appContext;
    private Context mContext;
    DbAccess dbaccess;
    Spinner location;
    String current_id;
    ProgressBar my_progress;
    ScrollView scrollView;
    LinearLayout linearlayout;
    public ConfirmPalletFragment() {
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context
                .getApplicationContext();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_confirm_pallet, container, false);
        scrollView =  rootView.findViewById(R.id.scrollView);
        linearlayout =  rootView.findViewById(R.id.linearlayout);
        my_progress = rootView.findViewById(R.id.confirm_progress);

        pallet_id = (EditText)rootView.findViewById(R.id.pallet_counter_id);
        pallet_name =(EditText)rootView.findViewById(R.id.pallet_counter_name);
        pallet_type =(EditText)rootView.findViewById(R.id.pallet_counter_type);
        pallet_description  =(EditText)rootView.findViewById(R.id.pallet_description);
        barcode = (EditText)rootView.findViewById(R.id.pallet_barcode);
        tag = (EditText)rootView.findViewById(R.id.pallet_tag);
        weight =(EditText)rootView.findViewById(R.id.pallet_pallet_weight);
        weightButton = (Button)rootView.findViewById(R.id.weight_button);

        location =(Spinner)rootView.findViewById(R.id.pallet_location_id);
        location.setEnabled(false);

        isactive = (CheckBox)rootView.findViewById(R.id.pallet_is_active);
        isused = (CheckBox)rootView.findViewById(R.id.pallet_is_used);

        confirm =(Button)rootView.findViewById(R.id.pallet_confirm_btn);
        cancel = (Button)rootView.findViewById(R.id.pallet_cancel_btn);
        appContext = (GlobalVariables)getActivity().getApplicationContext();
        String  id = appContext.getPalletId();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        BusinessLogic businesslogic = new BusinessLogic(getContext());



        InventoryPalletLoc inventorypalletloc = businesslogic.getPalletById(id);
        current_id = inventorypalletloc.getId();

        pallet_name.setText(inventorypalletloc.getPallet_name());
        pallet_id.setText(inventorypalletloc.getId());
        pallet_type.setText(inventorypalletloc.getPallet_type());
        pallet_description.setText(inventorypalletloc.getPallet_description());
        barcode.setText(inventorypalletloc.getPallet_barcode());
        tag.setText(inventorypalletloc.getPallet_tag());
        weight.setText(inventorypalletloc.getPallet_weight());


        Toast.makeText(mContext, "Result " + inventorypalletloc.getPallet_weight()+ " & " +inventorypalletloc.getPallet_active(), Toast.LENGTH_SHORT).show();

        if (inventorypalletloc.getPallet_active())
        {
            isactive.setChecked(true);
        }
        else
        {
            isactive.setChecked(false);
        }

        if (inventorypalletloc.isPallet_is_used())
        {
            isused.setChecked(true);
        }
        else {
            isused.setChecked(false);
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
            if (loc.getId().equalsIgnoreCase(inventorypalletloc.getPallet_location_id())) {
                idx = locationlistadapter.getPosition(loc);
                break;
            }
        }
        location.setSelection(idx);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canclefunction();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowProgress(true);
                updatePallet();
            }
        });

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.counter_confirm){
            Toast.makeText(getContext(),"Confirm",Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.counter_confirm_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    private void ShowProgress(boolean show) {
        my_progress.setVisibility(show ? View.VISIBLE : View.GONE);
        scrollView.setVisibility(show ? View.GONE : View.VISIBLE);
        linearlayout.setVisibility(show ? View.GONE : View.VISIBLE);

    }
    private void updatePallet()
    {
        appContext.setShowalert(true);
        InventoryPallet inventorypallet = new InventoryPallet();
        inventorypallet.setId(pallet_id.getText().toString());
        inventorypallet.setPallet_name(pallet_name.getText().toString());
        inventorypallet.setPallet_description(pallet_description.getText().toString());
        inventorypallet.setPallet_type(pallet_type.getText().toString());
        inventorypallet.setBarcode(barcode.getText().toString());
        inventorypallet.setTag(tag.getText().toString());
        inventorypallet.setWeight(Double.parseDouble(weight.getText().toString()));
        inventorypallet.setActive(isactive.isChecked());
        inventorypallet.setIs_used(isused.isChecked());
        inventorypallet.setUploaded(false);
        inventorypallet.setTs( new Date(System.currentTimeMillis()));
        BusinessLogic businesslogic = new BusinessLogic(getContext());
        boolean success = businesslogic.updateInventoryPallet(inventorypallet);

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
    @Subscribe
    public void ShowMessage(WsEvents.EventShowMessage e) {
        ShowProgress(false);
        MessageBox.ShowMessage(getChildFragmentManager(),
                "Tray(s)",
                appContext.getTranslation("Tray Confirmed Successfully!"),
                MessageBox.CLOSE_FOLDER,
                null,
                "OK"
        );
    }
}
