package com.ppt.wsinventory;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DashboardSmithJob extends AppCompatActivity {

    private TextView mTextMessage;
    DbAccess dbAccess;
    List<AdministrationWsdashboard> smithJobOrderList = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_dashboard1:
                    mTextMessage.setText("List");
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
//            minflater = inflater;
        smithJobOrderList = dbAccess.getAllDashboardItems();
        SmithJobOrderAdapter adapter = new SmithJobOrderAdapter( this, smithJobOrderList);
        ListView listView = findViewById(R.id.lv_smithorder);
        listView.setAdapter(adapter);
    }



}
