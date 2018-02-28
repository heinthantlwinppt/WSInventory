package com.ppt.wsinventory;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ppt.wsinventory.inventory.model.Inventory_PalletLoc;
import com.ppt.wsinventory.model.CodeValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PPT on 2/26/2018.
 */

public class LocSpinnerListAdapter extends ArrayAdapter<CodeValue> {

    List<CodeValue> mRoutelist = new ArrayList<>();
    Context mContext;
    GlobalVariables appcontext;

    public LocSpinnerListAdapter(Context context, int resource, List<CodeValue> objects) {
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

    public CodeValue getItem(int position) {
        return mRoutelist.get(position);
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        View layout = inflater.inflate(R.layout.product_searchresult_list, parent, false);

        TextView textView = (TextView) layout
                .findViewById(R.id.tv_value);
        textView.setText(mRoutelist.get(position).getValue());

//        tvItemName.setTextColor(Color.rgb(75, 180, 225));
//        tvItemName.setTextSize(20f);
        return layout;
    }

}
