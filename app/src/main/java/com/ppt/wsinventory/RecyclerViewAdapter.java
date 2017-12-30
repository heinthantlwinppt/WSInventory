package com.ppt.wsinventory;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28/12/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<RecyclerDataModel> mValues;
    ArrayList<Item> mDataSet;
    Context mContext;
//    Item item;
    protected ItemListener mListener;

    public RecyclerViewAdapter(Context context, List<Item> mValure, ItemListener itemListener) {

        mDataSet= (ArrayList<Item>) mValure;
        mContext = context;
        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView,textView1;
        public ImageView imageView,imageView1;
//        public RelativeLayout relativeLayout;
        Item item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            textView1 = (TextView)v.findViewById(R.id.textView1);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            imageView1 = (ImageView) v.findViewById(R.id.imageView1);
//            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(Item item) {
            this.item = item;

            textView.setText(item.getItemName());
            textView1.setText(item.getItemType());
//            relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }


        @Override
        public void onClick(View view) {


            if (mListener != null) {

                mListener.onItemClick(item);
//                switch (view.getId()) {
//                    case R.id.imageView1:
//                        Toast.makeText(view.getContext(), "infromation is clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.imageView:
//                        // Your Code
//                        Toast.makeText(view.getContext(), "icon is clicked", Toast.LENGTH_SHORT).show();
//                        break;
//                }
            }
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, final int position) {
        Vholder.setData(mDataSet.get(position));

        Vholder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),mDataSet.get(position).getItemType()+"infromation is clicked", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void setfilter(List<Item> itemList)
    {
        mDataSet = new ArrayList<>();
        mDataSet.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return mDataSet.size();
    }

    public interface ItemListener {
        void onItemClick(Item item);
    }
}
