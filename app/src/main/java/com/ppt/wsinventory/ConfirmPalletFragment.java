package com.ppt.wsinventory;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.ppt.wsinventory.common.BusinessLogic;
import com.ppt.wsinventory.model.AdministrationLocations;
import com.ppt.wsinventory.model.InventoryPallet;
import com.ppt.wsinventory.model.InventoryPalletLoc;
import com.ppt.wsinventory.wsdb.DbAccess;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConfirmPalletFragment extends Fragment {

    EditText pallet_id,pallet_name,pallet_type,pallet_description,barcode,tag,weight;
    CheckBox isactive,isused;
    Button confirm,cancel;
    Button weightButton;
    GlobalVariables appContext;
    private Context mContext;
    DbAccess dbaccess;
    Spinner location;
    String current_id;

    public ConfirmPalletFragment() {
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
        View rootView =  inflater.inflate(R.layout.fragment_confirm_pallet, container, false);

        pallet_id = (EditText)rootView.findViewById(R.id.pallet_counter_id);
        pallet_name =(EditText)rootView.findViewById(R.id.pallet_counter_name);
        pallet_type =(EditText)rootView.findViewById(R.id.pallet_counter_type);
        pallet_description  =(EditText)rootView.findViewById(R.id.pallet_description);
        barcode = (EditText)rootView.findViewById(R.id.pallet_barcode);
        tag = (EditText)rootView.findViewById(R.id.pallet_tag);
        weight =(EditText)rootView.findViewById(R.id.pallet_pallet_weight);
        weightButton = (Button)rootView.findViewById(R.id.weight_button);

        location =(Spinner)rootView.findViewById(R.id.pallet_location_id);
        location.setEnabled(false);

        isactive = (CheckBox)rootView.findViewById(R.id.pallet_is_active);
        isused = (CheckBox)rootView.findViewById(R.id.pallet_is_used);

        confirm =(Button)rootView.findViewById(R.id.pallet_confirm_btn);
        cancel = (Button)rootView.findViewById(R.id.pallet_cancel_btn);
        appContext = (GlobalVariables)getActivity().getApplicationContext();
        String  id = appContext.getPalletId();
        dbaccess = new DbAccess(getContext());
        dbaccess.open();
        BusinessLogic businesslogic = new BusinessLogic(getContext());



        InventoryPalletLoc inventorypalletloc = businesslogic.getPalletById(id);
        current_id = inventorypalletloc.getId();

        pallet_name.setText(inventorypalletloc.getPallet_name());
        pallet_id.setText(inventorypalletloc.getId());
        pallet_type.setText(inventorypalletloc.getPallet_type());
        pallet_description.setText(inventorypalletloc.getPallet_description());
        barcode.setText(inventorypalletloc.getPallet_barcode());
        tag.setText(inventorypalletloc.getPallet_tag());
        weight.setText(inventorypalletloc.getPallet_weight());


        Toast.makeText(mContext, "Result " + inventorypalletloc.getPallet_weight()+ " & " +inventorypalletloc.getPallet_active(), Toast.LENGTH_SHORT).show();

        if (inventorypalletloc.getPallet_active())
        {
            isactive.setChecked(true);
        }
        else
        {
            isactive.setChecked(false);
        }

        if (inventorypalletloc.isPallet_is_used())
        {
            isused.setChecked(true);
        }
        else {
            isused.setChecked(false);
        }


        List<AdministrationLocations> locationslist = businesslogic.getAllLocation();
        LocationListAdapter locationlistadapter =
                new LocationListAdapter(getContext(),
                        android.R.layout.simple_spinner_item, locationslist);
//        ArrayAdapter<String> inventoryLocation = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_spinner_item, locationslist);
        location.setAdapter(locationlistadapter);
        int idx = 0;
        for (AdministrationLocations loc : locationslist) {
            if (loc.getId().equalsIgnoreCase(inventorypalletloc.getPallet_location_id())) {
                idx = locationlistadapter.getPosition(loc);
                break;
            }
        }
        location.setSelection(idx);

        cancel.setOnClickListener(new View.OnClickListener() {
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

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.counter_confirm){
            Toast.makeText(getContext(),"Confirm",Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.counter_confirm_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void confirmFunction()
    {
        ContentValues values = new ContentValues();
        values.put(InventoryPalletLoc.COLUMN_ID,pallet_id.getText().toString());
        values.put(InventoryPalletLoc.COLUMN_PALLET_NAME,pallet_name.getText().toString());
        values.put(InventoryPalletLoc.COLUMN_PALLET_DESCRIPTION,pallet_description.getText().toString());
        values.put(InventoryPalletLoc.COLUMN_PALLET_TYPE,pallet_type.getText().toString());
        values.put(InventoryPalletLoc.COLUMN_PALLET_BARCODE,barcode.getText().toString());
        values.put(InventoryPalletLoc.COLUMN_PALLET_TAG,tag.getText().toString());
        values.put(InventoryPalletLoc.COLUMN_PALLET_WEIGHT,weight.getText().toString());

        if (isactive.isChecked())
        {
            values.put(InventoryPalletLoc.COLUMN_PALLET_ACTIVE,1);
        }
        else
        {
            values.put(InventoryPalletLoc.COLUMN_PALLET_ACTIVE,0);
        }
        if (isused.isChecked())
        {
            values.put(InventoryPalletLoc.COLUMN_PALLET_IS_USED,1);
        }
        else
        {
            values.put(InventoryPalletLoc.COLUMN_PALLET_IS_USED,0);
        }
        values.put(InventoryPallet.COLUMN_UPDATED,0);
        boolean success = dbaccess.updateData(InventoryPallet.TABLE_INVENTORY_PALLET,values,InventoryPallet.COLUMN_ID + "= ?", new String[]{current_id});

        if (success)
        {
            Intent intent = new Intent(mContext, InventoryTrays.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            Toast.makeText(mContext, "success fully save ", Toast.LENGTH_SHORT).show();


        }
        else
        {
            Toast.makeText(mContext, "success Error", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(mContext, InventoryTrays.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            System.exit(0);


        }
    }

    private void canclefunction() {
        Intent intent = new Intent(mContext, InventoryTrays.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
        System.exit(0);

    }
}
