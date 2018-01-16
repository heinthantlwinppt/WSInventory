package com.ppt.wsinventory;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;

public class DashboardSmithJob extends AppCompatActivity  {

    private TextView mTextMessage;
    DbAccess dbAccess;
    SmithJobOrderAdapter adapter;
    RecyclerView recyclerView;
    List<AdministrationWsdashboard> smithJobOrderList = new ArrayList<>();
    private static final String TAG = "Ws-Smithjob";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_dashboard1:
//                    mTextMessage.setText("List");
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_smith_job);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.clearAnimation();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        dbAccess = new DbAccess(this);
        dbAccess.open();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_SmithJob);
        loadRecyclerView();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    private void loadRecyclerView() {

        smithJobOrderList = dbAccess.getAllDashboardItems();
        Log.i(TAG, "onReceive: " + smithJobOrderList);
        adapter = new SmithJobOrderAdapter((ArrayList<AdministrationWsdashboard>) smithJobOrderList);
        recyclerView.setAdapter(adapter);
    }


}
