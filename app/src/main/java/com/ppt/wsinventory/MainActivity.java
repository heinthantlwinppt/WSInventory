package com.ppt.wsinventory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.common.WsNewChangeDialog;
import com.ppt.wsinventory.wsdb.DbAccess;
import com.ppt.wsinventory.model.Item;
import com.ppt.wsinventory.websocket.WsApi;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    SearchView searchView;
    String mCurrentFragmentTag;
    DbAccess dbAccess;
//    Item item;
    List<Item> ItemList = new ArrayList<>();
    RecyclerViewAdapter adapter;
    private GlobalVariables appContext;
    private static final String ITEMLISTFRAGMENT_TAG = "ItemListFragment_tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbAccess = new DbAccess(this);
        dbAccess.open();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (!TextUtils.isEmpty(StateManager.getInstance().getCurrentFragmentTag())) {

            mCurrentFragmentTag = StateManager.getInstance().getCurrentFragmentTag();
        }else {

            mCurrentFragmentTag = ITEMLISTFRAGMENT_TAG;
        }

        LoadItemList();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.main, menu);
//        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
//        searchView = (SearchView) myActionMenuItem.getActionView();
//        ((EditText) searchView.findViewById(R.id.search))
//        changeSerchViewTextColor(searchView);
//        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
//                .setHintTextColor(getResources().getColor((R.color.white)));
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                if(!searchView.isIconfiedByDefault()) {
//                    searchView.setIconified(true);
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                final List<Item> filtermodelist = filter(ItemList,newText);
//                adapter.setfilter(filtermodelist);
//                return true;
//
//            }
//        });
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
            WsApi wsapi = new WsApi(this);
            wsapi.TestWebSocket();
//            exportDatabse("WS.db");
            Toast.makeText(this, "go to database", Toast.LENGTH_SHORT).show();

//            return true;
        }
        else if(id == R.id.saveItems) {

            exportDatabse("WS.db");
            Toast.makeText(this, " database is exported", Toast.LENGTH_SHORT).show();

//            dbAccess = new DbAccess(getApplicationContext());
//            dbAccess.open();
////            item =  StateManager.getInstance().getItem();
//
//            item = new Item();
//            item.setItemName("Inventory");
//            item.setItemType("Item_2");
//            dbAccess.insertItems(item);
        }

        return super.onOptionsItemSelected(items);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this,"click_gallery navigation",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, BackUpDatabase.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_new_change_user) {

            WsNewChangeDialog wsInputDialog = new WsNewChangeDialog();
            Bundle args = new Bundle();
            wsInputDialog.setArguments(args);
            args.putString(wsInputDialog.ACTION_NAME, WsNewChangeDialog.ACTION_ENTER_NEWCHANGE);
            wsInputDialog.show(getFragmentManager(), WsNewChangeDialog.ACTION_ENTER_NEWCHANGE);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        LoadItemList();

        return true;
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

    private void LoadItemList() {

        removeFragment();

        DashboardFragment frag = new DashboardFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .add(R.id.MyContainer, frag ,ITEMLISTFRAGMENT_TAG)
                .commit();


        mCurrentFragmentTag = ITEMLISTFRAGMENT_TAG;
        StateManager.getInstance().setCurrentFragmentTag(mCurrentFragmentTag);

//        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbAccess.open();
        LoadItemList();

    }

    @Override
    protected void onPause() {
        super.onPause();
        dbAccess.close();

    }

    @Override
    protected void onResume() {
        super.onResume();
        dbAccess.open();
        LoadItemList();
    }

    public void exportDatabse(String databaseName) {
        try {
//            File sd = Environment.getExternalStorageDirectory();
            File sd = new File("/mnt/sdcard");
            File folder = new File(sd.getAbsolutePath() + "/WSFolder");
            folder.mkdir();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + getPackageName() + "//databases//" + databaseName + "";
                String backupDBPath = "WS.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(folder, backupDBPath);

//                if (currentDB.exists()) {
//                    FileChannel src = new FileInputStream(currentDB).getChannel();
//                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
//                    dst.transferFrom(src, 0, src.size());
//                    src.close();
//                    dst.close();
//                    Toast.makeText(this, "Database is exported", Toast.LENGTH_SHORT).show();
//                }

                if (currentDB.exists()) {
                    int byteread;
                    int bytesum;
                    InputStream inStream = new FileInputStream(currentDB);
                    String file = Environment.getExternalStorageDirectory().getPath()
                            + "/" + "WS.db";
//                    Log.d("file name checking in  dbFilecondition", file);
                    FileOutputStream fs = new FileOutputStream(backupDB);
                    byte[] buffer = new byte[1444];
                    while ((byteread = inStream.read(buffer)) != -1) {
                        bytesum = byteread;
                        fs.write(buffer, 0, byteread);
                    }
                    inStream.close();
                    fs.close();

                }
            }
        } catch (Exception e) {

        }

    }

    private List<Item> filter(List<Item> pl, String query)
    {

        query = query.toLowerCase();
        final List<Item> filterdModeList = new ArrayList<>();

        for(Item model:pl)
        {
            final String tex = model.getItemName().toLowerCase();
            if(tex.startsWith(query))
            {
                filterdModeList.add(model);
            }
        }
        return filterdModeList;
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LoadItemList();
    }
}
