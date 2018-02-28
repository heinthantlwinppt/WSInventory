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

import com.ppt.wsinventory.R;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.inventory.model.Inventory_BinLoc;
import com.ppt.wsinventory.inventory.model.Inventory_PalletLoc;
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
    String loc_name = new String("");

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


        LoadPalletList(loc_name);
        LoadLoctionSpinner();

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                CodeValue codeValue = (CodeValue) adapterView.getItemAtPosition(position);
                loc_name = codeValue.getValue();
                LoadPalletList(loc_name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                LoadLoctionSpinner();
            }
        });

        return view;
    }


    public void LoadLoctionSpinner(){

        List<CodeValue> codeValues = new ArrayList<>();
        CodeValue all = new CodeValue();
        all.setCode("ALL");
        all.setValue(appContext.getTranslation("All Shop"));
        codeValues = dbAccess.getlocationSpinner();
        codeValues.add(all);
        LocSpinnerListAdapter dataAdapter =
                new LocSpinnerListAdapter(getActivity(),
                        android.R.layout.simple_spinner_item, codeValues);

        int idx = 0;
        for (CodeValue cv : codeValues) {
            if (cv.getCode().equalsIgnoreCase("ALL")) {
                idx = dataAdapter.getPosition(cv);
                break;
            }
        }
        location.setAdapter(dataAdapter);
        location.setSelection(idx);
    }
    public void LoadPalletList(String loc_name){

        inventory_palletLocs = dbAccess.getAllinventoryPalletLocation(loc_name);
        trayListItemsAdapter = new TrayListItemsAdapter((ArrayList<Inventory_PalletLoc>) inventory_palletLocs,appContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        traylist.setLayoutManager(mLayoutManager);
        traylist.setAdapter(trayListItemsAdapter);


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
