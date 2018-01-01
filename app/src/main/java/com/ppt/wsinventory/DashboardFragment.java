package com.ppt.wsinventory;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.ppt.wsinventory.wsdb.DbAccess;
import com.ppt.wsinventory.model.Item;
import com.ppt.wsinventory.util.ScreenUtility;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements RecyclerViewAdapter.ItemListener {

    SearchView searchView;


    RecyclerView recyclerView;
    List<RecyclerDataModel> arrayList;
    List<Item> ItemList = new ArrayList<>();
    private float dpWidth;
    private float dpHeight;
    RecyclerViewAdapter adapter;

    DbAccess dbaccess;
    private Context mcontext;


    public DashboardFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        ItemList = dbaccess.getAllItems();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        arrayList = new ArrayList<String[]>();
//        arrayList.add(new RecyclerDataModel("Item 1", R.drawable.battle, "#09A9FF"));
//        arrayList.add(new RecyclerDataModel("Item 2", R.drawable.beer, "#3E51B1"));
//        arrayList.add(new RecyclerDataModel("Item 3", R.drawable.ferrari, "#673BB7"));
//        arrayList.add(new RecyclerDataModel("Item 4", R.drawable.jetpack_joyride, "#4BAA50"));
//        arrayList.add(new RecyclerDataModel("Item 5", R.drawable.three_d, "#F94336"));
//        arrayList.add(new RecyclerDataModel("Item 6", R.drawable.terraria, "#0A9B88"));
//        arrayList.add(new RecyclerDataModel("Item 7", R.drawable.terraria, "#0A9B88"));
//        arrayList.add(new RecyclerDataModel("Item 8", R.drawable.terraria, "#0A9B88"));
//        arrayList.add(new RecyclerDataModel("Item 9", R.drawable.terraria, "#0A9B88"));

//        arrayList.add(new RecyclerDataModel("Sale", R.drawable.battle));
//        arrayList.add(new RecyclerDataModel("Inventory", R.drawable.terraria));
//        arrayList.add(new RecyclerDataModel("Achieve", R.drawable.beer));
//        arrayList.add(new RecyclerDataModel("Order", R.drawable.ferrari));
//        arrayList.add(new RecyclerDataModel("Payment", R.drawable.jetpack_joyride));

//        arrayList.add(Item.COLUMN_ALL);


         adapter = new RecyclerViewAdapter(getContext(), ItemList, this);
        recyclerView.setAdapter(adapter);


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
       w = 200;
//       w = w/d;
//        float density = this.getResources().getDisplayMetrics().density;
//
//        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            if(outMetrics.widthPixels > 1000){
//                dpWidth = outMetrics.widthPixels / 2;
//            }else {
//                dpWidth = outMetrics.widthPixels / 3;
//            }
//        }else
//        {
//            if(outMetrics.widthPixels > 1280){
//                dpWidth = outMetrics.widthPixels / 3;
//            }else {
//                dpWidth = outMetrics.widthPixels / 4;
//            }
//
//        }



        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getContext(), (int)w);
        recyclerView.setLayoutManager(layoutManager);



      return rootView;
    }

    @Override
    public void onItemClick(Item item) {

        Toast.makeText(getContext(), item.getItemName() + " is clicked", Toast.LENGTH_SHORT).show();

    }

//    @Override
//    public void onItemClick(RecyclerDataModel item) {
//
//        Toast.makeText(getContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
//
//
//
//       }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
       ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor((R.color.white)));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(!searchView.isIconfiedByDefault()) {
                    searchView.setIconified(true);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                final List<Item> filtermodelist = filter(ItemList,newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        dbaccess.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        dbaccess.open();
    }


    private List<Item> filter(List<Item> pl,String query)
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


}
