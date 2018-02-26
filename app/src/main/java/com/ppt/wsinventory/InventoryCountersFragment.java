package com.ppt.wsinventory;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.InventoryBIN;
import com.ppt.wsinventory.wsdb.DbAccess;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class InventoryCountersFragment extends Fragment {
    private GlobalVariables appContext;
    private Context mContext;
    ListView counterList;
    Spinner location;
    DbAccess dbAccess;
    List<InventoryBIN> inventoryBINS = new ArrayList<>();
    CounterListItemAdapter counterListItemAdapter;

    public InventoryCountersFragment() {

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context
                .getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbAccess = new DbAccess(getContext());
        dbAccess.open();
        inventoryBINS = dbAccess.getAllBinList();
        View view = inflater.inflate(R.layout.fragment_inventory_counters, container, false);

        counterList= (ListView)view.findViewById(R.id.counterlist);
        location = (Spinner)view.findViewById(R.id.location);

        loadCounter();
        return view;
    }

    private void loadCounter() {

    }






    @Subscribe
    public void onOpenScreen(WsEvents.EventOpenScreen e) {
     BusinessLogic businessLogic = new BusinessLogic(mContext);
            businessLogic.openScreen(e);

    }

    @Override
    public void onStart() {
        super.onStart();
        dbAccess.open();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onDestroy() {
        dbAccess.close();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        GlobalBus.getBus().unregister(this);
        dbAccess.close();
        super.onStop();
    }

}
