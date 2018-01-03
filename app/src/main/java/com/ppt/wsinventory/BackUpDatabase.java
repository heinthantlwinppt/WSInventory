package com.ppt.wsinventory;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ppt.wsinventory.wsdb.DbAccess;
import com.ppt.wsinventory.model.Item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class BackUpDatabase extends AppCompatActivity {

    DbAccess dbaccess;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_up_database);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        dbaccess = new DbAccess(getApplicationContext());
//        dbaccess.open();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem items) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = items.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            exportDatabse("WS.db");
            Toast.makeText(this, "go to database", Toast.LENGTH_SHORT).show();
//            return true;
        }
        else if (id == R.id.saveItems)
        {

            dbaccess = new DbAccess(getApplicationContext());
            dbaccess.open();
//            item =  StateManager.getInstance().getItem();

             item = new Item();
             item.setItemName("Sale");
             item.setItemType("Item_1");
            dbaccess.insertItems(item);

        }

        return super.onOptionsItemSelected(items);
    }

    public void exportDatabse(String databaseName) {
        try {
            File sd = Environment.getExternalStorageDirectory();
//            File folder = new File(sd.getAbsolutePath()+"SWFolder");
//            folder.mkdir();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + getPackageName() + "//databases//" + databaseName + "";
                String backupDBPath = "WS.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                    Toast.makeText(this, "Database is exported", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {

        }
    }
}
