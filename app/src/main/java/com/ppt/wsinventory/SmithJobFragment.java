package com.ppt.wsinventory;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;


public class SmithJobFragment extends Fragment{

    private ViewGroup rootView;
    RecyclerView ListView;
    private LayoutInflater minflater;
    DbAccess dbaccess;
    SmithJobAdapter adapter;
    private List<AdministrationWsdashboard> administrationWsdashboardList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_smith_job, container, false);
        ListView = (RecyclerView) rootView.findViewById(R.id.activity_recycler_view);
        minflater = inflater;
        administrationWsdashboardList = new ArrayList<>();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        loadRecyclerView();
        return  rootView;
    }

    private void loadRecyclerView() {

        administrationWsdashboardList = dbaccess.getAllDashboardItems();
        adapter = new SmithJobAdapter((ArrayList<AdministrationWsdashboard>) administrationWsdashboardList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        ListView.setLayoutManager(mLayoutManager);
        ListView.setItemAnimator(new DefaultItemAnimator());
        ListView.setAdapter(adapter);
    }

}
