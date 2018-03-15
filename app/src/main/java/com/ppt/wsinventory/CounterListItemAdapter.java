package com.ppt.wsinventory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppt.wsinventory.model.InventoryBIN;

import java.util.ArrayList;

/**
 * Created by User on 26/02/2018.
 */

public class CounterListItemAdapter extends RecyclerView.Adapter<CounterListItemAdapter.MyViewHolder> {

    ArrayList<InventoryBIN> mDataSet;
    Context mContext;
    InventoryBIN inventory_binLoc;
    GlobalVariables appContext;

    public CounterListItemAdapter(ArrayList<InventoryBIN> mDataSet, Context mContext) {
        this.mDataSet = mDataSet;
        this.mContext = mContext;
        appContext = (GlobalVariables)mContext.getApplicationContext();

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
        return mDataSet.size();
    }

    public class MyViewHolder extends
            RecyclerView.ViewHolder
            implements View.OnClickListener{

        InventoryBIN inventory_binLoc;
        public TextView txtbin_name, txtlocation;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtbin_name = (TextView) itemView.findViewById(R.id.bin_name);
            txtlocation = (TextView) itemView.findViewById(R.id.location);
        }

        public void setData(InventoryBIN inventory_binLoc) {
            this.inventory_binLoc = inventory_binLoc;
        }

        @Override
        public void onClick(View View)
        {

            appContext.setBinid(inventory_binLoc.getId());
            Intent intent = new Intent(mContext, ConfirmBin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
//            GlobalBus.getBus().post(
//                    new WsEvents.EventOpenScreen("open_confirm_counter")
//            );
        }
    }

}
