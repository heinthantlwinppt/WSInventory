package com.ppt.wsinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.inventory.model.ProductReceiving;
import java.util.ArrayList;

/**
 * Created by User on 16/02/2018.
 */

public class ProductReceivingAdapter extends RecyclerView.Adapter<ProductReceivingAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<ProductReceiving> mDataSet;
    ProductReceiving productReceiving;

    public ProductReceivingAdapter(ArrayList<ProductReceiving> productReceivings,Context context)
    {
        mContext = context;
        mDataSet = productReceivings;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receive_adapter_layout,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        productReceiving = mDataSet.get(position);
        holder.setData(mDataSet.get(position));
        holder.txt_prod_qty.setText(String.valueOf(productReceiving.getQty()));
        holder.txt_prod_name.setText(productReceiving.getDesignname());
        holder.txt_prod_wgt.setText(String.valueOf(productReceiving.getWeight()));

    }



    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ProductReceiving productReceiving;
        TextView txt_prod_name, txt_prod_type, txt_prod_qty,txt_prod_wgt;
        public MyViewHolder(View itemView) {
            super(itemView);

            txt_prod_name =(TextView)itemView.findViewById(R.id.product_name);
            txt_prod_type =(TextView)itemView.findViewById(R.id.product_type);
            txt_prod_wgt =(TextView)itemView.findViewById(R.id.prod_weight);
            txt_prod_qty =(TextView)itemView.findViewById(R.id.product_quantity);
        }

        public void setData(ProductReceiving productReceiving)
        {
            this.productReceiving = productReceiving;
        }


        @Override
        public void onClick(View v) {

        }
    }
}
