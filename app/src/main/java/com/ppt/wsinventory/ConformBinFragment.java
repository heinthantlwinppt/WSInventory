package com.ppt.wsinventory;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ppt.wsinventory.inventory.model.Inventory_BinLoc;
import com.ppt.wsinventory.model.InventoryBIN;
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
    EditText name,type,descrription,counter_id,barcode,tag;
//    TextView counter_id,barcode,tag;
    CheckBox active;
    Spinner location;
    DbAccess dbAccess;
    String current_id;
    Button cancle,confirm;

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

//        counter_id = (TextView) rootview.findViewById(R.id.counter_id);
        counter_id = (EditText) rootview.findViewById(R.id.counter_id);
        name = (EditText)rootview.findViewById(R.id.counter_name);
        type = (EditText)rootview.findViewById(R.id.counter_type);
        descrription = (EditText)rootview.findViewById(R.id.description);
        active = (CheckBox)rootview.findViewById(R.id.is_active);
        barcode= (EditText) rootview.findViewById(R.id.barcode);
        tag = (EditText)rootview.findViewById(R.id.tag);
        location =(Spinner)rootview.findViewById(R.id.location_id);
        cancle = (Button)rootview.findViewById(R.id.cancel_btn);
        confirm = (Button)rootview.findViewById(R.id.confirm_btn);
        String  id = appContext.getBinid();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        Inventory_BinLoc data = dbaccess.getAllInventoryBinByBin(id);

//        barcode= (TextView) rootview.findViewById(R.id.barcode);
//        tag = (TextView)rootview.findViewById(R.id.tag);

        counter_id.setText(data.getId());
        current_id = data.getId();
        name.setText(data.getBin_name());
        type.setText(data.getBin_type());
        descrription.setText(data.getBin_description());

        barcode.setText(data.getBarcode());
        tag.setText(data.getTag());
        if (data.isActive())
        {
            active.setChecked(true);
            Toast.makeText(mContext, "isActive " + data.isActive(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            active.setChecked(false);
            Toast.makeText(mContext, "isActive " + data.isActive(), Toast.LENGTH_SHORT).show();

        }
        dbAccess = new DbAccess(getContext());
        dbAccess.open();
        List<String> inventoryBINS = dbAccess.getAllLocation();

        ArrayAdapter<String> inventoryLocation = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, inventoryBINS);
        location.setAdapter(inventoryLocation);
        location.setSelection(1);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canclefunction();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmFunction();
            }
        });

        return rootview;
    }

    private void confirmFunction()
    {
        ContentValues values = new ContentValues();
        values.put(InventoryBIN.COLUMN_ID,counter_id.getText().toString());
        values.put(InventoryBIN.COLUMN_BIN_NAME,name.getText().toString());
        values.put(InventoryBIN.COLUMN_BIN_DESCRIPTION,descrription.getText().toString());
        values.put(InventoryBIN.COLUMN_BIN_TYPE,type.getText().toString());
        values.put(InventoryBIN.COLUMN_BARCODE,barcode.getText().toString());
        values.put(InventoryBIN.COLUMN_TAG,tag.getText().toString());
        values.put(InventoryBIN.COLUMN_LOCATION_ID,dbaccess.getLocaionId(location.getSelectedItem().toString()));
        values.put(InventoryBIN.COLUMN_UPDATED,0);

        if (active.isChecked()) {

            values.put(InventoryBIN.COLUMN_ACTIVE,1);
        }
        else
        {
            values.put(InventoryBIN.COLUMN_ACTIVE,0);
        }

        boolean success = dbaccess.updateData(InventoryBIN.TABLE_INVENTORY_BIN,values,InventoryBIN.COLUMN_ID + "= ?",new String []{current_id} );
        if (success)
        {
            Toast.makeText(mContext, "success fully save ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(mContext, "success Error", Toast.LENGTH_SHORT).show();

        }





    }

    private void canclefunction() {
    }
//
//    inventoryBINS = dbAccess.getAllLocation();
//    ArrayAdapter<String> inventoryLocation = new ArrayAdapter<String>(getContext(),
//            android.R.layout.simple_spinner_item, inventoryBINS);
//        Log.i("APT", "onCreateView: " + inventoryBINS.size());
//
//        location.setAdapter(inventoryLocation);


}
