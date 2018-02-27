package com.ppt.wsinventory;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.Gold;

public class InventoryCounters extends AppCompatActivity {
    GlobalVariables appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_counters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appContext = (GlobalVariables) getApplicationContext();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {

        Log.i("APT", "onBackPressed: " + appContext.getParientid());

        GlobalBus.getBus().post(
                new WsEvents.EventOpenScreen("open_dashboard"));

        Log.i("APT", "onBackPressed: asdf" + appContext.getParientid());


    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        GlobalBus.getBus().register(this);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        GlobalBus.getBus().unregister(this);
//    }
}
