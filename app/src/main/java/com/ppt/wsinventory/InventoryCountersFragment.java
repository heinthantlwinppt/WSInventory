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

import org.greenrobot.eventbus.Subscribe;

/**
 * A placeholder fragment containing a simple view.
 */
public class InventoryCountersFragment extends Fragment {
    private GlobalVariables appContext;
    private Context mContext;
    ListView counterList;
    Spinner location;

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

        View view = inflater.inflate(R.layout.fragment_inventory_counters, container, false);
        TextView counter = (TextView)view.findViewById(R.id.counter);
        counter.setText("Test"+appContext.getParientid());
//        counterList= (ListView)view.findViewById(R.id.counterlist);
        location = (Spinner)view.findViewById(R.id.location);

        loadCounter();
        return view;
    }

    private void loadCounter() {

    }


    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        GlobalBus.getBus().unregister(this);
        super.onStop();
    }



    @Subscribe
    public void onOpenScreen(WsEvents.EventOpenScreen e) {
     BusinessLogic businessLogic = new BusinessLogic(mContext);
            businessLogic.openScreen(e);

    }
}
