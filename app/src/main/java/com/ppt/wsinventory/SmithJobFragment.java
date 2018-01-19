package com.ppt.wsinventory;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.model.ManufacturingSmith;
import com.ppt.wsinventory.model.Manufacturing_smith_joborder;
import com.ppt.wsinventory.services.WsSyncService;
import com.ppt.wsinventory.wsdb.DbAccess;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;


public class SmithJobFragment extends Fragment{

    private ViewGroup rootView;
    RecyclerView ListView;
    private LayoutInflater minflater;
    DbAccess dbaccess;
    private Context mContext;
    private GlobalVariables appContext;
    SmithJobAdapter adapter;
    SearchView searchView;
    private List<Inventory_SmithJob> inventory_smithJobs;
    Manufacturing_smith_joborder smith_joborder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context
                .getApplicationContext();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_smith_job, container, false);
        ListView = (RecyclerView) rootView.findViewById(R.id.activity_recycler_view);
        setHasOptionsMenu(true);
        minflater = inflater;
        inventory_smithJobs = new ArrayList<>();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        loadRecyclerView();
        return  rootView;
    }

    private void loadRecyclerView() {

        inventory_smithJobs = dbaccess.getAllInventorySmithJob();
        adapter = new SmithJobAdapter((ArrayList<Inventory_SmithJob>) inventory_smithJobs);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        ListView.setLayoutManager(mLayoutManager);
        ListView.setItemAnimator(new DefaultItemAnimator());
        ListView.setAdapter(adapter);
//        ListView.setOnClickListener(new AdapterView.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Manufacturing_smith_joborder customer = (Manufacturing_smith_joborder) parent.getItemAtPosition(position);vgdfg
//                OpenSelectedCustomer(customer);
//            }
//        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu,inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_smithjob, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.searchsmithjob);
        searchView = new SearchView(((DashboardSmithJobOrder) mContext).getSupportActionBar().getThemedContext());
        changeSerchViewTextColor(searchView);
        MenuItemCompat.setShowAsAction(myActionMenuItem, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(myActionMenuItem, searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!searchView.isIconfiedByDefault()) {
                    searchView.setIconified(true);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                final List<Inventory_SmithJob> filtermodelist = filter(inventory_smithJobs, newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });

    }

    private List<Inventory_SmithJob> filter(List<Inventory_SmithJob> pl, String query) {

        query = query.toLowerCase();
        final List<Inventory_SmithJob> filterdModeList = new ArrayList<>();

        for (Inventory_SmithJob model : pl) {
            final String tex = model.getJoborder_no().toLowerCase();
            final String tex2 = model.getName().toLowerCase();
            if (tex.startsWith(query) || tex2.startsWith(query)) {
                filterdModeList.add(model);
            }
        }
        return filterdModeList;
    }

    private void changeSerchViewTextColor(View view) {

        if(view != null) {

            if(view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            }else if(view instanceof ViewGroup) {

                ViewGroup viewGroup = (ViewGroup) view;
                for(int i= 0; i<viewGroup.getChildCount(); i++) {

                    changeSerchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }

    @Subscribe
    public void onOpenSelectedSmithJob(WsEvents.EventOpenSmithJob e) {
        BusinessLogic businessLogic = new BusinessLogic(mContext);
        smith_joborder = businessLogic.openSmithJobScreen(e);
        StateManager.getInstance().setmSelectedSmithJobOrder(smith_joborder);
        Intent intent = new Intent(getContext(),DashboardSmithJob.class);
        startActivity(intent);


    }
    private void OpenSelectedCustomer(Manufacturing_smith_joborder manufacturing_smith_joborder){


    }


    @Override
    public void onPause() {

        dbaccess.close();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        dbaccess.open();

    }

    @Override
    public void onStart() {
        super.onStart();
        dbaccess.open();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getBus().unregister(this);
        super.onStop();
    }
}
