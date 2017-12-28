package com.ppt.wsinventory;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemListFragment extends Fragment implements RecyclerViewAdapter.ItemListener {


    RecyclerView recyclerView;
    ArrayList<RecyclerDataModel> arrayList;
    private float dpWidth;
    private float dpHeight;

    public ItemListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_itemlist, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new RecyclerDataModel("Item 1", R.drawable.battle, "#09A9FF"));
        arrayList.add(new RecyclerDataModel("Item 2", R.drawable.beer, "#3E51B1"));
        arrayList.add(new RecyclerDataModel("Item 3", R.drawable.ferrari, "#673BB7"));
        arrayList.add(new RecyclerDataModel("Item 4", R.drawable.jetpack_joyride, "#4BAA50"));
        arrayList.add(new RecyclerDataModel("Item 5", R.drawable.three_d, "#F94336"));
        arrayList.add(new RecyclerDataModel("Item 6", R.drawable.terraria, "#0A9B88"));
        arrayList.add(new RecyclerDataModel("Item 7", R.drawable.terraria, "#0A9B88"));
        arrayList.add(new RecyclerDataModel("Item 8", R.drawable.terraria, "#0A9B88"));
        arrayList.add(new RecyclerDataModel("Item 9", R.drawable.terraria, "#0A9B88"));


        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), arrayList, this);
        recyclerView.setAdapter(adapter);


        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = this.getResources().getDisplayMetrics().density;

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            if(outMetrics.widthPixels > 1000){
                dpWidth = outMetrics.widthPixels / 2;
            }else {
                dpWidth = outMetrics.widthPixels / 3;
            }
        }else
        {
            if(outMetrics.widthPixels > 1280){
                dpWidth = outMetrics.widthPixels / 3;
            }else {
                dpWidth = outMetrics.widthPixels / 4;
            }

        }



        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getContext(), (int)dpWidth);
        recyclerView.setLayoutManager(layoutManager);

      return rootView;
    }

    @Override
    public void onItemClick(RecyclerDataModel item) {

        Toast.makeText(getContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }
}
