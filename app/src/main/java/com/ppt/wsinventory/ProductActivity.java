package com.ppt.wsinventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.common.WsNewChangeDialog;
import com.ppt.wsinventory.common.WsSearchDialog;

import org.greenrobot.eventbus.Subscribe;

public class ProductActivity extends AppCompatActivity {

    private GlobalVariables appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appContext = (GlobalVariables) getApplicationContext();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appContext.setProductname("");
                appContext.setGroupname("");
                appContext.setSubgroupname("");
                Intent intent = new Intent(ProductActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.search:
                WsSearchDialog wsSearchDialog = new WsSearchDialog();
                Bundle args = new Bundle();
                wsSearchDialog.setArguments(args);
                args.putString(wsSearchDialog.ACTION_NAME, wsSearchDialog.ACTION_ENTER_SEARCH);
                wsSearchDialog.show(getFragmentManager(), "Search Product");

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        appContext.setProductname("");
        appContext.setGroupname("");
        appContext.setSubgroupname("");
//        super.onBackPressed();
        finish();
    }
}
