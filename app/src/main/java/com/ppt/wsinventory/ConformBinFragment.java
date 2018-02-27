package com.ppt.wsinventory;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.inventory.model.Inventory_BinLoc;
import com.ppt.wsinventory.wsdb.DbAccess;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConformBinFragment extends Fragment {
    private GlobalVariables appContext;
    private Context mContext;
    DbAccess dbaccess;
    EditText counter_id,name,type,descrription;
    TextView barcode,tag;
    CheckBox active;
    Spinner location;
    DbAccess dbAccess;

    public ConformBinFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        appContext = (GlobalVariables) context
                .getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview =
         inflater.inflate(R.layout.fragment_conform_bin, container, false);

        counter_id = (EditText)rootview.findViewById(R.id.counter_id);
        name = (EditText)rootview.findViewById(R.id.counter_name);
        type = (EditText)rootview.findViewById(R.id.counter_type);
        descrription = (EditText)rootview.findViewById(R.id.description);
        active = (CheckBox)rootview.findViewById(R.id.is_active);
        barcode= (TextView) rootview.findViewById(R.id.barcode);
        tag = (TextView)rootview.findViewById(R.id.tag);
        location =(Spinner)rootview.findViewById(R.id.location);
        String  id = appContext.getBinid();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        Inventory_BinLoc data = dbaccess.getAllInventoryBinByBin(id);


        counter_id.setText(data.getId());
        name.setText(data.getBin_name());
        type.setText(data.getBin_type());
        descrription.setText(data.getBin_description());

        barcode.setText(data.getBarcode());
        tag.setText(data.getTag());
        if (data.isActive())
        {
            active.setChecked(true);
        }
        else
        {
            active.setChecked(false);
        }
        dbAccess = new DbAccess(getContext());
        dbAccess.open();
        List<String> inventoryBINS = dbAccess.getAllLocation();
//
//        ArrayAdapter<String> inventoryLocation = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_item, inventoryBINS);
//        location.setAdapter(inventoryLocation);
        return rootview;
    }
//
//    inventoryBINS = dbAccess.getAllLocation();
//    ArrayAdapter<String> inventoryLocation = new ArrayAdapter<String>(getContext(),
//            android.R.layout.simple_spinner_item, inventoryBINS);
//        Log.i("APT", "onCreateView: " + inventoryBINS.size());
//
//        location.setAdapter(inventoryLocation);


}
