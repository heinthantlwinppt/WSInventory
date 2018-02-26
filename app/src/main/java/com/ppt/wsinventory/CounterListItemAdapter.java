package com.ppt.wsinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.model.InventoryBIN;

import java.util.ArrayList;

/**
 * Created by User on 26/02/2018.
 */

public class CounterListItemAdapter extends RecyclerView.Adapter<CounterListItemAdapter.MyViewHolder> {

    ArrayList<InventoryBIN> mDataSet;
    Context mContext;
    InventoryBIN inventoryBIN;

    public CounterListItemAdapter(ArrayList<InventoryBIN> mDataSet, Context mContext) {
        this.mDataSet = mDataSet;
        this.mContext = mContext;
    }

    @Override
    public CounterListItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.counterbin_item, parent, false);
        return new CounterListItemAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CounterListItemAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
