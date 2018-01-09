package com.ppt.wsinventory;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.common.WsNewChangeDialog;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.ApiModel;
import com.ppt.wsinventory.model.ApiParam;
import com.ppt.wsinventory.services.WsSyncService;
import com.ppt.wsinventory.util.HexStringConverter;
import com.ppt.wsinventory.util.JsonHelper;
import com.ppt.wsinventory.websocket.WsApi;
import com.ppt.wsinventory.wsdb.DbAccess;
import com.ppt.wsinventory.model.Item;
import com.ppt.wsinventory.util.ScreenUtility;

import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements RecyclerViewAdapter.ItemListener {
    SearchView searchView;
    private GlobalVariables appContext;
    RecyclerView recyclerView;
    List<RecyclerDataModel> arrayList;
    List<Item> ItemList = new ArrayList<>();
    private float dpWidth;
    private float dpHeight;
    RecyclerViewAdapter adapter;
    DbAccess dbaccess;
    private Context mcontext;
    private static final String TAG = "Ws-Dashboard";

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcontext = context;
        appContext = (GlobalVariables) context
                .getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        ItemList = dbaccess.getAllItems();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(getContext(), ItemList, this);
        recyclerView.setAdapter(adapter);

        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        ScreenUtility screenutility = new ScreenUtility(getActivity());
        float w = screenutility.getDpWidth();
        float h = screenutility.getDpHeight();
        float d = screenutility.getDensity();
        w = 200;

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getContext(), (int) w);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onItemClick(Item item) {
        Toast.makeText(getContext(), item.getItemName() + " is clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor((R.color.white)));
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

                final List<Item> filtermodelist = filter(ItemList, newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });

    }
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(WsSyncService.API_SERVICE_SYNC)) {
                String msgtype = intent.getStringExtra(WsSyncService.SERVICE_TYPE);
                WsApi wsApi = new WsApi(context);
                if (msgtype.equalsIgnoreCase(WsSyncService.SERVICE_RESPONSE)) {
                    String response = appContext.getResponseMessage();
                    response = HexStringConverter.getHexStringConverterInstance().hexToString(response);
                    Log.i(TAG, "onReceive: " + response);
                    Gson gson = JsonHelper.getGson();
                    ApiModel apiModel = gson.fromJson(response,ApiModel.class);
                    if (apiModel.getName().equalsIgnoreCase(ApiModel.GETACTIONLIST)){
                        appContext.setActionLists(null);
                        List<ActionList> actionLists = new ArrayList<>();
                        Type listType = new TypeToken<ArrayList<ActionList>>(){}.getType();
                        actionLists = gson.fromJson(apiModel.getMessage(), listType);
                        appContext.setActionLists(actionLists);
                        if (appContext.getActionLists().size() > 0)
                            wsApi.doSync();
                    }else{
                        //TODO Implement Delete the tables records
                        wsApi.doSync();
                    }

                }else if (msgtype.equalsIgnoreCase(WsSyncService.SERVICE_ERROR)) {
                    Toast.makeText(mcontext, appContext.getResponseMessage(), Toast.LENGTH_SHORT).show();
                }

            }

        }
    };

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(mcontext.getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
        dbaccess.close();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        dbaccess.open();
        LocalBroadcastManager.getInstance(mcontext.getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(WsSyncService.API_SERVICE_SYNC));
    }

    @Override
    public void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onStop() {
        GlobalBus.getBus().unregister(this);
        super.onStop();
    }
    @Subscribe
    public void onOpenScreen(WsEvents.EventOpenScreen e) {

    }
    @Subscribe
    public void onInputEvent(WsEvents.EventNewChange e) {
        if(e.getActionname().equalsIgnoreCase(WsNewChangeDialog.ACTION_ENTER_NEWCHANGE)){
            String req ="";
            Gson gson = JsonHelper.getGson();
            List<ApiParam> params = new ArrayList<>();
            appContext.setSolutionname(e.getSolution_name());
            appContext.setDeviceid(e.getValue());
            params.add(
              new ApiParam("newuser", "True")
            );
            params.add(
                    new ApiParam("solutionname", appContext.getSolutionname())
            );
            params.add(
                    new ApiParam("deviceid", appContext.getDeviceid())
            );
//            Date toDate = new Date(System.currentTimeMillis());
            Date ts = new GregorianCalendar(2001, 0, 1, 0, 0, 0).getTime();
            appContext.setTs(ts);
            String jsonString = gson.toJson(params);
            Log.i(TAG, "onInputEvent: " + jsonString);
            ApiModel apimodel= new ApiModel(1,ApiModel.GETACTIONLIST, ApiModel.TYPE_GET, jsonString );
            jsonString = gson.toJson(apimodel);
            try {
                req =  HexStringConverter.getHexStringConverterInstance().stringToHex(jsonString);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            Log.i(TAG, "onInputEvent: " + req);

//            String req = "7b226964223a312c226e616d65223a22676574416374696f6e4c697374222c2274797065223a22676574222c226d657373616765223a225b7b5c226e616d655c223a5c226e6577757365725c222c5c2276616c75655c223a5c22547275655c227d2c7b5c226e616d655c223a5c22736f6c7574696f6e6e616d655c222c5c2276616c75655c223a5c22574d535c227d5d227d";

            appContext.setRequestMessage(req);
            WsApi wsapi = new WsApi(appContext);
            wsapi.getActionList();
        }
        Toast.makeText(getContext(), e.getActionname(), Toast.LENGTH_SHORT).show();
    }

    private List<Item> filter(List<Item> pl, String query) {

        query = query.toLowerCase();
        final List<Item> filterdModeList = new ArrayList<>();

        for (Item model : pl) {
            final String tex = model.getItemName().toLowerCase();
            if (tex.startsWith(query)) {
                filterdModeList.add(model);
            }
        }
        return filterdModeList;
    }

}
