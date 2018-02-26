package com.ppt.wsinventory;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.common.WsInputDialog;
import com.ppt.wsinventory.common.WsNewChangeDialog;
import com.ppt.wsinventory.model.ActionList;
import com.ppt.wsinventory.model.AdministrationSettings;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.model.ApiModel;
import com.ppt.wsinventory.model.ApiParam;
import com.ppt.wsinventory.model.Goods;
import com.ppt.wsinventory.model.WsDashboardModel;
import com.ppt.wsinventory.services.WsService;
import com.ppt.wsinventory.services.WsSyncService;
import com.ppt.wsinventory.util.HexStringConverter;
import com.ppt.wsinventory.util.JsonHelper;
import com.ppt.wsinventory.util.MessageBox;
import com.ppt.wsinventory.websocket.WsApi;
import com.ppt.wsinventory.wsdb.DbAccess;
import com.ppt.wsinventory.util.ScreenUtility;

import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static android.text.TextUtils.isEmpty;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements RecyclerViewAdapter.ItemListener {
    SearchView searchView;
    private GlobalVariables appContext;
    RecyclerView recyclerView;
    List<RecyclerDataModel> arrayList;
    List<WsDashboardModel> ItemList = new ArrayList<>();
    //    private float dpWidth;
    private float dpHeight;
    RecyclerViewAdapter adapter;
    AdministrationSettings administrationSettings;
    DbAccess dbaccess;
    private Context mContext;
    private static final String TAG = "Ws-Dashboard";
    BusinessLogic b1;
    float w;
    ProgressBar my_progress;


    private void ShowProgress(boolean show) {
        my_progress.setVisibility(show ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);

    }


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Subscribe
    public void reloadAdapter()
    {
        if (adapter!=null)
        {
            adapter.goBack();
        }
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
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        setHasOptionsMenu(true);
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        my_progress = rootView.findViewById(R.id.confirm_progress);


        loadRecyclerView();

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
//        rootView.setOnKeyListener( new View.OnKeyListener(){
//            @Override
//            public boolean onKey( View v, int keyCode, KeyEvent event )
//            {
//                if( keyCode == KeyEvent.KEYCODE_BACK )
//                {
//                    adapter.goBack();
//                    return true;
//                }
//                return super.onKey(keyCode, event);
//            }
//        } );




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


        return rootView;
    }
//    @Override
//    public void onDetach()
//    {
//        super.onDetach();
//        adapter.goBack();
//
//    }
    //onItemClick
    @Override
    public void onItemClick(AdministrationWsdashboard item) {
//        if(item.getActionname().equalsIgnoreCase(WsEvents.OPEN_RECEIVING_INVENTORY)){
//
//            Toast.makeText(mContext, "Show Receiving Detail", Toast.LENGTH_SHORT).show();
//        }
//        else if(item.getActionname().equalsIgnoreCase(WsEvents.OPEN_TAGGING_INVENTORY)){
//
//            Toast.makeText(mContext, "Show Tagging Detail", Toast.LENGTH_SHORT).show();
//
//        }
//        Toast.makeText(getContext(), item.getTitle() + " is clicked", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu,inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = new SearchView(((MainActivity) mContext).getSupportActionBar().getThemedContext());
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

                final List<WsDashboardModel> filtermodelist = filter(ItemList, newText);
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
                    ApiModel apiModel = gson.fromJson(response, ApiModel.class);
                    if (apiModel.getName().equalsIgnoreCase(ApiModel.GETACTIONLIST)) {
                        appContext.setActionLists(null);
                        List<ActionList> actionLists = new ArrayList<>();
                        Type listType = new TypeToken<ArrayList<ActionList>>() {
                        }.getType();
                        actionLists = gson.fromJson(apiModel.getMessage(), listType);
                        appContext.setActionLists(actionLists);
                        if (appContext.getActionLists().size() > 0)
                            wsApi.doSync();
                    } else {
                        //TODO Implement Delete the tables records
                        wsApi.doSync();
                    }

                } else if (msgtype.equalsIgnoreCase(WsSyncService.SERVICE_ERROR)) {
//                    Toast.makeText(mContext, appContext.getResponseMessage(), Toast.LENGTH_SHORT).show();
                    ShowProgress(false);
                    MessageBox.ShowMessage(getFragmentManager(),
                            appContext.getTranslation("ERROR"),
                            appContext.getTranslation(appContext.getTranslation(appContext.getResponseMessage())),
                            null,
                            null,
                            "OK");
                }

            }

        }
    };

    private BroadcastReceiver mBroadcastIntegration = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(WsSyncService.API_SERVICE_MESSAGE)) {
                String msgtype = intent.getStringExtra(WsSyncService.SERVICE_TYPE);
                WsApi wsApi = new WsApi(context);
                if (msgtype.equalsIgnoreCase(WsSyncService.SERVICE_RESPONSE)) {
                    String response = appContext.getResponseMessage();
                    response = HexStringConverter.getHexStringConverterInstance().hexToString(response);
                    Log.i(TAG, "onReceive: " + response);
                    Gson gson = JsonHelper.getGson();
                    ApiModel apiModel = gson.fromJson(response, ApiModel.class);
                    if (apiModel.getName().equalsIgnoreCase(ApiModel.GETGOODSLIST)) {
                        appContext.setActionLists(null);
                        List<Goods> goodsList = new ArrayList<>();
                        Type listType = new TypeToken<ArrayList<Goods>>() {
                        }.getType();
                        goodsList = gson.fromJson(apiModel.getMessage(), listType);
                        for(Goods goods: goodsList){
                            Log.i(TAG, "onReceive: " + goods.toString());
                            break;
                        }
                    }

                } else if (msgtype.equalsIgnoreCase(WsSyncService.SERVICE_ERROR)) {
//                    Toast.makeText(mContext, appContext.getResponseMessage(), Toast.LENGTH_SHORT).show();
                    ShowProgress(false);
                    MessageBox.ShowMessage(getFragmentManager(),
                            appContext.getTranslation("ERROR"),
                            appContext.getTranslation(appContext.getTranslation(appContext.getResponseMessage())),
                            null,
                            null,
                            "OK");
                }

            }

        }
    };

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(mContext.getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
        LocalBroadcastManager.getInstance(mContext.getApplicationContext())
                .unregisterReceiver(mBroadcastIntegration);
        dbaccess.close();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        dbaccess.open();
        LocalBroadcastManager.getInstance(mContext.getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(WsSyncService.API_SERVICE_SYNC));
        LocalBroadcastManager.getInstance(mContext.getApplicationContext())
                .registerReceiver(mBroadcastIntegration,
                        new IntentFilter(WsService.API_SERVICE_MESSAGE));
    }

    @Override
    public void onStart() {
        super.onStart();
        dbaccess.open();
        GlobalBus.getBus().register(this);
    }

    @Override
    public void onDestroy() {
        dbaccess.close();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        GlobalBus.getBus().unregister(this);
        dbaccess.close();
        super.onStop();
    }

    @Subscribe
    public void onOpenScreen(WsEvents.EventOpenScreen e) {
//        BusinessLogic businessLogic = new BusinessLogic(mContext);
//        businessLogic.openScreen(e);


        Toast.makeText(mContext, e.getActionname()+ appContext.getParientid(), Toast.LENGTH_SHORT).show();
        if (appContext.getActionName().equals("close_folder"))
        {
            adapter.goBack();
        }
        else
        {
            int id = appContext.getParientid();
            appContext.setParientid(id);
            BusinessLogic businessLogic = new BusinessLogic(mContext);
            businessLogic.openScreen(e);
        }
    }

    @Subscribe
    public void onInputEvent(WsEvents.EventInputChange e) {
        if (e.getActionname().equalsIgnoreCase(WsInputDialog.ACTION_ENTER_GOODSID)) {
            String req = "";
            Gson gson = JsonHelper.getGson();
            List<ApiParam> params = new ArrayList<>();

//            appContext.setDeviceid(e.getValue());
            params.add(
                    new ApiParam("goodsid", e.getGoodsid())
            );
            String jsonString = gson.toJson(params);
            Log.i(TAG, "onInputEvent: " + jsonString);
            ApiModel apimodel = new ApiModel(1, ApiModel.GETGOODSLIST, ApiModel.TYPE_GET, jsonString);
            jsonString = gson.toJson(apimodel);
            try {
                req = HexStringConverter.getHexStringConverterInstance().stringToHex(jsonString);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            Log.i(TAG, "onInputEvent: " + req);

//            String req = "7b226964223a312c226e616d65223a22676574416374696f6e4c697374222c2274797065223a22676574222c226d657373616765223a225b7b5c226e616d655c223a5c226e6577757365725c222c5c2276616c75655c223a5c22547275655c227d2c7b5c226e616d655c223a5c22736f6c7574696f6e6e616d655c222c5c2276616c75655c223a5c22574d535c227d5d227d";

            appContext.setRequestMessage(req);
            WsApi wsapi = new WsApi(appContext);
            wsapi.getGoodsID();
        }

        Toast.makeText(getContext(), e.getActionname(), Toast.LENGTH_SHORT).show();

    }

    @Subscribe
    public void onNewChangeEvent(WsEvents.EventNewChange e) {
        if (e.getActionname().equalsIgnoreCase(WsNewChangeDialog.ACTION_ENTER_NEWCHANGE)) {
            String req = "";
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
            ApiModel apimodel = new ApiModel(1, ApiModel.GETACTIONLIST, ApiModel.TYPE_GET, jsonString);
            jsonString = gson.toJson(apimodel);
            try {
                req = HexStringConverter.getHexStringConverterInstance().stringToHex(jsonString);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            Log.i(TAG, "onInputEvent: " + req);

//            String req = "7b226964223a312c226e616d65223a22676574416374696f6e4c697374222c2274797065223a22676574222c226d657373616765223a225b7b5c226e616d655c223a5c226e6577757365725c222c5c2276616c75655c223a5c22547275655c227d2c7b5c226e616d655c223a5c22736f6c7574696f6e6e616d655c222c5c2276616c75655c223a5c22574d535c227d5d227d";

            appContext.setRequestMessage(req);
            WsApi wsapi = new WsApi(appContext);
            wsapi.getActionList();
        }

        ShowProgress(true);
//        loadRecyclerView();
        Toast.makeText(getContext(), e.getActionname(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void ShowMessage(WsEvents.EventShowMessage e) {
        ShowProgress(false);
        MessageBox.ShowMessage(getFragmentManager(),
                appContext.getTranslation(e.getTitle()),
                appContext.getTranslation(appContext.getTranslation(e.getCaption())),
                e.getAction(),
                e.getButtonLeft(),
                e.getButtonRight());
        loadRecyclerView();
    }

    private List<WsDashboardModel> filter(List<WsDashboardModel> pl, String query) {

        query = query.toLowerCase();
        final List<WsDashboardModel> filterdModeList = new ArrayList<>();

        for (WsDashboardModel model : pl) {
            final String tex = model.getTitle().toLowerCase();
            if (tex.startsWith(query)) {
                filterdModeList.add(model);
            }
        }
        return filterdModeList;
    }

    private void loadRecyclerView() {

        if (appContext.getParientid() == 0)
        {
            ItemList = dbaccess.getAllDashboardItems();
        }
        else
        {
          ItemList = dbaccess.getparient(appContext.getParientid());
        }

        adapter = new RecyclerViewAdapter(getContext(), ItemList, this);
        recyclerView.setAdapter(adapter);
        administrationSettings = dbaccess.getAdministrationSettings();

        if (!isEmpty(administrationSettings.getId())) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

                w = administrationSettings.getDashboarditempwith();
            } else {
                w = administrationSettings.getDashboarditemlwith();
            }
        } else {

            w = 200;
        }

//        w = administrationSettings.getDashboarditemlwith();


        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getContext(), (int) w);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
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
}
