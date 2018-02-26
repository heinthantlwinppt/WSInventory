package com.ppt.wsinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppt.wsinventory.inventory.model.InventoryAllProducts;
import com.ppt.wsinventory.inventory.model.Inventory_BinLoc;
import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.model.InventoryBIN;

import java.util.ArrayList;

/**
 * Created by User on 26/02/2018.
 */

public class CounterListItemAdapter extends RecyclerView.Adapter<CounterListItemAdapter.MyViewHolder> {

    ArrayList<Inventory_BinLoc> mDataSet;
    Context mContext;
    Inventory_BinLoc inventory_binLoc;

    public CounterListItemAdapter(ArrayList<Inventory_BinLoc> mDataSet, Context mContext) {
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
        inventory_binLoc = mDataSet.get(position);
        holder.setData(mDataSet.get(position));
        holder.txtbin_name.setText(inventory_binLoc.getBin_name());
        holder.txtlocation.setText(inventory_binLoc.getLocation_name());
//        holder.txt_prod_wgt.setText(String.valueOf(productReceiving.getWeight()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Inventory_BinLoc inventory_binLoc;
        public TextView txtbin_name, txtlocation;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        public void setData(Inventory_BinLoc inventory_binLoc) {
            this.inventory_binLoc = inventory_binLoc;
        }
    }
}
