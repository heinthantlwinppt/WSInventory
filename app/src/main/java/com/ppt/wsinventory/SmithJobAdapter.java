package com.ppt.wsinventory;

import android.content.Context;
import android.net.sip.SipSession;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppt.wsinventory.model.AdministrationWsdashboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 16/01/2018.
 */

public class SmithJobAdapter extends RecyclerView.Adapter<SmithJobAdapter.MyViewHolder> {
    ArrayList<AdministrationWsdashboard> mDataSet;
    Context mContext;

    public SmithJobAdapter(ArrayList<AdministrationWsdashboard> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.smithjoborder_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        AdministrationWsdashboard wsdashboard = mDataSet.get(position);
        holder.txt1.setText(wsdashboard.getGroupname());
        holder.txt2.setText(wsdashboard.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt1,txt2;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.OrderName);
            txt2 = itemView.findViewById(R.id.OrderItem);
        }
    }
}
