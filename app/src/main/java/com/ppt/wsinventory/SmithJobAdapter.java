package com.ppt.wsinventory;

import android.content.Context;
import android.net.sip.SipSession;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.model.Manufacturing_smith_joborder;
import com.ppt.wsinventory.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 16/01/2018.
 */

public class SmithJobAdapter extends RecyclerView.Adapter<SmithJobAdapter.MyViewHolder> {
    ArrayList<Manufacturing_smith_joborder> mDataSet;
    Context mContext;

    public SmithJobAdapter(ArrayList<Manufacturing_smith_joborder> mDataSet) {
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

        Manufacturing_smith_joborder smith_joborder = mDataSet.get(position);
        holder.txt_smith_id.setText(String.valueOf(smith_joborder.getSmith_id()));
        holder.txt_orderid.setText(String.valueOf(smith_joborder.getJoborder_no()));
        holder.txt_date_start.setText(Utility.dateFormat.format( smith_joborder.getDate_start()));
        holder.txt_date_target.setText(Utility.dateFormat.format( smith_joborder.getDate_target()));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txt_smith_id,txt_orderid,txt_date_start,txt_date_target;
        Manufacturing_smith_joborder manufacturing_smith_joborder;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt_smith_id = itemView.findViewById(R.id.smith_id);
            txt_orderid = itemView.findViewById(R.id.orderid);
            txt_date_start = itemView.findViewById(R.id.date_start);
            txt_date_target = itemView.findViewById(R.id.date_target);
        }

        @Override
        public void onClick(View v) {
            GlobalBus.getBus().post(
                    new WsEvents.EventOpenSmithJob(manufacturing_smith_joborder.getJoborder_no()));
        }
    }


}
