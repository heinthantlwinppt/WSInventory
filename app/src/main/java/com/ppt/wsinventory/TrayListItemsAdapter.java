package com.ppt.wsinventory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.inventory.model.Inventory_PalletLoc;

import java.util.ArrayList;

/**
 * Created by PPT on 2/26/2018.
 */

public class TrayListItemsAdapter extends RecyclerView.Adapter<TrayListItemsAdapter.MyviewHolder> {
    ArrayList<Inventory_PalletLoc> mDataSet;
    Context mContext;
    Inventory_PalletLoc inventory_palletLoc;
    GlobalVariables appContext;

    public TrayListItemsAdapter(ArrayList<Inventory_PalletLoc> mDataSet, Context mContext) {
        appContext = (GlobalVariables)mContext.getApplicationContext();
        this.mDataSet = mDataSet;
        this.mContext = mContext;
    }

    @Override
    public TrayListItemsAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trayitems, parent, false);
        return new MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrayListItemsAdapter.MyviewHolder holder, int position) {
        inventory_palletLoc = mDataSet.get(position);
        holder.setData(mDataSet.get(position));
        holder.txtbin_name.setText(inventory_palletLoc.getPallet_name());
        holder.txtlocation.setText(inventory_palletLoc.getLocation_name());
        holder.txtbarcode.setText(inventory_palletLoc.getBarcode());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Inventory_PalletLoc inventory_palletLoc;
        public TextView txtbin_name, txtlocation,txtbarcode;

        public MyviewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtbin_name = (TextView) itemView.findViewById(R.id.bin_name);
            txtlocation = (TextView) itemView.findViewById(R.id.location);
            txtbarcode = (TextView) itemView.findViewById(R.id.txt_barcode);
        }

        public void setData(Inventory_PalletLoc inventory_palletLoc) {
            this.inventory_palletLoc = inventory_palletLoc;
        }


        @Override
        public void onClick(View View)
        {

            Log.i("APT", "onClick: true "  + inventory_palletLoc.getId());
            Toast.makeText(mContext, "on click it "+inventory_palletLoc.getId(), Toast.LENGTH_SHORT).show();
            appContext.setPalletId(inventory_palletLoc.getId());
            Intent intent = new Intent(mContext, ConfirmPallet.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
//            GlobalBus.getBus().post(
//                    new WsEvents.EventOpenScreen("open_confirm_counter")
//            );
        }
    }
}
