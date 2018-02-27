package com.ppt.wsinventory;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.ppt.wsinventory.R;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.inventory.model.Inventory_BinLoc;
import com.ppt.wsinventory.inventory.model.Inventory_PalletLoc;
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
        inventory_palletLocs = dbAccess.getAllinventoryPalletLocation();
        trayListItemsAdapter = new TrayListItemsAdapter((ArrayList<Inventory_PalletLoc>) inventory_palletLocs,appContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        traylist.setLayoutManager(mLayoutManager);
        traylist.setAdapter(trayListItemsAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dbAccess.open();
    }

    @Override
    public void onDestroy() {
        dbAccess.close();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        dbAccess.close();
        super.onStop();
    }
}
