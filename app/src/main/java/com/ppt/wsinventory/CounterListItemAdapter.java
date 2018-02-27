package com.ppt.wsinventory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.inventory.model.InventoryAllProducts;
import com.ppt.wsinventory.inventory.model.Inventory_BinLoc;
import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.model.InventoryBIN;

import java.util.ArrayList;

import butterknife.OnClick;

/**
 * Created by User on 26/02/2018.
 */

public class CounterListItemAdapter extends RecyclerView.Adapter<CounterListItemAdapter.MyViewHolder> {

    ArrayList<Inventory_BinLoc> mDataSet;
    Context mContext;
    Inventory_BinLoc inventory_binLoc;
    GlobalVariables appContext;

    public CounterListItemAdapter(ArrayList<Inventory_BinLoc> mDataSet, Context mContext) {
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

        Inventory_BinLoc inventory_binLoc;
        public TextView txtbin_name, txtlocation;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtbin_name = (TextView) itemView.findViewById(R.id.bin_name);
            txtlocation = (TextView) itemView.findViewById(R.id.location);
        }

        public void setData(Inventory_BinLoc inventory_binLoc) {
            this.inventory_binLoc = inventory_binLoc;
        }

        @Override
        public void onClick(View View)
        {

            Log.i("APT", "onClick: true "  + inventory_binLoc.getId());
            Toast.makeText(mContext, "on click it "+inventory_binLoc.getId(), Toast.LENGTH_SHORT).show();
            appContext.setBinid(inventory_binLoc.getId());
            Intent intent = new Intent(mContext, ConformBin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
//            GlobalBus.getBus().post(
//                    new WsEvents.EventOpenScreen("open_confirm_counter")
//            );
        }
    }

}
