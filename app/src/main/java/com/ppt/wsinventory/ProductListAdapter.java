package com.ppt.wsinventory;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.inventory.model.InventoryAllProducts;
import com.ppt.wsinventory.inventory.model.Inventory_SmithJob;
import com.ppt.wsinventory.model.Inventory_products;
import com.ppt.wsinventory.model.Product;
import com.ppt.wsinventory.util.Utility;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25/01/2018.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyProductViewHolder> {

    ArrayList<InventoryAllProducts> mDataSet;
    Context mContext;
    InventoryAllProducts inventoryAllProduct;

    public ProductListAdapter(Context context,ArrayList<InventoryAllProducts> mDataSet) {
        this.mDataSet =  mDataSet;
        mContext = context;
    }

    @Override
    public ProductListAdapter.MyProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_items, parent, false);

        return new MyProductViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ProductListAdapter.MyProductViewHolder holder, int position) {
        inventoryAllProduct = mDataSet.get(position);
        holder.setData(mDataSet.get(position));
        holder.txtdesignname.setText(inventoryAllProduct.getDesign_name());
        holder.txtname.setText(inventoryAllProduct.getProduct_name());
        holder.txtgroupname.setText(inventoryAllProduct.getGroup_name()+"-->"+inventoryAllProduct.getSubgroup_name()+"-->"+inventoryAllProduct.getPlength());
        holder.valueK.setText(String.valueOf(inventoryAllProduct.getReduce_k()));
        holder.valueP.setText(String.valueOf(inventoryAllProduct.getReduce_p()));
        holder.valueY.setText(String.valueOf(inventoryAllProduct.getReduce_y()));
        holder.costK.setText(String.valueOf(inventoryAllProduct.getCost_reduce_k()));
        holder.costP.setText(String.valueOf(inventoryAllProduct.getCost_reduce_p()));
        holder.costY.setText(String.valueOf(inventoryAllProduct.getCost_reduce_y()));
        holder.minqty.setText(String.valueOf(inventoryAllProduct.getMinqty()));
        holder.maxqty.setText(String.valueOf(inventoryAllProduct.getMaxqty()));

        File dir = Utility.creatdesignfolder(inventoryAllProduct.getPhoto_type());
        File my_file = new File(dir, inventoryAllProduct.getPhoto_name() + ".png");
        if(my_file.exists()) {
            Picasso.with(mContext).load(my_file).resize(50, 50).into(holder.productPhoto);
        }

        holder.productPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Click " + mDataSet.get(position).getProduct_name(),Toast.LENGTH_SHORT).show();
                Bundle arguments = new Bundle();
                arguments.putString(inventoryAllProduct.COLUMN_PRODUCT_NAME,mDataSet.get(position).getProduct_name());
                arguments.putString(inventoryAllProduct.COLUMN_PHOTO_TYPE,mDataSet.get(position).getPhoto_type());
                arguments.putString (inventoryAllProduct.COLUMN_PHOTO_NAME,mDataSet.get(position).getPhoto_name());

                ProductInfoDialog productInfoDialog = new ProductInfoDialog();
                productInfoDialog.setArguments(arguments);
                productInfoDialog.show(((FragmentActivity) mContext).getFragmentManager(), "ProductInfo Dialog");
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class MyProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        InventoryAllProducts inventoryAllProducts;
        public ImageView productPhoto;
        public TextView txtdesignname,txtname,txtgroupname;
        public EditText valueK,valueP,valueY,costK,costP,costY,minqty,maxqty;
        public MyProductViewHolder(View itemView) {
            super(itemView);
            txtdesignname = itemView.findViewById(R.id.designname);
            txtname = itemView.findViewById(R.id.name);
            txtgroupname = itemView.findViewById(R.id.groupname);
            valueK = itemView.findViewById(R.id.valuek);
            valueP = itemView.findViewById(R.id.valueP);
            valueY = itemView.findViewById(R.id.valueY);
            costK = itemView.findViewById(R.id.costK);
            costP = itemView.findViewById(R.id.costP);
            costY = itemView.findViewById(R.id.costY);
            minqty = itemView.findViewById(R.id.mqty);
            maxqty = itemView.findViewById(R.id.maxqty);
            productPhoto = itemView.findViewById(R.id.productPhoto);
        }

        public void setData(InventoryAllProducts dashboarditem) {
            this.inventoryAllProducts = dashboarditem;

//            textView.setText(dashboarditem.getTitle());
//            textView1.setText(dashboarditem.getGroupname());
//            relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }

        @Override
        public void onClick(View v) {

        }
    }
}
