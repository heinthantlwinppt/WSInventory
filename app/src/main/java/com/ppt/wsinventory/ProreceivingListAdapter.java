package com.ppt.wsinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.model.Smith_joborder;
import com.ppt.wsinventory.util.Utility;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by User on 08/02/2018.
 */

public class ProreceivingListAdapter extends RecyclerView.Adapter<ProreceivingListAdapter.MyViewHolder> {
    ArrayList<Inventory_SmithJob> mDataSet;
    Context mContext;
    Inventory_SmithJob smith_joborder;

    public ProreceivingListAdapter(ArrayList<Inventory_SmithJob> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public ProreceivingListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prorecevinglist_items,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProreceivingListAdapter.MyViewHolder holder, int position) {
        smith_joborder = mDataSet.get(position);
        holder.setData(mDataSet.get(position));
        holder.order_no.setText(smith_joborder.getJoborder_no());
        holder.delivery_date.setText(Utility.dateFormat.format(smith_joborder.getDate_end()));
        holder.smith.setText(smith_joborder.getName());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView order_no,delivery_date,smith;
        Inventory_SmithJob inventorySmithJob;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            order_no = itemView.findViewById(R.id.orderno);
            delivery_date = itemView.findViewById(R.id.delivery_date);
            smith = itemView.findViewById(R.id.smith);

        }

        public void setData(Inventory_SmithJob smithjob_item){

            this.inventorySmithJob = smithjob_item;
        }
        @Override
        public void onClick(View v) {

            Toast.makeText(mContext,"Click Order no" +inventorySmithJob.getJoborder_no(),Toast.LENGTH_SHORT).show();

        }
    }
}
