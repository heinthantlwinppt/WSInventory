package com.ppt.wsinventory;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.ppt.wsinventory.inventory.model.InventoryAllProducts;
import com.ppt.wsinventory.model.CodeValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29/01/2018.
 */

public class ProductSearchListAdapter extends ArrayAdapter<CodeValue> {
    List<CodeValue> mRoutelist;
    Context mContext;
    GlobalVariables appcontext;

    public ProductSearchListAdapter(Context context, int resource, List<CodeValue> objects) {
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
