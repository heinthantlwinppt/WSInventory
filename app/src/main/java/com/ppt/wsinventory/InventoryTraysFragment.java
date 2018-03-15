package com.ppt.wsinventory;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.inventory.model.Inventory_PalletLoc;
import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.CodeValue;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class InventoryTraysFragment extends Fragment {

    private GlobalVariables appContext;
    private Context mContext;
    RecyclerView counterList;
    Spinner location;
    DbAccess dbAccess;
    List<Inventory_PalletLoc> inventory_palletLocs = new ArrayList<>();
    TrayListItemsAdapter trayListItemsAdapter;
    RecyclerView traylist;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context
                .getApplicationContext();
    }

    public InventoryTraysFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_inventory_trays, container, false);
        dbAccess = new DbAccess(getContext());
        dbAccess.open();
        traylist = view.findViewById(R.id.trayList);
        location = view.findViewById(R.id.location_tray_spinner);


        LoadLoction();

        return view;
    }


    public void LoadLoction(){
        BusinessLogic businesslogic = new BusinessLogic(getContext());
        List<AdministrationLocations> locationslist = businesslogic.getAllLocation();
        LocationListAdapter locationlistadapter =
                new LocationListAdapter(getContext(),
                        android.R.layout.simple_spinner_item, locationslist);

        int idx = 0;
        for (AdministrationLocations loc : locationslist) {
            if (loc.getId().equalsIgnoreCase(appContext.getWssetting().getLocation_id())) {
                idx = locationlistadapter.getPosition(loc);
                break;
            }
        }
        location.setAdapter(locationlistadapter);
        location.setSelection(idx);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                AdministrationLocations loc = (AdministrationLocations) adapterView.getItemAtPosition(position);
                LoadTrayList(loc.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                LoadLoction();
            }
        });

    }
    public void LoadTrayList(String id){
        BusinessLogic businesslogic = new BusinessLogic(getContext());
        inventory_palletLocs = businesslogic.getAllinventoryPalletLocation(id);
        trayListItemsAdapter = new TrayListItemsAdapter((ArrayList<Inventory_PalletLoc>) inventory_palletLocs,appContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        traylist.setLayoutManager(mLayoutManager);
        traylist.setAdapter(trayListItemsAdapter);

    }
    @Override
    public void onStart() {
        super.onStart();
      //  dbAccess.open();
    }

    @Override
    public void onDestroy() {
 //       dbAccess.close();
        super.onDestroy();
    }

    @Override
    public void onStop() {
    //    dbAccess.close();
        super.onStop();
    }
}
