package com.ppt.wsinventory;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.common.WsSearchDialog;
import com.ppt.wsinventory.inventory.model.InventoryAllProducts;
import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.model.Inventory_products;
import com.ppt.wsinventory.wsdb.DbAccess;

import org.greenrobot.eventbus.Subscribe;

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
    private static final String TAG = "TruckKit-productfrag";
    String productname = "";
    String groupname = "";
    String subgroupname = "";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context.getApplicationContext();
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

        if(appContext.getProductname()!=null) {
            productname = appContext.getProductname();
        }
        if(appContext.getGroupname()!= null) {
            groupname = appContext.getGroupname();
        }
        if(appContext.getSubgroupname()!= null) {
            subgroupname = appContext.getSubgroupname();
        }

        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        inventoryAllProducts = new ArrayList<>();
        inventoryAllProducts = dbaccess.getInventoryAllProducts(productname ,groupname,subgroupname);
        loadProductRecyclerView(inventoryAllProducts);
        return rootView;

    }

    private void loadProductRecyclerView(List<InventoryAllProducts> inventoryAllProducts) {


        adapter = new ProductListAdapter(mContext,(ArrayList< InventoryAllProducts >)inventoryAllProducts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        ListView.setLayoutManager(mLayoutManager);
        ListView.setItemAnimator(new DefaultItemAnimator());
        ListView.setAdapter(adapter);


    }


    @Subscribe
    public void onSearchEvent(WsEvents.EventSearchProduct e) {

        if (e.getactionname().equalsIgnoreCase(WsSearchDialog.ACTION_ENTER_SEARCH)) {

            Log.i(TAG, "onInputEvent: " + e.getmGroupname());
            appContext.setProductname(e.getmProductname());
            appContext.setGroupname(e.getmGroupname());
            appContext.setSubgroupname(groupname = e.getmSubGroupname());
           inventoryAllProducts = dbaccess.getInventoryAllProducts(e.getmProductname(),e.getmGroupname(),e.getmSubGroupname());

           if(inventoryAllProducts.isEmpty()){

               Toast.makeText(getContext(), " no search items ", Toast.LENGTH_SHORT).show();
           }else {
               loadProductRecyclerView(inventoryAllProducts);
           }



        }

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
        loadProductRecyclerView(inventoryAllProducts);

    }

    @Override
    public void onStart() {
        super.onStart();
        dbaccess.open();
        loadProductRecyclerView(inventoryAllProducts);
        GlobalBus.getBus().register(this);

    }

    @Override
    public void onStop() {

        GlobalBus.getBus().unregister(this);
       dbaccess.close();
//       appContext.setProductname("");
//       appContext.setGroupname("");
//       appContext.setSubgroupname("");
        super.onStop();
    }


}
