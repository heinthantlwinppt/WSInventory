package com.ppt.wsinventory;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.InventoryBIN;
import com.ppt.wsinventory.wsdb.DbAccess;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class InventoryCountersFragment extends Fragment {
    private GlobalVariables appContext;
    private Context mContext;
    RecyclerView counterList;
    Spinner location;
    DbAccess dbaccess;
    List<AdministrationLocations > inventoryBINS = new ArrayList<>();
    CounterListItemAdapter counterListItemAdapter;

    public InventoryCountersFragment() {

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

        View view = inflater.inflate(R.layout.fragment_inventory_counters, container, false);
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        BusinessLogic businesslogic = new BusinessLogic(getContext());

        counterList= (RecyclerView) view.findViewById(R.id.counterlist);
        location = (Spinner)view.findViewById(R.id.location);

        List<AdministrationLocations> locationslist = businesslogic.getAllLocation();
        LocationListAdapter locationlistadapter =
                new LocationListAdapter(getContext(),
                        android.R.layout.simple_spinner_item, locationslist);
//        ArrayAdapter<String> inventoryLocation = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_item, locationslist);
        location.setAdapter(locationlistadapter);
        int idx = 0;
        for (AdministrationLocations loc : locationslist) {
            if (loc.getId().equalsIgnoreCase(appContext.getWssetting().getLocation_id())) {
                idx = locationlistadapter.getPosition(loc);
                break;
            }
        }
        location.setSelection(idx);


        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AdministrationLocations loc = (AdministrationLocations) location.getSelectedItem();
                BusinessLogic bl = new BusinessLogic(getContext());
//                String  locid = dbaccess.getLocaionId(locationname);
                List<InventoryBIN> allcounter_by_local = bl.getAllbinByLocation(loc.getId());
                counterListItemAdapter = new CounterListItemAdapter((ArrayList<InventoryBIN>) allcounter_by_local, appContext);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                counterList.setLayoutManager(mLayoutManager);
                counterList.setAdapter(counterListItemAdapter);
//        loadCounter();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                BusinessLogic businesslogic = new BusinessLogic(getContext());
                List<InventoryBIN> allcounter_by_local = businesslogic.getAllInventoryBin();
                counterListItemAdapter = new CounterListItemAdapter((ArrayList<InventoryBIN>) allcounter_by_local, appContext);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                counterList.setLayoutManager(mLayoutManager);
                counterList.setAdapter(counterListItemAdapter);
            }
        });

//
        return view;
    }

    private void loadCounter() {

    }

    @Subscribe
    public void onOpenScreen(WsEvents.EventOpenScreen e) {
     BusinessLogic businessLogic = new BusinessLogic(mContext);
            businessLogic.openScreen(e);

    }

    @Override
    public void onStart() {
        super.onStart();
//        dbaccess.open();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
//        dbaccess.close();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        GlobalBus.getBus().unregister(this);
//        dbaccess.close();
        super.onStop();
    }


}