package com.ppt.wsinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.model.Manufacturing_smith_joborder;
import com.ppt.wsinventory.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 16/01/2018.
 */

public class SmithJobAdapter extends RecyclerView.Adapter<SmithJobAdapter.MyViewHolder> {
    ArrayList<Inventory_SmithJob> mDataSet;
    Context mContext;
    Inventory_SmithJob smith_joborder;

    public SmithJobAdapter(ArrayList<Inventory_SmithJob> mDataSet) {
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

        smith_joborder = mDataSet.get(position);
        holder.setData(mDataSet.get(position));
        holder.txt_joborder_no.setText(String.valueOf(smith_joborder.getJoborder_no()));
        holder.txt_name.setText(String.valueOf(smith_joborder.getName()) +","+smith_joborder.getNickname()
        +", Smith"+smith_joborder.getSmith_id());
        holder.txt_orderdate.setText( Utility.dateFormat.format(smith_joborder.getDate_start()));
        if(smith_joborder.getRemarks() != null)
        holder.remarks.setText(Utility.dateFormat.format( smith_joborder.getRemarks())+", အသစ္ျပင္");
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txt_joborder_no,txt_name, txt_orderdate,remarks;
        Inventory_SmithJob inventorySmithJob;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txt_joborder_no = itemView.findViewById(R.id.joborder_no);
            txt_joborder_no.setAllCaps(true);
            txt_name = itemView.findViewById(R.id.name);
            txt_name.setAllCaps(true);
//            txt_name.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
            txt_orderdate = itemView.findViewById(R.id.orderdate);
            remarks = itemView.findViewById(R.id.remarks);
//            remarks.setAllCaps(true);
        }

        public void setData(Inventory_SmithJob dashboarditem) {
            this.inventorySmithJob = dashboarditem;

//            textView.setText(dashboarditem.getTitle());
//            textView1.setText(dashboarditem.getGroupname());
//            relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }

        @Override
        public void onClick(View v) {
            String joborder_no = inventorySmithJob.getJoborder_no();
            GlobalBus.getBus().post(
                    new WsEvents.EventOpenSmithJob(joborder_no));
        }
    }

    public void setfilter(List<Inventory_SmithJob> itemList) {
        mDataSet = new ArrayList<>();
        mDataSet.addAll(itemList);
        notifyDataSetChanged();
    }


}
