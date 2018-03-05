package com.ppt.wsinventory;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ppt.wsinventory.model.AdministrationLocations;

import java.util.List;

/**
 * Created by arkar on 6/3/2018.
 */

public class LocationListAdapter extends ArrayAdapter<AdministrationLocations> {
    List<AdministrationLocations> mRoutelist;
    Context mContext;
    GlobalVariables appcontext;
    public LocationListAdapter(@NonNull Context context, int resource, List<AdministrationLocations> objects) {
        super(context, resource, objects);
        appcontext = (GlobalVariables) context.getApplicationContext();
        mContext = context;
        mRoutelist = objects;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public AdministrationLocations getItem(int position) {
        return mRoutelist.get(position);
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View layout = inflater.inflate(R.layout.location_list_item, parent, false);

        TextView textView = (TextView) layout
                .findViewById(R.id.tv_routename);
        textView.setText(mRoutelist.get(position).getLoc_name());

        //        tvItemName.setTextColor(Color.rgb(75, 180, 225));
        //        tvItemName.setTextSize(20f);
        return layout;
    }
}
