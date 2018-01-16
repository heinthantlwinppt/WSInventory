package com.ppt.wsinventory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.AdministrationWsdashboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28/12/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //    ArrayList<RecyclerDataModel> mValues;
    ArrayList<AdministrationWsdashboard> mDataSet;
    private Unbinder unbinder;
    Context mContext;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView1)
    ImageView imageView1;


    //    Item item;
    protected ItemListener mListener;

    public RecyclerViewAdapter(Context context, List<AdministrationWsdashboard> mValure, ItemListener itemListener) {

        mDataSet = (ArrayList<AdministrationWsdashboard>) mValure;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView, textView1;
        public ImageView imageView, imageView1;
        //        public RelativeLayout relativeLayout;
        AdministrationWsdashboard dashboarditem;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
            textView = (TextView) v.findViewById(R.id.textView);
            textView1 = (TextView) v.findViewById(R.id.textView1);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            imageView1 = (ImageView) v.findViewById(R.id.imageView1);
//            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(AdministrationWsdashboard dashboarditem) {
            this.dashboarditem = dashboarditem;

            textView.setText(dashboarditem.getTitle());
            textView1.setText(dashboarditem.getGroupname());
//            relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }


        @Override
        public void onClick(View view) {


            if (mListener != null) {

//                mListener.onItemClick(dashboarditem);
                GlobalBus.getBus().post(
                        new WsEvents.EventOpenScreen(dashboarditem.getActionname())
                );

            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, final int position) {
        Vholder.setData(mDataSet.get(position));

        Vholder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), mDataSet.get(position).getTitle() + "infromation is clicked", Toast.LENGTH_SHORT).show();

            }
        });

//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), mDataSet.get(position).getTitle() + "infromation is clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public void setfilter(List<AdministrationWsdashboard> itemList) {
        mDataSet = new ArrayList<>();
        mDataSet.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return mDataSet.size();
    }

    public interface ItemListener {
        void onItemClick(AdministrationWsdashboard item);
    }
}
