package com.ppt.wsinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.inventory.model.Inventory_production_receiving;
import com.ppt.wsinventory.util.Utility;

import java.util.ArrayList;

/**
 * Created by User on 15/02/2018.
 */

public class ProdhdrRecyclerAdapter extends RecyclerView.Adapter<ProdhdrRecyclerAdapter.MyViewHolder> {

    ArrayList<Inventory_production_receiving> mDataSet;
    Context mContext;
    Inventory_production_receiving inventory_prodhdr;

    public ProdhdrRecyclerAdapter(ArrayList<Inventory_production_receiving> mDataSet, Context mContext) {
        this.mDataSet = mDataSet;
        this.mContext = mContext;
    }

    @Override
    public ProdhdrRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_prodhdr, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProdhdrRecyclerAdapter.MyViewHolder holder, int position) {

        inventory_prodhdr = mDataSet.get(position);
        holder.setData(mDataSet.get(position));
        holder.txt_voucher_no.setText(String.valueOf(inventory_prodhdr.getVoucher_no()));
        holder.txt_product_no.setText(String.valueOf(inventory_prodhdr.getProd_no()));
        holder.txt_smith_no.setText(String.valueOf(inventory_prodhdr.getSmit_id()));
        holder.txt_date.setText(Utility.dateFormatDoc.format(inventory_prodhdr.getProd_date()));


    }



    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Inventory_production_receiving inventory_prodhdr;
        public TextView txt_voucher_no,txt_product_no,txt_smith_no,txt_date;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt_voucher_no = (TextView)itemView.findViewById(R.id.prodhdr_voucher_no);
            txt_product_no = (TextView)itemView.findViewById(R.id.prodhdr_product_no);
            txt_smith_no = (TextView)itemView.findViewById(R.id.prodhdr_smith_name);
            txt_date = (TextView)itemView.findViewById(R.id.prodhdr_date);

        }

        public void setData(Inventory_production_receiving inventory_prodhdr) {
            this.inventory_prodhdr = inventory_prodhdr;

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(mContext,inventory_prodhdr.getVoucher_no()+"Clicked",Toast.LENGTH_SHORT).show();
        }
    }
}
