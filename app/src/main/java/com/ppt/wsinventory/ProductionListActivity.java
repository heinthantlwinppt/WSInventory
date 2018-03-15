package com.ppt.wsinventory;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.ppt.wsinventory.wsdb.DbAccess;

public class ProductionListActivity extends AppCompatActivity {
    GlobalVariables appContext;
    DbAccess dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receving_inventory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
        dbAccess = new DbAccess(this);
        dbAccess.open();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbAccess.open();
    }

    @Override
    protected void onPause() {
        dbAccess.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.prodhdr_menu, menu);
        return true;
    }

}
