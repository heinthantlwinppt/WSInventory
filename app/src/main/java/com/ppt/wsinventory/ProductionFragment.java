package com.ppt.wsinventory;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.inventory.model.Inventory_production_receiving;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15/02/2018.
 */

public class ProductionFragment extends Fragment {
    ViewGroup rootView;
    RecyclerView listView;
    private Context mContext;
    DbAccess dbaccess;
    private GlobalVariables appContext;
    ProdhdrRecyclerAdapter adapter;
    private LayoutInflater minflater;
    SearchView searchView;

    private List<Inventory_production_receiving> inventory_production_receivings;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context.getApplicationContext();
    }

    public ProductionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.activity_prodhdr, container, false);
        listView = rootView.findViewById(R.id.prodhdr_recycler);
        setHasOptionsMenu(true);
        minflater = inflater;
        inventory_production_receivings = new ArrayList<>();

        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        loadRecyclerView();
        return rootView;
    }

    private void loadRecyclerView() {

        inventory_production_receivings = dbaccess.getAllInventoryProdhdr();
        adapter = new ProdhdrRecyclerAdapter((ArrayList<Inventory_production_receiving>) inventory_production_receivings, appContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(adapter);


    }
}
