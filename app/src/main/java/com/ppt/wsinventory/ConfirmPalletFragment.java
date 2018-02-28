package com.ppt.wsinventory;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConfirmPalletFragment extends Fragment {

    EditText pallet_id,pallet_name,pallet_type,pallet_description,barcode;
    public ConfirmPalletFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_confirm_pallet, container, false);
    }
}
