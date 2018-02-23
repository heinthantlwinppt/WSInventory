package com.ppt.wsinventory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
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
import com.ppt.wsinventory.model.WsDashboardModel;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28/12/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    DbAccess dbaccess;
    GlobalVariables appContext;

    //    ArrayList<RecyclerDataModel> mValues;
    ArrayList<WsDashboardModel> mDataSet;
    private Unbinder unbinder;
    Context mContext;
    @BindView(R.id.textView)
    TextView title;
    @BindView(R.id.imageView)
    ImageView dashboard_icon;


    //    Item item;
    protected ItemListener mListener;

    public RecyclerViewAdapter(Context context, List<WsDashboardModel> mValure, ItemListener itemListener) {
        dbaccess = new DbAccess(context);
        appContext = (GlobalVariables)context.getApplicationContext();
        mContext = context;
        mDataSet = (ArrayList<WsDashboardModel>) mValure;

        mListener = itemListener;
    }

    public void goBack()
    {
        if (appContext.getLatest_parient_id()==0)
        {

        }
        else
        {
            dbaccess.open();
            List<WsDashboardModel> parientList = dbaccess.getparient(appContext.getLatest_parient_id());
            dbaccess.close();
            clear();
//            appContext.setLatest_parient_id(parientList.get(0).getId());
            mDataSet = (ArrayList<WsDashboardModel>) parientList;

            Toast.makeText(mContext,appContext.getLatest_parient_id()+" it is " + parientList.size() ,Toast.LENGTH_LONG).show();


        }
        Toast.makeText(mContext,"it is back press" , Toast.LENGTH_SHORT).show();
    }




    public class ViewHolder extends RecyclerView.
            ViewHolder implements View.OnClickListener {

        public TextView title;
        public ImageView dashboard_icon;
        //        public RelativeLayout relativeLayout;
        WsDashboardModel dashboarditem;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
            title = (TextView) v.findViewById(R.id.textView);
            dashboard_icon = (ImageView) v.findViewById(R.id.imageView);
//            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
        }

        public void setData(WsDashboardModel dashboarditem) {
            this.dashboarditem = dashboarditem;

            title.setText(dashboarditem.getTitle());
            File sd = Environment.getExternalStorageDirectory();
            File folder = new File(sd.getAbsolutePath()  , "WsImages/hh_dashboard/"+ dashboarditem.getImage());
            Bitmap bitmap = BitmapFactory.decodeFile(folder.getAbsolutePath());
            dashboard_icon.setImageBitmap(bitmap);
//            relativeLayout.setBackgroundColor(Color.parseColor(item.color));

        }



        @Override
        public void onClick(View view) {

            if (dashboarditem.isFolder())
            {
                loadChild(dashboarditem.getId());
            }
            else
            {
                Toast.makeText(mContext,"It isn't folder " + dashboarditem.isFolder(),Toast.LENGTH_LONG).show();
            }


//            if (mListener != null) {
//
////                mListener.onItemClick(dashboarditem);
//                GlobalBus.getBus().post(
//                        new WsEvents.EventOpenScreen(dashboarditem.getActionname()));
//
//            }
        }


    }

    public void loadChild(int id) {
        dbaccess.open();
        List<WsDashboardModel> childList = dbaccess.getAllChild(id);
        dbaccess.close();
        clear();
        appContext.setLatest_parient_id(id);
        mDataSet = (ArrayList<WsDashboardModel>) childList;
        Toast.makeText(mContext,"It is folder " +  childList.size() ,Toast.LENGTH_LONG).show();
    }

    private void clear() {

        final int size = mDataSet.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                mDataSet.remove(0);
            }

            notifyItemRangeRemoved(0, size);
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


    }

    public void setfilter(List<WsDashboardModel> itemList) {
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
