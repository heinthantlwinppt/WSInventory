package com.ppt.wsinventory;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.inventory.model.ProductReceiving;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.wsdb.DbAccess;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class Receive_productFragment extends Fragment{
    ViewGroup rootView;
    RecyclerView listView;
    private Context mContext;
    private LayoutInflater minflater;
    DbAccess dbaccess;
    private GlobalVariables appContext;
    private List<ProductReceiving> productReceivings;
    ProductReceivingAdapter adapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context.getApplicationContext();
    }

    public Receive_productFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_receive_product, container, false);
        listView = rootView.findViewById(R.id.id_receiving_recycler);
        setHasOptionsMenu(true);
        minflater = inflater;
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        loadRecyclerView();
        return rootView;
    }

    private void loadRecyclerView() {
        productReceivings = dbaccess.getAllProductReceiving(appContext.getProduct_no());
        Toast.makeText(getContext(),productReceivings.size()+"",Toast.LENGTH_LONG).show();
        dbaccess.close();
        adapter = new ProductReceivingAdapter((ArrayList<ProductReceiving>) productReceivings, appContext);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(adapter);
    }

    @Subscribe
    public void onOpenReceivingProduct(WsEvents.EventOpenProductReceiving e)
    {



    }


}
