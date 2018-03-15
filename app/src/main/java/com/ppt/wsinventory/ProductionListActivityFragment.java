package com.ppt.wsinventory;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.inventory.model.Inventory_production_receiving;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductionListActivityFragment extends Fragment {

    ViewGroup rootView;
    RecyclerView listView;
    private Context mContext;
    DbAccess dbaccess;
    private GlobalVariables appContext;
    ProdhdrRecyclerAdapter adapter;
    private LayoutInflater minflater;
    SearchView searchView;
    private List<Inventory_production_receiving> inventory_production_receiving;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context.getApplicationContext();
    }

    public ProductionListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_receving_inventory, container, false);
        listView = rootView.findViewById(R.id.id_receiving_recycler);
        setHasOptionsMenu(true);
        minflater = inflater;
        inventory_production_receiving = new ArrayList<>();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        loadRecyclerView();
        return rootView;
    }

    private void loadRecyclerView() {

        inventory_production_receiving = dbaccess.getAllInventoryProdhdr();
            Toast.makeText(getContext(),inventory_production_receiving.size()+"",Toast.LENGTH_LONG).show();
            adapter = new ProdhdrRecyclerAdapter((ArrayList<Inventory_production_receiving>) inventory_production_receiving, appContext);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            listView.setLayoutManager(mLayoutManager);
            listView.setAdapter(adapter);

    }
}

