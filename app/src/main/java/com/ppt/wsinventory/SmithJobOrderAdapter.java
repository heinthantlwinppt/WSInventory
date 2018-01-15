package com.ppt.wsinventory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ppt.wsinventory.model.AdministrationStaff;
import com.ppt.wsinventory.model.AdministrationWsdashboard;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by User on 15/01/2018.
 */

public class SmithJobOrderAdapter extends ArrayAdapter {
    ArrayList<AdministrationWsdashboard> mDataSet;
    LayoutInflater mInflater;
    @BindView(R.id.OrderName)
    TextView txt_OrderName;
    @BindView(R.id.OrderItem)
    TextView txt_OrderItem;
    @BindView(R.id.textView)
    TextView txt_textView;
    @BindView(R.id.textView1)
    TextView txt_textView1;

    public SmithJobOrderAdapter(@NonNull Context context, @NonNull List<AdministrationWsdashboard> objects) {
        super(context, R.layout.smithjoborder_items, objects);
        mInflater = LayoutInflater.from(context);
        mDataSet = (ArrayList<AdministrationWsdashboard>) objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.smithjoborder_items, parent, false);
        }
        txt_OrderItem = convertView.findViewById(R.id.OrderItem);
        txt_OrderName = convertView.findViewById(R.id.OrderName);

        AdministrationWsdashboard administrationWsdashboard = mDataSet.get(position);
        txt_OrderName.setText(administrationWsdashboard.getTitle());
        txt_OrderItem.setText(administrationWsdashboard.getActionname());


        return convertView;
    }
}
