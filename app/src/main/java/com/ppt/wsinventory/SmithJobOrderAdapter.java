package com.ppt.wsinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by User on 15/01/2018.
 */

public class SmithJobOrderAdapter extends RecyclerView.Adapter<SmithJobOrderAdapter.DataObjectHolder> {

    ArrayList<AdministrationWsdashboard> mDataSet;
    private Unbinder unbinder;
    Context mContext;
    private static final String TAG = "Ws-smithjob";

    public SmithJobOrderAdapter(ArrayList<AdministrationWsdashboard> myDataset) {
        mDataSet = myDataset;

        Log.i(TAG, "onReceive: " + mDataSet);
    }
    @Override
    public SmithJobOrderAdapter.DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.smithjoborder_items, parent, false);
        mContext = parent.getContext();
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(SmithJobOrderAdapter.DataObjectHolder holder, int position) {
       holder.txt_OrderName.setText(mDataSet.get(position).getGroupname());
       holder.txt_OrderItem.setText(mDataSet.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {

        TextView txt_OrderName,txt_OrderItem;
        public DataObjectHolder(View itemView) {
            super(itemView);

            txt_OrderItem = (TextView) itemView.findViewById(R.id.OrderItem);
            txt_OrderName = (TextView) itemView.findViewById(R.id.OrderName);
        }
    }
}
