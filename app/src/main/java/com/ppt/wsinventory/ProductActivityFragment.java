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

import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.inventory.model.InventoryAllProducts;
import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.model.Inventory_products;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductActivityFragment extends Fragment {

    private ViewGroup rootView;
    RecyclerView ListView;
    private LayoutInflater minflater;
    DbAccess dbaccess;
    private Context mContext;
    private GlobalVariables appContext;
    private List<InventoryAllProducts> inventoryAllProducts;
    ProductListAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public ProductActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product, container, false);
        ListView = (RecyclerView) rootView.findViewById(R.id.activity_Product_recycler_view);
        setHasOptionsMenu(true);
        minflater = inflater;

        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        inventoryAllProducts = new ArrayList<>();
        loadProductRecyclerView();
        return rootView;

    }

    private void loadProductRecyclerView() {

        inventoryAllProducts = dbaccess.getInventoryAllProducts();
        adapter = new ProductListAdapter(mContext,(ArrayList< InventoryAllProducts >)inventoryAllProducts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        ListView.setLayoutManager(mLayoutManager);
        ListView.setItemAnimator(new DefaultItemAnimator());
        ListView.setAdapter(adapter);


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
