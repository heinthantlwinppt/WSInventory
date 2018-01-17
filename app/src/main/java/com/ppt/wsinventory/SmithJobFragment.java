package com.ppt.wsinventory;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
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
    private List<Manufacturing_smith_joborder> manufacturing_smith_joborders;
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
        minflater = inflater;
        manufacturing_smith_joborders = new ArrayList<>();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        loadRecyclerView();
        return  rootView;
    }

    private void loadRecyclerView() {

        manufacturing_smith_joborders = dbaccess.getAllSmithJoborder();
        adapter = new SmithJobAdapter((ArrayList<Manufacturing_smith_joborder>) manufacturing_smith_joborders);
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

    }

    @Override
    public void onStop() {
        dbaccess.close();
        super.onStop();

    }
}
