package com.ppt.wsinventory;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
    RecyclerView recyclerView;
    String mCurrentFragmentTag;
    List<AdministrationWsdashboard> smithJobOrderList = new ArrayList<>();
    private static final String TAG = "Ws-Smithjob";
    private static final String SMITHJOBDETAILFRAGMENT_TAG = "smithjobdetailfragment";

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

//        dbAccess = new DbAccess(this);
//        dbAccess.open();
//        scrollView = (RecyclerView) findViewById(R.id.recyclerView_SmithJob);
        if (!TextUtils.isEmpty(StateManager.getInstance().getCurrentFragmentTag())) {

            mCurrentFragmentTag = StateManager.getInstance().getCurrentFragmentTag();
        }else {

            mCurrentFragmentTag = SMITHJOBDETAILFRAGMENT_TAG;
        }
        LoadSmithJobOrder();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

//    private void loadRecyclerView() {
//
//        smithJobOrderList = dbAccess.getAllDashboardItems();
//        Log.i(TAG, "onReceive: " + smithJobOrderList);
//        adapter = new SmithJobOrderAdapter((ArrayList<AdministrationWsdashboard>) smithJobOrderList);
//        scrollView.setAdapter(adapter);
//    }

    private void LoadSmithJobOrder() {

        removeFragment();

        SmithJobDetailFragment frag = new SmithJobDetailFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.content, frag ,SMITHJOBDETAILFRAGMENT_TAG)
                .commit();


        mCurrentFragmentTag = SMITHJOBDETAILFRAGMENT_TAG;
        StateManager.getInstance().setCurrentFragmentTag(mCurrentFragmentTag);

//        finish();
    }

    private void removeFragment() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentByTag(mCurrentFragmentTag);

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragment)
                    .commit();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();

//        LoadSmithJobOrder();
    }

    @Override
    public void onBackPressed() {

//            super.onBackPressed();
            finish();

    }

}
