package com.ppt.wsinventory;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.WsEvents;

import org.greenrobot.eventbus.Subscribe;

public class DashboardSmithJobOrder extends AppCompatActivity {

    String mCurrentFragmentTag;
    private static final String SMITHJOBFRAGMENT_TAG = "smithjobfragment_tag";
    private Context mContext;
    private GlobalVariables appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_smith_job_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appContext = (GlobalVariables) getApplicationContext();

        if (!TextUtils.isEmpty(StateManager.getInstance().getCurrentFragmentTag())) {

            mCurrentFragmentTag = StateManager.getInstance().getCurrentFragmentTag();
        }else {

            mCurrentFragmentTag = SMITHJOBFRAGMENT_TAG;
        }
        LoadSmithJobOrder();
    }

    private void LoadSmithJobOrder() {

        removeFragment();

        SmithJobFragment frag = new SmithJobFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.SmithJOrder_Containner, frag ,SMITHJOBFRAGMENT_TAG)
                .commit();


        mCurrentFragmentTag = SMITHJOBFRAGMENT_TAG;
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






}
