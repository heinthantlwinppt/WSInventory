package com.ppt.wsinventory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.common.GlobalBus;
import com.ppt.wsinventory.common.WsEvents;
import com.ppt.wsinventory.model.AdministrationWsdashboard;
import com.ppt.wsinventory.model.WsDashboardModel;
import com.ppt.wsinventory.util.MessageBox;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28/12/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

//    DbAccess dbaccess;
    GlobalVariables appContext;
    public static final String CONFIRM_SIGN_OUT = "CONFIRM_SIGN_OUT";

    //    ArrayList<RecyclerDataModel> mValues;
    ArrayList<WsDashboardModel> mDataSet;
    private Unbinder unbinder;
    Context mContext;
    @BindView(R.id.textView)
    TextView title;
    @BindView(R.id.imageView)
    ImageView dashboard_icon;
    FragmentManager mfragmentManager;

    //    Item item;
    protected ItemListener mListener;

    public RecyclerViewAdapter(Context context, List<WsDashboardModel> mValure, ItemListener itemListener , FragmentManager fragmentManager) {
//        dbaccess = new DbAccess(context);
        appContext = (GlobalVariables)context.getApplicationContext();
        mContext = context;
        mDataSet = (ArrayList<WsDashboardModel>) mValure;
//        Toast.makeText(mContext,appContext.getParentid() + " = parent id & current id =" + appContext.getCurrentid(),Toast.LENGTH_SHORT).show();
        mfragmentManager = fragmentManager;
        mListener = itemListener;
    }

    public void goBack()
    {
        if (appContext.getParentid()==0 )
        {
            alart();
        }
        else
        {
//            dbaccess.open();
            BusinessLogic businessLogic = new BusinessLogic(appContext);
            WsDashboardModel wsdashboarditem = businessLogic.getDashboardItem(appContext.getParentid());
            List<WsDashboardModel> parentList = businessLogic.getDashboardItems(wsdashboarditem.getParent_id());
//            dbaccess.close();
            clear();
            if(parentList.size()!=0) {
                mDataSet = (ArrayList<WsDashboardModel>) parentList;
            }
        }
//
//
    }

    private void alart() {
        MessageBox.ShowMessage( mfragmentManager,
                appContext.getTranslation("Exit?"),
                appContext.getTranslation("Do you want to Exit application?"),
                CONFIRM_SIGN_OUT,
                "Cancel",
                "OK"
        );

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
                appContext.setParentid(dashboarditem.getId());
                loadChild(dashboarditem.getId());
            }
            else
            {
                appContext.setParentid(dashboarditem.getParent_id());
                GlobalBus.getBus().post(
                        new WsEvents.EventOpenScreen(dashboarditem.getActionname()));
//                Toast.makeText(mContext,"It isn't folder " + dashboarditem.isFolder(),Toast.LENGTH_LONG).show();
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
//        dbaccess.open();
        BusinessLogic businessLogic = new BusinessLogic(appContext);
        List<WsDashboardModel> childList = businessLogic.getDashboardItems(id);
//        dbaccess.close();
        clear();
//        appContext.setParentid(childList.get(0).getParent_id());
//        appContext.setCurrentid(childList.get(0).getId());
        mDataSet = (ArrayList<WsDashboardModel>) childList;
//        Toast.makeText(mContext,"It is folder CURRENT ID IS " +  childList.get(0).getId() + " & parent id is " + childList.get(0).getParent_id() ,Toast.LENGTH_LONG).show();
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
