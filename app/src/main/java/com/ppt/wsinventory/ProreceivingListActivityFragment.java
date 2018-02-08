package com.ppt.wsinventory;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProreceivingListActivityFragment extends Fragment {
    ViewGroup rootView;
    RecyclerView listView;
    private Context mContext;
    DbAccess dbaccess;
    private GlobalVariables appContext;
    ProreceivingListAdapter adapter;
    private LayoutInflater minflater;
    SearchView searchView;
    private List<Inventory_SmithJob> inventory_smithJobs;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context.getApplicationContext();
    }

    public ProreceivingListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_proreceiving_list,container,false);
        listView = rootView.findViewById(R.id.proreceivingList);
        setHasOptionsMenu(true);
        minflater = inflater;
        inventory_smithJobs = new ArrayList<>();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        loadRecyclerView();
        return rootView;
    }

    private void loadRecyclerView() {

        inventory_smithJobs = dbaccess.getAllInventorySmithJob();
        adapter = new ProreceivingListAdapter((ArrayList<Inventory_SmithJob>) inventory_smithJobs);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(adapter);


    }
}
